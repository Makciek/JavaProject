package JavaProjectFrontend.Models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Makciek on 04.06.2017.
 */
public class Code
{
    public SimpleStringProperty codeName = new SimpleStringProperty(this, "codeName");
    public SimpleStringProperty code = new SimpleStringProperty(this, "code");

    public Code(){}
    public Code(String codeName, String code)
    {
        this.codeName.set(codeName);
        this.code.set(code);
    }
}
