package zabsu.chatbot.chatbot;

import javafx.collections.ObservableList;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Класс msgProcessing реализует интерфейс {@link ImsgProcessing} и отвечает за обработку входящих сообщений.
 * <p>
 * Он использует набор объектов-ответчиков ({@link IAnswer}), каждый из которых проверяет входящую строку
 * на соответствие определённому регулярному выражению и возвращает ответ, если строка удовлетворяет условию.
 * </p>
 * <p>
 * В конструкторе класса создаются и добавляются в список {@code ans} различные обработчики:
 * <ul>
 *   <li>{@link TextAnswer} для приветствий, вопросов о личности и прощаний,</li>
 *   <li>{@link TextAnswer} для запроса времени, где в ответ передается текущее время,</li>
 *   <li>{@link MathAnswer} для обработки математических выражений,</li>
 *   <li>{@link HTTPAnswer} для обработки команд, связанных с HTTP-запросами.</li>
 * </ul>
 * </p>
 */
public class msgProcessing implements  ImsgProcessing {

    /**
     * Статический список сообщений, который может использоваться для хранения истории сообщений.
     * <p>
     * ListView из ChatController использует messages как источник данных.
     * </p>
     * Все изменения в messages (например, добавление новых сообщений) автоматически
     * отображаются в интерфейсе без необходимости вручную перерисовывать список.
     */
    public static ObservableList<Message> messages;

    /**
     * Список обработчиков ответов, реализующих интерфейс {@link IAnswer}.
     */
    List<IAnswer> ans = new ArrayList<>();

    /**
     * Конструктор по умолчанию, инициализирующий набор обработчиков для ответов бота.
     * <p>
     * Здесь добавляются:
     * <ul>
     *   <li>TextAnswer для приветствий,</li>
     *   <li>TextAnswer для вопросов о том, кто бот, как его зовут,</li>
     *   <li>TextAnswer для прощаний,</li>
     *   <li>TextAnswer для запроса времени,</li>
     *   <li>MathAnswer для обработки математических выражений,</li>
     *   <li>HTTPAnswer для обработки HTTP-команд.</li>
     * </ul>
     * </p>
     */
    public msgProcessing(){
        // Добавление обработчика приветствий
        ans.add(new TextAnswer(List.of("Привет", "Hello"), Pattern.compile("hi|hello|привет|здравствуйте")));
        // Добавление обработчика вопросов о личности бота
        ans.add(new TextAnswer(List.of("Я чат-бот помощник!"), Pattern.compile(".*кто\\s.*ты.*|.*ты\\s.*кто.*|.*как\\s.*зовут.*|.*как\\s.*имя.*")));
        // Добавление обработчика прощаний
        ans.add(new TextAnswer(List.of("До свидания!", "Пока!", "До новых встреч!"), Pattern.compile(".*до свидания|.*пока")));
        // Добавление обработчика запроса времени, ответом будет текущее время
        ans.add(new TextAnswer(List.of(new Date().toString()), Pattern.compile(".*который\\s.*час.*|.*сколько\\s.*врем.*")));
        // Добавление обработчика математических выражений
        ans.add(new MathAnswer());
        // Добавление обработчика HTTP-команд
        ans.add(new HTTPAnswer());
    }

    /**
     * Обрабатывает входящее сообщение и возвращает соответствующий ответ.
     * <p>
     * Метод приводит входящее сообщение к нижнему регистру, разбивает строку по любым символам, не являющимся буквами или цифрами,
     * объединяя полученные подстроки пробелами, затем перебирает все обработчики ответов.
     * Если текущий обработчик {@code cur_ans} считает, что сообщение удовлетворяет его шаблону, возвращается ответ,
     * сформированный этим обработчиком.
     * </p>
     *
     * @param s входящее сообщение от пользователя
     * @return ответ, если обработчик нашёл совпадение, либо строка "К сожалению, пока что бот не умеет отвечать на такие вопросы"
     */
    public String answer(String s){
        // Приведение строки к нижнему регистру и разбивка по разделителям (например, пробелам, запятым, точкам, знакам вопроса и восклицания)
        String temp = String.join(" ", s.toLowerCase().split("[ {,|.}?!]+"));
        // Перебираем обработчики ответов (IAnswer)
        for (int i = 0; i < ans.size(); i++){
            IAnswer cur_ans = ans.get(i);
            // Если обработчик определяет, что сообщение соответствует его шаблону...
            if (cur_ans.is_matched(temp))
                // ...возвращаем ответ, сформированный обработчиком
                    return cur_ans.answer(temp);
        }
        // Если ни один обработчик не сработал, возвращаем стандартное сообщение об отсутствии ответа
        return "К сожалению, пока что бот не умеет отвечать на такие вопросы";
    }

    }

