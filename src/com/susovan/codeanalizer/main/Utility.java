package com.susovan.codeanalizer.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.susovan.codeanalizer.main.LineCounter.FileData;
import com.susovan.codeanalizer.main.SummeryBean;


public class Utility {

	
	public static String generateHtmlStringHeader(String heading) {
        String htmlString = "<!DOCTYPE html>\r\n" + 
        		"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
        		"<head>\r\n" + 
        		"    <title>"+heading+"</title>\r\n" + 
        		"    <style media=\"screen\">\r\n" + 
        		"        body {\r\n" + 
        		"            font-family: 'Segoe UI','Microsoft Sans Serif',sans-serif;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        header:before, header:after {\r\n" + 
        		"            content: \" \";\r\n" + 
        		"            display: table;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        header:after {\r\n" + 
        		"            clear: both;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		".invoiceNbr {\r\n" + 
        		"			background: linear-gradient(to top, #e0e0e0, #ffffff);\r\n" + 
        		"            font-size: 40px;          \r\n" + 
        		"			color: black; \r\n" + 
        		"            padding: 20px;\r\n" + 
        		"        }"+
        		"\r\n" + 
        		"        .logo {\r\n" + 
        		"            float: left;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .from {\r\n" + 
        		"            float: left;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .to {\r\n" + 
        		"            float: right;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .fromto {\r\n" + 
        		"            border-style: solid;\r\n" + 
        		"            border-width: 1px;\r\n" + 
        		"            border-color: #e8e5e5;\r\n" + 
        		"            border-radius: 5px;\r\n" + 
        		"            margin: 10px;\r\n" + 
        		"            min-width: 150px;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"		.alert {\r\n" + 
            	"            border-style: solid;\r\n" + 
            	"            border-width: 1px;\r\n" + 
            	"            border-color: #fac5c5;\r\n" + 
            	"            border-radius: 5px;\r\n" + 
            	"            margin: 10px;\r\n" + 
            	"            min-width: 150px;\r\n" + 
            	"        }\r\n" + 
            	"\r\n" + 
        		"        .fromtocontent {\r\n" + 
        		"            margin: 10px;\r\n" + 
        		"            margin-right: 15px;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .panel {\r\n" + 
        		"            background-color: #e8e5e5;\r\n" + 
        		"            padding: 7px;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" +
        		"        .generalPanel {\r\n" + 
        		"            background-color: #e8e5e5;\r\n" + 
        		"            padding: 7px;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .alertPanel {\r\n" + 
        		"            background-color: #fac5c5;\r\n" + 
        		"            padding: 7px;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .items {\r\n" + 
        		"            clear: both;\r\n" + 
        		"            display: table;\r\n" + 
        		"            padding: 20px;\r\n" + 
        		"        }\r\n" + 
        		".rules{\r\n" + 
        		"            clear: both;\r\n" + 
        		"            padding: 5px;\r\n" + 
        		"        }" +
        		"\r\n" + 
        		"        /* Factor out common styles for all of the \"col-\" classes.*/\r\n" + 
        		"        div[class^=\"col-\"] {\r\n" + 
        		"            display: table-cell;\r\n" + 
        		"            padding: 7px;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        /*for clarity name column styles by the percentage of width */\r\n" + 
        		"        .col-1-10 {\r\n" + 
        		"            width: 10%;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .col-1-52 {\r\n" + 
        		"            width: 52%;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		"        .row {\r\n" + 
        		"            display: table-row;\r\n" + 
        		"            page-break-inside: avoid;\r\n" + 
        		"        }\r\n" + 
        		"\r\n" + 
        		".collapsible {\r\n" + 
        		"  background-color: white;\r\n" + 
        		"  color: black;\r\n" + 
        		"  cursor: pointer;\r\n" + 
        		"  padding: 5px;\r\n" + 
        		"  width: 100%;\r\n" + 
        		"  border: none;\r\n" + 
        		"  text-align: left;\r\n" + 
        		"  outline: none;\r\n" + 
        		"  font-size: 15px;\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		".active, .collapsible:hover {\r\n" + 
        		"  background-color: lavender;\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		".content {\r\n" + 
        		"  padding: 0 10px;\r\n" + 
        		"  max-height: 0;\r\n" + 
        		"  overflow: hidden;\r\n" + 
        		"  transition: max-height 0.4s ease-out;\r\n" + 
        		"  background-color: #ffffff;\r\n" + 
        		"}"+
        		//This Section is for the Table to show the Complexity
        		"#customers {\r\n" + 
        		"  font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"  border-collapse: collapse;\r\n" + 
        		"  width: 50%;\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		"#customers td, #customers th {\r\n" + 
        		"  border: 1px solid #ddd;\r\n" + 
        		"  padding: 8px;\r\n" + 
        		"}\r\n" + 
        		"\r\n" + 
        		"#customers tr:nth-child(even){background-color: #f2f2f2;}\r\n" + 
        		"\r\n" + 
        		"#customers tr:hover {background-color: #ddd;}\r\n" + 
        		"\r\n" + 
        		"#customers th {\r\n" + 
        		"  padding-top: 12px;\r\n" + 
        		"  padding-bottom: 12px;\r\n" + 
        		"  text-align: left;\r\n" + 
        		"  background-color: #04AA6D;\r\n" + 
        		"  color: white;\r\n" + 
        		"} \r\n"+
        		"    </style>\r\n" + 
        		"\r\n" + 
        		"</head>\r\n";

        return htmlString;
    }
	
	
	public static String generateHtmlStringBody(String appName) {
		
		String htmlString ="<body>\r\n" + 
				"    <header>\r\n" + 
				"        <div class=\"invoiceNbr\">\r\n" + 
				"            Application Code analysis Report for "+appName+"\r\n" + 
				"        </div>\r\n" + 
				"        \r\n" + 
				"    </header> <Br> <Br><Br> <Br>"
				+ "\r\n "
				+ "*******************\r\n";
		
		return htmlString;
	}
	
	public static String generateSummery(List<SummeryBean> summeryBeanList) {
		StringBuffer summery = new StringBuffer();
		for (SummeryBean summeryBean : summeryBeanList) {
			summery.append(generateHtmlStringSummeryDiv(summeryBean.getRuleType(),summeryBean.getCountMatch(), summeryBean.isAlert()));
        }
		return summery.toString();
	}
	
	public static String generateHtmlStringSummeryDiv(String ruleType, int countMatch, boolean alert) {
		String htmlString;
		if(alert) {
			htmlString = "<div class=\"alert from\">\r\n" + 
					"        <div class=\"alertPanel\">"+ruleType+"</div>\r\n" + 
					"        <div class=\"fromtocontent\">\r\n" + 
					"            <span>"+countMatch+"</span><br />\r\n" + 
					"        </div>\r\n" + 
					"    </div>\r\n";
		}else {
			htmlString = "<div class=\"fromto from\">\r\n" + 
					"        <div class=\"generalPanel\">"+ruleType+"</div>\r\n" + 
					"        <div class=\"fromtocontent\">\r\n" + 
					"            <span>"+countMatch+"</span><br />\r\n" + 
					"        </div>\r\n" + 
					"    </div>\r\n";
		}
		
		return htmlString;
	}
	
	
	public static String generateHtmlStringRuleDetailsCollapsible(int ruleNo, String RuleDetails) {
		String htmlString = "<button class=\"collapsible\">\r\n" + 
				"	<p style=\"color:black; font-size:20px; font-family:Courier;\"> "
				+ "Rule No : "+ruleNo+":"+RuleDetails+" </p> \r\n"+
				"	</button> \r\n"
				+"<div class=\"content\"> \r\n"
				+ "<section class=\"items\"> \r\n";
		return htmlString;
	}
	
	public static String generateHtmlStringRuleDetails(int ruleNo, String RuleDetails) {
		String htmlString = "<section class=\"rules\">\r\n" + 
				"	<h5 style=\"color:Green;\"> Rule No : "+ruleNo+":"+RuleDetails+" </h5> \r\n" + 
				"	</section> \r\n"
				+ "<section class=\"items\"> \r\n";
		return htmlString;
	}
	
	public static String generateHtmlTableHeader() {
		String htmlString="<div class=\"row\">\r\n" + 
				"            <div class=\"col-1-10 panel\">\r\n" + 
				"                Java Files\r\n" + 
				"            </div>\r\n" + 
				"            <div class=\"col-1-52 panel\">\r\n" + 
				"                Matches\r\n" + 
				"            </div>\r\n" + 
				"        </div> \r\n";
		return htmlString;
	}
	
	public static String generateHtmlTableData(String fileName, String matches) {
		String htmlString = "<div class=\"row\">\r\n" + 
				"            <div class=\"col-1-10\">\r\n" + 
				"                <p style=\"color:#303030;\"><i>"+fileName+"</i></p>\r\n" + 
				"            </div>\r\n" + 
				"            <div class=\"col-1-52\">\r\n" + 
				"                "+ matches+"\r\n" + 
				"            </div>\r\n" + 
				"        </div> \r\n";
		
		return htmlString;
	}
	
	
	public static String generateHtmlTableEnd() {

		return "</section>";
	}
	public static String generateHtmlTableEndCollapsible() {

		return "</div></section>";
	}
	public static String generateHtmlEndReport() {
		String htmlString="<script>\r\n" + 
				"var coll = document.getElementsByClassName(\"collapsible\");\r\n" + 
				"var i;\r\n" + 
				"\r\n" + 
				"for (i = 0; i < coll.length; i++) {\r\n" + 
				"  coll[i].addEventListener(\"click\", function() {\r\n" + 
				"    this.classList.toggle(\"active\");\r\n" + 
				"    var content = this.nextElementSibling;\r\n" + 
				"    if (content.style.maxHeight){\r\n" + 
				"      content.style.maxHeight = null;\r\n" + 
				"    } else {\r\n" + 
				"      content.style.maxHeight = content.scrollHeight + \"px\";\r\n" + 
				"    } \r\n" + 
				"  });\r\n" + 
				"}\r\n" + 
				"</script>"+
				"</body>\r\n" + 
				"</html>";
		return htmlString;
	}
	
    public static String highlightYellowString(String line, String searchString) {
        String lowerCaseLine = line.toLowerCase();
        String lowerCaseSearchString = searchString.toLowerCase();
        int index = lowerCaseLine.indexOf(lowerCaseSearchString);
        while (index >= 0) {
            String startTag = "<mark>";
            String endTag = "</mark>";
            line = line.substring(0, index) + startTag + line.substring(index, index + searchString.length()) + endTag + line.substring(index + searchString.length());
            index = lowerCaseLine.indexOf(lowerCaseSearchString, index + startTag.length() + searchString.length() + endTag.length());
        }
        return line;
    }
    
    public static String generateNoMatch(String heading) {
        String htmlHeading = "<h5 style=\"color:Red;\">" + heading + "</h5>";
        return htmlHeading;
    }
	
	public static String generateFinalReport(String summerySection, String partialReport) {
		return replaceString("*******************",partialReport, summerySection );
	}
	
	public static String replaceString(String toReplace, String targetString, String replacement) {
        return targetString.replace(toReplace, replacement);
    }
	
	public static void writeStringBufferToHtmlReport(StringBuffer content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static Map<String, String> readProperties(String filePath) {
        Properties prop = new Properties();
        InputStream input = null;
        Map<String, String> map = new HashMap<>();

        try {
            input = new FileInputStream(filePath);
            prop.load(input);

            for (String key : prop.stringPropertyNames()) {
                String value = prop.getProperty(key);
                map.put(key, value);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }
	
	
	public static String sanitizeForHtml(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;")
                    .replace("/", "&#x2F;");
    }
	
	//Added On 5/15/2024 for Complexity Analysis
	
	public static String generateHtmlStringComplexityCollapsible(String appName) {
		String htmlString = "<button class=\"collapsible\">\r\n" + 
				"	<p style=\"color:green; font-size:20px; font-family:Courier;\"> "
				+ "Complexity Analysis for "+appName+" </p> \r\n"+
				"	</button> \r\n"
				+"<div class=\"content\"> \r\n <BR>";
				//+ "<section class=\"items\"> \r\n";
		return htmlString;
	}
	
	public static String generateHtmlStringTableHeader() {
		String htmlString =
				"<table id=\"customers\"> \r\n"+
				"  <tr>\r\n" + 
				"    <th>File Extensions Present in the Project</th>\r\n" + 
				"    <th>File Count</th>\r\n" + 
				"    <th>Lines of Code Count (US)</th>\r\n" + 
				"    <th>Lines of Code Count (in K)</th>\r\n" + 
				"  </tr>";
		return htmlString;
	}
	
	public static String createTableRows(List<FileData> fileDataList) {
        StringBuilder sb = new StringBuilder();

        for (FileData fileData : fileDataList) {
            sb.append("<tr>\n");
            sb.append("  <td>").append(fileData.getExtension()).append("</td>\n");
            sb.append("  <td>").append(formatNumberWithCommas(fileData.getFileCount())).append("</td>\n");
            sb.append("  <td>").append(formatNumberWithCommas(fileData.getLineCount())).append("</td>\n");
            sb.append("  <td>").append(formatNumberWithKSuffix(fileData.getLineCount())).append("</td>\n");
            sb.append("</tr>\n");
        }

        return sb.toString();
    }
	
	public static String endHtmlTable() {
		return "</table> \r\n" + 
				"</div>";
	}
	
	public static String addComplexityToHtmlReport(String complexityValue) {
		return "\r\n" + 
				"<b>The Complixity of the Project is : <font color='red'>"+complexityValue+"</font><b/>"+
				"<BR><BR>";
	}
	public static String formatNumberWithCommas(int number) {
		return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
	
	public static String formatNumberWithKSuffix(int number) {
        if (number < 1000) {
            return String.valueOf(number);
        }
        return formatNumberWithCommas(number / 1000) + "K";
    }
	
}
