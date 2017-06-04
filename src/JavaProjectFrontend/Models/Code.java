package JavaProjectFrontend.Models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Makciek on 04.06.2017.
 */
public class Code
{
    public SimpleStringProperty CodeName = new SimpleStringProperty(this, "codeName");

    public Code(){}
    public Code(String codeName)
    {
        CodeName.set(codeName);
    }
}
