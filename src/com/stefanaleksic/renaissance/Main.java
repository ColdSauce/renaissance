package com.stefanaleksic.renaissance;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import javafx.scene.input.MouseButton;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Stefan on 1/3/2015.
 * This code is taken from the TarsosDSP example code.
 * To check this awesome library out yourself, look at https://github.com/JorenSix/TarsosDSP
 */
public class Main implements PitchDetectionHandler {

    private AudioDispatcher dispatcher;
    private Mixer currentMixer;
    private PitchProcessor.PitchEstimationAlgorithm algo;

    public static void main(String[] args) {
        boolean SHOULD_LOOP = true;

        Main main = new Main();
        main.algo = PitchProcessor.PitchEstimationAlgorithm.AMDF;
        //This tests if the loop finished by finding a microphone or not.
        int index = -1;
        try {
            index = main.getMicrophone(0);
        } catch (MicrophoneNotFoundException mnfe) {
            mnfe.printStackTrace();
        }

        Mixer currentMicrophoneMixer = main.getNewMixer(index);


        while (SHOULD_LOOP) {

            try {

                main.setNewMixer(currentMicrophoneMixer);

                //Because there was no exception caught, the code should not loop again.
                SHOULD_LOOP = false;

            } catch (LineUnavailableException e) {

                //The microphone is being used right now so try another one.
                currentMicrophoneMixer = main.getNewMixer(index);

                //Now that there is a new microphone, try again and see if this one works.
                SHOULD_LOOP = true;
            } catch (UnsupportedAudioFileException e) {

                //The mixer didn't have valid data, therefore, should try a different mixer.
                currentMicrophoneMixer = main.getNewMixer(index);

                //Now that there is a new microphone, try again and see if this one works.
                SHOULD_LOOP = true;
            }
        }
    }


    /**
     * @param index The index at which this mixer is found.
     * @return The Mixer at the index.
     */
    private Mixer getNewMixer(int index) {
        Mixer mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[index]);
        return mixer;
    }


    /**
     * @param offset Starting point of search.
     * @return Index of first microphone mixer found with respect to offset.
     */
    private int getMicrophone(int offset) throws MicrophoneNotFoundException {
        //Mixer's Info object provides information on what each mixer is.
        //All the elements in the array are in respect to different mixers at the same index.
        Mixer.Info[] allInfos = AudioSystem.getMixerInfo();

        for (int i = offset; i < allInfos.length; i++) {
            Mixer.Info currentInfo = allInfos[i];
            if (currentInfo.getName().contains("Microphone")) {
                return i;
            }
        }

        //If code reaches the end of the for loop, that means that no microphone was found.
        throw new MicrophoneNotFoundException();
    }

    /**
     * This was one of the methods taken from the example code from the library in use.
     *
     * @param mixer The mixer a one wants to use.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     */
    private void setNewMixer(Mixer mixer) throws LineUnavailableException,
            UnsupportedAudioFileException {

        if (dispatcher != null) {
            dispatcher.stop();
        }
        currentMixer = mixer;

        //A common sample rate is 44100 and a common bufferSize is 1024.
        //That's why they are used here
        float sampleRate = 44100;
        int bufferSize = 1024;
        int overlap = 0;
        int sampleInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;

        AudioFormat format = new AudioFormat(sampleRate, sampleInBits, channels, signed,
                bigEndian);

        DataLine.Info dataLineInfo = new DataLine.Info(
                TargetDataLine.class, format);

        TargetDataLine line = (TargetDataLine) mixer.getLine(dataLineInfo);

        int numberOfSamples = bufferSize;
        line.open(format, numberOfSamples);
        line.start();

        AudioInputStream stream = new AudioInputStream(line);

        //TarsosDSP wraps the AudioInputStream to make it super simple to detect pitch.
        JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);


        dispatcher = new AudioDispatcher(audioStream, bufferSize,
                overlap);

        dispatcher.addAudioProcessor(new PitchProcessor(algo, sampleRate, bufferSize, this));

        new Thread(dispatcher, "Audio dispatching").start();
    }


    private boolean isBetween(double value, double one, double two) {
        return one < value && value < two;
    }

    //TODO: Get this to write directly to the keyboard buffer so that it works with other native programs that aren't text boxes.

    /**
     * This method gets called when the library TarsosDSP detected a pitch.
     *
     * @param pitchDetectionResult The object where you can get the details of a detected pitch.
     * @param audioEvent           Object that depicts the audio at the instance a pitch is detected.
     */
    @Override
    public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {


        float pitch = 0;

        boolean isPitchProbable = pitchDetectionResult.getProbability() > 0.5;

        if ((pitch = pitchDetectionResult.getPitch()) != -1 && isPitchProbable) {
            //TODO: Get the robot to do things based on musical notes.
            try {
                Robot robot = new Robot();
                int mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                int mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();

                //An example would be
//                final int SPEED = 10;
//                if(isBetween(pitch,400,450)){
//                    robot.mouseMove(mouseX + SPEED, mouseY);
//                }
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
}
