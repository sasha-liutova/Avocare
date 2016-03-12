package com.liutova.avocare.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.liutova.avocare.R;
import com.liutova.avocare.model.DbSubstance;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
//
//    ListView sideMenuLayout;
//    SideMenuAdapter sideMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(MainFragment.newInstance());

//        setContentView(R.layout.activity_main);
//        sideMenuLayout = (ListView)findViewById(R.id.side_menu);
//        ArrayList<String> sideMenuItemsNames = new ArrayList<String>();
//        sideMenuItemsNames.add("Home");
//        sideMenuItemsNames.add("History");
//        sideMenuItemsNames.add("Favourites");
//        sideMenuItemsNames.add("My alergens");
//        sideMenuItemsNames.add("Settings");
//        sideMenuItemsNames.add("Help");
//        sideMenuItemsNames.add("Share");
//        sideMenuItemsNames.add("About");
//        sideMenuAdapter = new SideMenuAdapter(sideMenuItemsNames);
//        sideMenuLayout.setAdapter(sideMenuAdapter);
//        Log.d("MainActivity", "onCreate: ");

    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.navigation_item_1:
//                Log.d("navigation_item_1", "navigation_item_1: ");
//                return true;
//            case R.id.navigation_item_2:
//                Log.d("navigation_item_2", "navigation_item_2: ");
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //    public void parseTest(View view){
//        Toast.makeText(MainActivity.this, "Calling", Toast.LENGTH_SHORT).show();
//        DbSubstance.getQuery().whereEqualTo("safetyLevelID","3ZnaDKgKTC").findInBackground(new FindCallback<DbSubstance>() {
//            @Override
//            public void done(List<DbSubstance> objects, ParseException e) {
//                if(e == null){
//                    Log.d("MainActivity", "done: " + objects.get(0).getObjectId());
//                } else{
//                    Log.d("MainActivity", "e: " + e.getMessage());
//                }
//            }
//        });
//    }

/*    @Override
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
    }*/
}
