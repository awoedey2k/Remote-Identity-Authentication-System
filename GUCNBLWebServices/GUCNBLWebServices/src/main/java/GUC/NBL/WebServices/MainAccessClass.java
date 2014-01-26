package GUC.NBL.WebServices;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;


import sun.misc.BASE64Decoder;



import static gov.nist.itl.wsbd.Logging.trace;
import gov.nist.itl.wsbd.SensorService;

import gov.nist.itl.wsbd.dom.Dictionary;
import gov.nist.itl.wsbd.dom.Result;

@Path("/Photo")
public class MainAccessClass extends SensorService{
	
	public static String image1;
	
	
	//@Context    
	//UriInfo uriInfo;
	 
	//@Context    
	//Request request;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/capture")
	//send a message to the remote client 
	public void  getString() {
       		URLConnection conn = null;
			try {
				conn = new URL("http://128.39.42.159:8080/GUCNBLWebServices/sendAll").openConnection();
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
 /* 
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/capture2")
	//send a message to the remote client 
	public void  getString2() {
       		URLConnection conn = null;
			try {
				conn = new URL("http://128.39.42.159:8080/GUCNBLWebServices/sendAll3").openConnection();
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
   
	
	*/
	//posting the captured images to server(have been encoded into a String String value)	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/photo") 
	public void getimage(@FormParam("image") String image){
	              System.out.println("image" +" "+ image);
	              image1=image;
	              SendRequest.Sendrequest();
	}
		
	//can be used for the uploading test (whether the captured client upload the image to server successfully)
		@GET
		@Produces(MediaType.TEXT_PLAIN)
		@Path("/download")
		public String Download() throws IOException{
			if (image1==null){
				System.out.println("the image1 is null");
				}
			else{
			System.out.println("success!");
			}
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(image1);
			for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
           // 生成.png图片
            String imgFilePath = "F:\1234567.png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);    
            out.write(b);
            out.flush();
            out.close();
            return image1;
        } 

		    
		 
	
	@Override
	public Result capture(UUID arg0) throws InterruptedException {
		// todo Auto-generated method stub
		return null;
	}

	@Override
	public Result download(UUID arg0) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getConfiguration(UUID arg0) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getDownloadInformation(UUID arg0) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getServiceInformation() {
		// TODO Auto-generated method stub
		trace("LeeTest");
		return null;
	}

	@Override
	public Result initialize(UUID arg0) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result setConfiguration(UUID arg0, Dictionary arg1)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result thriftyDownload(UUID arg0, String arg1)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}