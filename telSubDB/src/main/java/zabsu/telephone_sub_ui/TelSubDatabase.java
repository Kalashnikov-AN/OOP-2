package zabsu.telephone_sub_ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;

public class TelSubDatabase {
    @FXML
    public ObservableList<TelSub> data = FXCollections.observableArrayList();

    public FilteredList<TelSub> filteredData;
    public SortedList<TelSub> sortedData;
    public void addSubscriber(TelSub subscriber) {
        data.add(subscriber); // Добавляем данные в ObservableList
    }

    public void deleteSubscriber(TelSub subscriber){
        data.remove(subscriber);
    }

    public void editSubscriber(TelSub subscriber, String pn, String an, String name, String tariff, double balance){
        subscriber.setName(name);
        subscriber.setPhone_number(pn);
        subscriber.setAccount_number(an);
        subscriber.setTariff(tariff);
        subscriber.balance = (balance);
    }

}
