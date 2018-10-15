package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Buyer")
@XmlType(propOrder = {"ILN", "taxID"})
public class BuyerModel {
    private String ILN;
    private String taxID;

    public BuyerModel(String ILN, String taxID) {
        this.ILN = ILN;
        this.taxID = taxID;
    }

    public BuyerModel() {
    }

    public String getILN() {
        return ILN;
    }

    @XmlElement(name = "ILN")
    public void setILN(String ILN) {
        this.ILN = ILN;
    }

    public String getTaxID() {
        return taxID;
    }

    @XmlElement(name = "TaxID")
    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    @Override
    public String toString() {
        return "BuyerModel{" +"\n"+
                "ILN='" + ILN + '\'' +"\n"+
                ", TaxID='" + taxID + '\'' +"\n"+
                '}';
    }
}
