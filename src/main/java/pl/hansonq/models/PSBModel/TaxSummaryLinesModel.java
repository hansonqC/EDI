package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"taxRate", "taxSymbol", "taxCategoryCode", "taxAmount", "taxableAmount", "grossAmount"})
@XmlRootElement(name = "TaxSummaryLine")
public class TaxSummaryLinesModel {
    private String taxRate;
    private String taxSymbol;
    private String taxCategoryCode;
    private String taxAmount;
    private String taxableAmount;
    private String grossAmount;


    public TaxSummaryLinesModel() {
    }


    public String getTaxRate() {
        return taxRate;
    }

    @XmlElement(name = "TaxRate")
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxSymbol() {
        return taxSymbol;
    }

    @XmlElement(name = "TaxSymbol")
    public void setTaxSymbol(String taxSymbol) {
        this.taxSymbol = taxSymbol;
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

    public String getGrossAmount() {
        return grossAmount;
    }

    @XmlElement(name = "GrossAmount")
    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    @Override
    public String toString() {
        return "TaxSummaryLinesModel{" +
                "taxRate='" + taxRate + '\'' +
                ", taxSymbol='" + taxSymbol + '\'' +
                ", taxCategoryCode='" + taxCategoryCode + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", taxableAmount='" + taxableAmount + '\'' +
                ", grossAmount='" + grossAmount + '\'' +
                '}';
    }
}
