package pl.hansonq.controllers;

public class kopie {


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
}

//    @Override
//    public boolean AddCart(CartModel cartModel) {
//
//        CallableStatement statement = null;
//        try {
//            conect.setAutoCommit(true);
//
//            statement = conect.prepareCall("{call XXX_LC_IMPORT_KARTOTEK(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//
//            if (cartModel.getIndeks() == null) {
//                statement.setNull(1, Types.VARCHAR);  // Utils.createSimpleDialog("Błąd importu", "", "Pole Indeksu jest puste", Alert.AlertType.ERROR);
//            } else {
//                statement.setString(1, cartModel.getIndeks());
//            }
//            if (cartModel.getIdentyfikator() == null) {
//                statement.setString(2, cartModel.getIdentyfikator());
//            } else {
//                statement.setNull(2, Types.VARCHAR);
//            }
//            if (cartModel.getNazwaInternetowa() == null) {
//                statement.setNull(3, Types.VARCHAR);
//            } else {
//                statement.setString(3, cartModel.getNazwaInternetowa());
//            }
//            if (cartModel.getNazwaSystemowa() == null) {
//                statement.setNull(4, Types.VARCHAR);// Utils.createSimpleDialog("Błąd importu", "", "Pole Nazwa Systemowa jest puste", Alert.AlertType.ERROR);
//
//            } else {
//                statement.setString(4, cartModel.getNazwaSystemowa());
//            }
//            if (cartModel.getJednostka() == null) {
//                statement.setNull(5, Types.VARCHAR);//Utils.createSimpleDialog("Błąd importu", "", "Pole Jednostka jest puste", Alert.AlertType.ERROR);
//
//            } else {
//                statement.setString(5, cartModel.getJednostka());
//            }
//            if (cartModel.getKodEanDomyslny() == null) {
//                statement.setNull(6, Types.VARCHAR);
//            } else {
//                statement.setString(6, cartModel.getKodEanDomyslny());
//            }
//            if (cartModel.getIdDodatkowegoOpakowanieZbiorczegoEAN() == null) {
//                statement.setNull(7, Types.INTEGER);
//            } else {
//                statement.setInt(7, Integer.valueOf(cartModel.getIdDodatkowegoOpakowanieZbiorczegoEAN()));
//
//            }
//            if (cartModel.getDodatkowyEan() == null) {
//                statement.setNull(8, Types.VARCHAR);
//            } else {
//                statement.setString(8, cartModel.getDodatkowyEan());
//            }
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
//            if (cartModel.getOstatniaCena() == null) {
//
//                statement.setDouble(12, 0.0000);
//            } else {
//                statement.setDouble(12, Double.parseDouble(cartModel.getOstatniaCena().replace(",", ".")));
//            }
//            if (cartModel.getUwagi() == null) {
//                statement.setString(13, null);
//            } else {
//                statement.setString(13, cartModel.getUwagi());
//            }
//            if (cartModel.getOstrzezenie() == null) {
//                statement.setString(14, null);
//            } else {
//                statement.setString(14, cartModel.getOstrzezenie());
//            }
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
//            if (cartModel.getIdPriorytet() == null) {
//                statement.setInt(17, 0);
//            } else {
//                statement.setInt(17, Integer.parseInt(cartModel.getIdPriorytet()));
//            }
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
//            if (cartModel.getStronaWWW() == null) {
//                statement.setNull(28, Types.VARCHAR);
//            } else {
//                statement.setString(28, cartModel.getStronaWWW());
//            }
//
//            if (cartModel.getIdRodzajuGrupyKartotekowejProducent() == null) {
//                statement.setNull(29, Types.INTEGER);
//            } else {
//                statement.setInt(29, Integer.valueOf(cartModel.getIdRodzajuGrupyKartotekowejProducent()));
//            }
//
//            if (cartModel.get == null) {
//                statement.setNull(30, Types.VARCHAR);
//            } else {
//                statement.setString(30, cartModel.getProducent());
//            }
//
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
//            if (cartModel.getIdStawkiVat() == null) {
//                statement.setInt(41, 1);
//            } else {
//                statement.setInt(41, Integer.valueOf(cartModel.getIdStawkiVat()));
//            }
//
//            //  JOptionPane.showMessageDialog(null,cartModel.toString());
//          statement.execute();
//            statement.close();
//                return true;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
//            ;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            Utils.createSimpleDialog("Błąd importu danych", "", "Błąd podczas importu, komunikat błędu :\n" + e.getMessage(), Alert.AlertType.ERROR);
//            ;
//        }
//        finally {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return false;
//    }
