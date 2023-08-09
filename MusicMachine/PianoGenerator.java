package MusicMachine;

import java.util.ArrayList;
import java.awt.*;
import javax.sound.midi.*;
import javax.swing.*;

import static javax.sound.midi.ShortMessage.*;

public class PianoGenerator {
    private ArrayList<JCheckBox> checkBoxList;
    private Sequencer mySequencer;
    private Sequence mySequence;
    private Track myTrack;

    String[] notesNames = {"d''", "c''", "h'", "a'", "g'", "f'", "e'", "d'", "c'", 
                            "h", "a", "g", "f", "e", "d", "c"};
    int[] notesNumbers = {74, 72, 71, 69, 67, 65, 64, 62, 60, 59, 57, 55, 53, 52, 50, 48};

    public static void main(String[] args) {
        new PianoGenerator().createGUI();
    }

    public void createGUI() {
        JFrame mainFrame = new JFrame("Piano Generator");
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
        for (String note : notesNames) {
            JLabel noteLabel = new JLabel(note);
            noteLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
            nameArea.add(noteLabel);
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

            int key = notesNumbers[i];

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

        myTrack.add(createEvent(PROGRAM_CHANGE, 1, 1, 0, 15));

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
                myTrack.add(createEvent(NOTE_ON, 1, key, 100, i));
                myTrack.add(createEvent(NOTE_OFF, 1, key, 100, i + 1));
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
