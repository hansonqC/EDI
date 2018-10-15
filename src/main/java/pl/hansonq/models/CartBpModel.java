package pl.hansonq.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="data")
public class CartBpModel {
    private String kod;
    private String dlugosc;
    private String szerokosc;
    private String wysokosc;
    private String objetosc;
    private String waga;
    private String waga_objetosciowa;

    public CartBpModel(String kod, String dlugosc, String szerokosc, String wysokosc,String objetosc, String waga, String waga_objetosciowa) {
        this.kod = kod;
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.objetosc=objetosc;
        this.waga = waga;
        this.waga_objetosciowa = waga_objetosciowa;
    }

    public CartBpModel() {
    }

    public String getEan() {
        return kod;
    }
    @XmlElement(name = "UserValue1")
    public void setEan(String ean) {
        this.kod = ean;
    }

    public String getDlugosc() {
        return dlugosc;
    }
    @XmlElement(name = "Lenght")
    public void setDlugosc(String dlugosc) {
        this.dlugosc = dlugosc;
    }

    public String getSzerokosc() {
        return szerokosc;
    }
    @XmlElement(name = "Width")
    public void setSzerokosc(String szerokosc) {
        this.szerokosc = szerokosc;
    }

    public String getWysokosc() {
        return wysokosc;
    }
    @XmlElement(name = "Height")
    public void setWysokosc(String wysokosc) {
        this.wysokosc = wysokosc;
    }

    public String getObjetosc() {
        return objetosc;
    }
    @XmlElement(name = "Volume")
    public void setObjetosc(String objetosc) {
        this.objetosc = objetosc;
    }

    public String getWaga() {
        return waga;
    }
    @XmlElement(name = "Weight")
    public void setWaga(String waga) {
        this.waga = waga;
    }

    public String getWaga_objetosciowa() {
        return waga_objetosciowa;
    }
    @XmlElement(name = "VolumeWeight")
    public void setWaga_objetosciowa(String waga_objetosciowa) {
        this.waga_objetosciowa = waga_objetosciowa;
    }

    @Override
    public String toString() {
        return "Kartoteka : \n" +
                "EAN : "+kod+"\n"+
                "Długość : "+dlugosc+"\n"+
                "Szerokość : "+szerokosc+"\n"+
                "Wysokość : "+wysokosc+"\n"+
                "Objętość : "+objetosc+"\n"+
                "Waga : "+waga+"\n"+
                "Waga objętościowa : "+waga_objetosciowa;

    }
}
