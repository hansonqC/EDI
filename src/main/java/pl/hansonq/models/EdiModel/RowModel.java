package pl.hansonq.models.EdiModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.util.Objects;

public class RowModel {
    private CheckBox select;
    private SimpleStringProperty xmlName;
    private SimpleStringProperty invoiceNumber;

    public RowModel(String xmlName, String invoiceNumber) {
        this.select = new CheckBox();
        this.xmlName = new SimpleStringProperty(xmlName);
        this.invoiceNumber = new SimpleStringProperty(invoiceNumber);
    }

    public RowModel() {
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public String getXmlName() {
        return xmlName.get();
    }


    public void setXmlName(String xmlName) {
        this.xmlName.set(xmlName);
    }

    public String getInvoiceNumber() {
        return invoiceNumber.get();
    }


    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber.set(invoiceNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowModel rowModel = (RowModel) o;
        return Objects.equals(xmlName, rowModel.xmlName) &&
                Objects.equals(invoiceNumber, rowModel.invoiceNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(xmlName, invoiceNumber);
    }
}
