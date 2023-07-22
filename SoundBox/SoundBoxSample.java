package SoundBox;

import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class SoundBoxSample {
    public static void main(String[] args) {
        SoundBoxSample box = new SoundBoxSample();
        box.play();
    }

    public void play(){
        try {
            Sequencer sqcr = MidiSystem.getSequencer();
            sqcr.open();

            Sequence sqce = new Sequence(Sequence.PPQ, 4);

            Track track = sqce.createTrack();

            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(NOTE_ON, 1, 44, 100);
            MidiEvent noteStart = new MidiEvent(msg1, 1);
            track.add(noteStart);

            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(NOTE_OFF, 1, 44, 100);
            MidiEvent noteEnd = new MidiEvent(msg2, 16);
            track.add(noteEnd);

            ShortMessage msg3 = new ShortMessage();
            msg3.setMessage(NOTE_ON, 1, 32, 100);
            MidiEvent noteStart3 = new MidiEvent(msg3, 4);
            track.add(noteStart3);

            ShortMessage msg4 = new ShortMessage();
            msg4.setMessage(NOTE_OFF, 1, 32, 100);
            MidiEvent noteEnd3 = new MidiEvent(msg4, 18);
            track.add(noteEnd3);

            sqcr.setSequence(sqce);

            sqcr.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}