package pl.hansonq.dao;

import pl.hansonq.models.CartModel;
import pl.hansonq.models.DocumentInvoiceModel;
import pl.hansonq.models.InvoiceModel.CartModelEdi;
import pl.hansonq.models.InvoiceModel.InvoiceModel;
import pl.hansonq.models.InvoiceModel.OrderModel;

import java.util.List;
import java.util.Map;

public interface DocumentInvoiceDao {
    boolean ImportPz(DocumentInvoiceModel documentInvoiceModel);
    public List<String> getZamdost();
    public List<Integer> getKontrah(String nip, String iln);
    public boolean CheckIfExist(DocumentInvoiceModel documentInvoiceModel);
    public int GetKartId(String ean);
    int ImportPzNagl(InvoiceModel invoiceModel);
    int ImportPzPoz(InvoiceModel invoiceModel,CartModelEdi cartModelEdi, int id_urzzewnagl);
    boolean PowiazPZ(InvoiceModel invoiceModel,OrderModel orderModel);
    List<String> getMagazyn();
    String GetKartIndeks(String ean);
    public boolean InsertNewDocumentNumber(InvoiceModel invoiceModel);
    public boolean CheckIfDocumentExists(String nrdok);
    public boolean CheckIfDocumentExists2(String nrdok);
    int GetUrzzewId(String code);
    boolean DeleteFromBufor();
    int GetIdNaglPZ(String number);
    int GetIdNaglZAMDOST(String number, int idKontrah);
}
