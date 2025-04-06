package zabsu.chatbot.chatbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class TextAnswer implements IAnswer{

    Random rnd = new Random();

    public Pattern reg;
    public List<String> answers = new ArrayList<>();

    public TextAnswer(List<String> answers, Pattern reg){
        this.answers = answers;
        this.reg = reg;
    }

    @Override
    public boolean is_matched(String s) {
        return reg.matcher(s).matches();
    }

    @Override
    public String answer(String s) {
        return answers.get(rnd.nextInt(answers.size()));
    }
}
