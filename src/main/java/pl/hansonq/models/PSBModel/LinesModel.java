package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "InvoiceLines")
public class LinesModel {
    private List<LineModel> invoiceLines;

    public LinesModel(List<LineModel> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public LinesModel() {
    }

    public List<LineModel> getInvoiceLines() {
        return invoiceLines;
    }

    @XmlElement(name = "Line")
    public void setInvoiceLines(List<LineModel> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @Override
    public String toString() {
        return "LinesModel{" +"\n"+
                "invoiceLines=" + invoiceLines +"\n"+
                '}';
    }
}
