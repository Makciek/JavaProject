package JavaProjectFrontend;

import JavaProjectFrontend.Models.Code;
import JavaProjectFrontend.Models.CodesGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.List;

public class Controller
{
    private ObservableList<CodesGroup> _codeGroups;

    @FXML
    private Tab someTab;

    @FXML
    private ListView codesListView;

    @FXML
    public TabPane codesTabPane;

    public Controller()
    {
        _codeGroups = FXCollections.observableArrayList();

        for (int i=0;i<20;i++){
            _codeGroups.add(new CodesGroup(
                    i + ". Pierwsze laby",
                    FXCollections.observableArrayList
                            (
                                    new Code(i + ".1 Przykład pierwszy"),
                                    new Code(i + ".1 Przykład drugi")   ,
                                    new Code(i + ".1 Przykład trzeci")
                            )
            ));
        }
    }

    @FXML
    private void initialize()
    {
        codesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        Tab tab = new Tab();
                        tab.setText("Hello world");

                        codesTabPane.getTabs().add(tab);
                    }
                }
            }
        });

        codesListView.setItems(_codeGroups);
        codesListView.setCellFactory(param -> new CodesGroupListCell());
    }
}
