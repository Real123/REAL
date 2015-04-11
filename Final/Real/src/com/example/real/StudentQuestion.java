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
import org.w3c.dom.ls.LSInput;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class StudentQuestion extends Activity {
 private String jsonResult;
 private String url = "http://192.168.0.55/question.php";
  ListView listView;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.studquestion);
  listView = (ListView)findViewById(R.id.listView1);
  accessWebService();
  
   

listView.setOnItemClickListener(new OnItemClickListener()
  {

		@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
		{
			
			Intent i=new Intent(StudentQuestion.this, StartingActivity.class);
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
 
 
 public void logout(View v)
 {
 	Intent i=new Intent(this, MainLogin.class);
 	startActivity(i);
 	 finish();
 	
 }
 
 public void back(View v)
 {
 	Intent i=new Intent(this, MainStudent.class);
 	startActivity(i);
 	 finish();
 	
 }
 public void Answer(View v)
 {
 	Intent i=new Intent(this, StartingActivity.class);
 	startActivity(i);
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
   // String sub_id = jsonChildNode.optString("subject_id");
    String outPut = material_id + "-" + question;
    questionList.add(createQuestion("question", outPut));
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
 
 private HashMap<String, String> createQuestion(String question, String name) {
  HashMap<String, String> QuestionNo = new HashMap<String, String>();
  QuestionNo.put(question, name);
  return QuestionNo;
 }
}


