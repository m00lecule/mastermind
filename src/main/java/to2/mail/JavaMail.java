package to2.mail;


import to2.model.Config;
import to2.persistance.User;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Class responsible for sending emails
 */
public class JavaMail {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    static public void notifyUsers(List list) throws MessagingException {
        JavaMail javaEmail = new JavaMail();
        javaEmail.setMailServerProperties();
        for (Object o : list) {
            User u = (User) o;
            javaEmail.createEmailMessage(u.getEmail());
            javaEmail.sendEmail();
        }
    }

    public void setMailServerProperties() {

        String emailPort = "587";

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
    }

    public void createEmailMessage(String... toEmails) throws AddressException, MessagingException {
        String emailSubject = "Mastermind - it's time to play!";
        String emailBody = "There is a new highcore, will You try to beat him?";

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (String emailAddress : toEmails) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");
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