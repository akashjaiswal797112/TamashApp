package com.akash.tamashaproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    ListView list;
    private RequestQueue mQueue;
    Integer imgid= R.drawable.img;
    MyListView adapter;
    String[] maintitle;
    String[] subtitle;
    String[] salary;
    String url = "https://mocki.io/v1/61cf7d91-a7f8-405e-b505-67926b759d78";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        functionFetchData();

    }



    private void functionFetchData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            maintitle = new String[jsonArray.length()];
                            subtitle = new String[jsonArray.length()];
                            salary = new String[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject tamashaApi = jsonArray.getJSONObject(i);

                                maintitle[i] = tamashaApi.getString("employee_name");
                                subtitle[i] = tamashaApi.getString("id");
                                salary[i] = tamashaApi.getString("employee_salary");


                            }
                            adapter = new MyListView(MainActivity.this, maintitle,subtitle,imgid);
                            list=(ListView)findViewById(R.id.list);
                            list.setAdapter(adapter);

                            functionShowsalary();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

        private void functionShowsalary() {

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub


                Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
                String pass_Name = maintitle[position];
                String pass_id = subtitle[position];
                String pass_salary = salary[position];
                Integer pass_image = imgid;
                intent.putExtra("EXTRA_MESSAGE1", pass_Name);
                intent.putExtra("EXTRA_MESSAGE2", pass_id);
                intent.putExtra("EXTRA_MESSAGE3", pass_salary);
                intent.putExtra("EXTRA_MESSAGE4", pass_image);

                startActivity(intent);


            }
        });
    }



}