package com.example.real;


import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class StartingActivity extends Activity implements View.OnClickListener{
	Button newSolution;
	Button editSolution;
	Button viewSolution;
	Intent i;
	static boolean isToBeEdited = false;
	static boolean isToBeViewd = false;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);
        
        newSolution = (Button)findViewById(R.id.buttonNew);
        editSolution = (Button)findViewById(R.id.buttonEdit);
        viewSolution = (Button)findViewById(R.id.buttonViewImage);
        newSolution.setOnClickListener(this);
        editSolution.setOnClickListener(this);
        viewSolution.setOnClickListener(this);
        
        i = new Intent();
        
        i.setClassName("com.example.real", "com.example.real.AndroidPaint");
        
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == newSolution){
		startActivity(i);
		isToBeEdited  = false;
		isToBeViewd = false;
		}
		
		if(arg0 == editSolution){
			isToBeEdited = true;
			isToBeViewd = false;
			Intent iFileList = new Intent();
			iFileList.setClassName("com.example.real","com.example.real.FileList");
			startActivity(iFileList);
			//isToBeEdited = false;
		}
		
		if(arg0 == viewSolution){
			isToBeEdited  = false;
			isToBeViewd = true;
			Intent iFileList = new Intent();
			iFileList.setClassName("com.example.real","com.example.real.FileList");
			startActivity(iFileList);
			//isToBeEdited = false;
		}
	}
	@Override
	public void onBackPressed() {
		/*
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);*/
		Intent i=new Intent(this, StudentQuestion.class);
	 	startActivity(i);
	 	 finish();

	}
	}
