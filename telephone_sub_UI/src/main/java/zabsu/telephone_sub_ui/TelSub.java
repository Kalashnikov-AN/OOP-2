// Автор: Калашников А.Н.

package zabsu.telephone_sub_ui;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// Класс телефонного абонента
public class TelSub {
    // поля класса:
    // номер телефона абонента
    private String phone_number;
    // номер лицевого счёта абонента
    private String account_number;
    // название тарифа абонента
    private String tariff;
    // имя абонента
    private String name;
    // Список тарифов
    private final static List<String> tariffs = Arrays.asList("Based", "Medium", "Advanced", "Pro", "Budget", "Super-Tariff", "Mega-Tariff");
    /// Возвращает баланс абонента balance
    public double getBalance() {
        return balance;
    }

    // баланс абонента
    public double balance;

    /// Конструктор по умолчанию
    TelSub(){
        balance = 0;
        phone_number = "+8(924)000-00-00";
        account_number = "000001";
        tariff = "Based";
        name = "Name Surname";
    }
    /// Конструктор с параметрами: pn - номер телефона, an - номер лицевого счёта, tariff1 - тариф, name1 - имя, balance1 - баланс
    TelSub(final String pn, final String an, final String tariff1, final String name1, final double balance1) {
            balance = balance1;
            setName(name1);
            setTariff(tariff1);
            setAccount_number(an);
            setPhone_number(pn);
    }

    /// Возвращает номер телефона абонента phone_number
    public String getPhone_number() {
        return phone_number;
    }

    /// Изменяет поле номера телефона на номер телефона pn
    public void setPhone_number(String pn) throws RuntimeException {
        Pattern pattern = Pattern.compile("\\+([0-9]{1})(\\([0-9]{3}\\))([0-9\\-]{9})"); // маска номера телефона
        Matcher matcher = pattern.matcher(pn); // создаем matcher для проверки соответствия

        if (matcher.matches()) { // если введенный номер телефона совпадает с шаблоном
            phone_number = pn;
        } else { // если не совпадает
            phone_number = "+8(924)000-00-00";
            throw new RuntimeException("Придерживайтесь формата +.(...)...-..-..");
        }
    }

    /// Возвращает номер лицевого счёта абонента
    public String getAccount_number() {
        return account_number;
    }

    /// Изменяет поле лицевого счёта абонента на лицевой счёт an
    public void setAccount_number(String an) throws RuntimeException {
        Pattern pattern = Pattern.compile("([0-9]{6})");
        Matcher matcher = pattern.matcher(an);
        if (matcher.matches()){
            account_number = an;
        }
        else {
            account_number = "000001";
            throw new RuntimeException("Придерживайтесь  шестизначного числового формата");
        }
    }

    /// Возвращает тариф абонента tariff1
    public String getTariff() {
        return tariff;
    }

    /// Изменяет поле тарифа на тариф tariff1
    public void setTariff(String tariff1) throws RuntimeException {
        // если введённый тариф присутствует в списке тарифов
        if (tariffs.contains(tariff1)) {
            this.tariff = tariff1;
        } else {
            this.tariff = "Based";
            throw new RuntimeException("Выберите тариф из списка тарифов");
        }
    }

    /// Возвращает имя абонента name
    public String getName() {
        return name;
    }

    /// Изменяет поле имени абонента на имя name1
    public void setName(String name1) throws RuntimeException {
        // Используем регулярное выражение для проверки, состоит ли строка только из букв и пробелов
        Pattern pattern = Pattern.compile("[a-zA-Z\\s]+");
        Matcher matcher = pattern.matcher(name1);
        boolean isNameValid = matcher.matches();

        if (isNameValid) {
            this.name = name1;
        } else {
            this.name = "Name Surname";
            throw new RuntimeException("Ошибка: неверно введено ФИО");
        }
    }

    /// Пополнение баланса абонента на величину balance
    public void replenish_balance(final double balance) throws RuntimeException {
        if (balance > 0) {
            this.balance += balance;
        } else {
            throw new RuntimeException("Ошибка: неверно введена сумма пополнения баланса");
        }
    }

    /// Возвращает строку из всех полей объекта класса TelSub
    public String to_string() {
        String s;
        s = phone_number + ' ' + account_number + ' ' + tariff + ' ' + name + ' ' + String.valueOf(balance) + '\n';
        return s;
    }
}

