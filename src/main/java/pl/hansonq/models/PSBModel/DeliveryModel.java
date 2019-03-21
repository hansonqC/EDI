package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeliveryDate")
public class DeliveryModel {
    private String deliveryDate;

    public String getDeliveryDate() {
        return deliveryDate;
    }

    @XmlElement(name = "DeliveryDate")
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
