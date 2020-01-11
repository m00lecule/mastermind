package to2.model;


import to2.Mastermind;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Config {
    static private Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream(Mastermind.class.getResource("/properties/credentials.properties").getFile())) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getEmailLogin(){
        return (String) properties.get("login");
    }

    public static String getEmailPassword(){
        return (String) properties.get("password");
    }

    public static void main(String args[]) throws AddressException,
            MessagingException {

        System.out.println(Config.getEmailLogin());
    }
}
