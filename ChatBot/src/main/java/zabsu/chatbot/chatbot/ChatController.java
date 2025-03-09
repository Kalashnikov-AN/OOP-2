package zabsu.chatbot.chatbot;

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
            messages.add(new Message(InputController.name, text, formattedNow));
            textfieldInput.clear();

            // Преобразовать дату и время
            formattedNow = now.format(formatter);

            messages.add(new Message("Bot", "Вы сказали: " + text, formattedNow));

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
//                    // Создаем Label для текста сообщения
//                    Label messageLabel = new Label(message.getTime() + " " + message.getSender() + ": " + message.getContent());
//                    // Оборачиваем в HBox для возможности выравнивания
//                    HBox messageBox = new HBox(messageLabel);
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

                    // Ограничиваем ширину HBox до 70% от ширины ListView
                    //messageBox.maxWidthProperty().bind(chatListView.widthProperty().multiply(0.7));

                    // Если сообщение от пользователя, выравниваем вправо, иначе – влево
                    if (InputController.name.equalsIgnoreCase(message.getSender())) {
                       // textFlow.setTextAlignment(TextAlignment.RIGHT);
                        //messageBox.setAlignment(Pos.CENTER_RIGHT);
                        messageBox.setPadding(new Insets(0, 0, 0, 210)); // Отступы сверху, справа, снизу и слева соответственно
                        // Можно добавить стиль через CSS для сообщений пользователя
                        //messageLabel.getStyleClass().add("user-message");
                    } else {
                        messageBox.setAlignment(Pos.CENTER_LEFT);
                        //messageLabel.getStyleClass().add("bot-message");
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
        messages.add(new Message("Bot", "Привет, " + InputController.name + "! Я чат-бот. Как я могу помочь?", formattedNow));

    }
}
