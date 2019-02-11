package uk.ac.solent.mapping;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity
{

    MapView mv;
    Button button1 = (Button)findViewById (R.id.btn1);
    Button button2 = (Button)findViewById (R.id.btn2);


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // set default values lat lon
        EditText lonEditText = (EditText) findViewById(R.id.longitude);
        lonEditText.setText(DEFAULT_LON.toString());
        EditText latEditText = (EditText) findViewById(R.id.latitude);
        latEditText.setText(DEFAULT_LAT.toString());

        Button = (button1) findViewById(R.id.btn1);
        c.setOnClickListener(this);
        Button = (button2) findViewById(R.id.btn2);
        d.setOnClickListener(this);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
        mv.getController().setCenter(new GeoPoint(51.3975,-1.3226));



    }
}