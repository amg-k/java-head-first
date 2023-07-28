package SoundBox;

import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class MusicVisualizer {
    public static void main(String[] args) {
        MusicVisualizer myVisualizer = new MusicVisualizer();
        myVisualizer.playSounds();
    }

    public void playSounds() {
        try {
            Sequencer mySequencer = MidiSystem.getSequencer();
            mySequencer.open();

            int[] eventFlagNum = {127};
            mySequencer.addControllerEventListener(myEvent -> System.out.println("note played"), 
                                                    eventFlagNum);

            Sequence mySequence = new Sequence(Sequence.PPQ, 4);
            Track myTrack = mySequence.createTrack();

            for (int i = 50; i < 103; i += 4) {
                myTrack.add(createEvent(NOTE_ON, 1, i, 100, i - 49));
                myTrack.add(createEvent(CONTROL_CHANGE, 1, 127, 0, i - 49));
                myTrack.add(createEvent(NOTE_OFF, 1, i, 100, i - 47));
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
