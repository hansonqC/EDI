package pl.hansonq.dao.Impl;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import pl.hansonq.dao.CartBpDao;
import pl.hansonq.models.CartBpModel;
import pl.hansonq.utils.FirebirdConnector;

import java.sql.*;

public class CartBpDaoImpl implements CartBpDao {


    final static Logger logger = Logger.getLogger(CartBpDaoImpl.class);

    FirebirdConnector conn = FirebirdConnector.getInstance();
    Connection conect = conn.getConnection();

    public CartBpDaoImpl() {
        DOMConfigurator.configure("log4j.xml");
    }


    @Override
    public boolean Import(CartBpModel cartBPModel) {


        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            conect.setAutoCommit(true);
            conect.setTransactionIsolation(1);

            statement = conect.prepareCall("{call XXX_LC_IMPORTKART_BP(?,?, ?,?,?,?,?,?}");
            statement.registerOutParameter(8, Types.INTEGER);
            statement.setString(1, cartBPModel.getEan());
            statement.setString(2, cartBPModel.getDlugosc());
            statement.setString(3, cartBPModel.getSzerokosc());
            statement.setString(4, cartBPModel.getWysokosc());
            statement.setString(5, cartBPModel.getObjetosc());
            statement.setString(6, cartBPModel.getWaga());
            statement.setString(7, cartBPModel.getWaga_objetosciowa());
            statement.execute();
            int id_kartopteki = statement.getInt(8);
            statement.close();




            // ResultSet rs = null;
//        try {
//            conect.setAutoCommit(true);
//            conect.setTransactionIsolation(1);
//
//            statement = conect.prepareCall("{call XXX_LC_IMPORT_CECH(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//            statement.registerOutParameter(1, Types.INTEGER);
//            statement.registerOutParameter(2, Types.INTEGER);
//
//
//
//
            if (id_kartopteki != 0) {
                return true;
            } else {
                return false;
            }

        } catch (
                SQLException ex)

        {
            ex.printStackTrace();
            logger.debug(ex.getMessage() + " - dotyczy kartoteki :" + cartBPModel.getEan());
            return false;
        }

    }
}
