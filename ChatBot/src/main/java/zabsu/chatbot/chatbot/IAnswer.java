package zabsu.chatbot.chatbot;

public interface IAnswer {
    boolean is_matched(String s);
    String answer(String s);
}
