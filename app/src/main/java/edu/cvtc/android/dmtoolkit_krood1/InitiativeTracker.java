package edu.cvtc.android.dmtoolkit_krood1;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class InitiativeTracker extends AppCompatActivity  {

    private Button addButton;
    private EditText characterName;

    private static final String SAVED_NAME_KEY = "nameText";
    private static final String SAVED_ARRAY_KEY = "arrayText";

    private ArrayList<Character> characterList = new ArrayList<>();

    private ListView characterListView;

    private CharacterAdapter characterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiative);
        characterAdapter = new CharacterAdapter(this, characterList);

        initLayout();

        final String[] characters = getResources().getStringArray(R.array.characterList);
        for (final String characterText : characters) {
            addCharacter(new Character(characterText));
        }

        initListeners();
    }

    private void initLayout() {

        setContentView(R.layout.activity_initiative);

        addButton = (Button) findViewById(R.id.addCharacterButton);
        characterName = (EditText) findViewById(R.id.characterEditText);

        characterListView = (ListView) findViewById(R.id.characterListViewGroup);
        characterListView.setAdapter(characterAdapter);
    }


    // Method used to provide Event Listeners to our Views.
    private void initListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCharacterFromEditText();
            }
        });

        characterName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_NUMPAD_ENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            addCharacterFromEditText();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void addCharacterFromEditText() {
        final String characterText = characterName.getText().toString().trim();

        if (characterText != null && !characterText.isEmpty()) {
            addCharacter(new Character(characterText));
            characterName.setText("");
        } else {
            Toast.makeText(this, R.string.empty_character_message, Toast.LENGTH_SHORT);
        }

        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        final View view = getCurrentFocus();
        if (view != null) {
            final InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    protected void addCharacter(final Character character) {
        characterList.add(character);
        characterAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final String nameText = savedInstanceState.getString(SAVED_NAME_KEY, "");
        characterName.setText(nameText);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_NAME_KEY, characterName.getText().toString());

    }
}