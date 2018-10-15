package pl.hansonq.utils;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FirebirdConnectorSystem {
    private static String SQL_LINK="jdbc:firebirdsql:localhost/3050:"+Settings.getSystemDatabase()+"?sql_dialect=3&amp;encoding=UTF8";//"jdbc:firebirdsql:localhost/3050:"+"SYSTEMST.FB"/*Settings.getDatabase()*/+"?sql_dialect=3&amp;encoding=UTF8";
    private static String SQL_USER=Settings.getLogin();;//Settings.getLogin();// "SYSDBA";
    private static String SQL_PASS=Settings.getPassword();;//Settings.getPassword();// = "masterkey";
    private static final String SQL_CLASS = "org.firebirdsql.jdbc.FBDriver";
    private static String SQL_DATABASE;//=Settings.getDatabase();;

    private static FirebirdConnectorSystem connector = new FirebirdConnectorSystem();

    public static FirebirdConnectorSystem getInstance(){
        return connector;
    }

    public FirebirdConnectorSystem(){

        connect();
    }

    private Connection connection;
    public Connection getConnection(){
        return connection;
    }



    public static FirebirdConnectorSystem getConnector() {
        return connector;
    }

    public static void setConnector(FirebirdConnectorSystem connector) {
        FirebirdConnectorSystem.connector = connector;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public  void connect() {
        try {
            //System.out.println(Settings.getDatabase());
            Class.forName(SQL_CLASS);

            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);

            System.out.println("Połaczono z bazą danych!");
            //Logger


        } catch (SQLException ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych","","Błąd połączenia !"+ex.getMessage(), Alert.AlertType.ERROR);//e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Utils.createSimpleDialog("Połączenie z bazą danych","","Błąd połączenia !"+ex.getMessage(), Alert.AlertType.ERROR);//e.printStackTrace();
        }catch(Exception ex){
            Utils.createSimpleDialog("Połączenie z bazą danych","","Błąd połączenia !+\n"+ex.getMessage(), Alert.AlertType.ERROR);//e.printStackTrace();
        }
    }
    public  void connectionTest() {
        try {

            Class.forName(SQL_CLASS);

            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);
            Utils.createSimpleDialog("Połączenie z bazą danych","","Połączono z bazą danych !", Alert.AlertType.INFORMATION);//e.printStackTrace();


        } catch (SQLException e) {
            Utils.createSimpleDialog("Połączenie z bazą danych","","Błąd połączenia !", Alert.AlertType.ERROR);//e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Utils.createSimpleDialog("Połączenie z bazą danych","","Błąd połączenia !", Alert.AlertType.ERROR);//e.printStackTrace();
        }catch(Exception ex){
            Utils.createSimpleDialog("Połączenie z bazą danych","","Błąd połączenia !", Alert.AlertType.ERROR);//e.printStackTrace();
        }
    }
}