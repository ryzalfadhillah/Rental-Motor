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
public class TransaksiModel {

    private String nama;
    private String NIK;
    private String noTelp;
    private String alamat;
    private String nopol;
    private String merk;
    private String type;
    private String tahun;
    private int durasi;
    private double harga;

    Koneksi conn = new Koneksi();

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

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

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void read(HomeView view) {
        try {
            DefaultTableModel tabelData = new DefaultTableModel();
            view.getTabelTransaksi().setModel(tabelData);

            tabelData.addColumn("Nama");
            tabelData.addColumn("NIK");
            tabelData.addColumn("No_Telp");
            tabelData.addColumn("Alamat");
            tabelData.addColumn("No Polisi");
            tabelData.addColumn("Durasi");
            tabelData.addColumn("Harga");

            String query = "SELECT * FROM transaksi WHERE status = 'peminjaman'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                Object[] obj = new Object[7];

                obj[0] = rs.getString("nama");
                obj[1] = rs.getString("nik");
                obj[2] = rs.getString("noTelp");
                obj[3] = rs.getString("alamat");
                obj[4] = rs.getString("nopol");
                obj[5] = rs.getString("durasi");
                obj[6] = rs.getString("harga");

                tabelData.addRow(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void readSelesai(HomeView view) {
        try {
            DefaultTableModel tabelData = new DefaultTableModel();
            view.getTabelTransaksiSelesai().setModel(tabelData);

            tabelData.addColumn("Nama");
            tabelData.addColumn("NIK");
            tabelData.addColumn("No_Telp");
            tabelData.addColumn("Alamat");
            tabelData.addColumn("No Polisi");
            tabelData.addColumn("Durasi");
            tabelData.addColumn("Harga");

            String query = "SELECT * FROM transaksi WHERE status = 'selesai'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                Object[] obj = new Object[7];

                obj[0] = rs.getString("nama");
                obj[1] = rs.getString("nik");
                obj[2] = rs.getString("noTelp");
                obj[3] = rs.getString("alamat");
                obj[4] = rs.getString("nopol");
                obj[5] = rs.getString("durasi");
                obj[6] = rs.getString("harga");

                tabelData.addRow(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void updateTransaksi(HomeView view, String nik) {
        try {
            String query = "UPDATE transaksi SET status = 'selesai' WHERE nik = '" + nik + "'";

            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void updateStatusMotor(HomeView view, String status, String nopol) {
        try {
            String query = "UPDATE motor SET status = '" + status + "' WHERE nopol = '" + nopol + "'";
            
            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);

            JOptionPane.showMessageDialog(view, "Berhasil mengembalikan motor");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void cmNopol(HomeView view) {
        try {
            String query = "SELECT * FROM motor WHERE status = 'ada'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                view.getCmNopol().addItem(rs.getString("nopol"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void dataSewaMotor(HomeView view, String selectedValue) {
        try {
            String query = "SELECT * FROM motor WHERE nopol = '" + selectedValue + "'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                view.getlMerk().setText(rs.getString("merk"));
                view.getlType().setText(rs.getString("type"));
                view.getlTahun().setText(rs.getString("tahun"));
                view.getlHarga().setText(rs.getString("harga"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void sewaMotor(HomeView view, String nama, String nik, String noTelp, String alamat, String nopol, String durasi, String harga) {
        try {
            String query = "INSERT INTO `transaksi`(`nama`, `nik`, `noTelp`, `alamat`, `nopol`, `durasi`, `harga`, `status`) VALUES ('" + 
                    nama + "', '" + nik + "', '" + noTelp + "', '" + alamat + "', '" + nopol + "', '" + durasi + "', '" + harga + "', 'peminjaman')";

            conn.statement = conn.connection.createStatement();
            conn.statement.execute(query);

            JOptionPane.showMessageDialog(view, "Berhasil menyewa motor");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
    
    public void updateStatusSewaMotor(HomeView view, String status, String nopol) {
        try {
            String query = "UPDATE motor SET status = '" + status + "' WHERE nopol = '" + nopol + "'";
            
            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }
}
