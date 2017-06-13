package JavaProjectFrontend.Models;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 * Created by Makciek on 04.06.2017.
 */
public class CodesGroup
{
    public SimpleStringProperty groupName = new SimpleStringProperty(this, "groupName");
    public SimpleListProperty<Code> codes = new SimpleListProperty<Code>(this, "codes");

    public CodesGroup(){}

    public CodesGroup(String groupName, ObservableList<Code> codes)
    {
        this.groupName.set(groupName);
        this.codes.set(codes);
    }
}
