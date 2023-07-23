package SoundBox;

import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class SoundBoxCmd {
    public static void main(String[] args) {
        SoundBoxCmd cmdBox = new SoundBoxCmd();
        if (args.length < 2) {
            System.out.println("Don't forget to type arguments defining instrument and note");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            cmdBox.play(instrument, note);
        }
    }

    public void play(int instrument, int note) {
        try {
            Sequencer sqcr = MidiSystem.getSequencer();
            sqcr.open();
            Sequence sqce = new Sequence(Sequence.PPQ, 4);
            Track track = sqce.createTrack();

            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(PROGRAM_CHANGE, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(msg1, 1);
            track.add(changeInstrument);

            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(NOTE_ON, 1, note, 100);
            MidiEvent noteStart = new MidiEvent(msg2, 1);
            track.add(noteStart);

            ShortMessage msg3 = new ShortMessage();
            msg3.setMessage(NOTE_OFF, 1, note, 100);
            MidiEvent noteEnd = new MidiEvent(msg3, 16);
            track.add(noteEnd);

            sqcr.setSequence(sqce);
            sqcr.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
