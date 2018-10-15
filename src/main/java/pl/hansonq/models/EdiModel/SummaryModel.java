package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Invoice-Summary")
@XmlType(propOrder = {"totalLines", "totalNetAmount", "totalTaxAmount", "totalGrossAmount", "taxSummaryModel"})
public class SummaryModel {
    private String totalLines;
    private String totalNetAmount;
    private String totalTaxAmount;
    private String totalGrossAmount;
    private ArrayList<TaxSummaryModel> taxSummaryModel;

    public SummaryModel(String totalLines, String totalNetAmount, String totalTaxAmount, String totalGrossAmount, ArrayList<TaxSummaryModel> taxSummaryModel) {
        this.totalLines = totalLines;
        this.totalNetAmount = totalNetAmount;
        this.totalTaxAmount = totalTaxAmount;
        this.totalGrossAmount = totalGrossAmount;
        this.taxSummaryModel = taxSummaryModel;
    }

    public SummaryModel() {
    }

    public String getTotalLines() {
        return totalLines;
    }

    @XmlElement(name = "TotalLines")
    public void setTotalLines(String totalLines) {
        this.totalLines = totalLines;
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

    public ArrayList<TaxSummaryModel> getTaxSummaryModel() {
        return taxSummaryModel;
    }
    @XmlElementWrapper(name = "Tax-Summary")
    @XmlElement(name = "Tax-Summary-Line")
    public void setTaxSummaryModel(ArrayList<TaxSummaryModel> taxSummaryModel) {
        this.taxSummaryModel = taxSummaryModel;
    }

    @Override
    public String toString() {
        return "SummaryModel{" +"\n"+
                "totalLines='" + totalLines + '\'' +"\n"+
                ", totalNetAmount='" + totalNetAmount + '\'' +"\n"+
                ", totalTaxAmount='" + totalTaxAmount + '\'' +"\n"+
                ", totalGrossAmount='" + totalGrossAmount + '\'' +"\n"+
                ", taxSummaryModel=" + taxSummaryModel +"\n"+
                '}';
    }
}
