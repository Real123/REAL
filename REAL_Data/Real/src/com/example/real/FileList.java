package com.example.real;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.Toast;

public class FileList extends ListActivity 
{
private File file;
private List<String> myList;
//File imageFile;
static String imageFilePath;

String Outcomedirectory;

public void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);

    myList = new ArrayList<String>();   

    String root_sd = Environment.getExternalStorageDirectory().toString();
    Outcomedirectory = root_sd + "/Outcome";
    //file = new File( root_sd + "/androidpaint" ) ;
    file = new File(Outcomedirectory); 
    File list[] = file.listFiles();

    for( int i=0; i< list.length; i++)
    {
            myList.add( list[i].getName() );
    }

    setListAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, myList ));

}

protected void onListItemClick(ListView l, View v, int position, long id) 
{
    super.onListItemClick(l, v, position, id);
    
    //imageFile = new File(file, myList.get(position));
    
    File temp_file = new File( file, myList.get( position ) ); 
    
    

    if( !temp_file.isFile())        
    {
        file = new File( file, myList.get( position ));
        File list[] = file.listFiles();

        myList.clear();

        for( int i=0; i< list.length; i++)
        {
            myList.add( list[i].getName() );
        }
        //Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show(); 
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myList ));
        
    }
    
    else if(StartingActivity.isToBeViewd == true){
    	 String fileName = temp_file.getName();
    	 imageFilePath = Outcomedirectory + "/" + fileName;
    	 Intent i = new Intent();
	    i.setClassName("com.example.real", "com.example.real.viewImageActivity");
	    startActivity(i); 
    }
    else {	
    	String fileName = temp_file.getName();
        imageFilePath = Outcomedirectory + "/" + fileName;
    	 Intent i = new Intent();
	    i.setClassName("com.example.real", "com.example.real.AndroidPaint");
	    startActivity(i);
    }
    
}


@Override
public void onBackPressed() {
           
	if(MainStudent.Viewd == true){
   	
   	 Intent i = new Intent();
	    i.setClassName("com.example.real", "com.example.real.MainStudent");
	    startActivity(i);
	    finish();
   }
	else
		{Intent i = new Intent();
		i.setClassName("com.example.real", "com.example.real.StartingActivity");
		startActivity(i);
		}
		}
}

