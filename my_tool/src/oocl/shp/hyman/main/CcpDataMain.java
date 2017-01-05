package oocl.shp.hyman.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import oocl.shp.hyman.tool.GenerateDataHelper;

public class CcpDataMain {

	public static void main(String[] args) {
		String file = "res/cms/cms_for_ccp.txt";
		String outputFile = "res/cms/output/output_{2}.txt"; 
		
//		File file1=new File(outputFile);
//		if(file1.exists()){
//			file1=new File(outputFile.replaceAll("\\{[^}]*\\}","{3}"));
////			file1.renameTo(new File(outputFile.replaceAll("\\{[^}]*\\}","{2}")));
//		}
		BufferedReader bufReader;
		StringBuffer strBuf = new StringBuffer(); 
		PrintWriter printWriter;
		try {
			bufReader = new BufferedReader(new FileReader(file));
			for (String line = null; (line = bufReader.readLine()) != null; line = null) {

	    	line = line.replaceAll("\\[", "");
	    	line = line.replaceAll("]", "");
	    	System.out.println("line："+line);
	    	String url = "http://irisbackendqa2.oocl.com/wls_dom_shp/rest/shipment/lw/ccp/xml/" + line.trim();
	    	String document = GenerateDataHelper.generateDataFromURL(url);
	    	if(document != null){
	    		strBuf.append(line+"--->"+document.split("</cusSalesRegionGroup>")[1].split("<cusSalesSubRegionGroup>")[0]+";"+"\n"); 
	    	}
	    }

			printWriter = new PrintWriter(outputFile);
			printWriter.write(strBuf.toString().toCharArray());  

			bufReader.close();  
			printWriter.flush();  
	    printWriter.close(); 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
