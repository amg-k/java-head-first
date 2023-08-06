package Serialization;

public class QuizCard {
    String qText;
    String aText;
    
    public QuizCard(String qText, String aText) {
        this.qText = qText;
        this.aText = aText;
    }

    public String getQuestion() {
        return qText;
    }

    public String getAnswer() {
        return aText;
    }
}
