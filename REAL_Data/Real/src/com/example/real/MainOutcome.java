package com.example.real;

import java.util.List;

import com.example.real.DatabaseHandler;
import com.example.real.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainOutcome extends Activity {
	String s;
	public static String images="";
	 public ListView lstListView1;		
	 ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toutcome);
       // adapter.clear();
        lstListView1=(ListView) findViewById(R.id.listView1);
        //LoadSpinnerData();
           
        
    lstListView1.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String item = ((TextView)arg1).getText().toString();
			images=item;
			
			Intent i=new Intent(MainOutcome.this, MainSolution.class);
			startActivity(i);
			
		}
	});
    
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    

    public void back(View v)
    {
    	Intent i=new Intent(this, MainLogin.class);
    	startActivity(i);
    	finish();
    }
    public void Mainback(View v)
    {
    	Intent i=new Intent(this, MainTeacher.class);
    	startActivity(i);
    	finish();
    }
    
    public void LoadSpinnerData(View v)
	{
    	
	
		 	DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        List<String> lables1 = db.getAllsolution();
	       
	         adapter = new ArrayAdapter<String>(this,
	        R.layout.list1color, lables1);
	       
	        lstListView1.setAdapter(adapter);        
	    
    }
    
    
}
