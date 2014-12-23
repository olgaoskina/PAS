import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;

/**
 * Created by olgaoskina
 * 23 December 2014.
 */
public class GuiForm extends JFrame{

	private JPanel panelMain;
	private JTabbedPane tabbedPaneTypes;
	private JPanel panelTCP;
	private JPanel panelUDP;
	private JPanel panelICMP;
	private JPanel paneMainBottom;
	private JPanel paneBottomLeft;
	private JPanel paneBottomRight;
	private JPanel panelMainLeft;
	private JPanel panelMainRight;
	private JLabel labelVersion;
	private JTextField textFieldVersion;
	private JTextField textFieldHeaderLenght;
	private JLabel labelHeaderLenght;
	private JTextField textFieldSourceIP;
	private JLabel labelSourceIP;
	private JTextField textFieldDestinationIP;
	private JLabel labelDestinationIP;
	private JTextField textFieldTotalLenght;
	private JLabel labelTotalLenght;
	private JPanel panelTypeOfService;
	private JPanel panelTypeTop;
	private JPanel panelTypeBottom;
	private JTextField textFieldTypePrecedence;
	private JLabel labelTypePrecedence;
	private JTextField textFieldTypeD;
	private JTextField textFieldTypeC;
	private JTextField textFieldTypeT;
	private JTextField textFieldTypeX;
	private JTextField textFieldTypeR;
	private JLabel labelTypeD;
	private JLabel labelTypeC;
	private JLabel labelTypeT;
	private JLabel labelTypeX;
	private JLabel labelTypeR;
	private JTextField textFieldOffset;
	private JTextField textFieldHeaderCheckSum;
	private JLabel labelOffset;
	private JLabel labelHeaderCheckSum;
	private JPanel panelFlags;
	private JPanel panelFlagsTop;
	private JPanel panelFlagsBottom;
	private JLabel lavelTypeOfService;
	private JLabel labelTCPData;
	private JTextField textFieldTCPData;
	private JPanel panelTCPTop;
	private JPanel panelTCPBottom;
	private JTextField textFieldUDPData;
	private JPanel panelUDPBottom;
	private JPanel panelUDPTop;
	private JLabel labelUDPData;
	private JTextField textFieldICMPData;
	private JPanel panelICMPTop;
	private JPanel panelICMPBottom;
	private JLabel labelICMPData;
	private JPanel panelMainTop;
	private JButton buttonSafe;
	private JButton buttonSend;
	private JButton buttonCansel;
	private JLabel labelUICMPType;
	private JComboBox comboBoxICMPType;
	private JLabel labelICMPCode;
	private JTextField textFieldICMPCode;
	private JLabel labelCheckSum;
	private JTextField textFieldCheckSum;
	private JLabel labelID;
	private JTextField textFieldID;
	private JLabel labelICMPSequence;
	private JTextField textFieldICMPSequence;
	private JLabel labelICMPHeaderLenght;
	private JTextField textFieldICMPHeaderLenght;
	private JLabel labelICMPDataLenght;
	private JTextField textFieldICMPDataLenght;
	private JTextField textFieldUDPSourcePort;
	private JTextField textFieldUSDPDestinationPort;
	private JTextField textFieldUDPHeaderLenght;
	private JLabel labelUDPSourcePort;
	private JLabel labelUDPDestinationPort;
	private JLabel labelUDPHeaderLenght;
	private JLabel panelTCPHeaderLenght;
	private JTextField textFieldTCPHeaderLenght;
	private JLabel labelTCPSourcePort;
	private JTextField textFieldTCPSourcePort;
	private JLabel labelDestinationPort;
	private JTextField textFieldTCPDestinationPort;
	private JLabel labelTCPCheckSum;
	private JTextField textFieldTCPSheckSum;
	private JLabel labelTCPFlags;
	private JPanel panelTCPLeft;
	private JPanel panelTCPRigth;
	private JPanel panelTCPRightTop;
	private JPanel panelTCPRigthBottom;
	private JCheckBox checkBoxTCPFlagsSYN;
	private JCheckBox CheckBoxTCPFlagsACK;
	private JCheckBox checkBoxTCPFlagsFIN;
	private JCheckBox checkBoxTCPFlagsRST;
	private JCheckBox checkBoxTCPFlagsPUSH;
	private JCheckBox checkBoxTCPFlagsURG;
	private JLabel labelTCPSeq;
	private JTextArea textAreaTCPSeq;
	private JTextField textFieldTCPAck;
	private JTextField textFieldTCPWin;
	private JLabel labelTCPAck;
	private JLabel labelTCPWin;
	private JCheckBox checkBoxFlagsX;
	private JCheckBox checkBoxFlagsD;
	private JCheckBox checkBoxFlagsM;

	public GuiForm() {
		super("PAS");
		setContentPane(panelMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void createUIComponents() {
		String[] items = {"Echo request", "Echo reply"};
		comboBoxICMPType = new JComboBox<String>(items);

	}
}
