package com.myCompany.RepairAgency.servlet.util;



import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;


import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;


public class EmailSender {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("email");
    private static final String from = resourceBundle.getString("email");
    private static final String password = resourceBundle.getString("password");
    public static void send(String to, String sub, String msg, File... files){
        System.out.println(to + sub + msg);
        return;
          //Get properties object
//          Properties props = new Properties();
//          props.put("mail.smtp.host", "smtp.gmail.com");
//          props.put("mail.smtp.socketFactory.port", "465");
//          props.put("mail.smtp.socketFactory.class", "jakarta.net.ssl.SSLSocketFactory");
//          props.put("mail.smtp.auth", "true");
//          props.put("mail.smtp.ssl.enable", "true");
//          props.put("mail.smtp.port", "465");
//          //get Session
//          Session session = Session.getDefaultInstance(props,
//                           new jakarta.mail.Authenticator() {
//           protected PasswordAuthentication getPasswordAuthentication() {
//           return new PasswordAuthentication(from,password);
//           }
//          });
//          //compose message
//          try {
//           MimeMessage message = new MimeMessage(session);
//           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//           message.setSubject(sub);
//           message.setText(msg);
//           //
////           message.setText(msg);
//          Multipart multipart = new MimeMultipart();
//          MimeBodyPart mimeBodyPart = new MimeBodyPart();
//
//          mimeBodyPart.setContent(msg, "text/plain");
//          multipart.addBodyPart(mimeBodyPart);
//
//          for (File file : files) {
//              mimeBodyPart = new MimeBodyPart();
//              DataSource source = new FileDataSource(file);
//              mimeBodyPart.setDataHandler(new DataHandler(source));
//              mimeBodyPart.setFileName(file.getName());
//              multipart.addBodyPart(mimeBodyPart);
//          }
//
//          message.setContent(multipart);
//           //send message
//           Transport.send(message);
//           System.out.println("message sent successfully");
//          } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}
