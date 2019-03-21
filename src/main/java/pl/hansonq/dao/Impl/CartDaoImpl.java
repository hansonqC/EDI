package pl.hansonq.dao.Impl;


import javafx.scene.control.Alert;
import jdk.nashorn.internal.scripts.JO;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import pl.hansonq.controllers.MainController;
import pl.hansonq.controllers.SettingsController;
import pl.hansonq.dao.CartDao;
import pl.hansonq.models.CartModel;

import pl.hansonq.utils.FirebirdConnector;
import pl.hansonq.utils.Settings;
import pl.hansonq.utils.Utils;

import javax.swing.*;
import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CartDaoImpl implements CartDao {


    final static Logger logger = Logger.getLogger(CartDaoImpl.class);

    FirebirdConnector conn = FirebirdConnector.getInstance();
    Connection conect = conn.getConnection();
    private int id_kartoteka;
    public static int new_kart;
    private boolean addNewCart = false;

    public CartDaoImpl() {
        DOMConfigurator.configure("log4j.xml");
    }

    ///////  punkt 2
    @Override
    public boolean UpdateCart(CartModel cartModel) {
        CallableStatement statement = null;

        ResultSet rs = null;
        try {
            conect.setAutoCommit(true);
            conect.setTransactionIsolation(1);

            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTOTEK_PUNKT2(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.registerOutParameter(1, Types.INTEGER);

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
            if ((cartModel.getKodEanDomyslny() != null) && (!cartModel.getKodEanDomyslny().isEmpty())) {
                statement.setString(6, cartModel.getKodEanDomyslny());
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

                statement.setString(9, "0.0000");
            }
            if ((cartModel.getOstatniaCena() != null) && (!cartModel.getOstatniaCena().isEmpty())) {// && (cartModel.getGrupaBonusowa().isEmpty()) || (cartModel.getGrupaRabatowa().isEmpty()) || (cartModel.getGrupaBonusowa() == null) || (cartModel.getGrupaRabatowa() == null)) {
                statement.setString(9, cartModel.getOstatniaCena());
            } else {
                statement.setString(9, "0.0000");
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
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
        }

        id_kartoteka = 0;
        try {
            id_kartoteka = statement.getInt(1);


        } catch (Exception ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage()+" dotyczy kartoteki "+id_kartoteka);
            id_kartoteka = 0;
        }
      //  JOptionPane.showMessageDialog(null, id_kartoteka);
        // statement.close();
        if (id_kartoteka != 0) {
            addNewCart = true;
            try {

                try {
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_CENY2(?,?, ?,?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.setString(2, cartModel.getCenaHurtowa());
                    statement.setString(3, cartModel.getCenaSklepInternetowy());
                    statement.setString(4, cartModel.getCenaDlaParagonu());
                    statement.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.debug(ex.getMessage());
                }

                try {
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_OPIS_PUNKT2(?,?, ?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.setString(2, cartModel.getIdTypOpisu());
                    statement.setString(3, cartModel.getOpis());
                    statement.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.debug(ex.getMessage());
                }
                // statement.close();
                // return true;

                //waga
                if ((cartModel.getNazwaZdjecia() != null) && (!cartModel.getNazwaZdjecia().isEmpty()) && (!cartModel.getNazwaZdjecia().equals(""))) {
                    String nazwa_zdjecia = cartModel.getNazwaZdjecia();
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_USUWANIE_ZDJEC(?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());

                    }

                    if (nazwa_zdjecia.contains(";")) {
                        String[] zdjecia = nazwa_zdjecia.split(";");
                        for (String zdjecie : zdjecia) {
                            try {

                                statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zdjecie);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());

                            }

                        }
                    } else {
                        try {

                            statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, nazwa_zdjecia);
                            statement.execute();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_USUWANIE_ZDJEC_P2(?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getNazwaZdjecia());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());

                    }
                    if(cartModel.getNazwaZdjecia().equals("0")){
                        File zdjecie=new File(Settings.getPicturesPath()+"\\"+cartModel.getNazwaZdjecia());

                        try{
                            zdjecie.delete();
                            logger.debug("Usunięto zdjęcie "+cartModel.getNazwaZdjecia());
                        }catch(Exception ex){
                            logger.debug("Błąd podczas usuwania zdjęcia "+cartModel.getNazwaZdjecia());
                        }


                    }
                }

                if ((cartModel.getWaga() != null) && (cartModel.getKgo() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getKgo().isEmpty()) && (!cartModel.getKgo().equals("")) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_KGO2(?,?, ?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getKgo());
                        statement.setString(3, cartModel.getWaga());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }
                }


                if ((cartModel.getWaga() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_WAGA_P2(?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getWaga());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }

                }
                // statement.close();

                if ((cartModel.getKartotekiPowiazane() != null) && (!cartModel.getKartotekiPowiazane().isEmpty()) && (!cartModel.getKartotekiPowiazane().equals(""))) {

                    String kartoteki = String.valueOf(cartModel.getKartotekiPowiazane().toString());
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_USUWANIE_KARTPOW(?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.execute();
                    if (kartoteki.contains(";")) {
                        String[] kart_pow = kartoteki.split(";", -1);//" ;//kartoteki.split(";");
                        for (String kartoteka : kart_pow) {

                            //  JOptionPane.showMessageDialog(null,kart);
                            try {

                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW_P2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, kartoteka);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW_P2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, kartoteki);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }
                if ((cartModel.getZamienniki() != null) && (!cartModel.getZamienniki().isEmpty()) && (!cartModel.getZamienniki().equals(""))) {
                    String zamieniki = cartModel.getZamienniki();
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_USUWANIE_KARTZAM(?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.execute();
                    if (zamieniki.contains(";")) {
                        String[] kart_zam = zamieniki.split(";", -1);
                        for (String zamiennik : kart_zam) {

                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM_P2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zamiennik);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {

                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM_P2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, zamieniki);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());
                        }
                    }
                }
                //opakowanie zbiorcze 1
                if ((cartModel.getIdOpakowaniaZbiorczego1() != null) && (!cartModel.getIdOpakowaniaZbiorczego1().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze1() != null) && (!cartModel.getIloscOpakowanieZbiorcze1().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ_P2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego1());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze1());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego2() != null) && (!cartModel.getIdOpakowaniaZbiorczego2().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze2() != null) && (!cartModel.getIloscOpakowanieZbiorcze2().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ_P2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego2());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze2());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego3() != null) && (!cartModel.getIdOpakowaniaZbiorczego3().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze3() != null) && (!cartModel.getIloscOpakowanieZbiorcze3().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ_P2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego3());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze3());
                        statement.execute();
                        //  JOptionPane.showMessageDialog(null, "opakowania zbiorcze");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej1() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej1().isEmpty()) && (cartModel.getKodGrupaKartotekowa1() != null) && (!cartModel.getKodGrupaKartotekowa1().isEmpty()) && (!cartModel.getKodGrupaKartotekowa1().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej1().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej1());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa1());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }

                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej2() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej2().isEmpty()) && (cartModel.getKodGrupaKartotekowa2() != null) && (!cartModel.getKodGrupaKartotekowa2().isEmpty()) && (!cartModel.getKodGrupaKartotekowa2().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej2().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej2());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa2());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej3() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej3().isEmpty()) && (cartModel.getKodGrupaKartotekowa3() != null) && (!cartModel.getKodGrupaKartotekowa3().isEmpty()) && (!cartModel.getKodGrupaKartotekowa3().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej3().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej3());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa3());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej4() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej4().isEmpty()) && (cartModel.getKodGrupaKartotekowa4() != null) && (!cartModel.getKodGrupaKartotekowa4().isEmpty()) && (!cartModel.getKodGrupaKartotekowa4().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej4().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej4());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa4());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej5() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej5().isEmpty()) && (cartModel.getKodGrupaKartotekowa5() != null) && (!cartModel.getKodGrupaKartotekowa5().isEmpty()) && (!cartModel.getKodGrupaKartotekowa5().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej5().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej5());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa5());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowejX() != null) && (!cartModel.getIdRodzajuGrupyKartotekowejX().isEmpty()) && (cartModel.getKodGrupaKartotekowaX() != null) && (!cartModel.getKodGrupaKartotekowa1().isEmpty()) && (!cartModel.getKodGrupaKartotekowaX().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowejX().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowejX());
                        statement.setString(3, cartModel.getKodGrupaKartotekowaX());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }

                if ((cartModel.getDokumentacja() != null) && (!cartModel.getDokumentacja().isEmpty()) && (!cartModel.getDokumentacja().equals(""))) {

                    String dokumentacja = String.valueOf(cartModel.getDokumentacja().toString());

                    if (dokumentacja.contains(";")) {
                        String[] dok = dokumentacja.split(";", -1);//" ;//kartoteki.split(";");
                        for (String kartoteka : dok) {

                            //  JOptionPane.showMessageDialog(null,kart);
                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA_P2(?,?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, kartoteka);
                                statement.setString(3, Settings.getDocPath());
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA_P2(?,?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, dokumentacja);
                            statement.setString(3, Settings.getDocPath());
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                    if(cartModel.getDokumentacja().equals("0")){
                        File dokumentation=new File(Settings.getDocPath()+"\\"+cartModel.getDokumentacja());
//JOptionPane.showMessageDialog(null,dokumentation);
                        try{
                            dokumentation.delete();
                            logger.debug("Usunięto dokumentację kartoteki "+cartModel.getDokumentacja());
                        }catch(Exception ex){
                            logger.debug("Błąd podczas usuwania dokumentacji kartoteki "+cartModel.getNazwaZdjecia());
                        }


                    }
                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
                // MainController.addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks());

                // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

            } finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.debug(e.getMessage());
                }
            }


            //if(kartotek==0)      }


        }
        return false;
    }


    ///////  punkt 3
    @Override
    public boolean UpdateCart3(CartModel cartModel) {
        CallableStatement statement = null;

        ResultSet rs = null;
        try {
            conect.setAutoCommit(true);
            conect.setTransactionIsolation(1);

            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTOTEK_PUNKT3(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.registerOutParameter(1, Types.INTEGER);

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
            if ((cartModel.getKodEanDomyslny() != null) && (!cartModel.getKodEanDomyslny().isEmpty())) {
                statement.setString(6, cartModel.getKodEanDomyslny());
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

                statement.setString(9, "0.0000");
            }
            if ((cartModel.getOstatniaCena() != null) && (!cartModel.getOstatniaCena().isEmpty())) {// && (cartModel.getGrupaBonusowa().isEmpty()) || (cartModel.getGrupaRabatowa().isEmpty()) || (cartModel.getGrupaBonusowa() == null) || (cartModel.getGrupaRabatowa() == null)) {
                statement.setString(9, cartModel.getOstatniaCena());
            } else {
                statement.setString(9, "0.0000");
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
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
        }

        id_kartoteka = 0;
        try {
            id_kartoteka = statement.getInt(1);


        } catch (Exception ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
            id_kartoteka = 0;
        }
     //   JOptionPane.showMessageDialog(null, id_kartoteka);
        // statement.close();
        if (id_kartoteka != 0) {
            addNewCart = true;
            try {

                try {
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_CENY2(?,?, ?,?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.setString(2, cartModel.getCenaHurtowa());
                    statement.setString(3, cartModel.getCenaSklepInternetowy());
                    statement.setString(4, cartModel.getCenaDlaParagonu());
                    statement.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.debug(ex.getMessage());
                }

                try {
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_OPIS2(?,?, ?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.setString(2, cartModel.getIdTypOpisu());
                    statement.setString(3, cartModel.getOpis());
                    statement.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.debug(ex.getMessage());
                }
                // statement.close();
                // return true;

                //waga
                if ((cartModel.getNazwaZdjecia() != null) && (!cartModel.getNazwaZdjecia().isEmpty()) && (!cartModel.getNazwaZdjecia().equals(""))) {
                    String nazwa_zdjecia = cartModel.getNazwaZdjecia();

                    statement = conect.prepareCall("{call XXX_LC_IMPORT_USUWANIE_ZDJEC(?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.execute();
                    if (nazwa_zdjecia.contains(";")) {
                        String[] zdjecia = nazwa_zdjecia.split(";");
                        for (String zdjecie : zdjecia) {
                            try {

                                statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zdjecie);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());

                            }

                        }
                    } else {
                        try {

                            statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, nazwa_zdjecia);
                            statement.execute();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }

                if ((cartModel.getWaga() != null) && (cartModel.getKgo() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getKgo().isEmpty()) && (!cartModel.getKgo().equals("")) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_KGO2(?,?, ?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getKgo());
                        statement.setString(3, cartModel.getWaga());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }
                }


                if ((cartModel.getWaga() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_WAGA2(?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getWaga());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }
                }
                // statement.close();

                if ((cartModel.getKartotekiPowiazane() != null) && (!cartModel.getKartotekiPowiazane().isEmpty()) && (!cartModel.getKartotekiPowiazane().equals(""))) {

                    String kartoteki = String.valueOf(cartModel.getKartotekiPowiazane().toString());
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_USUWANIE_KARTPOW(?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.execute();
                    if (kartoteki.contains(";")) {
                        String[] kart_pow = kartoteki.split(";", -1);//" ;//kartoteki.split(";");
                        for (String kartoteka : kart_pow) {

                            //  JOptionPane.showMessageDialog(null,kart);
                            try {

                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, kartoteka);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, kartoteki);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }
                if ((cartModel.getZamienniki() != null) && (!cartModel.getZamienniki().isEmpty()) && (!cartModel.getZamienniki().equals(""))) {
                    String zamieniki = cartModel.getZamienniki();
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_USUWANIE_KARTZAM(?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.execute();
                    if (zamieniki.contains(";")) {
                        String[] kart_zam = zamieniki.split(";", -1);
                        for (String zamiennik : kart_zam) {

                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zamiennik);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {

                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, zamieniki);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());
                        }
                    }
                }
                //opakowanie zbiorcze 1
                if ((cartModel.getIdOpakowaniaZbiorczego1() != null) && (!cartModel.getIdOpakowaniaZbiorczego1().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze1() != null) && (!cartModel.getIloscOpakowanieZbiorcze1().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego1());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze1());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego2() != null) && (!cartModel.getIdOpakowaniaZbiorczego2().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze2() != null) && (!cartModel.getIloscOpakowanieZbiorcze2().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego2());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze2());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego3() != null) && (!cartModel.getIdOpakowaniaZbiorczego3().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze3() != null) && (!cartModel.getIloscOpakowanieZbiorcze3().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego3());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze3());
                        statement.execute();
                        //  JOptionPane.showMessageDialog(null, "opakowania zbiorcze");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej1() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej1().isEmpty()) && (cartModel.getKodGrupaKartotekowa1() != null) && (!cartModel.getKodGrupaKartotekowa1().isEmpty()) && (!cartModel.getKodGrupaKartotekowa1().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej1().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej1());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa1());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }

                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej2() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej2().isEmpty()) && (cartModel.getKodGrupaKartotekowa2() != null) && (!cartModel.getKodGrupaKartotekowa2().isEmpty()) && (!cartModel.getKodGrupaKartotekowa2().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej2().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej2());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa2());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej3() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej3().isEmpty()) && (cartModel.getKodGrupaKartotekowa3() != null) && (!cartModel.getKodGrupaKartotekowa3().isEmpty()) && (!cartModel.getKodGrupaKartotekowa3().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej3().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej3());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa3());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej4() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej4().isEmpty()) && (cartModel.getKodGrupaKartotekowa4() != null) && (!cartModel.getKodGrupaKartotekowa4().isEmpty()) && (!cartModel.getKodGrupaKartotekowa4().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej4().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej4());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa4());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej5() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej5().isEmpty()) && (cartModel.getKodGrupaKartotekowa5() != null) && (!cartModel.getKodGrupaKartotekowa5().isEmpty()) && (!cartModel.getKodGrupaKartotekowa5().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej5().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej5());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa5());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowejX() != null) && (!cartModel.getIdRodzajuGrupyKartotekowejX().isEmpty()) && (cartModel.getKodGrupaKartotekowaX() != null) && (!cartModel.getKodGrupaKartotekowa1().isEmpty()) && (!cartModel.getKodGrupaKartotekowaX().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowejX().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowejX());
                        statement.setString(3, cartModel.getKodGrupaKartotekowaX());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
                // MainController.addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks());

                // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

            } finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.debug(e.getMessage());
                }
            }


            //if(kartotek==0)      }


        }
        return false;
    }


    ///////   punkt 5
    @Override
    public boolean DeleteCart(CartModel cartModel) {
        CallableStatement statement2 = null;
        try {
            conect.setAutoCommit(true);

            statement2 = conect.prepareCall("{call XXX_LC_IMPORT_DELETE4(?)}");
            statement2.registerOutParameter(1, Types.INTEGER);
            //  statement.registerOutParameter(1,Types.INTEGER);
            //  if (cartModel.getIndeks() != null) {
            statement2.setString(1, cartModel.getIndeks());
            statement2.execute();
            int result = statement2.getInt(1);
            statement2.close();
            if (result == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            ;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas usuwania kartoteki o indeksie "+cartModel.getIndeks()+", komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
            //  return false;
        } finally {
            try {
                statement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
            }
        }
        return false;
    }

    //////   punkt 4
    @Override
    public boolean UpdatePrice(CartModel cartModel) {
        CallableStatement statement = null;

        ResultSet rs = null;
        try {
            conect.setAutoCommit(true);
            //   conect.setTransactionIsolation(1);

            statement = conect.prepareCall("{call XXX_LC_IMPORT_UPDATE_CENY(?, ?,?,?,?)}");
            //   statement.registerOutParameter(1, Types.INTEGER);
            if ((cartModel.getIndeks() == null) || (cartModel.getIndeks().isEmpty())) {
                statement.setNull(1, Types.VARCHAR);

            } else {
                statement.setString(1, cartModel.getIndeks());

            }
            statement.setString(2, cartModel.getCenaHurtowa());
            statement.setString(3, cartModel.getCenaSklepInternetowy());
            statement.setString(4, cartModel.getCenaDlaParagonu());
            statement.setString(5, cartModel.getOstatniaCena());

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            return false;
            //    MainController.addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks());

            //Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);

        } catch (Exception e) {

            e.printStackTrace();
            logger.debug(e.getMessage());
            return false;
            // MainController.addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks());

            // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
        }
        //return true;
    }

    //////  punkt 1

    /////   punkt 1
    @Override   //
    public boolean AddCart(CartModel cartModel) {

        CallableStatement statement = null;

        ResultSet rs = null;
        try {
            conect.setAutoCommit(true);
            conect.setTransactionIsolation(1);

            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTOTEK2(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.registerOutParameter(1, Types.INTEGER);

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
            if ((cartModel.getKodEanDomyslny() != null) && (!cartModel.getKodEanDomyslny().isEmpty())) {
                statement.setString(6, cartModel.getKodEanDomyslny());
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

                statement.setString(9, "0.0000");
            }
            if ((cartModel.getOstatniaCena() != null) && (!cartModel.getOstatniaCena().isEmpty())) {// && (cartModel.getGrupaBonusowa().isEmpty()) || (cartModel.getGrupaRabatowa().isEmpty()) || (cartModel.getGrupaBonusowa() == null) || (cartModel.getGrupaRabatowa() == null)) {
                statement.setString(9, cartModel.getOstatniaCena());
            } else {
                statement.setString(9, "0.0000");
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

            statement.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage()+" - dotyczy kartoteki :"+cartModel.getIndeks());

        }

        id_kartoteka = 0;
        try {
            id_kartoteka = statement.getInt(1);


        } catch (Exception ex) {
            ex.printStackTrace();
            //logger.debug(ex.getMessage());
            id_kartoteka = 0;

        }

        // statement.close();
        if (id_kartoteka != 0) {
            addNewCart = true;
            try {

                try {
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_CENY2(?,?, ?,?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.setString(2, cartModel.getCenaHurtowa());
                    statement.setString(3, cartModel.getCenaSklepInternetowy());
                    statement.setString(4, cartModel.getCenaDlaParagonu());

                    statement.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                    logger.debug(ex.getMessage());
                }
                // statement.close();
                // return true;

                //waga
                if ((cartModel.getNazwaZdjecia() != null) && (!cartModel.getNazwaZdjecia().isEmpty()) && (!cartModel.getNazwaZdjecia().equals(""))) {
                    String nazwa_zdjecia = cartModel.getNazwaZdjecia();
                    if (nazwa_zdjecia.contains(";")) {
                        String[] zdjecia = nazwa_zdjecia.split(";");
                        for (String zdjecie : zdjecia) {
                            try {

                                statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zdjecie);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());

                            }

                        }
                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, nazwa_zdjecia);
                            statement.execute();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }

                if ((cartModel.getWaga() != null) && (cartModel.getKgo() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getKgo().isEmpty()) && (!cartModel.getKgo().equals("")) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_KGO2(?,?, ?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getKgo());
                        statement.setString(3, cartModel.getWaga());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }
                }


                if ((cartModel.getWaga() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_WAGA2(?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getWaga());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }
                }
                // statement.close();

                if ((cartModel.getKartotekiPowiazane() != null) && (!cartModel.getKartotekiPowiazane().isEmpty()) && (!cartModel.getKartotekiPowiazane().equals(""))) {

                    String kartoteki = String.valueOf(cartModel.getKartotekiPowiazane().toString());

                    if (kartoteki.contains(";")) {
                        String[] kart_pow = kartoteki.split(";", -1);//" ;//kartoteki.split(";");
                        for (String kartoteka : kart_pow) {

                            //  JOptionPane.showMessageDialog(null,kart);
                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, kartoteka);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, kartoteki);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }
                if ((cartModel.getZamienniki() != null) && (!cartModel.getZamienniki().isEmpty()) && (!cartModel.getZamienniki().equals(""))) {
                    String zamieniki = cartModel.getZamienniki();
                    if (zamieniki.contains(";")) {
                        String[] kart_zam = zamieniki.split(";", -1);
                        for (String zamiennik : kart_zam) {

                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM2(?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zamiennik);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {

                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM2(?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, zamieniki);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());
                        }
                    }
                }
                //opakowanie zbiorcze 1
                if ((cartModel.getIdOpakowaniaZbiorczego1() != null) && (!cartModel.getIdOpakowaniaZbiorczego1().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze1() != null) && (!cartModel.getIloscOpakowanieZbiorcze1().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego1());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze1());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego2() != null) && (!cartModel.getIdOpakowaniaZbiorczego2().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze2() != null) && (!cartModel.getIloscOpakowanieZbiorcze2().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego2());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze2());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego3() != null) && (!cartModel.getIdOpakowaniaZbiorczego3().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze3() != null) && (!cartModel.getIloscOpakowanieZbiorcze3().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego3());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze3());
                        statement.execute();
                        //  JOptionPane.showMessageDialog(null, "opakowania zbiorcze");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej1() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej1().isEmpty()) && (cartModel.getKodGrupaKartotekowa1() != null) && (!cartModel.getKodGrupaKartotekowa1().isEmpty()) && (!cartModel.getKodGrupaKartotekowa1().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej1().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej1());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa1());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }

                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej2() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej2().isEmpty()) && (cartModel.getKodGrupaKartotekowa2() != null) && (!cartModel.getKodGrupaKartotekowa2().isEmpty()) && (!cartModel.getKodGrupaKartotekowa2().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej2().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej2());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa2());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej3() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej3().isEmpty()) && (cartModel.getKodGrupaKartotekowa3() != null) && (!cartModel.getKodGrupaKartotekowa3().isEmpty()) && (!cartModel.getKodGrupaKartotekowa3().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej3().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej3());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa3());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej4() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej4().isEmpty()) && (cartModel.getKodGrupaKartotekowa4() != null) && (!cartModel.getKodGrupaKartotekowa4().isEmpty()) && (!cartModel.getKodGrupaKartotekowa4().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej4().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej4());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa4());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej5() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej5().isEmpty()) && (cartModel.getKodGrupaKartotekowa5() != null) && (!cartModel.getKodGrupaKartotekowa5().isEmpty()) && (!cartModel.getKodGrupaKartotekowa5().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej5().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej5());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa5());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej5() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej5().isEmpty()) && (cartModel.getKodGrupaKartotekowa5() != null) && (!cartModel.getKodGrupaKartotekowa5().isEmpty()) && (!cartModel.getKodGrupaKartotekowa5().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej5().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPIS2(?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej5());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa5());
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getDokumentacja() != null) && (!cartModel.getDokumentacja().isEmpty()) && (!cartModel.getDokumentacja().equals(""))) {

                    String dokumentacja = String.valueOf(cartModel.getDokumentacja().toString());

                    if (dokumentacja.contains(";")) {
                        String[] dok = dokumentacja.split(";", -1);//" ;//kartoteki.split(";");
                        for (String kartoteka : dok) {

                            //  JOptionPane.showMessageDialog(null,kart);
                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA(?,?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, kartoteka);
                                statement.setString(3, Settings.getDocPath());
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA(?,?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, dokumentacja);
                            statement.setString(3, Settings.getDocPath());
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }


                return true;
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
                // MainController.addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks());

                // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
                ;
            } finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.debug(e.getMessage());
                }
            }


            //if(kartotek==0)      }


        }
        return false;
    }

    @Override
    public int SelectCart(CartModel cartModel) {
        try {
            PreparedStatement statement = conect.prepareStatement("select k.id_kartoteka\n" +
                    "  from kartoteka k\n" +
                    "  where k.indeks=?");
            statement.setString(1, cartModel.getIndeks());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
        }
        return 0;
    }




    /////   punkt 1
    @Override   //
    public boolean AddCart4(CartModel cartModel,int punkt) {

        CallableStatement statement = null;

        ResultSet rs = null;
        try {
            conect.setAutoCommit(true);
            conect.setTransactionIsolation(1);

            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTOTEK4(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.registerOutParameter(2, Types.INTEGER);

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
            if ((cartModel.getKodEanDomyslny() != null) && (!cartModel.getKodEanDomyslny().isEmpty())) {
                statement.setString(6, cartModel.getKodEanDomyslny());
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

                statement.setString(9, "0.0000");
            }
            if ((cartModel.getOstatniaCena() != null) && (!cartModel.getOstatniaCena().isEmpty())) {// && (cartModel.getGrupaBonusowa().isEmpty()) || (cartModel.getGrupaRabatowa().isEmpty()) || (cartModel.getGrupaBonusowa() == null) || (cartModel.getGrupaRabatowa() == null)) {
                statement.setString(9, cartModel.getOstatniaCena());
            } else {
                statement.setString(9, "0.0000");
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

            statement.setInt(18,punkt);
            statement.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage()+" - dotyczy kartoteki :"+cartModel.getIndeks());

        }

        id_kartoteka = 0;
        try {
            id_kartoteka = statement.getInt(1);
            new_kart=statement.getInt(2);
           // JOptionPane.showMessageDialog(null,id_kartoteka+" ,"+"nowa :"+new_kart);


        } catch (Exception ex) {
            ex.printStackTrace();
            //logger.debug(ex.getMessage());
            id_kartoteka = 0;

        }

        // statement.close();
        if (id_kartoteka != 0) {
            addNewCart = true;
            try {

                try {
                    statement = conect.prepareCall("{call XXX_LC_IMPORT_CENY4(?,?, ?,?,?,?)}");
                    statement.setInt(1, id_kartoteka);
                    statement.setString(2, cartModel.getCenaHurtowa());
                    statement.setString(3, cartModel.getCenaSklepInternetowy());
                    statement.setString(4, cartModel.getCenaDlaParagonu());
                    statement.setInt(5,punkt);
                    statement.setInt(6,new_kart);
                    statement.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                    logger.debug(ex.getMessage());
                }
                // statement.close();
                // return true;

                //waga
                if ((cartModel.getNazwaZdjecia() != null) && (!cartModel.getNazwaZdjecia().isEmpty()) && (!cartModel.getNazwaZdjecia().equals(""))) {
                    String nazwa_zdjecia = cartModel.getNazwaZdjecia();
                    if (nazwa_zdjecia.contains(";")) {
                        String[] zdjecia = nazwa_zdjecia.split(";");
                        for (String zdjecie : zdjecia) {
                            try {

                                statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA4(?,?,?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zdjecie);
                                statement.setInt(3,punkt);
                                statement.setInt(4,new_kart);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());

                            }

                        }
                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_ZDJECIA4(?,?,?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, nazwa_zdjecia);
                            statement.setInt(3,punkt);
                            statement.setInt(4,new_kart);
                            statement.execute();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }

                if ((cartModel.getWaga() != null) && (cartModel.getKgo() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getKgo().isEmpty()) && (!cartModel.getKgo().equals("")) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_KGO4(?,?, ?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getKgo());
                        statement.setString(3, cartModel.getWaga());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }
                }


                if ((cartModel.getWaga() != null) && (!cartModel.getWaga().isEmpty()) && (!cartModel.getWaga().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_WAGA4(?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getWaga());
                        statement.setInt(3,punkt);
                        statement.setInt(4,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }
                }
                // statement.close();

                if ((cartModel.getKartotekiPowiazane() != null) && (!cartModel.getKartotekiPowiazane().isEmpty()) && (!cartModel.getKartotekiPowiazane().equals(""))) {

                    String kartoteki = String.valueOf(cartModel.getKartotekiPowiazane().toString());

                    if (kartoteki.contains(";")) {
                        String[] kart_pow = kartoteki.split(";", -1);//" ;//kartoteki.split(";");
                        for (String kartoteka : kart_pow) {

                            //  JOptionPane.showMessageDialog(null,kart);
                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW4(?,?,?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, kartoteka);
                                statement.setInt(3,punkt);
                                statement.setInt(4,new_kart);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTPOW4(?,?,?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, kartoteki);
                            statement.setInt(3,punkt);
                            statement.setInt(4,new_kart);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }
                if ((cartModel.getZamienniki() != null) && (!cartModel.getZamienniki().isEmpty()) && (!cartModel.getZamienniki().equals(""))) {
                    String zamieniki = cartModel.getZamienniki();
                    if (zamieniki.contains(";")) {
                        String[] kart_zam = zamieniki.split(";", -1);
                        for (String zamiennik : kart_zam) {

                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM4(?,?,?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, zamiennik);
                                statement.setInt(3,punkt);
                                statement.setInt(4,new_kart);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {

                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARZAM4(?,?,?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, zamieniki);
                            statement.setInt(3,punkt);
                            statement.setInt(4,new_kart);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());
                        }
                    }
                }
                //opakowanie zbiorcze 1
                if ((cartModel.getIdOpakowaniaZbiorczego1() != null) && (!cartModel.getIdOpakowaniaZbiorczego1().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze1() != null) && (!cartModel.getIloscOpakowanieZbiorcze1().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego1());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze1());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego2() != null) && (!cartModel.getIdOpakowaniaZbiorczego2().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze2() != null) && (!cartModel.getIloscOpakowanieZbiorcze2().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego2());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze2());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdOpakowaniaZbiorczego3() != null) && (!cartModel.getIdOpakowaniaZbiorczego3().isEmpty()) && (cartModel.getIloscOpakowanieZbiorcze3() != null) && (!cartModel.getIloscOpakowanieZbiorcze3().isEmpty())) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPAKZ4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdOpakowaniaZbiorczego3());
                        statement.setString(3, cartModel.getIloscOpakowanieZbiorcze3());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                        //  JOptionPane.showMessageDialog(null, "opakowania zbiorcze");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej1() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej1().isEmpty()) && (cartModel.getKodGrupaKartotekowa1() != null) && (!cartModel.getKodGrupaKartotekowa1().isEmpty()) && (!cartModel.getKodGrupaKartotekowa1().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej1().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej1());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa1());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }

                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej2() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej2().isEmpty()) && (cartModel.getKodGrupaKartotekowa2() != null) && (!cartModel.getKodGrupaKartotekowa2().isEmpty()) && (!cartModel.getKodGrupaKartotekowa2().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej2().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej2());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa2());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej3() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej3().isEmpty()) && (cartModel.getKodGrupaKartotekowa3() != null) && (!cartModel.getKodGrupaKartotekowa3().isEmpty()) && (!cartModel.getKodGrupaKartotekowa3().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej3().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej3());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa3());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej4() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej4().isEmpty()) && (cartModel.getKodGrupaKartotekowa4() != null) && (!cartModel.getKodGrupaKartotekowa4().isEmpty()) && (!cartModel.getKodGrupaKartotekowa4().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej4().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej4());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa4());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getIdRodzajuGrupyKartotekowej5() != null) && (!cartModel.getIdRodzajuGrupyKartotekowej5().isEmpty()) && (cartModel.getKodGrupaKartotekowa5() != null) && (!cartModel.getKodGrupaKartotekowa5().isEmpty()) && (!cartModel.getKodGrupaKartotekowa5().equals("")) && (!cartModel.getIdRodzajuGrupyKartotekowej5().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_GRUPAKART4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdRodzajuGrupyKartotekowej5());
                        statement.setString(3, cartModel.getKodGrupaKartotekowa5());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getOpis() != null) && (!cartModel.getOpis().isEmpty()) && (cartModel.getOpis() != null) && (!cartModel.getOpis().isEmpty()) && (!cartModel.getOpis().equals("")) && (!cartModel.getOpis().equals(""))) {
                    try {
                        statement = conect.prepareCall("{call XXX_LC_IMPORT_OPIS4(?,?,?,?,?)}");
                        statement.setInt(1, id_kartoteka);
                        statement.setString(2, cartModel.getIdTypOpisu());
                        statement.setString(3, cartModel.getOpis());
                        statement.setInt(4,punkt);
                        statement.setInt(5,new_kart);
                        statement.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        logger.debug(ex.getMessage());
                    }


                }
                if ((cartModel.getDokumentacja() != null) && (!cartModel.getDokumentacja().isEmpty()) && (!cartModel.getDokumentacja().equals(""))) {

                    String dokumentacja = String.valueOf(cartModel.getDokumentacja().toString());

                    if (dokumentacja.contains(";")) {
                        String[] dok = dokumentacja.split(";", -1);//" ;//kartoteki.split(";");
                        for (String kartoteka : dok) {

                            //  JOptionPane.showMessageDialog(null,kart);
                            try {
                                statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA4(?,?,?,?,?)}");
                                statement.setInt(1, id_kartoteka);
                                statement.setString(2, kartoteka);
                                statement.setString(3, Settings.getDocPath());
                                statement.setInt(4,punkt);
                                statement.setInt(5,new_kart);
                                statement.execute();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                logger.debug(ex.getMessage());
                            }

                        }

                    } else {
                        try {
                            statement = conect.prepareCall("{call XXX_LC_IMPORT_DOKUMENTACJA4(?,?,?,?,?)}");
                            statement.setInt(1, id_kartoteka);
                            statement.setString(2, dokumentacja);
                            statement.setString(3, Settings.getDocPath());
                            statement.setInt(4,punkt);
                            statement.setInt(5,new_kart);
                            statement.execute();
                        } catch (SQLException ex) {

                            ex.printStackTrace();
                            logger.debug(ex.getMessage());

                        }
                    }
                }


                return true;
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug(e.getMessage());
                // MainController.addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks());

                // Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
                ;
            } finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.debug(e.getMessage());
                }
            }


            //if(kartotek==0)      }


        }
        return false;
    }

}