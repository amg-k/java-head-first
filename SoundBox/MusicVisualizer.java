package SoundBox;

import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class MusicVisualizer {
    public static void main(String[] args) {
        try {
            Sequencer mySequencer = MidiSystem.getSequencer();
            mySequencer.open();

            Sequence mySequence = new Sequence(Sequence.PPQ, 4);
            Track myTrack = mySequence.createTrack();

            for (int i = 50; i < 103; i += 4) {
                myTrack.add(createEvent(NOTE_ON, 1, i, 100, i));
                myTrack.add(createEvent(NOTE_OFF, 1, i, 100, i + 2));
            }
            
            mySequencer.setSequence(mySequence);
            mySequencer.setTempoInBPM(220);
            mySequencer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static MidiEvent createEvent(int command, int channel, int data1, int data2, int tick) {
        MidiEvent myEvent = null;
        try {
            ShortMessage myMsg = new ShortMessage();
            myMsg.setMessage(command, channel, data1, data2);
            myEvent = new MidiEvent(myMsg, tick);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return myEvent;
    }
}
