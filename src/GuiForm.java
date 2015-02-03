import com.sun.org.apache.xpath.internal.operations.Bool;
import org.savarese.vserv.tcpip.ICMPEchoPacket;
import org.savarese.vserv.tcpip.TCPPacket;
import org.savarese.vserv.tcpip.UDPPacket;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;

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
    private JButton buttonTCPSave;
    private JButton buttonTCPSend;
    private JButton buttonTCPCansel;
    private JLabel labelUICMPType;
    private JComboBox comboBoxICMPType;
    private JLabel labelICMPCode;
    private JTextField textFieldICMPCode;
    private JLabel labelICMPCheckSum;
    private JTextField textFieldICMPCheckSum;
    private JLabel labelICMPID;
    private JTextField textFieldICMPID;
    private JLabel labelICMPSequence;
    private JTextField textFieldICMPSequence;
    private JTextField textFieldUDPSourcePort;
    private JTextField textFieldUDPDestinationPort;
    private JTextField textFieldUDPLenght;
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
    private JTextField textFieldTCPCheckSum;
    private JLabel labelTCPFlags;
    private JPanel panelTCPLeft;
    private JPanel panelTCPRigth;
    private JPanel panelTCPRightTop;
    private JPanel panelTCPRigthBottom;
    private JCheckBox checkBoxTCPFlagsSYN;
    private JCheckBox checkBoxTCPFlagsACK;
    private JCheckBox checkBoxTCPFlagsFIN;
    private JCheckBox checkBoxTCPFlagsRST;
    private JCheckBox checkBoxTCPFlagsPSH;
    private JCheckBox checkBoxTCPFlagsURG;
    private JLabel labelTCPSeq;
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
    private JButton buttonUDPSave;
    private JButton buttonUDPCansel;
    private JButton buttonUDPSend;
    private JPanel panelUDPButtons;
    private JButton buttonICMPSave;
    private JButton buttonICMPCansel;
    private JButton buttonICMPSend;
    private JPanel panelICMPButtons;
    private JTextField textFieldTCPSeq;
    private JTextField textFieldTCPUrgentPointer;
    private JLabel labelTCPUrgentPointer;
    private JButton buttonTCPOpen;
    private JCheckBox checkBoxTCPReserved1;
    private JCheckBox checkBoxTCPReserved2;
    private JCheckBox checkBoxTCPReserved3;
    private JCheckBox checkBoxTCPReserved4;
    private JCheckBox checkBoxTCPReservedCWR;
    private JCheckBox checkBoxTCPReservedECE;
    private JLabel labelTCPReserved;
    private JTextField textFieldUDPCheckSum;
    private JLabel labelUDPCheckSum;
    private JButton buttonUDPOpen;
    private JButton buttonICMPOpen;

    private GenerateTCPPacket generateTCPPacket;
    private GenerateUDPPacket generateUDPPacket;
    private GenerateICMPPacket generateICMPPacket;

    private TCPPacket tcpPacket;
    private UDPPacket udpPacket;
    private ICMPEchoPacket icmpEchoPacket;

    private String DEVICE;


    public GuiForm(String device) {
        super("PAS");
        DEVICE = device;
        $$$setupUI$$$();
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        addTCPListeners();
        addUDPListeners();
        addICMPListeners();
    }


    private void createUIComponents() {
        String[] items = {"Echo request", "Echo reply"};
        comboBoxICMPType = new JComboBox<String>(items);

        textFieldDestinationIP = new JTextField();
        textFieldSourceIP = new JTextField();
        textFieldSourceIP.setText("10.0.177.124");
        textFieldDestinationIP.setText("10.0.177.126");


        try {
            textFieldVersion = new JFormattedTextField(new MaskFormatter("#"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        textFieldHeaderCheckSum = new JTextField();
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
        textFieldID = new JTextField();
        textFieldOffset = new JTextField();
        textFieldTTL = new JTextField();


        textFieldVersion.setText("4");
        textFieldHeaderCheckSum.setText("a");
        textFieldTotalLenght.setText("200");
        textFieldHeaderLenght.setText("12");
        textFieldID.setText("55");
        textFieldOffset.setText("0");
        textFieldTTL.setText("23");


        textFieldTypePrecedence = new JTextField();
        textFieldTypeD = new JTextField();
        textFieldTypeR = new JTextField();
        textFieldTypeT = new JTextField();
        textFieldTypeECN = new JTextField();
        textFieldTCPHeaderLenght = new JTextField();
        textFieldTCPSourcePort = new JTextField();
        textFieldTCPDestinationPort = new JTextField();
        textFieldTCPCheckSum = new JTextField();
        textFieldTCPUrgentPointer = new JTextField();
        textFieldTCPSeq = new JTextField();
        textFieldTCPAck = new JTextField();
        textFieldTCPWin = new JTextField();
        textFieldTCPData = new JTextField();

        textFieldTypePrecedence.setText("1");
        textFieldTypeD.setText("1");
        textFieldTypeR.setText("1");
        textFieldTypeT.setText("1");
        textFieldTypeECN.setText("1");
        textFieldTCPHeaderLenght.setText("5");
        textFieldTCPSourcePort.setText("444");
        textFieldTCPDestinationPort.setText("333");
        textFieldTCPCheckSum.setText("a");
        textFieldTCPUrgentPointer.setText("1");
        textFieldTCPSeq.setText("1");
        textFieldTCPAck.setText("1");
        textFieldTCPWin.setText("1");
        textFieldTCPData.setText("TCP packet");

        textFieldUDPSourcePort = new JTextField();
        textFieldUDPDestinationPort = new JTextField();
        textFieldUDPCheckSum = new JTextField();
        textFieldUDPLenght = new JTextField();
        textFieldUDPData = new JTextField();

        textFieldUDPSourcePort.setText("333");
        textFieldUDPDestinationPort.setText("444");
        textFieldUDPCheckSum.setText("a");
        textFieldUDPLenght.setText("12");
        textFieldUDPData.setText("UDP packet");

        textFieldICMPCheckSum = new JTextField();
        textFieldICMPCode = new JTextField();
        textFieldICMPID = new JTextField();
        textFieldICMPSequence = new JTextField();
        textFieldICMPData = new JTextField();


        textFieldICMPCheckSum.setText("a");
        textFieldICMPCode.setText("0");
        textFieldICMPID.setText("12");
        textFieldICMPSequence.setText("23");
        textFieldICMPData.setText("ICMP packet");
    }

    private GenerateTCPPacket readIpAndTcpParametersAndGeneratePacket() {
        int version = Integer.parseInt(textFieldVersion.getText());
        int headerLength = Integer.parseInt(textFieldHeaderLenght.getText());
        int typePrecedence = Integer.parseInt(textFieldTypePrecedence.getText());
        int tosD = Integer.parseInt(textFieldTypeD.getText());
        int tosT = Integer.parseInt(textFieldTypeT.getText());
        int tosR = Integer.parseInt(textFieldTypeR.getText());
        int tosECN = Integer.parseInt(textFieldTypeECN.getText());
        int totalLength = Integer.parseInt(textFieldTotalLenght.getText());
        int id = Integer.parseInt(textFieldID.getText());
        boolean flagX = checkBoxFlagsX.isSelected();
        boolean flagD = checkBoxFlagsD.isSelected();
        boolean flagM = checkBoxFlagsM.isSelected();
        int offset = Integer.parseInt(textFieldOffset.getText());
        int ttl = Integer.parseInt(textFieldTTL.getText());
        int checkSum = 0;
        boolean needCalculateCheckSum = false;
        if (textFieldHeaderCheckSum.getText().matches("\\w")) {
            needCalculateCheckSum = true;
        } else {
            checkSum = Integer.parseInt(textFieldHeaderCheckSum.getText());
        }
        byte[] sourceIP = null;
        byte[] destinationIP = null;

        try {

            sourceIP = InetAddress.getByName(textFieldSourceIP.getText()).getAddress();

            destinationIP = InetAddress.getByName(textFieldDestinationIP.getText()).getAddress();
            System.out.println("[IP TEXT]: " + InetAddress.getByAddress(destinationIP).getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        int tcpSourcePort = Integer.parseInt(textFieldTCPSourcePort.getText());
        int tcpDestinationPort = Integer.parseInt(textFieldTCPDestinationPort.getText());
        int tcpSeq = Integer.parseInt(textFieldTCPSeq.getText());
        int tcpWinSize = Integer.parseInt(textFieldTCPWin.getText());
        int tcpCheckSum = 0;
        boolean tcpNeedCalculateCheckSum = false;
        if (textFieldTCPCheckSum.getText().matches("\\w")) {
            tcpNeedCalculateCheckSum = true;
        } else {
            tcpCheckSum = Integer.parseInt(textFieldTCPCheckSum.getText());
        }

        int tcpHeaderLength = Integer.parseInt(textFieldTCPHeaderLenght.getText());
        int tcpACK = Integer.parseInt(textFieldTCPAck.getText());
        boolean tcpFlagSYN = checkBoxTCPFlagsSYN.isSelected();
        boolean tcpFlagACK = checkBoxTCPFlagsACK.isSelected();
        boolean tcpFlagFIN = checkBoxTCPFlagsFIN.isSelected();
        boolean tcpFlagRST = checkBoxTCPFlagsRST.isSelected();
        boolean tcpFlagPSH = checkBoxTCPFlagsPSH.isSelected();
        boolean tcpFlagURG = checkBoxTCPFlagsURG.isSelected();
        boolean tcpFlagCWR = checkBoxTCPReservedCWR.isSelected();
        boolean tcpFlagECE = checkBoxTCPReservedECE.isSelected();
        boolean tcpFlagReserved1 = checkBoxTCPReserved1.isSelected();
        boolean tcpFlagReserved2 = checkBoxTCPReserved2.isSelected();
        boolean tcpFlagReserved3 = checkBoxTCPReserved3.isSelected();
        boolean tcpFlagReserved4 = checkBoxTCPReserved4.isSelected();
        int tcpUrgentPointer = Integer.parseInt(textFieldTCPUrgentPointer.getText());

        byte[] tcpData = textFieldTCPData.getText().getBytes();


        tcpPacket = new TCPPacket(Math.max(tcpData.length + 4 * headerLength + 4 * tcpHeaderLength, totalLength));
        generateTCPPacket = new GenerateTCPPacket(tcpPacket, DEVICE);

        generateTCPPacket.setVersion(version);
        generateTCPPacket.setHeaderLength(headerLength);
        generateTCPPacket.setPrecedence(typePrecedence);
        generateTCPPacket.setTosD(tosD);
        generateTCPPacket.setTosECN(tosECN);
        generateTCPPacket.setTosR(tosR);
        generateTCPPacket.setTosT(tosT);
        generateTCPPacket.setTotalLength(totalLength);
        generateTCPPacket.setID(id);
        generateTCPPacket.setFlagD(flagD ? 1 : 0);
        generateTCPPacket.setFlagM(flagM ? 1 : 0);
        generateTCPPacket.setFlagX(flagX ? 1 : 0);
        generateTCPPacket.setOffset(offset);
        generateTCPPacket.setTTL(ttl);
        generateTCPPacket.setNeedCalculateTCPCheckSum(tcpNeedCalculateCheckSum);
        generateTCPPacket.setNeedCalculateIPCheckSum(needCalculateCheckSum);
        generateTCPPacket.setCheckSum(checkSum);
        try {
            System.out.println("[SOURCE IP]: " + InetAddress.getByAddress(sourceIP).getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        generateTCPPacket.setSourceAddress(sourceIP);
        generateTCPPacket.setDestinationAddress(destinationIP);

        generateTCPPacket.setTCPSourcePort(tcpSourcePort);
        generateTCPPacket.setTCPDestinationPort(tcpDestinationPort);
        generateTCPPacket.setTCPSeqNumber(tcpSeq);
        generateTCPPacket.setTCPAckNumber(tcpACK);
        generateTCPPacket.setTCPHeaderLength(tcpHeaderLength);
        generateTCPPacket.setTCPSyn(tcpFlagSYN);
        generateTCPPacket.setTCPAck(tcpFlagACK);
        generateTCPPacket.setTCPFin(tcpFlagFIN);
        generateTCPPacket.setTCPRst(tcpFlagRST);
        generateTCPPacket.setTCPPsh(tcpFlagPSH);
        generateTCPPacket.setTCPUrg(tcpFlagURG);
        generateTCPPacket.setTCPReservedCWR(tcpFlagCWR);
        generateTCPPacket.setTCPReservedECE(tcpFlagECE);
        generateTCPPacket.setTCPReserved1(tcpFlagReserved1);
        generateTCPPacket.setTCPReserved2(tcpFlagReserved2);
        generateTCPPacket.setTCPReserved3(tcpFlagReserved3);
        generateTCPPacket.setTCPReserved4(tcpFlagReserved4);

        generateTCPPacket.setTCPWinSize(tcpWinSize);
        generateTCPPacket.setTCPCheckSum(tcpCheckSum);
        generateTCPPacket.setTCPCalculateCheckSum(tcpNeedCalculateCheckSum);
        generateTCPPacket.setTCPUrgentPointer(tcpUrgentPointer);

        generateTCPPacket.setTCPData(tcpData);
        generateTCPPacket.generate();
        return generateTCPPacket;
    }

    private GenerateUDPPacket readIpAndUdpParametersAndGeneratePacket() {
        int version = Integer.parseInt(textFieldVersion.getText());
        int headerLength = Integer.parseInt(textFieldHeaderLenght.getText());
        int typePrecedence = Integer.parseInt(textFieldTypePrecedence.getText());
        int tosD = Integer.parseInt(textFieldTypeD.getText());
        int tosT = Integer.parseInt(textFieldTypeT.getText());
        int tosR = Integer.parseInt(textFieldTypeR.getText());
        int tosECN = Integer.parseInt(textFieldTypeECN.getText());
        int totalLength = Integer.parseInt(textFieldTotalLenght.getText());
        int id = Integer.parseInt(textFieldID.getText());
        boolean flagX = checkBoxFlagsX.isSelected();
        boolean flagD = checkBoxFlagsD.isSelected();
        boolean flagM = checkBoxFlagsM.isSelected();
        int offset = Integer.parseInt(textFieldOffset.getText());
        int ttl = Integer.parseInt(textFieldTTL.getText());
        int checkSum = 0;
        boolean needCalculateCheckSum = false;
        if (textFieldHeaderCheckSum.getText().matches("\\w")) {
            needCalculateCheckSum = true;
        } else {
            checkSum = Integer.parseInt(textFieldHeaderCheckSum.getText());
        }
        byte[] sourceIP = null;
        byte[] destinationIP = null;

        try {
            sourceIP = InetAddress.getByName(textFieldSourceIP.getText()).getAddress();
            destinationIP = InetAddress.getByName(textFieldDestinationIP.getText()).getAddress();
            System.out.println("[IP TEXT]: " + InetAddress.getByAddress(destinationIP).getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        int udpSourcePort = Integer.parseInt(textFieldUDPSourcePort.getText());
        int udpDestinationPort = Integer.parseInt(textFieldUDPDestinationPort.getText());
        int udpChecksum = 0;
        boolean udpNeedCalculateCheckSum = false;
        if (textFieldUDPCheckSum.getText().matches("\\w")) {
            udpNeedCalculateCheckSum = true;
        } else {
            udpChecksum = Integer.parseInt(textFieldUDPCheckSum.getText());
        }

        int udpLength = Integer.parseInt(textFieldUDPLenght.getText());

        byte[] tcpData = textFieldUDPData.getText().getBytes();


        udpPacket = new UDPPacket(Math.max(tcpData.length + 4 * headerLength + 4 * udpLength, totalLength));
        generateUDPPacket = new GenerateUDPPacket(udpPacket, DEVICE);

        generateUDPPacket.setVersion(version);
        generateUDPPacket.setHeaderLength(headerLength);
        generateUDPPacket.setPrecedence(typePrecedence);
        generateUDPPacket.setTosD(tosD);
        generateUDPPacket.setTosECN(tosECN);
        generateUDPPacket.setTosR(tosR);
        generateUDPPacket.setTosT(tosT);
        generateUDPPacket.setTotalLength(totalLength);
        generateUDPPacket.setID(id);
        generateUDPPacket.setFlagD(flagD ? 1 : 0);
        generateUDPPacket.setFlagM(flagM ? 1 : 0);
        generateUDPPacket.setFlagX(flagX ? 1 : 0);
        generateUDPPacket.setOffset(offset);
        generateUDPPacket.setTTL(ttl);
        generateUDPPacket.setNeedCalculateTCPCheckSum(udpNeedCalculateCheckSum);
        generateUDPPacket.setNeedCalculateIPCheckSum(needCalculateCheckSum);
        generateUDPPacket.setCheckSum(checkSum);
        try {
            System.out.println("[SOURCE IP]: " + InetAddress.getByAddress(sourceIP).getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        generateUDPPacket.setSourceAddress(sourceIP);
        generateUDPPacket.setDestinationAddress(destinationIP);

        generateUDPPacket.setUDPSourcePort(udpSourcePort);
        generateUDPPacket.setUDPDestinationPort(udpDestinationPort);
        generateUDPPacket.setUDPLength(udpLength);
        generateUDPPacket.setUDPCheckSum(udpChecksum);
        generateUDPPacket.setNeedUDPCalculateCheckSum(udpNeedCalculateCheckSum);

        generateUDPPacket.setUDPData(tcpData);
        generateUDPPacket.generate();
        return generateUDPPacket;
    }

    private GenerateICMPPacket readIpAndIcmpParametersAndGeneratePacket() {
        int version = Integer.parseInt(textFieldVersion.getText());
        int headerLength = Integer.parseInt(textFieldHeaderLenght.getText());
        int typePrecedence = Integer.parseInt(textFieldTypePrecedence.getText());
        int tosD = Integer.parseInt(textFieldTypeD.getText());
        int tosT = Integer.parseInt(textFieldTypeT.getText());
        int tosR = Integer.parseInt(textFieldTypeR.getText());
        int tosECN = Integer.parseInt(textFieldTypeECN.getText());
        int totalLength = Integer.parseInt(textFieldTotalLenght.getText());
        int id = Integer.parseInt(textFieldID.getText());
        boolean flagX = checkBoxFlagsX.isSelected();
        boolean flagD = checkBoxFlagsD.isSelected();
        boolean flagM = checkBoxFlagsM.isSelected();
        int offset = Integer.parseInt(textFieldOffset.getText());
        int ttl = Integer.parseInt(textFieldTTL.getText());
        int checkSum = 0;
        boolean needCalculateCheckSum = false;
        if (textFieldHeaderCheckSum.getText().matches("\\w")) {
            needCalculateCheckSum = true;
        } else {
            checkSum = Integer.parseInt(textFieldHeaderCheckSum.getText());
        }
        byte[] sourceIP = null;
        byte[] destinationIP = null;

        try {
            sourceIP = InetAddress.getByName(textFieldSourceIP.getText()).getAddress();
            destinationIP = InetAddress.getByName(textFieldDestinationIP.getText()).getAddress();
            System.out.println("[IP TEXT]: " + InetAddress.getByAddress(destinationIP).getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        int icmpChecksum = 0;
        boolean icmpNeedCalculateCheckSum = false;
        if (textFieldICMPCheckSum.getText().matches("\\w")) {
            icmpNeedCalculateCheckSum = true;
        } else {
            icmpChecksum = Integer.parseInt(textFieldICMPCheckSum.getText());
        }
        int icmpCode = Integer.parseInt(textFieldICMPCode.getText());
        int icmpID = Integer.parseInt(textFieldICMPID.getText());
        int icmpSeqNumber = Integer.parseInt(textFieldICMPSequence.getText());
        int icmpType = comboBoxICMPType.getSelectedIndex() == 1 ? 0 : 8;
        byte[] icmpData = textFieldICMPData.getText().getBytes();


        icmpEchoPacket = new ICMPEchoPacket(Math.max(icmpData.length + 4 * headerLength + 4 * 8 - 6 * 4, totalLength));
        generateICMPPacket = new GenerateICMPPacket(icmpEchoPacket, DEVICE);

        generateICMPPacket.setVersion(version);
        generateICMPPacket.setHeaderLength(headerLength);
        generateICMPPacket.setPrecedence(typePrecedence);
        generateICMPPacket.setTosD(tosD);
        generateICMPPacket.setTosECN(tosECN);
        generateICMPPacket.setTosR(tosR);
        generateICMPPacket.setTosT(tosT);
        generateICMPPacket.setTotalLength(totalLength);
        generateICMPPacket.setID(id);
        generateICMPPacket.setFlagD(flagD ? 1 : 0);
        generateICMPPacket.setFlagM(flagM ? 1 : 0);
        generateICMPPacket.setFlagX(flagX ? 1 : 0);
        generateICMPPacket.setOffset(offset);
        generateICMPPacket.setTTL(ttl);
        generateICMPPacket.setNeedCalculateTCPCheckSum(icmpNeedCalculateCheckSum);
        generateICMPPacket.setNeedCalculateIPCheckSum(needCalculateCheckSum);
        generateICMPPacket.setCheckSum(checkSum);
        try {
            System.out.println("[SOURCE IP]: " + InetAddress.getByAddress(sourceIP).getHostAddress());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        generateICMPPacket.setSourceAddress(sourceIP);
        generateICMPPacket.setDestinationAddress(destinationIP);

        generateICMPPacket.setICMPCheckSum(icmpChecksum);
        generateICMPPacket.setNeedICMPCalculateCheckSum(icmpNeedCalculateCheckSum);
        generateICMPPacket.setICMPCode(icmpCode);
        generateICMPPacket.setICMPID(icmpID);
        generateICMPPacket.setICMPSeqNumber(icmpSeqNumber);
        generateICMPPacket.setICMPType(icmpType);

        generateICMPPacket.setICMPData(icmpData);
        generateICMPPacket.generate();
        return generateICMPPacket;
    }

    private void addTCPListeners() {
        buttonTCPSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readIpAndTcpParametersAndGeneratePacket().send();
            }
        });

        buttonTCPCansel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonTCPOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.showOpenDialog(null);
                File selectedFile = fileChooser.getSelectedFile();

                BufferedReader bufferedReader = null;
                boolean needIp = false;
                boolean needTcp = false;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile + ".time")));
                    needIp = Boolean.parseBoolean(bufferedReader.readLine());
                    needTcp = Boolean.parseBoolean(bufferedReader.readLine());

                    System.out.println("[NEED IP]: " + needIp);
                    System.out.println("[NEED TCP]: " + needTcp);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(selectedFile);
                    int b;
                    java.util.List<Integer> buffer = new ArrayList<Integer>();
                    while ((b = fis.read()) != -1) {
                        buffer.add(b);
                    }
                    byte[] byteBuffer = new byte[buffer.size()];
                    for (int i = 0; i < buffer.size(); i++) {
                        byteBuffer[i] = buffer.get(i).byteValue();
                    }
                    tcpPacket = new TCPPacket(byteBuffer.length);
                    tcpPacket.setData(byteBuffer);
                    generateTCPPacket = new GenerateTCPPacket(tcpPacket, DEVICE);
                    updateIpAndTcpFields();
                    if (needTcp) {
                        textFieldTCPCheckSum.setText("a");
                    }
                    if (needIp) {
                        textFieldHeaderCheckSum.setText("a");
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


            }
        });

        buttonTCPSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                GenerateTCPPacket generateTCPPacket1 = readIpAndTcpParametersAndGeneratePacket();

                byte[] buffer = new byte[generateTCPPacket1.getTcpPacket().size()];
                generateTCPPacket1.getTcpPacket().getData(buffer);
                String time = String.valueOf(System.currentTimeMillis());
                try {
                    fos = new FileOutputStream(time + ".tcp");
                    fos.write(buffer);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                OutputStreamWriter outputStreamWriter = null;
                try {
                    outputStreamWriter = new OutputStreamWriter(new FileOutputStream(time + ".tcp.time"));
                    outputStreamWriter.write("" + generateTCPPacket1.NEED_CALCULATE_IP_CHECKSUM + "\n");
                    outputStreamWriter.write("" + generateTCPPacket1.TCP_NEED_CALCULATE_CHECK_SUM);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    private void updateIpAndTcpFields() {
        textFieldVersion.setText(String.valueOf(tcpPacket.getIPVersion()));
        textFieldHeaderLenght.setText(String.valueOf(tcpPacket.getIPHeaderLength()));
        textFieldTotalLenght.setText(String.valueOf(tcpPacket.getIPPacketLength()));
        textFieldOffset.setText(String.valueOf(tcpPacket.getFragmentOffset()));
        textFieldID.setText(String.valueOf(tcpPacket.getIdentification()));
        textFieldHeaderCheckSum.setText(String.valueOf(tcpPacket.getIPChecksum()));
        textFieldTTL.setText(String.valueOf(tcpPacket.getTTL()));

        int typeOfService = tcpPacket.getTypeOfService();
        textFieldTypePrecedence.setText(String.valueOf((typeOfService & 0xe0) >> 5));
        textFieldTypeD.setText(String.valueOf((typeOfService & 0x10) >> 4));
        textFieldTypeT.setText(String.valueOf((typeOfService & 0x8) >> 3));
        textFieldTypeR.setText(String.valueOf((typeOfService & 0x4) >> 2));
        textFieldTypeECN.setText(String.valueOf(typeOfService & 0x3));

        int ipFlags = tcpPacket.getIPFlags();

        checkBoxFlagsX.setSelected((ipFlags & 0x4) >> 2 == 1);
        checkBoxFlagsD.setSelected((ipFlags & 0x2) >> 1 == 1);
        checkBoxFlagsM.setSelected((ipFlags & 0x1) >> 1 == 1);

        textFieldTCPHeaderLenght.setText(String.valueOf(tcpPacket.getTCPHeaderLength()));
        textFieldTCPSourcePort.setText(String.valueOf(tcpPacket.getSourcePort()));
        textFieldTCPDestinationPort.setText(String.valueOf(tcpPacket.getDestinationPort()));
        textFieldTCPCheckSum.setText(String.valueOf(tcpPacket.getTCPChecksum()));
        textFieldTCPUrgentPointer.setText(String.valueOf(tcpPacket.getUrgentPointer()));
//        textFieldTCPData.setText(Arrays.toString(tcpPacket.getT));
        textFieldTCPSeq.setText(String.valueOf(tcpPacket.getSequenceNumber()));
        textFieldTCPAck.setText(String.valueOf(tcpPacket.getAckNumber()));
        textFieldTCPWin.setText(String.valueOf(tcpPacket.getWindowSize()));

        int flags = tcpPacket.getControlFlags();
        checkBoxTCPFlagsACK.setSelected((flags & TCPPacket.MASK_ACK) != 0);
        checkBoxTCPFlagsFIN.setSelected((flags & TCPPacket.MASK_FIN) != 0);
        checkBoxTCPFlagsSYN.setSelected((flags & TCPPacket.MASK_SYN) != 0);
        checkBoxTCPFlagsRST.setSelected((flags & TCPPacket.MASK_RST) != 0);
        checkBoxTCPFlagsPSH.setSelected((flags & TCPPacket.MASK_PSH) != 0);
        checkBoxTCPFlagsURG.setSelected((flags & TCPPacket.MASK_URG) != 0);
        checkBoxTCPReservedCWR.setSelected((flags & TCPPacket.MASK_CWR) != 0);
        checkBoxTCPReservedECE.setSelected((flags & TCPPacket.MASK_ECE) != 0);

        int reservedFlags = tcpPacket.getReservedBits4();
        checkBoxTCPReserved1.setSelected((reservedFlags & TCPPacket.MASK_RESERVED_1) != 0);
        checkBoxTCPReserved2.setSelected((reservedFlags & TCPPacket.MASK_RESERVED_2) != 0);
        checkBoxTCPReserved3.setSelected((reservedFlags & TCPPacket.MASK_RESERVED_3) != 0);
        checkBoxTCPReserved4.setSelected((reservedFlags & TCPPacket.MASK_RESERVED_4) != 0);

        try {
            textFieldSourceIP.setText(tcpPacket.getSourceAsInetAddress().getHostAddress());
            textFieldDestinationIP.setText(tcpPacket.getDestinationAsInetAddress().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void updateIpAndUdpFields() {
        textFieldVersion.setText(String.valueOf(udpPacket.getIPVersion()));
        textFieldHeaderLenght.setText(String.valueOf(udpPacket.getIPHeaderLength()));
        textFieldTotalLenght.setText(String.valueOf(udpPacket.getIPPacketLength()));
        textFieldOffset.setText(String.valueOf(udpPacket.getFragmentOffset()));
        textFieldID.setText(String.valueOf(udpPacket.getIdentification()));
        textFieldHeaderCheckSum.setText(String.valueOf(udpPacket.getIPChecksum()));
        textFieldTTL.setText(String.valueOf(udpPacket.getTTL()));

        int typeOfService = udpPacket.getTypeOfService();
        textFieldTypePrecedence.setText(String.valueOf((typeOfService & 0xe0) >> 5));
        textFieldTypeD.setText(String.valueOf((typeOfService & 0x10) >> 4));
        textFieldTypeT.setText(String.valueOf((typeOfService & 0x8) >> 3));
        textFieldTypeR.setText(String.valueOf((typeOfService & 0x4) >> 2));
        textFieldTypeECN.setText(String.valueOf(typeOfService & 0x3));

        int ipFlags = udpPacket.getIPFlags();

        checkBoxFlagsX.setSelected((ipFlags & 0x4) >> 2 == 1);
        checkBoxFlagsD.setSelected((ipFlags & 0x2) >> 1 == 1);
        checkBoxFlagsM.setSelected((ipFlags & 0x1) >> 1 == 1);

        textFieldUDPLenght.setText(String.valueOf(udpPacket.getUDPPacketLength()));
        textFieldUDPSourcePort.setText(String.valueOf(udpPacket.getSourcePort()));
        textFieldUDPDestinationPort.setText(String.valueOf(udpPacket.getDestinationPort()));
        textFieldUDPCheckSum.setText(String.valueOf(udpPacket.getUDPChecksum()));
//        textFieldTCPData.setText(Arrays.toString(udpPacket.getT));

        try {
            textFieldSourceIP.setText(udpPacket.getSourceAsInetAddress().getHostAddress());
            textFieldDestinationIP.setText(udpPacket.getDestinationAsInetAddress().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void updateIpAndIcmpFields() {
        textFieldVersion.setText(String.valueOf(icmpEchoPacket.getIPVersion()));
        textFieldHeaderLenght.setText(String.valueOf(icmpEchoPacket.getIPHeaderLength()));
        textFieldTotalLenght.setText(String.valueOf(icmpEchoPacket.getIPPacketLength()));
        textFieldOffset.setText(String.valueOf(icmpEchoPacket.getFragmentOffset()));
        textFieldID.setText(String.valueOf(icmpEchoPacket.getIdentification()));
        textFieldHeaderCheckSum.setText(String.valueOf(icmpEchoPacket.getIPChecksum()));
        textFieldTTL.setText(String.valueOf(icmpEchoPacket.getTTL()));

        int typeOfService = icmpEchoPacket.getTypeOfService();
        textFieldTypePrecedence.setText(String.valueOf((typeOfService & 0xe0) >> 5));
        textFieldTypeD.setText(String.valueOf((typeOfService & 0x10) >> 4));
        textFieldTypeT.setText(String.valueOf((typeOfService & 0x8) >> 3));
        textFieldTypeR.setText(String.valueOf((typeOfService & 0x4) >> 2));
        textFieldTypeECN.setText(String.valueOf(typeOfService & 0x3));

        int ipFlags = icmpEchoPacket.getIPFlags();

        checkBoxFlagsX.setSelected((ipFlags & 0x4) >> 2 == 1);
        checkBoxFlagsD.setSelected((ipFlags & 0x2) >> 1 == 1);
        checkBoxFlagsM.setSelected((ipFlags & 0x1) >> 1 == 1);

        textFieldICMPSequence.setText(String.valueOf(icmpEchoPacket.getSequenceNumber()));
        textFieldICMPCheckSum.setText(String.valueOf(icmpEchoPacket.getICMPChecksum()));
        textFieldICMPCode.setText(String.valueOf(icmpEchoPacket.getCode()));
        comboBoxICMPType.setSelectedIndex(icmpEchoPacket.getType() == 0 ? 0 : 1);
        textFieldICMPID.setText(String.valueOf(icmpEchoPacket.getIdentifier()));
//        textFieldTCPData.setText(Arrays.toString(icmpEchoPacket.getT));

        try {
            textFieldSourceIP.setText(icmpEchoPacket.getSourceAsInetAddress().getHostAddress());
            textFieldDestinationIP.setText(icmpEchoPacket.getDestinationAsInetAddress().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void addUDPListeners() {
        buttonUDPSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readIpAndUdpParametersAndGeneratePacket().send();
            }
        });
        buttonUDPCansel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonUDPOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.showOpenDialog(null);
                File selectedFile = fileChooser.getSelectedFile();

                BufferedReader bufferedReader = null;
                boolean needIp = false;
                boolean needUdp = false;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile + ".time")));
                    needIp = Boolean.parseBoolean(bufferedReader.readLine());
                    needUdp = Boolean.parseBoolean(bufferedReader.readLine());

                    System.out.println("[NEED IP]: " + needIp);
                    System.out.println("[NEED UDP]: " + needUdp);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(selectedFile);
                    int b;
                    java.util.List<Integer> buffer = new ArrayList<Integer>();
                    while ((b = fis.read()) != -1) {
                        buffer.add(b);
                    }
                    byte[] byteBuffer = new byte[buffer.size()];
                    for (int i = 0; i < buffer.size(); i++) {
                        byteBuffer[i] = buffer.get(i).byteValue();
                    }
                    udpPacket = new UDPPacket(byteBuffer.length);
                    udpPacket.setData(byteBuffer);
                    generateUDPPacket = new GenerateUDPPacket(udpPacket, DEVICE);
                    updateIpAndUdpFields();
                    if (needUdp) {
                        textFieldUDPCheckSum.setText("a");
                    }
                    if (needIp) {
                        textFieldHeaderCheckSum.setText("a");
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


            }
        });

        buttonUDPSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                GenerateUDPPacket generateUDPPacket1 = readIpAndUdpParametersAndGeneratePacket();

                byte[] buffer = new byte[generateUDPPacket1.getUdpPacket().size()];
                generateUDPPacket1.getUdpPacket().getData(buffer);
                String time = String.valueOf(System.currentTimeMillis());
                try {
                    fos = new FileOutputStream(String.valueOf(time) + ".udp");
                    fos.write(buffer);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                OutputStreamWriter outputStreamWriter = null;
                try {
                    outputStreamWriter = new OutputStreamWriter(new FileOutputStream(time + ".udp.time"));
                    outputStreamWriter.write("" + generateUDPPacket1.NEED_CALCULATE_IP_CHECKSUM + "\n");
                    outputStreamWriter.write("" + generateUDPPacket1.UDP_NEED_CALCULATE_CHECK_SUM);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    private void addICMPListeners() {
        buttonICMPCansel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonICMPSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readIpAndIcmpParametersAndGeneratePacket().send();
            }
        });
        buttonICMPOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.showOpenDialog(null);
                File selectedFile = fileChooser.getSelectedFile();

                BufferedReader bufferedReader = null;
                boolean needIp = false;
                boolean needIcmp = false;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile + ".time")));
                    needIp = Boolean.parseBoolean(bufferedReader.readLine());
                    needIcmp = Boolean.parseBoolean(bufferedReader.readLine());

                    System.out.println("[NEED IP]: " + needIp);
                    System.out.println("[NEED ICMP]: " + needIcmp);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(selectedFile);
                    int b;
                    java.util.List<Integer> buffer = new ArrayList<Integer>();
                    while ((b = fis.read()) != -1) {
                        buffer.add(b);
                    }
                    byte[] byteBuffer = new byte[buffer.size()];
                    for (int i = 0; i < buffer.size(); i++) {
                        byteBuffer[i] = buffer.get(i).byteValue();
                    }
                    icmpEchoPacket = new ICMPEchoPacket(byteBuffer.length);
                    icmpEchoPacket.setData(byteBuffer);
                    generateICMPPacket = new GenerateICMPPacket(icmpEchoPacket, DEVICE);
                    updateIpAndIcmpFields();
                    if (needIcmp) {
                        textFieldICMPCheckSum.setText("a");
                    }
                    if (needIp) {
                        textFieldHeaderCheckSum.setText("a");
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


            }
        });

        buttonICMPSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                GenerateICMPPacket generateICMPPacket1 = readIpAndIcmpParametersAndGeneratePacket();

                byte[] buffer = new byte[generateICMPPacket1.getIcmpEchoPacket().size()];
                generateICMPPacket1.getIcmpEchoPacket().getData(buffer);
                String time = String.valueOf(System.currentTimeMillis());
                try {
                    fos = new FileOutputStream(time + ".icmp");
                    fos.write(buffer);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                OutputStreamWriter outputStreamWriter = null;
                try {
                    outputStreamWriter = new OutputStreamWriter(new FileOutputStream(time + ".icmp.time"));
                    outputStreamWriter.write("" + generateICMPPacket1.NEED_CALCULATE_IP_CHECKSUM + "\n");
                    outputStreamWriter.write("" + generateICMPPacket1.ICMP_NEED_CALCULATE_CHECK_SUM);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panelMain = new JPanel();
        panelMain.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(15, 15, 15, 15), -1, -1));
        tabbedPaneTypes = new JTabbedPane();
        panelMain.add(tabbedPaneTypes, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        panelTCP = new JPanel();
        panelTCP.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneTypes.addTab("TCP", panelTCP);
        panelTCPBottom = new JPanel();
        panelTCPBottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelTCP.add(panelTCPBottom, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelTCPData = new JLabel();
        labelTCPData.setText("Data");
        panelTCPBottom.add(labelTCPData, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPBottom.add(textFieldTCPData, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelTCPTop = new JPanel();
        panelTCPTop.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelTCP.add(panelTCPTop, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTCPLeft = new JPanel();
        panelTCPLeft.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelTCPTop.add(panelTCPLeft, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTCPHeaderLenght = new JLabel();
        panelTCPHeaderLenght.setText("Header Lenght");
        panelTCPLeft.add(panelTCPHeaderLenght, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPLeft.add(textFieldTCPHeaderLenght, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTCPSourcePort = new JLabel();
        labelTCPSourcePort.setText("Source Port");
        panelTCPLeft.add(labelTCPSourcePort, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPLeft.add(textFieldTCPSourcePort, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelDestinationPort = new JLabel();
        labelDestinationPort.setText("Destination Port");
        panelTCPLeft.add(labelDestinationPort, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPLeft.add(textFieldTCPDestinationPort, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTCPCheckSum = new JLabel();
        labelTCPCheckSum.setText("CheckSum");
        panelTCPLeft.add(labelTCPCheckSum, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPLeft.add(textFieldTCPCheckSum, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTCPUrgentPointer = new JLabel();
        labelTCPUrgentPointer.setText("Urgent Pointer");
        panelTCPLeft.add(labelTCPUrgentPointer, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPLeft.add(textFieldTCPUrgentPointer, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelTCPRigth = new JPanel();
        panelTCPRigth.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTCPTop.add(panelTCPRigth, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTCPRightTop = new JPanel();
        panelTCPRightTop.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelTCPRigth.add(panelTCPRightTop, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelTCPFlags = new JLabel();
        labelTCPFlags.setText("Flags");
        panelTCPRightTop.add(labelTCPFlags, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPFlagsSYN = new JCheckBox();
        checkBoxTCPFlagsSYN.setText("SYN");
        panelTCPRightTop.add(checkBoxTCPFlagsSYN, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPFlagsACK = new JCheckBox();
        checkBoxTCPFlagsACK.setText("ACK");
        panelTCPRightTop.add(checkBoxTCPFlagsACK, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPFlagsFIN = new JCheckBox();
        checkBoxTCPFlagsFIN.setText("FIN");
        panelTCPRightTop.add(checkBoxTCPFlagsFIN, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPFlagsRST = new JCheckBox();
        checkBoxTCPFlagsRST.setText("RST");
        panelTCPRightTop.add(checkBoxTCPFlagsRST, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPFlagsPSH = new JCheckBox();
        checkBoxTCPFlagsPSH.setText("PUSH");
        panelTCPRightTop.add(checkBoxTCPFlagsPSH, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPFlagsURG = new JCheckBox();
        checkBoxTCPFlagsURG.setText("URG");
        panelTCPRightTop.add(checkBoxTCPFlagsURG, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPRigthBottom = new JPanel();
        panelTCPRigthBottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelTCPRigth.add(panelTCPRigthBottom, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelTCPSeq = new JLabel();
        labelTCPSeq.setText("SEQ");
        panelTCPRigthBottom.add(labelTCPSeq, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelTCPAck = new JLabel();
        labelTCPAck.setText("ACK");
        panelTCPRigthBottom.add(labelTCPAck, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPRigthBottom.add(textFieldTCPAck, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTCPWin = new JLabel();
        labelTCPWin.setText("WIN");
        panelTCPRigthBottom.add(labelTCPWin, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPRigthBottom.add(textFieldTCPWin, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelTCPRigthBottom.add(textFieldTCPSeq, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelTCPRigth.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelTCPReserved = new JLabel();
        labelTCPReserved.setHorizontalAlignment(0);
        labelTCPReserved.setHorizontalTextPosition(0);
        labelTCPReserved.setText("Reserved");
        panel1.add(labelTCPReserved, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPReserved1 = new JCheckBox();
        checkBoxTCPReserved1.setText("Reserved 1");
        panel1.add(checkBoxTCPReserved1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPReserved2 = new JCheckBox();
        checkBoxTCPReserved2.setText("Reserved 2");
        panel1.add(checkBoxTCPReserved2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPReserved3 = new JCheckBox();
        checkBoxTCPReserved3.setText("Reserved 3");
        panel1.add(checkBoxTCPReserved3, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPReserved4 = new JCheckBox();
        checkBoxTCPReserved4.setText("Reserved 4");
        panel1.add(checkBoxTCPReserved4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPReservedCWR = new JCheckBox();
        checkBoxTCPReservedCWR.setText("CWR");
        panel1.add(checkBoxTCPReservedCWR, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxTCPReservedECE = new JCheckBox();
        checkBoxTCPReservedECE.setText("ECE");
        panel1.add(checkBoxTCPReservedECE, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTCPButtons = new JPanel();
        panelTCPButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelTCP.add(panelTCPButtons, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonTCPSave = new JButton();
        buttonTCPSave.setText("Safe");
        panelTCPButtons.add(buttonTCPSave, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTCPSend = new JButton();
        buttonTCPSend.setText("Send");
        panelTCPButtons.add(buttonTCPSend, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTCPCansel = new JButton();
        buttonTCPCansel.setText("Cansel");
        panelTCPButtons.add(buttonTCPCansel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTCPOpen = new JButton();
        buttonTCPOpen.setText("Open");
        panelTCPButtons.add(buttonTCPOpen, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelUDP = new JPanel();
        panelUDP.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneTypes.addTab("UDP", panelUDP);
        panelUDPBottom = new JPanel();
        panelUDPBottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelUDP.add(panelUDPBottom, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelUDPData = new JLabel();
        labelUDPData.setText("Data");
        panelUDPBottom.add(labelUDPData, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelUDPBottom.add(textFieldUDPData, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelUDPTop = new JPanel();
        panelUDPTop.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelUDP.add(panelUDPTop, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelUDPSourcePort = new JLabel();
        labelUDPSourcePort.setText("Source Port");
        panelUDPTop.add(labelUDPSourcePort, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelUDPDestinationPort = new JLabel();
        labelUDPDestinationPort.setText("Destination Port");
        panelUDPTop.add(labelUDPDestinationPort, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelUDPHeaderLenght = new JLabel();
        labelUDPHeaderLenght.setText("Header Lenght");
        panelUDPTop.add(labelUDPHeaderLenght, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelUDPTop.add(textFieldUDPSourcePort, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelUDPTop.add(textFieldUDPDestinationPort, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelUDPTop.add(textFieldUDPLenght, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelUDPCheckSum = new JLabel();
        labelUDPCheckSum.setText("CheckSum");
        panelUDPTop.add(labelUDPCheckSum, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelUDPTop.add(textFieldUDPCheckSum, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelUDPButtons = new JPanel();
        panelUDPButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelUDP.add(panelUDPButtons, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonUDPSave = new JButton();
        buttonUDPSave.setText("Safe");
        panelUDPButtons.add(buttonUDPSave, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonUDPCansel = new JButton();
        buttonUDPCansel.setText("Cansel");
        panelUDPButtons.add(buttonUDPCansel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonUDPSend = new JButton();
        buttonUDPSend.setText("Send");
        panelUDPButtons.add(buttonUDPSend, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonUDPOpen = new JButton();
        buttonUDPOpen.setText("Open");
        panelUDPButtons.add(buttonUDPOpen, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelICMP = new JPanel();
        panelICMP.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneTypes.addTab("ICMP", panelICMP);
        panelICMPTop = new JPanel();
        panelICMPTop.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelICMP.add(panelICMPTop, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelUICMPType = new JLabel();
        labelUICMPType.setText("Type");
        panelICMPTop.add(labelUICMPType, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelICMPTop.add(comboBoxICMPType, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelICMPCode = new JLabel();
        labelICMPCode.setText("Code");
        panelICMPTop.add(labelICMPCode, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelICMPTop.add(textFieldICMPCode, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelICMPCheckSum = new JLabel();
        labelICMPCheckSum.setText("CheckSum");
        panelICMPTop.add(labelICMPCheckSum, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelICMPTop.add(textFieldICMPCheckSum, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelICMPID = new JLabel();
        labelICMPID.setText("ID");
        panelICMPTop.add(labelICMPID, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelICMPTop.add(textFieldICMPID, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelICMPSequence = new JLabel();
        labelICMPSequence.setText("Sequence");
        panelICMPTop.add(labelICMPSequence, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelICMPTop.add(textFieldICMPSequence, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelICMPBottom = new JPanel();
        panelICMPBottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelICMP.add(panelICMPBottom, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelICMPData = new JLabel();
        labelICMPData.setText("Data");
        panelICMPBottom.add(labelICMPData, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelICMPBottom.add(textFieldICMPData, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelICMPButtons = new JPanel();
        panelICMPButtons.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelICMP.add(panelICMPButtons, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonICMPSave = new JButton();
        buttonICMPSave.setText("Safe");
        panelICMPButtons.add(buttonICMPSave, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonICMPCansel = new JButton();
        buttonICMPCansel.setText("Cansel");
        panelICMPButtons.add(buttonICMPCansel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonICMPSend = new JButton();
        buttonICMPSend.setText("Send");
        panelICMPButtons.add(buttonICMPSend, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonICMPOpen = new JButton();
        buttonICMPOpen.setText("Open");
        panelICMPButtons.add(buttonICMPOpen, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainLeft = new JPanel();
        panelMainLeft.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelMainLeft, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelVersion = new JLabel();
        labelVersion.setText("Version");
        panelMainLeft.add(labelVersion, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainLeft.add(textFieldVersion, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelHeaderLenght = new JLabel();
        labelHeaderLenght.setText("Header lenght");
        panelMainLeft.add(labelHeaderLenght, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainLeft.add(textFieldHeaderLenght, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTotalLenght = new JLabel();
        labelTotalLenght.setText("Total lenght");
        panelMainLeft.add(labelTotalLenght, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainLeft.add(textFieldTotalLenght, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelMainRight = new JPanel();
        panelMainRight.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelMainRight, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelOffset = new JLabel();
        labelOffset.setText("Offset");
        panelMainRight.add(labelOffset, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainRight.add(textFieldOffset, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelHeaderCheckSum = new JLabel();
        labelHeaderCheckSum.setText("Header CheckSum");
        panelMainRight.add(labelHeaderCheckSum, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainRight.add(textFieldHeaderCheckSum, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelID = new JLabel();
        labelID.setText("ID");
        panelMainRight.add(labelID, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainRight.add(textFieldID, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTTL = new JLabel();
        labelTTL.setText("TTL");
        panelMainRight.add(labelTTL, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMainRight.add(textFieldTTL, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelTypeOfService = new JPanel();
        panelTypeOfService.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelTypeOfService, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTypeOfService.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        panelTypeTop = new JPanel();
        panelTypeTop.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelTypeOfService.add(panelTypeTop, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelTypePrecedence = new JLabel();
        labelTypePrecedence.setText("Precedence");
        panelTypeTop.add(labelTypePrecedence, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTypeTop.add(textFieldTypePrecedence, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelTypeBottom = new JPanel();
        panelTypeBottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelTypeOfService.add(panelTypeBottom, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelTypeD = new JLabel();
        labelTypeD.setText("D");
        panelTypeBottom.add(labelTypeD, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTypeBottom.add(textFieldTypeD, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTypeECN = new JLabel();
        labelTypeECN.setText("ECN");
        panelTypeBottom.add(labelTypeECN, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTypeBottom.add(textFieldTypeECN, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTypeT = new JLabel();
        labelTypeT.setText("T");
        panelTypeBottom.add(labelTypeT, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTypeBottom.add(textFieldTypeT, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelTypeR = new JLabel();
        labelTypeR.setText("R");
        panelTypeBottom.add(labelTypeR, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelTypeBottom.add(textFieldTypeR, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lavelTypeOfService = new JLabel();
        lavelTypeOfService.setText("Type of Service");
        panelTypeOfService.add(lavelTypeOfService, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelFlags = new JPanel();
        panelFlags.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelFlags, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelFlags.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        panelFlagsTop = new JPanel();
        panelFlagsTop.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelFlags.add(panelFlagsTop, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Flags");
        panelFlagsTop.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelFlagsBottom = new JPanel();
        panelFlagsBottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelFlags.add(panelFlagsBottom, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxFlagsX = new JCheckBox();
        checkBoxFlagsX.setText("X (reserved)");
        panelFlagsBottom.add(checkBoxFlagsX, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxFlagsD = new JCheckBox();
        checkBoxFlagsD.setText("D");
        panelFlagsBottom.add(checkBoxFlagsD, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxFlagsM = new JCheckBox();
        checkBoxFlagsM.setText("M");
        panelFlagsBottom.add(checkBoxFlagsM, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        paneMainBottom = new JPanel();
        paneMainBottom.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(paneMainBottom, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        paneMainBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        paneBottomLeft = new JPanel();
        paneBottomLeft.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        paneMainBottom.add(paneBottomLeft, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(154, 119), null, 0, false));
        labelSourceIP = new JLabel();
        labelSourceIP.setText("Source IP");
        paneBottomLeft.add(labelSourceIP, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        paneBottomLeft.add(textFieldSourceIP, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        paneBottomRight = new JPanel();
        paneBottomRight.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        paneMainBottom.add(paneBottomRight, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(486, 119), null, 0, false));
        labelDestinationIP = new JLabel();
        labelDestinationIP.setText("Destination IP");
        paneBottomRight.add(labelDestinationIP, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        paneBottomRight.add(textFieldDestinationIP, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }
}
