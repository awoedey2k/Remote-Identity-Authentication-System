package GUC.NBL.WebServices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SendRequest {

	public static void Sendrequest(){
		URLConnection conn = null;
		try {
			conn = new URL("http://128.39.42.159:8080/GUCNBLWebServices/sendAll2").openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}            
	java.io.InputStream is = null;
	try {
		is =  conn.getInputStream();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}            
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
		System.out.println("Requested by somebody");  
		
		}
}
