package zabsu.telephone_sub_ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;

public class TelSubDatabase {
    @FXML
    public ObservableList<TelSub> data = FXCollections.observableArrayList();

    public FilteredList<TelSub> filteredData;

    public void addSubscriber(TelSub subscriber) {
        data.add(subscriber); // Добавляем данные в ObservableList
    }
}
