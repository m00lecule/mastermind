package to2.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import to2.persistance.Postgres;
import to2.persistance.User;

import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField loginEmailTextField;

    @FXML
    private TextField registrationLoginTextField;

    @FXML
    private TextField registrationEmailTextField;

    @FXML
    private CheckBox registrationCheckBox;

    @FXML
    private void initialize() {
    }

    private void showWarning(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        System.out.println(loginTextField.getText());

        if (loginEmailTextField.getText().isEmpty() && loginTextField.getText().isEmpty()) {
            this.showWarning("Fill email or nickname");
            return;
        }

        if (!(loginEmailTextField.getText().isEmpty() || loginTextField.getText().isEmpty())) {
            this.showWarning("Specify only email or nickname");
            return;
        }

        boolean exists = true;

        SessionFactory sessionFactory = Postgres.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = null;

        if (!loginEmailTextField.getText().isEmpty()) {
            query = session.createQuery("from User where email = :email");
            query.setParameter("email", loginEmailTextField.getText());
        }

        if (!loginTextField.getText().isEmpty()) {
            query = session.createQuery("from User where nickname = :nickname");
            query.setParameter("nickname", loginTextField.getText());
        }

        try {
            User.LOGGED_USER = (User) query.getSingleResult();
        } catch (Exception e) {
            this.showWarning("User doesn't exist");
            e.printStackTrace();
            exists = false;
        }

        tx.commit();
        session.close();

        if (!exists)
            return;

        ((Stage) tabPane.getScene().getWindow()).close();
    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {

        if (registrationCheckBox.isSelected() && registrationEmailTextField.getText().isEmpty()) {
            this.showWarning("Email address is obligatory to allow notifications");
            return;
        }

        if (registrationEmailTextField.getText().isEmpty() && (registrationLoginTextField.getText().isEmpty())) {
            this.showWarning("fill email or login");
            return;
        }

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);

        if (!(registrationEmailTextField.getText().isEmpty() || pattern.matcher(registrationEmailTextField.getText()).matches())) {
            this.showWarning("Wrong email format!");
            return;
        }

        SessionFactory sessionFactory = Postgres.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        boolean exception = false;

        User user = new User();
        user.setNickname((registrationLoginTextField.getText().isEmpty()) ? null : registrationLoginTextField.getText());
        user.setEmail((registrationEmailTextField.getText().isEmpty()) ? null : registrationEmailTextField.getText());
        user.setSendNotification(registrationCheckBox.isSelected());

        try {
            session.save(user);

            tx.commit();

        } catch (ConstraintViolationException e) {
            exception = true;
            if (e.getConstraintName().contains("nickname")) {
                this.showWarning("This nickname is taken!");
            } else if (e.getConstraintName().contains("email")) {
                this.showWarning("User associated this email already exists!");
            }
        }

        session.close();

        if (exception) {
            return;
        }

        User.LOGGED_USER = user;

        ((Stage) tabPane.getScene().getWindow()).close();
    }


}
