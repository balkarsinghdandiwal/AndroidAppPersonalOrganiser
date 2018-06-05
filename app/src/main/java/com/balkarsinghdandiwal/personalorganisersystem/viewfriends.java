package com.balkarsinghdandiwal.personalorganisersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewfriends extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private DatabaseManager mydManager;
    private TextView response;
    private ListView productRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfriends);
        mydManager = new DatabaseManager(viewfriends.this);
        response = (TextView)findViewById(R.id.response);
        productRec = (ListView)findViewById(R.id.prodrec);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        productRec.setVisibility(View.VISIBLE);
        mydManager.openReadable();
        ArrayList<String> tableContent = mydManager.retrieveRows();
        response.setText("Saved Friends:");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tableContent);
        productRec.setAdapter(adapter);
        productRec.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                long item = productRec.getAdapter().getItemId(position);
                String st = (String) productRec.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(), st + " selected", Toast.LENGTH_LONG).show();
                Intent i=new Intent(getBaseContext(),editfriends.class);
                i.putExtra("friendselected", item);
                startActivity(i);
            }
        });
            }



    public boolean insertRec() {

        Intent i=new Intent(getBaseContext(),navigation.class);
        startActivity(i);
        //productRec.setText("");
        //mydManager.close();
        return true;
    }
    public boolean removeRecs(){
        mydManager.clearRecords();
        Toast.makeText(viewfriends.this,"All Friends deleted",Toast.LENGTH_LONG).show();
        Intent i=new Intent(getBaseContext(),addfriends.class);
        startActivity(i);
        return true;
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

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
        if (id ==R.id.remove_rows){
            removeRecs();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {


            try {

                Intent i=new Intent(getBaseContext(),navigation.class);
                startActivity(i);
                //finish();

            } catch (Exception e) {

            }

            // Handle the camera action
        }else if (id == R.id.AddFriends) {


            try {

                Intent i=new Intent(getBaseContext(),addfriends.class);
                startActivity(i);
                //finish();

            } catch (Exception e) {

            }

            // Handle the camera action
        } else if (id == R.id.ViewFriends) {
            try {

                Intent i=new Intent(getBaseContext(),viewfriends.class);
                startActivity(i);
                //finish();

            } catch (Exception e) {

            }

        } else if (id == R.id.Addimage) {
            try {

                Intent i=new Intent(getBaseContext(),addimg.class);
                startActivity(i);
                //finish();

            } catch (Exception e) {

            }

        }  else if (id == R.id.AddEvents) {
            try {

                Intent i=new Intent(getBaseContext(),addevents.class);
                startActivity(i);
                //finish();

            } catch (Exception e) {

            }

            // Handle the camera action
        } else if (id == R.id.ViewEvents) {
            try {

                Intent i=new Intent(getBaseContext(),ViewEvents.class);
                startActivity(i);
                //finish();

            } catch (Exception e) {

            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
