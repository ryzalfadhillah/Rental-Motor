package Controller;

import Model.LoginModel;
import View.LoginView;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController {

    private LoginModel model;

    public void setLogin(LoginModel model) {
        this.model = model;
    }

    public void cekLogin(LoginView view) throws SQLException {
        try {
            String email = view.getfEmail().getText();
            String pass = view.getfPassword().getText();

            if (email.trim().equals("") || pass.trim().equals("")) {
                JOptionPane.showMessageDialog(view, "Email atau Password tidak boleh kosong");
            } else {
                model.cekLogin(view);
            }
        } catch (HeadlessException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
}
