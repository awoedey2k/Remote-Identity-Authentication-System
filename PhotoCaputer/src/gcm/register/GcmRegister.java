package gcm.register;

import static gcm.register.CommonUtilities.DISPLAY_IMAGE_ACTION;
import static gcm.register.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static gcm.register.CommonUtilities.EXTRA_MESSAGE;
import static gcm.register.CommonUtilities.SENDER_ID;
import static gcm.register.CommonUtilities.SERVER_URL;
import image.display.ImageDisplay;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;

/**
 * Main UI for the demo app.
 */
public class GcmRegister extends Activity {

	TextView mDisplay;
	ImageButton button;

    AsyncTask<Void, Void, Void> mRegisterTask;

	@SuppressLint("NewApi")
	@Override
    
	public void onCreate(Bundle savedInstanceState) {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads()  
        .detectDiskWrites()  
        .detectNetwork()   // or .detectAll() for all detectable problems  
        .penaltyLog()  
        .build());  
  StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
        .detectLeakedSqlLiteObjects()  
       .detectLeakedClosableObjects()  
        .penaltyLog()  
        .penaltyDeath()  
    .build());  

    	
        super.onCreate(savedInstanceState);
        checkNotNull(SERVER_URL, "SERVER_URL");
        checkNotNull(SENDER_ID, "SENDER_ID");
        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);
        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);
        setContentView(R.layout.main);
        mDisplay = (TextView) findViewById(R.id.display);
        button = (ImageButton)findViewById(R.id.imageButton );
       
        registerReceiver(mHandleMessageReceiver1,new IntentFilter(DISPLAY_MESSAGE_ACTION));
        registerReceiver(mHandleMessageReceiver2,new IntentFilter(DISPLAY_IMAGE_ACTION));
   
          button.setOnClickListener( new Button.OnClickListener()
        {

            @Override
            public void onClick( View v )
            {
            	
            	mRegisterTask = new AsyncTask<Void, Void, Void>() {
                    @Override
                   protected Void doInBackground(Void... params) {
                try {
              	   
                	//1.set up object  
              	  HttpClient httpclient = new DefaultHttpClient();
              	  
              	  //2.creating the method(get ,post,update,delete)
                     HttpGet request = new HttpGet("http://128.39.42.159:8080/GUCNBLWebServices/jaxrs/Photo/capture");
                     
                   	request.addHeader("Accept", "text/plain");
                     //3.invoke execute method of the object created in first step to execute the request 
                     HttpResponse response = httpclient.execute(request);
                     @SuppressWarnings("unused")
					HttpEntity client=response.getEntity();
                     System.out.println("request is successful!");
                    } catch (ClientProtocolException e) {
              	   System.out.println("Please check your provided http address!");
              	   e.printStackTrace();
                } catch (IOException e) {
                     e.printStackTrace();
                } 
                return null;
                }
                    
                    @Override
		             protected void onPostExecute(Void result) {
		                       mRegisterTask = null;
		                    }              
    
            	};
            	 mRegisterTask.execute(null, null, null);
            	}
            
    		
        } );
          
            	 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            /*
             * Typically, an application registers automatically, so options
             * below are disabled. Uncomment them if you want to manually
             * register or unregister the device (you will also need to
             * uncomment the equivalent options on options_menu.xml).
             */
            /*
            case R.id.options_register:
                GCMRegistrar.register(this, SENDER_ID);
                return true;
            case R.id.options_unregister:
                GCMRegistrar.unregister(this);
                return true;
           */
            case R.id.options_clear:
                mDisplay.setText(null);
                return true;
            case R.id.options_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        unregisterReceiver(mHandleMessageReceiver1);
        GCMRegistrar.onDestroy(this);
        super.onDestroy();
    }

    private void checkNotNull(Object reference, String name) {
        if (reference == null) {
            throw new NullPointerException(
                    getString(R.string.error_config, name));
        }
    }

    private final BroadcastReceiver mHandleMessageReceiver1 =
            new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
            mDisplay.append(newMessage + "\n");
        }
    };
    private final BroadcastReceiver mHandleMessageReceiver2 =new BroadcastReceiver() {
  	  @Override
        public void onReceive(Context context, Intent intent) {
intent.setClass(GcmRegister.this , ImageDisplay.class);
               startActivity(intent);
    }
    };

}