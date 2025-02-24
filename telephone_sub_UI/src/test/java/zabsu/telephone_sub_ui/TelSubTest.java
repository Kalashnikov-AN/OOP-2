package zabsu.telephone_sub_ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

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
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего телефонный номер абонента
    void getPhone_number() {
        assertEquals("+8(924)000-00-00", telSub.getPhone_number());
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего телефонный номер абонента
    void setPhone_number() {
        telSub.setPhone_number("+7(912)345-67-89");
        assertEquals("+7(912)345-67-89", telSub.getPhone_number());
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего номер лицевого счёта абонента
    void getAccount_number() {
        assertEquals("000001", telSub.getAccount_number());
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего номер лицевого счёта абонента
    void setAccount_number() {
        telSub.setAccount_number("654321");
        assertEquals("654321", telSub.getAccount_number());
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего название тарифа абонента
    void getTariff() {
        assertEquals("Based", telSub.getTariff());
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего название тарифа абонента
    void setTariff() {
        telSub.setTariff("Advanced");
        assertEquals("Advanced", telSub.getTariff());
    }

    @Test // метод является тестовым
    /// Проверка геттера, возвращающего ФИО абонента
    void getName() {
        assertEquals("Name Surname", telSub.getName());
    }

    @Test // метод является тестовым
    /// Проверка сеттера, устанавливаюшего ФИО абонента
    void setName() {
        telSub.setName("Ivan Ivanov");
        assertEquals("Ivan Ivanov", telSub.getName());
    }

    @Test // метод является тестовым
    /// Проверка метода пополнения баланса на указанную сумму
    void replenish_balance() {
        telSub.replenish_balance(200);
        assertEquals(200, telSub.getBalance());
    }

    @Test // метод является тестовым
    /// Проверка метода, возвращающего все поля в одной строке
    void to_string() {
        String expected = "+8(924)000-00-00 000001 Based Name Surname 0.0\n";
        assertEquals(expected, telSub.to_string());
    }
}