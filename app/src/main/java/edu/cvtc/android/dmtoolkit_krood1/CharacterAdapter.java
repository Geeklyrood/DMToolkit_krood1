package edu.cvtc.android.dmtoolkit_krood1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Keenan on 5/18/17.
 */

public class CharacterAdapter extends BaseAdapter {

    /*
    * The application context in which the adapter is used.
    * */
    private Context context;

    /*
    * This is the data set to which the adapter is bound.
    * */
    private List<Character> characterList;

    /*
    * Parameterized constructor that takes in the application context in which
    * it is being used and the list of Joke objects to which it is bound.
    * @param context
    *
    * */
    public CharacterAdapter(final Context context, List<Character> characterList) {
        this.context = context;
        this.characterList = characterList;
    }

    @Override
    public int getCount() {
        return characterList != null ? characterList.size() : 0;
    }


    @Override
    public Object getItem(int position) {
        return characterList != null ? characterList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Character character = characterList.get(position);

        convertView = new CharacterView(context, character);

        return convertView;
    }
}
