package edu.cvtc.android.dmtoolkit_krood1;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Keenan on 5/18/17.
 */

public class CharacterView extends LinearLayout {

    private TextView characterTextView;

    public CharacterView(final Context context, final Character character) {
        super(context);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.character_view, this, true);

        characterTextView = (TextView) findViewById(R.id.characterTextView);
        characterTextView.setText(character.getCharacterName());
    }
}
