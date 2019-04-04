package com.stlindia.optclapprv;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.stlindia.optclapprv.adapter.myAdapter;
import com.stlindia.optclapprv.model.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

        String[] block = { "None", "block1", "block2", "block3", "block4", "block5",  };
        String[] village = { "None", "village1", "village2", "village3", "village4", "village5",  };
        String[] status = { "None", "approved", "not approved",  };
        private List<Consumer> consumerList = new ArrayList<>();
        private List<Consumer> tempConsumerList = new ArrayList<>();
        private RecyclerView recyclerView;
        private myAdapter mAdapter;
        private Toolbar myToolbar;
        private Spinner spin,spin1,spin2;
        private int flag=0;
        private String idd,uid,uid1,en;
        private int flag1=0,flag2=0;
        public static final int CONNECTION_TIMEOUT = 10000;
        public static final int READ_TIMEOUT = 15000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                myToolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(myToolbar);
                getSupportActionBar().setTitle("Home");
                myToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
                //spinner
                spin = (Spinner) findViewById(R.id.block);
                spin1 = (Spinner) findViewById(R.id.village);
                spin1.setEnabled(false);
                spin2= (Spinner) findViewById(R.id.status);
                spin2.setEnabled(false);
                uid= getIntent().getStringExtra("userid");
                uid1= getIntent().getStringExtra("useid");
                en= getIntent().getStringExtra("enam");
                Log.d("TAG", "onCreate:intent value -----------------------------------------------------------"+uid);
                new AsyncFetch().execute();
                spin.setOnItemSelectedListener(this);
                spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
                        {

                                ++flag1;
                                if(flag1>=2) {
                                        spin2.setEnabled(true);
                                        Toast.makeText(getApplicationContext(), village[position], Toast.LENGTH_LONG).show();
                                }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                        }

                });
                spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
                        {
                                ++flag2;
                                if(flag2>=2) {
                                        Toast.makeText(getApplicationContext(), status[position], Toast.LENGTH_LONG).show();
                                }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                        }

                });

                //Creating the ArrayAdapter instance having the country list
                ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,block);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter aaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,village);
                aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ArrayAdapter aaaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);
                aaaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spin.setAdapter(aa);
                spin1.setAdapter(aaa);
                spin2.setAdapter(aaaa);
                //recyclerview
                recyclerView=(RecyclerView)findViewById(R.id.recycler);
                RecyclerView.LayoutManager mLayoutmanager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutmanager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                final String uid=getIntent().getStringExtra("IDD");
                //consumerData();
                recyclerView.addOnItemTouchListener(new Recyclertouchlistener(getApplicationContext(), recyclerView, new Recyclertouchlistener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                                Intent i=new Intent(getApplicationContext(),Main3Activity.class);
                                i.putExtra("key", consumerList.get(position).getConsumer());
                                i.putExtra("key1", consumerList.get(position).getPhone());
                                i.putExtra("key2", consumerList.get(position).getMeter());
                                i.putExtra("key5",consumerList.get(position).getId());
                                i.putExtra("status",consumerList.get(position).getStatus());
                                i.putExtra("ename",en);
                                i.putExtra("userid",uid);
                                startActivity(i);

                                //Toast.makeText(getApplicationContext(), movie.getConsumer() + " is selected!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLongClick(View view, int position) {

                        }
                }));

        }
       /* public void consumerData()
        {
                Consumer consumer=new Consumer("name","address", "1234");
                consumerList.add(consumer);
                consumer=new Consumer("ludovicho eunaudi","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("sarthak mohanty","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("saurav mohanty","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("4 non blondes","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("daft punk","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("Rahul kumar","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("linkin park","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("roshan kumar","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("helefrdv","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("hello","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("coldplay","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("lovelytheband","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("avegened sevenfold","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("backstreet boys","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("maroon5","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("adelle","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("hello","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("hello","myntra","12345");
                consumerList.add(consumer);
                consumer=new Consumer("hello","myntra","12345");
                consumerList.add(consumer);consumer=new Consumer("hello","myntra","12345");
                consumerList.add(consumer);
                tempConsumerList.addAll(consumerList);


        }*/
        
        //spinner
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

                flag++;
                if(flag>=2){
                        spin1.setEnabled(true);
                Toast.makeText(getApplicationContext(),block[position] ,Toast.LENGTH_LONG).show();}


        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {


        }
        //searchbar
        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.menuitems, menu);
                Log.d("Menu items checking","----------------------->menu intializing");
                MenuItem menuItem = menu.findItem(R.id.new_search);
                SearchView searchView =(SearchView) menuItem.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                                return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                                filter(newText);
                                return false;
                        }
                });
                return true;
        }

        private void filter(String newText) {
                String lowerString = newText.toLowerCase();
                consumerList.clear();
                mAdapter.notifyDataSetChanged();
                if (!TextUtils.isEmpty(lowerString)){
                        for (Consumer consumer:tempConsumerList){
                                if (consumer.getConsumer().contains(lowerString)){
                                        consumerList.add(consumer);
                                }
                        }
                }else {
                        consumerList.addAll(tempConsumerList);
                }
                mAdapter.notifyDataSetChanged();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {

                switch (item.getItemId())
                {


                        default:
                                Log.d("Menu items checking","----------------------->deafult case");
                                return super.onOptionsItemSelected(item);

                }
        }
        private class AsyncFetch extends AsyncTask<String, String, String> {
                ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
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

                                url = new URL("http://192.168.137.1:8181/optcl_demo/display.php?id="+uid);

                        } catch (MalformedURLException e) {
                                e.printStackTrace();
                                return e.toString();
                        }
                        try {
                                Log.d("team", "doInBackground: connection -------------------------------------");

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
                                Log.d("team", "doInBackground:loading -------------------------------------");

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
                                Log.d("t4", "onPstExecut: parsing-------------------------------------");
                                JSONObject jsonobject =new JSONObject(result);
                                JSONArray jArray = jsonobject.getJSONArray("consumer_output_07052018");
                                for (int i = 0; i < jArray.length(); i++) {
                                        JSONObject json_data = jArray.getJSONObject(i);
                                        Consumer consumerData = new Consumer();
                                        Log.d("1", "enter ------------------------------------------------------------------ "+consumerList);
                                        consumerData.consumer = json_data.getString("name");
                                        consumerData.meter = json_data.getString("meter number");
                                        consumerData.phone = json_data.getString("phone");
                                        consumerData.block = json_data.getString("block");
                                        consumerData.village = json_data.getString("village");
                                        consumerData.district = json_data.getString("district");
                                        consumerData.date = json_data.getString("date");
                                        consumerData.status = json_data.getString("status");
                                        Log.d("TAG", "onPostExecute: ----------------------------------------------"+consumerData.status);
                                        idd=consumerData.id = json_data.getString("pk");

                                        consumerList.add(consumerData);
                                        Log.d("2", "exit ------------------------------------------------------------------ "+consumerList);

                                }
                                tempConsumerList.addAll(consumerList);
                                recyclerView = (RecyclerView) findViewById(R.id.recycler);
                                mAdapter = new myAdapter(MainActivity.this, consumerList);
                                recyclerView.setAdapter(mAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        } catch (JSONException e) {
                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                }


        }

        @Override
        protected void onStart() {

                super.onStart();
        }
}

