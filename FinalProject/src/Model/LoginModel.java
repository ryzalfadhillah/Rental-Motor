package Model;

import Connection.Koneksi;
import View.HomeView;
import View.LoginView;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginModel {

    private String email;
    private String pass;
    HomeView home;

    Koneksi conn = new Koneksi();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void cekLogin(LoginView view) throws SQLException {
        String email = view.getfEmail().getText();
        String pass = view.getfPassword().getText();

        String query = "SELECT * FROM admin WHERE email = '" + email + "'";

        conn.statement = conn.connection.createStatement();
        ResultSet rs = conn.statement.executeQuery(query);

        if (rs.next()) {
            if (email.equals(rs.getString("email")) && pass.equals(rs.getString("password"))) {
                JOptionPane.showMessageDialog(view, "Login berhasil");
                home = new HomeView();
                home.getLabelNama().setText(rs.getString("username"));
                home.setVisible(true);                
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Email atau password salah");
            }
        }
    }
}
