package oocl.shp.hyman.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GenerateDataHelper {
	
	public static String generateDataFromURL(String urlStr){
		StringBuffer document = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while((line = reader.readLine()) != null){
				document.append(line + " ");
			}
			reader.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(!"".equals(document.toString()) && document.toString() != null){
//			return d
//		}
		return (!"".equals(document.toString()) && document.toString() != null)?document.toString():null;
	}

}
