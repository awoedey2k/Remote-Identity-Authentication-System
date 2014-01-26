/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.remotecamera;

import android.content.Context;
import android.content.Intent;

/**
 * Helper class providing methods and constants common to other classes in the
 * app.
 */
public final class CommonUtilities {

    /**
     * Base URL of the Demo Server (such as http://my_host:8080/gcm-demo)
     */
    static final String SERVER_URL = "http://128.39.141.135:8080/GUCNBLWebServices";

    /**
     * Google API project id registered to use GCM.
     */
    static final String SENDER_ID ="513598229149";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "Remote";

    /**
     * Intent used to display a message in the screen.
     */
    static final String DISPLAY_MESSAGE_ACTION ="com.example.remotecamera.DISPLAY_MESSAGE";

    /**
     * Intent used to open the camera.
     */
    static final String CAMERA_OPEN_ACTION ="com.example.remotecamera.OPEN_CAMERA";
     /**
      * Intent's extra that contains the message to be displayed. 
      */
     
   
    static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
        }
    /**
    * @param message to open the camera.
    *  
    *  
    */
        static void openCamera(Context context, String message) {
            Intent intent = new Intent(CAMERA_OPEN_ACTION);
           // intent.putExtra(EXTRA_MESSAGE, message);
            intent.getAction();
            context.sendBroadcast(intent);
        }
   
}
