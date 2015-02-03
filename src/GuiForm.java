import org.savarese.vserv.tcpip.ICMPEchoPacket;
import org.savarese.vserv.tcpip.TCPPacket;
import org.savarese.vserv.tcpip.UDPPacket;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
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
//        READ
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

//        WRITE
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
//        READ
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

//        WRITE
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
//        READ
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

//        WRITE
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
        buttonTCPSend.addActionListener(e -> readIpAndTcpParametersAndGeneratePacket().send());

        buttonTCPCansel.addActionListener(e -> System.exit(0));

        buttonTCPOpen.addActionListener(e -> {
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
        });

        buttonTCPSave.addActionListener(e -> {
            FileOutputStream fos = null;
            GenerateTCPPacket generateTCPPacket1 = readIpAndTcpParametersAndGeneratePacket();

            byte[] buffer = new byte[generateTCPPacket1.getTcpPacket().size()];
            generateTCPPacket1.getTcpPacket().getData(buffer);
            String time = String.valueOf(System.currentTimeMillis());
            try {
                fos = new FileOutputStream(time + ".tcp");
                fos.write(buffer);
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
        buttonUDPSend.addActionListener(e -> readIpAndUdpParametersAndGeneratePacket().send());
        buttonUDPCansel.addActionListener(e -> System.exit(0));
        buttonUDPOpen.addActionListener(e -> {
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
        });

        buttonUDPSave.addActionListener(e -> {
            FileOutputStream fos = null;
            GenerateUDPPacket generateUDPPacket1 = readIpAndUdpParametersAndGeneratePacket();

            byte[] buffer = new byte[generateUDPPacket1.getUdpPacket().size()];
            generateUDPPacket1.getUdpPacket().getData(buffer);
            String time = String.valueOf(System.currentTimeMillis());
            try {
                fos = new FileOutputStream(String.valueOf(time) + ".udp");
                fos.write(buffer);
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
        });
    }

    private void addICMPListeners() {
        buttonICMPCansel.addActionListener(e -> System.exit(0));
        buttonICMPSend.addActionListener(e -> readIpAndIcmpParametersAndGeneratePacket().send());
        buttonICMPOpen.addActionListener(e -> {
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
        });

        buttonICMPSave.addActionListener(e -> {
            FileOutputStream fos = null;
            GenerateICMPPacket generateICMPPacket1 = readIpAndIcmpParametersAndGeneratePacket();

            byte[] buffer = new byte[generateICMPPacket1.getIcmpEchoPacket().size()];
            generateICMPPacket1.getIcmpEchoPacket().getData(buffer);
            String time = String.valueOf(System.currentTimeMillis());
            try {
                fos = new FileOutputStream(time + ".icmp");
                fos.write(buffer);
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
        });
    }

}
