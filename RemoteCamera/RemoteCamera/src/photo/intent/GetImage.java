package photo.intent;

import com.example.remotecamera.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GetImage extends Activity {
ImageView img,img1;
int column_index;
Intent intent=null;
// Declare our Views, so we can access them later
String logo,imagePath,Logo;
Cursor cursor;
//YOU CAN EDIT THIS TO WHATEVER YOU WANT
private static final int SELECT_PICTURE = 1;

 String selectedImagePath;
//ADDED
 String filemanagerstring;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    img= (ImageView)findViewById(R.id.ImageView01);
   
 ((Button) findViewById(R.id.btnSelect))               
.setOnClickListener(new OnClickListener() {

        public void onClick(View arg0) {

            // in onCreate or any event where your want the user to
            // select a file
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), SELECT_PICTURE);


        }
    });
}

//UPDATED
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == Activity.RESULT_OK) {
        if (requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();

            //OI FILE Manager
            filemanagerstring = selectedImageUri.getPath();

            //MEDIA GALLERY
            selectedImagePath = getPath(selectedImageUri);


            img.setImageURI(selectedImageUri);

           imagePath.getBytes();
          //TextView txt = (TextView)findViewById(R.id.display);
           //txt.setText(imagePath.toString());


           Bitmap bm = BitmapFactory.decodeFile(imagePath);

           img1.setImageBitmap(bm);



        }

    }

}

//UPDATED!
public String getPath(Uri uri) {
String[] projection = { MediaColumns.DATA };
@SuppressWarnings("deprecation")
Cursor cursor = managedQuery(uri, projection, null, null, null);
column_index = cursor
        .getColumnIndexOrThrow(MediaColumns.DATA);
cursor.moveToFirst();
 imagePath = cursor.getString(column_index);

return cursor.getString(column_index);
}

} 





 
