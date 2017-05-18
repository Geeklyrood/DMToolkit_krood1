package edu.cvtc.android.dmtoolkit_krood1;

/**
 * Created by Keenan on 5/18/17.
 */

public class Character {

    public String characterName;

    public Character() {
        characterName = "";
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Character(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String toString() {
        return getCharacterName();
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof Character
                && ((Character) o).getCharacterName().equals(characterName);
    }

}
