package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ProductFeeDetails")
@XmlType(propOrder = {"unitNetPriceWithoutFee", "taxRateWithoutFee", "taxAmountWithoutFee", "netAmountWithoutFee", "feeValue", "feeTaxRate", "feeTaxAmount","feeNetAmount"})

public class ProductFeeDetailsModel {

    private String unitNetPriceWithoutFee;
    private String taxRateWithoutFee;
    private String taxAmountWithoutFee;
    private String netAmountWithoutFee;
    private String feeValue;
    private String feeTaxRate;
    private String feeTaxAmount;
    private String feeNetAmount;

    public ProductFeeDetailsModel() {
    }

    public String getUnitNetPriceWithoutFee() {
        return unitNetPriceWithoutFee;
    }
    @XmlElement(name = "UnitNetPriceWithoutFee")
    public void setUnitNetPriceWithoutFee(String unitNetPriceWithoutFee) {
        this.unitNetPriceWithoutFee = unitNetPriceWithoutFee;
    }

    public String getTaxRateWithoutFee() {
        return taxRateWithoutFee;
    }
    @XmlElement(name = "TaxRateWithoutFee")
    public void setTaxRateWithoutFee(String taxRateWithoutFee) {
        this.taxRateWithoutFee = taxRateWithoutFee;
    }

    public String getTaxAmountWithoutFee() {
        return taxAmountWithoutFee;
    }
    @XmlElement(name = "TaxAmountWithoutFee")
    public void setTaxAmountWithoutFee(String taxAmountWithoutFee) {
        this.taxAmountWithoutFee = taxAmountWithoutFee;
    }

    public String getNetAmountWithoutFee() {
        return netAmountWithoutFee;
    }
    @XmlElement(name = "NetAmountWithoutFee")
    public void setNetAmountWithoutFee(String netAmountWithoutFee) {
        this.netAmountWithoutFee = netAmountWithoutFee;
    }

    public String getFeeValue() {
        return feeValue;
    }
    @XmlElement(name = "FeeValue")
    public void setFeeValue(String feeValue) {
        this.feeValue = feeValue;
    }

    public String getFeeTaxRate() {
        return feeTaxRate;
    }
    @XmlElement(name = "FeeTaxRate")
    public void setFeeTaxRate(String feeTaxRate) {
        this.feeTaxRate = feeTaxRate;
    }

    public String getFeeTaxAmount() {
        return feeTaxAmount;
    }
    @XmlElement(name = "FeeTaxAmount")
    public void setFeeTaxAmount(String feeTaxAmount) {
        this.feeTaxAmount = feeTaxAmount;
    }

    public String getFeeNetAmount() {
        return feeNetAmount;
    }
    @XmlElement(name = "FeeNetAmount")
    public void setFeeNetAmount(String feeNetAmount) {
        this.feeNetAmount = feeNetAmount;
    }
}
