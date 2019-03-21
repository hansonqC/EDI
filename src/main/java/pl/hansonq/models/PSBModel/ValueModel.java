package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Values")
@XmlType(propOrder = {"value"})
public class ValueModel {
    private String value;

    public ValueModel() {
    }

    public ValueModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

 @ XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueModel{" +
                "value='" + value + '\'' +
                '}';
    }
}
