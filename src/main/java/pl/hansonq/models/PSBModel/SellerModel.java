package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Seller")
@XmlType(propOrder = {"companyModel"})
public class SellerModel {
    private CompanyModel companyModel;

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    @XmlElement(name = "Company")
    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }

    @Override
    public String
    toString() {
        return "SellerModel{" +
                "companyModel=" + companyModel +
                '}';
    }
}
