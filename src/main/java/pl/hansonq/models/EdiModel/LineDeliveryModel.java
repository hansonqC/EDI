package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Line-Delivery")
@XmlType(propOrder = {"partyName", "streetAndNumber", "cityName", "postCode", "deliveryDate", "despatchNumber"})
public class LineDeliveryModel {

    private String partyName;
    private String streetAndNumber;
    private String cityName;
    private String postCode;
    private String deliveryDate;
    private String despatchNumber;

    public LineDeliveryModel(String partyName, String streetAndNumber, String cityName, String postCode, String deliveryDate, String despatchNumber) {
        this.partyName = partyName;
        this.streetAndNumber = streetAndNumber;
        this.cityName = cityName;
        this.postCode = postCode;
        this.deliveryDate = deliveryDate;
        this.despatchNumber = despatchNumber;
    }

    public LineDeliveryModel() {
    }

    public String getPartyName() {
        return partyName;
    }

    @XmlElement(name = "PartyName")
    public void setPartyName(String partyName) {
        partyName = partyName;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    @XmlElement(name = "StreetAndNumber")
    public void setStreetAndNumber(String streetAndNumber) {
        streetAndNumber = streetAndNumber;
    }

    public String getCityName() {
        return cityName;
    }

    @XmlElement(name = "CityName")
    public void setCityName(String cityName) {
        cityName = cityName;
    }

    public String getPostCode() {
        return postCode;
    }

    @XmlElement(name = "PostCode")
    public void setPostCode(String postCode) {
        postCode = postCode;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    @XmlElement(name = "DeliveryDate")
    public void setDeliveryDate(String deliveryDate) {
        deliveryDate = deliveryDate;
    }

    public String getDespatchNumber() {
        return despatchNumber;
    }

    @XmlElement(name = "DespatchNumber")
    public void setDespatchNumber(String despatchNumber) {
        despatchNumber = despatchNumber;
    }

    @Override
    public String toString() {
        return "LineDeliveryModel{" +"\n"+
                "PartyName='" + partyName + '\'' +"\n"+
                ", StreeAndNumber='" + streetAndNumber + '\'' +"\n"+
                ", CityName='" + cityName + '\'' +"\n"+
                ", PostCode='" + postCode + '\'' +"\n"+
                ", DeliveryDate='" + deliveryDate + '\'' +"\n"+
                ", DespatchNumber='" + despatchNumber + '\'' +"\n"+
                '}';
    }
}
