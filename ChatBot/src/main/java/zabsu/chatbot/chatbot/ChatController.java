package zabsu.chatbot.chatbot;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zabsu.chatbot.chatbot.files.*;
//import zabsu.chatbot.chatbot.msgProcessing.messages;

/**
 * Контроллер главного экрана чат-бота.
 * <p>
 * Этот класс отвечает за:
 * <ul>
 *   <li>Обработку событий отправки сообщений,</li>
 *   <li>Отображение сообщений в ListView с кастомным оформлением,</li>
 *   <li>Загрузку и сохранение истории сообщений,</li>
 *   <li>Инициализацию бота для обработки входящих сообщений.</li>
 * </ul>
 * </p>
 */
public class ChatController {

    /** Путь к файлу истории сообщений */
    private static final String HISTORY_FILE = "chat_history.txt";

    /** Имя пользователя, передаваемое из экрана ввода */
    public String name;

    /** ListView для отображения сообщений */
    @FXML
    public ListView chatListView;

    /** Кнопка отправки сообщения */
    @FXML
    public Button sendButton;

    /** Текстовое поле для ввода сообщения */
    @FXML
    public TextField textfieldInput;

    /** Объект для обработки входящих сообщений (бот) */
    public msgProcessing bot; // Добавляем поле для бота

    /**
     * Обработчик нажатия кнопки отправки сообщения.
     * <p>
     * Если поле с текстом не пустое, метод:
     * <ol>
     *   <li>Получает текущее время и форматирует его,</li>
     *   <li>Добавляет сообщение пользователя в список,</li>
     *   <li>Вызывает метод {@code bot.answer(text)} для получения ответа ботом,</li>
     *   <li>Добавляет ответ бота в список и прокручивает ListView до последнего сообщения.</li>
     * </ol>
     * </p>
     *
     * @param event событие, вызванное нажатием на кнопку отправки
     */
    @FXML
    void onSendClick(ActionEvent event) {

        String text = textfieldInput.getText().trim();
        if (!text.isEmpty()) {
            // Получить текущую дату и время
            LocalDateTime now = LocalDateTime.now();

            // Определить формат времени
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Преобразовать дату и время
            String formattedNow = now.format(formatter);

            // Добавляем сообщение пользователя
            bot.messages.add(new Message(name, text, formattedNow));
            // Очищаем поле ввода текстового сообщения
            textfieldInput.clear();

            // Преобразовать дату и время
            formattedNow = now.format(formatter);

            // Добавляем новое сообщение в массив, хранящий сообщения
            bot.messages.add(new Message("Bot", bot.answer(text), formattedNow));

            // Прокручиваем ListView вниз к последнему сообщению
            chatListView.scrollTo(bot.messages.size() - 1);
        }
    }

    /**
     * Метод инициализации, вызываемый после загрузки FXML.
     * <p>
     * В данном методе:
     * <ul>
     *   <li>Инициализируется обработчик сообщений (бот),</li>
     *   <li>Создаётся ObservableList для сообщений,</li>
     *   <li>Настраивается отображение сообщений в ListView с кастомным cell factory,</li>
     *   <li>Загружается история сообщений из файла,</li>
     *   <li>Добавляется пример приветственного сообщения бота,</li>
     *   <li>Настраивается автосохранение истории при закрытии окна.</li>
     * </ul>
     * </p>
     */
    @FXML
    void initialize() {
        // Инициализируем бота, который будет обрабатывать входящие сообщения
        bot = new msgProcessing();
        // Инициализируем список сообщений
        bot.messages = FXCollections.observableArrayList();
        chatListView.setItems(bot.messages);
        // Загружаем историю сообщений
        files.loadChatHistory(bot.messages, "chat_history.txt");
        // Настраиваем кастомный cell factory для отображения сообщений
        chatListView.setCellFactory(listView -> new ListCell<Message>() {
            @Override
            public void updateItem(Message message, boolean empty) {
                super.updateItem(message, empty);
                if (empty || message == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Создаем текст для времени и устанавливаем жирное начертание
                    Text timeText = new Text(message.getTime() + " ");
                    timeText.setStyle("-fx-font-weight: bold;");

                    // Создаем текст для имени отправителя и устанавливаем жирное начертание
                    Text senderText = new Text(message.getSender() + ": ");
                    senderText.setStyle("-fx-font-weight: bold;");

                    // Создаем текст для основного сообщения без изменений
                    Text contentText = new Text(message.getContent());

                    // Объединяем все части в один TextFlow
                    TextFlow textFlow = new TextFlow(timeText, senderText, contentText);
                    textFlow.setLineSpacing(2.0);

                    // Привязываем предпочтительную ширину TextFlow к ширине ListView (с учетом отступов)
                    textFlow.prefWidthProperty().bind(chatListView.widthProperty().subtract(220));

                    // Оборачиваем TextFlow в HBox для возможности выравнивания по левому/правому краю
                    HBox messageBox = new HBox(textFlow);

                    // Если сообщение от пользователя, выравниваем вправо, иначе – влево
                    if (name.equalsIgnoreCase(message.getSender())) {
                        messageBox.setPadding(new Insets(0, 0, 0, 210)); // Отступы сверху, справа, снизу и слева соответственно

                    } else {
                        messageBox.setAlignment(Pos.CENTER_LEFT);
                    }
                    setGraphic(messageBox);
                    // Прокручиваем ListView вниз к последнему сообщению
                    chatListView.scrollTo(bot.messages.size() - 1);
                }
            }
        });

        // Получить текущую дату и время
        LocalDateTime now = LocalDateTime.now();

        // Определить формат времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Преобразовать дату и время
        String formattedNow = now.format(formatter);

        // Пример начального сообщения от бота
        bot.messages.add(new Message("Bot", "Привет, " + name + "! Я чат-бот. Как я могу помочь?", formattedNow));

        // Автосохранение при выходе
        Platform.runLater(() -> {
            chatListView.getScene().getWindow().setOnCloseRequest(event -> files.saveChatHistory(bot.messages, "chat_history.txt"));
        });
    }


}
