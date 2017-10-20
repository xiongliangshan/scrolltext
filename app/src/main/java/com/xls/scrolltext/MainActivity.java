package com.xls.scrolltext;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.xls.library.ScrollTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ScrollTextView scrollTextView;
    private ScrollTextView scrollTextView1;
    private Button startBtn;
    private Button pauseBtn;
    private Button stopBtn;

    private Button slowBtn,normalBtn,fastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.btn_start);
        pauseBtn = (Button) findViewById(R.id.btn_pause);
        stopBtn = (Button) findViewById(R.id.btn_end);
        slowBtn = (Button) findViewById(R.id.btn_slow);
        normalBtn = (Button) findViewById(R.id.btn_normal);
        fastBtn = (Button) findViewById(R.id.btn_fast);
        scrollTextView = (ScrollTextView) findViewById(R.id.marqueeView);
        scrollTextView1 = (ScrollTextView) findViewById(R.id.marqueeView1);
        startBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        slowBtn.setOnClickListener(this);
        normalBtn.setOnClickListener(this);
        fastBtn.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                scrollTextView.startScroll();
                scrollTextView1.startScroll();
                break;
            case R.id.btn_pause:
                scrollTextView.pauseScroll();
                scrollTextView1.pauseScroll();
                break;
            case R.id.btn_end:
                scrollTextView.stopScroll();
                scrollTextView1.stopScroll();
                break;
            case R.id.btn_slow:
                scrollTextView1.setSpeed(ScrollTextView.SLOW);
                scrollTextView.setSpeed(ScrollTextView.SLOW);
                break;
            case R.id.btn_normal:
                scrollTextView.setSpeed(ScrollTextView.NORMAL);
                scrollTextView1.setSpeed(ScrollTextView.NORMAL);
                break;
            case R.id.btn_fast:
                scrollTextView.setSpeed(ScrollTextView.FAST);
                scrollTextView1.setSpeed(ScrollTextView.FAST);
                break;
        }
    }
}
