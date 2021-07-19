package com.example.b2.cet_mca;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.b2.cet_mca.fragments.about;
import com.example.b2.cet_mca.fragments.calender;
import com.example.b2.cet_mca.fragments.contactus;
import com.example.b2.cet_mca.fragments.facaulty;
import com.example.b2.cet_mca.fragments.FeedbackFragment;
import com.example.b2.cet_mca.fragments.gallery;
import com.example.b2.cet_mca.fragments.home;
import com.example.b2.cet_mca.fragments.NoticeFragment;
import com.example.b2.cet_mca.fragments.PlacementFragment;
import com.example.b2.cet_mca.fragments.Syllabus_fragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean back=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,new home(),"home");
        ft.commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().findFragmentById(R.id.container).getTag().equals("others")) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container,new home(),"home");
            ft.commit();
        }else {
            if(back)
                super.onBackPressed();
            else{
                Toast.makeText(this, "press back again to exit", Toast.LENGTH_SHORT).show();
                back=true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        back=false;
                    }
                },1500);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            startActivity(new Intent(this,LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment f=null;
        String tag="others";
        if (id == R.id.nav_About) {
            f=new about();
        } else if (id == R.id.nav_gallery) {
            f=new gallery();
        } else if (id == R.id.nav_calender) {
            f=new calender();
        } else if (id == R.id.nav_Contact) {
            f=new contactus();
        } else if (id == R.id.nav_Feedback) {
            f=new FeedbackFragment();
        } else if (id == R.id.nav_HOME) {
            tag="home";
            f=new home();
        } else if (id == R.id.nav_faculty) {
            f=new facaulty();
        } else if (id == R.id.nav_syllabus) {
            f=new Syllabus_fragment();
        } else if (id == R.id.nav_placement) {
            f=new PlacementFragment();
        } else if (id == R.id.nav_Notification) {
            f=new NoticeFragment();
        }
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,f,tag);
        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void loadFragment(Fragment f,String tag){
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,f,tag);
        ft.commit();
    }
    public void home(View view) {
        loadFragment(new home(),"home");
    }

    public void syllabus(View view) {
        loadFragment(new Syllabus_fragment(),"others");
    }

    public void notification(View view) {
        loadFragment(new NoticeFragment(),"others");
    }

    public void placement(View view) {
        loadFragment(new PlacementFragment(),"others");
    }

    public void gallery(View view) {
        loadFragment(new gallery(),"others");
    }

    public void faculty(View view) {
        loadFragment(new facaulty(),"others");
    }

    public void calendar(View view) {
        loadFragment(new calender(),"others");
    }

    public void about(View view) {
        loadFragment(new about(),"others");
    }

    public void contact(View view) {
        loadFragment(new contactus(),"others");
    }

    public void call(View v) {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CALL_PHONE"}, 1);
            return;
        }
        Intent i = new Intent("android.intent.action.CALL");
        i.setData(Uri.parse("tel:+7205716253"));
        startActivity(i);
    }

    public void mail(View v) {
        Intent i = new Intent("android.intent.action.SEND");
        i.putExtra("android.intent.extra.EMAIL", new String[]{"mjpinaki@gmail.com"});
        i.putExtra("android.intent.extra.SUBJECT", "Inquiry");
        i.putExtra("android.intent.extra.TEXT", "Hello I want to know about ... !");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i, "Send Email"));
    }

    public void find(View v) {
        //http://maps.google.com/maps?daddr=20.2760989,85.7751577
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://goo.gl/maps/zj8pQmA7iaA2")));
    }

    public void fb(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/CET MCA,BBSR")));
    }

    @SuppressLint("WrongConstant")
    public void play(View view) {
        Intent goToMarket = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
        goToMarket.addFlags(1208483840);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }

    public void whatsapp(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://api.whatsapp.com/send?phone=+917205716253&text=Hii%20....I%20want%20to%20know%20about%20")));
    }
}


