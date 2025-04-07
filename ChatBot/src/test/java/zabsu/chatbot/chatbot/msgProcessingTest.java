package zabsu.chatbot.chatbot;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class msgProcessingTest {


    ImsgProcessing processor = new msgProcessing();

    @Test
    void testGreeting1() {
        // Тестируем приветствие
        // Первый TextAnswer отвечает на шаблоны "hi|hello|привет|здравствуйте" и возвращает один из вариантов: "Привет" или "Hello"
        String response = processor.answer("Привет");
        // Проверяем, что ответ равен одному из вариантов (зависит от реализации TextAnswer)
        assertTrue(response.equals("Привет") || response.equals("Hello"),
                "Ответ на приветствие должен быть 'Привет' или 'Hello'");
    }

    @Test
    void testGreeting2() {
        // Тестируем приветствие
        // Первый TextAnswer отвечает на шаблоны "hi|hello|привет|здравствуйте" и возвращает один из вариантов: "Привет" или "Hello"
        String response = processor.answer("Hi");
        // Проверяем, что ответ равен одному из вариантов (зависит от реализации TextAnswer)
        assertTrue(response.equals("Привет") || response.equals("Hello"),
                "Ответ на приветствие должен быть 'Привет' или 'Hello'");
    }

    @Test
    void testGreeting3() {
        // Тестируем приветствие
        // Первый TextAnswer отвечает на шаблоны "hi|hello|привет|здравствуйте" и возвращает один из вариантов: "Привет" или "Hello"
        String response = processor.answer("hello");
        // Проверяем, что ответ равен одному из вариантов (зависит от реализации TextAnswer)
        assertTrue(response.equals("Привет") || response.equals("Hello"),
                "Ответ на приветствие должен быть 'Привет' или 'Hello'");
    }

    @Test
    void testGreeting4() {
        // Тестируем приветствие
        // Первый TextAnswer отвечает на шаблоны "hi|hello|привет|здравствуйте" и возвращает один из вариантов: "Привет" или "Hello"
        String response = processor.answer("Здравствуйте");
        // Проверяем, что ответ равен одному из вариантов (зависит от реализации TextAnswer)
        assertTrue(response.equals("Привет") || response.equals("Hello"),
                "Ответ на приветствие должен быть 'Привет' или 'Hello'");
    }

    @Test
    void testIdentity1() {
        // Тестируем вопрос о личности бота
        // Второй TextAnswer обрабатывает шаблоны, связанные с вопросом о том, кто ты/как зовут
        String response = processor.answer("Как тебя зовут?");
        // Ожидаемый ответ — "Я чат-бот помощник!" (согласно конструктору msgProcessing)
        assertEquals("Я чат-бот помощник!", response,
                "Ответ на вопрос о личности должен быть 'Я чат-бот помощник!'");
    }

    @Test
    void testIdentity2() {
        // Тестируем вопрос о личности бота
        // Второй TextAnswer обрабатывает шаблоны, связанные с вопросом о том, кто ты/как зовут
        String response = processor.answer("Кто ты такой?");
        // Ожидаемый ответ — "Я чат-бот помощник!" (согласно конструктору msgProcessing)
        assertEquals("Я чат-бот помощник!", response,
                "Ответ на вопрос о личности должен быть 'Я чат-бот помощник!'");
    }

    @Test
    void testIdentity3() {
        // Тестируем вопрос о личности бота
        // Второй TextAnswer обрабатывает шаблоны, связанные с вопросом о том, кто ты/как зовут
        String response = processor.answer("Расскажи пожалуйста, ты кто вообще?");
        // Ожидаемый ответ — "Я чат-бот помощник!" (согласно конструктору msgProcessing)
        assertEquals("Я чат-бот помощник!", response,
                "Ответ на вопрос о личности должен быть 'Я чат-бот помощник!'");
    }

    @Test
    void testFarewell1() {
        // Тестируем прощание
        // Третий TextAnswer возвращает один из вариантов: "До свидания!", "Пока!", "До новых встреч!"
        String response = processor.answer("Пока!");
        assertTrue(response.equals("До свидания!") || response.equals("Пока!") ||  response.equals("До новых встреч!"), "Ответ на прощание должен быть одним из ожидаемых вариантов.");
    }

    @Test
    void testFarewell2() {
        // Тестируем прощание
        // Третий TextAnswer возвращает один из вариантов: "До свидания!", "Пока!", "До новых встреч!"
        String response = processor.answer("До свидания......");
        assertTrue(response.equals("До свидания!") || response.equals("Пока!") ||  response.equals("До новых встреч!"), "Ответ на прощание должен быть одним из ожидаемых вариантов.");
    }

    @Test
    void testFarewell3() {
        // Тестируем прощание
        // Третий TextAnswer возвращает один из вариантов: "До свидания!", "Пока!", "До новых встреч!"
        String response = processor.answer("До свидания");
        assertTrue(response.equals("До свидания!") || response.equals("Пока!") ||  response.equals("До новых встреч!"), "Ответ на прощание должен быть одним из ожидаемых вариантов.");
    }

    @Test
    void testFarewell4() {
        // Тестируем прощание
        // Третий TextAnswer возвращает один из вариантов: "До свидания!", "Пока!", "До новых встреч!"
        String response = processor.answer("Пока");
        assertTrue(response.equals("До свидания!") || response.equals("Пока!") ||  response.equals("До новых встреч!"), "Ответ на прощание должен быть одним из ожидаемых вариантов.");
    }

    @Test
    void testTimeQuery1() {
        // Тестируем запрос времени
        // Четвёртый TextAnswer обрабатывает шаблоны, связанные с запросом времени
        String response = processor.answer("Который час?");
        // Так как ответ основан на текущем времени (new Date().toString()), его сложно предсказать,
        // поэтому проверим, что ответ не является сообщением по умолчанию
        assertEquals(new Date().toString(), response);
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response,
                "Ответ на запрос времени не должен быть сообщением об отсутствии ответа.");
        assertFalse(response.isEmpty(), "Ответ не должен быть пустой строкой.");
    }

    @Test
    void testTimeQuery2() {
        // Тестируем запрос времени
        // Четвёртый TextAnswer обрабатывает шаблоны, связанные с запросом времени
        String response = processor.answer("Сколько время?");
        // Так как ответ основан на текущем времени (new Date().toString()), его сложно предсказать,
        // поэтому проверим, что ответ не является сообщением по умолчанию
        assertEquals(new Date().toString(), response);
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response,
                "Ответ на запрос времени не должен быть сообщением об отсутствии ответа.");
        assertFalse(response.isEmpty(), "Ответ не должен быть пустой строкой.");
    }

    @Test
    void testTimeQuery3() {
        // Тестируем запрос времени
        // Четвёртый TextAnswer обрабатывает шаблоны, связанные с запросом времени
        String response = processor.answer("Сколько времени?");
        // Так как ответ основан на текущем времени (new Date().toString()), его сложно предсказать,
        // поэтому проверим, что ответ не является сообщением по умолчанию
        assertEquals(new Date().toString(), response);
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response,
                "Ответ на запрос времени не должен быть сообщением об отсутствии ответа.");
        assertFalse(response.isEmpty(), "Ответ не должен быть пустой строкой.");
    }

    @Test
    void testMathExpression1() {
        // Тестируем обработку математического выражения
        // Предполагается, что MathAnswer сможет обработать простой пример вроде "2+2"
        String response = processor.answer("2+2");
        // Если MathAnswer сработает, ответ должен быть числом (например, "4" или "4.0")
        // Проверяем, что ответ не равен сообщению по умолчанию
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response);
        // И пытаемся распарсить ответ в число:
        assertEquals(4, Double.parseDouble(response));
    }

    @Test
    void testMathExpression2() {
        // Тестируем обработку математического выражения
        String response = processor.answer("5*2");
        // Если MathAnswer сработает, ответ должен быть числом (например, "10" или "10.0")
        // Проверяем, что ответ не равен сообщению по умолчанию
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response);
        // И пытаемся распарсить ответ в число:
        assertEquals(10, Double.parseDouble(response));
    }

    @Test
    void testMathExpression3() {
        // Тестируем обработку математического выражения
        String response = processor.answer("5-2");
        // Если MathAnswer сработает, ответ должен быть числом (например, "3" или "3.0")
        // Проверяем, что ответ не равен сообщению по умолчанию
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response);
        // И пытаемся распарсить ответ в число:
        assertEquals(3, Double.parseDouble(response));
    }

    @Test
    void testMathExpression4() {
        // Тестируем обработку математического выражения
        String response = processor.answer("5/2");
        // Проверяем, что ответ не равен сообщению по умолчанию
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response);
        // И пытаемся распарсить ответ в число:
        assertEquals(2.5, Double.parseDouble(response));
    }

    @Test
    void testMathExpression5() {
        // Тестируем обработку математического выражения
        String response = processor.answer("5/0");
        // Проверяем, что ответ не равен сообщению по умолчанию
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response);
        assertEquals("Ошибка: деление на ноль", response);
    }


    @Test
    void testHTTPCommand() {
        // Тестируем обработку команды, связанной с HTTPAnswer
        String response = processor.answer("Курс валют");
        // Если команда найдена, ответ не должен быть сообщением по умолчанию
        assertNotEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response);
        assertFalse(response.isEmpty(), "Ответ для HTTP команды не должен быть пустым.");
    }

    @Test
    void testUnknownInput() {
        // Тестируем ситуацию, когда входная строка не соответствует ни одному шаблону
        String response = processor.answer("Это неизвестная команда");
        assertEquals("К сожалению, пока что бот не умеет отвечать на такие вопросы", response,
                "При неизвестной команде должен возвращаться стандартный ответ.");
    }

}