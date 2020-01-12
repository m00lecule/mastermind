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

    private void showWarning(String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        System.out.println(loginTextField.getText());

        if(loginEmailTextField.getText().isEmpty()){
            this.showWarning("Fill email");
            return;
        }

        if(loginTextField.getText().isEmpty()){
            this.showWarning("Fill login");
            return;
        }

        boolean exists = true;

        SessionFactory sessionFactory = Postgres.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from User where email = :email and nickname = :nickname");
        query.setParameter("email", loginEmailTextField.getText());
        query.setParameter("nickname", loginTextField.getText());

        try {
            User.LOGGED_USER = (User) query.getSingleResult();
        }catch (Exception e){
            this.showWarning("User doesn't exist");
            e.printStackTrace();
            exists = false;
        }

        tx.commit();
        session.close();

        if(!exists)
            return;

        ((Stage)tabPane.getScene().getWindow()).close();
    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {

        if(registrationEmailTextField.getText().isEmpty()){
            this.showWarning("fill email");
            return;
        }

        if(registrationLoginTextField.getText().isEmpty()){
            this.showWarning("fill login");
        }

        SessionFactory sessionFactory = Postgres.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        boolean exception = false;

        User user = new User(registrationLoginTextField.getText(), registrationEmailTextField.getText(), registrationCheckBox.isSelected());

        try {
            session.save(user);
        }catch (ConstraintViolationException e){
            exception = true;
            e.printStackTrace();
            System.out.println(e.getConstraintName());
        }


        tx.commit();
        session.close();

        if(exception)
            return;

        User.LOGGED_USER = user;

        ((Stage)tabPane.getScene().getWindow()).close();
    }


}
