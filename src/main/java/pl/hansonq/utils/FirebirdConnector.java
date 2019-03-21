package pl.hansonq.utils;

import javafx.scene.control.Alert;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import pl.hansonq.dao.Impl.CartDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FirebirdConnector {
    private static String SQL_LINK = "jdbc:firebirdsql:" + Preferences.getPreferences().getSerwer() + "/3050:" + Preferences.getPreferences().getDatabase() + "?sql_dialect=3&amp;encoding=UTF8";//"jdbc:firebirdsql:localhost/3050:"+"SYSTEMST.FB"/*Settings.getDatabase()*/+"?sql_dialect=3&amp;encoding=UTF8";
    private static String SQL_USER = Preferences.getPreferences().getLogin();
    //Settings.getLogin();// "SYSDBA";
    private static String SQL_PASS = Preferences.getPreferences().getPassword();
    //Settings.getPassword();// = "masterkey";
    private static final String SQL_CLASS = "org.firebirdsql.jdbc.FBDriver";
    private static String SQL_DATABASE;//=Settings.getDatabase();;
    final static Logger logger = Logger.getLogger(FirebirdConnector.class);
    private static FirebirdConnector connector = new FirebirdConnector();

    public static FirebirdConnector getInstance() {
        return connector;
    }

    public FirebirdConnector() {

        DOMConfigurator.configure("log4j.xml");
        connect();


    }

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }


    public static FirebirdConnector getConnector() {
        return connector;
    }

    public static void setConnector(FirebirdConnector connector) {
        FirebirdConnector.connector = connector;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void connect() {


        try {


            //  Settings.loadSettings();
            Preferences.getPreferences();

            SQL_LINK = "jdbc:firebirdsql:" + Preferences.getPreferences().getSerwer() + "/3050:" + Preferences.getPreferences().getDatabase() + "?sql_dialect=3&amp;encoding=WIN1250";
            SQL_USER = Preferences.getPreferences().getLogin();// "SYSDBA";
            SQL_PASS = Preferences.getPreferences().getPassword();// = "masterkey";
            SQL_DATABASE = Preferences.getPreferences().getDatabase();



            //System.out.println(Settings.getDatabase());
            Class.forName(SQL_CLASS);

            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);

            System.out.println("Połączono z bazą danych!");
            logger.debug("Połączono z bazą danych!");


        } catch (SQLException ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych", "", "Błąd połączenia !" + ex.getMessage(), Alert.AlertType.ERROR);//e.printStackTrace();
            logger.debug("Błąd połączenia !" + ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych", "", "Błąd połączenia !" + ex.getMessage(), Alert.AlertType.ERROR);//e.printStackTrace();
            logger.debug("Błąd połączenia !" + ex.getMessage(), ex);
        } catch (Exception ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych", "", "Błąd połączenia !+\n" + ex.getMessage(), Alert.AlertType.ERROR);//e.printStackTrace();
            logger.debug("Błąd połączenia !" + ex.getMessage(), ex);
        }
    }

    public void connectionTest() {
        try {
            Preferences.getPreferences();
            //   Settings.loadSettings();

            SQL_LINK = "jdbc:firebirdsql:" + Preferences.getPreferences().getSerwer() + "/3050:" + Preferences.getPreferences().getDatabase() + "?sql_dialect=3&amp;encoding=UTF8";
            SQL_USER = Preferences.getPreferences().getLogin();// "SYSDBA";
            SQL_PASS = Preferences.getPreferences().getPassword();// = "masterkey";
            SQL_DATABASE = Preferences.getPreferences().getDatabase();




            Class.forName(SQL_CLASS);

            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);
            Utils.createSimpleDialog("Połączenie z bazą danych", "", "Połączono z bazą danych !", Alert.AlertType.INFORMATION);//e.printStackTrace();
            logger.debug("Połączono z bazą danych !");

        } catch (SQLException ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych", "", "Błąd połączenia !", Alert.AlertType.ERROR);//e.printStackTrace();
            logger.debug("Błąd połączenia !" + ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych", "", "Błąd połączenia !", Alert.AlertType.ERROR);//e.printStackTrace();
            logger.debug("Błąd połączenia !" + ex.getMessage(), ex);
        } catch (Exception ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych", "", "Błąd połączenia !", Alert.AlertType.ERROR);//e.printStackTrace();
            logger.debug("Błąd połączenia !" + ex.getMessage(), ex);
        }
    }
}