package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Application")
@XmlType(propOrder = {"name","version","formatVersion","operatorName"})
public class ApplicationModel {
    private String name;
    private String version;
    private String formatVersion;
    private String operatorName;

    public ApplicationModel() {
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }
    @XmlElement(name = "Version")
    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormatVersion() {
        return formatVersion;
    }
    @XmlElement(name = "FormatVersion")
    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }

    public String getOperatorName() {
        return operatorName;
    }
    @XmlElement(name = "OperatorName")
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "ApplicationModel{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", formatVersion='" + formatVersion + '\'' +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}
