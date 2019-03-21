package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Info")
@XmlType(propOrder = {"description","layout","valueModel"})
public class InfoModel {
    private String description;
    private String layout;
    private ValueModel valueModel;

    public InfoModel() {
    }

    public String getDescription() {
        return description;
    }
@XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLayout() {
        return layout;
    }
    @XmlElement(name = "Layout")
    public void setLayout(String layout) {
        this.layout = layout;
    }

    public ValueModel getValueModel() {
        return valueModel;
    }
    @XmlElement(name = "Values")
    public void setValueModel(ValueModel valueModel) {
        this.valueModel = valueModel;
    }

    @Override
    public String toString() {
        return "InfoModel{" +
                "description='" + description + '\'' +
                ", layout='" + layout + '\'' +
                ", valueModel=" + valueModel +
                '}';
    }
}
