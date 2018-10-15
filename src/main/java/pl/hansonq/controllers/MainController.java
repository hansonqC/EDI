package pl.hansonq.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.hansonq.dao.CartDao;
import pl.hansonq.dao.Impl.CartDaoImpl;
import pl.hansonq.models.CartModel;
import pl.hansonq.models.Index;
import pl.hansonq.utils.FirebirdConnector;
import pl.hansonq.utils.Utils;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController implements Initializable {
    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Row rowProperty;
    private static Row rowPropertyId;

    private static Cell cell;
    private static Cell cellProperty;
    private static Cell cellPropertyId;
    private static FileInputStream fileInput;
    private CartModel cartModel = new CartModel();
    private CartDao cartDao = new CartDaoImpl();
    private String filePath;
    private String extension;
    ObservableList<String> lista = FXCollections.observableArrayList();
    private ExecutorService executorService;

    private static String lastVisitedDirectory = System.getProperty("user.home");
    @FXML
    private Menu menuDatabaseConnection;
    @FXML
    private ListView<String> log;

    @FXML
    private MenuItem menuDatabaseSettings, menuOprogramie, menuTestConnection, menuClose;
    @FXML
    MenuBar menuBar;
    @FXML
    private TextArea textArea, textArea2;
    @FXML
    private Button loadButton, buttonImport;
    @FXML
    private TextField textFileImport;

    @FXML
    private ToggleGroup importOption;

    @FXML
    private RadioButton radioButton1;

    @FXML
    private TableView tableView;
    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton radioButton4;

    @FXML
    private RadioButton radioButton5;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // tableView=new TableView();

        executorService = Executors.newSingleThreadExecutor();
        textArea.setText("1- dodaje nowe kartoteki, nie zmienia istniejących\n" +
                "2 - zastępuje informacje na istniejących kartotekach nowymi danymi z cennika oraz tworzy nowe kartoteki \n(jeśli pole puste w pliku cennika informacja w bazie nie zostaje wyzerowana)\n(czyści pola, które w cenniku wpisane mają \"0\" lub inny ustalony symbol)\n" +
                "3 - dopisuje informacje z cennika tylko w tych kartotekach, w których dane pole było puste\n" +
                "4 - aktualizuje tylko ceny (jeśli cena pusta lub równa 0 - nie zerować poprzedniej wartości)\n" +
                "5 - usuwanie kartotek\n");

        // tableView.getItems().clear();
        menuTestConnection.setOnAction(e -> testConnection());
        menuOprogramie.setOnAction(e -> about());
        menuDatabaseSettings.setOnAction(e -> settingsOpen());
        loadButton.setOnMouseClicked(e -> chooseFile());
        menuClose.setOnAction(e -> System.exit(0));
        buttonImport.setVisible(false);
        importOption.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (importOption.getSelectedToggle() != null) {
                    buttonImport.setVisible(true);
                }
            }
        });
        buttonImport.setOnMouseClicked(e -> ImportKartotek());

    }

//    private void ImportKartotek()
//
//    {
//        try {
//            executorService.execute(new Runnable(){
//                loadFile(filePath, extension);
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void execute() {
        Index index = new Index();
        index.delecteCart(12391);
    }

    private void chooseFile() {
        //  tableView.getColumns().clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(lastVisitedDirectory));
        fileChooser.setTitle("Wskaż lokalizację cennika");
        FileChooser.ExtensionFilter extFilter1 =
                new FileChooser.ExtensionFilter("Plik cennika", "*.xls");
        FileChooser.ExtensionFilter extFilter2 =
                new FileChooser.ExtensionFilter("Plik cennika", "*.xlsx");
        FileChooser.ExtensionFilter extFilter3 =
                new FileChooser.ExtensionFilter("Wszystkie pliki", "*.*");

        fileChooser.getExtensionFilters().add(extFilter2);
        fileChooser.getExtensionFilters().add(extFilter1);
        fileChooser.getExtensionFilters().add(extFilter3);
        File file = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
        try {
            filePath = file.getAbsolutePath();
            extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());

            textFileImport.setText(filePath);
        } catch (Exception ex) {
            Utils.createSimpleDialog("Wskaż plik", "", "Nie wskzano pliku do zaimportowania\nBłąd : " + ex.getMessage(), Alert.AlertType.ERROR);
        }
        //   if (file != null) {

//        importOption.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
//
//                if (importOption.selectedToggleProperty(). == 1) {
//
//                    System.out.println(importOption.getSelectedToggle().getUserData().toString());
//                    // Do something here with the userData of newly selected radioButton
//
//                }
//
//            }
//        });

    }


    private void settingsOpen() {

        Parent root2 = null;
        try {
            root2 = FXMLLoader.load(getClass().getClassLoader().getResource("settingsView.fxml"));
            Stage secondStage = new Stage();
            secondStage.setTitle("Ustawienia");
            secondStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("logo.png")));//("file:logo.png"));
            secondStage.setResizable(false);
            secondStage.initStyle(StageStyle.UNIFIED);
            // secondStage.centerOnScreen();
            secondStage.setScene(new Scene(root2, 585, 700));
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void about() {

        Alert alertAbout = new Alert(Alert.AlertType.INFORMATION);
        alertAbout.setTitle("O programie");
        alertAbout.setHeaderText("");
        alertAbout.setContentText("IMPORT KARTOTEK 1.0\nCopyright by STREAMSOFT\nKontakt : lczereda@streamsoft.pl");

        alertAbout.showAndWait();

    }

    private void testConnection() {

        try {
            FirebirdConnector connector = FirebirdConnector.getInstance();
            connector.connectionTest();

        } catch (Exception ex) {
            Utils.createSimpleDialog("Test połączenia z bazą danych", "", "Połączenie z bazą danych nie powiodło się !", Alert.AlertType.ERROR);
        }

    }

    private void ImportKartotek()

    {
//        try {
//            Runnable runnable = () -> {
        try {
            loadFile(filePath, extension);
        } catch (IOException e) {
            e.printStackTrace();
        }
//            };
//            executorService.execute(runnable);


//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void loadFile(String file, String extension) throws IOException {

        if (radioButton1.isSelected() == true) {
            fileInput = new FileInputStream(file);
            switch (extension) {
                case ".xlxs":
                    workbook = new XSSFWorkbook(fileInput);
                    break;
                case ".xlx":
                    workbook = new HSSFWorkbook(fileInput);
                    break;
            }
            Workbook wb = new XSSFWorkbook(fileInput);
            Sheet sheetProduct = wb.getSheet("produkty".toLowerCase());
            Sheet sheetProperty = wb.getSheet("Cechy".toLowerCase());


//        for (Cell cell : sheetProduct.getRow(0)) {
//
//            tableView.getColumns().add(new TableColumn(cell.getStringCellValue()));
//        }

            TableColumn tableColumn;


            int columns = sheetProduct.getRow(0).getLastCellNum();
            int columnsProperty = sheetProperty.getRow(0).getLastCellNum();

            int rows = sheetProduct.getLastRowNum();
            JOptionPane.showMessageDialog(null, rows);
            int rowsProperty = sheetProperty.getLastRowNum();
            List cartList = new ArrayList();
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheetProduct.rowIterator();
            int r = 0;
            for (Row row: sheetProduct)
            {
                cartModel = new CartModel();
                for(Cell cell: row) {

                    if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("indeks")){//cell.getColumnIndex()==0 ) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        cartModel.setIdentyfikator(cellValue);//;System.out.print(cellValue + "\t");.
                    }
                    if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                       cartModel.setNazwaInternetowa(cellValue);
                    }
                    if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
                        {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                        if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
                            {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                            if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
                                {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                                if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
                                    {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                                    if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
                                        {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                                        if(sheetProduct.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
                                            {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==8) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==9) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==10) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==11) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==12) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==13) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==14) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==15) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==16) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==17) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==18) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==19) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==20) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==21) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==22) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==23) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==24) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==25) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==26) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==27) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==28) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==29) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==30) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==31) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==32) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==33) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                    if(cell.getColumnIndex()==34) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    }
                }
                System.out.println(cartModel.toString());
                cartModel=null;
            }
        /*    while ((rowIterator.hasNext())) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

//                for (int r = 2; r <= rows; r++) {
//                Row row = sheetProduct.getRow(r);
//                   while (!row.getCell(0).getStringCellValue().isEmpty()) {
                cartModel = new CartModel();

                //     while (!row.getCell(0).getStringCellValue().isEmpty()) {
                //for (int c = 0; c < columns + 1; c++)
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    
                    //  Cell cell = row.getCell(c);
                    //    if(!cell.getStringCellValue().equals(""))
                    if (cellIterator.next().getColumnIndex()==0) {
                        cartModel.setIdentyfikator(dataFormatter.formatCellValue(cell));
                        //      JOptionPane.showMessageDialog(null, cartModel.getIdentyfikator());


//                        switch (cell.getCellTypeEnum()) {
//                            case NUMERIC:
//                                cartModel.setIdentyfikator(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                identyfikator = String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue());
//                                break;
//                            case STRING:
//                                cartModel.setIdentyfikator(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                JOptionPane.showMessageDialog(null, "W wierszu " + (r + 1) + " brakuje Identyfikatora");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//
//                    }


                        if (cellIterator.next().getColumnIndex()== 1) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("nazwa internetowa")) {
                            cartModel.setNazwaInternetowa(dataFormatter.formatCellValue(cell));
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setNazwaInternetowa(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setNazwaInternetowa(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setNazwaInternetowa("");
//                            case FORMULA:
//
//                                break;
//                        }
//                    }

                        if (cellIterator.next().getColumnIndex()== 2) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("nazwa systemowa")) {
                            cartModel.setNazwaSystemowa(dataFormatter.formatCellValue(cell));
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setNazwaSystemowa(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setNazwaSystemowa(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                JOptionPane.showMessageDialog(null, "W wierszu " + (r + 1) + " brakuje Nazwy systemowej");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 3) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("jednostka miary")) {
                            cartModel.setJednostka(dataFormatter.formatCellValue(cell));


                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setJednostka(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setJednostka(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                JOptionPane.showMessageDialog(null, "W wierszu " + (r + 1) + " brakuje jednostki miary");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 4) {//(sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("kodeandomyślny")) || (sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("kodeandomyslny"))) {
                            cartModel.setKodEanDomyslny(dataFormatter.formatCellValue(cell));
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setKodEanDomyslny(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setKodEanDomyslny(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setKodEanDomyslny("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 5) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("dodatkowy ean do opakowania zbiorczego")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setDodatkowyEan(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdDodatkowegoOpakowanieZbiorczegoEAN(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setDodatkowyEan(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdDodatkowegoOpakowanieZbiorczegoEAN(sheetProduct.getRow(1).getCell(c).getStringCellValue());// / cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setDodatkowyEan("");
//                            case FORMULA:
//
//                                break;
//                        }
//                        // cartModel.setIdDodatkowegoOpakowanieZbiorczegoEAN(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }
                        if (cellIterator.next().getColumnIndex()== 6) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("cena hurtowa netto")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setCenaHurtowa(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setCenaHurtowa(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setCenaHurtowa("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 7) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("cena sklep internetowy netto")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setCenaSklepInternetowy(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setCenaSklepInternetowy(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setCenaSklepInternetowy("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 8) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("cena dla paragonu netto")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setCenaDlaParagonu(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setCenaDlaParagonu(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setCenaDlaParagonu("")
//                                ;
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 9) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("ostatnia cena zakupu netto")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setOstatniaCena(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setOstatniaCena(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setOstatniaCena("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 10) {//(sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("nazwa zdjęcia")) || (sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("nazwa zdjecia"))) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setNazwaZdjecia(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setNazwaZdjecia(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setNazwaZdjecia("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 11) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("dokumentacja")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setDokumentacja(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setDokumentacja(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setDokumentacja("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 12) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("uwagi")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setUwagi(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setUwagi(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setUwagi("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 13) {//(sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("ostrzeżenie")) || (sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("ostrzezenie"))) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setOstrzezenie(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setOstrzezenie(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setOstrzezenie("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }

                        if (cellIterator.next().getColumnIndex()== 14) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("kgo")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setKgo(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setKgo(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setKgo("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 15) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("waga")) {

                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setWaga(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setWaga(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setWaga("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                    }
                        if (cellIterator.next().getColumnIndex()== 16) {//(sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("kartoteki powiązane")) || (sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("kartoteki powiazane"))) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setKartotekiPowiazane(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setKartotekiPowiazane(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setKartotekiPowiazane("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //   cartModel.setKartotekiPowiazane(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//
//                    }
                        if (cellIterator.next().getColumnIndex()== 17) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("zamienniki")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setZamienniki(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setZamienniki(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setZamienniki("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //   cartModel.setZamienniki(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//
//                    }
                        if (cellIterator.next().getColumnIndex()== 19) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("priorytet")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setIdPriorytet(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setIdPriorytet(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                break;
//                            case BLANK:
//                                cartModel.setIdPriorytet("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        // cartModel.setIdPriorytet(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//
//                    }
                        if (cellIterator.next().getColumnIndex()== 20) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("opakowanie zbiorcze 1")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setOpakowanieZbiorcze1(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setOpakowanieZbiorcze1(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                //JOptionPane.showMessageDialog(null,cartModel.getOpakowanieZbiorcze1());// / cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setOpakowanieZbiorcze1("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //   cartModel.setOpakowanieZbiorcze1(cell.getStringCellValue());
//
//                        //  cartModel.setIdOpakowaniaZbiorczego1(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }

                        if (cellIterator.next().getColumnIndex()== 21) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("opakowanie zbiorcze 2")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setOpakowanieZbiorcze2(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setOpakowanieZbiorcze2(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setOpakowanieZbiorcze2("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //   cartModel.setOpakowanieZbiorcze2(cell.getStringCellValue());// cartList.add(cell.getStringCellValue());
//
//                        //cartModel.setIdOpakowaniaZbiorczego2(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }
                        if (cellIterator.next().getColumnIndex()== 22) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("opakowanie zbiorcze 3")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setOpakowanieZbiorcze3(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setOpakowanieZbiorcze3(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setOpakowanieZbiorcze3("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //   cartModel.setOpakowanieZbiorcze3(cell.getStringCellValue());
//                        //cartModel.setIdOpakowaniaZbiorczego3(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }
                        if (cellIterator.next().getColumnIndex()== 23) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("stawka vat")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setStawkaVat(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setStawkaVat(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setStawkaVat(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //  cartModel.setStawkaVat(cell.getStringCellValue());
//
//                    }
                        if (cellIterator.next().getColumnIndex()== 24) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("opis")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setOpis(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setOpis(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setOpis("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        // cartModel.setOpis(cell.getStringCellValue());// cartList.add(cell.getStringCellValue());
//
//                        //  cartModel.setIdTypOpisu(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }

                        if (cellIterator.next().getColumnIndex()== 25) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("strona www")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setStronaWWW(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setStronaWWW(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setStronaWWW("")
//                                ;
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //  cartModel.setStronaWWW(cell.getStringCellValue());
//                    }
                        if (cellIterator.next().getColumnIndex()== 26) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("grupa rabatowa")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setGrupaRabatowa(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setGrupaRabatowa(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaRabatowa("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //   cartModel.setGrupaRabatowa(cell.getStringCellValue());// cartList.add(cell.getStringCellValue());
//
//                    }
                        if (cellIterator.next().getColumnIndex()== 27) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("grupa bonusowa")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setGrupaBonusowa(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setGrupaBonusowa(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaBonusowa("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //   cartModel.setGrupaBonusowa(cell.getStringCellValue());// cartList.add(cell.getStringCellValue());
//
//                    }
                        if (cellIterator.next().getColumnIndex()== 28) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("producent")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setProducent(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdRodzajuGrupyKartotekowejProducent(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setProducent(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdRodzajuGrupyKartotekowejProducent(sheetProduct.getRow(1).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                JOptionPane.showMessageDialog(null, "W wierszu " + (r + 1) + " brakuje pola Producent");
//                                cartModel.setIdRodzajuGrupyKartotekowejProducent("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        // cartModel.setProducent(cell.getStringCellValue());
//                        //cartModel.setIdRodzajuGrupyKartotekowejProducent(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }
                        if (cellIterator.next().getColumnIndex()== 29) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("id rodzaju grupy kartotekowej 1")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setGrupaKartotekowa1(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdRodzajuGrupyKartotekowej1(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                                break;
//                            case STRING:
//                                cartModel.setGrupaKartotekowa1(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdRodzajuGrupyKartotekowej1(sheetProduct.getRow(1).getCell(c).getStringCellValue().toLowerCase().trim());// cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaKartotekowa1("");
//                                cartModel.setIdRodzajuGrupyKartotekowej1("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        // cartModel.setGrupaKartotekowa1(cell.getStringCellValue());
//                        //  cartModel.setIdRodzajuGrupyKartotekowej1(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }
                        if (cellIterator.next().getColumnIndex()== 30) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("id rodzaju grupy kartotekowej 2")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setGrupaKartotekowa2(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdRodzajuGrupyKartotekowej2(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//
//                                break;
//                            case STRING:
//                                cartModel.setGrupaKartotekowa2(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdRodzajuGrupyKartotekowej2(sheetProduct.getRow(1).getCell(c).getStringCellValue().toLowerCase().trim());
//                                ;
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaKartotekowa2("");
//                                cartModel.setIdRodzajuGrupyKartotekowej2("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //    cartModel.setGrupaKartotekowa2(cell.getStringCellValue());
//                        //   cartModel.setIdRodzajuGrupyKartotekowej2(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//
//                    }
                        if (cellIterator.next().getColumnIndex()== 31) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("id rodzaju grupy kartotekowej 3")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setGrupaKartotekowa3(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdRodzajuGrupyKartotekowej3(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//
//                                break;
//                            case STRING:
//                                cartModel.setGrupaKartotekowa3(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdRodzajuGrupyKartotekowej3(String.valueOf(sheetProduct.getRow(1).getCell(c).getStringCellValue().toLowerCase().trim()));
//                                // / cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaKartotekowa3("");
//                                cartModel.setIdRodzajuGrupyKartotekowej3("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //  cartModel.setGrupaKartotekowa3(cell.getStringCellValue());
//                        //   cartModel.setIdRodzajuGrupyKartotekowej3(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }
                        if (cellIterator.next().getColumnIndex()== 32) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("id rodzaju grupy kartotekowej 4")) {
                        }
//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setGrupaKartotekowa4(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdRodzajuGrupyKartotekowej4(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//
//                                break;
//                            case STRING:
//                                cartModel.setGrupaKartotekowa4(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdRodzajuGrupyKartotekowej4(String.valueOf(sheetProduct.getRow(1).getCell(c).getStringCellValue().toLowerCase().trim()));
//                                // / cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaKartotekowa4("");
//                                cartModel.setIdRodzajuGrupyKartotekowej4("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //  cartModel.setGrupaKartotekowa3(cell.getStringCellValue());
//                        //   cartModel.setIdRodzajuGrupyKartotekowej3(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }

                        if (cellIterator.next().getColumnIndex()== 33) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("id rodzaju grupy kartotekowej 5")) {
                        }
//                        switch (cell.getCellTypeEnum()) {
//                            case NUMERIC:
//                                cartModel.setGrupaKartotekowa5(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdRodzajuGrupyKartotekowej5(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//
//                                break;
//                            case STRING:
//                                cartModel.setGrupaKartotekowa5(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdRodzajuGrupyKartotekowej5(String.valueOf(sheetProduct.getRow(1).getCell(c).getStringCellValue().toLowerCase().trim()));
//                                // / cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaKartotekowa5("");
//                                cartModel.setIdRodzajuGrupyKartotekowej5("");
//                                break;
//                            case FORMULA:
//
//                                break;
//                        }
//                        //  cartModel.setGrupaKartotekowa3(cell.getStringCellValue());
//                        //   cartModel.setIdRodzajuGrupyKartotekowej3(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//                    }
                        if (cellIterator.next().getColumnIndex()== 34) {//sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("id rodzaju grupy kartotekowej X")) {

                        }


//                        switch ((cell.getCellTypeEnum())) {
//                            case NUMERIC:
//                                cartModel.setGrupaKartotekowaX(String.valueOf(sheetProduct.getRow(r).getCell(c).getNumericCellValue()));
//                                cartModel.setIdRodzajuGrupyKartotekowejX(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
//
//                                break;
//                            case STRING:
//                                cartModel.setGrupaKartotekowaX(sheetProduct.getRow(r).getCell(c).getStringCellValue().toLowerCase().trim());
//                                cartModel.setIdRodzajuGrupyKartotekowejX(String.valueOf(sheetProduct.getRow(1).getCell(c).getStringCellValue().toLowerCase().trim()));
//                                // / cartList.add(cell.getStringCellValue());
//                                break;
//                            case BLANK:
//                                cartModel.setGrupaKartotekowaX("");
//                                cartModel.setIdRodzajuGrupyKartotekowejX("");
//                                break;
//                            case FORMULA:
//
//                                break;
//
//                            //  cartModel.setGrupaKartotekowa3(cell.getStringCellValue());
//                            //   cartModel.setIdRodzajuGrupyKartotekowej3(String.valueOf(sheetProduct.getRow(1).getCell(c).getNumericCellValue()));
                    }

//miejsce na import

                    //Platform.runLater(() ->

                }
                // Utils.createSimpleDialog("Komunikat", "", cartModel.toString(), Alert.AlertType.INFORMATION);
                JOptionPane.showMessageDialog(null, cartModel.toString() + "\n");

                cartModel = null;
                r++;

            }*/
            log = new ListView<>(lista);
            log.setItems(lista);


//            for (int r = 2; r <= rowsProperty; r++) {
//                while (!row.getCell(0).getStringCellValue().isEmpty()) {
//                    cartModel = new CartModel();
//
//                    Row row = sheetProduct.getRow(r);
//                    for (int c = 0; c < columns; c++) {
//                        Cell cell = row.getCell(c);
//                    }
//                }
//            }
        }
    }
}
//}

//
//}
//miejsce na import


//  cartDao.AddCart(cartModel);
//   }
//   JOptionPane.showMessageDialog(null,cartModel.getIdentyfikator());

//            wb.close();
//        }
//
//}}

//
//            //rowPropertyId = sheetProperty.getRow(1);
////            for (int r1 = 1; r1 < rowsProperty; r1++) {
////
////
////
////                if (rowProperty != null) {
////                    for (int c = 1; c < columnsProperty; c++)
////                    {
////                        cellPropertyId = rowPropertyId.getCell((short) c);
////                        cellProperty = rowProperty.getCell((short) c+1);
////                       while(cellPropertyId!=null){
////                            switch ((cellPropertyId.getCellTypeEnum())) {
////                                case NUMERIC:
////                                   propertyMap.put(cellPropertyId.getNumericCellValue())
////                                    break;
////                                case STRING:
////
////                                    break;
////                                case BLANK:
////
////                                    break;
////                                case FORMULA:
////
////                                    break;
////                            }
////                        }fpsmax
////
//                        if (sheetProperty.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim() == "napięcie") {//||(sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim() == "ostrzezenie")) {
//                            switch ((cellProperty.getCellTypeEnum())) {
//                                case NUMERIC:
//                                    cartModel.setCechaNapiecie(String.valueOf(cellProperty.getNumericCellValue()));
//                                    break;
//                                case STRING:
//                                    cartModel.setCechaNapiecie(cellProperty.getStringCellValue());// cartList.add(cell.getStringCellValue());
//                                    break;
//                                case BLANK:
//                                    cartModel.setCechaNapiecie("");
//                                    break;
//                                case FORMULA:
//
//                                    break;
//                            }
//                        }
//                        if (sheetProperty.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim() == "moc") {//||(sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim() == "ostrzezenie")) {
//                            switch ((cellProperty.getCellTypeEnum())) {
//                                case NUMERIC:
//                                    cartModel.setCechaMoc(String.valueOf(cellProperty.getNumericCellValue()));
//                                    break;
//                                case STRING:
//                                    cartModel.setCechaMoc(cellProperty.getStringCellValue());// cartList.add(cell.getStringCellValue());
//                                    break;
//                                case BLANK:
//                                    cartModel.setCechaMoc("");
//                                    break;
//                                case FORMULA:
//
//                                    break;
//                            }
//                        }
//                    }
//                }
//
//
//            }
//
//
//


//        while (rowIterator.hasNext()) {
////            TableRow tableRow = new TableRow();
//
//            Row row = rowIterator.next();
//            TableRow tableRow = new TableRow();
//            //For each row, iterate through each columns
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//
//                Cell cell = cellIterator.next();
//
//                switch ((cell.getCellTypeEnum())) {
//                    case NUMERIC:
//                        cartList.add(cell.getNumericCellValue());
//                        break;
//                    case STRING:
//                        cartList.add(cell.getStringCellValue());
//                        break;
//                    case BLANK:
//                        cartList.add("");
//                        break;
//                    case FORMULA:
//                        cartList.add(cell.getCellFormula());
//                        break;
//                }
////cartList.clear();
//            }

//
//            TableColumn columnIdentyfikator = new TableColumn("Identyfikator");
//            TableColumn columnNazwaInternetowa = new TableColumn("Nazwa internetowa");
//            TableColumn columnNazwaSystemowa = new TableColumn("Nazwa systemowa");
//            TableColumn columnJednostka = new TableColumn("Jednostka");
//            TableColumn columnKodEanDomyslny = new TableColumn("Kod Ean Domyślny");
//            TableColumn columnKodEanOpakowanie = new TableColumn("Dodatkowy Ean do opakowania zbiorcczego");
//            TableColumn columnCenaHurtowa = new TableColumn("Cena hurtowa");
//            TableColumn columnCenaSklepInternetowy = new TableColumn("Cena sklep internetowy");
//            TableColumn columnCenaDlaParagonu = new TableColumn("Cena dla paragonu");
//            TableColumn columnOstaniaCenaZakupu = new TableColumn("Ostatnia cena zakupu");
//            TableColumn columnNazwaZdjecia = new TableColumn("Nazwa zdjęcia");
//            TableColumn columnDokumentacja = new TableColumn("Dokumentacja");
//            TableColumn columnOstrzezenie = new TableColumn("Ostrzeżenie");
//            TableColumn columnKGO = new TableColumn("KGO");
//            TableColumn columnWaga = new TableColumn("Waga");
//            TableColumn columnKartotekiPowiazane = new TableColumn("Kartoteki powiązane");
//            TableColumn columnZamienniki = new TableColumn("Zamienniki");
//            TableColumn columnPriorytet = new TableColumn("Priorytet");
//            TableColumn columnOpakowanieZbiorcze = new TableColumn("Opakowanie zbiorcze");
//            TableColumn columnOpakowanieZbiorcze2 = new TableColumn("Opakowanie zbiorcze 2");
//            TableColumn columnOpakowanieZbiorcze3 = new TableColumn("Opakowanie zbiorcze 3");
//            TableColumn columnStawkaVAT = new TableColumn("Stawka VAT");
//            TableColumn columnOpis = new TableColumn("Opis");
//            TableColumn columnStronaWWW = new TableColumn("Strona WWW");
//            TableColumn columnGrupaRabatowa = new TableColumn("Grupa rabatowa");
//            TableColumn columnGrupaBonusowa = new TableColumn("Grupa bonusowa");
//            TableColumn columnProducent = new TableColumn("Producent");
//            TableColumn columnIdRodzajuGrupyKartotekowej = new TableColumn("Id rodzaju grupy kartotekowej");
//            TableColumn columnIdRodzajuGrupyKartotekowej2 = new TableColumn("Id rodzaju grupy kartotekowej 2");
//            TableColumn columnIdRodzajuGrupyKartotekowej3 = new TableColumn("Id rodzaju grupy kartotekowej 3");
//            TableColumn columnIdRodzajuGrupyKartotekowej4 = new TableColumn("Id rodzaju grupy kartotekowej 4");
//            TableColumn columnIdRodzajuGrupyKartotekowej5 = new TableColumn("Id rodzaju grupy kartotekowej 5");
//            TableColumn columnIdRodzajuGrupyKartotekowejX = new TableColumn("Id rodzaju grupy kartotekowej X");
//            TableColumn columnIndeks = new TableColumn("Indeks");
//            TableColumn columnWkatalogu = new TableColumn("W katalogu");
//            TableColumn columnNapiecie = new TableColumn("Napiecie");
//            TableColumn columnMoc = new TableColumn("Moc");


// ObservableList lista=FXCollections.observableArrayList();
//lista.addAll(cartList);
//  tableView.setItems(lista);
//        tableView.getColumns().addAll(columnIdentyfikator,columnNazwaInternetowa,columnNazwaSystemowa,columnJednostka,columnKodEanDomyslny,columnKodEanOpakowanie
//                ,columnCenaHurtowa,columnCenaSklepInternetowy,columnCenaDlaParagonu,columnOstaniaCenaZakupu,columnNazwaZdjecia,columnDokumentacja,columnOstrzezenie
//        ,columnKGO,columnWaga,columnKartotekiPowiazane,columnZamienniki,columnPriorytet,columnOpakowanieZbiorcze,columnOpakowanieZbiorcze2,columnOpakowanieZbiorcze3,columnStawkaVAT,columnOpis,
//                columnStronaWWW,columnGrupaRabatowa,columnGrupaBonusowa,columnProducent,columnIdRodzajuGrupyKartotekowej,columnIdRodzajuGrupyKartotekowej2,columnIdRodzajuGrupyKartotekowej3,columnIdRodzajuGrupyKartotekowej4,columnIdRodzajuGrupyKartotekowej5,
//                columnIdRodzajuGrupyKartotekowejX,columnIndeks,columnWkatalogu,columnNapiecie,columnMoc);
//


//
//            tableRow.setItem(cartList);
//            tableView.setItems(cartList);
//          //  sheetProduct.getRow(0).getCell(1).getStringCellValue();
//            //tableRows.add(tableRow);
//
//         //  TableColumn tableColumn1= tableView.getColumns()
//
//             cartList.clear();
//        }
// JOptionPane.showMessageDialog(null,tableRow);


//        while (rowIterator.hasNext()) {
//


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

//    HSSFWorkbook workbook = new HSSFWorkbook();
//    HSSFSheet sheet = workbook.createSheet("Course Pack Resolution Details");
//    outputFileName = outPut.getAbsolutePath();
//    int rownum = 0;`enter code here`
//            for (int i = 0; i < dataList.size(); i++) {
//        Object[] objArr = dataList.get(i);
//        HSSFRow row = sheet.createRow(rownum++);
//
//        int cellnum = 0;
//        for (Object obj : objArr) {
//            Cell cell = row.createCell(cellnum++);
//            sheet.autoSizeColumn((short) cellnum);
//            if (obj instanceof Date) {
//                cell.setCellValue((Date) obj);
//            } else if (obj instanceof Boolean) {
//                cell.setCellValue((Boolean) obj);
//            } else if (obj instanceof String) {
//                cell.setCellValue((String) obj);
//            } else if (obj instanceof Double) {
//                cell.setCellValue((Double) obj);
//            }
//        }
//    }
//        if (outPut.exists()) {
//        outPut.delete();
//    }
//    FileOutputStream out =
//            new FileOutputStream(outPut);
//        workbook.write(out);
//
//

//for (int r = 2; r <= 3; r++) {
//                cartModel = new CartModel();
//                Row row = sheetProduct.getRow(r);
//
//
//                // data.put(i, new ArrayList<String>());
//                for (int c = 0; c < columns; c++) {
//                    Cell cell = row.getCell(c);
//                    while (!row.getCell(0).getStringCellValue().isEmpty()) {
//                        if (sheetProduct.getRow(0).getCell(c).getStringCellValue().toLowerCase().trim().equals("identyfikator")) {
//
//                        switch (cell.getCellTypeEnum()) {
//                            case STRING:
//                              cart.add(cell.getStringCellValue());
//                                break;
//                            case NUMERIC:
//                                cart.add(cell.getNumericCellValue());
//                                break;
//                            case FORMULA:
//                                cart.add(cell.getCellFormula());
//                                break;
//                            default:cart.add("");
//                                ;
//                        }
//                    }}
//                }
//                i++;
//                JOptionPane.showMessageDialog(null,cart.get(0));
//            }
//
//
//        }