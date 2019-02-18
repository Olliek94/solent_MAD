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

import static uk.ac.solent.mapping.R.id.btn2;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    public static final Double DEFAULT_LAT = 51.3975;
    public static final Double DEFAULT_LON = -1.3226;
    public static final Integer DEFAULT_ZOOM = 11;

    MapView mv;
    Button button1 = (Button)findViewById (R.id.btn1);
    Button button2 = (Button)findViewById (btn2);


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // set default values lat lon
        EditText lonEditText = (EditText) findViewById(R.id.etLon);
        lonEditText.setText(DEFAULT_LON.toString());
        EditText latEditText = (EditText) findViewById(R.id.etLat);
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

    @Override
    public void onClick(View view) {

    }
}