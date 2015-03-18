package cs.citytour;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class PlaceActivity extends FragmentActivity {

    TextView nameView;
    WebView webView;
    SupportMapFragment mapFragment;
    GoogleMap googleMap;

    String placeName = "";
    String URL = "";
    double[] placePoint = new double[2];

    boolean mapState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        Intent intent = getIntent();
        int id = intent.getIntExtra("PlaceNumber", 0);

        nameView = (TextView)findViewById(R.id.placeNameView);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView v, String url) {
                v.loadUrl(url);
                return true;
            }
        });
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        googleMap = mapFragment.getMap();

        switch(id){
            case 1:
                placeName = "경복궁";
                URL = "http://www.royalpalace.go.kr";
                placePoint[0] = 37.579634;
                placePoint[1] = 126.977127;
                break;
            case 2:
                placeName = "창경궁";
                URL = "http://cgg.cha.go.kr";
                placePoint[0] = 37.578758;
                placePoint[1] = 126.994816;
                break;
            case 3:
                placeName = "국립중앙박물관";
                URL = "http://museum.go.kr";
                placePoint[0] = 37.513742;
                placePoint[1] = 126.949578;
                break;
            case 4:
                placeName = "현충원";
                URL = "http://snmb.mil.kr";
                placePoint[0] = 37.500206;
                placePoint[1] = 126.973200;
                break;
        }

        nameView.setText(placeName);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(placePoint[0], placePoint[1])));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    public void mapButtonClicked(View v){
        if(mapState == false){
            mapState = true;
            webView.setVisibility(View.INVISIBLE);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(placePoint[0], placePoint[1])));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }

    public void infoButtonClicked(View v){
        if(mapState == true){
            mapState = false;
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(URL);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("PlaceName", placeName);
        setResult(RESULT_OK, intent);
        finish();
    }
}
