package zabsu.chatbot.chatbot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.net.URL;

/**
 * Класс HTTPAnswer реализует интерфейс {@link IAnswer} и отвечает за получение курсов валют
 * с внешнего HTTP-сервера (в данном случае – с сайта Центрального банка России) и формирование ответа в виде строки.
 * <p>
 * Если входящее сообщение соответствует регулярному шаблону, связанному с курсом или валютами,
 * метод {@code answer(String)} отправляет HTTP GET-запрос, получает JSON-ответ, парсит его,
 * извлекает названия валют и их курсы, а затем возвращает их в виде форматированной строки.
 * </p>
 */
public class HTTPAnswer implements IAnswer{
    /**
     * Регулярное выражение для определения, соответствует ли входящее сообщение запросу о курсах валют.
     */
    public Pattern reg = Pattern.compile(".*курс.*|.*валют.*");

    /**
     * Определяет, удовлетворяет ли входящая строка шаблону для запроса курсов валют.
     *
     * @param s входящая строка сообщения
     * @return {@code true}, если сообщение соответствует шаблону, иначе {@code false}
     */
    @Override
    public boolean is_matched(String s) {
        return reg.matcher(s).matches();
    }

    /**
     * Обрабатывает входящее сообщение, делая HTTP-запрос для получения JSON с курсами валют,
     * парсит полученный ответ и возвращает строку с названиями валют и их значениями.
     * <p>
     * Если возникает ошибка при запросе или парсинге, выводится сообщение об ошибке, и возвращается пустая строка.
     * </p>
     *
     * @param s входящее сообщение (используется только для определения необходимости вызова данного метода)
     * @return строка, содержащая названия валют и их курсы, или пустая строка в случае ошибки
     */
    @Override
    public String answer(String s) {
        String result = "";
        try{
        URL url = new URL("https://www.cbr-xml-daily.ru/daily_json.js");
        // класс для отправки запросов к HTTP серверу
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // Тип запроса - GET
        con.setRequestMethod("GET");

        // Получение тела ответа - длительная процедура. Для чтения ответа используется класс BufferedReader,
        // который умеет читать потоковые данные
        // InputStreamReader конвертирует полученные данные в набор символов
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

        // склеивание набора символов в строку
        String output = br.lines().collect(Collectors.joining());

            // Создание ObjectMapper для парсинга JSON
            ObjectMapper mapper = new ObjectMapper();

            // Преобразуем строку JSON в дерево JSON-объектов
            JsonNode root = mapper.readTree(output);

            // Достаём подузел "Valute"
            JsonNode valuteNode = root.get("Valute");

            // Перебираем все валюты внутри "Valute"
            Iterator<Map.Entry<String, JsonNode>> fields = valuteNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                // Получаем объект, описывающий конкретную валюту
                JsonNode currency = entry.getValue();

                // Извлекаем название валюты
                String name = currency.get("Name").asText();
                // Извлекаем значение курса валюты
                double value = currency.get("Value").asDouble();
                 result += (name + ": " + value + "\n");
                 }

            // Закрываем соединение
            con.disconnect();
        }
        catch (IOException e) {
            System.out.println(e);
            return "";
        }
        return result;
        }

    }

