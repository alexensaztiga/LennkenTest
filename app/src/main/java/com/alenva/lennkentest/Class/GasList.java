package com.alenva.lennkentest.Class;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.alenva.lennkentest.Adapter.RecyclerViewAdapter;
import com.alenva.lennkentest.Controller.AppController;
import com.alenva.lennkentest.Model.Gas;
import com.alenva.lennkentest.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GasList extends AppCompatActivity {
    private Button map_button;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RequestQueue requestQueue;
    private List<Gas> gasList =  new ArrayList<>();
    private int limit =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_list);


        map_button = findViewById(R.id.map_button);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        for (int i = 1; i < limit; i++) {
            String url = "https://api.datos.gob.mx/v1/precio.gasolina.publico?page=" + i;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    url, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {

                        JSONArray results = response.getJSONArray("results");

                        for (int i = 0; i < results.length(); i++) {

                            Gas gas = new Gas();
                            JSONObject jsonObject = results.getJSONObject(i);
                            if (!jsonObject.isNull("razonsocial")) {
                                gas.setName(jsonObject.getString("razonsocial"));
                            }
                            if (!jsonObject.isNull("calle")) {
                                gas.setStreet(jsonObject.getString("calle"));
                            }
                            if (!jsonObject.isNull("codigopostal")) {
                                gas.setPostalcode(jsonObject.getString("codigopostal"));
                            }
                            if (!jsonObject.isNull("rfc")) {
                                gas.setRfc(jsonObject.getString("rfc"));
                            }
                            if (!jsonObject.isNull("regular")) {
                                gas.setRegular(jsonObject.getString("regular"));
                            }
                            if (!jsonObject.isNull("premium")) {
                                gas.setPremium(jsonObject.getString("premium"));
                            }

                            if (!jsonObject.isNull("latitude")) {
                                gas.setLatitude(jsonObject.getString("latitude"));
                            }
                            if (!jsonObject.isNull("longitude")) {
                                gas.setLongitude(jsonObject.getString("longitude"));
                            }

                            gasList.add(gas);


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    recyclerViewAdapter = new RecyclerViewAdapter(GasList.this, gasList);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.notifyDataSetChanged();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    VolleyLog.d("Error", "Error: " + error.getMessage());

                }
            });
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
            // requestQueue.add(jsonObjectRequest);
        }
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GasList.this, gas_maps.class);
                startActivity(intent);
            }
        });

    }


}
