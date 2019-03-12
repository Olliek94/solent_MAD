package uk.ac.solent.asyncjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import uk.ac.solent.asyncjson.R;


public class AddArtistActivity extends AppCompatActivity implements OnClickListener {

    /**
     * Called when the activity is first created.
     */

    private static final String BASE_URL = "http://www.free-map.org.uk/course/ws/hits.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_artist);
        Button addSong = (Button) findViewById(R.id.btnAddSong);
        addSong.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText artistet = (EditText) findViewById(R.id.artist);
        EditText songet = (EditText) findViewById(R.id.song);
        EditText yearet = (EditText) findViewById(R.id.date);

        String artist= artistet.getText().toString().trim();
        String song = songet.getText().toString().trim();
        String year = yearet.getText().toString().trim();

        if (artist.isEmpty() ) {
            popupAlert("artist must be set");
        } else if(song.isEmpty() ){
            popupAlert("song must be set");
        } else if(artist.isEmpty() ){
            popupAlert("year must be set");
        } else {
            AddArtistTask t = new AddArtistTask();
            t.execute(artist, song, year);
        }

    }

    class AddArtistTask extends AsyncTask<String, Void, String> {

        public String doInBackground(String... input) {
            HttpURLConnection conn = null;
            String artist = input[0];
            String song = input[1];
            String year = input[2];

            try {

                URL url = new URL("http://www.free-map.org.uk/course/ws/addhit.php" );
                conn = (HttpURLConnection) url.openConnection();
                String postData = "artist="+artist+"&song=" +song+
                        "&year="+year;
                // For POST
                conn.setDoOutput(true);
                conn.setFixedLengthStreamingMode(postData.length());

                OutputStream out = null;
                out = conn.getOutputStream();
                out.write(postData.getBytes());





                if (conn.getResponseCode() == 200) {
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String result = "", line;
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                    return result;
                } else {
                    return "HTTP ERROR: " + conn.getResponseCode();
                }
            } catch (IOException e) {
                return e.toString();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
    }



    private void popupAlert(String message){
        new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage(message).show();
    }

}