import org.jnetpcap.PcapIf;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class SelectDevice {
    private JPanel panel;
    private String selectDevice;
    private List<PcapIf> allDevices;
    private byte[] MAC_ADDRESS;

    public SelectDevice() {
        panel = new JPanel();
        allDevices= new FindDevice().findAllDevices();
        createWindow();
        showWindow();
        closeWindow();
    }

    private void createWindow() {
        panel.add(new JLabel("Please select a device:"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

        for (PcapIf device: allDevices) {
            model.addElement(device.getName());
        }
        JComboBox comboBox = new JComboBox<String>(model);
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Devices", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            selectDevice = (String) comboBox.getSelectedItem();

            try {
                MAC_ADDRESS = allDevices.get(comboBox.getSelectedIndex()).getHardwareAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getSelectDevice() {
        return selectDevice;
    }

    private void showWindow() {
        panel.setVisible(true);
    }

    private void closeWindow() {
        panel.setVisible(false);
    }

    public byte[] getMacAddress() {
        return MAC_ADDRESS;
    }
}