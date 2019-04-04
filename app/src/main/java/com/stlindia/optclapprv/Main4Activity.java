package com.stlindia.optclapprv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.PriorityQueue;

public class Main4Activity extends AppCompatActivity {
    private Button button2;
    private Toolbar myToolbar;
    private EditText consumer_name_edit, meter_number, initial_reading, type, TCSN, make, max_digit, capacity, tp1_seal_number, tp2_seal_number;
    public String consumer_name_edit1, meter_number1, initial_reading1, type1, TCSN1, make1, max_digit1, capacity1, tp1_seal_number1, tp2_seal_number1;
    public String block,en,uid,sta, aadhar, phone, name, customer_type, address1, address2, village, district, CVC, previous_connections, SDO, binder, load, connections, remarks;
    private String l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, idd,status;
    public static final int CONNECTION_TIMEOUT = 10000;
    private int idd1,flag=0;
    private final String TAG = getClass().getSimpleName();
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(myToolbar);
        uid= getIntent().getStringExtra("userid");
        getSupportActionBar().setTitle("Information");
        consumer_name_edit = (EditText) findViewById(R.id.consumer_name_edit);
        consumer_name_edit.setEnabled(false);
        meter_number = (EditText) findViewById(R.id.meter_number);
        initial_reading = (EditText) findViewById(R.id.initial_reading);
        type = (EditText) findViewById(R.id.type);
        TCSN = (EditText) findViewById(R.id.TCSN);
        make = (EditText) findViewById(R.id.make);
        max_digit = (EditText) findViewById(R.id.max_digit);
        capacity = (EditText) findViewById(R.id.capacity);
        tp1_seal_number = (EditText) findViewById(R.id.tp1_seal_number);
        tp2_seal_number = (EditText) findViewById(R.id.tp2_seal_number);
        //previous activity stuff
        idd = getIntent().getStringExtra("key6");
        status=getIntent().getStringExtra("status");
        Log.d(TAG, "onCreate:--------------------------------------------------------------------> "+idd);
        name = getIntent().getStringExtra("consumer");
        Log.d(TAG, "-------------------------------------------------------------------------------> "+name);
        aadhar = getIntent().getStringExtra("aadhar");
        en= getIntent().getStringExtra("ename");
        phone = getIntent().getStringExtra("phone");
        customer_type = getIntent().getStringExtra("consumer_type");
        address1 = getIntent().getStringExtra("address1");
        address2 = getIntent().getStringExtra("address2");
        block = getIntent().getStringExtra("block");
        village = getIntent().getStringExtra("village");
        district = getIntent().getStringExtra("district");
        Log.d(TAG, "----------------------------------------------------------------------------> "+district);
        CVC = getIntent().getStringExtra("CVC");
        previous_connections = getIntent().getStringExtra("previous_connections");
        SDO = getIntent().getStringExtra("SDO");
        binder = getIntent().getStringExtra("binder");
        load = getIntent().getStringExtra("load");
        connections = getIntent().getStringExtra("connections");
        remarks = getIntent().getStringExtra("remarks");
        //previous activity stuff
        new AsyncFetch().execute();
        Log.d(TAG, "status status status status ------------------------------------------------------"+sta);
        //this activity stuff

        //this activity stuff
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        button2=(Button)findViewById(R.id.submit);
        Log.d(TAG, "flag value is hereererererererererere ---------------------------------------------------"+status);
        if(status.equals("APPROVED")) {
            button2.setEnabled(false);
            consumer_name_edit.setEnabled(false);
            meter_number.setEnabled(false);
            initial_reading.setEnabled(false);
            type.setEnabled(false);
            TCSN.setEnabled(false);
            make.setEnabled(false);
            max_digit.setEnabled(false);
            capacity.setEnabled(false);
            tp1_seal_number.setEnabled(false);
            tp2_seal_number.setEnabled(false);
        }

        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        consumer_name_edit1 = consumer_name_edit.getText().toString();
                        meter_number1 = meter_number.getText().toString();
                        initial_reading1 = initial_reading.getText().toString();
                        type1 = type.getText().toString();
                        TCSN1 = TCSN.getText().toString();
                        make1 = make.getText().toString();
                        max_digit1 = max_digit.getText().toString();
                        capacity1 = capacity.getText().toString();
                        tp1_seal_number1 = tp1_seal_number.getText().toString();
                        tp2_seal_number1 = tp2_seal_number.getText().toString();

                       new AsyncUpdate().execute();

                    }
                });



    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Main4Activity.this);
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
            Log.d("team", "doInBackground: entry -------------------------------------");
            try {
                String urlstring = "http://192.168.137.1:8181/optcl_demo/editdetails.php?id="+idd;
                Log.d(TAG, "doInBackground:----------------------------------- " + urlstring);
                url = new URL(urlstring);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                Log.d(TAG, "doInBackground: connection -------------------------------------");

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
                Log.d("t4", "onPstExecut: parsing-------------------------------------" + result);
                JSONObject jsonobject = new JSONObject(result);
                JSONArray jArray = jsonobject.getJSONArray("consumer_output_07052018");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Consumer consumerData = new Consumer();
                    consumer_name_edit.setText(name);
                    l2 = consumerData.meter = json_data.getString("meter number");
                    meter_number.setText(l2);
                    l3 = consumerData.initial_reading = json_data.getString("initial reading");
                    initial_reading.setText(l3);
                    l4 = consumerData.type = json_data.getString("type");
                    type.setText(l4);
                    l5 = consumerData.TCSN = json_data.getString("TCSN");
                    TCSN.setText(l5);
                    l6 = consumerData.make = json_data.getString("make");
                    make.setText(l6);
                    l7 = consumerData.max_digit = json_data.getString("max digit");
                    max_digit.setText(l7);
                    l8 = consumerData.capacity = json_data.getString("capacity");
                    capacity.setText(l8);
                    l9 = consumerData.tp1_seal_number = json_data.getString("tp1 seal number");
                    tp1_seal_number.setText(l9);
                    l10 = consumerData.tp2_seal_nummber = json_data.getString("tp2 seal no");
                    tp2_seal_number.setText(l10);
                }


            } catch (JSONException e) {
                Toast.makeText(Main4Activity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

    private class AsyncUpdate extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Main4Activity.this);
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
            Log.d("team", "doInBackground: entry -------------------------------------");
            try {
                Log.d(TAG, "doInBackground:------------------------------------------------------------------------ "+idd);
                Log.d(TAG, "doInBackground: ---------------------------------------------------------------------------------------"+name);
                Log.d(TAG, "doInBackground: ================================================================================="+tp1_seal_number1);
                Log.d(TAG, "doInBackground: ---------------------------------------------------------------------------------------"+tp1_seal_number);
                String urlstring1 = "http://192.168.137.1:8181/optcl_demo/update.php? id= " +idd+ " & name= "+name+" & aadhar= "+aadhar+" & phone= "+phone+" & customer_type= "+customer_type+" & address1= "+address1+" & address2= "+address2+" & block= "+block+" & village= "+village+" & district= "+district+" & CVC= "+CVC+" & previous_connections= "+previous_connections+" & SDO= "+SDO+" & binder= "+binder+" & load= "+load+" & connections= "+connections+" & remarks= "+remarks+" & meter_number= "+meter_number1+" & initial_reading= "+initial_reading1+" & type= "+type1+" & TCSN= "+TCSN1+" & make= "+make1+" & max_digit= "+max_digit1+" & capacity= "+capacity1+" & tp1= "+tp1_seal_number1+" & tp2= "+tp2_seal_number1+"& ename="+en;
                Log.d(TAG, "doInBackground:----------------------------------- this is the update url " + urlstring1);
                url = new URL(urlstring1);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                Log.d(TAG, "doInBackground: connection ------------------------------------- the update url");

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();
            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                Log.d(TAG, "doInBackground:loading ------------------------------------- the update url ");

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
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("useid",uid);
            startActivity(intent);

                }


        }

    }

