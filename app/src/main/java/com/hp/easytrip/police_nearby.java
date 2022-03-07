package com.hp.easytrip;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.hp.easytrip.databinding.ActivityPoliceNearbyBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class police_nearby extends FragmentActivity implements OnMapReadyCallback {

    FusedLocationProviderClient client;
    double lat, log;
    private GoogleMap mMap;
    private ActivityPoliceNearbyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPoliceNearbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLoca();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);

        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getCurrentLoca() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        lat = location.getLatitude();
                        log = location.getLongitude();
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        MarkerOptions options = new MarkerOptions().position(latLng).title("Current Postion");
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        mMap.addMarker(options);
                    }
                }
            });
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        getCurrentLoca();

        StringBuilder builder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        builder.append("location="+lat+","+log);
        builder.append("&radius=1000");
        builder.append("&keyword="+"atm");
        builder.append("&sensor=true");
        builder.append("&key="+getResources().getString(R.string.KEY));

        String url = builder.toString();
        Object data[] = new Object[2];
        data[0]=mMap;
        data[1]=url;
        FetchData fetchData =new FetchData();
        fetchData.execute(data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLoca();
            }
        }
    }

    class DownloadURL {
        public String retriveurl(String url) throws IOException {
            String urlData = "";
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            try {
                URL getUrl = new URL(url);
                httpURLConnection = (HttpURLConnection) getUrl.openConnection();
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                urlData = sb.toString();
                bufferedReader.close();
            } catch (Exception e) {
                Log.e("TAG", "retriveurl: " + e);
            } finally {
                inputStream.close();
                httpURLConnection.disconnect();
            }
            return urlData;
        }
    }

    class FetchData extends AsyncTask<Object, String, String> {
        String googleNearbyData;
        String url;
        GoogleMap map;

        @Override
        protected String doInBackground(Object... objects) {
            try {
                map = (GoogleMap) objects[0];
                url = (String) objects[1];
                DownloadURL downloadURL = new DownloadURL();
                googleNearbyData = downloadURL.retriveurl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return googleNearbyData;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject object = new JSONObject(s);
                JSONArray jsonArray = object.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject getLOC = jsonObject.getJSONObject("geometry")
                            .getJSONObject("location");
                    String lat = getLOC.getString("lat");

                    String lang = getLOC.getString("lng");

                    JSONObject getname =jsonArray.getJSONObject(i);
                    String name = getname.getString("name");

                    LatLng latLng = new LatLng(Double.parseDouble(lat),Double.parseDouble(lang));
                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(name);
                    map.addMarker(markerOptions);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}