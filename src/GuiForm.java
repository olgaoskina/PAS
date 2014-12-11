import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;

/**
 * Created by olgaoskina
 * 23 December 2014.
 */
public class GuiForm extends JFrame {

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
    private JTextField textFieldTypeECN;
    private JTextField textFieldTypeT;
    private JTextField textFieldTypeR;
    private JLabel labelTypeD;
    private JLabel labelTypeECN;
    private JLabel labelTypeT;
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
    private JPanel panelTCPButtons;
    private JButton buttonTCPSafe;
    private JButton buttonTCPSend;
    private JButton buttonTCPCansel;
    private JLabel labelUICMPType;
    private JComboBox comboBoxICMPType;
    private JLabel labelICMPCode;
    private JTextField textFieldICMPCode;
    private JLabel labelCheckSum;
    private JTextField textFieldCheckSum;
    private JLabel labelICMPID;
    private JTextField textFieldICMPID;
    private JLabel labelICMPSequence;
    private JTextField textFieldICMPSequence;
    private JLabel labelICMPHeaderLenght;
    private JTextField textFieldICMPHeaderLenght;
    private JLabel labelICMPDataLenght;
    private JTextField textFieldICMPDataLenght;
    private JTextField textFieldUDPSourcePort;
    private JTextField textFieldUDPDestinationPort;
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
    private JTextField textFieldID;
    private JLabel labelID;
    private JTextField textFieldTTL;
    private JLabel labelTTL;
    private JButton buttonUDPSafe;
    private JButton buttonUDPCansel;
    private JButton buttonUDPSend;
    private JPanel panelUDPButtons;
    private JButton buttonICMPSafe;
    private JButton buttonICMPCansel;
    private JButton buttonICMPSend;
    private JPanel panelICMPButtons;
    private GenerateIPPacket ipPacket;

    public GuiForm() {
        super("PAS");
        ipPacket = new GenerateIPPacket();
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        updateText();
        addIPListeners();
        addTCPListeners();
        addUDPListeners();
    }

    private void updateText() {
        try {
            ipPacket.setSourceAddress(InetAddress.getByName(textFieldSourceIP.getText()).getAddress());
            ipPacket.setDestinationAddress(InetAddress.getByName(textFieldDestinationIP.getText()).getAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
        String[] items = {"Echo request", "Echo reply"};
        comboBoxICMPType = new JComboBox<String>(items);

        textFieldDestinationIP = new JTextField();
        textFieldSourceIP = new JTextField();
        textFieldSourceIP.setText("10.0.2.15");
        textFieldDestinationIP.setText("10.0.2.16");


        textFieldHeaderLenght = new JTextField();
        textFieldHeaderLenght.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str.matches("[^0-9]")) {
                    return;
                }

                if (this.getLength() + str.length() <= 2) {
                    super.insertString(offs, str, a);
                }
            }
        });

        textFieldTotalLenght = new JTextField();
        textFieldTotalLenght.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str.matches("[^0-9]")) {
                    return;
                }

                if (this.getLength() + str.length() <= 5) {
                    super.insertString(offs, str, a);
                }
            }
        });

        try {
            textFieldVersion = new JFormattedTextField(new MaskFormatter("#"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addIPListeners() {
        textFieldVersion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setVersion(Byte.parseByte(e.getActionCommand()));
            }
        });

        textFieldHeaderLenght.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setHeaderLength(Byte.parseByte(e.getActionCommand()));
            }
        });
        textFieldTotalLenght.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTotalLength(Integer.parseInt(e.getActionCommand()));
            }
        });
        buttonTCPSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setSelectedType(Tcp.ID);
                ipPacket.send();
            }
        });

        buttonUDPSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setSelectedType(Udp.ID);
                ipPacket.send();
            }
        });

        textFieldTypePrecedence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setPrecedence(Byte.parseByte(e.getActionCommand()));
            }
        });
        textFieldTypeD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTosD(Byte.parseByte(e.getActionCommand()));
            }
        });

        textFieldTypeT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTosT(Byte.parseByte(e.getActionCommand()));
            }
        });

        textFieldTypeR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTosR(Byte.parseByte(e.getActionCommand()));
            }
        });

        textFieldTypeECN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTosECN(Byte.parseByte(e.getActionCommand()));
            }
        });

        checkBoxFlagsX.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                byte result;
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    result = 1;
                } else {
                    result = 0;
                }
                ipPacket.setFlagX(result);
            }
        });

        checkBoxFlagsD.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                byte result;
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    result = 1;
                } else {
                    result = 0;
                }
                ipPacket.setFlagD(result);
            }
        });

        checkBoxFlagsM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                byte result;
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    result = 1;
                } else {
                    result = 0;
                }
                ipPacket.setFlagM(result);
            }
        });

        textFieldID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setID(Integer.parseInt(e.getActionCommand()));
            }
        });

        textFieldOffset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setOffset(Integer.parseInt(e.getActionCommand()));
            }
        });

        textFieldTTL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTTL(Integer.parseInt(e.getActionCommand()));
            }
        });

        textFieldSourceIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                if (validIP(actionCommand)) {

                    try {
                        ipPacket.setSourceAddress(InetAddress.getByName(actionCommand).getAddress());
                    } catch (UnknownHostException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    System.out.println("SOURCE IP ERROR!!!");
                }
            }
        });

        textFieldDestinationIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                if (validIP(actionCommand)) {

                    try {
                        ipPacket.setDestinationAddress(InetAddress.getByName(actionCommand).getAddress());
                    } catch (UnknownHostException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    System.out.println("DESTINATION IP ERROR!!!");
                }
            }
        });

        textFieldHeaderCheckSum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                System.out.println(actionCommand.matches("\\w"));
                if (actionCommand.matches("\\w")) {
                    ipPacket.setCalculateCheckSum(true);
                } else {

                    ipPacket.setCheckSum(Integer.parseInt(actionCommand));
                }
            }
        });
    }

    private boolean validIP(String ip) {
        return ip.matches("^\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}$");
    }

    private void addTCPListeners() {
        textFieldTCPHeaderLenght.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTCPHeaderLength(Integer.parseInt(e.getActionCommand()));
            }
        });

        textFieldTCPSourcePort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setTCPSourcePort(Integer.parseInt(e.getActionCommand()));
            }
        });
    }

    private void addUDPListeners() {


        textFieldUDPSourcePort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setUDPSourcePort(Integer.parseInt(e.getActionCommand()));
            }
        });
        textFieldUDPDestinationPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setUDPDestinationPort(Integer.parseInt(e.getActionCommand()));
            }
        });
        textFieldUDPHeaderLenght.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setUDPHeaderLength(Integer.parseInt(e.getActionCommand()));
            }
        });
        textFieldUDPData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipPacket.setUDPData(e.getActionCommand().getBytes());
            }
        });



    }
}
