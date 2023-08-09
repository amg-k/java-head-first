package Serialization;

import java.io.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class QuizCardGame {
    private ArrayList<QuizCard> cardsTable;
    private int currentCardIndex;
    private QuizCard currentCard;
    private JTextArea presentationArea;
    private JFrame mainFrame;
    private JButton nextButton;
    private boolean isAnswerShown;

    public static void main(String[] args) {
        QuizCardGame cardReader = new QuizCardGame();
        cardReader.doIt();
    }

    public void doIt() {
        mainFrame = new JFrame("Fiszki");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        presentationArea = new JTextArea(10, 20);
        presentationArea.setFont(bigFont);
        presentationArea.setLineWrap(true);
        presentationArea.setEditable(false);

        JScrollPane presentationScroll = new JScrollPane(presentationArea);
        presentationScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        presentationScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(presentationScroll);

        nextButton = new JButton("Pokaż pytanie");
        nextButton.addActionListener(ev -> nextCard());
        mainPanel.add(nextButton);

        JMenuBar myMenuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Plik");
        JMenuItem optionOpen = new JMenuItem("Otwórz zestaw kart");
        optionOpen.addActionListener(ev -> openCardSet());
        menuFile.add(optionOpen);
        myMenuBar.add(menuFile);
        mainFrame.setJMenuBar(myMenuBar);

        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainFrame.setSize(500, 430);
        mainFrame.setVisible(true);
    }

    private void nextCard() {
        if (isAnswerShown) {
            presentationArea.setText(currentCard.getAnswer());
            nextButton.setText("Następna karta");
            isAnswerShown = false;
        } else {
            if (currentCardIndex < cardsTable.size()) {
                showNextCard();
            } else {
                presentationArea.setText("To była ostatnia karta");
                nextButton.setEnabled(false);
            }
        }
    }

    private void openCardSet() {
        JFileChooser dialogFileChooser = new JFileChooser();
        dialogFileChooser.showOpenDialog(mainFrame);
        openFile(dialogFileChooser.getSelectedFile());
    }

    private void openFile(File myFile) {
        cardsTable = new ArrayList<>();
        currentCardIndex = 0;
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(myFile));
            String row;
            while ((row = myReader.readLine()) != null) {
                createCard(row);
            }
            myReader.close();
        } catch (IOException ex) {
            System.out.println("Nie udało się utworzyć tablicy kart: " + ex.getMessage());
        }
        nextButton.setEnabled(true);
        showNextCard();
    }

    private void createCard(String textLine) {
        String[] result = textLine.split("/");
        QuizCard myCard = new QuizCard(result[0], result[1]);
        cardsTable.add(myCard);
        System.out.println("Utworzono kartę");
    }

    private void showNextCard() {
        currentCard = cardsTable.get(currentCardIndex);
        currentCardIndex++;
        presentationArea.setText(currentCard.getQuestion());
        nextButton.setText("Wyświetl odpowiedź");
        isAnswerShown = true;
    }
}
