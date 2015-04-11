package com.example.real;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainSolution extends Activity {
	public static String data,uid,names,img,img1;
	Cursor cursor;
	//byte[] img1;
	int  getcount;
	ImageView imageView;
	
	//long ci=(Long) null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution);
        
        final WebView learn2crack = (WebView)findViewById(R.id.webview);
	
        
  img=MainOutcome.images;
       
        
        //long imag=spilet();
        Button get=(Button)findViewById(R.id.button1);
        
        get.setOnClickListener(new OnClickListener() 
        {
        	 DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        	 
		@Override
		public void onClick(View v) 
		{
						      
		        try
		        {
		    	        	
		        	Cursor type=db.get(img);
					getcount=type.getCount(); 
		    	if(getcount==0)
		    	{
		    		Toast.makeText(MainSolution.this, "data not fetch "+getcount+DatabaseHandler.ok1,Toast.LENGTH_LONG).show();
			    	  //Intent i=new Intent(MainSolution.this,MainSolution.class);
						//startActivity(i);
		    		
		    	}
		    	else
		    	{
		    	
		    	  if (type.moveToFirst())
		 	     {
		 	      do 
		 	      {
		 	    	 //Toast.makeText(MainSolution.this, "data fetch "+getcount+DatabaseHandler.ok1,Toast.LENGTH_LONG).show();
		 	    	  
		 	          uid=type.getString(0);
		 	          names=type.getString(1);
		 	          img1=type.getString(2);
		 	          data=type.getString(3);
		 	           //img1=type.getBlob(type.getColumnIndex("solutions"));
		 	         
		 	       				 	       
		 	      } while (type.moveToNext());
		 	      
		 	     learn2crack.loadUrl("http://"+img1);
		 	    learn2crack.getSettings().setJavaScriptEnabled(true);
		   
		 	     // Bitmap b1=BitmapFactory.decodeByteArray(img1, 0, img1.length);

		             //imageView.setImageBitmap(b1);
		 	     }
		    	  type.close();	
		    	}
		       }catch (Exception e) {
				//TODO: handle exception
		    Toast.makeText(MainSolution.this, "data not fetch "+e+img,Toast.LENGTH_LONG).show();  
			}
			
		}
	});
        
     }   
       
	 
        
	
	
	@Override
	public void onBackPressed() {
		/*File tempFile = new File(AndroidPaint.mSavedTempFilePath);
		if(tempFile.exists() == true){
			tempFile.delete();
			AndroidPaint.isTempFileAlreadySaved = false;
		}*/
		//tempFile.delete();
		Intent intent = new Intent(MainSolution.this,MainOutcome.class);
		startActivity(intent);
	}
	public void bestAnswer(View v)
	{
		new Remote_Data(getApplicationContext(),uid,names,img1,data).execute();
		Toast.makeText(MainSolution.this, " "+Remote_Data.ok,Toast.LENGTH_LONG).show();
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    
 
}

