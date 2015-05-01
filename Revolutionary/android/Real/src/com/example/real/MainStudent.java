package com.example.real;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainStudent extends Activity {

	Button viewSolution;
	
	
	static boolean Viewd = false;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        int a=1;
               
        new Remote_Data(getApplicationContext(),a).execute("");
          
	}
	
	@Override
	public void onBackPressed() {
		/*File tempFile = new File(AndroidPaint.mSavedTempFilePath);
		if(tempFile.exists() == true){
			tempFile.delete();
			AndroidPaint.isTempFileAlreadySaved = false;
		}*/
		//tempFile.delete();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void Question(View v)
    {
    	Intent i=new Intent(this, StudentQuestion.class);
    	startActivity(i);
    }
    
    
    public void Best(View v)
    {
    	Intent i=new Intent(this, BestSolution.class);
    	startActivity(i);
    }
    
    public void Back(View v)
    {
    	Intent i=new Intent(this, MainLogin.class);
    	startActivity(i);
    	finish();
    }
    
    
}

