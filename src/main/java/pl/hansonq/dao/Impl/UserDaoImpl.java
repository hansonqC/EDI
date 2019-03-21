package pl.hansonq.dao.Impl;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import pl.hansonq.dao.UserDao;
import pl.hansonq.utils.FirebirdConnectorSystem;
import pl.hansonq.utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {


    FirebirdConnectorSystem connector = FirebirdConnectorSystem.getInstance();
    final static Logger logger = Logger.getLogger(DocumentInvoiceDaoImpl.class);

    public UserDaoImpl() {
        DOMConfigurator.configure("log4j.xml");
    }

    @Override
    public boolean login(String username, String password) {

        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM UZYTKOWNIK WHERE upper(LOGIN) =upper(?)");
            preparedStatement.setString(1, username);
            ResultSet resulSet = preparedStatement.executeQuery();
            if (!resulSet.next()) {  // na pocz�tku jest -1, sprawdzamy, jezeli jest 0 tzn �e jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego u�ytkownika
                return false;
            }
            logger.debug(String.valueOf(true + " metoda login "));
           return  Utils.streamHash(resulSet.getInt("ID_UZYTKOWNIK"), resulSet.getString("HASLO"), password);  // has�o poprawne


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.fillInStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.fillInStackTrace());
        }
        return false;
    }

    @Override
    public String Uzytkownik(String username, String password) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT FIRST 1 * FROM UZYTKOWNIK WHERE upper(LOGIN) =upper(?)");
            preparedStatement.setString(1, username);
            ResultSet resulSet = preparedStatement.executeQuery();
            if (!resulSet.next()) {  // na pocz�tku jest -1, sprawdzamy, jezeli jest 0 tzn �e jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego u�ytkownika
                return "Brak takiego uzytkownika w bazie !";
            }
            if (Utils.streamHash(resulSet.getInt("ID_UZYTKOWNIK"), resulSet.getString("HASLO"), password)) {
                logger.debug(String.valueOf(true + " metoda uzytkownik "));
                return resulSet.getString("NAZWISKOIMIE");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e.fillInStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.fillInStackTrace());
        }
        return "Brak takiego uzytkownika w bazie !";
    }

}
