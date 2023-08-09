package MusicMachine;

import java.util.ArrayList;
import java.awt.*;
import javax.sound.midi.*;
import javax.swing.*;

import static javax.sound.midi.ShortMessage.*;

public class DrumGenerator {
    private ArrayList<JCheckBox> checkBoxList;
    private Sequencer mySequencer;
    private Sequence mySequence;
    private Track myTrack;

    String[] drumNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
                            "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", 
                            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] drumNumbers = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new DrumGenerator().createGUI();
    }

    public void createGUI() {
        JFrame mainFrame = new JFrame("Drum Generator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout myLayout = new BorderLayout();
        JPanel myPanelSide = new JPanel(myLayout);
        myPanelSide.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box buttonArea = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(ev -> createTrackPlay());
        buttonArea.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(ev -> mySequencer.stop());
        buttonArea.add(stop);

        JButton faster = new JButton("Faster");
        faster.addActionListener(ev -> changeSpeed(1.03f));
        buttonArea.add(faster);

        JButton slower = new JButton("Slower");
        slower.addActionListener(ev -> changeSpeed(0.97f));
        buttonArea.add(slower);

        Box nameArea = new Box(BoxLayout.Y_AXIS);
        for (String drumName : drumNames) {
            JLabel drumLabel = new JLabel(drumName);
            drumLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
            nameArea.add(drumLabel);
        }

        myPanelSide.add(BorderLayout.EAST, buttonArea);
        myPanelSide.add(BorderLayout.WEST, nameArea);

        mainFrame.getContentPane().add(myPanelSide);

        GridLayout checkBoxGrid = new GridLayout(16, 16);
        checkBoxGrid.setVgap(1);
        checkBoxGrid.setHgap(2);

        JPanel mainPanel = new JPanel(checkBoxGrid);
        myPanelSide.add(BorderLayout.CENTER, mainPanel);

        checkBoxList = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }

        configureMIDI();

        mainFrame.setBounds(50, 50, 300, 300);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void configureMIDI() {
        try {
            mySequencer = MidiSystem.getSequencer();
            mySequencer.open();
            mySequence = new Sequence(Sequence.PPQ, 4);
            myTrack = mySequence.createTrack();
            mySequencer.setTempoInBPM(120);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createTrackPlay() {
        int[] trackList;

        mySequence.deleteTrack(myTrack);
        myTrack = mySequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];

            int key = drumNumbers[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox jc = checkBoxList.get(j + 16 * i);
                if (jc.isSelected()) {
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }
            createTracks(trackList);
            myTrack.add(createEvent(CONTROL_CHANGE, 1, 127, 0, 16));
        }

        myTrack.add(createEvent(PROGRAM_CHANGE, 9, 1, 0, 15));

        try {
            mySequencer.setSequence(mySequence);
            mySequencer.setLoopCount(-1);
            mySequencer.setTempoInBPM(120);
            mySequencer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void changeSpeed(float speedMultiplier) {
        float speedFactor = mySequencer.getTempoFactor();
        mySequencer.setTempoFactor(speedFactor * speedMultiplier);
    }

    private void createTracks(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];

            if (key != 0) {
                myTrack.add(createEvent(NOTE_ON, 9, key, 100, i));
                myTrack.add(createEvent(NOTE_OFF, 9, key, 100, i + 1));
            }
        }
    }

    public static MidiEvent createEvent(int plc, int channel, int data1, int data2, int tick) {
        MidiEvent myEvent = null;
        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(plc, channel, data1, data2);
            myEvent = new MidiEvent(msg, tick);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return myEvent;
    }
}
