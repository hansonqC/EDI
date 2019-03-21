package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Line")
@XmlType(propOrder = {"detailModel"})
public class LineModel {
    private DetailModel detailModel;

    public LineModel() {
    }

    public DetailModel getDetailModel() {
        return detailModel;
    }

    @XmlElement(name = "Detail")
    public void setDetailModel(DetailModel detailModel) {
        this.detailModel = detailModel;
    }

    @Override
    public String toString() {
        return "LineModel{" +
                "detailModel=" + detailModel +
                '}';
    }
}
