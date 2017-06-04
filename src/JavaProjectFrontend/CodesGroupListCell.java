package JavaProjectFrontend;

import JavaProjectFrontend.Models.Code;
import JavaProjectFrontend.Models.CodesGroup;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.VBox;

/**
 * Created by Makciek on 04.06.2017.
 */
public class CodesGroupListCell extends ListCell<CodesGroup>
{
    @Override
    protected void updateItem(CodesGroup item, boolean empty) {
        super.updateItem(item, empty);

        if(item != null){
            VBox vbox = new VBox();
            Label header = new Label(item.GroupName.getValue());
            ListView<Code> codeListView = new ListView<Code>();

            codeListView.setItems(item.Codes);

            vbox.getChildren().add(header);
            vbox.getChildren().add(codeListView);
            setGraphic(vbox);
        }
    }
}
