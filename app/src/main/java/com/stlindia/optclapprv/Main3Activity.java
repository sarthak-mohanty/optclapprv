package com.stlindia.optclapprv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class Main3Activity extends AppCompatActivity {
    private final String TAG=getClass().getSimpleName();
    private Toolbar myToolbar;
    private Button button1;
    private EditText editText1, editText2, editText3, block, aadhar, phone, name, customer_type, address1, address2, village, district, CVC, previous_connections, SDO, binder, load, connections, remarks;
    private String id,uid, id1, id2,status, idd,en, l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16;
    private int iddd;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //this is gor the edit text
        block = (EditText) findViewById(R.id.block_edit);
        uid= getIntent().getStringExtra("userid");
        en= getIntent().getStringExtra("ename");
        aadhar = (EditText) findViewById(R.id.aadhar_edit);
        name = (EditText) findViewById(R.id.name_edit);
        phone = (EditText) findViewById(R.id.phone_edit);
        customer_type = (EditText) findViewById(R.id.customer_type_edit);
        address1 = (EditText) findViewById(R.id.address1_edit);
        address2 = (EditText) findViewById(R.id.address2_edit);
        village = (EditText) findViewById(R.id.village_edit);
        district = (EditText) findViewById(R.id.district_edit);
        CVC = (EditText) findViewById(R.id.CVC_edit);
        previous_connections = (EditText) findViewById(R.id.previous_connections_edit);
        SDO = (EditText) findViewById(R.id.SDO_edit);
        binder = (EditText) findViewById(R.id.binder_edit);
        load = (EditText) findViewById(R.id.load_edit);
        connections = (EditText) findViewById(R.id.connections_edit);
        remarks = (EditText) findViewById(R.id.remarks_edit);
        //till here
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Information");
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        button1 = findViewById(R.id.button2);
        idd= getIntent().getStringExtra("key5");
        status= getIntent().getStringExtra("status");
        iddd=Integer.parseInt(idd);
        if(status.equals("APPROVED"))
        {
            block.setEnabled(false);
            aadhar.setEnabled(false);
            name.setEnabled(false);
            phone.setEnabled(false);
            customer_type.setEnabled(false);
            address1.setEnabled(false);
            address2.setEnabled(false);
            village.setEnabled(false);
            district.setEnabled(false);
            CVC.setEnabled(false);
            previous_connections.setEnabled(false);
            SDO.setEnabled(false);
            binder.setEnabled(false);
            load.setEnabled(false);
            connections.setEnabled(false);
            remarks.setEnabled(false);
        }
        new AsyncFetch().execute();

        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
                        Log.d(TAG, "onClick:------------------------------------------------"+iddd);
                        intent.putExtra("key6",Integer.toString(iddd));
                        intent.putExtra("consumer",name.getText().toString());
                        Log.d(TAG, "--------------------------------------------------------------------"+name.getText().toString());
                        intent.putExtra("aadhar",aadhar.getText().toString());
                        intent.putExtra("phone",phone.getText().toString());
                        intent.putExtra("consumer_type",customer_type.getText().toString());
                        intent.putExtra("address1",address1.getText().toString());
                        Log.d(TAG, "--------------------------------------------------------------------"+address1.getText().toString());
                        intent.putExtra("address2",address2.getText().toString());
                        intent.putExtra("block",block.getText().toString());
                        intent.putExtra("village",village.getText().toString());
                        intent.putExtra("district",district.getText().toString());
                        intent.putExtra("CVC",CVC.getText().toString());
                        intent.putExtra("previous_connections",previous_connections.getText().toString());
                        intent.putExtra("SDO",SDO.getText().toString());
                        intent.putExtra("binder",binder.getText().toString());
                        intent.putExtra("load",load.getText().toString());
                        intent.putExtra("connections",connections.getText().toString());
                        intent.putExtra("remarks",remarks.getText().toString());
                        intent.putExtra("ename",en);
                        intent.putExtra("status",status);
                        intent.putExtra("userid",uid);

                        startActivity(intent);


                    }
                }

        );
        myToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Main3Activity.this);
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
            Log.d(TAG, "doInBackground: entry -------------------------------------");
            try {
                Log.d("3", "-----------------------------------------------------"+idd);
                String urlString = "http://192.168.137.1:8181/optcl_demo/editdetails.php?id="+idd;
                Log.d(TAG,"URL"+urlString);
                url = new URL(urlString);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                Log.d(TAG, "doInBackground: connection -------------------------------------");

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                Log.d(TAG, "doInBackground:loading -------------------------------------");

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
                Log.d("t4", "onPstExecut: parsing-------------------------------------"+result);
                JSONObject jsonobject = new JSONObject(result);
                JSONArray jArray = jsonobject.getJSONArray("consumer_output_07052018");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Consumer consumerData = new Consumer();
                    l1 = consumerData.consumer = json_data.getString("name");
                    name.setText(l1);
                    l2 = consumerData.aadhar = json_data.getString("aadhar");
                    aadhar.setText(l2);
                    l3 = consumerData.phone = json_data.getString("phone");
                    phone.setText(l3);
                    l4 = consumerData.customer_type = json_data.getString("consumer_type");
                    customer_type.setText(l4);
                    l5 = consumerData.address1 = json_data.getString("address1");
                    address1.setText(l5);
                    l6 = consumerData.address2 = json_data.getString("address2");
                    address2.setText(l6);
                    l7 = consumerData.block = json_data.getString("block");
                    block.setText(l7);
                    l8 = consumerData.village = json_data.getString("village");
                    village.setText(l8);
                    l9 = consumerData.district = json_data.getString("district");
                    district.setText(l9);
                    l10 = consumerData.CVC = json_data.getString("CVC");
                    CVC.setText(l10);
                    l11 = consumerData.previous_connections = json_data.getString("previously_connected");
                    previous_connections.setText(l11);
                    l12 = consumerData.SDO = json_data.getString("SDO");
                    SDO.setText(l12);
                    l13 = consumerData.binder = json_data.getString("binder");
                    binder.setText(l13);
                    l14 = consumerData.load = json_data.getString("load");
                    load.setText(l14);
                    l15 = consumerData.connections = json_data.getString("connections");
                    connections.setText(l15);
                    l16 = consumerData.remarks = json_data.getString("remarks");
                    remarks.setText(l16);
                }

            } catch (JSONException e) {
                Toast.makeText(Main3Activity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}
