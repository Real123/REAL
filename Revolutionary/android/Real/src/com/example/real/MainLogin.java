package com.example.real;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainLogin extends Activity {
public static String username,data, uid;
   private EditText usernameField,passwordField;
   private TextView status,role,method;
   private Button login, insert;
   //DatabaseHandler db,db1;
   @Override 
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.login_main);
      usernameField = (EditText)findViewById(R.id.editText1);
      passwordField = (EditText)findViewById(R.id.editText2);
      
      login=(Button)findViewById(R.id.button1);
      insert=(Button)findViewById(R.id.button2);
      
      new Remote_Data(getApplicationContext()).execute();
		 Toast.makeText(MainLogin.this, "data inserting",Toast.LENGTH_LONG).show();
      
     // method = (TextView)findViewById(R.id.textView9);
      insert.setOnClickListener(new OnClickListener(){      

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//String pass="m",name="v";
			//String username = usernameField.getText().toString();
		      //String password = passwordField.getText().toString();
			 new Remote_Data(getApplicationContext()).execute();
			 Toast.makeText(MainLogin.this, "data insert"+DatabaseHandler.ok1+Remote_Data.ok+Remote_Data.ok3,Toast.LENGTH_LONG).show();
		}
    	  
    		     		  
      });
      
      login.setOnClickListener(new OnClickListener() {
			String uname1,upass1,types1;
			@Override
			
			public void onClick(View v) {			
				
				DatabaseHandler db = new DatabaseHandler(getApplicationContext());
				 username = usernameField.getText().toString();
			      String password = passwordField.getText().toString();
			     // String type=db.usercheck(username,password);
			     // Cursor type=db.usercheck(username,password);
			      int nameField=usernameField.getText().toString().length();
			      int passField=passwordField.getText().toString().length();
				
			      if(nameField==0 && passField==0 )
			      {
			    	  
			    	  Toast.makeText(MainLogin.this, "please enter user name and password ",Toast.LENGTH_LONG).show();
			    	  Intent i=new Intent(MainLogin.this,MainLogin.class);
						startActivity(i);
			      }
				
				else
				{
				      try
				      {
				    	  Cursor type=db.allcheck(username,password);
				    	int  getcount = type.getCount(); 
				    	if(getcount==0)
				    	{
				    		Toast.makeText(MainLogin.this, "Wroung user name and password ",Toast.LENGTH_LONG).show();
					    	  Intent i=new Intent(MainLogin.this,MainLogin.class);
								startActivity(i);
				    		
				    	}
				    	else
				    	{
				    	
				    	  if (type.moveToFirst())
				 	     {
				 	      do 
				 	      {
				 	          uid=type.getString(0);
				 	          uname1=type.getString(1);
				 	          upass1=type.getString(2);
				 	         types1=type.getString(3);
				 	        
				 	       				 	       
				 	      } while (type.moveToNext());
				 	     }
				    	  type.close();	
				    	// Toast.makeText(MainLogin.this, "Wroung user name and password "+types1,Toast.LENGTH_LONG).show();
				    	  
				     if(types1.equals("teacher")==true)
						 {
							
								Toast.makeText(MainLogin.this, "teacher Login",Toast.LENGTH_LONG).show();
								 Intent i=new Intent(MainLogin.this,MainTeacher.class);
								// i.putExtra("uid", uid);
								 //i.putExtra("uname1", uname1);
								 //i.putExtra("types1", types1);
									startActivity(i);  
									 finish();
						 }
				      else if(types1.equals("student")==true)
				      {
				    	  //Intent i=new Intent(MainLogin.this,MainTeacher.class);
						//	startActivity(i);
				    	  Toast.makeText(MainLogin.this, "student Login",Toast.LENGTH_LONG).show();
				    	  Intent i=new Intent(MainLogin.this,MainStudent.class);				    	  
							startActivity(i);  
							 finish();
				      }
				     
				      }
				      }
				      catch(Exception e)
				      {
				    	  e.printStackTrace();
				      }
					
				}
				
				
				
			}
			
		});
      
   }
   @Override
   
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   
   

}
