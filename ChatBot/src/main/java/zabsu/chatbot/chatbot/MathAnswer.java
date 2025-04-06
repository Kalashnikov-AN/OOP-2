package zabsu.chatbot.chatbot;

import java.util.Arrays;
import java.util.regex.Pattern;

public class MathAnswer implements IAnswer{

    public Pattern reg = Pattern.compile(".*\\d\\s*[-+*/]\\s*\\d.*");

    @Override
    public boolean is_matched(String s) {
        return reg.matcher(s).matches();
    }

    @Override
    public String answer(String s) {
        char operator = ' ';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operator = ch;
                break;
            }
        }
        String regex = "\\D";
        System.out.println("123\n");
//        String operands[] = s.split(regex);
        String[] parts = s.split("\\D+");
        String[] operands = Arrays.stream(parts)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);
        System.out.println(Arrays.toString(operands));
        float a = Float.parseFloat(operands[0]);
        float b = Float.parseFloat(operands[1]);
        float result = 0;
        // Выбираем операцию в зависимости от оператора
        switch (operator) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/':
                if (b == 0) {
                    return "Ошибка: деление на ноль";
                }
                result = a / b;
                break;
        }

        return String.valueOf(result);
    }

}
