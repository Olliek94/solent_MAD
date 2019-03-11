package uk.ac.solent.asyncjson;

import android.os.AsyncTask;
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
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.widget.TextView;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    /* Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addSong = (Button) findViewById(R.id.btnGetSongs);
        addSong.setOnClickListener(this);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addartist) {
            //react to the menu item being selected...
            Intent intent = new Intent(this, AddArtistActivity.class);
            startActivityForResult(intent, 0);
            startActivity(intent);
            return true;
        }
        return false;
    }

    public void onClick(View v) {
        EditText artist = (EditText) findViewById(R.id.artist);
        DownloadArtistTask t = new DownloadArtistTask();
        t.execute(artist.getText().toString());


    }

    class DownloadArtistTask extends AsyncTask<String, Void, String> {

        public String doInBackground(String... input) {
            HttpURLConnection conn = null;
            String artist = input[0];

            try {
                //http://www.free-map.org.uk/course/ws/hits.php?artist=Madonna
                URL url = new URL("http://www.free-map.org.uk/course/ws/hits.php?format=json&artist="+artist);
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String result = "", line;
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                    System.out.println("debug"+result);
                    return result;
                } else {
                    return "HTTMadonnaP ERROR: " + conn.getResponseCode();
                }
            } catch (IOException e) {
                return e.toString();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }

        public void onPostExecute(String result) {
            TextView et1 = (TextView) findViewById(R.id.songList);
            et1.setText(result);
        }

    }
}