package com.stefanaleksic.renaissance;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Stefan on 1/4/2015.
 * Configuration files allow for easy saving of custom frequency values.
 * Each instrument has a different frequency. Some
 * Config file is layout out as follows
 * [note][OPTIONAL s or f][space][frequency]
 * therefore, an example would be
 * A 400.12
 * B f 400.12
 */
public class ConfigFileHandler {
    private HashMap<FrequencyUtil.Note,Float> notes = null;
    
    public ConfigFileHandler(File file) throws FileNotFoundException,IOException {
        notes = new HashMap<FrequencyUtil.Note,Float>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String currentLine = "";

        while((currentLine = br.readLine()) != null){
            String[] splitString = currentLine.split(" ");
            final int NOTE_LETTER_INDEX = 0;
            final int OPTIONAL_SHARP_INDEX = 1;
            final int FREQUENCY_INDEX = splitString.length - 1;

            FrequencyUtil.NoteLetter noteLetter = FrequencyUtil.NoteLetter.valueOf(splitString[NOTE_LETTER_INDEX]);

            FrequencyUtil.Note note = null;
            FrequencyUtil.Accidental accidental = FrequencyUtil.Accidental.NONE;
            if(splitString.length == 3){
                accidental = FrequencyUtil.Accidental.getAccidentalFromOneLetter(splitString[OPTIONAL_SHARP_INDEX].charAt(0));
            }
            note = new FrequencyUtil.Note(noteLetter,accidental);

            notes.put(note,Float.valueOf(splitString[FREQUENCY_INDEX]));
        }
    }

    public float getFrequencyFromNote(FrequencyUtil.Note note){
        return notes.get(note);
    }



}
