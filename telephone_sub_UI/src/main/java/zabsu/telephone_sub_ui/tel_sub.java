package zabsu.telephone_sub_ui;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tel_sub {
    // поля класса:
    // номер телефона абонента
    private String phone_number;
    // номер лицевого счёта абонента
    private String account_number;
    // название тарифа абонента
    private String tariff;
    // имя абонента
    private String name;

    private final List<String> tariffs = Arrays.asList("Based", "Medium", "Advanced", "Pro", "Budget", "Super-Tariff", "Mega-Tariff");

    public double getBalance() {
        return balance;
    }

    // баланс абонента
    public double balance;
    tel_sub(){
        balance = 0;
        phone_number = "+8(924)000-00-00";
        account_number = "000001";
        tariff = "Based";
        name = "Name Surname";
    }
    tel_sub(final String pn, final String an, final String tariff1, final String name1, final double balance1) {
        balance = balance1;
        setName(name1);
        setTariff(tariff1);
        setAccount_number(an);
        setPhone_number(pn);
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String pn) {
        Pattern pattern = Pattern.compile("\\+([0-9]{1})(\\([0-9]{3}\\))([0-9\\-]{9})"); // маска номера телефона
        Matcher matcher = pattern.matcher(pn); // создаем matcher для проверки соответствия

        if (matcher.matches()) { // если введенный номер телефона совпадает с шаблоном
            phone_number = pn;
        } else {
            phone_number = "+8(924)000-00-00";
            System.err.println("Ошибка: неверно введён номер телефона. Придерживайтесь формата +.(...)...-..-..");
        }
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String an) {
        Pattern pattern = Pattern.compile("([0-9]{6})");
        Matcher matcher = pattern.matcher(an);
        if (matcher.matches()){
            account_number = an;
        }
        else {
            account_number = "000001";
            System.err.println("Ошибка: неверно введён лицевой счёт. Придерживайтесь  шестизначного числового формата ");
        }
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff1) {
        // если введённый тариф присутствует в списке тарифов
        if (tariffs.contains(tariff1)) {
            this.tariff = tariff1;
        } else {
            this.tariff = "Based";
            System.err.println("Ошибка: неверно введён тариф. Выберите тариф из списка тарифов");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name1) {
        // Используем регулярное выражение для проверки, состоит ли строка только из букв и пробелов
        Pattern pattern = Pattern.compile("[a-zA-Z\\s]+");
        Matcher matcher = pattern.matcher(name1);
        boolean isNameValid = matcher.matches();

        if (isNameValid) {
            this.name = name1;
        } else {
            this.name = "Name Surname";
            System.err.println("Ошибка: неверно введено ФИО");
        }
    }
    public void replenish_balance(final double balance){
        if (balance > 0) {
            this.balance += balance;
        } else {
            System.err.println("Ошибка: неверно введена сумма пополнения баланса");
        }
    }
}

