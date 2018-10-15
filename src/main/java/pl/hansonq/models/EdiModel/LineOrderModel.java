package pl.hansonq.models.EdiModel;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Line-Order")
@XmlType(propOrder = {"buyerOrderNumber", "buyerOrderDate", "orderLineNumber"})
public class LineOrderModel {
    private String buyerOrderNumber;
    private String buyerOrderDate;
    private String orderLineNumber;

    public LineOrderModel() {
    }

    public LineOrderModel(String buyerOrderNumber, String buyerOrderDate, String orderLineNumber) {
        this.buyerOrderNumber = buyerOrderNumber;
        this.buyerOrderDate = buyerOrderDate;
        this.orderLineNumber = orderLineNumber;
    }

    public String getBuyerOrderNumber() {
        return buyerOrderNumber;
    }

    @XmlElement(name = "BuyerOrderNumber")
    public void setBuyerOrderNumber(String buyerOrderNumber) {
        this.buyerOrderNumber = buyerOrderNumber;
    }

    public String getBuyerOrderDate() {
        return buyerOrderDate;
    }

    @XmlElement(name = "BuyerOrderDate")
    public void setBuyerOrderDate(String buyerOrderDate) {
        this.buyerOrderDate = buyerOrderDate;
    }

    public String getOrderLineNumber() {
        return orderLineNumber;
    }

    @XmlElement(name = "OrderLineNumber")
    public void setOrderLineNumber(String orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    @Override
    public String toString() {
        return "LineOrderModel{" +"\n"+
                "BuyerOrderNumber='" + buyerOrderNumber + '\'' +"\n"+
                ", BuyerOrderDate='" + buyerOrderDate + '\'' +"\n"+
                ", OrderLineNumber='" + orderLineNumber + '\'' +"\n"+
                '}';
    }
}
