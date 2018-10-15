package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Seller")
@XmlType(propOrder = {"ILN", "taxID", "accountNumber", "utilizationRegisterNumber"})
public class SellerModel {
    private String ILN;
    private String taxID;
    private String accountNumber;
    private String utilizationRegisterNumber;

    public SellerModel(String ILN, String taxID, String accountNumber, String utilizationRegisterNumber) {
        this.ILN = ILN;
        this.taxID = taxID;
        this.accountNumber = accountNumber;
        this.utilizationRegisterNumber = utilizationRegisterNumber;
    }



    public SellerModel() {
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

    public String getAccountNumber() {
        return accountNumber;
    }

    @XmlElement(name = "AccountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUtilizationRegisterNumber() {
        return utilizationRegisterNumber;
    }

    @XmlElement(name = "UtilizationRegisterNumber")
    public void setUtilizationRegisterNumber(String utilizationRegisterNumber) {
        this.utilizationRegisterNumber = utilizationRegisterNumber;
    }

    @Override
    public String toString() {
        return "SellerModel{" +"\n"+
                "ILN='" + ILN + '\'' +"\n"+
                ", TaxID='" + taxID + '\'' +"\n"+
                ", AccountNumber='" + accountNumber + '\'' +"\n"+
                ", UtilizationRegisterNumber='" + utilizationRegisterNumber + '\'' +"\n"+
                '}';
    }

}
