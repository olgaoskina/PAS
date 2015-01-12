import org.jnetpcap.PcapIf;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Info {
    private JPanel panel;

    public Info() {
        panel = new JPanel();
        createWindow();
        showWindow();
        closeWindow();
    }

    private void createWindow() {
        panel.add(new JLabel("Если Вы хотите посчитать checksum автоматически, "));
        panel.add(new JLabel("внесите в соответствующее поле: a"));

        int result = JOptionPane.showConfirmDialog(null, panel, "Info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }

    }

    private void showWindow() {
        panel.setVisible(true);
    }

    private void closeWindow() {
        panel.setVisible(false);
    }

}