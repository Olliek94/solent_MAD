package uk.ac.solent.mapping;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MainActivity extends AppCompatActivity implements OnClickListener
{
    public static final Double DEFAULT_LAT = 51.3975;
    public static final Double DEFAULT_LON = -1.3226;
    public static final Integer DEFAULT_ZOOM = 11;

    MapView mv;



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        // set default values lat lon
        EditText lonEditText = (EditText) findViewById(R.id.etLon);
        lonEditText.setText(DEFAULT_LON.toString());
        EditText latEditText = (EditText) findViewById(R.id.etLat);
        latEditText.setText(DEFAULT_LAT.toString());

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);

        // This line sets the user agent, a requirement to download OSM maps


        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
        mv.getController().setCenter(new GeoPoint(51.3975,-1.3226));



    }

    @Override
    public void onClick(View view) {

    }
}