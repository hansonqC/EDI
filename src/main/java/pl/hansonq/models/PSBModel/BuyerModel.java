package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Buyer")
@XmlType(propOrder = {"taxID", "name", "address1", "address2", "cityName", "postalCode", "csk"})
public class BuyerModel {
    private String taxID;
    private String name;
    private String address1;
    private String address2;
    private String cityName;
    private String postalCode;
    private String csk;

    public BuyerModel() {
    }

    public String getTaxID() {
        return taxID;
    }

    @XmlElement(name = "TaxID")
    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    @XmlElement(name = "Address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    @XmlElement(name = "Address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCityName() {
        return cityName;
    }

    @XmlElement(name = "CityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @XmlElement(name = "PostalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCsk() {
        return csk;
    }

    @XmlElement(name = "CSK")
    public void setCsk(String csk) {
        this.csk = csk;
    }

    @Override
    public String toString() {
        return "BuyerModel{" +
                "taxID='" + taxID + '\'' +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", cityName='" + cityName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", csk='" + csk + '\'' +
                '}';
    }
}
