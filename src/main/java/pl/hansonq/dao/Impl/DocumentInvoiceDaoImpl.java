package pl.hansonq.dao.Impl;

import jdk.nashorn.internal.scripts.JO;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.log4j.Logger;
import pl.hansonq.controllers.MainController;
import pl.hansonq.dao.DocumentInvoiceDao;
import pl.hansonq.models.DocumentInvoiceModel;
import pl.hansonq.models.InvoiceModel.CartModelEdi;
import pl.hansonq.models.InvoiceModel.InvoiceModel;
import pl.hansonq.models.InvoiceModel.OrderModel;
import pl.hansonq.utils.FirebirdConnector;
import pl.hansonq.utils.Settings;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentInvoiceDaoImpl implements DocumentInvoiceDao {
    final static Logger logger = Logger.getLogger(DocumentInvoiceDaoImpl.class);


    FirebirdConnector conn = FirebirdConnector.getInstance();
    Connection conect = conn.getConnection();

    private int blad;
    private int id_poz;


    @Override
    public boolean ImportPz(DocumentInvoiceModel documentInvoiceModel) {
        return false;
    }

    @Override
    public List<String> getZamdost() {
        return null;
    }

    @Override
    public List<Integer> getKontrah(String nip, String iln) {
        PreparedStatement statement2 = null;
        List<Integer> lista = new ArrayList<>();
        int idKontrah = 0;
        int nrKontrah = 0;
        int[] kontrah = null;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select K.ID_KONTRAH, K.NRKONTRAH\n" +
                    "from KONTRAH K\n" +
                    "join DANEKONTRAH DK on DK.ID_KONTRAH = K.ID_KONTRAH\n" +
                    "where K.BAZAKONTRAH = 0 and\n" +
                    "      DK.BAZADANEKONTRAH = 1 and\n" +
                    "      DK.NIPW =?");// trim(replace(?, '-', ''))");
            statement2.setString(1, nip);
            ResultSet rs = statement2.executeQuery();
            // statement2.close();
            while (rs.next()) {
                //  JOptionPane.showMessageDialog(null,"YYEYEYYEE");
                idKontrah = rs.getInt(1);
                nrKontrah = rs.getInt(2);
                lista.add(idKontrah);
                lista.add(nrKontrah);
                //  JOptionPane.showMessageDialog(null, lista.size());
                //  kontrah = new int[]{idKontrah, nrKontrah};

                // System.out.println(lista.get(0)+"  "+lista.get(1));


            }


//            if (rs.getInt(1) == 0) {
//
//                statement2 = conect.prepareStatement("select K.ID_KONTRAH, K.NRKONTRAH\n" +
//                        "from KONTRAH K\n" +
//                        "join DANEKONTRAH DK on DK.ID_KONTRAH = K.ID_KONTRAH\n" +
//                        "\n" +
//                        "where K.BAZAKONTRAH = 0 and\n" +
//                        "      DK.BAZADANEKONTRAH = 1 and\n" +
//                        "      K.GLOBALNRLOKALIZ  = trim(replace(?, '-', ''))");
//                statement2.setString(1, iln);
//                ResultSet rs2 = statement2.executeQuery();
//                //  statement2.close();
//                while (rs2.next()) {
//                    idKontrah = rs.getInt(1);
//                    nrKontrah = rs.getInt(2);
//                    lista.add(idKontrah);
//                    lista.add(nrKontrah);
//                    // JOptionPane.showMessageDialog(null, lista.get(1));
//                    //  kontrah = new int[]{idKontrah, nrKontrah};
//
//                    // System.out.println(lista.get(0)+"  "+lista.get(1));
//
//
//                }
//            }

            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return null;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return null;
        } finally {
            try {
                statement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
            }
            return lista;
        }


    }

    @Override
    public List<Integer> getKontrahILN(String nip, String iln) {
        PreparedStatement statement2 = null;
        List<Integer> lista = new ArrayList<>();
        int idKontrah = 0;
        int nrKontrah = 0;
        int[] kontrah = null;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select K.ID_KONTRAH, K.NRKONTRAH\n" +
                    "from KONTRAH K\n" +
                    "join DANEKONTRAH DK on DK.ID_KONTRAH = K.ID_KONTRAH\n" +
                    "\n" +
                    "where K.BAZAKONTRAH = 0 and\n" +
                    "      DK.BAZADANEKONTRAH = 1 and\n" +
                    "      K.GLOBALNRLOKALIZ  = trim(replace(?, '-', ''))");
            statement2.setString(1, iln);
            ResultSet rs2 = statement2.executeQuery();
            //  statement2.close();
            while (rs2.next()) {
                idKontrah = rs2.getInt(1);
                nrKontrah = rs2.getInt(2);
                lista.add(idKontrah);
                lista.add(nrKontrah);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return null;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return null;
        } finally {
            try {
                statement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
            }
            return lista;
        }


    }

    // Sprawdza czy dokument
    @Override
    public boolean CheckIfExist(DocumentInvoiceModel documentInvoiceModel) {
        return false;
    }

    @Override
    public int GetKartId(String ean) {
        PreparedStatement statement2 = null;
        int idKart = 0;

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select K.ID_KARTOTEKA\n" +
                    "from KARTOTEKA K\n" +
                    "left join EAN E on E.ID_KARTOTEKA = K.ID_KARTOTEKA\n" +
                    "where E.EAN = ?");// trim(replace(?, '-', ''))");
            statement2.setString(1, ean);
            ResultSet rs = statement2.executeQuery();
            // statement2.close();
            while (rs.next()) {
                idKart = rs.getInt(1);
            }

            return idKart;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return 0;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return 0;
        } finally {
            try {
                statement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
            }
            return idKart;
        }

    }

//  1  AID_URZZEWSKAD : Integer - id z tabeli URZZEWSKAD
//  2  AKODURZ : Varchar(15) - kod urządzenia zew.
//  3  ANRUZYT : Varchar(10) - nr użytkownika dodającego dane tabela UZYTKOWNIK pole OZNNRWYDRUZYT
//  4  ANRAKW : Integer - numer akwizytora
//  5  AODB_IDKONTRAH : Varchar(15) - Numer lub Nip lub Id_Kontrahenta (patrz dalszy parametr AJAKINUMERKONTRAH)
//  6  APONIPW : Varchar(10) - wartość dodatkowa w przypadku gdy 2 kontrahentów z tym samym nip-em
//  7  AJAKINUMERKONTRAH : Integer - określa co podano w polu AODB_IDKONTRAH
//        0 - podano numer kontrahenta
//        1 - podano Nip i poNIP
//        2 - podano Id_Kontrahenta
//  8 AODB_DATA : Timestamp - data odebranego dokumentu
//  9  AODB_NAZWADOK : Varchar(10) - nazwa odebranego dokumentu
//  10  AODB_NRDOK : Varchar(25) - numer odebranego dokumentu
//  11  AODB_TERMIN : Timestamp - termin płatności odebranego dokumentu
//  12  AODB_PLATNOSC : Varchar(35) - sposób płatności odebranego dokumentu
//  13  AODB_SUMA : Numeric(18,4) - suma dokumentu
//  14  AODB_ILEPOZ : Integer - ilość pozycji na dokumencie
//  15  AODB_GOTOWKA : Numeric(18,4) - kwota przyjęta gotówką
//  16  AODB_DOT_DATA : Timestamp - data dokumentu, którego dotyczy przyjmowany dokument
//  17  AODB_DOT_NAZWADOK : Varchar(10) - nazwa dokumentu, którego dotyczy przyjmowany dokument
//  18  AODB_DOT_NRDOK : Varchar(25) - numer dokumentu, którego dotyczy przyjmowany dokument
//  19  AODB_UWAGI : Varchar(255) - uwagi do dokumentu
//  20  AODB_DATAOBOW : Timestamp - data obowiązywania dokumentu
//  21  AODB_SPOSDOSTAWY : Varchar(50) - nazwa sposobu dostawy dokumentu
//  22  AODB_CECHA1 : Varchar(35) - wartość dla cechy 1 dla nagłówka dokumentu (patrz cechy w definicji urządzenia zew.)
//  23  AODB_CECHA2 : Varchar(35) - wartość cechy 2
//  24  AODB_CECHA3 : Varchar(35) - wartość cechy 3
//  25  AODB_CECHA4 : Varchar(35) - wartość cechy 4
//  26  AODB_CECHA5 : Varchar(35) - - wartość cechy 5
//  27  AODB_IDNABYWCA : Varchar(15) - identyfikator nabywcy (patrz AODB_IDKONTRAH)
//  28  AODB_IDKONTRAHDOST : Varchar(15) - identyfikator dostawcy (patrz AODB_IDKONTRAH)
//  29  AODB_ANULOWANY : Smallint - czy dokument został anulowany (1 - tak, 0/null - nie)
//  30  AODB_WYDRUKOWANY : Smallint - czy dokument został wydrukowany na zwykłej drukarce (1 - tak, 0/null - nie)
//  31  AODB_FISKWYDRUKOWANY : Smallint - czy dokument był drukowany na drukarce fiskalne (1 - tak, 0/null - nie), parametr uwzględniany tylko na dokumentach podlegających fiskalizacji
//  32  AODB_MAGAZYN : Varchar(40) - nazwa magazynu używana w przerzutach magazynowych (dla MM- jest to magazyn „NA”; dla MM+ jest to magazyn „Z”)
//  33  ANAZWADOK_DOREALIZ : Varchar(10) - nazwa dokumentu do realizacji, parametr ten przyjmuje wartości:
//     -  OFEODB - dla „Ofert dla odbiorców”
//     -  ZOO - dla „Zapytań ofertowych od odbiorców
//  34  AID_WALUTA : waluta dla dokumentów walutowych
//  35  AKURS - w przypadku aktywnego modułu zarządzania produkcją 2.0
//  36  AID_ZLECENIEPARTIAZP :Integer – parametr umożliwia łączenie dokumentów rozchodu magazynowego ze zleceniem produkcyjnym. W zależności od rodzaju aktywnego modułu zarządzania produkcją nowy argument przyjmuje wartość:
//  38  AID_ZLECENIE - w przypadku aktywnego modułu zarządzania produkcją 3.0
//
//    Parametry wyjściowe:
//
//    AID_URZZEWNAGL : Integer – identyfikator rekordu danych w tabeli URZZEWNAGL (ten numer należy zapamiętać do wykorzystania w następnych procedurach)
//    BLAD : Integer – parametr informuje czy wystąpił błąd podczas dodawania nagłówka dokumentu, może przyjmować wartości:
//            0 - nie ma błędu
//        1 - brak w spisie urz. zewnętrznych podanego kodu "aKODURZ"
//            2 - brak w spisie kontrahenta o podanym NIP "aNIPKONTR"
//            3 - brak zadanej definicji dokumentu
//        9 - inny nieznany błąd

    @Override
    public int ImportPzNagl(InvoiceModel invoiceModel) {
        int id_nagl = 0;
        blad = 0;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String timestamp = dateTime.format(formatter);
        //  JOptionPane.showMessageDialog(null, timestamp);
        try {
            CallableStatement statement = conect.prepareCall("{call XXX_LC_URZZEWNAGL_ADD_7(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)}");

            statement.registerOutParameter(37, Types.INTEGER);
            statement.registerOutParameter(38, Types.INTEGER);
            statement.setInt(1, 1);
            statement.setString(2, Settings.getKodUrzZew());
            statement.setNull(3, Types.VARCHAR);
            statement.setNull(4, Types.INTEGER);
            statement.setString(5, invoiceModel.getNip());
            statement.setString(6, "");
            statement.setInt(7, 1);
            statement.setString(8, timestamp);//timestamp
            statement.setString(9, "PZ");
            statement.setString(10, invoiceModel.getInvoiceNumber());
            statement.setNull(11, Types.TIMESTAMP);
            statement.setNull(12, Types.VARCHAR);      //timestamp
            statement.setBigDecimal(13, BigDecimal.valueOf(invoiceModel.getSum()));
            statement.setInt(14, invoiceModel.getPozycje().size());
            statement.setNull(15, Types.NUMERIC);
            statement.setNull(16, Types.TIMESTAMP);  //timestamp
            statement.setNull(17, Types.VARCHAR);
            statement.setNull(18, Types.VARCHAR);
            statement.setNull(19, Types.VARCHAR);
            statement.setNull(20, Types.TIMESTAMP);      //timestamp
            statement.setNull(21, Types.VARCHAR);
            statement.setNull(22, Types.VARCHAR);
            statement.setNull(23, Types.VARCHAR);
            statement.setNull(24, Types.VARCHAR);
            statement.setNull(25, Types.VARCHAR);
            statement.setNull(26, Types.VARCHAR);
            statement.setNull(27, Types.VARCHAR);
            statement.setNull(28, Types.VARCHAR);
            statement.setNull(29, Types.SMALLINT);
            statement.setNull(30, Types.SMALLINT);
            statement.setNull(31, Types.SMALLINT);
            statement.setNull(32, Types.VARCHAR);
            statement.setNull(33, Types.VARCHAR);
            statement.setNull(34, Types.INTEGER);
            statement.setNull(35, Types.NUMERIC);
            statement.setNull(36, Types.INTEGER);

            statement.execute();

            try {
                id_nagl = statement.getInt(37);
                blad = statement.getInt(38);
                // JOptionPane.showMessageDialog(null,id_kartoteka+" ,"+"nowa :"+new_kart);


            } catch (Exception ex) {
                ex.printStackTrace();
                logger.debug(ex.getMessage() + "\nBłąd numer : " + blad);
                id_nagl = 0;

            }
            return id_nagl;

        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
            //   JOptionPane.showMessageDialog(null, "Kartot eka o numerze " + id_kartoteka + " posiada nieuzupełnione wszystkie wymagane pola !", "Bład", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }

        return id_nagl;
    }

    // 1   AID_URZZEWNAGL : Integer - identyfikator nagłówka dokumentu w tabeli URZZEWNAGL, parametr wyjściowy z procedur dodających nagłówek dokumentu
// 2  AKODTOW : Varchar(40) - indeks kartoteki
// 3   AILOSC : Numeric(18,4) - ilość
// 4   ACENA : Numeric(18,4) - cena
// 5   ACENAUZG : Smallint - parametr określa czy cena uzgodniona (wartość 0 = nie, 1 = tak)
// 6   APROCBONIF : Numeric(18,4) - procent bonifikaty
// 7   AODB_UWAGI : Varchar(255) - uwagi do pozycji dokumentu
// 8   AODB_CECHA1 : Varchar(35) - wartość dla cechy 1 dla pozycji dokumentu (patrz cechy w definicji urządzenia zew.)
// 9   AODB_CECHA2 : Varchar(35) - wartość cechy 2
// 10  AODB_CECHA3 : Varchar(35) - wartość cechy 3
// 11  AODB_CENA_BRUTTO : Smallint - parametr określa czy podana cena jest ceną BRUTTO (wartość 0 = nie, 1 = tak)
// 12  AODB_DOT_DATA : Timestamp - data dokumentu, którego dotyczy przyjmowana pozycja, gdy jest pusta sprawdzany jest parametr AODB_DOT_DATA nagłówka dokumentu np. data dokumentu zamówienia od odbiorcy dla dokumentu WZ
// 13  AODB_DOT_NAZWADOK : Varchar(10) - nazwa dokumentu, którego dotyczy przyjmowana pozycja, gdy jest pusta sprawdzany jest parametr AODB_DOT_NAZWADOK nagłówka dokumentu
// 14  AODB_DOT_NRDOK : Varchar(25) - numer dokumentu, którego dotyczy przyjmowana pozycja, gdy jest pusta sprawdzany jest parametr AODB_DOT_NRDOK nagłówka dokumentu
// 15  AODB_DOT_LP : Integer - lp pozycji na dokumencie, którego dotyczy przyjmowana pozycja
// 16  AODB_MAG_OZNNRWYDR : Varchar(10) - oznaczenie na wydruku magazynu, z którego będzie realizowana pozycja
//  17  AODB_DATA_WAZNOSCI : Timestamp - data ważności dostawy
//  18  AODB_RODZAJ_REZERWACJI : Smallint - parametr steruje obsługą rezerwacji:
//    jeżeli będzie miał wartość 0 lub null, to wtedy program nic nie rezerwuje, chyba że włączono odpowiednie opcje w konfiguracji: "Automatycznie przepisz ilość zamawianą do ilości rezerwowanej na magazyn w zamówieniach od odbiorców" lub "Automatycznie przepisz ilość zamawianą do ilości rezerwowanej na magazyn w zamówieniach wewnętrznych"
//    jeżeli będzie miał wartość 1, to program będzie próbował dodać rezerwację stanu magazynowego na ilość przekazaną w parametrze AODB_ILOSC_REZERWACJI, jeżeli stan dostępny na magazynie będzie mniejszy, to zarezerwuje stan dostępny
//    jeżeli będzie miał wartość 2, to program będzie próbował dodać rezerwację dostawy przekazanej w parametrze AODB_ID_DOSTAWA_REZ na ilość przekazaną w parametrze AODB_ILOSC_REZERWACJI, jeżeli stan dostępny na dostawie będzie mniejszy, to zarezerwuje stan dostępny
//    jeżeli będzie miał wartość 3, to wtedy program nic nie rezerwuje, nawet wtedy jeżeli włączono odpowiednie opcje w konfiguracji: "Automatycznie przepisz ilość zamawianą do ilości rezerwowanej na magazyn w zamówieniach od odbiorców" lub "Automatycznie przepisz ilość zamawianą do ilości rezerwowanej na magazyn w zamówieniach wewnętrznych"
//  19  AODB_ILOSC_REZERWACJI : Numeric(18,4) - parametr używany wtedy jak parametr AODB_RODZAJ_REZERWACJI ma wartość 1 lub 2, do określenia ilości do rezerwacji (patrz opis parametru AODB_RODZAJ_REZERWACJI)
//  20 AODB_ID_DOSTAWA_REZ: Integer - parametr używany w dwóch przypadkach:
//    Wtedy jak parametr AODB_RODZAJ_REZERWACJI ma wartość 2 - rezerwacja ilości na dostawie o id_dostawa = AODB_ID_DOSTAWA_REZ (tylko dla zam. odb. i wew.), (patrz opis parametru AODB_RODZAJ_REZERWACJI)
//    Parametr umożliwia wprowadzenie dokumentów rozchodów magazynowych / sprzedaży (z poziomu procedur wbudowanych) z zadaną dostawą do rozchodu. Dodając pozycję dokumentu za pomocą procedury wbudowanej, należy ID zadanej dostawy podać w polu AODB_ID_DOSTAWA_REZ. Realizacja takich dokumentów odbywa się z poziomu menu "Sprzedaż - Obsługa urządzeń zewnętrznych". Wyjątkiem jest sytuacja, w której wprowadzono dokument rozchodu magazynowego / sprzedaży z zadaną dostawą do rozchodu, który realizuje zamówienie od odbiorców posiadający rezerwacje na innej dostawie niż zadano do rozchodu.
//  21 AODB_PROCCLA&nbsp;: Numeric(18,4) - parametr umożliwia wpisanie procentu cła dla pozycji walutowych zamówień od odbiorców i zamówień do dostawców
//   22 ACENA_PRZEPISZ : Smallint – parametr używany podczas realizacji zamówienia od odbiorców z poziomu bufora urządzeń zewnętrznych, umożliwia bezpośrednie przepisanie ceny pozycji. Wcześniej przepisanie wprowadzonej ceny pozycji uzależnione było od parametru wejściowego ACENAUZG (Cena uzgodniona) dla procedur z rodziny URZZEWPOZ_ADD. Dzięki wprowadzonej modyfikacji przepisanie ceny uzależnione będzie od parametru ACENA_PRZEPISZ (wartość 1 = przepisz; 0 = pobierz cenę kartotekową; NULL = cena zależna od parametru ACENAUZG)
//   23 AODB_DOT_LPDOD : Integer - służy do dokładnego wskazania pozycji rozchodu magazynowego na dokumencie zamówienia od odbiorców handlowych jaka ma zostać zrealizowana
//  24  AODB_NRDOSTAWY : Varchar(60) – wypełnienie parametru spowoduje wypełnienie pola dostawy „Nr dostawy/partii” dla dokumentów przychodu
//
//    Parametry wyjściowe:
//
//    AID_URZZEWPOZ : Integer – identyfikator rekordu danych w tabeli URZZEWPOZ
//    BLAD : Integer – parametr informuje czy wystąpił błąd podczas dodawania nagłówka dokumentu, może przyjmować wartości:
//            0 - nie ma błędu
//        1 - brak kartoteki
//        9 - inny nieznany błąd
    @Override
    public int ImportPzPoz(InvoiceModel invoiceModel, CartModelEdi cartModelEdi, int id_urzzewnagl) {
        int id_Nagl = 0;
        int id_poz = 0;
        int blad = 0;
        try {
            CallableStatement statement = conect.prepareCall("{call XXX_LC_URZZEWPOZ_ADD_9(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            statement.registerOutParameter(25, Types.INTEGER);
            statement.registerOutParameter(26, Types.INTEGER);
            statement.setInt(1, id_urzzewnagl);
            statement.setString(2, cartModelEdi.getIndeks());
            statement.setString(3, cartModelEdi.getQuantity());
            statement.setString(4, cartModelEdi.getNetPice());
            statement.setInt(5, 1);
            statement.setString(6, "0.00");
            statement.setString(7, "");
            statement.setString(8, "");//timestamp
            statement.setString(9, "");
            statement.setString(10, "");
            statement.setInt(11, 0);
            statement.setString(12, invoiceModel.getInvoiceDate());      //timestamp
            statement.setString(13, "");
            statement.setString(14, "");
            statement.setInt(15, invoiceModel.getPozycje().size());
            statement.setString(16, "");  //timestamp
            statement.setNull(17, Types.VARCHAR);
            statement.setNull(18, Types.INTEGER);
            statement.setNull(19, Types.NUMERIC);
            statement.setNull(20, Types.INTEGER);      //timestamp
            statement.setNull(21, Types.NUMERIC);
            statement.setInt(22, 1);
            statement.setNull(23, Types.INTEGER);
            statement.setNull(24, Types.VARCHAR);

            ;

            statement.execute();

            try {
                id_poz = statement.getInt(25);
                blad = statement.getInt(26);
                // JOptionPane.showMessageDialog(null,id_kartoteka+" ,"+"nowa :"+new_kart);


            } catch (Exception ex) {
                ex.printStackTrace();
                logger.debug(ex.getMessage() + "\nBłąd numer : " + blad);
                //id_nagl = 0;

            }
            //return id_nagl;

        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
            //   JOptionPane.showMessageDialog(null, "Kartot eka o numerze " + id_kartoteka + " posiada nieuzupełnione wszystkie wymagane pola !", "Bład", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }

        return id_poz;
    }

    @Override
    public boolean PowiazPZ(InvoiceModel invoiceModel, OrderModel orderModel) {

        try {
            // CallableStatement statement = conect.prepareCall("{call XXX_LC_EDI_UTWORZPOW(?,?)}");
            JOptionPane.showMessageDialog(null, "LOL");
            return true;

//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            logger.debug(ex.getMessage());
//            //   JOptionPane.showMessageDialog(null, "Kartot eka o numerze " + id_kartoteka + " posiada nieuzupełnione wszystkie wymagane pola !", "Bład", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<String> getMagazyn() {
        PreparedStatement statement2 = null;
        List<String> magazyny = new ArrayList<>();

        try {
            conect.setAutoCommit(true);

            statement2 = conect.prepareStatement("select M.NAZWAMAG\n" +
                    "from MAGAZYN M ;");
            ResultSet rs = statement2.executeQuery();

            while (rs.next()) {
                magazyny.add(rs.getString(1));
            }

            return magazyny;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return null;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return null;
        } finally {
            try {
                statement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
            }
            return magazyny;
        }

    }

    @Override
    public String GetKartIndeks(String ean) {
        PreparedStatement statement2 = null;
        //PreparedStatement statement3 = null;
        String indeks = "";
        //   int[] kontrah = null;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select K.INDEKS\n" +
                    "from KARTOTEKA K\n" +
                    "left join EAN E on E.ID_KARTOTEKA = K.ID_KARTOTEKA\n" +
                    "where E.EAN =?");// trim(replace(?, '-', ''))");
            statement2.setString(1, ean);
            ResultSet rs = statement2.executeQuery();
            // statement2.close();
            //statement2 = null;

            while (rs.next()) {
                ///   JOptionPane.showMessageDialog(null,indeks);
                indeks = rs.getString(1);
            }
            if ((indeks.equals("")) || (indeks.isEmpty()) || (indeks == null)) {
                statement2 = conect.prepareStatement("select K.INDEKS\n" +
                        "from KARTOTEKA K\n" +
                        "where K.INDEKS =?");// trim(replace(?, '-', ''))");
                statement2.setString(1, ean.trim());
                ResultSet rs2 = statement2.executeQuery();
                // statement2 = null;
                while (rs2.next()) {
                    ///   JOptionPane.showMessageDialog(null,indeks);
                    String indeks2 = rs2.getString(1);

                    return indeks2;
                }
            } else {
                return indeks;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return null;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return null;
        } finally {
            try {
                statement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
            }
            // return indeks;
        }
        return null;
    }

    @Override
    public boolean InsertNewDocumentNumber(InvoiceModel invoiceModel) {
        PreparedStatement statement2 = null;
        String indeks = "";
        //   int[] kontrah = null;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("insert into XXX_LC_EDI_IMPORT ( ID_PZ,ID_URZZEWNAGL, NRDOKZEW, DATADOK)\n" +
                    "  values (gen_id(gen_xxx_lc_edi_import_id,1),?, ?, cast(? as date))");// trim(replace(?, '-', ''))");
            statement2.setInt(1, invoiceModel.getId_urzzew_nagl());
            statement2.setString(2, invoiceModel.getInvoiceNumber());
            //   JOptionPane.showMessageDialog(null, invoiceModel.getInvoiceDate());
            statement2.setString(3, invoiceModel.getInvoiceDate());
            statement2.executeUpdate();
            //  conect.close();


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return false;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return false;
        } finally {
            try {
                statement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
            }
            return false;
        }

    }

    @Override
    public boolean CheckIfDocumentExists(String nrdok) {
        PreparedStatement statement2 = null;
        String urzzewCode = Settings.getKodUrzZew();
        int idUrzZewX = GetUrzzewId(urzzewCode);
        //    JOptionPane.showMessageDialog(null, idUrzZewX);
        //   int[] kontrah = null;
        //  JOptionPane.showMessageDialog(null, idUrzZewX);
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select N.odb_nrdok\n" +
                    "from URZZEWNAGL N\n" +
                    "inner join URZZEW Z on (N.ID_URZZEW = Z.ID_URZZEW)\n" +
                    "where ((Z.ID_URZZEW = ?) and\n" +
                    "      (N.ZREALZIOWANY = 1))and N.ODB_NRDOK =?");// trim(replace(?, '-', ''))");
            statement2.setInt(1, idUrzZewX);
            statement2.setString(2, nrdok);
            ResultSet rs = statement2.executeQuery();
            // statement2.close();
            while (rs.next()) {
                return true;
                //   JOptionPane.showMessageDialog(null, nrdok2);

            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return false;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return false;
//        } finally {
//            try {
//                statement2.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//            }
//            return null;
//        }

        }
        return false;
    }

    @Override
    public boolean CheckIfDocumentExists2(String nrdok) {
        PreparedStatement statement2 = null;
        try {
            conect.setAutoCommit(true);
            statement2 = conect.prepareStatement("select N.ID_NAGL\n" +
                    "from NAGL N\n" +
                    "where N.NRDOKZEW=?");
            statement2.setString(1, nrdok);
            ResultSet rs = statement2.executeQuery();
            while (rs.next()) {
                return true;
                //   JOptionPane.showMessageDialog(null, nrdok2);

            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return false;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return false;
//        } finally {
//            try {
//                statement2.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//            }
//            return null;
//        }

        }
        return false;
    }

    @Override
    public int GetUrzzewId(String code) {
        PreparedStatement statement2 = null;
        int id_Urzzew = 0;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select U.ID_URZZEW\n" +
                    "from URZZEW U\n" +
                    "where U.KODURZ = ?");// trim(replace(?, '-', ''))");
            statement2.setString(1, code);
            ResultSet rs = statement2.executeQuery();

            if (rs.next()) {
                id_Urzzew = rs.getInt(1);


            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());

        }
        return id_Urzzew;
    }

    @Override
    public boolean DeleteFromBufor() {
        PreparedStatement statement2 = null;
        String urzzewCode = Settings.getKodUrzZew();
        int idUrzZewX = GetUrzzewId(urzzewCode);

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("delete from URZZEWNAGL UN\n" +
                    "\n" +
                    "where UN.ZREALZIOWANY = 0 and\n" +
                    "      UN.ID_URZZEW = ?");// trim(replace(?, '-', ''))");
            statement2.setInt(1, idUrzZewX);
            ResultSet rs = statement2.executeQuery();
            // statement2.close();
            if (rs.next()) {
                String olo = rs.getString(1);
                return true;
                //   JOptionPane.showMessageDialog(null, nrdok2);

            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return false;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return false;
//        } finally {
//            try {
//                statement2.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//            }
//            return null;
//        }

        }
        return false;
    }

    @Override
    public int GetIdNaglPZ(String number) {
        PreparedStatement statement2 = null;
        int id_nagl = 0;

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select N.ID_NAGL\n" +
                    "from NAGL N\n" +
                    "where N.NRDOKZEW = ? and\n" +
                    "      N.DATADOK = current_date  ");// trim(replace(?, '-', ''))");
            statement2.setString(1, number);
            ResultSet rs = statement2.executeQuery();
            // statement2.close();
            while (rs.next()) {
                id_nagl = rs.getInt(1);


            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());

            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;


        }
        return id_nagl;
    }

    @Override
    public int GetIdNaglZAMDOST(String number, int idKontrah) {
        PreparedStatement statement2 = null;
        int id_nagl = 0;

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("select N.ID_NAGL\n" +
                    "from NAGL N\n" +
                    "where N.NRDOKWEW LIKE ? and N.ID_KONTRAH=? and \n" +
                    "      extract(year from N.DATADOK) = 2018");// trim(replace(?, '-', ''))");
            statement2.setString(1, number + "%");
            statement2.setInt(2, idKontrah);
            ResultSet rs = statement2.executeQuery();
            // statement2.close();
            while (rs.next()) {
                id_nagl = rs.getInt(1);

                //   JOptionPane.showMessageDialog(null, nrdok2);

            }
            return id_nagl;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return 0;
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
            return 0;
//        } finally {
//            try {
//                statement2.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//            }
//            return null;
//        }

        }
        //  return id_nagl;
    }
}


