package pl.hansonq.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sun.net.smtp.SmtpClient;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextArea text;

    @FXML
    Button buttonSend;

    @FXML
    TextField subject, receiver;

    public LoginController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonSend.setOnMouseClicked(e -> send());
    }

    private void send() {

        String to = receiver.getText();

        // Sender's email ID needs to be mentioned
        String from = "l.czereda@streamsoft.pl";
        final String username = "lczereda";//change accordingly
        final String password = "LBP9uNNo";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.streamsoft.pl";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


//
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "465");


        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject.getText());

            // Send the actual HTML message, as big as you like
            message.setContent(
                    text.getText() + "\n\n\n\n<!-- Informacje kontaktowe do edytowania -->\n" +
                            "<!-- Imię i nazwisko START-->\n" +
                            "<span style=\"font-family: Arial; color:#3b5cd3; font-weight: bold; font-size: 14px;\">Łukasz Czereda</span><br>\n" +
                            "<!-- Imię i nazwisko STOP-->\n" +
                            "<!-- Stanowisko START-->\n" +
                            "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px; font-style: italic;\">Konsultant Działu Wsparcia Klientów Bezpośrednich</span><br><br>\n" +
                            "<!-- Stanowisko STOP-->\n" +
                            "\n" +
                            "<img src=\"http://newsletter.streamsoft.pl/stopki2017/telefon.gif\" nosend=\"1\" width=\"17\" height=\"12\" alt=\"\">\n" +
                            "<!-- Telefon 1 START-->\n" +
                            "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px;\">+48 68 4566905</span>\n" +
                            "<!-- Telefon 1 STOP-->\n" +
                            "<img src=\"http://newsletter.streamsoft.pl/stopki2017/telefon.gif\" nosend=\"1\" width=\"17\" height=\"12\" alt=\"\">\n" +
                            "<!-- Telefon 2 START-->\n" +
                            "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px;\">+48 539 777 930</span>\n" +
                            "<!-- Telefon 2 STOP-->\n" +
                            "<img src=\"http://newsletter.streamsoft.pl/stopki2017/mail.gif\" nosend=\"1\" width=\"34\" height=\"12\" alt=\"\">\n" +
                            "<!-- E-mail START-->\n" +
                            "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px;\">l.czereda@streamsoft.pl</span><br><br>\n" +
                            "<!-- Email STOP-->\n" +
                            "\n" +
                            "<!-- NIEEDYTOWALNE ELEMENTY STOPKI - NIE ZMIENIAMY! -->\n" +
                            "<a href=\"http://www.streamsoft.pl/?utm_source=stopka&utm_medium=email&utm_campaign=logostreamsoft\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/streamsoft-logo.gif\" width=\"425\" height=\"42\" alt=\"\"/></a>\n" +
                            "<br>\n" +
                            "<table cellspacing=\"0\" cellpadding=\"0\" width=425\" style=\"font-family: Arial; color:#3b5cd3; font-weight: regular; font-size: 9px; float:left;\"><tr><td>Streamsoft Sp. z o.o. SK, al. Wojska Polskiego 11<br>\n" +
                            "65-077 Zielona Góra, NIP: 929-185-17-23</td>\n" +
                            "<td style=\"text-align: right\">\n" +
                            "<a href=\"http://www.streamsoft.pl/fb/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_13.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
                            "<a href=\"http://www.streamsoft.pl/twitter/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_14.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
                            "<a href=\"http://www.streamsoft.pl/linkedin/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_15.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
                            "<a href=\"http://www.streamsoft.pl/gplus/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_16.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
                            "<a href=\"http://www.streamsoft.pl/yt/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_17.gif\" width=\"20\" height=\"20\" alt=\"\"/></a></td></table><br><br>\n" +
                            "<a href=\"http://www.streamsoft.pl/prestiz-stopka/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/bannery/prestiz.png\" alt=\"\"/></a>",
                    "text/html; charset=UTF-8");

            // Send message
            for (int i = 0; i < 100; i++) {
                try {

                    message.setContent(
                            "<span style=\"font-family: Arial; color:#3b5cd3; font-weight: bold;text-align:center; font-size: 300px;\">"+i+"</span>",
//                            + "\n\n\n\n<!-- Informacje kontaktowe do edytowania -->\n" +
//                                    "<!-- Imię i nazwisko START-->\n" +
//                                    "<span style=\"font-family: Arial; color:#3b5cd3; font-weight: bold; font-size: 14px;\">Łukasz Czereda</span><br>\n" +
//                                    "<!-- Imię i nazwisko STOP-->\n" +
//                                    "<!-- Stanowisko START-->\n" +
//                                    "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px; font-style: italic;\">Konsultant Działu Wsparcia Klientów Bezpośrednich</span><br><br>\n" +
//                                    "<!-- Stanowisko STOP-->\n" +
//                                    "\n" +
//                                    "<img src=\"http://newsletter.streamsoft.pl/stopki2017/telefon.gif\" nosend=\"1\" width=\"17\" height=\"12\" alt=\"\">\n" +
//                                    "<!-- Telefon 1 START-->\n" +
//                                    "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px;\">+48 68 4566905</span>\n" +
//                                    "<!-- Telefon 1 STOP-->\n" +
//                                    "<img src=\"http://newsletter.streamsoft.pl/stopki2017/telefon.gif\" nosend=\"1\" width=\"17\" height=\"12\" alt=\"\">\n" +
//                                    "<!-- Telefon 2 START-->\n" +
//                                    "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px;\">+48 539 777 930</span>\n" +
//                                    "<!-- Telefon 2 STOP-->\n" +
//                                    "<img src=\"http://newsletter.streamsoft.pl/stopki2017/mail.gif\" nosend=\"1\" width=\"34\" height=\"12\" alt=\"\">\n" +
//                                    "<!-- E-mail START-->\n" +
//                                    "<span style=\"font-family: Arial; color:#676a72; font-weight: regular; font-size: 12px;\">l.czereda@streamsoft.pl</span><br><br>\n" +
//                                    "<!-- Email STOP-->\n" +
//                                    "\n" +
//                                    "<!-- NIEEDYTOWALNE ELEMENTY STOPKI - NIE ZMIENIAMY! -->\n" +
//                                    "<a href=\"http://www.streamsoft.pl/?utm_source=stopka&utm_medium=email&utm_campaign=logostreamsoft\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/streamsoft-logo.gif\" width=\"425\" height=\"42\" alt=\"\"/></a>\n" +
//                                    "<br>\n" +
//                                    "<table cellspacing=\"0\" cellpadding=\"0\" width=425\" style=\"font-family: Arial; color:#3b5cd3; font-weight: regular; font-size: 9px; float:left;\"><tr><td>Streamsoft Sp. z o.o. SK, al. Wojska Polskiego 11<br>\n" +
//                                    "65-077 Zielona Góra, NIP: 929-185-17-23</td>\n" +
//                                    "<td style=\"text-align: right\">\n" +
//                                    "<a href=\"http://www.streamsoft.pl/fb/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_13.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
//                                    "<a href=\"http://www.streamsoft.pl/twitter/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_14.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
//                                    "<a href=\"http://www.streamsoft.pl/linkedin/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_15.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
//                                    "<a href=\"http://www.streamsoft.pl/gplus/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_16.gif\" width=\"27\" height=\"20\" alt=\"\"/></a>\n" +
//                                    "<a href=\"http://www.streamsoft.pl/yt/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/stopka-streamsoft_17.gif\" width=\"20\" height=\"20\" alt=\"\"/></a></td></table><br><br>\n" +
//                                    "<a href=\"http://www.streamsoft.pl/prestiz-stopka/\" target=\"_blank\"><img src=\"http://newsletter.streamsoft.pl/stopki2017/bannery/prestiz.png\" alt=\"\"/></a>",
                           "text/html; charset=UTF-8");

                    Transport.send(message);
                    Thread.sleep(200);
                } catch (Exception ex) {
                }
            }


            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}

