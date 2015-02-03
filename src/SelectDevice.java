import org.jnetpcap.PcapIf;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by olgaoskina
 * 03 February 2015
 */
public class SelectDevice {
    private JPanel panel;
    private String selectedDevice;
    private List<PcapIf> allDevices;
    private byte[] macAddress;

    public SelectDevice() {
        panel = new JPanel();
        allDevices = FindDevice.getInstance().findAllDevices();
        createWindow();
        panel.setVisible(true);
        panel.setVisible(false);
    }

    private void createWindow() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

        for (PcapIf device : allDevices) {
            model.addElement(device.getName());
        }

        JComboBox comboBox = new JComboBox<>(model);
        panel.add(new JLabel("Please select a device:"));
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Devices",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.OK_OPTION) {
            selectedDevice = (String) comboBox.getSelectedItem();

            try {
                macAddress = allDevices.get(comboBox.getSelectedIndex()).getHardwareAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (result == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }

    public String getSelectedDevice() {
        return selectedDevice;
    }

    public byte[] getMacAddress() {
        return macAddress;
    }
}