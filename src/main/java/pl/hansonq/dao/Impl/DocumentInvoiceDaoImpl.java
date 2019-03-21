package pl.hansonq.dao.Impl;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import pl.hansonq.dao.DocumentInvoiceDao;
import pl.hansonq.models.CartModel;
import pl.hansonq.models.InvoiceModel.CartModelEdi;
import pl.hansonq.models.InvoiceModel.InvoiceModel;
import pl.hansonq.utils.FirebirdConnector;
import pl.hansonq.utils.Preferences;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DocumentInvoiceDaoImpl implements DocumentInvoiceDao {
    final static Logger logger = Logger.getLogger(DocumentInvoiceDaoImpl.class);

    public DocumentInvoiceDaoImpl() {
        DOMConfigurator.configure("log4j.xml");
    }

    FirebirdConnector conn = FirebirdConnector.getInstance();
    Connection conect = conn.getConnection();

    private int blad;
    private int id_poz;
    private int id_kartoteka;
    public static int new_kart;
    private boolean addNewCart = false;

    @Override
    public List<Integer> getKontrah(String nip, String iln) {
        CallableStatement statement2 = null;
        List<Integer> lista = new ArrayList<>();
        int idKontrah = 0;
        int nrKontrah = 0;
        int[] kontrah = null;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareCall("{call XXX_LC_EDI_GETKONTRAH(?,?,?,?)}");// trim(replace(?, '-', ''))");
            statement2.registerOutParameter(3, Types.INTEGER);
            statement2.registerOutParameter(4, Types.INTEGER);
            statement2.setString(1, nip);
            statement2.setString(2, iln);
            statement2.execute();
            idKontrah = statement2.getInt(3);
            nrKontrah = statement2.getInt(4);
            lista.add(idKontrah);
            lista.add(nrKontrah);

//JOptionPane.showMessageDialog(null, lista.get(0)+" "+ lista.get(1));
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
        //   String timestamp = invoiceModel.getInvoiceDate();
        try {
            CallableStatement statement = conect.prepareCall("{call XXX_LC_EDI_URZZEWNAGL_ADD_7(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            statement.registerOutParameter(37, Types.INTEGER);
            statement.registerOutParameter(38, Types.INTEGER);
            statement.setInt(1, 1);
            statement.setString(2, Preferences.getPreferences().getKodUrzZew());
            statement.setNull(3, Types.VARCHAR);
            statement.setNull(4, Types.INTEGER);
            statement.setString(5, invoiceModel.getNip());
            statement.setString(6, "");
            statement.setInt(7, 1);
            statement.setString(8, timestamp);//timestamp
            statement.setString(9, "FVZAK");
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
            CallableStatement statement = conect.prepareCall("{call XXX_LC_EDI_URZZEWPOZ_ADD_9(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?  )}");

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
            // JOptionPane.showMessageDialog(null, cartModelEdi);

            statement.execute();

            try {

                id_poz = statement.getInt(25);
                blad = statement.getInt(26);
                //    JOptionPane.showMessageDialog(null,id_poz+" ,"+blad);


            } catch (Exception ex) {
                ex.printStackTrace();
                logger.debug(ex.getMessage() + "\nBłąd numer : " + blad + ", " + id_poz);
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
    public boolean PowiazPZ(int zam, int pz) {

        try {
            conect.setAutoCommit(true);
            CallableStatement statement = conect.prepareCall("{call XXX_LC_EDI_UTWORZPOW(?,?)}");
            statement.setInt(1, zam);
            statement.setInt(2, pz);
            statement.executeUpdate();
            return true;

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

        return false;
    }

    @Override
    public List<String> getMagazyn() {
        PreparedStatement statement2 = null;
        List<String> magazyny = new ArrayList<>();
        //  magazyny.add("MAGAZYN");
        try {
            conect.setAutoCommit(true);

            statement2 = conect.prepareStatement("select M.NAZWAMAG\n" +
                    "from MAGAZYN M where M.AKTYWNY = 1");
            ResultSet rs = statement2.executeQuery();
            magazyny.clear();
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
    public String GetKartIndeksByEan(String ean) {
        CallableStatement statement2 = null;
        String indeks = "";
        try {
            conect.setAutoCommit(true);
            statement2 = conect.prepareCall("{call XXX_LC_EDI_GETKARTINDEKS (?,?)}");// trim(replace(?, '-', ''))");
            statement2.registerOutParameter(2, Types.VARCHAR);
            statement2.setString(1, ean);
            statement2.execute();
            indeks = statement2.getString(2);
            return indeks;
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
        String urzzewCode = Preferences.getPreferences().getKodUrzZew();
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
                    "      ((N.ZREALZIOWANY = 1)or(N.ZREALZIOWANY = 0)))and N.ODB_NRDOK =?");// trim(replace(?, '-', ''))");
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
        String urzzewCode = Preferences.getPreferences().getKodUrzZew();

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
        CallableStatement statement2 = null;
        int id_nagl = 0;

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareCall("{call XXX_LC_EDI_GETPZ(?,?)}");
            statement2.registerOutParameter(2, Types.INTEGER);// trim(replace(?, '-', ''))");
            statement2.setString(1, number);
            statement2.execute();
            id_nagl = statement2.getInt(2);
            return id_nagl;
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
        CallableStatement statement2 = null;
        int id_nagl = 0;

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareCall("{call XXX_LC_EDI_GETZAMDOST(?,?,?)}");
            statement2.registerOutParameter(3, Types.INTEGER);// trim(replace(?, '-', ''))");
            statement2.setString(1, number);
            statement2.setInt(2, idKontrah);
            //  ResultSet rs = statement2.executeQuery();
            statement2.execute();
            id_nagl = statement2.getInt(3);
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

    @Override
    public List<String> Kartoteki(String nazwa) {
        PreparedStatement statement = null;
        List<String> lista = new ArrayList<>();

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select K.NAZWADL\n" +
                    "  from KARTOTEKA K\n" +
                    "  where upper(K.nazwadl) like '%'||upper(?)||'%'");
            statement.setString(1, nazwa);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }
            return lista;
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
        return null;
    }

    @Override
    public String GetKartIndeksPSB(String numerPSB) {
        return null;
    }

    @Override
    public String GetKartIndeksByName(String kartName) {
        PreparedStatement statement = null;
        String kartIndeks = "";

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select first 1 K.INDEKS\n" +
                    "  from KARTOTEKA K\n" +
                    "  where upper(K.nazwadl) = upper(?)");
            statement.setString(1, kartName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kartIndeks = rs.getString(1);
            }
            //JOptionPane.showMessageDialog(null,kartIndeks);
            return kartIndeks;
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
        return null;
    }

    @Override
    public String GetKartIndeksByIndeks(String indeks) {
        PreparedStatement statement = null;
        String kartIndeks = "";

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select first 1 K.INDEKS\n" +
                    "  from KARTOTEKA K\n" +
                    "  where upper(K.INDEKS) = upper(?)");
            statement.setString(1, indeks);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kartIndeks = rs.getString(1);
            }
            return kartIndeks;
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
        return null;
    }

    @Override
    public String GetKartIndeksBySupplierCode(String code) {
        PreparedStatement statement = null;
        String kartIndeks = "";
        int idCechyPsb = Integer.valueOf(Preferences.getPreferences().getIdCechaPSB());

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select FIRST 1 K.INDEKS\n" +
                    "from KARTOTEKA K\n" +
                    "left join WYSTCECHKART WK on WK.ID_KARTOTEKA = K.ID_KARTOTEKA\n" +
                    "where WK.ID_CECHADOKK = ? and\n" +
                    "      (WK.WARTOSC is not null) and\n" +
                    "      (WK.WARTOSC <> '') and\n" +
                    "      upper(WK.WARTOSC) = upper(?) ");
            statement.setInt(1, idCechyPsb);
            statement.setString(2, code);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kartIndeks = rs.getString(1);
            }
            return kartIndeks;
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
        return null;
    }

    @Override
    public String GetKartNameById(int idKart) {
        PreparedStatement statement = null;
        String kartName = "";

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select FIRST 1 K.NAZWADL\n" +
                    "from KARTOTEKA K\n" +
                    "where K.ID_KARTOTEKA = ?");
            statement.setInt(1, idKart);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kartName = rs.getString(1);
            }
            return kartName;
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
        return null;
    }

    @Override
    public int GetKartIdByIndeks(String indeks) {
        PreparedStatement statement = null;
        int kartId = 0;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select K.ID_KARTOTEKA\n" +
                    "from KARTOTEKA K\n" +
                    "where upper(K.INDEKS)=upper(?)");
            statement.setString(1, indeks);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kartId = rs.getInt(1);
            }
            return kartId;
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
        return 0;
    }

    @Override
    public boolean UpdateSupplierNumber(int idKart, String wartosc) {
        PreparedStatement statement2 = null;
        int idCechyPsb = Integer.valueOf(Preferences.getPreferences().getIdCechaPSB());
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("update or insert into WYSTCECHKART (ID_KARTOTEKA, ID_CECHADOKK, WARTOSC)\n" +
                    "values (?, ?, ?)\n" +
                    "matching (ID_KARTOTEKA, ID_CECHADOKK)");// trim(replace(?, '-', ''))");
            statement2.setInt(1, idKart);
            statement2.setInt(2, idCechyPsb);
            //   JOptionPane.showMessageDialog(null, invoiceModel.getInvoiceDate());
            statement2.setString(3, wartosc);
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
    public boolean UpdateEan(int idKart, String ean) {
        CallableStatement statement2 = null;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareCall("{call XXX_LC_EDI_ADD_EAN(?,?) }");
            statement2.setInt(1, idKart);
            statement2.setString(2, ean);
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
    public String UnitOfMeasure(int idKart) {
        PreparedStatement statement = null;
        String unit = "";
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select first 1 JM1.JM\n" +
                    "from KARTOTEKA K\n" +
                    "join JM JM1 on JM1.ID_JM = K.ID_JM\n" +
                    "where K.ID_KARTOTEKA=?   ");
            statement.setInt(1, idKart);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                unit = rs.getString(1);
            }
            return unit;
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
        return null;
    }

    @Override
    public boolean InsertEan(int idKart) {
        return false;
    }

    @Override
    public boolean UpdateNaglUzytkownik(int idNagl, int idUser) {
        PreparedStatement statement2 = null;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareStatement("update nagl n set n.id_uzytkownik=?" +
                    "where n.id_nagl=?)");// trim(replace(?, '-', ''))");
            statement2.setInt(1, idUser);
            statement2.setInt(2, idNagl);
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
    public int GetIdNagl(String nrdok) {
        PreparedStatement statement = null;
        int idNagl = 0;
        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement = conect.prepareStatement("select first 1 N.ID_NAGL\n" +
                    "from NAGL N\n" +
                    "where upper(N.NRDOKZEW)=upper(?)");
            statement.setString(1, nrdok);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                idNagl = rs.getInt(1);
            }
            return idNagl;
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
        return 0;
    }


    @Override
    public String NewKart(CartModel cartModel, int punkt) {
        CallableStatement statement = null;
        String ean = "";
        ResultSet rs = null;
        try {
            conect.setAutoCommit(true);
            conect.setTransactionIsolation(1);

            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTOTEK4(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.registerOutParameter(19, Types.INTEGER);
            statement.registerOutParameter(20, Types.INTEGER);

            if ((cartModel.getIndeks() == null) || (cartModel.getIndeks().isEmpty())) {
                statement.setNull(1, Types.VARCHAR);

            } else {
                statement.setString(1, cartModel.getIndeks());

            }

            if ((cartModel.getIdentyfikator() != null) && (!cartModel.getIdentyfikator().isEmpty())) {
                statement.setString(2, cartModel.getIdentyfikator());


            }
            if ((cartModel.getIdentyfikator() == null) || (cartModel.getIdentyfikator().isEmpty())) {
                statement.setNull(2, Types.VARCHAR);

            }
            if ((cartModel.getNazwaInternetowa() != null) && (!cartModel.getNazwaInternetowa().isEmpty())) {

                statement.setString(3, cartModel.getNazwaInternetowa());
            }
            if ((cartModel.getNazwaInternetowa() == null) || (cartModel.getNazwaInternetowa().isEmpty())) {

                statement.setNull(3, Types.VARCHAR);

            }
            if ((cartModel.getNazwaSystemowa() != null) && (!cartModel.getNazwaSystemowa().isEmpty())) {

                statement.setString(4, cartModel.getNazwaSystemowa());
            }
            if ((cartModel.getNazwaSystemowa() == null) || (cartModel.getNazwaSystemowa().isEmpty())) {

                statement.setNull(4, Types.VARCHAR);

            }
            if ((cartModel.getJednostka() != null) && (!cartModel.getJednostka().isEmpty())) {

                statement.setString(5, cartModel.getJednostka());
            }
            if ((cartModel.getJednostka() == null) || (cartModel.getJednostka().isEmpty())) {
                statement.setNull(5, Types.VARCHAR);

            }
            //   JOptionPane.showMessageDialog(null,cartModel.getJednostka());
            if ((cartModel.getKodEanDomyslny() != null) && (!cartModel.getKodEanDomyslny().isEmpty())) {
                statement.setString(6, cartModel.getKodEanDomyslny());
                ean = cartModel.getKodEanDomyslny();
            }
            if ((cartModel.getKodEanDomyslny() == null) || (cartModel.getKodEanDomyslny().isEmpty())) {
                statement.setNull(6, Types.VARCHAR);

            }
            //  JOptionPane.showMessageDialog(null,cartModel.getJednostka());
            if ((cartModel.getIdDodatkowegoOpakowanieZbiorczegoEAN() == null) || (cartModel.getIdDodatkowegoOpakowanieZbiorczegoEAN().isEmpty())) {
                statement.setNull(7, Types.VARCHAR);

            } else {
                statement.setString(7, cartModel.getIdDodatkowegoOpakowanieZbiorczegoEAN());

            }
            if ((cartModel.getDodatkowyEan() == null) || (cartModel.getDodatkowyEan().isEmpty())) {
                statement.setNull(8, Types.VARCHAR);

            } else {
                statement.setString(8, cartModel.getDodatkowyEan());
            }
//            if (cartModel.getCenaHurtowa() == null) {
//                //  BigDecimal b1 = new BigDecimal("0.0000");
//                statement.setDouble(9, 0.0000);
//            } else {
//
////                BigDecimal b1 = new BigDecimal(cartModel.getCenaHurtowa());
//
//                statement.setDouble(9, Double.parseDouble(cartModel.getCenaHurtowa().replace(",", ".")));
//            }
//
//            if (cartModel.getCenaSklepInternetowy() == null) {
//
//                statement.setDouble(10, 0.0000);
//            } else {
//                statement.setDouble(10, (Double.parseDouble(cartModel.getCenaSklepInternetowy().replace(",", "."))));
//            }
//            if (cartModel.getCenaDlaParagonu() == null) {
//
//                statement.setDouble(11, 0.0000);
//            } else {
//                statement.setDouble(11, Double.parseDouble(cartModel.getCenaDlaParagonu().replace(",", ".")));
//            }
            if ((cartModel.getOstatniaCena() == null) || (cartModel.getOstatniaCena().isEmpty())) {

                statement.setNull(9, Types.VARCHAR);
            }
            if ((cartModel.getOstatniaCena() != null) && (!cartModel.getOstatniaCena().isEmpty())) {// && (cartModel.getGrupaBonusowa().isEmpty()) || (cartModel.getGrupaRabatowa().isEmpty()) || (cartModel.getGrupaBonusowa() == null) || (cartModel.getGrupaRabatowa() == null)) {
                statement.setString(9, cartModel.getOstatniaCena());
            } else {
                statement.setNull(9, Types.VARCHAR);
            }
            if ((cartModel.getUwagi() == null) || (cartModel.getUwagi().isEmpty())) {
                statement.setString(10, null);
                statement.setInt(15, 0);
            } else {
                statement.setString(10, cartModel.getUwagi());
                statement.setInt(15, 1);
            }
            if ((cartModel.getOstrzezenie() == null) || (cartModel.getOstrzezenie().isEmpty())) {
                statement.setString(11, null);
                statement.setInt(16, 0);
            } else {
                statement.setString(11, cartModel.getOstrzezenie());
                statement.setInt(16, 1);
            }
//            if (cartModel.getKgo() == null) {
//                statement.setDouble(15, 0);
//            } else {
//                statement.setDouble(15, Double.valueOf(cartModel.getKgo()));
//            }
//            if (cartModel.getWaga() == null) {
//                statement.setString(16, null);
//            } else {
//                statement.setString(16, cartModel.getWaga());
//            }
            if ((cartModel.getIdPriorytet() == null) || (cartModel.getIdPriorytet().isEmpty())) {
                statement.setString(12, "0");
            } else {
                statement.setString(12, cartModel.getIdPriorytet());
            }
//            if (cartModel.getJestUwaga() == null) {
//                statement.setInt(18, 0);
//            } else {
//                statement.setInt(18, 1);
//            }
//            if (cartModel.getJestOstrzezenie() == null) {
//                statement.setInt(19, 0);
//            } else {
//                statement.setInt(19, 1);
//            }
//            if (cartModel.getIdOpakowaniaZbiorczego1() == null) {
//                statement.setNull(20, Types.INTEGER);
//            } else {
//                statement.setInt(20, Integer.valueOf(cartModel.getIdOpakowaniaZbiorczego1()));
//            }
//            if (cartModel.getOpakowanieZbiorcze1() == null) {
//                statement.setNull(21, Types.NUMERIC);
//            } else {
//                statement.setDouble(21, Double.valueOf(cartModel.getOpakowanieZbiorcze1()));
//            }
//            if (cartModel.getIdOpakowaniaZbiorczego2() == null) {
//                statement.setNull(22, Types.INTEGER);
//            } else {
//                statement.setInt(22, Integer.valueOf(cartModel.getIdOpakowaniaZbiorczego2()));
//            }
//            if (cartModel.getOpakowanieZbiorcze2() == null) {
//                statement.setNull(23, Types.NUMERIC);
//            } else {
//                statement.setDouble(23, Double.valueOf(cartModel.getOpakowanieZbiorcze2()));
//            }
//            if (cartModel.getIdOpakowaniaZbiorczego3() == null) {
//                statement.setNull(23, Types.INTEGER);
//            } else {
//                statement.setInt(23, Integer.valueOf(cartModel.getIdOpakowaniaZbiorczego3()));
//            }
//            if (cartModel.getOpakowanieZbiorcze3() == null) {
//                statement.setNull(25, Types.NUMERIC);
//            } else {
//                statement.setDouble(25, Double.valueOf(cartModel.getOpakowanieZbiorcze3()));
//            }
//            if (cartModel.getIdTypOpisu() == null) {
//                statement.setNull(26, Types.INTEGER);
//            } else {
//                statement.setInt(26, Integer.valueOf(cartModel.getIdTypOpisu()));
//            }
//            if (cartModel.getOpis() == null) {
//                statement.setString(27, cartModel.getOpis());
//            } else {
//                statement.setNull(27, Types.VARCHAR);
//            }
            if ((cartModel.getStronaWWW() == null) || (cartModel.getStronaWWW().isEmpty())) {
                statement.setNull(13, Types.VARCHAR);
            } else {
                statement.setString(13, cartModel.getStronaWWW());
            }

//           if (cartModel.getIdRodzajuGrupyKartotekowejProducent() == null) {
//                statement.setInt(14, 2);
//            } else{
//                statement.setInt(14, Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowejProducent()));
//            }

            if ((cartModel.getKodProducent() == null) || (cartModel.getKodProducent().isEmpty())) {
                statement.setNull(14, Types.VARCHAR);
                //      JOptionPane.showMessageDialog(null,cartModel.getIndeks());
            } else {
                statement.setString(14, cartModel.getKodProducent());
            }

//            if (cartModel.getIdRodzajuGrupyKartotekowej1() == null) {
//                statement.setNull(31, Types.INTEGER);
//            } else {
//                statement.setInt(31, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej1())));
//            }
//            if (cartModel.getGrupaKartotekowa1() == null) {
//                statement.setNull(32, Types.VARCHAR);
//            } else {
//                statement.setString(32, (cartModel.getGrupaKartotekowa1()));
//            }
//            if (cartModel.getIdRodzajuGrupyKartotekowej2() == null) {
//                statement.setNull(33, Types.INTEGER);
//            } else {
//                statement.setInt(33, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej2())));
//            }
//            if (cartModel.getGrupaKartotekowa2() == null) {
//                statement.setNull(34, Types.VARCHAR);
//            } else {
//                statement.setString(34, (cartModel.getGrupaKartotekowa2()));
//            }
//            if (cartModel.getIdRodzajuGrupyKartotekowej3() == null) {
//                statement.setNull(35, Types.INTEGER);
//            } else {
//                statement.setInt(35, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej3())));
//            }
//            if (cartModel.getGrupaKartotekowa3() == null) {
//                statement.setNull(36, Types.VARCHAR);
//            } else {
//                statement.setString(36, (cartModel.getGrupaKartotekowa3()));
//            }
//            if (cartModel.getIdRodzajuGrupyKartotekowej4() == null) {
//                statement.setNull(37, Types.INTEGER);
//            } else {
//                statement.setInt(37, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej4())));
//            }
//            if (cartModel.getGrupaKartotekowa4() == null) {
//                statement.setNull(38, Types.VARCHAR);
//            } else {
//                statement.setString(38, (cartModel.getGrupaKartotekowa4()));
//            }
//            if (cartModel.getIdRodzajuGrupyKartotekowej5() == null) {
//                statement.setNull(39, Types.INTEGER);
//            } else {
//                statement.setInt(39, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej5())));
//            }
//            if (cartModel.getGrupaKartotekowa5() == null) {
//                statement.setNull(40, Types.VARCHAR);
//            } else {
//                statement.setString(40, (cartModel.getGrupaKartotekowa5()));
//            }
            if ((cartModel.getIdStawkiVat() == null) || (cartModel.getIdStawkiVat().isEmpty())) {
                statement.setString(17, "1");
            } else {
                statement.setString(17, cartModel.getIdStawkiVat());
            }

            statement.setInt(18, punkt);
            statement.execute();
            //
            // JOptionPane.showMessageDialog(null, cartModel.getIndeks());


        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage() + " - dotyczy kartoteki :" + cartModel.getIndeks());

        }

        id_kartoteka = 0;
        try {
            id_kartoteka = statement.getInt(19);
            new_kart = statement.getInt(20);
            //   JOptionPane.showMessageDialog(null,id_kartoteka+" ,"+"nowa :"+new_kart);


        } catch (Exception ex) {
            ex.printStackTrace();
            //logger.debug(ex.getMessage());
            id_kartoteka = 0;

        }

        // statement.close();
        if (id_kartoteka != 0) {
//JOptionPane.showMessageDialog(null,id_kartoteka);
            ean = cartModel.getKodEanDomyslny();

//            PreparedStatement statement2 = null;
//            String unit = "";
//            try {
//                conect.setAutoCommit(true);
//                //   conn.connectionTest();
//                statement2 = conect.prepareStatement("select first 1 JM1.JM\n" +
//                        "from KARTOTEKA K\n" +
//                        "join JM JM1 on JM1.ID_JM = K.ID_JM\n" +
//                        "where K.ID_KARTOTEKA=?   ");
//         //       statement.setInt(1, idKart);
//              //  ResultSet rs = statement.executeQuery();
//           //     while (rs.next()) {
//            //        unit = rs.getString(1);
//            //    }
//                return unit;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//
//                //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//                // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
//                //  return false;
//
//
//            }
//            return null;


//
//
//        {
//            addNewCart = true;
//            try {
//
//                try {
//                    statement = conect.prepareCall("{call XXX_LC_IMPORT_CENY4(?,?, ?,?,?,?)}");
//                    statement.setInt(1, id_kartoteka);
//                    statement.setString(2, cartModel.getCenaHurtowa());
//                    statement.setString(3, cartModel.getCenaSklepInternetowy());
//                    statement.setString(4, cartModel.getCenaDlaParagonu());
//                    statement.setInt(5, punkt);
//                    statement.setInt(6, new_kart);
//                    statement.execute();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//
//                    logger.debug(ex.getMessage());
//                }
//                // statement.close();
//                // return true;
//
//                //waga
//                if ((cartModel.getNazwaZdjecia() != null)) {// && (!cartModel.getNazwaZdjecia().isEmpty()) && (!cartModel.getNazwaZdjecia().equals(""))) {
//                    String nazwa_zdjecia = cartModel.getNazwaZdjecia();
//                    //      JOptionPane.showMessageDialog(null, nazwa_zdjecia);
//                    if (nazwa_zdjecia.contains(";")) {
//                        String[] zdjecia = nazwa_zdjecia.split(";");
//                        for (String zdjecie : zdjecia) {
//                            try {
//
//                                statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA4(?,?,?,?)}");
//                                statement.setInt(1, id_kartoteka);
//                                statement.setString(2, zdjecie);
//                                statement.setInt(3, punkt);
//                                statement.setInt(4, new_kart);
//                                statement.execute();
//                            } catch (SQLException ex) {
//                                ex.printStackTrace();
//                                logger.debug(ex.getMessage());
//
//                            }
//
//                        }
//                    } else {
//                        try {
//                            statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA4(?,?,?,?)}");
//                            statement.setInt(1, id_kartoteka);
//                            statement.setString(2, nazwa_zdjecia);
//                            statement.setInt(3, punkt);
//                            statement.setInt(4, new_kart);
//                            statement.execute();
//                        } catch (SQLException ex) {
//                            ex.printStackTrace();
//                            logger.debug(ex.getMessage());
//
//                        }
//                    }
//                }
//
//                if ((cartModel.getKgo() != null) && (!cartModel.getKgo().isEmpty()) && (!cartModel.getKgo().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_KGO4(?, ?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getKgo());
//                        statement.setInt(3, punkt);
//                        statement.setInt(4, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//                }
//
//
//                if ((cartModel.getWaga() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getWaga().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_WAGA4(?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getWaga());
//                        statement.setInt(3, punkt);
//                        statement.setInt(4, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//                }
//                // statement.close();
//
//                if ((cartModel.getKartotekiPowiazane() != null) && (!cartModel.getKartotekiPowiazane().isEmpty()) && (!cartModel.getKartotekiPowiazane().equals(""))) {
//
//                    String kartoteki = String.valueOf(cartModel.getKartotekiPowiazane().toString());
//
//                    if (kartoteki.contains(";")) {
//                        String[] kart_pow = kartoteki.split(";", -1);//" ;//kartoteki.split(";");
//                        for (String kartoteka : kart_pow) {
//
//                            //  JOptionPane.showMessageDialog(null,kart);
//                            try {
//                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW4(?,?,?,?)}");
//                                statement.setInt(1, id_kartoteka);
//                                statement.setString(2, kartoteka);
//                                statement.setInt(3, punkt);
//                                statement.setInt(4, new_kart);
//                                statement.execute();
//                            } catch (SQLException ex) {
//                                ex.printStackTrace();
//                                logger.debug(ex.getMessage());
//                            }
//
//                        }
//
//                    } else {
//                        try {
//                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW4(?,?,?,?)}");
//                            statement.setInt(1, id_kartoteka);
//                            statement.setString(2, kartoteki);
//                            statement.setInt(3, punkt);
//                            statement.setInt(4, new_kart);
//                            statement.execute();
//                        } catch (SQLException ex) {
//
//                            ex.printStackTrace();
//                            logger.debug(ex.getMessage());
//
//                        }
//                    }
//                }
//                if ((cartModel.getZamienniki() != null) && (!cartModel.getZamienniki().isEmpty()) && (!cartModel.getZamienniki().equals(""))) {
//                    String zamieniki = cartModel.getZamienniki();
//                    if (zamieniki.contains(";")) {
//                        String[] kart_zam = zamieniki.split(";", -1);
//                        for (String zamiennik : kart_zam) {
//
//                            try {
//                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM4(?,?,?,?)}");
//                                statement.setInt(1, id_kartoteka);
//                                statement.setString(2, zamiennik);
//                                statement.setInt(3, punkt);
//                                statement.setInt(4, new_kart);
//                                statement.execute();
//                            } catch (SQLException ex) {
//                                ex.printStackTrace();
//                                logger.debug(ex.getMessage());
//                            }
//
//                        }
//
//                    } else {
//
//                        try {
//                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM4(?,?,?,?)}");
//                            statement.setInt(1, id_kartoteka);
//                            statement.setString(2, zamieniki);
//                            statement.setInt(3, punkt);
//                            statement.setInt(4, new_kart);
//                            statement.execute();
//                        } catch (SQLException ex) {
//
//                            ex.printStackTrace();
//                            logger.debug(ex.getMessage());
//                        }
//                    }
//                }
//                //opakowanie zbiorcze 1
//                if ((cartModel.getIdOpakowaniaZbiorczego1() != null) && (!cartModel.getIdOpakowaniaZbiorczego1().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze1() != null) && (!cartModel.getIloscOpakowanieZbiorcze1().isEmpty())) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego1());
//                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze1());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                if ((cartModel.getIdOpakowaniaZbiorczego2() != null) && (!cartModel.getIdOpakowaniaZbiorczego2().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze2() != null) && (!cartModel.getIloscOpakowanieZbiorcze2().isEmpty())) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego2());
//                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze2());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                if ((cartModel.getIdOpakowaniaZbiorczego3() != null) && (!cartModel.getIdOpakowaniaZbiorczego3().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze3() != null) && (!cartModel.getIloscOpakowanieZbiorcze3().isEmpty())) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego3());
//                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze3());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                        //  JOptionPane.showMessageDialog(null, "opakowania zbiorcze");
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                ////opakowanie zbiorcze ean
//
//
//                if ((cartModel.getIdRodzajuGrupyKartotekowej1() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej1().isEmpty()) && (cartModel.getKodGrupaKartotekowa1() != null) && (!cartModel.getKodGrupaKartotekowa1().isEmpty()) && (!cartModel.getKodGrupaKartotekowa1().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej1().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej1());
//                        statement.setString(3, cartModel.getKodGrupaKartotekowa1());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//                }
//                if ((cartModel.getIdRodzajuGrupyKartotekowej2() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej2().isEmpty()) && (cartModel.getKodGrupaKartotekowa2() != null) && (!cartModel.getKodGrupaKartotekowa2().isEmpty()) && (!cartModel.getKodGrupaKartotekowa2().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej2().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej2());
//                        statement.setString(3, cartModel.getKodGrupaKartotekowa2());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                if ((cartModel.getIdRodzajuGrupyKartotekowej3() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej3().isEmpty()) && (cartModel.getKodGrupaKartotekowa3() != null) && (!cartModel.getKodGrupaKartotekowa3().isEmpty()) && (!cartModel.getKodGrupaKartotekowa3().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej3().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej3());
//                        statement.setString(3, cartModel.getKodGrupaKartotekowa3());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                if ((cartModel.getIdRodzajuGrupyKartotekowej4() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej4().isEmpty()) && (cartModel.getKodGrupaKartotekowa4() != null) && (!cartModel.getKodGrupaKartotekowa4().isEmpty()) && (!cartModel.getKodGrupaKartotekowa4().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej4().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej4());
//                        statement.setString(3, cartModel.getKodGrupaKartotekowa4());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                if ((cartModel.getIdRodzajuGrupyKartotekowej5() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej5().isEmpty()) && (cartModel.getKodGrupaKartotekowa5() != null) && (!cartModel.getKodGrupaKartotekowa5().isEmpty()) && (!cartModel.getKodGrupaKartotekowa5().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej5().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej5());
//                        statement.setString(3, cartModel.getKodGrupaKartotekowa5());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                if ((cartModel.getOpis() != null) && (!cartModel.getOpis().isEmpty()) && (cartModel.getOpis() != null) && (!cartModel.getOpis().isEmpty()) && (!cartModel.getOpis().equals("")) && (!cartModel.getOpis().equals(""))) {
//                    try {
//                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPIS4(?,?,?,?,?)}");
//                        statement.setInt(1, id_kartoteka);
//                        statement.setString(2, cartModel.getIdTypOpisu());
//                        statement.setString(3, cartModel.getOpis());
//                        statement.setInt(4, punkt);
//                        statement.setInt(5, new_kart);
//                        statement.execute();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        logger.debug(ex.getMessage());
//                    }
//
//
//                }
//                if ((cartModel.getDokumentacja() != null) && (!cartModel.getDokumentacja().isEmpty()) && (!cartModel.getDokumentacja().equals(""))) {
//
//                    String dokumentacja = String.valueOf(cartModel.getDokumentacja().toString());
//
//                    if (dokumentacja.contains(";")) {
//                        String[] dok = dokumentacja.split(";", -1);//" ;//kartoteki.split(";");
//                        for (String kartoteka : dok) {
//
//                            //  JOptionPane.showMessageDialog(null,kart);
//                            try {
//                                statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA4(?,?,?,?,?)}");
//                                statement.setInt(1, id_kartoteka);
//                                statement.setString(2, kartoteka);
//                                statement.setString(3, Settings.getDocPath());
//                                statement.setInt(4, punkt);
//                                statement.setInt(5, new_kart);
//                                statement.execute();
//                            } catch (SQLException ex) {
//                                ex.printStackTrace();
//                                logger.debug(ex.getMessage());
//                            }
//
//                        }
//
//                    } else {
//                        try {
//                            statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA4(?,?,?,?,?)}");
//                            statement.setInt(1, id_kartoteka);
//                            statement.setString(2, dokumentacja);
//                            statement.setString(3, Settings.getDocPath());
//                            statement.setInt(4, punkt);
//                            statement.setInt(5, new_kart);
//                            statement.execute();
//                        } catch (SQLException ex) {
//
//                            ex.printStackTrace();
//                            logger.debug(ex.getMessage());
//
//                        }
//                    }
//                }
//
//
//                return ean;
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//                // MainController.addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks());
//
//                // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
//                ;
//            } finally {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    logger.debug(e.getMessage());
//                }
//            }


            //if(kartotek==0)      }


        }


        return ean;
    }

    @Override
    public String GetEanByIdKart(int idKart) {
        return null;
    }

    @Override
    public boolean UpdateDoc(String doc, int user) {
        try {
            conect.setAutoCommit(true);
            CallableStatement statement = conect.prepareCall("{call XXX_LC_EDI_UPDATE_USER(?,?)}");
            statement.setString(1, doc);
            statement.setInt(2, user);
            statement.executeUpdate();
            return true;

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

        return false;
    }

    @Override
    public boolean UpdateUrzzew(String kod_urzzew, String magazyn) {
        try {
            conect.setAutoCommit(true);
            CallableStatement statement = conect.prepareCall("{call XXX_LC_EDI_UPDATE_URZZEW(?,?)}");
            statement.setString(1, kod_urzzew);
            statement.setString(2, magazyn);
            statement.execute();
            return true;

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

        return false;
    }

    @Override
    public int GetIdMagazyn(String name) {
        CallableStatement statement2 = null;
        int id_magazyn = 0;
        String mag = name.trim();

        try {
            conect.setAutoCommit(true);
            //   conn.connectionTest();
            statement2 = conect.prepareCall("{call XXX_LC_EDI_GETIDMAG(?,?)}");
            statement2.registerOutParameter(2, Types.INTEGER);
            statement2.setString(1, name);
            statement2.execute();
            id_magazyn = statement2.getInt(2);

            return id_magazyn;
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
        return 0;
    }


}


