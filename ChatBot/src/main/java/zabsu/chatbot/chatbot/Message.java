package zabsu.chatbot.chatbot;

/**
 * Класс представляет сообщение в чате, содержащее отправителя sender, текст сообщения content и время отправки time.
 */
public class Message {
    private String sender;
    private String content;
    private String time;

    /**
     * Конструктор класса message
     * @param sender отправитель
     * @param content содержание
     * @param time время отправки
     */
    public Message(String sender, String content, String time) {
        this.sender = sender;
        this.content = content;
        this.time = time;
    }

    /**
     * Геттер класса message
     * @return имя отправителя
     */
    public String getSender() {
        return sender;
    }

    /**
     * Геттер класса message
     * @return текст сообщения
     */
    public String getContent() {
        return content;
    }

    /**
     * Геттер класса message
     * @return время отправки
     */
    public String getTime() {
        return time;
    }
}
