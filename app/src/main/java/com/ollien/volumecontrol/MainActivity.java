package com.ollien.volumecontrol;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    Button volumeUp;
    Button volumeDown;
    Button muteButton;
    VolumeTransmit vt;
    String ip = "192.168.1.55";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        muteButton = (Button)findViewById(R.id.mute);
        volumeUp = (Button)findViewById(R.id.volumeUp);
        volumeDown = (Button)findViewById(R.id.volumeDown);
        vt = new VolumeTransmit();
        vt.execute(ip,"5565");
        Toast.makeText(this, "Should have connected, check logs", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void volumeUpClick(View v){
        vt.send("up");
    }
    public void volumeDownClick(View v){
        vt.send("down");
    }
    public void muteClick(View v){
        vt.send("mute");
    }
}
