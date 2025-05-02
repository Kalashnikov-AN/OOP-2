package zabsu.telephone_sub_ui;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс для работы с файлами (сохранение записей базы данных в файл и загрузка записей из файла)
 */
public class files {

    /**
     * Сохраняет записи базы данных в файл.
     * <p>
     * Проходит по всем записям, формирует строку, где поля разделены символом «;»
     * и записывает каждую запись в отдельной строке.
     * </p>
     */
    public static void saveHistory(ObservableList<TelSub> subs, String history_file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(history_file))) {
            for (TelSub sub : subs) {
                writer.write(sub.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // выводит стек ошибки в стандартный системный поток для ошибок
        }
    }

    /**
     * Загружает базу данных из файла.
     * <p>
     * Если файл существует, каждая строка разбивается на пять частей(пять полей класса TelSub)
     * и каждая запись добавляется в subs.
     * </p>
     */
    public static void loadHistory(ObservableList<TelSub> subs, String history_file) {
        if (Files.exists(Paths.get(history_file))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(history_file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";", 5);
                    if (parts.length == 5) {
                        subs.add(new TelSub(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(); // выводит стек ошибки в стандартный системный поток для ошибок
            }
        }
    }
}
