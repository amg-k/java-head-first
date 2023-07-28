package SoundBox;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

import static javax.sound.midi.ShortMessage.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MusicVisualizer {
    JFrame myFrame;
    GraphicPanel myPanel;

    public static void main(String[] args) {
        MusicVisualizer myVisualizer = new MusicVisualizer();
        myVisualizer.playSounds();
    }

    public void playSounds() {
        try {
            Sequencer mySequencer = MidiSystem.getSequencer();
            mySequencer.open();

            createPanelFrame();

            int[] eventFlagNum = {127};
            mySequencer.addControllerEventListener(myPanel, eventFlagNum);

            Sequence mySequence = new Sequence(Sequence.PPQ, 4);
            Track myTrack = mySequence.createTrack();

            for (int i = 1; i < 64; i += 4) {
                int note = getRandomInt(21, 109);
                myTrack.add(createEvent(NOTE_ON, 1, note, 100, i));
                myTrack.add(createEvent(CONTROL_CHANGE, 1, 127, 0, i));
                myTrack.add(createEvent(NOTE_OFF, 1, note, 100, i + 2));
            }
            
            mySequencer.setSequence(mySequence);
            mySequencer.setTempoInBPM(220);
            mySequencer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    int getRandomInt(int min, int max) {
            int myRandomInt = ThreadLocalRandom.current().nextInt(min, max);
            return myRandomInt;
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

    public void createPanelFrame() {
        myPanel = new GraphicPanel();
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().add(BorderLayout.CENTER, myPanel);
        myFrame.setSize(600, 500);
        myFrame.setVisible(true);
    }

    class GraphicPanel extends JPanel implements ControllerEventListener {
        private boolean myFlag = false;

        public void controlChange(ShortMessage myEvent) {
            myFlag = true;
            repaint();
        }

        public void paintComponent(Graphics g) {
            if (myFlag) {
                g.setColor(getRandomColor());

                int myHeight = getRandomInt(10, 120);
                int myWidth = getRandomInt(10, 120);

                int xValue = getRandomInt(10, 200);
                int yValue = getRandomInt(10, 200);

                g.fillRect(xValue, yValue, myWidth, myHeight);
                myFlag = false;
            }
        }

        private Color getRandomColor() {
            Random myRandom = new Random();
            int myRed = myRandom.nextInt(256);
            int myGreen = myRandom.nextInt(256);
            int myBlue = myRandom.nextInt(256);
    
            Color myRandomColor = new Color(myRed, myGreen, myBlue);
            return myRandomColor;  
        }
    }
}
