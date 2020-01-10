package com.alenva.lennkentest.Class;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alenva.lennkentest.Controller.AppController;
import com.alenva.lennkentest.Model.Gas;
import com.alenva.lennkentest.R;
import com.alenva.lennkentest.Util.Constants;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class gas_maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double lon, lat;
    private BitmapDescriptor[] iconColors;
    private Button back_button;
    private int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        back_button = findViewById(R.id.back_Button);
        iconColors = new BitmapDescriptor[]{
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA),

                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)

        };

        for (int i = 1 ; i<limit; i++ ) {
            String url = "https://api.datos.gob.mx/v1/precio.gasolina.publico?page=" + i;
//            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    url, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {

                        JSONArray results = response.getJSONArray("results");

                        for (int j = 0; j < results.length(); j++) {

                            Gas gas = new Gas();
                            JSONObject jsonObject = results.getJSONObject(j);
                            if (!jsonObject.isNull("razonsocial")) {
                                gas.setName(jsonObject.getString("razonsocial"));
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
                            lon = Double.valueOf(gas.getLongitude());
                            lat = Double.valueOf(gas.getLatitude());
                            MarkerOptions markerOptions = new MarkerOptions();

                            markerOptions.icon(iconColors[Constants.randomInt(iconColors.length, 0)]);
                            markerOptions.title(gas.getName());
                            markerOptions.position(new LatLng(lat, lon));
                            markerOptions.snippet("Magna: $" + gas.getRegular()
                                    + " " + "Premium: $" + gas.getPremium());

                            Marker marker = mMap.addMarker(markerOptions);
                            marker.setTag(gas.getName());
                            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 1));


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    VolleyLog.d("Error", "Error: " + error.getMessage());

                }
            });
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
            //requestQueue.add(jsonObjectRequest);
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4978,  -99.126919), 1));
        }
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }
}
