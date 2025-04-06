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

public class HTTPAnswer implements IAnswer{
    public Pattern reg = Pattern.compile("http");
    @Override
    public boolean is_matched(String s) {
        return reg.matcher(s).matches();
    }

    @Override
    public String answer(String s) {
        String result = "";
        try{
        URL url = new URL("https://www.cbr-xml-daily.ru/daily_json.js");
// класс для отправки запросов к HTTP серверу
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
// Тип запроса - GET (см. также POST)
        con.setRequestMethod("GET");

// Метаинформация ответа (response)
        System.out.println("Connection Response Message : "+con.getResponseMessage());  // Текстовый статус
        System.out.println("Connection Response Code :    "+con.getResponseCode());     // Код. Если всё ОК, то должен быть 200

// Получение тела ответа - длительная процедура. Для чтения ответа используется класс BufferedReader,
// который умеет читать потоковые данные
// InputStreamReader конвертирует полученные данные в набор символов
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

// склеивание набора символов в строку
        String output = br.lines().collect(Collectors.joining());

            ObjectMapper mapper = new ObjectMapper();

            // Преобразуем строку JSON в дерево JSON-объектов
            JsonNode root = mapper.readTree(output);

            // Достаём подузел "Valute"
            JsonNode valuteNode = root.get("Valute");

            // Перебираем все валюты внутри "Valute"
            Iterator<Map.Entry<String, JsonNode>> fields = valuteNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                JsonNode currency = entry.getValue();

                String name = currency.get("Name").asText();
                double value = currency.get("Value").asDouble();
                 result += (name + ": " + value + "\n");
                System.out.println(name + ": " + value); }

        //System.out.println("Connection Response Body :    "+output);
        con.disconnect();
        }
        catch (IOException e) {
            System.out.println(e);
            return "";
        }
        return result;
        }

    }

