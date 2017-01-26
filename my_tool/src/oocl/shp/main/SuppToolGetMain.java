package oocl.shp.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import oocl.shp.tool.GenerateDataHelper;

/**
 * Created by CHENHY on 1/10/2016.
 */
public class SuppToolGetMain {

	public static void main(String[] args) {
		
		String file = "res/cms/cms_list.txt";
		String outputFile = "res/cms/output/all/output_{2}.txt"; 	 
		String outputFile_IRL = "res/cms/output/IRL/output_IRL_{2}.txt"; 
		String urlHeader = "http://irisbackendqa2.oocl.com/wls_dom_shp/rest/shipment/lw/ccp/xml/";
		
//		File file1=new File(outputFile);
//		if(file1.exists()){
//			file1=new File(outputFile.replaceAll("\\{[^}]*\\}","{3}"));
////			file1.renameTo(new File(outputFile.replaceAll("\\{[^}]*\\}","{2}")));
//		}
		
		BufferedReader bufReader;
		StringBuffer allStrBuf = new StringBuffer(); 
		StringBuffer irlStrBuf = new StringBuffer();
		PrintWriter printWriter;
		PrintWriter printWriterIRL;
		try {
			bufReader = new BufferedReader(new FileReader(file));
			for (String line = null; (line = bufReader.readLine()) != null; line = null) {
	    	System.out.println("line："+line);
	    	String url = urlHeader + line.trim();
	    	String document = GenerateDataHelper.generateDataFromURL(url);
	    	if(document != null){
	    		String subRegionNodeStr = document.split("</cusSalesRegionGroup>")[1].split("<cusSalesSubRegionGroup>")[0];
	    		allStrBuf.append(line+"--->"+subRegionNodeStr+";"+"\n"); 
	    		if(subRegionNodeStr.contains("IRL")){
	    			irlStrBuf.append(line+"\n");
	    		}
	    	}else{
	    		allStrBuf.append(line+"--->"+"No record;"+"\n"); 
	    	}
	    }

			printWriter = new PrintWriter(outputFile);
			printWriterIRL = new PrintWriter(outputFile_IRL);
			
			printWriter.write(allStrBuf.toString().toCharArray());  
			printWriterIRL.write(irlStrBuf.toString().toCharArray());
			
			bufReader.close();  
			printWriter.flush();  
	    printWriter.close(); 
	    printWriterIRL.flush();  
	    printWriterIRL.close(); 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
