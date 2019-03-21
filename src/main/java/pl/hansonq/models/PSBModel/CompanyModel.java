package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Company")
@XmlType(propOrder = {"ILN", "taxID", "bankName", "accountNumber", "name", "address1", "address2", "country", "csk"})

public class CompanyModel {
  private String ILN;
    private String taxID;
    private String bankName;
    private String accountNumber;
    private String name;
    private String address1;
    private String address2;
    private String country;
    private String csk;

    public CompanyModel() {
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

    public String getBankName() {
        return bankName;
    }

    @XmlElement(name = "BankName")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @XmlElement(name = "AccountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCountry() {
        return country;
    }

    @XmlElement(name = "Country")
    public void setCountry(String country) {
        this.country = country;
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
        return "CompanyModel{" +
                "ILN='" + ILN + '\'' +
                ", taxID='" + taxID + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", csk='" + csk + '\'' +
                '}';
    }
}

