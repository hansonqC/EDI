package pl.hansonq.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Map;

public class CartModel {
    private String indeks;
    private String identyfikator;
    private String nazwaInternetowa;
    private String nazwaSystemowa;
    private String jednostka;
    private String kodEanDomyslny;
    private String dodatkowyEan;
    private String cenaHurtowa;
    private String cenaSklepInternetowy;
    private String cenaDlaParagonu;
    private String ostatniaCena;
    private String nazwaZdjecia;
    private String dokumentacja;
    private String uwagi;
    private String ostrzezenie;
    private String kgo;
    private String waga;
    private String kartotekiPowiazane;
    private String zamienniki;
    private String idPriorytet;
    private String opakowanieZbiorcze1;
    private String opakowanieZbiorcze2;
    private String opakowanieZbiorcze3;
    private String stawkaVat;
    private String opis;
    private String stronaWWW;
    private String grupaRabatowa;
    private String grupaBonusowa;
    private String producent;
    private String grupaKartotekowa1;
    private String grupaKartotekowa2;
    private String grupaKartotekowa3;
    private String grupaKartotekowa4;
    private String grupaKartotekowa5;
    private String grupaKartotekowaX;
    private String cechaWkatalogu;
    private String cechaNapiecie;
    private String cechaMoc;
    private String idDodatkowegoOpakowanieZbiorczegoEAN;
    private String idOpakowaniaZbiorczego1;
    private String idOpakowaniaZbiorczego2;
    private String idOpakowaniaZbiorczego3;
    private String idTypOpisu;
    private String idStawkiVat;
    private String idRodzajuGrupyKartotekowejProducent;
    private String idRodzajuGrupyKartotekowej1;
    private String idRodzajuGrupyKartotekowej2;
    private String idRodzajuGrupyKartotekowej3;
    private String idRodzajuGrupyKartotekowej4;
    private String idRodzajuGrupyKartotekowej5;
    private String idRodzajuGrupyKartotekowejX;


    private String idProperty;
    private Map<Integer, String> cechy;

    public CartModel() {
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public String getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator(String identyfikator) {
        this.identyfikator = identyfikator;
    }

    public String getNazwaInternetowa() {
        return nazwaInternetowa;
    }

    public void setNazwaInternetowa(String nazwaInternetowa) {
        this.nazwaInternetowa = nazwaInternetowa;
    }

    public String getNazwaSystemowa() {
        return nazwaSystemowa;
    }

    public void setNazwaSystemowa(String nazwaSystemowa) {
        this.nazwaSystemowa = nazwaSystemowa;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public String getKodEanDomyslny() {
        return kodEanDomyslny;
    }

    public void setKodEanDomyslny(String kodEanDomyslny) {
        this.kodEanDomyslny = kodEanDomyslny;
    }

    public String getDodatkowyEan() {
        return dodatkowyEan;
    }

    public void setDodatkowyEan(String dodatkowyEan) {
        this.dodatkowyEan = dodatkowyEan;
    }

    public String getCenaHurtowa() {
        return cenaHurtowa;
    }

    public void setCenaHurtowa(String cenaHurtowa) {
        this.cenaHurtowa = cenaHurtowa;
    }

    public String getCenaSklepInternetowy() {
        return cenaSklepInternetowy;
    }

    public void setCenaSklepInternetowy(String cenaSklepInternetowy) {
        this.cenaSklepInternetowy = cenaSklepInternetowy;
    }

    public String getCenaDlaParagonu() {
        return cenaDlaParagonu;
    }

    public void setCenaDlaParagonu(String cenaDlaParagonu) {
        this.cenaDlaParagonu = cenaDlaParagonu;
    }

    public String getOstatniaCena() {
        return ostatniaCena;
    }

    public void setOstatniaCena(String ostatniaCena) {
        this.ostatniaCena = ostatniaCena;
    }

    public String getNazwaZdjecia() {
        return nazwaZdjecia;
    }

    public void setNazwaZdjecia(String nazwaZdjecia) {
        this.nazwaZdjecia = nazwaZdjecia;
    }

    public String getDokumentacja() {
        return dokumentacja;
    }

    public void setDokumentacja(String dokumentacja) {
        this.dokumentacja = dokumentacja;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public String getOstrzezenie() {
        return ostrzezenie;
    }

    public void setOstrzezenie(String ostrzezenie) {
        this.ostrzezenie = ostrzezenie;
    }

    public String getKgo() {
        return kgo;
    }

    public void setKgo(String kgo) {
        this.kgo = kgo;
    }

    public String getWaga() {
        return waga;
    }

    public void setWaga(String waga) {
        this.waga = waga;
    }

    public String getKartotekiPowiazane() {
        return kartotekiPowiazane;
    }

    public void setKartotekiPowiazane(String kartotekiPowiazane) {
        this.kartotekiPowiazane = kartotekiPowiazane;
    }

    public String getZamienniki() {
        return zamienniki;
    }

    public void setZamienniki(String zamienniki) {
        this.zamienniki = zamienniki;
    }

    public String getIdPriorytet() {
        return idPriorytet;
    }

    public void setIdPriorytet(String idPriorytet) {
        this.idPriorytet = idPriorytet;
    }

    public String getOpakowanieZbiorcze1() {
        return opakowanieZbiorcze1;
    }

    public void setOpakowanieZbiorcze1(String opakowanieZbiorcze1) {
        this.opakowanieZbiorcze1 = opakowanieZbiorcze1;
    }

    public String getOpakowanieZbiorcze2() {
        return opakowanieZbiorcze2;
    }

    public void setOpakowanieZbiorcze2(String opakowanieZbiorcze2) {
        this.opakowanieZbiorcze2 = opakowanieZbiorcze2;
    }

    public String getOpakowanieZbiorcze3() {
        return opakowanieZbiorcze3;
    }

    public void setOpakowanieZbiorcze3(String opakowanieZbiorcze3) {
        this.opakowanieZbiorcze3 = opakowanieZbiorcze3;
    }

    public String getStawkaVat() {
        return stawkaVat;
    }

    public void setStawkaVat(String stawkaVat) {
        this.stawkaVat = stawkaVat;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getStronaWWW() {
        return stronaWWW;
    }

    public void setStronaWWW(String stronaWWW) {
        this.stronaWWW = stronaWWW;
    }

    public String getGrupaRabatowa() {
        return grupaRabatowa;
    }

    public void setGrupaRabatowa(String grupaRabatowa) {
        this.grupaRabatowa = grupaRabatowa;
    }

    public String getGrupaBonusowa() {
        return grupaBonusowa;
    }

    public void setGrupaBonusowa(String grupaBonusowa) {
        this.grupaBonusowa = grupaBonusowa;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getGrupaKartotekowa1() {
        return grupaKartotekowa1;
    }

    public void setGrupaKartotekowa1(String grupaKartotekowa1) {
        this.grupaKartotekowa1 = grupaKartotekowa1;
    }

    public String getGrupaKartotekowa2() {
        return grupaKartotekowa2;
    }

    public void setGrupaKartotekowa2(String grupaKartotekowa2) {
        this.grupaKartotekowa2 = grupaKartotekowa2;
    }

    public String getGrupaKartotekowa3() {
        return grupaKartotekowa3;
    }

    public void setGrupaKartotekowa3(String grupaKartotekowa3) {
        this.grupaKartotekowa3 = grupaKartotekowa3;
    }

    public String getGrupaKartotekowa4() {
        return grupaKartotekowa4;
    }

    public void setGrupaKartotekowa4(String grupaKartotekowa4) {
        this.grupaKartotekowa4 = grupaKartotekowa4;
    }

    public String getGrupaKartotekowa5() {
        return grupaKartotekowa5;
    }

    public void setGrupaKartotekowa5(String grupaKartotekowa5) {
        this.grupaKartotekowa5 = grupaKartotekowa5;
    }

    public String getGrupaKartotekowaX() {
        return grupaKartotekowaX;
    }

    public void setGrupaKartotekowaX(String grupaKartotekowaX) {
        this.grupaKartotekowaX = grupaKartotekowaX;
    }

    public String getCechaWkatalogu() {
        return cechaWkatalogu;
    }

    public void setCechaWkatalogu(String cechaWkatalogu) {
        this.cechaWkatalogu = cechaWkatalogu;
    }

    public String getCechaNapiecie() {
        return cechaNapiecie;
    }

    public void setCechaNapiecie(String cechaNapiecie) {
        this.cechaNapiecie = cechaNapiecie;
    }

    public String getCechaMoc() {
        return cechaMoc;
    }

    public void setCechaMoc(String cechaMoc) {
        this.cechaMoc = cechaMoc;
    }

    public String getIdDodatkowegoOpakowanieZbiorczegoEAN() {
        return idDodatkowegoOpakowanieZbiorczegoEAN;
    }

    public void setIdDodatkowegoOpakowanieZbiorczegoEAN(String idDodatkowegoOpakowanieZbiorczegoEAN) {
        this.idDodatkowegoOpakowanieZbiorczegoEAN = idDodatkowegoOpakowanieZbiorczegoEAN;
    }

    public String getIdOpakowaniaZbiorczego1() {
        return idOpakowaniaZbiorczego1;
    }

    public void setIdOpakowaniaZbiorczego1(String idOpakowaniaZbiorczego1) {
        this.idOpakowaniaZbiorczego1 = idOpakowaniaZbiorczego1;
    }

    public String getIdOpakowaniaZbiorczego2() {
        return idOpakowaniaZbiorczego2;
    }

    public void setIdOpakowaniaZbiorczego2(String idOpakowaniaZbiorczego2) {
        this.idOpakowaniaZbiorczego2 = idOpakowaniaZbiorczego2;
    }

    public String getIdOpakowaniaZbiorczego3() {
        return idOpakowaniaZbiorczego3;
    }

    public void setIdOpakowaniaZbiorczego3(String idOpakowaniaZbiorczego3) {
        this.idOpakowaniaZbiorczego3 = idOpakowaniaZbiorczego3;
    }

    public String getIdTypOpisu() {
        return idTypOpisu;
    }

    public void setIdTypOpisu(String idTypOpisu) {
        this.idTypOpisu = idTypOpisu;
    }

    public String getIdStawkiVat() {
        return idStawkiVat;
    }

    public void setIdStawkiVat(String idStawkiVat) {
        this.idStawkiVat = idStawkiVat;
    }

    public String getIdRodzajuGrupyKartotekowejProducent() {
        return idRodzajuGrupyKartotekowejProducent;
    }

    public void setIdRodzajuGrupyKartotekowejProducent(String idRodzajuGrupyKartotekowejProducent) {
        this.idRodzajuGrupyKartotekowejProducent = idRodzajuGrupyKartotekowejProducent;
    }

    public String getIdRodzajuGrupyKartotekowej1() {
        return idRodzajuGrupyKartotekowej1;
    }

    public void setIdRodzajuGrupyKartotekowej1(String idRodzajuGrupyKartotekowej1) {
        this.idRodzajuGrupyKartotekowej1 = idRodzajuGrupyKartotekowej1;
    }

    public String getIdRodzajuGrupyKartotekowej2() {
        return idRodzajuGrupyKartotekowej2;
    }

    public void setIdRodzajuGrupyKartotekowej2(String idRodzajuGrupyKartotekowej2) {
        this.idRodzajuGrupyKartotekowej2 = idRodzajuGrupyKartotekowej2;
    }

    public String getIdRodzajuGrupyKartotekowej3() {
        return idRodzajuGrupyKartotekowej3;
    }

    public void setIdRodzajuGrupyKartotekowej3(String idRodzajuGrupyKartotekowej3) {
        this.idRodzajuGrupyKartotekowej3 = idRodzajuGrupyKartotekowej3;
    }

    public String getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(String idProperty) {
        this.idProperty = idProperty;
    }

    public Map<Integer, String> getCechy() {
        return cechy;
    }

    public void setCechy(Map<Integer, String> cechy) {
        this.cechy = cechy;
    }

    public String getIdRodzajuGrupyKartotekowej4() {
        return idRodzajuGrupyKartotekowej4;
    }

    public void setIdRodzajuGrupyKartotekowej4(String idRodzajuGrupyKartotekowej4) {
        this.idRodzajuGrupyKartotekowej4 = idRodzajuGrupyKartotekowej4;
    }

    public String getIdRodzajuGrupyKartotekowej5() {
        return idRodzajuGrupyKartotekowej5;
    }

    public void setIdRodzajuGrupyKartotekowej5(String idRodzajuGrupyKartotekowej5) {
        this.idRodzajuGrupyKartotekowej5 = idRodzajuGrupyKartotekowej5;
    }

    public String getIdRodzajuGrupyKartotekowejX() {
        return idRodzajuGrupyKartotekowejX;
    }

    public void setIdRodzajuGrupyKartotekowejX(String idRodzajuGrupyKartotekowejX) {
        this.idRodzajuGrupyKartotekowejX = idRodzajuGrupyKartotekowejX;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "indeks='" + indeks + '\'' +
                ", identyfikator='" + identyfikator + '\'' +
                ", nazwaInternetowa='" + nazwaInternetowa + '\'' +
                ", nazwaSystemowa='" + nazwaSystemowa + '\'' +
                ", jednostka='" + jednostka + '\'' +
                ", kodEanDomyslny='" + kodEanDomyslny + '\'' +
                ", dodatkowyEan='" + dodatkowyEan + '\'' +
                ", cenaHurtowa='" + cenaHurtowa + '\'' +
                ", cenaSklepInternetowy='" + cenaSklepInternetowy + '\'' +
                ", cenaDlaParagonu='" + cenaDlaParagonu + '\'' +
                ", ostatniaCena='" + ostatniaCena + '\'' +
                ", nazwaZdjecia='" + nazwaZdjecia + '\'' +
                ", dokumentacja='" + dokumentacja + '\'' +
                ", uwagi='" + uwagi + '\'' +
                ", ostrzezenie='" + ostrzezenie + '\'' +
                ", kgo='" + kgo + '\'' +
                ", waga='" + waga + '\'' +
                ", kartotekiPowiazane='" + kartotekiPowiazane + '\'' +
                ", zamienniki='" + zamienniki + '\'' +
                ", idPriorytet='" + idPriorytet + '\'' +
                ", opakowanieZbiorcze1='" + opakowanieZbiorcze1 + '\'' +
                ", opakowanieZbiorcze2='" + opakowanieZbiorcze2 + '\'' +
                ", opakowanieZbiorcze3='" + opakowanieZbiorcze3 + '\'' +
                ", stawkaVat='" + stawkaVat + '\'' +
                ", opis='" + opis + '\'' +
                ", stronaWWW='" + stronaWWW + '\'' +
                ", grupaRabatowa='" + grupaRabatowa + '\'' +
                ", grupaBonusowa='" + grupaBonusowa + '\'' +
                ", producent='" + producent + '\'' +
                ", grupaKartotekowa1='" + grupaKartotekowa1 + '\'' +
                ", grupaKartotekowa2='" + grupaKartotekowa2 + '\'' +
                ", grupaKartotekowa3='" + grupaKartotekowa3 + '\'' +
                ", grupaKartotekowa4='" + grupaKartotekowa4 + '\'' +
                ", grupaKartotekowa5='" + grupaKartotekowa5 + '\'' +
                ", grupaKartotekowaX='" + grupaKartotekowaX + '\'' +
                ", cechaWkatalogu='" + cechaWkatalogu + '\'' +
                ", cechaNapiecie='" + cechaNapiecie + '\'' +
                ", cechaMoc='" + cechaMoc + '\'' +
                ", idDodatkowegoOpakowanieZbiorczegoEAN='" + idDodatkowegoOpakowanieZbiorczegoEAN + '\'' +
                ", idOpakowaniaZbiorczego1='" + idOpakowaniaZbiorczego1 + '\'' +
                ", idOpakowaniaZbiorczego2='" + idOpakowaniaZbiorczego2 + '\'' +
                ", idOpakowaniaZbiorczego3='" + idOpakowaniaZbiorczego3 + '\'' +
                ", idTypOpisu='" + idTypOpisu + '\'' +
                ", idStawkiVat='" + idStawkiVat + '\'' +
                ", idRodzajuGrupyKartotekowejProducent='" + idRodzajuGrupyKartotekowejProducent + '\'' +
                ", idRodzajuGrupyKartotekowej1='" + idRodzajuGrupyKartotekowej1 + '\'' +
                ", idRodzajuGrupyKartotekowej2='" + idRodzajuGrupyKartotekowej2 + '\'' +
                ", idRodzajuGrupyKartotekowej3='" + idRodzajuGrupyKartotekowej3 + '\'' +
                ", idRodzajuGrupyKartotekowej4='" + idRodzajuGrupyKartotekowej4 + '\'' +
                ", idRodzajuGrupyKartotekowej5='" + idRodzajuGrupyKartotekowej5 + '\'' +
                ", idRodzajuGrupyKartotekowejX='" + idRodzajuGrupyKartotekowejX + '\'' +
                ", idProperty='" + idProperty + '\'' +
                ", cechy=" + cechy +
                '}';
    }
}
