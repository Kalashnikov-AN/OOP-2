package zabsu.chatbot.chatbot;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс для работы с файлами (сохранение истории сообщений в файл и загрузка сообщений из файла)
 */
public class files {

    /** Путь к файлу истории сообщений */
    private static final String HISTORY_FILE = "chat_history.txt";

    /**
     * Сохраняет историю сообщений в файл.
     * <p>
     * Проходит по всем сообщениям, формирует строку, где поля разделены символом «;»
     * и записывает каждое сообщение в отдельной строке.
     * </p>
     */
    public static void saveChatHistory(ObservableList<Message> messages) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
            for (Message message : messages) {
                writer.write(message.getTime() + ";" + message.getSender() + ";" + message.getContent());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // выводит стек ошибки в стандартный системный поток для ошибок
        }
    }

    /**
     * Загружает историю сообщений из файла.
     * <p>
     * Если файл существует, каждая строка разбивается на три части (время, отправитель, текст)
     * и каждое сообщение добавляется в messages.
     * </p>
     */
    public static void loadChatHistory(ObservableList<Message> messages) {
        if (Files.exists(Paths.get(HISTORY_FILE))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";", 3);
                    if (parts.length == 3) {
                        messages.add(new Message(parts[1], parts[2], parts[0]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(); // выводит стек ошибки в стандартный системный поток для ошибок
            }
        }
    }
}
