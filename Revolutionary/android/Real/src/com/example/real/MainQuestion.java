package com.example.real;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
 
public class MainQuestion extends Activity {
 private String jsonResult;
 private String url = "http://192.168.0.55/question.php";
 private ListView listView;
 private Button share;
 private MultiAutoCompleteTextView meet;
 private EditText et;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.tquestion);
  listView = (ListView) findViewById(R.id.listView1);
  share=(Button) findViewById(R.id.button2);
  meet=(MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
  et=(EditText) findViewById(R.id.editText1);
  accessWebService();
 }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
  // Inflate the menu; this adds items to the action bar if it is present.
  getMenuInflater().inflate(R.menu.main, menu);
  
  share.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String test=meet.getText().toString().replace(" ","_");
		String id=et.getText().toString();
		String a="submit";
		new Remote_Data(getApplicationContext(),test,id).execute();
		Toast.makeText(getApplicationContext(), "Questions are share"+test, 5).show();
	}
});
  
  
  return true;
 }
 
 
 public void logout(View v)
 {
 	Intent i=new Intent(this, MainLogin.class);
 	startActivity(i);
 	 finish();
 	
 }
 public void back(View v)
 {
 	Intent i=new Intent(this, MainTeacher.class);
 	startActivity(i);
 	 finish();
 	
 }
 
 // Async Task to access the web
 private class JsonReadTask extends AsyncTask<String, Void, String> {
  @Override
  protected String doInBackground(String... params) {
   HttpClient httpclient = new DefaultHttpClient();
   HttpPost httppost = new HttpPost(params[0]);
   try {
    HttpResponse response = httpclient.execute(httppost);
    jsonResult = inputStreamToString(
      response.getEntity().getContent()).toString();
   }
 
   catch (ClientProtocolException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   }
   return null;
  }
 
  private StringBuilder inputStreamToString(InputStream is) {
   String rLine = "";
   StringBuilder answer = new StringBuilder();
   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
 
   try {
    while ((rLine = rd.readLine()) != null) {
     answer.append(rLine);
    }
   }
 
   catch (IOException e) {
    // e.printStackTrace();
    Toast.makeText(getApplicationContext(),
      "Error..." + e.toString(), Toast.LENGTH_LONG).show();
   }
   return answer;
  }
 
  @Override
  protected void onPostExecute(String result) {
   ListDrwaer();
  }
 }// end async task
 
 public void accessWebService() {
  JsonReadTask task = new JsonReadTask();
  // passes values for the urls string array
  task.execute(new String[] { url });
 }
 
 // build hash set for list view
 public void ListDrwaer() {
  List<Map<String, String>> questionList = new ArrayList<Map<String, String>>();
 
  try {
   JSONObject jsonResponse = new JSONObject(jsonResult);
   JSONArray jsonMainNode = jsonResponse.optJSONArray("Test_info");
 
   for (int i = 0; i < jsonMainNode.length(); i++) {
    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
    String material_id = jsonChildNode.optString("test_id");
    String question = jsonChildNode.optString("question").replace("_", " ");
    
    //String sub_id = jsonChildNode.optString("subject_id");
    String outPut = material_id + "-" + question;
    questionList.add(createEmployee("question", outPut));
   }
  } catch (JSONException e) {
   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
     Toast.LENGTH_SHORT).show();
  }
 
  SimpleAdapter simpleAdapter = new SimpleAdapter(this, questionList,
    android.R.layout.simple_list_item_1,
    new String[] { "question" }, new int[] { android.R.id.text1 });
  listView.setAdapter(simpleAdapter);
 }
 
 private HashMap<String, String> createEmployee(String name, String number) {
  HashMap<String, String> employeeNameNo = new HashMap<String, String>();
  employeeNameNo.put(name, number);
  return employeeNameNo;
 }
}


