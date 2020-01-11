package to2.mail;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sun.awt.image.ImageWatched;
import to2.model.Config;
import to2.persistance.Postgres;
import to2.persistance.User;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class JavaMail {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    public static void main(String args[]) throws AddressException,
            MessagingException {

        JavaMail javaEmail = new JavaMail();
        javaEmail.setMailServerProperties();
        List<String> s = new LinkedList<>();
        s.add("michaldygaz@gmail.com");
        javaEmail.createEmailMessage(s);
        javaEmail.sendEmail();
    }

    public void setMailServerProperties() {

        String emailPort = "587";

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
    }

    public void createEmailMessage(List<String> toEmails) throws AddressException, MessagingException {
        String emailSubject = "Java Email";
        String emailBody = "This is an email sent by JavaMail api.";

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (String emailAddress : toEmails) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email
        //emailMessage.setText(emailBody);// for a text email

    }

    public void sendEmail() throws AddressException, MessagingException {

        String emailHost = "smtp.gmail.com";
        String fromUser = Config.getEmailLogin();
        String fromUserEmailPassword = Config.getEmailPassword();

        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }
}