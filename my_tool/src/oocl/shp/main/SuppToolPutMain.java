package oocl.shp.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import oocl.shp.tool.GenerateDataHelper;

/**
 * Created by CHENHY on 1/10/2016.
 */
public class SuppToolPutMain {
	
	private static String URL_HEADER = "http://irisbackendqa2.oocl.com/wls_dom_shp/rest/shipment/shipmentLoader/shp-";
	private static String TRADELANE_REFRESH = "TradelaneRefresh";
	private static String CCP_CACHE_WARNUP = "CacheWarmUpCCP";

	public static void main(String[] args) {
		String file = "res/shp/shp_list.txt";
		String outputFile = "res/shp/output/output_{2}.txt";
		
		

		BufferedReader bufReader;
		PrintWriter printWriter;
//	File file1=new File(outputFile);
//	if(file1.exists()){
//		file1=new File(outputFile.replaceAll("\\{[^}]*\\}","{3}"));
////		file1.renameTo(new File(outputFile.replaceAll("\\{[^}]*\\}","{2}")));
//	}
		try {
			bufReader = new BufferedReader(new FileReader(file));
			printWriter = new PrintWriter(outputFile);
			StringBuilder sbStr = new StringBuilder();
			int i = 1;
			for (String line = null; (line = bufReader.readLine()) != null; line = null) {
				System.out.println("line"+ i++ + ":" + line);
				String url = URL_HEADER+line.trim()+",xmlCacheType-"+TRADELANE_REFRESH;
				URL putURL = new URL(url);
				HttpURLConnection httpURLConnection = (HttpURLConnection) putURL.openConnection();
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				httpURLConnection.setRequestMethod("PUT");
				httpURLConnection.setUseCaches(false);
				httpURLConnection.setInstanceFollowRedirects(true);
				httpURLConnection.setRequestProperty("Content-Type", "application/xml");
				httpURLConnection.setRequestProperty("FWApplicationContext","{\"userId\":\"chenhy\",\"token\":null,\"clientId\":\"chenhy-2-w7\",\"artContext\":\"23fe2e06-7231-4a19-bbac-218a016f7e46 0\"}");
				httpURLConnection.setRequestProperty("Accept", "application/xml");
				
				String xmlInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><searchNumber><searchNumber>1</searchNumber></searchNumber>";
				PrintWriter out = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "utf-8"));
				out.write(new String(xmlInfo.getBytes("ISO-8859-1")));
				out.close();

				if(httpURLConnection.getResponseCode() == 200){
					sbStr.append(line+"--->" + "success" + "\n");
				}else{
					sbStr.append(line+"--->" + "fail" + "\n");
				}
				httpURLConnection.disconnect();
			}
			printWriter.write(sbStr.toString().toCharArray());
			
			bufReader.close();  
			printWriter.flush();  
	    printWriter.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
