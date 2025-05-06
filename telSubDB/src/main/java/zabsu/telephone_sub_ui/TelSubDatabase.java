package zabsu.telephone_sub_ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
// Автор: Калашников А.Н.

/// Модель
public class TelSubDatabase {

    /// Массив для хранения записей базы данных
    @FXML
    public ObservableList<TelSub> data = FXCollections.observableArrayList();

    /// Массив для хранения отфилльтрованных поиском записей базы данных
    public FilteredList<TelSub> filteredData;

    /// Массив для хранения отфилльтрованных поиском записей базы данных(возможна сортировка по столбцам)
    public SortedList<TelSub> sortedData;

    /// Добавляет новую запись subsciber в массив, хранящий данные
    public void addSubscriber(TelSub subscriber) {
        data.add(subscriber); // Добавляем данные в ObservableList
    }

    /// Удаляет запись subscriber из массива с данными
    public void deleteSubscriber(TelSub subscriber){
        data.remove(subscriber);
    }

    /**
     * Обновляет данные существующего абонента
     * @param subscriber Абонент, данные которого необходимо изменить
     * @param pn Новый номер телефона
     * @param an Новый лицевой счёт
     * @param name Новое ФИО абонента
     * @param tariff Новый тариф абонента
     * @param balance Новый баланс абонента
     */
    public void editSubscriber(TelSub subscriber, String pn, String an, String name, String tariff, double balance){
        subscriber.setName(name);
        subscriber.setPhone_number(pn);
        subscriber.setAccount_number(an);
        subscriber.setTariff(tariff);
        subscriber.balance = (balance);
    }

}
