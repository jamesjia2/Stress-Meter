package jamesjia2.com.gmail.stressmeter_james_jia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static Vibrator vib;
    static MediaPlayer player;
    long waitPattern = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start vibrating
        long[] pattern = new long[] {waitPattern, waitPattern, waitPattern};
        vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        vib.vibrate(pattern, 1);

        //start music
        player = new MediaPlayer();
        player = MediaPlayer.create(this, R.raw.ringtone2);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setLooping(true);
        player.start();

        //set schedule
        PSMScheduler.setSchedule(this);

        //toolbar and navigation
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //initialize starting fragment
        Class fragmentClass;
        Fragment fragment = null;
        fragmentClass = rMeterFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //replace the framelayout in the layout xml with the fragment's UI
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

        checkPermission();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //stop vibrating and stop ringtone
        vib.cancel();
        player.stop();

        //get the item that was selected's id
        int id = item.getItemId();

        //get fragments
        Class fragmentClass;
        Fragment fragment = null;

        if (id == R.id.Results) {
            fragmentClass = ResultsFragment.class;
        }
        else{
            fragmentClass = rMeterFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //replace frame in content_main with fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());

        //close drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //get permission for reading/writing external storage
    private void checkPermission() {

        //reading, writing and camera permissions
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        //only need to check if version >23
        if(Build.VERSION.SDK_INT > 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 10);

            }
        }
    }

}
