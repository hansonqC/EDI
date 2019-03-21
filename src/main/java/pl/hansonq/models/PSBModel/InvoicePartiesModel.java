package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InvoiceParties")
@XmlType(propOrder = {"buyerModel","sellerModel"})
public class InvoicePartiesModel {
    private BuyerModel buyerModel;
    private SellerModel sellerModel;

    public InvoicePartiesModel(BuyerModel buyerModel, SellerModel sellerModel) {
        this.buyerModel = buyerModel;
        this.sellerModel = sellerModel;
    }

    public InvoicePartiesModel() {
    }

    public BuyerModel getBuyerModel() {
        return buyerModel;
    }

    @XmlElement(name = "Buyer")
    public void setBuyerModel(BuyerModel buyerModel) {
        this.buyerModel = buyerModel;
    }

    public SellerModel getSellerModel() {
        return sellerModel;
    }

    @XmlElement(name = "Seller")
    public void setSellerModel(SellerModel sellerModel) {
        this.sellerModel = sellerModel;
    }

    @Override
    public String toString() {
        return "InvoicePartiesModel{" +"\n"+
                "buyerModel=" + buyerModel +"\n"+
                ", sellerModel=" + sellerModel +"\n"+
                '}';
    }
}
