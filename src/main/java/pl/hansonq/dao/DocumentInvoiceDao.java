package pl.hansonq.dao;

import pl.hansonq.models.CartModel;
import pl.hansonq.models.DocumentInvoiceModel;
import pl.hansonq.models.InvoiceModel.CartModelEdi;
import pl.hansonq.models.InvoiceModel.InvoiceModel;
import pl.hansonq.models.InvoiceModel.OrderModel;

import java.util.List;
import java.util.Map;

public interface DocumentInvoiceDao {
    public List<Integer> getKontrah(String nip, String iln);
    public int GetKartId(String ean);
    int ImportPzNagl(InvoiceModel invoiceModel);
    int ImportPzPoz(InvoiceModel invoiceModel,CartModelEdi cartModelEdi, int id_urzzewnagl);
    boolean PowiazPZ(int zam, int pz);
    List<String> getMagazyn();

     boolean InsertNewDocumentNumber(InvoiceModel invoiceModel);
     boolean CheckIfDocumentExists(String nrdok);
     boolean CheckIfDocumentExists2(String nrdok);
    int GetUrzzewId(String code);
    boolean DeleteFromBufor();
    int GetIdNaglPZ(String number);
    int GetIdNaglZAMDOST(String number, int idKontrah);
    List<String>Kartoteki(String nazwa);
    String GetKartIndeksByEan(String ean);
    String GetKartIndeksPSB(String numerPSB);
    String GetKartIndeksByName(String kartName);
    String GetKartIndeksByIndeks(String indeks);
    String GetKartIndeksBySupplierCode(String code);
    String GetKartNameById(int idKart);
    int GetKartIdByIndeks(String indeks);
    boolean UpdateSupplierNumber(int idKart,String wartosc);
    boolean UpdateEan(int idKart,String ean);
    String UnitOfMeasure(int idKart);
    boolean InsertEan(int idKart);
    boolean UpdateNaglUzytkownik(int idNagl, int IdUser);
    int GetIdNagl(String nrdok);
    String NewKart(CartModel cartModel,int punkt);
    String GetEanByIdKart(int idKart);
    boolean UpdateDoc(String doc,int user);
    boolean UpdateUrzzew(String ID_URZZEW, String id_magazyn);
    int GetIdMagazyn(String name);
}
