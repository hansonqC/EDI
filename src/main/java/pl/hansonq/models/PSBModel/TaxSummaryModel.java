package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Tax-Summary-Line")
@XmlType(propOrder = {"taxRate","taxCategoryCode","taxAmount","taxableAmount"})
public class TaxSummaryModel {
    private String taxRate;
    private String taxCategoryCode;
    private String taxAmount;
    private String taxableAmount;

    public TaxSummaryModel(String taxRate, String taxCategoryCode, String taxAmount, String taxableAmount) {
        this.taxRate = taxRate;
        this.taxCategoryCode = taxCategoryCode;
        this.taxAmount = taxAmount;
        this.taxableAmount = taxableAmount;
    }

    public TaxSummaryModel() {
    }

    public String getTaxRate() {
        return taxRate;
    }

    @XmlElement(name = "TaxRate")
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxCategoryCode() {
        return taxCategoryCode;
    }
    @XmlElement(name = "TaxCategoryCode")
    public void setTaxCategoryCode(String taxCategoryCode) {
        this.taxCategoryCode = taxCategoryCode;
    }

    public String getTaxAmount() {
        return taxAmount;
    }
    @XmlElement(name = "TaxAmount")
    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxableAmount() {
        return taxableAmount;
    }
    @XmlElement(name = "TaxableAmount")
    public void setTaxableAmount(String taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    @Override
    public String toString() {
        return "TaxSummaryModel{" +"\n"+
                "taxRate='" + taxRate + '\'' +"\n"+
                ", taxCategoryCode='" + taxCategoryCode + '\'' +"\n"+
                ", taxAmount='" + taxAmount + '\'' +
                ", taxableAmount='" + taxableAmount + '\'' +"\n"+
                '}';
    }
}
