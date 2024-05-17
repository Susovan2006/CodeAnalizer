package com.susovan.codeanalizer.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.susovan.codeanalizer.main.LineCounter.FileData;




public class CodeAnalizerNewTemplate {

	public static void main(String[] args) throws IOException {
		
		
		//******************************************************************************************
		//         S E C T I O N   F O R   V A R I A B L E S     I N I T I A L I Z A T I O N 
		//******************************************************************************************
		String propFilePath = "config\\config.properties";
        System.out.println(Utility.readProperties(propFilePath));
		
        PropertiesReader propertiesReader = new PropertiesReader();
        propertiesReader.readProperties(propFilePath);
        
         String applicationName = propertiesReader.getApplicationName();
         String projectScanDirectory = propertiesReader.getProjectScanDirectory();
         String outputReportGenPath = propertiesReader.getOutputReportGenPath();
         String rulesetFile = propertiesReader.getRulesetFile();
        
         //These variables are applicavle for the Code Complexity Analysis.
         String isComplexityAnalysisEnabled = propertiesReader.getIsComplexityAnalysisEnabled();
         String complexityAnalysisExcludeExtension = propertiesReader.getComplexityAnalysisExcludeExtension();
         String complexityCriteria = propertiesReader.getComplexityCriteria();
         
         //All the exclude extensions are pushed to an ArrayList
         List<String> excludeExtensionlist = new ArrayList<String>();
         if (complexityAnalysisExcludeExtension != null) {
        	 String[] items = complexityAnalysisExcludeExtension.split(",");
        	 for (String item : items) {
        		 excludeExtensionlist.add(item.trim());
        	 }

         }
         //**********************************************************************************************
         //    H T M L   R E P O R T    I N I T I A L I Z A T I O N      S E C T I O N
         //**********************************************************************************************
         StringBuffer outPutReport = new StringBuffer();
 		 outPutReport.append(Utility.generateHtmlStringHeader(applicationName));
 		 outPutReport.append(Utility.generateHtmlStringBody(applicationName));
 		 
 		 
 		 
 		 
 		if(isComplexityAnalysisEnabled.equalsIgnoreCase("true")) {
	 		System.out.println("**********************************************************");
	 		System.out.println("   Section 1 :   Complexity Analysis Section			  ");
	 		System.out.println("**********************************************************");
	 		System.out.println("");
	 		System.out.println("Step 1 --> Searching in the Project for File Extensions......");
			
	 		List<String> filteredExtensions = FileScannerForExtension.getUniqueFileExtensions(Paths.get(projectScanDirectory), excludeExtensionlist);
			
	 		System.out.println("Step 2 --> Extensions collected from the Code.");
			System.out.println("Step 3 --> Fetching Line Count and File Count, It might take sometime......");
			
			List<FileData> counts = LineCounter.countFilesAndLinesByExtension(Paths.get(projectScanDirectory), filteredExtensions);
			String complexity = ComplexityUtil.getComplexity(complexityCriteria, counts);
			
			//This Section Will Generate the Collapsible Section to Display the Complexity Data. Basically this is the Heading.
			outPutReport.append(Utility.generateHtmlStringComplexityCollapsible(applicationName));
			outPutReport.append(Utility.addComplexityToHtmlReport(complexity));
			outPutReport.append(Utility.generateHtmlStringTableHeader());
			outPutReport.append(Utility.createTableRows(counts));
			outPutReport.append(Utility.endHtmlTable());

			System.out.println("Step 4 --> Complexity Analysis Completed Successfully !!!!"); 			
 		}
         
         
 		 
 		 
 		 
		System.out.println("*****************************************************************");
		System.out.println("  Section 2 :             Processing for The code Analysis		 ");
		System.out.println("*****************************************************************");	
		List<SummeryBean> summeryBeans = new ArrayList<SummeryBean>();
		
		 Gson gson = new Gson();
	        try (Reader reader = new FileReader(rulesetFile)) {
	            Ruleset ruleset = gson.fromJson(reader, Ruleset.class);
	           
	            
	            
	            List<Rule> rules = ruleset.getRuleset();
	            for (Rule rule : rules) {
	                int ruleNo = rule.getRuleNo();
	                boolean isAlertEnabled = rule.isAlertEnabled();
	                int matchCount =0;
	                String ruleDescription = rule.getRuleDescription();
	                List<String> listOfFileExtensions = rule.getListOfSearchFileType();
	                List<String> listOfSearchString = rule.getListOfSearchString();
	                List<String> listOfSearchRegex = rule.getListOfSearchRegex();
	                List<String> listOfSearchFilePattern = rule.getListOfSearchFilePattern();
	                //System.out.println("listOfSearchRegex"+listOfSearchRegex);
	                System.out.println("Executing Rule "+ruleNo+" -->"+ruleDescription);
	                try {
	                    List<String> files = listFiles(projectScanDirectory, listOfFileExtensions);
	                    
	                    //In case we have a Matching list of files.
	                    if(files.size()>0) {
	                    	//outPutReport.append(HtmlFormatter.generateHtmlTableHeader());
	                    	StringBuffer detailReviewData = new StringBuffer();
	        	            for (String file : files) {
	        	            	
	        	            	
	        	            	//Added on June 16 for File Pattern Search
	        	            	if(listOfSearchFilePattern!=null && listOfSearchFilePattern.size()>0) {
	        	            		List<String> matchFileNames = FileNameMatch.matchFiles(listOfSearchFilePattern, new File(file).getName());
	        	            		if(matchFileNames!=null && matchFileNames.size() > 0) {
	        	            			StringBuilder htmlString = new StringBuilder();
	        	            			for (String matchFileName : matchFileNames) {	        	                    	
		        	                    	htmlString.append("<p style=\"color:#303030;\"><i><mark>").append(matchFileName).append("</mark></i></p>");
		        	                    	matchCount++;
		        	                    }            			
	        	            			detailReviewData.append(Utility.generateHtmlTableData("Matching File Name Found",htmlString.toString()));
	        	            		}
	        	            	
	        	            	}
	        	            	
	        	            	//Checking for searched string
		        	            if(listOfSearchString!=null && listOfSearchString.size() > 0) {
		        	            List<String> matchingLines = searchInFile(file, listOfSearchString);
		        	            	if (!matchingLines.isEmpty()) {
		        	  
		        	            		
		        	            		StringBuilder htmlString = new StringBuilder();
		        	            		
		        	                    for (String matchingLine : matchingLines) {	        	                    	
		        	                    	htmlString.append("<p style=\"color:#303030;\"><i>").append(matchingLine).append("</i></p>");
		        	                    	matchCount++;
		        	                    }
		        	                    
		        	                    //The delail review section will applear inase there is a match	
		        	                    detailReviewData.append(Utility.generateHtmlTableData(new File(file).getAbsolutePath(),htmlString.toString()));
		        	                    
		        	                    
		        	                } 
		        	            }
		        	            //Checking for Regex Expression
		        	            //System.out.println(listOfSearchRegex +"--"+file);
		        	            if(listOfSearchRegex!=null && listOfSearchRegex.size() > 0 && !listOfSearchRegex.get(0).equals("")) {
			        	            List<String> matchingLines = searchRegexInFile(file, listOfSearchRegex);
			        	            	if (!matchingLines.isEmpty()) {
			        	  
			        	            		
			        	            		StringBuilder htmlString = new StringBuilder();
			        	            		
			        	                    for (String matchingLine : matchingLines) {	        	                    	
			        	                    	htmlString.append("<p style=\"color:#303030;\"><i>").append(matchingLine).append("</i></p>");
			        	                    	matchCount++;
			        	                    }
			        	                    
			        	                    //The delail review section will applear inase there is a match	
			        	                    detailReviewData.append(Utility.generateHtmlTableData(new File(file).getAbsolutePath(),htmlString.toString()));
			        	                    
			        	                    
			        	                } 
			        	            }
		                    }
	        	            //The summery will be displayed in case there is a match.
	        	            if(matchCount>0) {
	        	            	outPutReport.append(Utility.generateHtmlStringRuleDetailsCollapsible(ruleNo,ruleDescription));
	        	            	outPutReport.append(Utility.generateHtmlTableHeader());
	        	            	outPutReport.append(detailReviewData);
	        	            	
		        	            SummeryBean summeryBean = new SummeryBean();
		    	                summeryBean.setRuleType(rule.getRuleType());
		    	                summeryBean.setCountMatch(matchCount);
		    	                summeryBean.setAlert(isAlertEnabled);
		    	                summeryBeans.add(summeryBean);
		    	                
		    	                outPutReport.append(Utility.generateHtmlTableEndCollapsible());
	        	            }
	                    }//else {
	                    	//System.out.println("No match found");
	                    	//outPutReport.append(HtmlFormatter.generateNoMatch("No match found"));
	                    //}
	                    
	                    
	                    
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                
	                  
	            }

	            
	            outPutReport.append(Utility.generateHtmlEndReport());
	            
                String summerySection = Utility.generateSummery(summeryBeans);
                String report = outPutReport.toString();
                
                String finalReport = Utility.generateFinalReport(summerySection,report);
                
                Utility.writeStringBufferToHtmlReport(new StringBuffer(finalReport), outputReportGenPath);
	            
	            System.out.println("Report Generated");
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}
	
	
	public static List<String> listFiles(String dirPath, final List<String> fileExtensions) throws IOException {
        final List<String> files = new ArrayList<>();

        Files.walkFileTree(Paths.get(dirPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                for (String extension : fileExtensions) {
                    if (file.toString().endsWith(extension)) {
                        files.add(file.toString());
                        break;
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return files;
    }
	
	public static List<String> searchInFile(String fileName, List<String> searchStrings) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> matchingLines = new ArrayList<>();
        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            for (String searchString : searchStrings) {
                if (line.toLowerCase().contains(searchString.toLowerCase())) {
                    matchingLines.add("Line " + lineNumber + "--> " + Utility.highlightYellowString(Utility.sanitizeForHtml(line),searchString ));
                    break;
                }
            }
        }
        reader.close();
        return matchingLines;
    }
	
	
	public static List<String> searchRegexInFile(String fileName, List<String> searchRegexExps) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> matchingLines = new ArrayList<>();

        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            for (String searchRegexExp : searchRegexExps) {
            	//searchRegexExp="\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
            	//System.out.println("Regex -->"+searchRegexExp);
            	Pattern pattern = Pattern.compile(searchRegexExp);
            	
            	Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                	matchingLines.add("Line " + lineNumber + "--> " + Utility.highlightYellowString(Utility.sanitizeForHtml(line),matcher.group()));
                	break;
                }
            }
        }
        reader.close();
        return matchingLines;
    }

}
