package com.example.saud.meta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.low)
    EditText mLow;
    @BindView(R.id.high)
    EditText mHigh;
    @BindView(R.id.stop)
    EditText mStop;
    @BindView(R.id.tv_most_low)
    TextView mTvMostLow;
    @BindView(R.id.tv_second_low)
    TextView mTvSecondLow;
    @BindView(R.id.tv_second_hight)
    TextView mTvSecondHight;
    @BindView(R.id.tv_most_hight)
    TextView mTvMostHight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initListener();

    }

    private void initListener() {
        mLow.addTextChangedListener(watcher);
        mHigh.addTextChangedListener(watcher);
        mStop.addTextChangedListener(watcher);
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String stopStr = mStop.getText().toString().trim();
            float stopNum = 0;
            if (!TextUtils.isEmpty(stopStr)) {
                stopNum = Float.parseFloat(stopStr);
            }

            String hightStr = mHigh.getText().toString().trim();
            float highNum = 0;
            if (!TextUtils.isEmpty(hightStr)) {
                highNum = Float.parseFloat(hightStr);
            }

            String lowStr = mLow.getText().toString().trim();
            float lowNum = 0;
            if (!TextUtils.isEmpty(lowStr)) {
                lowNum = Float.parseFloat(lowStr);
            }

            format(lowNum, highNum, stopNum);
        }
    };


    private void format(float lowNum, float hightNum, float stopNum) {
        float average = ((stopNum * 2) + hightNum + lowNum) / 4;
        float mostH = average + hightNum - lowNum;
        float mostL = average + lowNum - hightNum;
        float seH = average + average - lowNum;
        float seL = average + average - hightNum;

        mTvMostHight.setText("Top Price：" + mostH);
        mTvSecondHight.setText("Second Price：" + seH);
        mTvSecondLow.setText("Low Price：" + seL);
        mTvMostLow.setText("Floor Price：" + mostL);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
