package Controller;

import Model.MotorModel;
import Model.TransaksiModel;
import View.HomeView;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryzal
 */
public class HomeController {

    private MotorModel motor;
    private TransaksiModel transaksi;

    public void setMotor(MotorModel motor) {
        this.motor = motor;
    }

    public void setTransaksi(TransaksiModel transaksi) {
        this.transaksi = transaksi;
    }

    public void getData(HomeView view) {
        int pilih = view.getTabelMotor().getSelectedRow();

        String nopol = (String) view.getTabelMotor().getValueAt(pilih, 0);
        String merk = (String) view.getTabelMotor().getValueAt(pilih, 1);
        String type = (String) view.getTabelMotor().getValueAt(pilih, 2);
        String tahun = (String) view.getTabelMotor().getValueAt(pilih, 3);
        String harga = (String) view.getTabelMotor().getValueAt(pilih, 4);

        view.getfNopol().setText(nopol);
        view.getfMerk().setText(merk);
        view.getfType().setText(type);
        view.getfTahun().setText(tahun);
        view.getfHarga().setText(harga);

        motor.setNopol(nopol);
    }

    public void readMotor(HomeView view) throws SQLException {
        motor.read(view);
    }

    public void addMotor(HomeView view) throws SQLException {
        try {
            String nopol = view.getfNopol().getText();
            String merk = view.getfMerk().getText();
            String type = view.getfType().getText();
            String tahun = view.getfTahun().getText();
            String harga = view.getfHarga().getText();

            Double thn = Double.parseDouble(tahun);
            Double hrg = Double.parseDouble(harga);

            if (nopol.equals("") || merk.equals("") || type.equals("") || tahun.equals("") || harga.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                motor.addMotor(view);
                motor.read(view);
                view.reset();
            }
        } catch (HeadlessException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Tahun atau harga harus berupa angka");
        }
    }

    public void updateMotor(HomeView view) throws SQLException {
        try {
            String nopol = view.getfNopol().getText();
            String merk = view.getfMerk().getText();
            String type = view.getfType().getText();
            String tahun = view.getfTahun().getText();
            String harga = view.getfHarga().getText();

            Double thn = Double.parseDouble(tahun);
            Double hrg = Double.parseDouble(harga);

            if (nopol.equals("") || merk.equals("") || type.equals("") || tahun.equals("") || harga.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                int opsi = JOptionPane.showConfirmDialog(view, "Yakin ingin update data?");
                if (opsi == JOptionPane.YES_OPTION) {
                    motor.update(view, nopol, merk, type, thn, hrg);
                    motor.read(view);
                    view.reset();
                }
            }
        }catch (HeadlessException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void deleteMotor(HomeView view) throws SQLException {
        try{
            String nopol = view.getfNopol().getText();
            String merk = view.getfMerk().getText();
            String type = view.getfType().getText();
            String tahun = view.getfTahun().getText();
            String harga = view.getfHarga().getText();
            
            if (nopol.equals("") || merk.equals("") || type.equals("") || tahun.equals("") || harga.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                int opsi = JOptionPane.showConfirmDialog(view, "Yakin ingin hapus data?");
                if (opsi == JOptionPane.YES_OPTION) {
                    motor.delete(view, nopol);
                    motor.read(view);
                    view.reset();
                }
            }            
        }catch (HeadlessException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void readTrabsaksi(HomeView view) {
        transaksi.read(view);
    }
    
    public void readTrabsaksiSelesai(HomeView view) {
        transaksi.readSelesai(view);
    }

    public void kembaliMotor(HomeView view) {
        int pilih = view.getTabelTransaksi().getSelectedRow();

        String nik = (String) view.getTabelTransaksi().getValueAt(pilih, 1);
        String nopol = (String) view.getTabelTransaksi().getValueAt(pilih, 4);
        
        String status = "ada";
        
        transaksi.updateTransaksi(view, nik);
        transaksi.updateStatusMotor(view, status, nopol);
        transaksi.read(view);
        transaksi.readSelesai(view);
    }

    public void cmNopol(HomeView view) {
        transaksi.cmNopol(view);
    }

    public void dataSewaMotor(HomeView view, String selectedValue) {
        transaksi.dataSewaMotor(view, selectedValue);
    }

    public void sewaMotor(HomeView view) throws SQLException {
        try {
            String nama = view.getfNama().getText();
            String nik = view.getfNIK().getText();
            String noTelp = view.getfNoTlp().getText();
            String alamat = view.getfAlamat().getText();
            String nopol = view.getCmNopol().getSelectedItem().toString();
            String durasi = view.getCmDurasi().getSelectedItem().toString();
            String harga = view.getlTotal().getText();
            String status = "peminjaman";
            
            if (nama.equals("") || nik.equals("") || noTelp.equals("") || alamat.equals("") || durasi.equals("") ||harga.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                int opsi = JOptionPane.showConfirmDialog(view, "Yakin ingin menyewa?");
                if (opsi == JOptionPane.YES_OPTION) {
                    transaksi.sewaMotor(view, nama, nik, noTelp, alamat, nopol, durasi, harga);
                    transaksi.updateStatusSewaMotor(view, status, nopol);
                    view.resetFormSewa();
                }
            }
        }catch (HeadlessException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
}
