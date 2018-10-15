package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Invoice-Parties")
@XmlType(propOrder = {"buyerModel", "sellerModel"})
public class PartiesModel {

    private BuyerModel buyerModel;
    private SellerModel sellerModel;

    public PartiesModel(BuyerModel buyerModel, SellerModel sellerModel) {
        this.buyerModel = buyerModel;
        this.sellerModel = sellerModel;
    }

    public PartiesModel() {
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
        return "PartiesModel{" +"\n"+
                "buyerModel=" + buyerModel +"\n"+
                ", sellerModel=" + sellerModel +"\n"+
                '}';
    }
}
