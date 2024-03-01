package com.app.q2p;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ArrayList<DataClass> arrlists = new ArrayList<>();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progressBar);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        Adapter adap = new Adapter(this, arrlists);
        recycler.setAdapter(adap);

        progressBar.setVisibility(View.VISIBLE);

        RequestQueue queues = Volley.newRequestQueue(this);
        String url = "https://dummyjson.com/products";


        JsonObjectRequest jsonObject = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONArray a = response.getJSONArray("products");
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject b = a.getJSONObject(i);
                                String idss = b.getString("id");
                                String names = b.getString("title");
                                String img=b.getString("thumbnail");
                                String price=b.getString("price");
                                String brand=b.getString("brand");
                                arrlists.add(new DataClass(idss, names, img, price, brand));
                            }
                            adap.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });

        queues.add(jsonObject);

    }
}