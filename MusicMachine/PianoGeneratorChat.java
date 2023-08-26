package MusicMachine;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

import static javax.sound.midi.ShortMessage.*;

public class PianoGeneratorChat {
    private JList<String> recievedList;
    private JTextArea userMessage;
    private ArrayList<JCheckBox> checkBoxList;
    
    private Vector<String> vectorList = new Vector<>();
    private HashMap<String, boolean[]> recievedSongsMap = new HashMap<>();

    private String user;
    private int nextNum;
    
    private ObjectOutputStream exit;
    private ObjectInputStream enter;

    private Sequencer mySequencer;
    private Sequence mySequence;
    private Track myTrack;
    
    String[] notesNames = {"d''", "c''", "h'", "a'", "g'", "f'", "e'", "d'", "c'", 
                            "h", "a", "g", "f", "e", "d", "c"};
    int[] notesNumbers = {74, 72, 71, 69, 67, 65, 64, 62, 60, 59, 57, 55, 53, 52, 50, 48};

    public static void main(String[] args) {
        new PianoGeneratorChat().appInitialize("Adam");
    }

    public void appInitialize(String name) {
        user = name;

        try {
            Socket mySocket = new Socket("127.0.0.1", 4242);
            exit = new ObjectOutputStream(mySocket.getOutputStream());
            enter = new ObjectInputStream(mySocket.getInputStream());
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new RemoteDataReader());
        } catch (Exception ex) {
            System.out.println("Nie można nawiązać połączenia.");
        }
        configureMidi();
        createGUI();
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

        JButton send = new JButton("Send");
        send.addActionListener(ev -> sendMessageAndSong());
        buttonArea.add(send);
        
        userMessage = new JTextArea();
        userMessage.setLineWrap(true);
        userMessage.setWrapStyleWord(true);
        JScrollPane messageScroll = new JScrollPane(userMessage);
        buttonArea.add(messageScroll);

        recievedList = new JList<>();
        recievedList.addListSelectionListener(new ListSelectListener());
        recievedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane list = new JScrollPane(recievedList);
        buttonArea.add(list);
        recievedList.setListData(vectorList);
        
/*         JButton save = new JButton("Save");
        save.addActionListener(ev -> saveSong());
        buttonArea.add(save);

        JButton load = new JButton("Load");
        load.addActionListener(ev -> loadSong());
        buttonArea.add(load);

        JButton clear = new JButton("Clear all");
        clear.addActionListener(ev -> clearAll());
        buttonArea.add(clear); */

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

        mainFrame.setBounds(50, 50, 300, 300);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void configureMidi() {
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
        ArrayList<Integer> trackList;

        mySequence.deleteTrack(myTrack);
        myTrack = mySequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new ArrayList<>();

            int key = notesNumbers[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox jc = checkBoxList.get(j + 16 * i);
                if (jc.isSelected()) {
                    trackList.add(key);
                } else {
                    trackList.add(0);
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

    private void sendMessageAndSong() {
        boolean[] boxStates = new boolean[256];
        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = checkBoxList.get(i);
            if (checkBox.isSelected()) {
                boxStates[i] = true;
            }
        }
        try {
            exit.writeObject(user + nextNum++ + ": " + userMessage.getText());
            exit.writeObject(boxStates);
        } catch (IOException ex) {
            System.out.println("Nie udało się wysłać danych na serwer.");
            ex.printStackTrace();
        }
        userMessage.setText("");
    }

    public class ListSelectListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent lse) {
            if (!lse.getValueIsAdjusting()) {
                String selected = recievedList.getSelectedValue();
                if (selected != null) {
                    boolean[] selectedState = recievedSongsMap.get(selected);
                    sequenceModify(selectedState);
                    mySequencer.stop();
                    createTrackPlay();
                }
            }
        }
    }

    private void sequenceModify(boolean[] boxStates) {
        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = checkBoxList.get(i);
            checkBox.setSelected(boxStates[i]);
        }
    }

    private void createTracks(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            Integer key = list.get(i);

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

    public class RemoteDataReader implements Runnable {
        public void run() {
            try {
                Object obj;
                while ((obj = enter.readObject()) != null) {
                    System.out.println("pobrano obiekt z serwera");
                    System.out.println(obj.getClass());

                    String nameToDisplay = (String) obj;
                    boolean[] boxStates = (boolean[]) enter.readObject();
                    recievedSongsMap.put(nameToDisplay, boxStates);

                    vectorList.add(nameToDisplay);
                    recievedList.setListData(vectorList);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

/*     private void saveSong() {
        boolean[] boxStates = new boolean[256];
        JFileChooser dialogFileChooser = new JFileChooser();
        
        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = checkBoxList.get(i);
            if (checkBox.isSelected()) {
                boxStates[i] = true;
            }
        }

        dialogFileChooser.showSaveDialog(mainFrame);
        try (ObjectOutputStream os = 
                new ObjectOutputStream(new FileOutputStream(dialogFileChooser.getSelectedFile()))) {
            os.writeObject(boxStates);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadSong() {
        boolean[] boxStates = null;
        JFileChooser dialogFileChooser = new JFileChooser();

        dialogFileChooser.showOpenDialog(mainFrame);
        try (ObjectInputStream is = 
                new ObjectInputStream(new FileInputStream(dialogFileChooser.getSelectedFile()))) {
            boxStates = (boolean[]) is.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = checkBoxList.get(i);
            checkBox.setSelected(boxStates[i]);
        }
    }

    private void clearAll() {
        for (int i = 0; i < 256; i++) {
            checkBoxList.get(i).setSelected(false);
        }
    } */
}
