package pl.hansonq.utils;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FirebirdConnector {
    private static String SQL_LINK=Settings.getSqlLink();//"jdbc:firebirdsql:localhost/3050:"+Settings.getDatabase()+"?sql_dialect=3&amp;encoding=UTF8";//"jdbc:firebirdsql:localhost/3050:"+"SYSTEMST.FB"/*Settings.getDatabase()*/+"?sql_dialect=3&amp;encoding=UTF8";
    private static String SQL_USER=Settings.getLogin();;//Settings.getLogin();// "SYSDBA";
    private static String SQL_PASS=Settings.getPassword();;//Settings.getPassword();// = "masterkey";
    private static final String SQL_CLASS = "org.firebirdsql.jdbc.FBDriver";
    private static String SQL_DATABASE;//=Settings.getDatabase();;

    private static FirebirdConnector connector = new FirebirdConnector();

    public static FirebirdConnector getInstance(){
        return connector;
    }

    public FirebirdConnector(){

       connect();
    }

    private Connection connection;
    public Connection getConnection(){
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