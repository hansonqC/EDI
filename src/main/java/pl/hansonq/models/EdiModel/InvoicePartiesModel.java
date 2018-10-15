package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Invoice-Parties")
public class InvoiceModel {
private BuyerModel buyerModel;
private SellerModel sellerModel;

    public InvoiceModel(BuyerModel buyerModel, SellerModel sellerModel) {
        this.buyerModel = buyerModel;
        this.sellerModel = sellerModel;
    }

    public InvoiceModel() {
    }

    public BuyerModel getBuyerModel() {
        return buyerModel;
    }
@XmlElement(name="Buyer")
    public void setBuyerModel(BuyerModel buyerModel) {
        this.buyerModel = buyerModel;
    }

    public SellerModel getSellerModel() {
        return sellerModel;
    }
    @XmlElement(name="Seller")
    public void setSellerModel(SellerModel sellerModel) {
        this.sellerModel = sellerModel;
    }
}
