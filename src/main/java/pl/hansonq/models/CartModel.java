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
    private String jestUwaga;
    private String ostrzezenie;
    private String jestOstrzezenie;
    private String kgo;
    private String waga;
    private String kartotekiPowiazane;
    private String zamienniki;
    private String idPriorytet;
    private String iloscOpakowanieZbiorcze1;
    private String iloscOpakowanieZbiorcze2;
    private String iloscOpakowanieZbiorcze3;
    private String idStawkiVat;
    private String opis;
    private String stronaWWW;
    private String grupaRabatowa;
    private String grupaBonusowa;
    private String kodProducent;

    private String kodGrupaKartotekowa1;
    private String kodGrupaKartotekowa2;
    private String kodGrupaKartotekowa3;
    private String kodGrupaKartotekowa4;
    private String kodGrupaKartotekowa5;
    private String kodGrupaKartotekowaX;

    private String idDodatkowegoOpakowanieZbiorczegoEAN;
    private String idOpakowaniaZbiorczego1;
    private String idOpakowaniaZbiorczego2;
    private String idOpakowaniaZbiorczego3;
    private String idTypOpisu;
   private String idPodstawowejStawkiVat;
    private String idRodzajuGrupyKartotekowejProducent;
    private String idRodzajuGrupyKartotekowej;
    private String idRodzajuGrupyKartotekowej1;
    private String idRodzajuGrupyKartotekowej2;
    private String idRodzajuGrupyKartotekowej3;
    private String idRodzajuGrupyKartotekowej4;
    private String idRodzajuGrupyKartotekowej5;
    private String idRodzajuGrupyKartotekowejX;
    private String idCechy;
    private String wartoscCechy;

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

    public String getJestUwaga() {
        return jestUwaga;
    }

    public void setJestUwaga(String jestUwaga) {
        this.jestUwaga = jestUwaga;
    }

    public String getOstrzezenie() {
        return ostrzezenie;
    }

    public void setOstrzezenie(String ostrzezenie) {
        this.ostrzezenie = ostrzezenie;
    }

    public String getJestOstrzezenie() {
        return jestOstrzezenie;
    }

    public void setJestOstrzezenie(String jestOstrzezenie) {
        this.jestOstrzezenie = jestOstrzezenie;
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

    public String getIloscOpakowanieZbiorcze1() {
        return iloscOpakowanieZbiorcze1;
    }

    public void setIloscOpakowanieZbiorcze1(String iloscOpakowanieZbiorcze1) {
        this.iloscOpakowanieZbiorcze1 = iloscOpakowanieZbiorcze1;
    }

    public String getIloscOpakowanieZbiorcze2() {
        return iloscOpakowanieZbiorcze2;
    }

    public void setIloscOpakowanieZbiorcze2(String iloscOpakowanieZbiorcze2) {
        this.iloscOpakowanieZbiorcze2 = iloscOpakowanieZbiorcze2;
    }

    public String getIloscOpakowanieZbiorcze3() {
        return iloscOpakowanieZbiorcze3;
    }

    public void setIloscOpakowanieZbiorcze3(String iloscOpakowanieZbiorcze3) {
        this.iloscOpakowanieZbiorcze3 = iloscOpakowanieZbiorcze3;
    }

    public String getIdPodstawowejStawkiVat() {
        return idPodstawowejStawkiVat;
    }

    public void setIdPodstawowejStawkiVat(String idPodstawowejStawkiVat) {
        this.idPodstawowejStawkiVat = idPodstawowejStawkiVat;
    }

    public String getIdStawkiVat() {
        return idStawkiVat;
    }

    public void setIdStawkiVat(String idStawkiVat) {
        this.idStawkiVat = idStawkiVat;
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

    public String getKodProducent() {
        return kodProducent;
    }

    public void setKodProducent(String kodProducent) {
        this.kodProducent = kodProducent;
    }


    public String getKodGrupaKartotekowa1() {
        return kodGrupaKartotekowa1;
    }

    public void setKodGrupaKartotekowa1(String kodGrupaKartotekowa1) {
        this.kodGrupaKartotekowa1 = kodGrupaKartotekowa1;
    }

    public String getKodGrupaKartotekowa2() {
        return kodGrupaKartotekowa2;
    }

    public void setKodGrupaKartotekowa2(String kodGrupaKartotekowa2) {
        this.kodGrupaKartotekowa2 = kodGrupaKartotekowa2;
    }

    public String getKodGrupaKartotekowa3() {
        return kodGrupaKartotekowa3;
    }

    public void setKodGrupaKartotekowa3(String kodGrupaKartotekowa3) {
        this.kodGrupaKartotekowa3 = kodGrupaKartotekowa3;
    }

    public String getKodGrupaKartotekowa4() {
        return kodGrupaKartotekowa4;
    }

    public void setKodGrupaKartotekowa4(String kodGrupaKartotekowa4) {
        this.kodGrupaKartotekowa4 = kodGrupaKartotekowa4;
    }

    public String getKodGrupaKartotekowa5() {
        return kodGrupaKartotekowa5;
    }

    public void setKodGrupaKartotekowa5(String kodGrupaKartotekowa5) {
        this.kodGrupaKartotekowa5 = kodGrupaKartotekowa5;
    }

    public String getKodGrupaKartotekowaX() {
        return kodGrupaKartotekowaX;
    }

    public void setKodGrupaKartotekowaX(String kodGrupaKartotekowaX) {
        this.kodGrupaKartotekowaX = kodGrupaKartotekowaX;
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

//    public String getIdPodstawowejStawkiVat() {
//        return idPodstawowejStawkiVat;
//    }
//
//    public void setIdPodstawowejStawkiVat(String idPodstawowejStawkiVat) {
//        this.idPodstawowejStawkiVat = idPodstawowejStawkiVat;
//    }

    public String getIdRodzajuGrupyKartotekowejProducent() {
        return idRodzajuGrupyKartotekowejProducent;
    }

    public void setIdRodzajuGrupyKartotekowejProducent(String idRodzajuGrupyKartotekowejProducent) {
        this.idRodzajuGrupyKartotekowejProducent = idRodzajuGrupyKartotekowejProducent;
    }

    public String getIdRodzajuGrupyKartotekowej() {
        return idRodzajuGrupyKartotekowej;
    }

    public void setIdRodzajuGrupyKartotekowej(String idRodzajuGrupyKartotekowej) {
        this.idRodzajuGrupyKartotekowej = idRodzajuGrupyKartotekowej;
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

    public String getIdCechy() {
        return idCechy;
    }

    public void setIdCechy(String idCechy) {
        this.idCechy = idCechy;
    }

    public String getWartoscCechy() {
        return wartoscCechy;
    }

    public void setWartoscCechy(String wartoscCechy) {
        this.wartoscCechy = wartoscCechy;
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
                ", jestUwaga='" + jestUwaga + '\'' +
                ", ostrzezenie='" + ostrzezenie + '\'' +
                ", jestOstrzezenie='" + jestOstrzezenie + '\'' +
                ", kgo='" + kgo + '\'' +
                ", waga='" + waga + '\'' +
                ", kartotekiPowiazane='" + kartotekiPowiazane + '\'' +
                ", zamienniki='" + zamienniki + '\'' +
                ", idPriorytet='" + idPriorytet + '\'' +
                ", iloscOpakowanieZbiorcze1='" + iloscOpakowanieZbiorcze1 + '\'' +
                ", iloscOpakowanieZbiorcze2='" + iloscOpakowanieZbiorcze2 + '\'' +
                ", iloscoOpakowanieZbiorcze3='" + iloscOpakowanieZbiorcze3 + '\'' +
                ", idStawkiVat='" + idStawkiVat + '\'' +
                ", opis='" + opis + '\'' +
                ", stronaWWW='" + stronaWWW + '\'' +
                ", grupaRabatowa='" + grupaRabatowa + '\'' +
                ", grupaBonusowa='" + grupaBonusowa + '\'' +
                ", kodProducent='" + kodProducent + '\'' +
                ", kodGgrupaKartotekowa1='" + kodGrupaKartotekowa1 + '\'' +
                ", kodGrupaKartotekowa2='" + kodGrupaKartotekowa2 + '\'' +
                ", kodGrupaKartotekowa3='" + kodGrupaKartotekowa3 + '\'' +
                ", kodGrupaKartotekowa4='" + kodGrupaKartotekowa4 + '\'' +
                ", kodGrupaKartotekowa5='" + kodGrupaKartotekowa5 + '\'' +
                ", kodGrupaKartotekowaX='" + kodGrupaKartotekowaX + '\'' +

                ", idDodatkowegoOpakowanieZbiorczegoEAN='" + idDodatkowegoOpakowanieZbiorczegoEAN + '\'' +
                ", idOpakowaniaZbiorczego1='" + idOpakowaniaZbiorczego1 + '\'' +
                ", idOpakowaniaZbiorczego2='" + idOpakowaniaZbiorczego2 + '\'' +
                ", idOpakowaniaZbiorczego3='" + idOpakowaniaZbiorczego3 + '\'' +
                ", idTypOpisu='" + idTypOpisu + '\'' +

                ", idRodzajuGrupyKartotekowejProducent='" + idRodzajuGrupyKartotekowejProducent + '\'' +
                ", idRodzajuGrupyKartotekowej='" + idRodzajuGrupyKartotekowej + '\'' +
                ", idRodzajuGrupyKartotekowej1='" + idRodzajuGrupyKartotekowej1 + '\'' +
                ", idRodzajuGrupyKartotekowej2='" + idRodzajuGrupyKartotekowej2 + '\'' +
                ", idRodzajuGrupyKartotekowej3='" + idRodzajuGrupyKartotekowej3 + '\'' +
                ", idRodzajuGrupyKartotekowej4='" + idRodzajuGrupyKartotekowej4 + '\'' +
                ", idRodzajuGrupyKartotekowej5='" + idRodzajuGrupyKartotekowej5 + '\'' +
                ", idRodzajuGrupyKartotekowejX='" + idRodzajuGrupyKartotekowejX + '\'' +
                ", idCechy='" + idCechy + '\'' +
                ", wartoscCechy='" + wartoscCechy + '\'' +
                '}';
    }
}
