package Serialization;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class QuizCardEditor {
    private ArrayList<QuizCard> cardList = new ArrayList<>();
    private JTextArea question;
    private JTextArea answer;
    private JFrame mainFrame;

    public static void main(String[] args) {
        new QuizCardEditor().doIt();
    }

    public void doIt() {
        mainFrame = new JFrame("Fiszki - Edytor");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        question = createTextArea(bigFont);
        JScrollPane scrollQ = createScroll(question);
        answer = createTextArea(bigFont);
        JScrollPane scrollA = createScroll(answer);

        mainPanel.add(new JLabel("Pytanie:"));
        mainPanel.add(scrollQ);
        mainPanel.add(new JLabel("Odpowiedź:"));
        mainPanel.add(scrollA);

        JButton nextButton = new JButton("Następna karta");
        nextButton.addActionListener(ev -> nextCard());
        mainPanel.add(nextButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Plik");

        JMenuItem optionNew = new JMenuItem("Nowa");
        optionNew.addActionListener(ev -> clearAll());

        JMenuItem optionSave = new JMenuItem("Zapisz");
        optionSave.addActionListener(ev -> saveCard());

        menuFile.add(optionNew);
        menuFile.add(optionSave);
        menuBar.add(menuFile);
        mainFrame.setJMenuBar(menuBar);

        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainFrame.setSize(500, 600);
        mainFrame.setVisible(true);
    }

    private JTextArea createTextArea(Font font) {
        JTextArea myArea = new JTextArea(6, 20);
        myArea.setLineWrap(true);
        myArea.setWrapStyleWord(true);
        myArea.setFont(font);
        return myArea;
    }

    private JScrollPane createScroll(JTextArea area) {
        JScrollPane myScroll = new JScrollPane(area);
        myScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        myScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return myScroll;
    }

    private void nextCard() {
        QuizCard myCard = new QuizCard(question.getText(), answer.getText());
        cardList.add(myCard);
        clearCard();
    }

    private void saveCard() {
        QuizCard myCard = new QuizCard(question.getText(), answer.getText());
        cardList.add(myCard);

        JFileChooser fileSaveWindow = new JFileChooser();
        fileSaveWindow.showSaveDialog(mainFrame);
        saveFile(fileSaveWindow.getSelectedFile());
    }

    private void clearAll() {
        cardList.clear();
        clearCard();
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file) {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(file));
            for (QuizCard card : cardList) {
                myWriter.write(card.getQuestion() + "/");
                myWriter.write(card.getAnswer() + "\n");
            }
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("Zapisanie pliku kart nie powiodło się: " + ex.getMessage());
        }
    }
}
