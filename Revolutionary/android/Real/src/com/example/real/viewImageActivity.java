package com.example.real;



import java.io.ByteArrayOutputStream;
import java.io.File;

import com.example.real.FileList;
import com.example.real.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class viewImageActivity extends Activity {
	
	//private PinchImageView view;
	private Bitmap mBitmap,bitmap;
	RequestParams params = new RequestParams();
	ProgressDialog prgDialog;
	String encodedString,fileName,data1;
	public static String Path;
	//private String imageFilePath;
	 String s="normal";
     /** Called when the activity is first created. */
 public void onCreate(Bundle savedInstanceState) {
	 
     super.onCreate(savedInstanceState);
     
    // setContentView(R.layout.viewpage);
      Path=FileList.imageFilePath;
    // view = (PinchImageView) findViewById(R.id.image);
     mBitmap = BitmapFactory.decodeFile(Path);
     //view.setImageBitmap(mBitmap);*/
     String fileNameSegments[] = Path.split("/");
     fileName = fileNameSegments[fileNameSegments.length - 1];
     //data1=AndroidPaint.data;
     // Put file name in Async Http Post Param which will used in Php web app
     params.put("filename", fileName);
     String uid=MainLogin.uid;
     params.put("uid", uid);
    
     params.put("status", s);
     ZoomableImageView zoomableImageView = new ZoomableImageView(this);
     
     zoomableImageView.setImageBitmap(mBitmap);
     
     setContentView(zoomableImageView);
     prgDialog = new ProgressDialog(this);
     // Set Cancelable as False
     prgDialog.setCancelable(false);       
 }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
 	MenuInflater inflater = getMenuInflater();
 	inflater.inflate(R.menu.menuforimageview, menu);
 	//mMenu = menu;
 	return true;
 	}
 
 
public boolean onOptionsItemSelected(MenuItem item){
	 
	 switch(item.getItemId()){
	 case R.id.itemShareImageForViewImage:
	    	
	    	Intent share;
	    	File attachment = null;
	    	attachment = new File(FileList.imageFilePath);
				boolean isFileThere = attachment.exists();
				if (isFileThere == true){
					share = new Intent(Intent.ACTION_SEND);
					share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
					share.setType("image/png");
					startActivity(Intent.createChooser(share, "Share drawing"));
					
				}
				
	 case R.id.upload:
		 uploadImage();
		 
	    	}
			
			return true;
	 }
	 
 
 @SuppressLint("NewApi")
public void uploadImage() {
	 	 try
	 	 {
	        // When Image is selected from Gallery
	        if (!Path.isEmpty()) {
	            prgDialog.setMessage("Converting Image to Binary Data");
	            prgDialog.show();
	            // Convert image to String using Base64
	        	
	            encodeImagetoString();
	            //Toast.makeText(getApplicationContext(), ""+Path, Toast.LENGTH_LONG).show();
	        // When Image is not selected from Gallery
	        } else {
	            Toast.makeText(
	                    getApplicationContext(),
	                    "You must select image from gallery before you try to upload",
	                    Toast.LENGTH_LONG).show();
	        }
	    
	 }catch(Exception e)
	 {
		 Toast.makeText(getApplicationContext(), "EEEErrRRRR"+e+Path, Toast.LENGTH_LONG).show();
	 }
 }
 
 
//AsyncTask - To convert Image to String
 @SuppressLint("NewApi")
public void encodeImagetoString() {
	
    try
    {
    	
        	if(!Path.isEmpty())
        	{
             BitmapFactory.Options options = null;
             options = new BitmapFactory.Options();
             options.inSampleSize = 3;
             bitmap = BitmapFactory.decodeFile(Path,
                     options);
             ByteArrayOutputStream stream = new ByteArrayOutputStream();
             // Must compress the Image to reduce image size to make upload easy
             bitmap.compress(Bitmap.CompressFormat.PNG, 45, stream); 
             byte[] byte_arr = stream.toByteArray();
             // Encode Image to String
             encodedString = Base64.encodeToString(byte_arr, 0);
          
             prgDialog.setMessage("Calling Upload");
             // Put converted Image string into Async Http Post param
             params.put("solution", encodedString);
             // Trigger Image upload
             triggerImageUpload();
            
        	}
             //return "";
    }catch(Exception e)
    {
    	Toast.makeText(getApplicationContext(), "caaaaalllllll"+Path, Toast.LENGTH_LONG).show();
    }
         }
 public void triggerImageUpload() {
     makeHTTPCall();
 }
 
 public void makeHTTPCall() {
     prgDialog.setMessage("Invoking Php");        
     AsyncHttpClient client = new AsyncHttpClient();
     // Don't forget to change the IP address to your LAN address. Port no as well.
     client.post("http://192.168.0.55/upload_solution.php",
             params, new AsyncHttpResponseHandler() {
                 // When the response returned by REST has Http
                 // response code '200'
                 @Override
                 public void onSuccess(String response) {
                     // Hide Progress Dialog
                     prgDialog.hide();
                     Toast.makeText(getApplicationContext(), response,
                             Toast.LENGTH_LONG).show();
                 }

                 // When the response returned by REST has Http
                 // response code other than '200' such as '404',
                 // '500' or '403' etc
                 @Override
                 public void onFailure(int statusCode, Throwable error,
                         String content) {
                     // Hide Progress Dialog
                     prgDialog.hide();
                     // When Http response code is '404'
                     if (statusCode == 404) {
                         Toast.makeText(getApplicationContext(),
                                 "Requested resource not found",
                                 Toast.LENGTH_LONG).show();
                     }
                     // When Http response code is '500'
                     else if (statusCode == 500) {
                         Toast.makeText(getApplicationContext(),
                                 "Something went wrong at server end",
                                 Toast.LENGTH_LONG).show();
                     }
                     // When Http response code other than 404, 500
                     else {
                         Toast.makeText(
                                 getApplicationContext(),
                                 "Error Occured \n Most Common Error: \n1. Device not connected to Internet\n2. Web App is not deployed in App server\n3. App server is not running\n HTTP Status code : "
                                         + statusCode, Toast.LENGTH_LONG)
                                 .show();
                     }
                 }
             });
 } 
 }


