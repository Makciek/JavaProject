package JavaProjectFrontend;

import JavaProjectFrontend.Models.Code;
import JavaProjectFrontend.Models.CodesGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.util.Callback;

import java.util.List;

public class Controller
{
    private ObservableList<CodesGroup> _codeGroups;

    @FXML
    private Tab someTab;

    @FXML
    private ListView codesListView;

    public Controller()
    {
        _codeGroups = FXCollections.observableArrayList
        (
            new CodesGroup(
                "1. Pierwsze laby",
                FXCollections.observableArrayList
                (
                     new Code("1.1 Przykład pierwszy"),
                     new Code("1.1 Przykład drugi")   ,
                     new Code("1.1 Przykład trzeci")
                )
            ),
            new CodesGroup(
                "2. Drugie laby",
                FXCollections.observableArrayList
                (
                     new Code("1.1 Przykład pierwszy"),
                     new Code("1.1 Przykład drugi")   ,
                     new Code("1.1 Przykład trzeci")
                )
            )
        );
    }

    @FXML
    private void initialize()
    {
        codesListView.setItems(_codeGroups);
        codesListView.setCellFactory(new Callback<ListView<CodesGroup>, ListCell<CodesGroup>>() {
            @Override
            public ListCell call(ListView param)
            {
                return new CodesGroupListCell();
            }
        });
    }
}
