package edu.cvtc.android.dmtoolkit_krood1;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceRollerActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner dieSpinner;
    private Button rollButton;
    private EditText modifierText;
    private EditText amountText;
    private TextView resultView;


    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private static final String SAVED_TEXT_KEY = "resultsText";
    private static final String SAVED_AMOUNT_KEY = "amtText";
    private static final String SAVED_MODIFIER_KEY = "modText";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dice_roller);

        dieSpinner = (Spinner) findViewById(R.id.die_spinner);

        rollButton = (Button) findViewById(R.id.roll_button);

        modifierText = (EditText) findViewById(R.id.modifier_text);
        amountText = (EditText) findViewById(R.id.amount_text);

        resultView = (TextView) findViewById(R.id.result_view);

        rollButton.setOnClickListener(this);

        mDrawerList = (ListView)findViewById(R.id.navList);

        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }



    @Override
    public void onClick(View v) {

        final String amountToRoll = amountText.getText().toString();
        final String modifier = modifierText.getText().toString();
        final String die = dieSpinner.getSelectedItem().toString();

        if (!modifier.isEmpty() && !amountToRoll.isEmpty()) {
            final int intMod = Integer.parseInt(modifier);
            final int intAmount = Integer.parseInt(amountToRoll);

            if(die.endsWith("4")) {
                final int dice = 4;

                resultView.setText(rollDie(dice, intAmount, intMod));
            } else if(die.endsWith("6")) {
                final int dice = 6;

                resultView.setText(rollDie(dice, intAmount, intMod));
            } else if(die.endsWith("8")) {
                final int dice = 8;

                resultView.setText(rollDie(dice, intAmount, intMod));
            } else if(die.endsWith("10")) {
                final int dice = 10;

                resultView.setText(rollDie(dice, intAmount, intMod));
            } else if(die.endsWith("12")) {
                final int dice = 12;

                resultView.setText(rollDie(dice, intAmount, intMod));
            } else if(die.endsWith("20")) {
                final int dice = 20;

                resultView.setText(rollDie(dice, intAmount, intMod));
            } else if(die.endsWith("100")) {
                final int dice = 100;

                resultView.setText(rollDie(dice, intAmount, intMod));
            } else if(die.endsWith("10000")) {
                final int dice = 10000;

                resultView.setText(rollDie(dice, intAmount, intMod));
            }

        } else {
            Toast.makeText(getApplicationContext(), "You must complete all fields!",
                    Toast.LENGTH_SHORT).show();
        }


    }

    public String rollDie(int die, int intAmount, int intMod) {
        Random rand = new Random();

        int count = 0;
        int finalRoll = 0;
        String rolls = "";

        while(count < intAmount) {
            int roll = rand.nextInt(die) + 1;
            finalRoll = finalRoll + roll;

            if(count != intAmount - 1){
                rolls += roll + ", ";
            } else if (count < intAmount - 1){
                rolls += " and a " + roll + ".";
            } else {
                rolls += roll + ".";
            }

            count++;
        }

        int rollWithMod = finalRoll + intMod;

        return "You rolled " + intAmount + ", "+ die + " sided die with a modifier of " + intMod +
                ". Your total is " + rollWithMod + ". You rolled a " + rolls;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final String resultsText = savedInstanceState.getString(SAVED_TEXT_KEY, "");
        resultView.setText(resultsText);
        final String amtText = savedInstanceState.getString(SAVED_AMOUNT_KEY, "");
        amountText.setText(amtText);
        final String modText = savedInstanceState.getString(SAVED_MODIFIER_KEY, "");
        modifierText.setText(modText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(SAVED_TEXT_KEY, resultView.getText().toString());
        outState.putString(SAVED_AMOUNT_KEY, amountText.getText().toString());
        outState.putString(SAVED_MODIFIER_KEY, modifierText.getText().toString());
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }

    private void addDrawerItems() {
        String[] activityArray = { "Dice Roller", "Initiative Tracker" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position == 1) {
                   Intent intent = new Intent(DiceRollerActivity.this, InitiativeTracker.class);

                   DiceRollerActivity.this.startActivity(intent);
               } else if (position == 0) {

                   Toast.makeText(DiceRollerActivity.this, "You are here already!", Toast.LENGTH_SHORT).show();
               }

            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}