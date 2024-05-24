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

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		//******************************************************************************************
		//         S E C T I O N   F O R   V A R I A B L E S     I N I T I A L I Z A T I O N 
		//******************************************************************************************
		String propFilePath = "config\\config.properties";
		Utility.printStartupBanner1();
        //System.out.println(Utility.readProperties(propFilePath));
		
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
         
         //Analysis and Design Section
         String isAnalysisAndDesignSectionEnabled = propertiesReader.getIsAnalysisAndDesignSectionEnabled();
         String pathForAnalysisAndDesignTemplate = propertiesReader.getPathForAnalysisAndDesignTemplate();
         
         
         //All the exclude extensions are pushed to an ArrayList
         List<String> excludeExtensionlist = new ArrayList<String>();
         if (complexityAnalysisExcludeExtension != null) {
        	 String[] items = complexityAnalysisExcludeExtension.split(",");
        	 for (String item : items) {
        		 excludeExtensionlist.add(item.trim());
        	 }

         }
         
         List<String> analysisSubCategoryListByUser = new ArrayList<String>();
         String analysisSubCategoryStringByUser = propertiesReader.getAnalysisSubCategoryListByUser();
         if (analysisSubCategoryStringByUser != null) {
        	 String[] items = analysisSubCategoryStringByUser.split(",");
        	 for (String item : items) {
        		 analysisSubCategoryListByUser.add(item.trim());
        	 }

         }
         //**********************************************************************************************
         //    H T M L   R E P O R T    I N I T I A L I Z A T I O N      S E C T I O N
         //**********************************************************************************************
         StringBuffer outPutReport = new StringBuffer();
 		 outPutReport.append(Utility.generateHtmlStringHeader(applicationName));
 		 outPutReport.append(Utility.generateHtmlStringBody(applicationName));
 		 
 		 
 		 
 		 
 		if(isComplexityAnalysisEnabled.equalsIgnoreCase("true")) {
 			Utility.printComplexityAnalysisConsole();
			
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
		System.out.println("  Section 2 :   Executing Ruleset for Code Analysis     		 ");
		//System.out.println("*****************************************************************");	
		List<SummaryBean> summaryBeans = new ArrayList<SummaryBean>();
		
		 Gson gson = new Gson();
	        try (Reader reader = new FileReader(rulesetFile)) {
	            Ruleset ruleset = gson.fromJson(reader, Ruleset.class);
	            Utility.printRuleSetDetailsConsole(ruleset);
	            
	            
	            List<Rule> rules = ruleset.getRuleset();
		            for (Rule rule : rules) {
		            	int ruleNo = rule.getRuleNo();
		            	String ruleDescription = rule.getRuleDescription();
				        if(rule.isRuleEnabled()) {		                
				             boolean isAlertEnabled = rule.isAlertEnabled();
				             int matchCount =0;		                
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
				        	            	
				        	            	SummaryBean summaryBean = new SummaryBean();
				        	            	summaryBean.setRuleType(rule.getRuleType());
				        	            	summaryBean.setCountMatch(matchCount);
				        	            	summaryBean.setAlert(isAlertEnabled);
				        	            	summaryBeans.add(summaryBean);
					    	                
					    	                outPutReport.append(Utility.generateHtmlTableEndCollapsible());
				        	            }
				                    }		                    
				                    
				                    
				                } catch (IOException e) {
				                    e.printStackTrace();
				                }
				                
				            }else {
		            	System.out.println("Rule "+ruleNo+" Skipped*** -->"+ruleDescription);
		            }
	            }

	            //***********************************************************************************
		        // SECTION FOR A&D SECTION
		        //***********************************************************************************
		            String analysisAndDesignSection = "";
		        if(isAnalysisAndDesignSectionEnabled.equals(isAnalysisAndDesignSectionEnabled)) {
		        	
		        	Utility.printAnalysisAndDesignConsole();
		        	
		        	Reader analysisReader = new FileReader(pathForAnalysisAndDesignTemplate);
				    AnalysisDocumentSet analysisRulesetSet = gson.fromJson(analysisReader, AnalysisDocumentSet.class);
				    Utility.printRuleSetForAnalysisConsole(analysisRulesetSet);

				    analysisAndDesignSection = Utility.generateHTMLForAnalaysisAndDesignV2(
															    		analysisSubCategoryListByUser,
															    		analysisRulesetSet.getAnalysisDocSet(), 
															    		summaryBeans);
				    //Section To do the Complexity Analysis
				    
		        }
			    
		            
		            
		            
	            outPutReport.append(Utility.generateHtmlEndReport());
	            
                String summerySection = Utility.generateSummery(summaryBeans);
                String report = outPutReport.toString();
                
                //String finalReport = Utility.generateFinalReport(summerySection,report);
                String reportWithComplexitySection = Utility.generateFinalReport(summerySection,report);
                String finalReport = Utility.generateFinalReportWithAnalysis(reportWithComplexitySection,
                																analysisAndDesignSection, 
                																isAnalysisAndDesignSectionEnabled);
                
                Utility.writeStringBufferToHtmlReport(new StringBuffer(finalReport), outputReportGenPath);
	            
	            System.out.println("Report Generated Successfully !!!");
	            
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
