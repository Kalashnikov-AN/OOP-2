package zabsu.chatbot.chatbot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatController {

    @FXML
    public ListView chatListView;

    @FXML
    public Button sendButton;

    @FXML
    public TextField textfieldInput;

    private ObservableList<Message> messages;

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
            messages.add(new Message("user", text, formattedNow));
            textfieldInput.clear();

            // Преобразовать дату и время
            formattedNow = now.format(formatter);

            messages.add(new Message("bot", "Вы сказали: " + text, formattedNow));

            // Прокручиваем ListView вниз к последнему сообщению
            chatListView.scrollTo(messages.size() - 1);
        }
    }

    @FXML
    void initialize() {
        // Инициализируем список сообщений
        messages = FXCollections.observableArrayList();
        chatListView.setItems(messages);

        // Настраиваем кастомный cell factory для отображения сообщений
        chatListView.setCellFactory(listView -> new ListCell<Message>() {
            @Override
            public void updateItem(Message message, boolean empty) {
                super.updateItem(message, empty);
                if (empty || message == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Создаем Label для текста сообщения
                    Label messageLabel = new Label(message.getContent());
                    // Оборачиваем в HBox для возможности выравнивания
                    HBox messageBox = new HBox(messageLabel);

                    // Если сообщение от пользователя, выравниваем вправо, иначе – влево
                    if ("user".equalsIgnoreCase(message.getSender())) {
                        messageBox.setAlignment(Pos.CENTER_RIGHT);
                        // Можно добавить стиль через CSS для сообщений пользователя
                        messageLabel.getStyleClass().add("user-message");
                    } else {
                        messageBox.setAlignment(Pos.CENTER_LEFT);
                        messageLabel.getStyleClass().add("bot-message");
                    }
                    setGraphic(messageBox);
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
        messages.add(new Message("bot", "Привет, " + InputController.name + "! Я чат-бот. Как я могу помочь?", formattedNow));

    }
}
