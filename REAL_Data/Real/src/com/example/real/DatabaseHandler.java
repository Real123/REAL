package com.example.real;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;
import android.widget.Toast;
 
public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "real";
    public static String a,ok1="",type=null,x=""; 
    public static String name=null;
   // public static Cursor c = null;
    public static String rslt="";
    public static long data;
    byte[] img,img1;
    // Labels table name
    
    private static final String TABLE_Outcome  = "solution";
    private static final String FINAL_Outcome  = "finalsolution";
    
    private static final String TABLE_Test  = "test";
    private static final String TABLE_User  = "users";
    //users table attribute 
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_PASS  = "user_pass";
    private static final String TYPES  = "types";
    // test attribute
    private static final String TEST = "test_id";
    private static final String question= "question";
    SQLiteDatabase db,db1,db2;
    //solution attribute 
    private static final String SOLUTION_NAME = "names";
    private static final String SOLUTION = "solutions";
    private static final String PATH = "path";
    private static final String STATUS = "status";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
    	
    	/*String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_User + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
                + USER_NAME +" TEXT,"+ USER_PASS + " TEXT,"+TYPES+" TEXT)";
    	db.execSQL(CREATE_USER_TABLE);*/
    	db.execSQL("create table users (user_id INTEGER PRIMARY KEY UNIQUE, user_name TEXT, user_pass TEXT, types TEXT  )");
    	
    	
    	db.execSQL("create table solution (user_id INTEGER, names TEXT, solutions LONGBLOB )");
    	db.execSQL("create table finalsolution (user_id INTEGER, user_name TEXT, path TEXT, status TEXT )");
    	
    	
      }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Outcome);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Test);
        db.execSQL("DROP TABLE IF EXISTS users" );
        db.execSQL("DROP TABLE IF EXISTS finalsolution" );
        
        // Create tables again
        onCreate(db);
    }
 
    /**
     * Inserting new lable into lables table
     * @return 
     * */
    
  

 public long insertFinalSolution(String uid, String name1, String p, String s){
	 	long a=0;
	 	String uid1 = null,temp;
	 	temp=uid;
	 	
     SQLiteDatabase db = this.getWritableDatabase();
     ok1="ccccccalllll";
     try
     {
     	
     ContentValues values = new ContentValues();
    // values.put(MATERIAL_ID, label1);
     values.put("user_id", uid);
     values.put("user_name", name1);
     values.put("path",p);
     values.put("status",s);
     // Inserting Row
    a= db.insert("finalsolution", null, values);
    x="insert";
     
     }catch(Exception e)
     {
     	a=2;
     }
     db.close(); // Closing database connection       
		return a;
   }
 

 
 //Alldata on toast
 public List<String> getAllsolution()
 {
     List<String> labels = new ArrayList<String>();
     try
     {
     // Select All Query
     //String selectQuery = "SELECT  * FROM finalsolution";
    	 String selectQuery = "SELECT  DISTINCT user_id FROM finalsolution";
     SQLiteDatabase db = this.getReadableDatabase();
     Cursor cursor = db.rawQuery(selectQuery, null);

     // looping through all rows and adding to list
     if (cursor.moveToFirst()) 
     {
         do 
         {
        	 
         	labels.add(cursor.getString(0));
            
         } while (cursor.moveToNext());
     }

     // closing connection
     cursor.close();
     db.close();
     }catch (Exception e) {
		// TODO: handle exception
    	 x=""+e;
	}
     // returning lables
     return labels;
 }
  
//Alldata on toast
public List<String> getAllbest()
{
    List<String> labels = new ArrayList<String>();
    try
    {
    // Select All Query
    //String selectQuery = "SELECT  * FROM finalsolution";
   	 String selectQuery = "SELECT  DISTINCT user_id FROM finalsolution where status='best'";
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) 
    {
        do 
        {
       	 
        	labels.add(cursor.getString(0));
           
        } while (cursor.moveToNext());
    }

    // closing connection
    cursor.close();
    db.close();
    }catch (Exception e) {
		// TODO: handle exception
   	 x=""+e;
	}
    // returning lables
    return labels;
}
 
 
 public long insertuser( String uid, String uname , String upass, String type ){
	 	
     SQLiteDatabase db = this.getWritableDatabase();
     int i=0;
     long b = 0;
     try
     {
     ContentValues values = new ContentValues();
    // values.put(MATERIAL_ID, label1);
     values.put("user_id", uid);
     values.put("user_name", uname );
     values.put("user_pass", upass );
     values.put("types", type);
     // Inserting Row
    b=db.insert("users", null, values);
	//i=2;
   // ok1="hhh";
     }
     
     catch(Exception e)
     {
    	 b=5;
     }
     db.close(); // Closing database connection       

     
     
	
	//return data= db.rawQuery("select * from users where name ='"+ uname+ "',user_pass='"+upass+"'", null);
	return b;
	
      
	
 }
 
 
 
 public Cursor allcheck(String uname, String upass) throws SQLException
	{SQLiteDatabase db = this.getReadableDatabase();
	String uid,uname1,upass1,types1;
	 Cursor c = null;
	 String data= null;
   int i = 0;
   
   try
	    {
	        
	        String selectQuery ="select * from users where user_name='"+ uname + "' AND user_pass='"+upass+"' ";
	        c = db.rawQuery(selectQuery, null);
	       // c.moveToFirst();
	        //i = c.getCount(); 
	        //c.close();   	       
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        i=5;
	    }
  
	    return c;
	}
 
 public Cursor get(String i) throws SQLException
 {
	 Cursor c = null;
	 
	 SQLiteDatabase db = this.getReadableDatabase();
	 try
	 {
	 String selectQuery = "SELECT * FROM finalsolution where user_id="+ i;

     c=db.rawQuery(selectQuery,null);

     
	 }catch (Exception e) {
		// TODO: handle exception
		 ok1=""+e+i;
	}
	return c;

   }
 public Cursor getBest(String i) throws SQLException
 {
	 Cursor c = null;
	 
	 SQLiteDatabase db = this.getReadableDatabase();
	 try
	 {
	 String selectQuery = "SELECT * FROM finalsolution where user_id="+ i;

     c=db.rawQuery(selectQuery,null);

     
	 }catch (Exception e) {
		// TODO: handle exception
		 ok1=""+e+i;
	}
	return c;

   }

 
 public Cursor usercheck(String uname, String upass) throws SQLException
	{SQLiteDatabase db1 = this.getReadableDatabase();
		String uid,uname1,upass1,types1;
	Cursor cursor=null;
	
 try{
		//String selectQuery ="select * from users where user_name='" +uname"' ";
		String selectQuery ="select * from users where user_name='"+ uname + "' AND user_pass='"+upass+"' ";
	     cursor = db1.rawQuery(selectQuery, null);
	
if (cursor.moveToFirst())
{
 do 
 {
     uid=cursor.getString(0);
     uname1=cursor.getString(1);
     upass1=cursor.getString(2);
    types1=cursor.getString(3);
   
  rslt=cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3);
  
 } while (cursor.moveToNext());
}
cursor.close();
}

catch(Exception e)
{
	rslt=""+e;
}
 return cursor;
 
	}
  
}

