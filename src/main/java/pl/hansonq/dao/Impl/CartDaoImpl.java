package pl.hansonq.dao.Impl;

import javafx.scene.control.Alert;
import pl.hansonq.dao.CartDao;
import pl.hansonq.models.CartModel;
import pl.hansonq.utils.FirebirdConnector;
import pl.hansonq.utils.Utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CartDaoImpl implements CartDao {
    FirebirdConnector conn = FirebirdConnector.getInstance();

    @Override
    public boolean AddCart(CartModel cartModel) {


        try {
            CallableStatement statement = conn.getConnection().prepareCall("{call XXX_LC_IMPORT_KARTOTEK(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            if (cartModel.getIdentyfikator().equals("")) {
                Utils.createSimpleDialog("Błąd importu", "", "Pole Identyfikator jest puste", Alert.AlertType.ERROR);
            } else {
                statement.setString(1, cartModel.getIdentyfikator());
            }
            if (cartModel.getNazwaInternetowa().equals("")) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, cartModel.getNazwaInternetowa());
            }
            if (cartModel.getNazwaSystemowa().equals("")) {
                Utils.createSimpleDialog("Błąd importu", "", "Pole Nazwa Systemowa jest puste", Alert.AlertType.ERROR);

            } else {
                statement.setString(3, cartModel.getNazwaSystemowa());
            }
            if (cartModel.getJednostka().equals("")) {
                Utils.createSimpleDialog("Błąd importu", "", "Pole Jednostka jest puste", Alert.AlertType.ERROR);

            } else {
                statement.setString(4, cartModel.getJednostka());
            }
            if (cartModel.getKodEanDomyslny().equals("")) {
                statement.setNull(5, Types.VARCHAR);
            } else {
                statement.setString(5, cartModel.getKodEanDomyslny());
            }
            if (cartModel.getIdDodatkowegoOpakowanieZbiorczegoEAN().equals("")) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, Integer.valueOf(cartModel.getIdDodatkowegoOpakowanieZbiorczegoEAN()));

            }
            if (cartModel.getDodatkowyEan().equals("")) {
                statement.setNull(7, Types.VARCHAR);
            } else {
                statement.setString(7, cartModel.getDodatkowyEan());
            }
            if(cartModel.getCenaHurtowa().equals("")){
            statement.setNull(8, Types.NUMERIC);}
            else{
            statement.setDouble(8, Double.valueOf(cartModel.getCenaHurtowa()));}
            if(cartModel.getCenaSklepInternetowy().equals("")){
            statement.setNull(9, Types.NUMERIC);}
            else{
            statement.setDouble(9, Double.valueOf(cartModel.getCenaSklepInternetowy()));}
            if(cartModel.getCenaDlaParagonu().equals("")){
            statement.setDouble(10, 0);}
            else{
            statement.setDouble(10, Double.valueOf(cartModel.getCenaDlaParagonu()));}
            if(cartModel.getOstatniaCena().equals("")){
            statement.setDouble(11, 0);}
            else{
            statement.setDouble(11, Double.valueOf(cartModel.getOstatniaCena()));}
            if(cartModel.getOstatniaCena().equals("")){
            statement.setString(12, null);}
            else{
            statement.setString(12, cartModel.getOstatniaCena());}
            if(cartModel.getOstrzezenie().equals("")){
            statement.setString(13, null);}
            else{
            statement.setString(13, cartModel.getOstrzezenie());}
            if(cartModel.getKgo().equals("")){
            statement.setDouble(14, 0);}
            else{
            statement.setDouble(14, Double.valueOf(cartModel.getKgo()));}
            if(cartModel.getWaga().equals("")){
            statement.setString(15, null);}
            else{
            statement.setString(15, cartModel.getWaga());}
            if(cartModel.getIdPriorytet().equals("")){
            statement.setInt(16, 0);}
            else{
            statement.setInt(16, Integer.parseInt(cartModel.getIdPriorytet()));}

            statement.setInt(17, 0);
            statement.setInt(17, 1);
            statement.setInt(18, 0);
            statement.setInt(18, 1);
            statement.setInt(19, 0);
            statement.setInt(19, Integer.valueOf(cartModel.getIdOpakowaniaZbiorczego1()));
            statement.setDouble(20, 0);
            statement.setDouble(20, Double.valueOf(cartModel.getOpakowanieZbiorcze1()));
            statement.setInt(21,0);
            statement.setInt(21, Integer.parseInt(cartModel.getIdOpakowaniaZbiorczego2()));
            statement.setDouble(22,0);
            statement.setDouble(22, Double.valueOf(cartModel.getOpakowanieZbiorcze2()));
            statement.setInt(23, 0);
            statement.setInt(23, Integer.valueOf(cartModel.getIdOpakowaniaZbiorczego3()));
            statement.setDouble(24, 0);
            statement.setDouble(24, Double.valueOf(cartModel.getOpakowanieZbiorcze3()));
            statement.setInt(25,0);
            statement.setInt(25, Integer.valueOf(cartModel.getIdTypOpisu()));
            statement.setString(26, null);
            statement.setString(26, cartModel.getOpis());
            statement.setString(27, null);
            statement.setString(27, cartModel.getStronaWWW());
            statement.setInt(28, 2);
            statement.setInt(28, 2);
            statement.setString(29, null);
            statement.setString(29, cartModel.getProducent());
            statement.setInt(30, 0);
            statement.setInt(30, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej1())));
            statement.setString(31, null);
            statement.setString(31, cartModel.getGrupaKartotekowa1());
            statement.setInt(32, 0);
            statement.setInt(32, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej2())));
            statement.setString(33,null);
            statement.setString(33, cartModel.getGrupaKartotekowa2());
            statement.setInt(34, 0);
            statement.setInt(34, (Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowej3())));
            statement.setString(35, null);
            statement.setString(35, cartModel.getGrupaKartotekowa3());
            statement.setInt(36, 0);
            statement.setInt(36, Integer.valueOf(cartModel.getIdStawkiVat()));


            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean DeleteCart(int id_kartoteka) {
        return false;
    }

    @Override
    public boolean UpdateCart(CartModel Kartoteka) {
        return false;
    }

    @Override
    public boolean Add(int id_kart, int id_opis, String opis) {

        try {
            CallableStatement statement = conn.getConnection().prepareCall("{call XXX_LC_IMPORT_OPIS(?, ?,?)}");


            statement.setInt(1, id_kart);
            statement.setInt(2, id_opis);
            statement.setString(3, opis);

            statement.execute();
            statement.close();

            return true;
        } catch (SQLException e) {
            Utils.createSimpleDialog("Bład", "", "Błąd import danych !\n" + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean AddProperties(int id_kartoteka, Map<Integer, String> cecha) {
        Map<Integer, String> cechaX = new HashMap<Integer, String>();


        return false;
    }
}
