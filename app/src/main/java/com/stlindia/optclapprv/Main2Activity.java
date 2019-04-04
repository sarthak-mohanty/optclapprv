package com.stlindia.optclapprv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stlindia.optclapprv.adapter.myAdapter;
import com.stlindia.optclapprv.model.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    private Button button;
    private EditText edit;
    private EditText edit2;
    private String id1, id2;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = findViewById(R.id.button);
        edit = (EditText)findViewById(R.id.editText3);
        edit2 = (EditText)findViewById(R.id.editText4);
        id1 = edit.getText().toString();
        id2 = edit2.getText().toString();

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        id1 = edit.getText().toString();
                        id2 = edit2.getText().toString();
                       if(TextUtils.isEmpty(id1)||TextUtils.isEmpty(id2))
                        {
                             Toast.makeText(Main2Activity.this,"username or passsword cannot be empty", Toast.LENGTH_LONG).show();
                        }
                        else {
                            new AsyncFetch().execute();
                       }
                        }
                }

        );

    }
        private class AsyncFetch extends AsyncTask<String, String, String> {
            ProgressDialog pdLoading = new ProgressDialog(Main2Activity.this);
            HttpURLConnection conn;
            URL url = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pdLoading.setMessage("\tplease wait...");
                pdLoading.setCancelable(false);
                pdLoading.show();

            }

            @Override
            protected String doInBackground(String... params) {
                                try {

                    url = new URL("http://192.168.137.1:8181/optcl_demo/login.php?username=" + id1 + " & password=" + id2);
                                    Log.d("TAG", "doInBackground: -----------------------------------------"+url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return e.toString();
                }
                try {


                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(READ_TIMEOUT);
                    conn.setConnectTimeout(CONNECTION_TIMEOUT);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                } catch (IOException e1) {
                    e1.printStackTrace();
                    return e1.toString();
                }

                try {

                    int response_code = conn.getResponseCode();

                    if (response_code == HttpURLConnection.HTTP_OK) {
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        return (result.toString());

                    } else {

                        return ("unsuccessful");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    return e.toString();
                } finally {
                    conn.disconnect();
                }


            }

            @Override
            protected void onPostExecute(String result) {
                pdLoading.dismiss();
                try {
                    JSONObject json_data= new JSONObject(result);
                    String userId = json_data.getString("success");
                    String Id = json_data.getString("id");
                    String en = json_data.getString("ename");
                    Log.d("TAG", "------------------------------------------------------------------------> "+Id);
                    if(userId.equals("1"))
                    { Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("userid",json_data.getString("id"));
                        intent.putExtra("enam",json_data.getString("ename"));
                        startActivity(intent);
                        finish();

                    }
                    else
                        Toast.makeText(Main2Activity.this,"invalid username or password", Toast.LENGTH_LONG).show();




                } catch (JSONException e) {
                    Toast.makeText(Main2Activity.this, e.toString(), Toast.LENGTH_LONG).show();
                }




            }

        }}

