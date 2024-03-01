package com.app.q2p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetail extends AppCompatActivity {
    private TextView title;
    private TextView description;
    private TextView brand;
    private TextView categry;
    private TextView price;

    private CircleImageView img;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        title=findViewById(R.id.title);
        description=findViewById(R.id.descr);
        brand=findViewById(R.id.brand);
        categry=findViewById(R.id.category);
        price=findViewById(R.id.price);

        progressBar = findViewById(R.id.progressBar);
        img=findViewById(R.id.profile_image);

        Intent intent = getIntent();
        String n = intent.getStringExtra("idss");
        Log.d("check", "onCreate: " + n);

        getSupportActionBar().setTitle("Product Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RequestQueue queues = Volley.newRequestQueue(this);

        String url = "https://dummyjson.com/products/" + n;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            int id = response.getInt("id");
                            String titles = response.getString("title");
                            String descriptions = response.getString("description");
                            String brands=response.getString("brand");
                            String categs=response.getString("category");
                            String prices=response.getString("price");
                            String imgs= response.getString("thumbnail");

                            title.setText(titles);
                            description.setText(descriptions);
                            brand.setText(brands);
                            categry.setText(categs);
                            price.setText(prices);
                            Glide.with(ProductDetail.this).load(imgs).into(img);

                            Log.d("resp", "onCreate: " + id);
                            Log.d("resp", "onCreate: " + titles);
                            Log.d("resp", "onCreate: " + descriptions);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProductDetail.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ProductDetail.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });

        queues.add(jsonObjectRequest);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}