package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement(name = "Invoice-Summary")
@XmlType(propOrder = {"totalNetAmount", "totalTaxAmount", "totalGrossAmount", "taxSummaryLineModel"})
public class SummaryModel {

    private String totalNetAmount;
    private String totalTaxAmount;
    private String totalGrossAmount;
    private ArrayList<TaxSummaryLinesModel> taxSummaryLineModel;

    public SummaryModel() {
    }

    public String getTotalNetAmount() {
        return totalNetAmount;
    }
    @XmlElement(name = "TotalNetAmount")
    public void setTotalNetAmount(String totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public String getTotalTaxAmount() {
        return totalTaxAmount;
    }
    @XmlElement(name = "TotalTaxAmount")
    public void setTotalTaxAmount(String totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public String getTotalGrossAmount() {
        return totalGrossAmount;
    }
    @XmlElement(name = "TotalGrossAmount")
    public void setTotalGrossAmount(String totalGrossAmount) {
        this.totalGrossAmount = totalGrossAmount;
    }

    public ArrayList<TaxSummaryLinesModel> getTaxSummaryLineModel() {
        return taxSummaryLineModel;
    }

    @XmlElementWrapper(name = "TaxSummaryLines")
    @XmlElement(name = "TaxSummaryLine")
    public void setTaxSummaryLineModel(ArrayList<TaxSummaryLinesModel> taxSummaryLineModel) {
        this.taxSummaryLineModel = taxSummaryLineModel;
    }

    @Override
    public String toString() {
        return "SummaryModel{" +
                "totalNetAmount='" + totalNetAmount + '\'' +
                ", totalTaxAmount='" + totalTaxAmount + '\'' +
                ", totalGrossAmount='" + totalGrossAmount + '\'' +
                ", taxSummaryLineModel=" + taxSummaryLineModel +
                '}';
    }
}
