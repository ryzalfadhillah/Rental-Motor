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
}
