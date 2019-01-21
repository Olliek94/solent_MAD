package uk.ac.solent.helloworld2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this);
        tv.text = ("Hello World!");
        //setContentView(tv);
        //setContentView(R.layout.activity_main);
    }
}
