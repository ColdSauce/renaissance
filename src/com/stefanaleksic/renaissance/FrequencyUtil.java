package com.stefanaleksic.renaissance;

/**
 * Created by Stefan on 1/3/2015.
 */
public class FrequencyUtil {

    //This is the hertz a piano emits while playing the note a4.
    private static final int a4Hz = 400;

    //This is the key that a4 corresponds to in a 88 key piano.
    private static final int a4Key = 49;

    /**
     * This method implements the frequency from n-th key found on Wikipedia.
     * To check it out for yourself, see this handy Wikipedia article.
     * https://en.wikipedia.org/wiki/Piano_key_frequencies
     *
     * @param n The key from 1 to 88 (piano)
     * @return The frequency from that key.
     */
    private double getFrequencyOfNthKey(int n) {
        double firstTerm = Math.pow(2, 1 / 12);
        firstTerm = Math.pow(firstTerm, n - 49);
        double secondTerm = a4Hz;
        return firstTerm * secondTerm;
    }

    /**
     * This method implements the frequency to n-th key found on Wikipedia.
     * To check it out for yourself, see this handy Wikipedia article.
     * https://en.wikipedia.org/wiki/Piano_key_frequencies
     *
     * @param frequency The frequency to convert to a key.
     * @return The key from 1 to 88 (piano)
     */
    private int getKeyFromFrequency(double frequency) {
        final int BASE_TWO = 2;
        double firstTerm = 12;
        double secondTerm = MathUtil.logOfBase(frequency / a4Hz, BASE_TWO);
        return (int) ((firstTerm * secondTerm) + a4Key);
    }


    /**
     * This class corresponds to each note.
     */
    public static class Note {
        private Accidental accidental;
        private NoteLetter noteLetter;

        public Note(NoteLetter noteLetter,Accidental accidental){
            this.noteLetter = noteLetter;
            this.accidental = accidental;
        }

        public Accidental getAccidental() {
            return accidental;
        }

        public NoteLetter getNoteLetter() {
            return noteLetter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Note)) return false;

            Note note = (Note) o;

            if (accidental != note.accidental) return false;
            if (noteLetter != note.noteLetter) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = accidental != null ? accidental.hashCode() : 0;
            result = 31 * result + (noteLetter != null ? noteLetter.hashCode() : 0);
            return result;
        }
    }
    public enum NoteLetter {
        A,B,C,D,E,F,G,;
    }
    public enum Accidental {
          SHARP,FLAT,NONE;

        public static Accidental getAccidentalFromOneLetter(char letter){
            return 'f' == letter ? FLAT : SHARP;
        }
    }
}
