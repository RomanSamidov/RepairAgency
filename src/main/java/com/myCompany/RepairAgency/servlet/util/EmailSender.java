package com.myCompany.RepairAgency.servlet.util;


import com.myCompany.RepairAgency.Constants;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Class for sending emails from gmail. Uses email.properties, where should be parameters:
 * email - whom which gmail send emails(example@gmail.com).
 * password - password which Google set to use in program(NOT PASSWORD FROM EMAIL)
 * send_emails - send or not letters(true/false)
 */
public class EmailSender {
    private static final Logger logger = LogManager.getLogger(EmailSender.class);

    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle(Constants.EMAIL_SETTINGS_BUNDLE);
    private static final String from = resourceBundle.getString(Constants.EMAIL);
    private static final String password = resourceBundle.getString(Constants.PASSWORD);
    private static final Boolean sendEmails = Boolean.parseBoolean(resourceBundle.getString("send_emails"));

    /**
     * Method for sending email letter.
     * @param to post where to send letter
     * @param sub theme of letter
     * @param msg text of letter
     * @param files files that will be added to letter
     */
    public static void send(String to, String sub, String msg, File... files) {
        logger.debug(to + " " + sub + " " + msg);

        if (!sendEmails) {
            return;
        }

        send(to, sub, msg, 0, files);
    }

    private static void send(String to, String sub, String msg, int trie, File... files) {
        if (trie > 10) return;
        try {
            //Get properties object
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "jakarta.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.port", "465");
            //get Session
            Session session = Session.getDefaultInstance(props,
                    new jakarta.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, password);
                        }
                    });
            //compose message

            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //
//           message.setText(msg);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();


//          mimeBodyPart.setContent(msg, "UTF-8");
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);

            for (File file : files) {
                mimeBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                mimeBodyPart.setDataHandler(new DataHandler(source));
                mimeBodyPart.setFileName(file.getName());
                multipart.addBodyPart(mimeBodyPart);
            }

            message.setContent(multipart);
            //send message
            Transport.send(message);
            logger.debug("message sent successfully");
        } catch (MessagingException e) {
            logger.debug("message do not sent successfully, " + trie + " trie");
            logger.debug(e);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            send(to, sub, msg, ++trie, files);
        }

    }
}
