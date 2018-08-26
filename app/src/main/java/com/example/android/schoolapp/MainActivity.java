package com.example.android.schoolapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Adapter.postClickListener {


    private static final int NUM_LIST_POSTS = 20; //hold number of posts to recyclerview
    private Adapter mAdapter;
    private RecyclerView mPostsList;
    private ImageButton imageButton;
    private DrawerLayout drawer;
    private TextView SupportBack;
    private LinearLayout linearLayout;
    Toast toast;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.action_notifications);
        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();

        final MenuItem alertMenuItem1 = menu.findItem(R.id.action_new_account);
        FrameLayout rootView1 = (FrameLayout) alertMenuItem1.getActionView();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );




        // float button to add new school post
        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "It Will add New School Post", Snackbar.LENGTH_SHORT )
                        .setAction( "School",null ).show();
            }
        } );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        imageButton = findViewById(R.id.ib_back);

        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);


            }
        } );

        mPostsList = findViewById( R.id.rv_posts );
        //positioning item posts within a recyclerview into a linear list
        LinearLayoutManager layoutManager = new LinearLayoutManager( this);
        mPostsList.setLayoutManager(layoutManager);
        mPostsList.setHasFixedSize(true);

        mAdapter = new Adapter(NUM_LIST_POSTS, this);
        mPostsList.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new_account) {
            return true;
        }else if (id == R.id.action_notifications) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    @Override
    public void onPostClick(int clickedPostIndex) {
        String toastMessage = "Post #" + String.valueOf(clickedPostIndex+1  ) + " clicked.";
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();

    }

    public void ClickNav(View view)
    {

        switch (view.getId()){

            case R.id.view1:
                showtoast("Profile 1");
                break;
            case R.id.view2:
                showtoast("Profile 2");
                break;
            case R.id.view3:
                showtoast("Profile 3");
                break;
            case R.id.tv_home:
                showtoast( "Home" );
                closeAndColor(view.getId());
                break;
            case R.id.tv_add_school:
                 showtoast( "Add School" );
                drawer.closeDrawer(GravityCompat.START);
                 break;
            case R.id.tv_contact:
                showtoast( "Contact" );
                closeAndColor(view.getId());
                break;
            case R.id.tv_event:
                showtoast( "Events" );
                closeAndColor(view.getId());
                break;
            case R.id.tv_fav:
                showtoast( "Favorites" );
                closeAndColor(view.getId());
                break;
            case R.id.tv_feed:
                showtoast( "Feedback" );
                closeAndColor(view.getId());
                break;
            case R.id.tv_how_to_use:
                showtoast( "How To Use" );
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tv_inbox:
                showtoast( "Inbox" );
                closeAndColor(view.getId());
                linearLayout = findViewById(R.id.ll_inbox);
                linearLayout.setBackgroundColor( Color.rgb( 204,204,204 ));
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        linearLayout.setBackgroundColor( Color.WHITE);

                    }
                }, 1000);
                break;
            case R.id.ll_inbox:
                showtoast( "Inbox" );
                linearLayout = findViewById( view.getId() );
                linearLayout.setBackgroundColor( Color.rgb( 204,204,204 ));
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        linearLayout.setBackgroundColor( Color.WHITE);

                    }
                }, 1000);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tv_my_school:
                showtoast( "My Schools" );
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tv_profile:
                showtoast( "My Profile" );
                closeAndColor(view.getId());
                break;
            case R.id.tv_settings:
                showtoast( "Settings" );
                closeAndColor(view.getId());
                break;
            case R.id.tv_logout:
                showtoast( "LogOut" );
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.image1:
                showtoast( "New Account" );
                break;
            case R.id.image2:
                showtoast( "Notifications" );
                break;



        }

    }
    public void showtoast(String s)
    {
        if (toast!=null)
        {
            toast.cancel();
        }
        toast.makeText( MainActivity.this,s+" Clicked!",Toast.LENGTH_SHORT ).show();
    }
    public void closeAndColor(int id)
    {
        SupportBack=findViewById( id );
        SupportBack.setBackgroundColor( Color.rgb( 204,204,204 ) );
        timer();
        drawer.closeDrawer(GravityCompat.START);



    }

    public void timer()
    {

        new Handler().postDelayed(new Runnable() {

            public void run() {
                SupportBack.setBackgroundColor(Color.WHITE);
                /*linearLayout.setBackgroundColor( Color.WHITE);*/

            }
        }, 1000);


    }


}
