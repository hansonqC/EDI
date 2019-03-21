package pl.hansonq.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.hansonq.dao.DocumentInvoiceDao;
import pl.hansonq.dao.Impl.DocumentInvoiceDaoImpl;
import pl.hansonq.models.CartModel;
import pl.hansonq.models.DocumentInvoiceModel;
import pl.hansonq.models.InvoiceModel.CartModelEdi;
import pl.hansonq.models.InvoiceModel.InvoiceModel;
import pl.hansonq.models.PSBModel.LineModel;
import pl.hansonq.models.PSBModel.RowModel;
import pl.hansonq.utils.FirebirdConnector;
import pl.hansonq.utils.JaxB;
import pl.hansonq.utils.Preferences;
import pl.hansonq.utils.Utils;
import pl.hansonq.utils.progress_indicators.RingProgressIndicator;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController implements Initializable {

    private static FileInputStream fileInput;
    private String filePath;
    private String extension;
    List<String> faktury = listOfInvoices(Preferences.getPreferences().getInvoices());//(Settings.getListOfInvoices());
    List<String> faktury2;
    ObservableList<String> lista = FXCollections.observableArrayList();
    private ExecutorService executorService;
    List<String> invoices;
    List<String> fileToMove;
    private List<DocumentInvoiceModel> listOfXml;
    private List<DocumentInvoiceModel> listOfNumbers;
    DocumentInvoiceModel documentInvoiceModel;
    DocumentInvoiceDao documentInvoiceDao;
    List<String> documentsToConnectWithPZ;
    List<InvoiceModel> listOfDocumentsToConnect;
    private Service<Void> background;
    InvoiceModel invoiceModel;
    CartModelEdi cartModelEdi;
    CartModel cartModel;
    private Task task;
    List<RowModel> listOfFiles;
    String listaNiezgodnosci = "";
    static List<String> kartList;
    private int idUser;
    Alert alert;//=new ArrayList<>();
    private String nrDok;
    private int id_magazyn;
    @FXML
    private ComboBox combo;

    @FXML
    private TableView table;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuDatabaseConnection;

    @FXML
    private MenuItem menuDatabaseSettings;

    @FXML
    private MenuItem menuTestConnection;

    @FXML
    private MenuItem menuClose;

    @FXML
    private MenuItem menuOprogramie;

    @FXML
    private Button buttonImport, buttonRefresh;
    ;

    @FXML
    private ProgressBar progressBar;


    @FXML
    private Label labelUser, labelVersion;

    private static String lastVisitedDirectory = System.getProperty("user.home");


//    @FXML
//    private TableView table;
//    @FXML
//    private ProgressBar progressBar;
//    @FXML
//    private Menu menuDatabaseConnection;
////    @FXML
////    private ListView<String> listOfDocuments;
//
//    @FXML
//    private MenuItem menuDatabaseSettings, menuOprogramie, menuTestConnection, menuClose;
//    @FXML
//    MenuBar menuBar;
//    @FXML
//    ComboBox combo ;
//    @FXML
//   private TextArea  textArea2;
//    @FXML
//    Label labelVersion, labelUser;


    @FXML
    List<String> magazyny;

    final static String version = "1.0.0.1";
    final static Logger logger = Logger.getLogger(MainController.class);

    public MainController() {
        executorService = Executors.newSingleThreadExecutor();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences.getPreferences();
        executorService = Executors.newSingleThreadExecutor();
        //textArea2.setEditable(false);
        menuTestConnection.setOnAction(e -> testConnection());
        menuOprogramie.setOnAction(e -> about());
        menuDatabaseSettings.setOnAction(e -> settingsOpen());
        menuClose.setOnAction(e -> System.exit(0));
        buttonImport.setOnMouseClicked(e -> Start());//ImportKartotek());
        buttonRefresh.setOnMouseClicked(e -> Odswiez());
        //  listOfDocuments.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        invoices = new ArrayList<String>();
        listOfXml = new ArrayList<>();
        listOfNumbers = new ArrayList<>();
        try {
            DOMConfigurator.configure("log4j.xml");
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }


        labelVersion.setText("ver. " + version);
        labelUser.setText(LoginController.uzytkownik);

        magazyny = new ArrayList<>();
        try {
            documentInvoiceDao = new DocumentInvoiceDaoImpl();
            magazyny = documentInvoiceDao.getMagazyn();
            ObservableList<String> lista = FXCollections.observableArrayList(magazyny);

            combo.getItems().addAll(lista);
            combo.setValue(lista.get(0));
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());
        }
        // listOfDocuments.set
//        listOfDocuments.setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.SHIFT) {
//                listOfDocuments.getSelectionModel().selectAll();
//                if (faktury.size() > 0) {
//                    for (int i = 0; i < faktury.size(); i++) {
//                        //String item = "Item "+i ;
//                        //
//                    }
//                }
//            }
//        });
//        check.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                CheckIfChecked(newValue);
//
//            }
//        });
        //   listOfXml = LoadXml(invoices);
        //     JOptionPane.showMessageDialog(null, invoices.get(0));

        Odswiez();
        //FillTable();
        //   JOptionPane.showMessageDialog(null, faktury.size());
//        for (String faktura : faktury) {
//            faktury.set(faktury.indexOf(faktura), faktura + " - " + listOfXml.get(faktury.indexOf(faktura)).getHeaderModel().getInvoiceNumber());
//        }


    }

    private void ListaBledow() {
        TextArea textArea = new TextArea(listaNiezgodnosci);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

// Add a custom icon.
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));
        alert.setTitle("Import Faktur");
        alert.setHeaderText("\n\nZostały utworzone nowe kartoteki.\n\nProszę uzupełnić dane w podanych indeksach takie jak :" +
                "\n- ceny\n- cechy\n- grupy kartotekowe\netc.");
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();


    }

    private void FillTable() {
        table.getColumns().clear();
        List<RowModel> rowModels = LoadingFiles(faktury);
        TableColumn col1 = new TableColumn("V");
        TableColumn col2 = new TableColumn("Nazwa pliku XML");
        TableColumn col3 = new TableColumn("Numer faktury");
        col1.prefWidthProperty().bind(table.widthProperty().multiply(0.05));
        col2.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
        col3.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
        col1.setResizable(false);
        col2.setResizable(false);
        col3.setResizable(false);

        table.getColumns().addAll(col1, col2, col3);


        col1.setCellValueFactory(new PropertyValueFactory<RowModel, Boolean>("select"));
        col2.setCellValueFactory(new PropertyValueFactory<RowModel, String>("xmlName"));
        col3.setCellValueFactory(new PropertyValueFactory<RowModel, String>("invoiceNumber"));
        final ObservableList<RowModel> observableList = FXCollections.observableArrayList(
                rowModels
        );
        table.setItems(observableList);
        table.setPlaceholder(new Label("Nie załadowano żadnego pliku"));

    }

    private List<RowModel> LoadingFiles(List<String> files) {
        listOfFiles = new ArrayList<>();
        RowModel rowModel;
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            for (String file : files) {
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(Preferences.getPreferences().getInvoices() + file);

                doc.getDocumentElement().normalize();

                NodeList numbers = doc.getElementsByTagName("Number");

                Element number = (Element) numbers.item(0);

                NodeList invoiceNumbers = number.getChildNodes();
                String num = ((Node) invoiceNumbers.item(0)).getNodeValue().trim();
                //    JOptionPane.showMessageDialog(null,num);
                boolean set = false;
                rowModel = new RowModel(file, num);
                //       rowModel.setSelect(new CheckBox());
                //     JOptionPane.showMessageDialog(null, rowModel.toString());
                listOfFiles.add(rowModel);
                // Collections.sort(listOfFiles);
            }
            return listOfFiles;
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());
            return null;
        }

    }

//    private void Start1() {
//        JaxB.jaxbXMLToDocumentInvoiceModelObject(textFileImport.getText());
//    }

    private void Start() {
        Job();
        //   ProgressIndicator();
        //  PowiazPz(listOfDocumentsToConnect);

    }


    private boolean Job() {
        documentInvoiceDao = new DocumentInvoiceDaoImpl();
        String nrDok = "";
        int idNagl = 0;
        int idUser = 0;
        progressBar.setProgress(0.0d);
        buttonImport.setDisable(true);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try {
                    // for (String file : fileToMove) {


                    //  JOptionPane.showMessageDialog(null, listOfDocumentsToConnect.get(0));
                    // for(InvoiceModel model:listOfDocumentsToConnect){
                    //        JOptionPane.showMessageDialog(null, "ok");
                    //  }

                    //   idNagl=documentInvoiceDao.GetIdNagl()
                    //   documentInvoiceDao.UpdateNaglUzytkownik()
                } catch (Exception ex) {
                    logger.debug(ex.fillInStackTrace());
                }
                JOptionPane.showMessageDialog(null, "Zakończono import plików !", "Import EDI RAJMAN", JOptionPane.INFORMATION_MESSAGE);

                MoveImportedFile(fileToMove);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //  MoveImportedFile(fileToMove);
                //     JOptionPane.showConfirmDialog(null,listOfDocumentsToConnect.get(0));
//                if (PowiazPz(listOfDocumentsToConnect)) {
//                    JOptionPane.showMessageDialog(null, "Zakończono import plików !", "Import EDI INTER-ELEKTRO", JOptionPane.INFORMATION_MESSAGE);
//                    MoveImportedFile(fileToMove);
//
//                }
                Platform.runLater(() -> Odswiez());
                if (listaNiezgodnosci.length() > 0) {
                    Platform.runLater(() -> ListaBledow());
                }
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                //   Odswiez();
                if (wypychacz()) {

                    //   nowa wersja z watkiem
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Zakończono import plików !", "Import EDI RAJMAN", JOptionPane.INFORMATION_MESSAGE);

                    Platform.runLater(() -> End());
                }

            }
        };

        task = new Task<Void>() {
            @Override
            public Void call() {
                if (Run()) {
                    task.cancel();
                    if (task.isDone()) {
                        Platform.runLater(runnable1);
                        return null;
                    }
                }
                ;

                return null;
            }

        };
//          progressIndicator.progressProperty().bind(task.progressProperty());
//       progressIndicator.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
        return true;

    }

    ///  1  /////////////
    private void End() {
        MoveImportedFile(fileToMove);
        Odswiez();
        if (listaNiezgodnosci.length() > 0) {
            ListaBledow();
        }
    }

    private int GetMagazyn() {
        int id_magazyn = 0;
        JFrame frame = new JFrame("Import faktur...");
        java.net.URL imgURL3 = getClass().getClassLoader().getResource("logo.png");
        ImageIcon icon3 = new ImageIcon(imgURL3);
        frame.setLocationByPlatform(true);
        frame.setIconImage(icon3.getImage());
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        List<String> magazyny = new ArrayList<>();
        try {
            magazyny = documentInvoiceDao.getMagazyn();
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());
        }
        if (magazyny.size() > 0) {
            String[] mag = magazyny.stream().toArray(String[]::new);
            JComboBox jcd = new JComboBox(mag);
            jcd.setEditable(false);

            Object[] options = new Object[]{};
            JOptionPane jop = new JOptionPane("Wybierz magazyn na który ma być przyjęty towar z faktury",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            //add combos to JOptionPane
            jop.add(jcd);


            //create a JDialog and add JOptionPane to it
            JDialog diag = new JDialog();
            diag.getContentPane().add(jop);
            diag.pack();
            diag.setVisible(true);

        } else {
            logger.debug("Nie udało się pobrać listy magazynów");
        }


        return id_magazyn;
    }

    private boolean Run() {
        DocumentInvoiceDao dao;
        try {
            ObservableList<RowModel> dataList = FXCollections.observableArrayList();
            for (RowModel bean : listOfFiles) {
                if (bean.getSelect().isSelected()) {
                    invoices.add(bean.getXmlName());
                }
            }
            //   JOptionPane.showMessageDialog(null, invoices);
            //  JOptionPane.showMessageDialog(null, combo.getValue().toString());

            if ((!combo.getValue().equals("")) && (!combo.getSelectionModel().isEmpty())) {
                dao = new DocumentInvoiceDaoImpl();
               String mag = combo.getValue().toString();
                try {
                    dao.UpdateUrzzew(Preferences.getPreferences().getKodUrzZew(), mag);
                    Thread.sleep(500);
                  //  JOptionPane.showMessageDialog(null, mag);



                } catch (Exception ex) {
                    logger.debug(ex.fillInStackTrace());
                }
                listOfXml = LoadXml(invoices);
                ImportPlikow(listOfXml, combo.getValue().toString());
            } else if (combo.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Wybierz magazyn !", "Import faktur", JOptionPane.ERROR_MESSAGE);
            }


            return true;
        } catch (Exception ex) {
            logger.debug(ex.fillInStackTrace());

        }
        return false;
    }

//    private boolean Import(List<DocumentInvoiceModel> listOfDocumentInvoiceModels) {
//        List<InvoiceModel> invoicesList = new ArrayList<>();
//        try {
//            invoicesList = ImportPlikow(listOfDocumentInvoiceModels);
//            return true;
//        } catch (Exception ex) {
//            logger.debug(ex.fillInStackTrace());
//
//        }
//
////        } else if (!ImportPlikow()) {
////            return false;
//
//        return false;
//    }

    // Import Plików
    private List<InvoiceModel> ImportPlikow(List<DocumentInvoiceModel> listOfDocumentInvoiceModels2, String magazyn) {

        Preferences.getPreferences().setMagazyn(magazyn);

        fileToMove = new ArrayList<>();
        documentInvoiceDao = new DocumentInvoiceDaoImpl();
        List<InvoiceModel> modelList = new ArrayList<>();
        try {
            for (DocumentInvoiceModel documentInvoiceModel : listOfDocumentInvoiceModels2) {
                progressBar.setProgress(0.0d);
                // JOptionPane.showMessageDialog(null, invoices.get(0));
                String fileName = invoices.get(listOfDocumentInvoiceModels2.indexOf(documentInvoiceModel));
                InvoiceModel invoiceModel2;// = new InvoiceModel();
                CartModelEdi cartModelEdi;
                CartModel cartModel;//= new CartModelEdi();
                String nip1 = documentInvoiceModel.getInvoicePartiesModel().getSellerModel().getCompanyModel().getTaxID();
                String poNip = (nip1.replace("PL", "")).replace("-", "");
                String iln1 = documentInvoiceModel.getInvoicePartiesModel().getSellerModel().getCompanyModel().getILN();
                // String nazwapliku = xmlFile. //invoices.get(listOfXml.indexOf(documentInvoiceModel));
                int idKontrah = 0;
                int nrKontrah = 0;
                int urzzew_nagl = 0;

                List<Integer> seller = new ArrayList<>();
                String invoiceNumber = documentInvoiceModel.getHeaderModel().getNumber();
                boolean exist = documentInvoiceDao.CheckIfDocumentExists(invoiceNumber);
                boolean exist2 = documentInvoiceDao.CheckIfDocumentExists2(invoiceNumber);
                if (exist) {
                    JOptionPane.showMessageDialog(null, "Dokument o numerze: " + invoiceNumber + " był już importowany !\nPlik : " + fileName, "Błąd importu", JOptionPane.ERROR_MESSAGE);
                    logger.debug("Dokument o numerze: " + invoiceNumber + " był już importowany");
                   // Odswiez();
                    break;


                } else if (!exist) {
                    if (exist2) {
                        JOptionPane.showMessageDialog(null, "Dokument o numerze: " + invoiceNumber + " był już importowany !\nPlik : " + fileName, "Błąd importu", JOptionPane.ERROR_MESSAGE);
                        logger.debug("Dokument o numerze: " + invoiceNumber + " był już importowany");
                        //Odswiez();
                        break;

                    }
                }
                try {
                    seller = documentInvoiceDao.getKontrah(poNip, iln1);
                    //   JOptionPane.showMessageDialog(null, seller.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.debug(ex.fillInStackTrace());
                }

                if (seller.get(0) == 0) {
                    JOptionPane.showMessageDialog(null, "Nie odnaleziono kontrahenta dla dokumentu " + invoiceNumber + "\nPlik : " + fileName, "Błąd importu", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                idKontrah = seller.get(0);
                nrKontrah = seller.get(1);
                invoiceModel2 = new InvoiceModel();
                invoiceModel2.setNip(poNip);
                invoiceModel2.setFileName(fileName);
                invoiceModel2.setSeller(String.valueOf(idKontrah));
                invoiceModel2.setSellerNr(String.valueOf(nrKontrah));
                invoiceModel2.setInvoiceNumber(documentInvoiceModel.getHeaderModel().getNumber());
                invoiceModel2.setInvoiceDate(documentInvoiceModel.getHeaderModel().getInvoiceDate());
                invoiceModel2.setSum(Double.valueOf(documentInvoiceModel.getSummaryModel().getTotalGrossAmount()));
                invoiceModel2.setIdKontrah(idKontrah);
                // invoiceModel.setN
                List<CartModelEdi> list = new ArrayList<>();
                double lines = (documentInvoiceModel.getLinesModel().getInvoiceLines().size());
                double counter = 0.0;
                double progress = (1d / lines);
                for (LineModel line : documentInvoiceModel.getLinesModel().getInvoiceLines()) {


                    String kart_indeks = "";

                    cartModelEdi = new CartModelEdi();
                    cartModel = new CartModel();
                    cartModelEdi.setEan(line.getDetailModel().getEan());
                    cartModel.setKodEanDomyslny(line.getDetailModel().getEan());
                    cartModelEdi.setNetPice(line.getDetailModel().getUnitPrice());
                    cartModel.setOstatniaCena(line.getDetailModel().getUnitPrice());
                    cartModelEdi.setKartName(line.getDetailModel().getDescription());
                    cartModel.setNazwaSystemowa(line.getDetailModel().getDescription());
                    cartModelEdi.setQuantity(line.getDetailModel().getQuantity());
                    cartModel.setJednostka(line.getDetailModel().getUnitOfMeasure());
                    cartModelEdi.setUnit(line.getDetailModel().getUnitOfMeasure());
                    cartModel.setVat(line.getDetailModel().getTaxSymbol());
                    cartModelEdi.setTax(line.getDetailModel().getTaxSymbol());

                    // cartModelEdi.setIndeks();


                    //    String zamdost = line.getLineOrderModel().getBuyerOrderNumber();
                    // String poZamdost = zamdost.replace("ZAMDOST ", "");
                    //cartModelEdi.setZamdostNumber(zamdost);
//                if (line.getLineItemModel().getProductFeeDetailsModel() == null) {
//                    cartModelEdi.setNetPice(line.getLineItemModel().getInvoiceUnitNetPrice());
//                } else {
//                    cartModelEdi.setNetPice(line.getLineItemModel().getProductFeeDetailsModel().getUnitNetPriceWithoutFee());
//                }
                    String eanX = line.getDetailModel().getEan();
                    String supplierCodeX = line.getDetailModel().getSupplierItemCode();
                    cartModel.setIndeks(supplierCodeX);
                    kart_indeks = documentInvoiceDao.GetKartIndeksByEan(eanX);
                    if ((kart_indeks != null) && (!kart_indeks.isEmpty())) {
                        cartModelEdi.setIndeks(kart_indeks);
                        //  cartModel.setIndeks(kart_indeks);
                        logger.debug("Dodano kartotekę " + kart_indeks + " na podstawie kodu EAN : " + eanX);
                    }
                    if ((kart_indeks == null) || kart_indeks.isEmpty()) {
                        kart_indeks = documentInvoiceDao.GetKartIndeksBySupplierCode(supplierCodeX);

                    }
                    if ((kart_indeks != null) && (!kart_indeks.isEmpty())) {
                        cartModelEdi.setIndeks(kart_indeks);
                        //    cartModel.setIndeks(kart_indeks);
                        logger.debug("Dodano kartotekę " + kart_indeks + "\nna podstawie numeru EAN lub kodu producenta : " + supplierCodeX);

                    }

                    if ((kart_indeks == null) || kart_indeks.isEmpty()) {

                        JFrame frame1 = new JFrame("Wybierz właściwą kartotekę...");
                        java.net.URL imgURL2 = getClass().getClassLoader().getResource("logo.png");
                        ImageIcon icon2 = new ImageIcon(imgURL2);
                        frame1.setLocationByPlatform(true);
                        frame1.setIconImage(icon2.getImage());
                        frame1.setUndecorated(true);
                        frame1.setVisible(true);
                        frame1.setLocationRelativeTo(null);
                        logger.debug("Nie odnaleziono kartoteki\n" + line.getDetailModel().getDescription() + "\nna podstawie numer EAN ani kodu producenta : " + supplierCodeX);
                        JOptionPane.showMessageDialog(frame1, "Nie odnaleziono kartoteki\n" + line.getDetailModel().getDescription() + "\nna podstawie numeru EAN ani kodu producenta.\nWybierz właściwą kartotekę . . .", "Błąd importu pliku", JOptionPane.ERROR_MESSAGE);
//                                JFrame frame = new JFrame("Import faktur...");
//                                java.net.URL imgURL = getClass().getClassLoader().getResource("logo.png");
//                                ImageIcon icon = new ImageIcon(imgURL);
//                                frame.setIconImage(icon.getImage());
//                                frame.setUndecorated(true);
//                                frame.setVisible(true);
//                                frame.setLocationRelativeTo(null);

                        try {


                            String n = cartModelEdi.getKartName().trim().replace("  ", " ");
                            String[] nazwa = n.split(" ");
                            String nazwa1 = (nazwa[0] + "%" + nazwa[1]).trim().toUpperCase();
                            String nazwa2 = (nazwa[1] + "%" + nazwa[0]).trim().toUpperCase();
                            String nazwa3 = (nazwa[1] + "%" + nazwa[2]).trim().toUpperCase();
                            String nazwa4 = (nazwa[2] + "%" + nazwa[1]).trim().toUpperCase();
                            //   JOptionPane.showMessageDialog(null, "nazwa1 : " + nazwa1 + "\nnazwa2 : " + nazwa2);
                            kartList = new ArrayList<>();
                            try {
                                kartList = documentInvoiceDao.Kartoteki(nazwa1);
                            } catch (Exception ex2) {
                                logger.debug(ex2.getStackTrace());
                            }
                            if ((kartList.isEmpty()) || (kartList.size() == 0)) {
                                try {
                                    kartList = documentInvoiceDao.Kartoteki(nazwa2);
                                } catch (Exception ex2) {
                                    logger.debug(ex2.getStackTrace());
                                }
                            }
                            if ((kartList.isEmpty()) || (kartList.size() == 0)) {
                                try {
                                    kartList = documentInvoiceDao.Kartoteki(nazwa3);
                                } catch (Exception ex2) {
                                    logger.debug(ex2.getStackTrace());
                                }
                            }
                            if ((kartList.isEmpty()) || (kartList.size() == 0)) {
                                try {
                                    kartList = documentInvoiceDao.Kartoteki(nazwa4);
                                } catch (Exception ex2) {
                                    logger.debug(ex2.getStackTrace());
                                }
                            }
                            // Object[] listArray=(String[])kartList.toArray();
                            String[] listArray = kartList.stream().toArray(String[]::new);
//                            JFrame frame = new JFrame("Import faktur...1");
//                            java.net.URL imgURL = getClass().getClassLoader().getResource("logo.png");
//                            ImageIcon icon = new ImageIcon(imgURL);
//                            frame.setIconImage(icon.getImage());
//                            frame.setUndecorated(true);
//                            frame.setVisible(true);
//                            frame.setLocationRelativeTo(null);

                            JFrame frame2 = new JFrame("Import faktur...");
                            java.net.URL imgURL = getClass().getClassLoader().getResource("logo.png");
                            ImageIcon icon = new ImageIcon(imgURL);
                            frame2.setLocationByPlatform(true);
                            frame2.setIconImage(icon.getImage());
                            frame2.setUndecorated(true);
                            frame2.setVisible(true);
                            frame2.setLocationRelativeTo(null);
                            final JComboBox<String> combo = new JComboBox<>(listArray);

                            String[] options = {"      OK      ", "      Anuluj      ", "           Wyszukaj ręcznie             ", "           Dodaj nową kartotekę . . .        "};

                            String title = "Wybór kartoteki : " + cartModelEdi.getKartName();
                            int selection = JOptionPane.showOptionDialog(frame2, combo, title,
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,

                                    options, options[0]);
                            // frame2.dispose();
                            if (selection == 3) {

                                JOptionPane.showMessageDialog(null, "Zostanie utworzona nowa kartoteka na podstawie danych z faktury. . .", "Dodawanie kartotek", JOptionPane.PLAIN_MESSAGE);
                                //  JOptionPane.showMessageDialog(null,line.getDetailModel().getSupplierItemCode());
                                cartModelEdi.setIndeks(line.getDetailModel().getSupplierItemCode());
                                String nazwaskr = "";
                                if (cartModelEdi.getKartName().length() > 34) {
                                    nazwaskr = line.getDetailModel().getDescription().substring(0, 34);
                                } else {
                                    nazwaskr = cartModelEdi.getKartName();
                                }
                                cartModel.setIdentyfikator(nazwaskr);


//                                String s = (String) JOptionPane.showInputDialog(
//                                        frame2,
//                                        "Wyszukaj kartotekę\n" + cartModelEdi.getKartName() + "\nna podstawie indeksu : ",
//                                        "Kartoteka : "+cartModelEdi.getKartName(),
//                                        JOptionPane.QUESTION_MESSAGE);
//                                try {
//                                    String kartIndeks = documentInvoiceDao.GetKartIndeksByIndeks(s);
//                                    try {
//                                        Thread.sleep(100);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    if ((kartIndeks != null) && (!kartIndeks.isEmpty())) {
//                                        cartModelEdi.setIndeks(kartIndeks);
//                                        logger.debug("Dodano kartotekę " + kartIndeks);
//                                        JOptionPane.showMessageDialog(null, "Dodano kartotekę o indeksie : " + cartModelEdi.getIndeks(), "Wybór ręczny", JOptionPane.INFORMATION_MESSAGE);
//                                        frame2.dispose();
//                                    }
                                Object[] option = {"Zapisz ",
                                        "Anuluj"};
                                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 12)));
                                //  UIManager.put("OptionPane.background",new ColorUIResource(255,0,0));
                                int ok = JOptionPane.showOptionDialog(frame2,
                                        cartModelEdi.toString(),
                                        cartModelEdi.getKartName(),
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        option,
                                        option[0]);

                                frame2.dispose();
                                if (ok == 0) {
                                    try {
                                        //   JOptionPane.showMessageDialog(null,cartModel.toString());
                                        String Ean = documentInvoiceDao.NewKart(cartModel, 1);
                                        JOptionPane.showMessageDialog(frame2,
                                                "Dodano nową kartotekę :  " + cartModelEdi.getKartName(),
                                                "Nowa kartoteka", JOptionPane.QUESTION_MESSAGE);
                                        listaNiezgodnosci += "\nKartoteka : " + cartModelEdi.getKartName() + ", EAN : " + cartModelEdi.getEan() + "\n";

                                    } catch (Exception ex3) {
                                        logger.debug(ex3.getStackTrace());
                                    }
                                    frame2.dispose();
                                }
                                if (ok == 1) {
                                    try {
                                        frame2.dispose();
                                        break;


                                    } catch (Exception ex3) {
                                        logger.debug(ex3.getStackTrace());
                                    }
                                    frame2.dispose();
                                }
                            }
                            if (selection == 2) {
                                String s = (String) JOptionPane.showInputDialog(
                                        frame2,
                                        "Wyszukaj kartotekę\n" + cartModelEdi.getKartName() + "\nna podstawie indeksu : ",
                                        "Wprowadź indeks kartoteki",
                                        JOptionPane.QUESTION_MESSAGE);
                                if (s == null || (s != null && ("".equals(s)))) {
                                    JOptionPane.showMessageDialog(frame2, "Import został przerwany", "Wprowadź indeks kartoteki", JOptionPane.ERROR_MESSAGE);
                                    Odswiez();
                                    break;
                                }
//                                while((s.isEmpty())||(s.equals(""))||(s==null)){
//                                     s = (String) JOptionPane.showInputDialog(
//                                            frame2,
//                                            "Wyszukaj kartotekę\n" + cartModelEdi.getKartName() + "\nna podstawie indeksu : ",
//                                            "Wprowadź indeks kartoteki",
//                                            JOptionPane.QUESTION_MESSAGE);
//                                }
                                if ((!s.equals("")) && (!s.isEmpty()) && (s != null)) {
                                    try {
                                        String kartIndeks = documentInvoiceDao.GetKartIndeksByIndeks(s);
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        if ((kartIndeks != null) && (!kartIndeks.isEmpty())) {
                                            cartModelEdi.setIndeks(kartIndeks);
                                            logger.debug("Dodano kartotekę " + kartIndeks);
                                            JOptionPane.showMessageDialog(null, "Dodano kartotekę o indeksie : " + cartModelEdi.getIndeks(), "Wybór ręczny", JOptionPane.INFORMATION_MESSAGE);

                                            //   try {
//                                            int idKart = documentInvoiceDao.GetKartIdByIndeks(kartIndeks);
//                                            Thread.sleep(100);
//                                            documentInvoiceDao.UpdateSupplierNumber(idKart, supplierCodeX);
//                                            documentInvoiceDao.UpdateEan(idKart, eanX);
//                                        } catch (Exception ex) {
//                                            logger.debug(ex.fillInStackTrace());
//                                            System.out.println(ex.fillInStackTrace());
//                                        }
                                        }
                                        //  JOptionPane.showMessageDialog(null, s);
                                    } catch (Exception ex3) {
                                        logger.debug(ex3.getStackTrace());
                                    }
                                }
                                frame2.dispose();
                            }

                            if (selection == 1) {

//                                Platform.runLater(() -> {
//                                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                                    alert.setTitle("title");
//                                    alert.showAndWait();
//                                });
//

                                JOptionPane.showMessageDialog(null, "Nie wybrano kartoteki !\nWyszukaj kartotekę na podstawie indeksu. . .");


                                String s = (String) JOptionPane.showInputDialog(
                                        frame2,
                                        "Wyszukaj kartotekę\n" + cartModelEdi.getKartName() + "\nna podstawie indeksu : ",
                                        "Wprowadź indeks kartoteki",
                                        JOptionPane.QUESTION_MESSAGE);
                                try {
                                    String kartIndeks = documentInvoiceDao.GetKartIndeksByIndeks(s);
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if ((kartIndeks != null) && (!kartIndeks.isEmpty())) {
                                        cartModelEdi.setIndeks(kartIndeks);
                                        logger.debug("Dodano kartotekę " + kartIndeks);
//                                        try {
//                                            int idKart = documentInvoiceDao.GetKartIdByIndeks(kartIndeks);
//                                            Thread.sleep(100);
//                                            documentInvoiceDao.UpdateSupplierNumber(idKart, supplierCodeX);
//                                            documentInvoiceDao.UpdateEan(idKart, eanX);
//                                        } catch (Exception ex) {
//                                            logger.debug(ex.fillInStackTrace());
//                                            System.out.println(ex.fillInStackTrace());
//                                        }
                                        JOptionPane.showMessageDialog(null, "Dodano kartotekę o indeksie : " + cartModelEdi.getIndeks(), "Wybór ręczny", JOptionPane.INFORMATION_MESSAGE);
                                        frame2.dispose();
                                    }
                                } catch (Exception ex3) {
                                    logger.debug(ex3.getStackTrace());
                                }
                                frame2.dispose();
                            }

                            if (selection == 0) {
                                try {
                                    String longName = combo.getSelectedItem().toString();
                                    String kartIndeks = documentInvoiceDao.GetKartIndeksByName(longName);
                                    //   JOptionPane.showMessageDialog(null, kartIndeks);
                                    Thread.sleep(100);
                                    if ((kartIndeks != null) && (!kartIndeks.isEmpty())) {
                                        cartModelEdi.setIndeks(kartIndeks);
//                                        int idKart = documentInvoiceDao.GetKartIdByIndeks(kartIndeks);
//                                        Thread.sleep(100);
//                                        if (idKart > 0) {
//                                            //   JOptionPane.showMessageDialog(null, idKart + ", " + eanX + ", " + supplierCodeX);
//                                            documentInvoiceDao.UpdateEan(idKart, eanX);
//                                            documentInvoiceDao.UpdateSupplierNumber(idKart, supplierCodeX);
//                                        }

                                        logger.debug("Dodano kartotekę " + kart_indeks);
                                        JOptionPane.showMessageDialog(null, "Dodano kartotekę o indeksie : " + cartModelEdi.getIndeks(), "Lista wyboru", JOptionPane.INFORMATION_MESSAGE);
                                    }

                                } catch (Exception ex2) {
                                    logger.debug(ex2.getStackTrace());

                                }
                                frame2.dispose();
                            }
                            //    frame2.dispose();
                        } catch (Exception ex2) {
                            logger.debug(ex2.getStackTrace());
                        }

                        frame1.dispose();
                    }


                    //ostatni if


                    // ########################################################
                    //Odswiez();
                    //  continue;
//                    catch(Exception ex){
//                    System.out.println(ex.fillInStackTrace());
//
//                }


//                    Platform.runLater(() -> {
//                        try {
//                            ListOpen(kartList);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    int idKart = 0;
                    if ((!cartModelEdi.getIndeks().isEmpty()) && (cartModelEdi.getIndeks() != null)) {
                        try {
                            String indeks = cartModelEdi.getIndeks();
                            idKart = documentInvoiceDao.GetKartIdByIndeks(indeks);
                            Thread.sleep(100);
                            //  JOptionPane.showMessageDialog(null, idKart);
                            documentInvoiceDao.UpdateSupplierNumber(idKart, supplierCodeX);
                            documentInvoiceDao.UpdateEan(idKart, eanX);

                        } catch (Exception ex) {
                            logger.debug(ex.getStackTrace());
                        }
                    }
                    if (idKart != 0) {
                        // JOptionPane.showMessageDialog(null, idKart);
                        JFrame frame2 = new JFrame("Przelicz jednostkę miary...");
                        java.net.URL imgURL = getClass().getClassLoader().getResource("logo.png");
                        ImageIcon icon = new ImageIcon(imgURL);
                        frame2.setLocationByPlatform(true);
                        frame2.setIconImage(icon.getImage());
                        frame2.setUndecorated(true);
                        frame2.setVisible(true);
                        frame2.setLocationRelativeTo(null);
                        try {
                            String unit1 = line.getDetailModel().getUnitOfMeasure();
                            String ilosc = line.getDetailModel().getQuantity();
                            Double il = Double.valueOf(ilosc);
                            String iloscX = String.format("%.2f", il);
                            String kartoteka = cartModelEdi.getKartName();
                            String unit2 = documentInvoiceDao.UnitOfMeasure(idKart);
                            String unit1X = unit1.replace(".", "").replace(" ", "");
                            String unit2X = unit2.replace(".", "").replace(" ", "");
                            //  JOptionPane.showMessageDialog(frame2, unit1X + " : " + unit2X);
                            if (!(unit1X.toUpperCase().equals(unit2X.toUpperCase()))) {
                                String s = (String) JOptionPane.showInputDialog(
                                        frame2,
                                        cartModelEdi.getKartName() + "\n\nJednostka miary z dokumentu : " + unit1 + ", ilość : " + iloscX + "            \n\nJednostka miary z kartoteki : " + unit2 + "            .\n\nPrzelicz " + unit2 + " na " + unit1 + " i wprowadź poprawną ilość w " + unit1 + "             .",
                                        "Przelicz jednostkę miary :  ",
                                        JOptionPane.QUESTION_MESSAGE);
                                if ((s.isEmpty() && (s.equals("")))) {
                                    String jm = s.replace(",", ".").replace(" ", "");
                                    cartModelEdi.setQuantity(jm);
                                }
                            }

                            frame2.dispose();
                            // frame1.dispose();
                        } catch (Exception ex) {
                            logger.debug(ex.fillInStackTrace());
                            frame2.dispose();
                            // frame1.dispose();
                        }
                    }
                    list.add(cartModelEdi);

                    // JOptionPane.showMessageDialog(null, fileToMove.toString());
                    counter += progress;
                    progressBar.setProgress(counter);
                }
                ///  KONIEC LINII

                invoiceModel2.setPozycje(list);

                ///    JOptionPane.showMessageDialog(null, invoiceModel2.toString());
                ///  ZALOZENIE NAGLOWKA DOKUMENTU
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    // JOptionPane.showMessageDialog(null, invoiceModel);
                    urzzew_nagl = documentInvoiceDao.ImportPzNagl(invoiceModel2);
                    //   JOptionPane.showMessageDialog(null,"URZZEWNAGL :"+urzzew_nagl);
                    for (CartModelEdi cartModelEdi1 : invoiceModel2.getPozycje()) {
                        try {
                            //    JOptionPane.showMessageDialog(null, cartModelEdi1.toString());
                            documentInvoiceDao.ImportPzPoz(invoiceModel2, cartModelEdi1, urzzew_nagl);
                            // Thread.sleep();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Błąd importu pozycji dokumentu : " + fileName, "Błąd importu 1", JOptionPane.ERROR_MESSAGE);
                            buttonImport.setDisable(false);
                            //     buttonConnect.setDisable(false);
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nie udało się założyć nagłówka dla dokumentu : " + fileName);
                    //   Odswiez();
                }

                invoiceModel2.setId_urzzew_nagl(urzzew_nagl);

                try {
                    documentInvoiceDao.InsertNewDocumentNumber(invoiceModel2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Błąd zapisu do tabeli zaimportowanego pliku : " + fileName, "Błąd importu 2", JOptionPane.ERROR_MESSAGE);
                    //  Odswiez();
                }

//                if (wypychacz()) {
//                    JOptionPane.showMessageDialog(null, "Poprawnie zaimportowano plik " + fileName);
//
//
//                } else if (!wypychacz()) {
//                    JOptionPane.showMessageDialog(null, "Błąd importu pliku : " + fileName, "Błąd importu 3", JOptionPane.ERROR_MESSAGE);
//                }


                modelList.add(invoiceModel2);
                //   JOptionPane.showMessageDialog(null, fileName);

                fileToMove.add(fileName);
                //    JOptionPane.showMessageDialog(null, fileToMove.toString());
                // return invoiceModel2;
            }
        } catch (
                Exception ex)

        {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
            return null;
        }


        return modelList;
    }

    //        Wybór kartoteki ze spisu
    private Optional GetIndeksChoice(List<String> indeksNames) {
        String indeks = "";
        ObservableList<String> listOfIndekses = FXCollections.observableArrayList();
        listOfIndekses.addAll(indeksNames);
        ChoiceDialog<String> dialog = new ChoiceDialog<>();

        dialog.setHeaderText("Wskaż właściwą kartotekę...");
        dialog.setContentText("Wybierz z listy rozwijanej właściwą kartotekę");
        dialog.getItems().setAll(listOfIndekses);
        Optional<String> optional = dialog.showAndWait();

        return optional;
    }

    ///  OTWIERANIE LISTY PRODUKTÓW
    private void ListOpen(List<String> list) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("listView.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("EDI RAJMAN");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root, 820, 585);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        //scene.setUserAgentStylesheet(STY);
        primaryStage.setScene(scene);
//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent e) {
//                Platform.exit();
//                System.exit(0);
//
//            }
//        });
        primaryStage.setMaximized(false);
        // System.out.println(FirebirdConnector.getSqlLink());
        primaryStage.showAndWait();
    }

    //  Wiązanie PZ z ZAMDOST
//    private boolean PowiazPz(List<InvoiceModel> invoiceModels) {
//        DocumentInvoiceDao dao = new DocumentInvoiceDaoImpl();
//        //  ProgressIndicator();
//        int Pz;
//        int Zamdost;
//        boolean ok = false;
//        try {
//            for (InvoiceModel item : invoiceModels) {
//                for (CartModelEdi cartModelEdi : item.getPozycje()) {
//                    int idZamDost = 0;
//                    int idPz = 0;
//                    idPz = dao.GetIdNaglPZ(invoiceModels.get(invoiceModels.indexOf(item)).getInvoiceNumber());
//                    //   JOptionPane.showMessageDialog(null, idPz);
//                    idZamDost = dao.GetIdNaglZAMDOST(cartModelEdi.getZamdostNumber(), item.getIdKontrah());
//                 //   JOptionPane.showMessageDialog(null, "PZ: " + idPz + "\nZAMDOST: " + idZamDost);
//                    //logger.debug("PZ: " + idPz + " : "+item.getInvoiceNumber()+ "\nZAMDOST: " + idZamDost+" : "+cartModelEdi.getZamdostNumber());
//
//                    if ((idPz != 0) && (idZamDost != 0)) {
//
//                        if (dao.PowiazPZ(idZamDost, idPz)) {
//                            JOptionPane.showMessageDialog(null, "Dokumenty powiązne poprawnie", "Import EDI INTER-ELEKTRO", JOptionPane.INFORMATION_MESSAGE);
//
//                            ok = true;
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Nie odnaleziono nagłówka dokumentu Zamówienia o numerze : " + cartModelEdi.getZamdostNumber() + "\nBądź dokument już zrealizownay lub anulowany", "Błąd importu", JOptionPane.ERROR_MESSAGE);
//                        ok = false;
//                        continue;
//                    }
//
//
//                }
//
//            }
//            return ok;
//        } catch (Exception ex) {
//            logger.debug(ex.fillInStackTrace());
//            ex.printStackTrace();
//        }
//        return false;
//    }


    // Wypchnięcie dokumentów z bufora
    private boolean wypychacz() {

        List<String> commands = new ArrayList<String>();

        commands.add("schtasks.exe");
        commands.add("/RUN");
        commands.add("/TN");
        commands.add("\"URZZEW_REALIZ_PSB\"");
        //   commands.add(Settings.getSystem());

        ProcessBuilder builder = new ProcessBuilder(commands);
        try {
            Process p = builder.start();
            p.waitFor();
            System.out.println(p.exitValue()); // 0 : OK
            logger.debug(p.exitValue());
            int result = p.exitValue();
            if (result == 0) {
                //  Odswiez();
                return true;

            } else if (result == 1) {
                //  Odswiez();
                return false;
            }
            // 1 : Error
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return false;
        }
        return false;   //  MoveImportedFile(fileToMove); //Odswiez();
    }

    // Przeniesienie pliku po imporcie i powiązaniu do folderu DONE
    private void MoveImportedFile(List<String> files) {
        for (String file : files) {
            InputStream inStream = null;
            OutputStream outStream = null;

            try {
                //   JOptionPane.showMessageDialog(null, file);
                File afile = new File(Preferences.getPreferences().getInvoices() + file);
                File bfile = new File(Preferences.getPreferences().getInvoices() + "DONE\\" + file);
//JOptionPane.showMessageDialog(null, bfile);
                inStream = new FileInputStream(afile);
                //  JOptionPane.showMessageDialog(null, afile);
                Thread.sleep(100);
                outStream = new FileOutputStream(bfile);
                Thread.sleep(100);
                //  JOptionPane.showMessageDialog(null, bfile);
                byte[] buffer = new byte[1024];

                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {

                    outStream.write(buffer, 0, length);

                }
                Thread.sleep(100);
                inStream.close();
                afile.delete();
                outStream.close();
                Thread.sleep(1000);

                //delete the original file
               // if (afile.delete()) {
                    System.out.println("File is copied successful!");
                    logger.debug("File is copied successful!");
            //    }


            } catch (IOException e) {
                e.printStackTrace();
            }


//                File fileToMove = new File(file);
//                boolean temp=false;
//              //  JOptionPane.showMessageDialog(null, Settings.getListOfInvoices() + "DONE\\" + file);
//                  try {
//                      temp = fileToMove.renameTo(new File(Settings.getListOfInvoices() + "DONE\\" + file));//file.substring(file.lastIndexOf("\\", file.length()))));
//                  }catch(Exception ex){
//                      logger.debug(ex.fillInStackTrace());
//                      ex.printStackTrace();
//                  }
//                if (temp) {
//                    System.out.println("File renamed and moved successfully");
//                    logger.debug("File renamed and moved successfully");
//
//                } else {
//                    System.out.println("Failed to move the file");
//                    logger.debug("Failed to move the file");
//                }
            catch (Exception ex) {
                logger.debug(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Błąd podczas przenoszenia pliku do katalogu zaimportowanych plików !\n" + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    //  Odświeżanie widoku
    private boolean Odswiez() {
        invoices.clear();
        progressBar.setProgress(0.0d);
        //listOfXml.clear();
        //  listOfDocuments.getItems().clear();
        faktury.clear();
        faktury = new ArrayList<>();
        try {
            faktury = listOfInvoices(Preferences.getPreferences().getInvoices());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Import PZ");
            alert.setHeaderText("");
            alert.setContentText("W katalogu nie ma żadnych dokumentów PZ !");
            alert.showAndWait();
            logger.debug(ex.getMessage());
        }
        //  listOfDocuments = new ListView<>();
//        listOfDocuments.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
//            @Override
//            public ObservableValue<Boolean> call(String item) {
//                BooleanProperty observable = new SimpleBooleanProperty();
//                observable.addListener((obs, wasSelected, isNowSelected) ->
//                        sprawdz(item, isNowSelected, false)
//                );
//                return observable;
//            }
//        }));
//        listOfNumbers = LoadXml(faktury);
//        faktury2=new ArrayList<>();
//        for (String item : faktury) {
//            faktury2.add((faktury.indexOf(item)), item += " - " + (listOfNumbers.get(faktury.indexOf(item)).getHeaderModel().getInvoiceNumber()));
//        }


//           if (faktury.size() > 0) {
//            listOfDocuments.getItems().addAll(faktury);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Import PZ");
//            alert.setHeaderText("");
//            alert.setContentText("W katalogu nie ma żadnych dokumentów PZ !");
//            alert.showAndWait();
//        }

        buttonImport.setDisable(false);
        //buttonConnect.setDisable(false);
        //listOfDocuments.setDisable(false);
        FillTable();
        return true;
    }


    //  METODY DODATKOWE

    // Tworzenie listy plików z folderu z fakturami i wyświetlanie ich na liście
    private List<String> listOfInvoices(String path) {
        File folder = new File(path);
        List<String> list = new ArrayList<>();
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if ((listOfFiles[i].isFile()) && (listOfFiles[i].getName().contains(".xml"))) {// (listOfFiles[i].getName().contains("RPSH")) &&
                list.add(listOfFiles[i].getName());
                //   System.out.println(listOfFiles[i].getName());
//            } else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
            }

        }
        return list;
    }

    // Sprawdzanie zaznaczenia i zwraca listę zaznaczonych plików
//    private List<String> sprawdz(String item, boolean zaznacz, boolean all) {
//        double rotate = listOfDocuments.getRotate();
//        if (zaznacz) {
//            invoices.add(item);
////            for (int i = 0; i < 361; i++) {
////                try {
////                    listOfDocuments.setRotate(rotate += i);
////
////                    Thread.sleep(100);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }
//
//
//            //    JOptionPane.showMessageDialog(null, invoices.toString() + " " + listOfXml.size());
//        } else if (!zaznacz) {
//            invoices.remove(item);
//            //   listOfDocuments.setRotate(rotate -= 10);
//            //     JOptionPane.showMessageDialog(null, invoices.toString() + " " + listOfXml.size());
//        }
//        return invoices;
//    }

//    // TWORZENIE LISTY ZAMDOST
//    private List<String> listOfZamdost(String path) {
//        List<String> zamowienia = new ArrayList<>();
//        DocumentInvoiceModel invoiceModel = null;
//
//        for (String faktura : faktury) {
//            invoiceModel = new DocumentInvoiceModel();
//            String path2 = path + faktura;
//            //  JOptionPane.showMessageDialog(null, path2);
//
//            //   invoiceModel = LoadXml(path2);
//            for (LineModel lineModel : invoiceModel.getLinesModel().getInvoiceLines()) {
//                String zamdost = lineModel.getLineOrderModel().getBuyerOrderNumber();
//                if (zamdost.contains("ZAMDOST")) {
//                    zamowienia.add(zamdost);
//
//                }
//            }
//            //    String zamdost = invoiceModel.getLinesModel().getInvoceLines().get(0).getLineOrderModel().getBuyerOrderNumber();
//
//
//        }
//
//
//        return zamowienia;
//    }

    // ZAMIANA LISTY FAKTUR W LISTĘ MODELI FAKTUR
    private List<DocumentInvoiceModel> LoadXml(List<String> invoicesList) {
        List<DocumentInvoiceModel> listOfInvoicesXml = new ArrayList<>();
        for (String invoice : invoicesList) {
            //  String inv=invoice.substring(0,invoice.lastIndexOf("xml")+3);
            String file = Preferences.getPreferences().getInvoices() + invoice;


            //CartBpModel cartBpModel = new CartBpModel();
            try {
                InputStream reader = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                logger.debug(e.getMessage());
            }

            File xmlFile = new File(file);
            FileReader fr = null;
            try {
                fr = new FileReader(xmlFile);
            } catch (FileNotFoundException e) {
                logger.debug(e.getMessage());
            }
            BufferedReader br = new BufferedReader(fr);


            String line = null;
            try {
                line = br.readLine();
                // br.close();///////
            } catch (IOException e) {
                logger.debug(e.getMessage());
            }
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    logger.debug(e.getMessage());
                }
            }

            // static String plik = file;
            String outXml = sb.toString();
            documentInvoiceModel = JaxB.jaxbXMLToDocumentInvoiceModelObject(file);
            listOfInvoicesXml.add(documentInvoiceModel);


        }
        return listOfInvoicesXml;
    }


    //////////// METODY DODATKOWE ///////////////
    // Wczytanie ostatnio otwartego folderu
    private String loadPath() {
        String path;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("path.properties");

            if (input != null) {
                // input=new FileInputStream("path.properties");
                //   System.out.println("Brak pliku z ustawieniami");
                //    savePath(path);
                prop.loadFromXML(input);
                path = prop.getProperty("lastPath");
                if ((path.isEmpty()) || (path == null) || (path.equals(""))) {
                    path = "C:";
                }
                return path;
            }
            if (input == null) {
                return "c:";
            }


        } catch (Exception ex) {
            return null;//  Utils.createSimpleDialog("Odczyt danych", "", "Błąd odczytu ustawień !", Alert.AlertType.ERROR);


        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.debug(e.getMessage());
                    return null;//  Utils.createSimpleDialog("Odczyt danych", "", "Błąd odczytu ustawień !", Alert.AlertType.ERROR);

                }
            }
        }
        return null;
    }

    // Zapis ostatnio otwartego folderu
    private void savePath(String path) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("path.properties");

            prop.setProperty("lastPath", path);

            // save properties to project root folder
            prop.storeToXML(output, null);
            loadPath();


        } catch (Exception ex) {
            Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisu ustawień !\n" +
                    "\"Sprawdź poprawność wprowadzonych danych !\"", Alert.AlertType.ERROR));


        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    Platform.runLater(() -> Utils.createSimpleDialog("Zapis danych", "", "Błąd zapisuu ustawień !\n" +
                            "Sprawdź poprawność wprowadzonych danych !", Alert.AlertType.ERROR));

                }
            }
        }
    }

    // Otwieranie ustawień
    private void settingsOpen() {

        Parent root2 = null;
        try {
            root2 = FXMLLoader.load(getClass().getClassLoader().getResource("settingsView.fxml"));
            Stage secondStage = new Stage();
            secondStage.setTitle("Ustawienia");
            secondStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
            secondStage.setResizable(false);
            secondStage.initStyle(StageStyle.DECORATED);
            // secondStage.centerOnScreen();
            secondStage.setScene(new Scene(root2, 585, 325));
            secondStage.show();
        } catch (IOException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }


    }

    private void ProgressIndicator() {
        // Parent root3 = null;
        try {
            RingProgressIndicator ringProgressIndicator = new RingProgressIndicator();
            ringProgressIndicator.setRingWidth(20);
            ringProgressIndicator.makeIndeterminate();
            //root3 = FXMLLoader.load(getClass().getClassLoader().getResource("progressIndicator.fxml"));
            StackPane root3 = new StackPane();
            root3.getChildren().add(ringProgressIndicator);
            root3.setPrefWidth(150.0);
            root3.setPrefHeight(150.0);
            Stage secondStage = new Stage();
            secondStage.setTitle("Trwa import plików...");
            //    secondStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
            secondStage.setResizable(false);
            secondStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root3, 200, 200);
            scene.setFill(Color.BLUE);
            secondStage.setScene(scene);
            secondStage.setOpacity(0.5);
            secondStage.show();
            new WorkerThread(ringProgressIndicator).start();
        } catch (Exception e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    class WorkerThread extends Thread {
        RingProgressIndicator rpi;
        int progress = 0;

        public WorkerThread(RingProgressIndicator rpi) {
            this.rpi = rpi;

        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    logger.debug(e.fillInStackTrace());
                }

                Platform.runLater(() -> rpi.setProgress(progress));

                progress += 1;
                if (progress > 100) {
                    break;
                }
            }


        }

    }

    // O programie
    private void about() {

        Alert alertAbout = new Alert(Alert.AlertType.INFORMATION);
        alertAbout.setTitle("O programie");
        alertAbout.setHeaderText("");
        alertAbout.setContentText("EDI RAJMAN\nCopyright by STREAMSOFT\nKontakt : lczereda@streamsoft.pl");

        alertAbout.showAndWait();

    }

    // Test połączenia
    private void testConnection() {

        try {
            FirebirdConnector connector = FirebirdConnector.getInstance();
            connector.connectionTest();

        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            Utils.createSimpleDialog("Test połączenia z bazą danych", "", "Połączenie z bazą danych nie powiodło się !", Alert.AlertType.ERROR);
        }

    }

    // Logowanie w oknie
//    private void addLog(String text) {
//        try {
//            textArea2.appendText(text + "\n");
//        } catch (Exception ex) {
//            logger.debug(ex.getMessage());
//        }
//    }

    // Zaznaczanie / odznaczanie wszystkich faktur
//    private void CheckIfChecked(boolean value) {
//
//
//        if (value) {
//            listOfDocuments.getSelectionModel().selectAll();
//
//
//        }
//
//        //  listOfDocuments.getCellFactory().call(listOfDocuments);
//        else if (!value) {
//            listOfDocuments.getSelectionModel().clearSelection();
//        }
//    }

    // Wybór pliku do importu - otwieranie okna ( nieaktywne)
//    private void chooseFile() {
//        //  progressIndicator.setDisable(false);
//        progressBar.setDisable(false);
//        textArea2.clear();
//        File recordsDir;
//
//
//        FileChooser fileChooser = new FileChooser();
//
//        fileChooser.setTitle("Wskaż lokalizację pliku");
//        FileChooser.ExtensionFilter extFilter1 =
//                new FileChooser.ExtensionFilter("Faktura EDI", "*.xml");
//
//        FileChooser.ExtensionFilter extFilter3 =
//                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.*");
//
//        // fileChooser.getExtensionFilters().add(extFilter2);
//        fileChooser.getExtensionFilters().add(extFilter1);
//        fileChooser.getExtensionFilters().add(extFilter3);
////        filePath=loadPath();
////       if(!filePath.equals("")){
//        // JOptionPane.showMessageDialog(null,loadPath().toString());
//
//        String filePath = loadPath();
//
//        Path path = Paths.get(filePath);
//        if (Files.exists(path)) {
//            recordsDir = new File(loadPath());
//        } else {
//            recordsDir = new File(System.getProperty("user.home"));
//        }
//
//        File existDirectory = recordsDir.getParentFile();
//        try {
//            fileChooser.setInitialDirectory(existDirectory);
//        } catch (Exception ex) {
//            logger.debug(ex.getStackTrace());
//            fileChooser.setInitialDirectory(recordsDir);
//            JOptionPane.showMessageDialog(null, existDirectory);
//        }
////       }
//
//
//        File file = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
//        try
//
//        {
//            filePath = file.getAbsolutePath();
//            //  JOptionPane.showMessageDialog(null, filePath);
//            savePath(filePath);
//            //   int kat=filePath.lastIndexOf("\\");
//
//            extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
//            //   Settings.setLastVisitedDirectory(filePath);
//            //   Settings.saveDirectory(filePath);
//
//            textFileImport.setText(filePath);
//            //runImport();
//            //  RowModel rowModel=new RowModel(new CheckBox(),filePath,)
//
//
//        } catch (
//                Exception ex)
//
//        {
//            logger.debug(ex.getMessage());
//            Utils.createSimpleDialog("Wskaż plik", "", "Nie wskazano pliku do zaimportowania !", Alert.AlertType.ERROR);
//        }
//    }


    //  okay.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                JOptionPane pane = getOptionPane((JComponent)e.getSource());
//                                pane.setValue(okay);
//                            }
//                        });
//                        okay.setEnabled(false);
//                        final JButton cancel = new JButton("Cancel");
//                        cancel.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                JOptionPane pane = getOptionPane((JComponent)e.getSource());
//                                pane.setValue(cancel);
//                            }
//                        });
//                        Object[] options1 = { "Try This Number", "Choose A Random Number", "Quit" };
//
//                        JPanel panel = new JPanel();
//                        panel.add(new JLabel("Enter number between 0 and 1000"));
//                        JTextField textField = new JTextField(10);
//                        panel.add(textField);
//
//                        int result = JOptionPane.showOptionDialog(null, panel, "Enter a Number",
//                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
//                                options1, null);
//                        if (result == JOptionPane.YES_OPTION) {
//                            JOptionPane.showMessageDialog(null, textField.getText());
//                        }
    // String[] weekdays = possibilities;

////  URUCHAMIANIE W RÓŻNTCH WĄTKACH - NIEUŻYWANE
//    private void Run3() {
//        // ImportPlikow();
//        if (ImportPlikow()) {
//            // JOptionPane.showMessageDialog(null, "ok");
//            MoveImportedFile(fileToMove);
//        } else {
//            JOptionPane.showMessageDialog(null, "no");
//        }
//    }
//
//    private void Run2() {
//
//
//        Runnable runnable1 = new Runnable() {
//            @Override
//            public void run() {
//                ImportPlikow();
//            }
//        };
//        Runnable runnable2 = new Runnable() {
//            @Override
//            public void run() {
//                //      MoveImportedFile(fileToMove);
//            }
//        };
//        Runnable runnable3 = new Runnable() {
//            @Override
//            public void run() {
//                Odswiez();
//            }
//        };
//        Task task2 = new Task() {
//            @Override
//            protected Object call() throws Exception {
//                Odswiez();
//                return null;
//            }
//        };
//        Task task = new Task() {
//            @Override
//            protected Object call() throws Exception {
//                //   MoveImportedFile(fileToMove);
//                return null;
//            }
//        };
//        final Thread t1 = new Thread(runnable1);
//        // assume T1 is a Runnable
//
//        try {
//            t1.start();
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
////        final Thread t2 = new Thread(runnable2);
////
////        try {
////            t2.start();
////            t2.join();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        final Thread t3 = new Thread(runnable3);
//
////        try {
////
////            t3.start();
////            t3.join();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        //     executorService.execute(runnable1);
//        //  executorService.
//        //    executorService.submit(task);
//        //    executorService.submit(task2);
//        //   Platform.runLater(runnable2);
//        //  executorService.execute(runnable2);
//
//        //   Thread t1 = new Thread(runnable1);
//        //  Thread t2 = new Thread(runnable2);
//        // Thread t3 = new Thread(runnable2);
//        //  t1.start();
//        //t2.start();
//    }

// Stare metody uruchamiające import
//////////////////URUCHAMIANIE WSZUSYKICH 3 METOD


//    private InvoiceModel ImportXml(DocumentInvoiceModel xmlFile, String fileName) {
//        InvoiceModel invoiceModel;// = new InvoiceModel();
//        CartModelEdi cartModelEdi; //= new CartModelEdi();
//        String nip1 = xmlFile.getInvoicePartiesModel().getSellerModel().getTaxID();
//        String iln1 = xmlFile.getInvoicePartiesModel().getSellerModel().getILN();
//        // String nazwapliku = xmlFile. //invoices.get(listOfXml.indexOf(documentInvoiceModel));
//        int idKontrah = 0;
//        int nrKontrah = 0;
//        int urzzew_nagl = 0;
//        String kart_indeks = "";
//        List<Integer> seller = new ArrayList<>();
//        String invoiceNumber = xmlFile.getHeaderModel().getInvoiceNumber();
//        if (documentInvoiceDao.CheckIfDocumentExists(invoiceNumber)) {
//            JOptionPane.showMessageDialog(null, "Dokument o numerze: " + invoiceNumber + " był już importowany !\nPlik : " + fileName, "Błąd import", JOptionPane.ERROR_MESSAGE);
//            //  Odswiez();
//            return null;
//
//        }
//        if (documentInvoiceDao.CheckIfDocumentExists2(invoiceNumber)) {
//            JOptionPane.showMessageDialog(null, "Dokument o numerze: " + invoiceNumber + " był już importowany !\nPlik : " + fileName, "Błąd import", JOptionPane.ERROR_MESSAGE);
//            //  Odswiez();
//            return null;
//
//        }
//        if (documentInvoiceDao.getKontrah(nip1, iln1) != null) {
//            seller = documentInvoiceDao.getKontrah(nip1, iln1);
//        }
//        idKontrah = seller.get(0);
//        nrKontrah = seller.get(1);
//        invoiceModel = new InvoiceModel();
//        invoiceModel.setFileName(fileName);
//        invoiceModel.setSeller(String.valueOf(idKontrah));
//        invoiceModel.setSellerNr(String.valueOf(nrKontrah));
//        invoiceModel.setInvoiceNumber(xmlFile.getHeaderModel().getInvoiceNumber());
//        invoiceModel.setInvoiceDate(xmlFile.getHeaderModel().getInvoiceDate());
//        invoiceModel.setNip(xmlFile.getInvoicePartiesModel().getSellerModel().getTaxID());
//        invoiceModel.setIln(xmlFile.getInvoicePartiesModel().getSellerModel().getILN());
//        invoiceModel.setSum(Double.valueOf(xmlFile.getSummaryModel().getTotalGrossAmount()));
//        List<CartModelEdi> list = new ArrayList<>();
//        for (LineModel line : xmlFile.getLinesModel().getInvoiceLines()) {
//            cartModelEdi = new CartModelEdi();
//            cartModelEdi.setEan(line.getLineItemModel().getEAN());
//            cartModelEdi.setNetPice(line.getLineItemModel().getInvoiceUnitNetPrice());
//            cartModelEdi.setQuantity(line.getLineItemModel().getInvoiceQuantity());
//            cartModelEdi.setKartName(line.getLineItemModel().getItemDescription());
//            cartModelEdi.setZamdostNumber(line.getLineOrderModel().getBuyerOrderNumber());
//            if (line.getLineItemModel().getProductFeeDetailsModel() == null) {
//                cartModelEdi.setNetPice(line.getLineItemModel().getInvoiceUnitNetPrice());
//            } else {
//                cartModelEdi.setNetPice(line.getLineItemModel().getProductFeeDetailsModel().getUnitNetPriceWithoutFee());
//            }
//            String ean = line.getLineItemModel().getEAN();
//            try {
//                kart_indeks = documentInvoiceDao.GetKartIndeks(line.getLineItemModel().getEAN());
//                if ((kart_indeks.isEmpty()) || (kart_indeks == null)) {
//                    JOptionPane.showMessageDialog(null, "Błąd importu pliku " + fileName, "Nie znaleziono kartoteki o numerze EAN: " + ean + "\nlub podany numer EAN wskazany powtarza się.\nImport został przerwany\nNależy uzupełnić kod EAN w podanej kartotece", JOptionPane.ERROR_MESSAGE);
//                    logger.debug("Nie znaleziono kartoteki o numerze EAN: " + ean);
//                    //   Odswiez();
//                    return null;
//                } else {
//                    cartModelEdi.setIndeks(kart_indeks);
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, "Nie znaleziono kartoteki o numerze EAN: " + ean + "\nImport został przerwany\nNależy uzupełnić kod EAN w podanej kartotece : " + cartModelEdi.getKartName(), "Błąd importu pliku " + fileName, JOptionPane.ERROR_MESSAGE);
//                logger.debug("Nie znaleziono kartoteki o numerze EAN: " + ean + " (" + cartModelEdi.getKartName() + ")\n" + ex.getMessage());
//                //    Odswiez();
//                return null;
//            }
//            list.add(cartModelEdi);
//        }
//        invoiceModel.setPozycje(list);
//        ///  ZALOZENIE NAGLOWKA DOKUMENTU
//        try {
//            Thread.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            urzzew_nagl = documentInvoiceDao.ImportPzNagl(invoiceModel);
//
//            for (CartModelEdi cartModelEdi1 : invoiceModel.getPozycje()) {
//                documentInvoiceDao.ImportPzPoz(invoiceModel, cartModelEdi1, urzzew_nagl);
//            }
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Nie udało się założyć nagłówka dla dokumentu : " + fileName);
//            //   Odswiez();
//        }
//
//        invoiceModel.setId_urzzew_nagl(urzzew_nagl);
//
//        try {
//            documentInvoiceDao.InsertNewDocumentNumber(invoiceModel);
//    //    } catch (Exception ex) {
//       JOptionPane.showMessageDialog(null, "Błąd zapisu do tabeli zaimportowanego pliku : " + fileName, "Błąd importu 2", JOptionPane.ERROR_MESSAGE);
//  Odswiez();
//    }

//  if (wypychacz()) {
// int lol= PowiazPz(invoiceModel);
//    documentsToConnectWithPZ.add(nrdok2);
//  fileToMove.add(fileName);
//  int pz=documentInvoiceDao.GetIdNaglPZ(nrdok2);
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                wypychacz();
//            }
//        };
//        Platform.runLater(runnable);
//        JOptionPane.showMessageDialog(null, "Poprawnie zaimportowano plik " + fileName);
//                    for (String pz :)
//   Odswiez();
//        return invoiceModel;
//
//        } else if (!wypychacz()) {
//            JOptionPane.showMessageDialog(null, "Błąd importu pliku : " + fileName, "Błąd importu 3", JOptionPane.ERROR_MESSAGE);
//
//
//        }


//  return null;
//   }

//    String nip1 = documentInvoiceModel.getInvoicePartiesModel().getSellerModel().getTaxID();
//    String iln1 = documentInvoiceModel.getInvoicePartiesModel().getSellerModel().getILN();
//    String nazwapliku = invoices.get(listOfXml.indexOf(documentInvoiceModel));
//    int idKontrah = 0;
//    int nrKontrah = 0;
//    int urzzew_nagl = 0;
//    String kart_indeks = "";
//    List<Integer> seller = new ArrayList<>();
//    //  List<String> listOfZamdost = new ArrayList<>();
//
//    String invoiceNumber = documentInvoiceModel.getHeaderModel().getInvoiceNumber();
//                if (documentInvoiceDao.CheckIfDocumentExists(invoiceNumber)) {
//        JOptionPane.showMessageDialog(null, "Dokument o numerze: " + invoiceNumber + " był już importowany !\nPlik : " + nazwapliku, "Błąd import", JOptionPane.ERROR_MESSAGE);
//        Odswiez();
//        continue;
//    }
//                if (documentInvoiceDao.getKontrah(nip1, iln1) != null) {
//        seller = documentInvoiceDao.getKontrah(nip1, iln1);
//    }
//    idKontrah = seller.get(0);
//    nrKontrah = seller.get(1);
//    invoiceModel = new InvoiceModel();
//                invoiceModel.setFileName(nazwapliku);
//                invoiceModel.setSeller(String.valueOf(idKontrah));
//                invoiceModel.setSellerNr(String.valueOf(nrKontrah));
//                invoiceModel.setInvoiceNumber(documentInvoiceModel.getHeaderModel().getInvoiceNumber());
//                invoiceModel.setInvoiceDate(documentInvoiceModel.getHeaderModel().getInvoiceDate());
//                invoiceModel.setNip(documentInvoiceModel.getInvoicePartiesModel().getSellerModel().getTaxID());
//                invoiceModel.setIln(documentInvoiceModel.getInvoicePartiesModel().getSellerModel().getILN());
//                invoiceModel.setSum(Double.valueOf(documentInvoiceModel.getSummaryModel().getTotalGrossAmount()));
//    List<CartModelEdi> list = new ArrayList<>();
//                for (LineModel line : documentInvoiceModel.getLinesModel().getInvoceLines()) {
//        cartModelEdi = new CartModelEdi();
//        cartModelEdi.setEan(line.getLineItemModel().getEAN());
//        cartModelEdi.setNetPice(line.getLineItemModel().getInvoiceUnitNetPrice());
//        cartModelEdi.setQuantity(line.getLineItemModel().getInvoiceQuantity());
//        cartModelEdi.setKartName(line.getLineItemModel().getItemDescription());
//        cartModelEdi.setZamdostNumber(line.getLineOrderModel().getBuyerOrderNumber());
//        if (line.getLineItemModel().getProductFeeDetailsModel() == null) {
//            cartModelEdi.setNetPice(line.getLineItemModel().getInvoiceUnitNetPrice());
//        } else {
//            cartModelEdi.setNetPice(line.getLineItemModel().getProductFeeDetailsModel().getUnitNetPriceWithoutFee());
//        }
//        String ean = line.getLineItemModel().getEAN();
//        try {
//            kart_indeks = documentInvoiceDao.GetKartIndeks(line.getLineItemModel().getEAN());
//            if ((kart_indeks.isEmpty()) || (kart_indeks == null)) {
//                JOptionPane.showMessageDialog(null, "Błąd importu pliku " + nazwapliku, "Nie znaleziono kartoteki o numerze EAN: " + ean + "\nlub podany numer EAN wskazany powtarza się.\nImport został przerwany\nNależy uzupełnić kod EAN w podanej kartotece", JOptionPane.ERROR_MESSAGE);
//                logger.debug("Nie znaleziono kartoteki o numerze EAN: " + ean);
//                Odswiez();
//                return false;
//            } else {
//                cartModelEdi.setIndeks(kart_indeks);
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Nie znaleziono kartoteki o numerze EAN: " + ean + "\nImport został przerwany\nNależy uzupełnić kod EAN w podanej kartotece : " + cartModelEdi.getKartName(), "Błąd importu pliku " + nazwapliku, JOptionPane.ERROR_MESSAGE);
//            logger.debug("Nie znaleziono kartoteki o numerze EAN: " + ean + " (" + cartModelEdi.getKartName() + ")\n" + ex.getMessage());
//            Odswiez();
//            return false;
//        }
//        list.add(cartModelEdi);
//    }
//                invoiceModel.setPozycje(list);
//    ///  ZALOZENIE NAGLOWKA DOKUMENTU
//                try {
//        Thread.sleep(20);
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
//                try {
//        urzzew_nagl = documentInvoiceDao.ImportPzNagl(invoiceModel);
//
//        for (CartModelEdi cartModelEdi1 : invoiceModel.getPozycje()) {
//            documentInvoiceDao.ImportPzPoz(invoiceModel, cartModelEdi1, urzzew_nagl);
//        }
//
//    } catch (Exception ex) {
//        JOptionPane.showMessageDialog(null, "Nie udało się założyć nagłówka dla dokumentu : " + nazwapliku);
//        Odswiez();
//    }
//
//                invoiceModel.setId_urzzew_nagl(urzzew_nagl);
//
//                try {
//        documentInvoiceDao.InsertNewDocumentNumber(invoiceModel);
//    } catch (Exception ex) {
//        JOptionPane.showMessageDialog(null, "Błąd zapisu do tabeli zaimportowanego pliku : " + nazwapliku, "Błąd importu 2", JOptionPane.ERROR_MESSAGE);
//        Odswiez();
//    }
//
//                if (wypychacz()) {
//        // int lol= PowiazPz(invoiceModel);
//        //    documentsToConnectWithPZ.add(nrdok2);
//        //  fileToMove.add(nazwapliku);
//        //  int pz=documentInvoiceDao.GetIdNaglPZ(nrdok2);
//        JOptionPane.showMessageDialog(null, "Poprawnie zaimportowano plik " + nazwapliku);
////                    for (String pz :)
////                        Odswiez();
//
//    } else if (!wypychacz()) {
//        JOptionPane.showMessageDialog(null, "Błąd importu pliku : " + nazwapliku, "Błąd importu 3", JOptionPane.ERROR_MESSAGE);
//        continue;
//
//    }


//    private void Run() {
//
//        background = new Service<Void>() {
//            @Override
//            protected Task<Void> createTask() {
//                return new Task<Void>() {
//                    @Override
//                    protected Void call() throws Exception {
//                        ImportPlikow();
//                        background.cancel();
//                        return null;
//                    }
//                };
//            }
//        };
//
////        background.setOnScheduled(new EventHandler<WorkerStateEvent>() {
////            @Override
////            public void handle(WorkerStateEvent event) {
////                Odswiez();
////             MoveImportedFile(fileToMove);//
////            }
////        });
//        background.restart();
//    }
//
//    private void runImport() {
//        if (!textFileImport.getText().isEmpty()) {
//            textArea2.clear();
//
//            progressBar.setProgress(0.0d);
//
//            buttonImport.setDisable(true);
//            loadButton.setDisable(true);
//        }
//        task = new Task<Void>() {
//
//
//            @Override
//            public Void call() {
//                ImportPlikow();
//
//
//                // ImportKartotek();
//                task.cancel();
//                if (task.isDone()) {
//                    JOptionPane.showMessageDialog(null, "Poprawnie zaimportowano pliki");
//                    MoveImportedFile(fileToMove);
//                    Odswiez();
//
//                }
//
//                return null;
//            }
//
//        };
//        new Thread(task).start();
//    }//

//            listOfDocuments.cellFactoryProperty().setValue(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
//                @Override
//                public ObservableValue<Boolean> call(String param) {
//
//                }
//            }));


//                listOfDocuments.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setText(null);
//                    } else {
//                        setText(item.toString());
//                    }
//                }
//            };
//            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
//                if (event.getButton()== MouseButton.SECONDARY && (! cell.isEmpty())) {
//                    String item = cell.getItem();
//                    System.out.println("Right clicked "+item);
//                }
//            });
//            return cell ;
//        });


//    private void chooseFileOnStart() {
//        EventHandler<WindowEvent> handler = new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//
//            }
//        };
//    }


//    private void start() {
//
//        ScheduledFuture future = Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new MyScheduledTask(), 0, 10, TimeUnit.SECONDS);
//        ExecutorService executor = Executors.newFixedThreadPool(3); // pool composed of 3 threads
//        for (int i = 0; i < 3; i++) {
//            // for the example assuming that the 3 threads execute the same task.
//            executor.execute(new AnyTask());
//        }
//        // This will make the executor accept no new threads
//        // and finish all existing threads in the queue
//        executor.shutdown();
//        // expect current thread to wait for the ending of the 3 threads
//        try {
//            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        future.cancel(false); // to exit properly the scheduled task
//        // reprocessing the scheduled task only one time as expected
//        new Thread(new Sched.start());
//
//    }
//
//
//}}
//
//class MyScheduledTask implements Runnable {
//    @Override
//    public void run() {
//        //Process scheduled task here
//    }
//}
//
//class AnyTask implements Runnable {
//    @Override
//    public void run() {
//        //Process job of threads here;
//    }
//
//
//
//


//   System.out.println(sb);
//  JOptionPane.showMessageDialog(null,documentInvoiceModel.getHeaderModel().getInvoiceDate());
//        int stop = outXml.lastIndexOf("<data>");
//
//        int end = stop;
//        String xml = outXml.substring(stop, outXml.length());
//     System.out.println(outXml);
//        byte data[] = outXml.getBytes();
//        String file2 = null;
//        FileOutputStream out = null;
//        // String timestamp = String.valueOf(Timestamp.valueOf(LocalDateTime.now())).substring(0, (String.valueOf(Timestamp.valueOf(LocalDateTime.now())).length() - 4));
//
//
//        try {
//            file2 = file.replace(".xml", "_nowy.xml");
////                    . replace(".xml", "") + "_" + timestamp.replace("-", "").replace(":", "").
////
////                    replace(" ", "").
////
////                    replace(".xml_", "") + ".xml";
//            out = new FileOutputStream(file2);
//            out.write(data);
//            out.close();
//        } catch (FileNotFoundException e) {
//            logger.debug(e.getMessage());
//        } catch (IOException e) {
//            logger.debug(e.getMessage());
//        }

// FileWriter fileWriter=new FileWriter(plik,);
//cartBpModel = JaxB.jaxbCartBpModelXMLToObject(file2);

// JOptionPane.showMessageDialog(null, documentInvoiceModel.getHeaderModel().getInvoiceDate());

//        if (!cartBpModel.getEan().isEmpty()) {
//            cartBpModel.setEan(cartBpModel.getEan().replace("e", ""));
//        }
//   textArea2.appendText(documentInvoiceModel.toString());


//
//    private void LoadCSV(String file, String extension) throws IOException {
//
//
////
////        try (
////                Reader reader = Files.newBufferedReader(Paths.get(file));
////        ) {
////            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
////            strategy.setType(CartBpModel.class);
////            String[] memberFieldsToBindTo = {"kod", "dlugosc","szerokosc","wysokosc","objetosc","waga","waga objetosciowa"};
////            strategy.setColumnMapping(memberFieldsToBindTo);
////
////            CsvToBean<CartBpModel> csvToBean = new CsvToBeanBuilder(reader)
////                    .withMappingStrategy(strategy)
////                    .withSkipLines(1)
////                    .withIgnoreLeadingWhiteSpace(true)
////                    .build();
////
////            Iterator<CartBpModel> myUserIterator = csvToBean.iterator();
////
////            while (myUserIterator.hasNext()) {
////                CartBpModel myUser = myUserIterator.next();
////                System.out.println("Name : " + myUser.getName());
////                System.out.println("Email : " + myUser.getEmail());
////                System.out.println("PhoneNo : " + myUser.getPhoneNo());
////                System.out.println("Country : " + myUser.getCountry());
////                System.out.println("---------------------------");
////            }
////        }
////    }
////
//
//
////        try (
////                Reader reader = Files.newBufferedReader(Paths.get(file));
////                CSVParser csvParser = new CSVParser().
////        ) {
////            for (CSVRecord csvRecord : csvParser) {
////                // Accessing values by Header names
////                String name = csvRecord.get("Name");
////                String email = csvRecord.get("Email");
////                String phone = csvRecord.get("Phone");
////                String country = csvRecord.get("Country");
////
////                System.out.println("Record No - " + csvRecord.getRecordNumber());
////                System.out.println("---------------");
////                System.out.println("Name : " + name);
////                System.out.println("Email : " + email);
////                System.out.println("Phone : " + phone);
////                System.out.println("Country : " + country);
////                System.out.println("---------------\n\n");
////            }
////        }
////    }
//
//
////        try (
////                Reader reader = Files.newBufferedReader(Paths.get(file));
////                CSVReader csvReader = new CSVReader(reader);
////        ) {
////            // Reading Records One by One in a String array
////            String[] nextRecord;
////            while ((nextRecord = csvReader.readNext()) != null) {
////                System.out.println("Name : " + nextRecord[0]);
////                System.out.println("Email : " + nextRecord[1]);
////                System.out.println("Phone : " + nextRecord[2]);
////                System.out.println("Country : " + nextRecord[3]);
////                System.out.println("==========================");
////            }
////        }
//
////
////        BufferedReader br = null;
////        String line = "";
////        String csvSplitBy = Settings.getSeparator();
////        List<String[]> rows = new ArrayList<>();
////
////
////        try {
////
////            br = new BufferedReader(new FileReader(file));
////            while ((line = br.readLine()) != null) {
////                String[] dataLine = line.split(csvSplitBy);
////                rows.add(dataLine);
////            }
////
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            if (br != null) {
////                try {
////                    br.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////        for (String[] row : rows) {
////            for (String string : row) {
////                JOptionPane.showMessageDialog(null, rows.get(2));
////            }
////        }
//
//    }}


//    private void loadFile2(String file, String extension) throws IOException {
//
//
//        fileInput = new FileInputStream(file);
//        switch (extension) {
//            case ".xlxs":
//                workbook = new XSSFWorkbook(fileInput);
//                break;
//            case ".xlx":
//                workbook = new HSSFWorkbook(fileInput);
//                break;
//        }
//        Workbook wb = new XSSFWorkbook(fileInput);
//
//        Sheet sheetProduct = wb.getSheetAt(0);//StreamingReader.builder().rowCacheSize(1000).bufferSize(102400).sstCacheSize(1485760).open(fileInput).getSheetAt(0);//wb.getSheetAt(0);
//        Sheet sheetProperty = wb.getSheetAt(1);
//
//
////        Task task = new Task<Void>() {
////            @Override public Void call() {
////                static final int max = 1000000;
////                for (int i=1; i<=max; i++) {
////                    if (isCancelled()) {
////                        break;
////                    }
////                    updateProgress(i, max);
////                }
////                return null;
////            }
////        };
////        ProgressBar bar = new ProgressBar();
////        bar.progressProperty().bind(task.progressProperty());
////        new Thread(task).start();
//
//        TableColumn tableColumn;
//
//
//        int columns = sheetProduct.getRow(0).getLastCellNum();
//        int columnsProperty = sheetProperty.getRow(0).getLastCellNum();
//        int rows = sheetProduct.getLastRowNum() + 1;
//        //   JOptionPane.showMessageDialog(null, rows);
////        int rowsCount=0;
////        for(int i=2;i<=rows;i++){
////           if(!(sheetProduct.getRow(i).getCell(0).getStringCellValue()).isEmpty()){
////               rowsCount++;
////           }
////        }
//
//        double progress = (1d / rows);
//        double counter = 0.0;
//
//        //int rowsProperty = sheetProperty.getLastRowNum();
//        //  List cartList = new ArrayList();
//        DataFormatter dataFormatter = new DataFormatter();
//
//        //String Message;
//        //Iterator<Row> rowIterator = sheetProduct.rowIterator();
//        int r = 0;
//
//
//        for (Row row : sheetProduct) {
//            counter += progress;
//            cartModel = new CartModel();
//            if (row.getRowNum() <= 1) {
//                continue;
//            }
//
//
//            try {
//                if (cartDao.AddCart4(cartModel, 1)) {
//                    addLog("Poprawnie zaimportowano kartotekę o indeksie : " + cartModel.getIndeks() + " w wierszu : " + (row.getRowNum() + 1));
//
//
//                } else {
//
//
//                    addLog("Nie zaimportowano kartoteki " + cartModel.getIndeks() + " w wierszu : " + (row.getRowNum() + 1));
//
//                }
//
//                cartModel = null;
//            } catch (Exception ex) {
//                logger.debug(ex.getMessage());
//            }
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                logger.debug(e.getMessage());
//            }
//        }
/////koniec wiersza
//        progressBar.setProgress(counter);
//
////            if(progressBar.getProgress()==1.0d){
////
////                progressBar.setDisable(true);
////
////            }
////            if(progressIndicator.getProgress()==1.0d){
////                progressIndicator.setDisable(true);
////            }
//
//
//    }
//private void Import() {
//        if (cartBpDao.Import(cartBpModel)) {
//            textArea2.appendText("\n\nPoprawnie zaimportowano cechy kartoteki o u numerze EAN : " + cartBpModel.getEan());
//        } else {
//            Alert alertAbout = new Alert(Alert.AlertType.ERROR);
//            alertAbout.setTitle("PARCELCUBE IMPORT");
//            alertAbout.setHeaderText("");
//            alertAbout.setContentText("Błąd importu cech kartoteki !");
//
//            alertAbout.showAndWait();
//        }
//    }


//    private void runImport() {
//        if (!textFileImport.getText().isEmpty()) {
//            textArea2.clear();
//            progressBar.setProgress(0.0d);
//
//            buttonImport.setDisable(true);
//            loadButton.setDisable(true);
//        }
//        task = new Task<Void>() {
//
//
//            @Override
//            public Void call() {
//                ImportKartotek();
//                task.cancel();
//                progressBar.setProgress(0.0d);
//                return null;
//            }
//
//        };
////          progressIndicator.progressProperty().bind(task.progressProperty());
////       progressIndicator.progressProperty().bind(task.progressProperty());
//        new Thread(task).start();
//    }
//   try {
//            Runtime.getRuntime().exec("cmd /b start \"\"C:\\Users\\lukasz.czereda\\Desktop\\EDI_IMPORT\\start.bat");
//            ProcessBuilder pb = new ProcessBuilder("cmd", "/c",
//                    "C:\\Users\\lukasz.czereda\\Desktop\\EDI_IMPORT\\URZZEW.lnk");
//            Process p = pb.start();
//            p.waitFor();
//        } catch (Exception ex) {
//            logger.debug(ex.getMessage());
//            System.out.println(ex.getMessage());
//        }

}






