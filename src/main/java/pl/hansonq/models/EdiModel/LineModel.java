package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Line")
@XmlType(propOrder = {"lineItemModel", "lineOrderModel", "lineDeliveryModel"})
public class LineModel {

    private LineItemModel lineItemModel;
    private LineOrderModel lineOrderModel;
    private LineDeliveryModel lineDeliveryModel;

    public LineModel() {
    }

    public LineModel(LineItemModel lineItemModel, LineOrderModel lineOrderModel, LineDeliveryModel lineDeliveryModel) {
        this.lineItemModel = lineItemModel;
        this.lineOrderModel = lineOrderModel;
        this.lineDeliveryModel = lineDeliveryModel;
    }


    public LineItemModel getLineItemModel() {
        return lineItemModel;
    }

    @XmlElement(name = "Line-Item")
    public void setLineItemModel(LineItemModel lineItemModel) {
        this.lineItemModel = lineItemModel;
    }

    public LineOrderModel getLineOrderModel() {
        return lineOrderModel;
    }

    @XmlElement(name = "Line-Order")
    public void setLineOrderModel(LineOrderModel lineOrderModel) {
        this.lineOrderModel = lineOrderModel;
    }

    public LineDeliveryModel getLineDeliveryModel() {
        return lineDeliveryModel;
    }

    @XmlElement(name = "Line-Delivery")
    public void setLineDeliveryModel(LineDeliveryModel lineDeliveryModel) {
        this.lineDeliveryModel = lineDeliveryModel;
    }

    @Override
    public String toString() {
        return "LineModel{" +"\n"+
                "lineItemModel=" + lineItemModel +"\n"+
                ", lineOrderModel=" + lineOrderModel +"\n"+
                ", lineDeliveryModel=" + lineDeliveryModel +"\n"+
                '}';
    }
}
