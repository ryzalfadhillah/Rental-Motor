package Model;

import Connection.Koneksi;
import View.HomeView;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ryzal
 */
public class MotorModel {

    private String nopol;
    private String merk;
    private String type;
    private String tahun;
    private String harga;

    Koneksi conn = new Koneksi();

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void read(HomeView view) throws SQLException {
        try {
            DefaultTableModel tabelData = new DefaultTableModel();
            view.getTabelMotor().setModel(tabelData);

            tabelData.addColumn("No. Polisi");
            tabelData.addColumn("Merk");
            tabelData.addColumn("Type");
            tabelData.addColumn("Tahun");
            tabelData.addColumn("Harga");
            tabelData.addColumn("Status");

            String query = "SELECT * FROM motor";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                Object[] obj = new Object[6];

                obj[0] = rs.getString("nopol");
                obj[1] = rs.getString("merk");
                obj[2] = rs.getString("type");
                obj[3] = rs.getString("tahun");
                obj[4] = rs.getString("harga");
                obj[5] = rs.getString("status");

                tabelData.addRow(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void addMotor(HomeView view) {
        try {
            String nopol = view.getfNopol().getText();
            String merk = view.getfMerk().getText();
            String type = view.getfType().getText();
            String tahun = view.getfTahun().getText();
            String harga = view.getfHarga().getText();

            String query = "INSERT INTO `motor` (`nopol`, `merk`, `type`, `tahun`, `harga`, `status`) VALUES ('"
                    + nopol + "', '"
                    + merk + "', '"
                    + type + "', '"
                    + tahun + "', '"
                    + harga + "', 'ada')";

            conn.statement = conn.connection.createStatement();
            conn.statement.execute(query);

            JOptionPane.showMessageDialog(view, "Berhasil menambahkan motor");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Data sudah ada");
        }
    }

    public void update(HomeView view, String nopol, String merk, String type, Double thn, Double hrg) {
        try {
            String query = "UPDATE motor SET nopol = '"
                    + nopol + "', merk = '"
                    + merk + "', type = '"
                    + type + "', tahun = '"
                    + thn + "', harga = '"
                    + hrg + "' WHERE motor.nopol = '" + getNopol() + "'";

            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);

            JOptionPane.showMessageDialog(view, "Berhasil update data");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void delete(HomeView view, String nopol) {
        try {
            String query = "DELETE FROM `motor` WHERE `nopol` = '" + getNopol() + "'";

            conn.statement = conn.connection.createStatement();
            conn.statement.execute(query);

            JOptionPane.showMessageDialog(view, "Berhasil menghapus data");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

}
