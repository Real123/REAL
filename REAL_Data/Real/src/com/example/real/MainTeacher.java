package com.example.real;

import com.example.real.MainQuestion;
import com.example.real.MainOutcome;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainTeacher extends Activity  {

		String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);
        //MainLogin.a="";
       // String str=getIntent().getExtras().getString("uid");
        new Remote_Data(getApplicationContext(),s).execute("");
		//Toast.makeText(getApplicationContext(), "call"+Remote_Data.ok+DatabaseHandler.ok1, Toast.LENGTH_LONG).show();
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void Que(View v)
    {
    	Intent i=new Intent(this, MainQuestion.class);
    	startActivity(i);
    }
    public void Out(View v)
    {
    	Intent i=new Intent(this, MainOutcome.class);
    	startActivity(i);
    }
    
    public void back(View v)
    {
    	Intent i=new Intent(this, MainLogin.class);
    	startActivity(i);
    	 finish();
    	 //MainLogin.a=null;
    }


	
	
    
    
}

