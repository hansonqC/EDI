package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "InvoiceInfos")
@XmlType(propOrder = {"infoModelList"})
public class InvoiceInfosModel {
    private List<InfoModel> infoModelList;

    public List<InfoModel> getInfoModelList() {
        return infoModelList;
    }

    @XmlElement(name = "Info")
    public void setInfoModelList(List<InfoModel> infoModelList) {
        this.infoModelList = infoModelList;
    }
}
