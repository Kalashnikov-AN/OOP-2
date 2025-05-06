package zabsu.telephone_sub_ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
// Автор: Калашников А.Н.

/// Класс, тестирующий методы класса TelSub
class TelSubTest {

    private TelSub telSub; // поле типа TelSub, будем использовать его во всех тестах

    @BeforeEach // метод выполняется перед каждым тестом
    /// Метод, создающий объект типа TelSub
    void setUp() {
        telSub = new TelSub();
    }

    @Test // метод является тестовым
    /// Тест конструктора по умолчанию
    void testDefaultConstructor() {
        // проверка всех полей на равенство с значениями по умолчанию
        assertEquals("+8(924)000-00-00", telSub.getPhone_number());
        assertEquals("000001", telSub.getAccount_number());
        assertEquals("Based", telSub.getTariff());
        assertEquals("Name Surname", telSub.getName());
        assertEquals(0, telSub.getBalance());
    }

    @Test // метод является тестовым
    /// Тест конструктора с параметрами
    void testParameterizedConstructor() {
        // объект класса TelSub, создаваемый конструктором с параметрами
        TelSub customSub = new TelSub("+7(999)123-45-67", "123456", "Pro", "Ivan Ivanov", 100.5);
        // Проверка на равенство значений в конструкторе с параметрами и полей объекта
        assertEquals("+7(999)123-45-67", customSub.getPhone_number());
        assertEquals("123456", customSub.getAccount_number());
        assertEquals("Pro", customSub.getTariff());
        assertEquals("Ivan Ivanov", customSub.getName());
        assertEquals(100.5, customSub.getBalance());
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего баланс абонента
    void getBalance() {
        assertEquals(0, telSub.getBalance());
        telSub.replenish_balance(50);
        assertEquals(50, telSub.getBalance());
        telSub.replenish_balance(150);
        assertEquals(200, telSub.getBalance());
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего телефонный номер абонента
    void getPhone_number() {
        assertEquals("+8(924)000-00-00", telSub.getPhone_number());
        telSub.setPhone_number("+7(912)345-67-89");
        assertEquals("+7(912)345-67-89", telSub.getPhone_number());
        assertThrows(RuntimeException.class, () -> telSub.setPhone_number("12345"));
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего телефонный номер абонента
    void setPhone_number() {
        telSub.setPhone_number("+7(912)345-67-89");
        assertEquals("+7(912)345-67-89", telSub.getPhone_number());
        assertThrows(RuntimeException.class, () -> telSub.setPhone_number("abcd"));
        assertThrows(RuntimeException.class, () -> telSub.setPhone_number("+79991234567"));
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего номер лицевого счёта абонента
    void getAccount_number() {
        assertEquals("000001", telSub.getAccount_number());
        telSub.setAccount_number("654321");
        assertEquals("654321", telSub.getAccount_number());
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего номер лицевого счёта абонента
    void setAccount_number() {
        telSub.setAccount_number("654321");
        assertEquals("654321", telSub.getAccount_number());
        assertThrows(RuntimeException.class, () -> telSub.setAccount_number("12345"));
        assertThrows(RuntimeException.class, () -> telSub.setAccount_number("abcdef"));
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего название тарифа абонента
    void getTariff() {
        assertEquals("Based", telSub.getTariff());
        telSub.setTariff("Medium");
        assertEquals("Medium", telSub.getTariff());
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего название тарифа абонента
    void setTariff() {
        telSub.setTariff("Advanced");
        assertEquals("Advanced", telSub.getTariff());
        assertThrows(RuntimeException.class, () -> telSub.setTariff("Tariff"));
        assertThrows(RuntimeException.class, () -> telSub.setTariff("123"));
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего ФИО абонента
    void getName() {
        assertEquals("Name Surname", telSub.getName());
        telSub.setName("Yura Ivanov");
        assertEquals("Yura Ivanov", telSub.getName());
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего ФИО абонента
    void setName() {
        telSub.setName("Ivan Ivanov");
        assertEquals("Ivan Ivanov", telSub.getName());
        assertThrows(RuntimeException.class, () -> telSub.setName("John123"));
        assertThrows(RuntimeException.class, () -> telSub.setName("!!!!!"));
    }

    @Test // метод является тестовым
    /// Проверка метода пополнения баланса на указанную сумму
    void replenish_balance() {
        telSub.replenish_balance(200);
        assertEquals(200, telSub.getBalance());
        telSub.replenish_balance(50.5);
        assertEquals(250.5, telSub.getBalance());
        assertThrows(RuntimeException.class, () -> telSub.replenish_balance(-10));
    }

    @Test // метод является тестовым
    /// Проверка метода, возвращающего все поля в одной строке
    void to_string() {
        String expected = "+8(924)000-00-00 000001 Based Name Surname 0.0";
        assertEquals(expected, telSub.toString());
        telSub.setName("Ivan Ivanov");
        telSub.setTariff("Pro");
        telSub.replenish_balance(300);
        expected = "+8(924)000-00-00 000001 Pro Ivan Ivanov 300.0";
        assertEquals(expected, telSub.toString());
    }
}