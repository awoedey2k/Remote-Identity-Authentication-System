package image.display;

import gcm.register.R;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.widget.ImageView;

public class ImageDisplay extends Activity{
	private ImageView Image1;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
		        .detectDiskReads()  
		        .detectDiskWrites()  
		        .detectNetwork()   
		        .penaltyLog()  
		        .build());  
	      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
		        .detectLeakedSqlLiteObjects()  
		       .detectLeakedClosableObjects()  
		        .penaltyLog()  
		        .penaltyDeath()  
	        .build()); 
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displayimage);
		
		Image1=(ImageView)findViewById(R.id.image);
		 try {
	          	   
	            	//1.set up object  
	          	  HttpClient httpclient = new DefaultHttpClient();
	          	  
	          	  //2.creating the method(get ,post,update,delete)
	                 HttpGet request = new HttpGet("http://128.39.42.159:8080/GUCNBLWebServices/jaxrs/Photo/download");
	                 
	               	request.addHeader("Accept", "text/plain");
	                 //3.invoke execute method of the object created in first step to execute the request 
	                 HttpResponse response = httpclient.execute(request);
	                 //4.read response
	                 HttpEntity entity = response.getEntity();
	               
	                 /*this is for comman picture decode*/
	                 
		             //byte[] bitmapArray=EntityUtils.toByteArray(entity);
	                 
	                
	                 /*This is for Base64 String decode*/
	                 
	                 String  input = EntityUtils.toString(entity);
	                 // Log.v("Response ","Response : "+ input);
				     byte[] bitmapArray = Base64.decode(input, Base64.DEFAULT); 
	                 Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
	                 Image1.setImageBitmap(bitmap);

	        		} catch (ClientProtocolException e) {
	          	   System.out.println("Please check your provided http address!");
	          	   e.printStackTrace();
	            } catch (IOException e) {
	                 e.printStackTrace();
	            } 
		
            }
         
	        	 
	        	 
	        	 
	         }
	
	
	
	


