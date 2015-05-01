package com.example.real;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.LauncherActivity.ListItem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Remote_Data extends AsyncTask<String,Void,String> {
	 HttpResponse response;
	 HttpGet request ;
	 URL url;
	 String link,a,upass,que,id;	
	 HttpClient client;
	EditText etxt1,etxt2,etxt3;
	TextView txt1,txt2;
	Context context;
	ListView listView1;
	private int byGetOrPost = 0;
	String ary[];
	public static long data,ok3;
	public static String ok,type,uid,uname;
	public static String ok1,ok11;
	public static String i,n,p,s;
	public static int ok2;
	public static int j=0;
	DatabaseHandler db,db1;
		
	

	public Remote_Data(Context context, String s)
	{//3
		this.context=context;		
		
		a="getsolution";
		
	//selectall
		
	}
	public Remote_Data(Context context)
	{//3
		this.context=context;		
		//this.uname=name;
		//this.upass=pass;
		a="userdata";
		//ok="1st";
	//selectall
		
	}
	public Remote_Data(Context context, int s)
	{//3
		this.context=context;		
		//this.uname=name;
		//this.upass=pass;
		a="getbest";
		//ok="1st";
	//selectall
		
	}

	
	
	public Remote_Data(Context applicationContext, String test, String id) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.id=id;
		this.que=test.replace(" ","_");
		a="share";
		
	}
	public Remote_Data(Context applicationContext, String uid2, String names, String img1,
			String data2) {
		// TODO Auto-generated constructor stub
		this.context=applicationContext;
		this.i=uid2;
		
		//this.s=data2;
		a="best";
		
	}
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub	
		if(byGetOrPost == 0){
		
        try {
        	
        		if(a=="share")
        	{
        			
        	link = "http://192.168.0.55/insertQue.php?id="+id+"&question="+que;
        	//link = "http://192.168.0.55/insertQue.php"+id+que;
			url = new URL(link);
	        client = new DefaultHttpClient();
	        request = new HttpGet();
	        request.setURI(new URI(link));
	        response = client.execute(request);
	        
	        //ok1="kk";
       	}
        		else if(a.equals("best"))
        		{	
        			ok="calll"+i;
        			int id1=Integer.parseInt(i);
        			link = "http://192.168.0.55/bestAns.php?id="+id1;
                	//link = "http://192.168.0.55/insertQue.php"+id+que;
        			url = new URL(link);
        	        client = new DefaultHttpClient();
        	        request = new HttpGet();
        	        request.setURI(new URI(link));
        	        response = client.execute(request);        			
        		}
        	else if(a=="userdata") 
        	{
        		//String name=uname;
        		//String pass=upass;
        		 String link = "http://192.168.0.55/selectuser.php";
        		//String link = "http://192.168.1.100/main.php";
    			url = new URL(link);
    	        client = new DefaultHttpClient();
    	        request = new HttpGet();
    	        request.setURI(new URI(link));
    	        response = client.execute(request);
    	       
    	        ok1="2nd";        
        	}
        	else if(a=="getbest") 
        	{
        		//String name=uname;
        		//String pass=upass;
        		 String link = "http://192.168.0.55/bestsolution.php";
        		//String link = "http://192.168.1.100/main.php";
    			url = new URL(link);
    	        client = new DefaultHttpClient();
    	        request = new HttpGet();
    	        request.setURI(new URI(link));
    	        response = client.execute(request);
    	       
    	        ok1="2nd";        
        	}
        	else if(a.equals("getsolution")) 
        	{
        		
        		//String name=uname;
        		//String pass=upass;
        		 String link = "http://192.168.0.55/getsolution.php";
        		//String link = "http://192.168.1.100/main.php";
    			url = new URL(link);
    	        client = new DefaultHttpClient();
    	        request = new HttpGet();
    	        request.setURI(new URI(link));
    	        response = client.execute(request);
    	       
    	        ok1="2nd";        
        	}
        
        		
	        BufferedReader in = new BufferedReader
          		  (new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line="";
            while ((line = in.readLine()) != null) {
          	  sb.append(line);
          	  break;
            }
            in.close();
          
            return sb.toString();
           
		
     }
        
        catch (Exception e) {
			// TODO Auto-generated catch block
			 return new String("Exception: " + e.getMessage());
		}

		}
		return null; 
	}
	  @Override
	   protected void onPostExecute(String result){
		 
		  try {
		  JSONObject json_data; 
			  
		
		  if(a.equals("userdata"))
		  {
			  json_data=new JSONObject(result);
			  db= new DatabaseHandler(context);
			  int count=Integer.parseInt(json_data.getString("countrow"));			
			  for(int i=0;i<count;i++)
					{  
				 
					String uid1 = json_data.getString("user_id"+i).toString();					
					String uname1= json_data.getString("user_name"+i).toString();
					 String upass1 = json_data.getString("user_pass"+i).toString();
					String type1= json_data.getString("types"+i).toString();
					ok3 =db.insertuser(uid1,uname1,upass1,type1);
		//ok=""+result+"uid"+json_data.getString("user_id"+i).toString()+"user_name"+json_data.getString("user_name"+i).toString()+"upass"+json_data.getString("user_pass"+i).toString()+"types"+json_data.getString("types"+i).toString();

					//ok=""+upass1; 
					}
			  //ok=""+result+"uid"+json_data.getString("user_id").toString()+"user_name"+json_data.getString("name").toString()+"upass"+json_data.getString("user_pass").toString()+"types"+json_data.getString("types").toString()+"count"+json_data.getString("countrow").toString();
			 
			  
				  }
		  
		  if(a.equals("getsolution"))
		  {
			  json_data=new JSONObject(result);
			  db= new DatabaseHandler(context);
			  int count=Integer.parseInt(json_data.getString("countrow"));			
			  for(int i=0;i<count;i++)
					{  
				 
					String uid1 = json_data.getString("user_id"+i).toString();					
					String uname1= json_data.getString("uname"+i).toString();
					 String p = json_data.getString("path"+i).toString();
					String s= json_data.getString("status"+i).toString();
					ok3 =db.insertFinalSolution(uid1,uname1,p,s);
		//ok=""+result+"uid"+json_data.getString("user_id"+i).toString()+"user_name"+json_data.getString("user_name"+i).toString()+"upass"+json_data.getString("user_pass"+i).toString()+"types"+json_data.getString("types"+i).toString();

					//ok=""+upass1; 
					}
			  //ok=""+result+"uid"+json_data.getString("user_id").toString()+"user_name"+json_data.getString("name").toString()+"upass"+json_data.getString("user_pass").toString()+"types"+json_data.getString("types").toString()+"count"+json_data.getString("countrow").toString();
			 
			 // ok="caalllll"+result;
				  }
			
			
		  
		  
		  if(a.equals("getbest"))
		  {
			  json_data=new JSONObject(result);
			  db= new DatabaseHandler(context);
			  int count=Integer.parseInt(json_data.getString("countrow"));			
			  for(int i=0;i<count;i++)
					{  
				 
					String uid1 = json_data.getString("user_id"+i).toString();					
					String uname1= json_data.getString("uname"+i).toString();
					 String p = json_data.getString("path"+i).toString();
					String s= json_data.getString("status"+i).toString();
					ok3 =db.insertFinalSolution(uid1,uname1,p,s);
		//ok=""+result+"uid"+json_data.getString("user_id"+i).toString()+"user_name"+json_data.getString("user_name"+i).toString()+"upass"+json_data.getString("user_pass"+i).toString()+"types"+json_data.getString("types"+i).toString();

					//ok=""+upass1; 
					}
			  //ok=""+result+"uid"+json_data.getString("user_id").toString()+"user_name"+json_data.getString("name").toString()+"upass"+json_data.getString("user_pass").toString()+"types"+json_data.getString("types").toString()+"count"+json_data.getString("countrow").toString();
			 
			 // ok="caalllll"+result;
				  }
			
			
		  }
		  
		  catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok=""+e;
			}
		  					  
	  }
	  
	  
	 
}
