package JavaProjectFrontend.Models;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Observable;

/**
 * Created by Makciek on 04.06.2017.
 */
public class CodesGroup
{
    public SimpleStringProperty GroupName = new SimpleStringProperty(this, "groupName");
    public SimpleListProperty<Code> Codes = new SimpleListProperty<>(this, "codes");

    public CodesGroup(){}

    public CodesGroup(String groupName, ObservableList<Code> codes)
    {
        GroupName.set(groupName);
        Codes.set(codes);
    }
}
