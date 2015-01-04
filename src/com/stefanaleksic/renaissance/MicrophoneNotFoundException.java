package com.stefanaleksic.renaissance;

/**
 * Created by Stefan on 1/3/2015.
 * This class was created to make other code more readable.
 */
public class MicrophoneNotFoundException extends Exception{
    public MicrophoneNotFoundException(){
        super("No suitable microphone found! Please check that your microphone is plugged in and the drivers" +
                "are correctly installed!");
    }
}
