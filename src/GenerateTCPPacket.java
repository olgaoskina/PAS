import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.savarese.vserv.tcpip.IPPacket;
import org.savarese.vserv.tcpip.TCPPacket;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga
 * 24 December 2014.
 */
public class GenerateTCPPacket extends GenerateIPPacket implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int TCP_HEADER_LENGTH = 0;
    protected int TCP_SOURCE_PORT = 0;
    protected int TCP_DESTINATION_PORT = 0;
    protected int TCP_CHECKSUM = 0;
    protected byte[] TCP_DATA;
    protected long TCP_ACK_NUMBER = 0;
    protected long TCP_SEQ_NUMBER = 0;
    protected int TCP_WIN_SIZE = 0;
    protected boolean TCP_ACK = false;
    protected boolean TCP_FIN = false;
    protected boolean TCP_PSH = false;
    protected boolean TCP_RST = false;
    protected boolean TCP_SYN = false;
    protected boolean TCP_URG = false;
    protected boolean TCP_NEED_CALCULATE_CHECK_SUM = false;
    protected int TCP_URGENT_POINTER = 0;
    protected boolean RESERVED_1 = false;
    protected boolean RESERVED_2 = false;
    protected boolean RESERVED_3 = false;
    protected boolean RESERVED_4 = false;
    protected boolean RESERVED_CWR = false;
    protected boolean RESERVED_ECE = false;

    public TCPPacket getTcpPacket() {
        return tcpPacket;
    }
    public void setTcpPacket(byte[] buffer) {
        tcpPacket = new TCPPacket(buffer.length);
        tcpPacket.setData(buffer);
    }
    private TCPPacket tcpPacket;

    public GenerateTCPPacket(TCPPacket pack, String device) {
        super(pack);
        SELECTED_DEVICE = device;

    }

    @Override
    public void send() {
        byte[] bufferWithEthernet = getBufferWithEthernet();
        JPacket packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);


        if (NEED_CALCULATE_IP_CHECKSUM) {
            Pattern pattern = Pattern.compile("Ip:.*?checksum = [0-9A-Zx]+ \\([0-9]+\\) \\[incorrect: [0-9A-Zx]+");
            Matcher matcher = pattern.matcher(packet.toString());
            if (matcher.find()) {
                String newIPCheckSum = matcher.group().replaceFirst("^.*?incorrect: 0x", "");
                System.out.println("[FIND IP CHECKSUM]: " + newIPCheckSum);

                tcpPacket.setIPCheckSum(Integer.parseInt(newIPCheckSum, 16));
                bufferWithEthernet = getBufferWithEthernet();
                packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);
            }
        }

        if (TCP_NEED_CALCULATE_CHECK_SUM) {
            Pattern pattern = Pattern.compile("Tcp:.*?checksum = [0-9A-Zx]+ \\([0-9]+\\) \\[incorrect: [0-9A-Zx]+");
            Matcher matcher = pattern.matcher(packet.toString());
            if (matcher.find()) {
                String newIPCheckSum = matcher.group().replaceFirst("^.*?incorrect: 0x", "");
                System.out.println("[FIND TCP CHECKSUM]: " + newIPCheckSum);

                tcpPacket.setTCPCheckSum(Integer.parseInt(newIPCheckSum, 16));
                bufferWithEthernet = getBufferWithEthernet();
                packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);
            }
        }


        Ip4 ip4 = packet.getHeader(new Ip4());
        Tcp tcp = packet.getHeader(new Tcp());
        packet.scan(Ethernet.ID);

        System.out.println(pcap.sendPacket(ByteBuffer.wrap(packet.getByteArray(0, packet.size()))));
        System.out.println(packet);
    }


    private void computeMacAddresses() {
        try {
            byte[] macAddress = GetMacAddressByIpAddress.getMacAddress(InetAddress.getByAddress(SOURCE_ADDRESS));
            if (macAddress != null) {
                SOURCE_MAC_ADDRESS = macAddress;
            }
            macAddress = GetMacAddressByIpAddress.getMacAddress(InetAddress.getByAddress(DESTINATION_ADDRESS));
            if (macAddress != null) {
                DESTINATION_MAC_ADDRESS = macAddress;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private byte[] getBufferWithEthernet() {
        computeMacAddresses();

        byte[] buffer = new byte[tcpPacket.size()];
        byte[] bufferWithEthernet = new byte[tcpPacket.size() + 14];
        tcpPacket.getData(buffer);

        System.arraycopy(DESTINATION_MAC_ADDRESS, 0, bufferWithEthernet, 0, DESTINATION_MAC_ADDRESS.length);
        System.arraycopy(SOURCE_MAC_ADDRESS, 0, bufferWithEthernet, DESTINATION_MAC_ADDRESS.length, SOURCE_MAC_ADDRESS.length);

        bufferWithEthernet[2 * SOURCE_MAC_ADDRESS.length] = 0x08;
        bufferWithEthernet[2 * SOURCE_MAC_ADDRESS.length + 1] = 0x00;

        System.arraycopy(buffer, 0, bufferWithEthernet, 2 * SOURCE_MAC_ADDRESS.length + 2, buffer.length);

        System.out.println("[BUFFER WITH ETHERNET]: " + Arrays.toString(bufferWithEthernet));
        System.out.println("[BUFFER]: " + Arrays.toString(buffer));
        return bufferWithEthernet;
    }

    public void generate() {
        super.generate();
        tcpPacket = (TCPPacket) pack;

        tcpPacket.setProtocol(IPPacket.PROTOCOL_TCP);

//        tcpPacket.setTCPDataByteLength(TCP_DATA.length);

        tcpPacket.setTCPHeaderLength(TCP_HEADER_LENGTH);

        tcpPacket.setDestinationPort(TCP_DESTINATION_PORT);

        tcpPacket.setSourcePort(TCP_SOURCE_PORT);

        tcpPacket.setAckNumber(TCP_ACK_NUMBER);

        tcpPacket.setSequenceNumber(TCP_SEQ_NUMBER);

        tcpPacket.setWindowSize(TCP_WIN_SIZE);

        tcpPacket.setUrgentPointer(TCP_URGENT_POINTER);

        if (TCP_NEED_CALCULATE_CHECK_SUM) {
            tcpPacket.computeTCPChecksum(true);
        } else {
            tcpPacket.setTCPCheckSum(TCP_CHECKSUM);
        }


        byte[] buffer = new byte[tcpPacket.size()];
        tcpPacket.getData(buffer);
        System.out.println("[TCP PACKET 1]: " + Arrays.toString(buffer));

        System.arraycopy(TCP_DATA, 0, buffer, 4 * HEADER_LENGTH + 4 * TCP_HEADER_LENGTH, TCP_DATA.length);
        tcpPacket.setData(buffer);

        int flags = 0;
        if (TCP_ACK) {
            flags |= TCPPacket.MASK_ACK;
        }
        if (TCP_FIN) {
            flags |= TCPPacket.MASK_FIN;
        }
        if (TCP_PSH) {
            flags |= TCPPacket.MASK_PSH;
        }
        if (TCP_RST) {
            flags |= TCPPacket.MASK_RST;
        }
        if (TCP_SYN) {
            flags |= TCPPacket.MASK_SYN;
        }
        if (TCP_URG) {
            flags |= TCPPacket.MASK_URG;
        }
        tcpPacket.setControlFlags(flags);

        if (RESERVED_CWR) {
            tcpPacket.addControlFlags(TCPPacket.MASK_CWR);
        }
        if (RESERVED_ECE) {
            tcpPacket.addControlFlags(TCPPacket.MASK_ECE);
        }

        int reservedBits = 0;
        if (RESERVED_1) {
            reservedBits |= TCPPacket.MASK_RESERVED_1;
        }
        if (RESERVED_2) {
            reservedBits |= TCPPacket.MASK_RESERVED_2;
        }
        if (RESERVED_3) {
            reservedBits |= TCPPacket.MASK_RESERVED_3;
        }
        if (RESERVED_4) {
            reservedBits |= TCPPacket.MASK_RESERVED_4;
        }
        tcpPacket.setReservedBits4(reservedBits);

        byte[] array = new byte[tcpPacket.size()];
        tcpPacket.getData(array);
        System.out.println("[TCP PACKET]: " + Arrays.toString(array));
    }

    public void setTCPHeaderLength(int headerLength) {
        TCP_HEADER_LENGTH = headerLength;
        System.out.println("[SET TCP HEADER LENGTH]:" + TCP_HEADER_LENGTH);
    }

    public void setTCPSourcePort(int sourcePort) {
        TCP_SOURCE_PORT = sourcePort;
        System.out.println("[SET TCP SOURCE PORT]:" + TCP_SOURCE_PORT);
    }

    public void setTCPDestinationPort(int TCP_DESTINATION_PORT) {
        this.TCP_DESTINATION_PORT = TCP_DESTINATION_PORT;
    }

    public void setTCPCheckSum(int TCP_CHECKSUM) {
        this.TCP_CHECKSUM = TCP_CHECKSUM;
    }

    public void setTCPData(byte[] TCP_DATA) {
        this.TCP_DATA = TCP_DATA;
    }

    public void setTCPAckNumber(long TCP_ACK_NUMBER) {
        this.TCP_ACK_NUMBER = TCP_ACK_NUMBER;
    }

    public void setTCPSeqNumber(long TCP_SEQ_NUMBER) {
        this.TCP_SEQ_NUMBER = TCP_SEQ_NUMBER;
    }

    public void setTCPWinSize(int TCP_WIN_SIZE) {
        this.TCP_WIN_SIZE = TCP_WIN_SIZE;
    }

    public void setTCPAck(boolean TCP_ACK) {
        this.TCP_ACK = TCP_ACK;
    }

    public void setTCPFin(boolean TCP_FIN) {
        this.TCP_FIN = TCP_FIN;
    }

    public void setTCPPsh(boolean TCP_PSH) {
        this.TCP_PSH = TCP_PSH;
    }

    public void setTCPRst(boolean TCP_RST) {
        this.TCP_RST = TCP_RST;
    }

    public void setTCPSyn(boolean TCP_SYN) {
        this.TCP_SYN = TCP_SYN;
    }

    public void setTCPUrg(boolean TCP_URG) {
        this.TCP_URG = TCP_URG;
    }

    public void setTCPCalculateCheckSum(boolean needCalculate) {
        this.TCP_NEED_CALCULATE_CHECK_SUM = needCalculate;
    }

    public void setTCPUrgentPointer(int urgentPointer) {
        this.TCP_URGENT_POINTER = urgentPointer;
    }

    public void setTCPReserved1(boolean flag) {
        RESERVED_1 = flag;
        System.out.println("[SET TCP RESERVED 1]: " + flag);
    }
    public void setTCPReserved2(boolean flag) {
        RESERVED_2 = flag;
        System.out.println("[SET TCP RESERVED 2]: " + flag);
    }
    public void setTCPReserved3(boolean flag) {
        RESERVED_3 = flag;
        System.out.println("[SET TCP RESERVED 3]: " + flag);
    }
    public void setTCPReserved4(boolean flag) {
        RESERVED_4 = flag;
        System.out.println("[SET TCP RESERVED 4]: " + flag);
    }
    public void setTCPReservedCWR(boolean flag) {
        RESERVED_CWR = flag;
        System.out.println("[SET TCP RESERVED CWR]: " + flag);
    }
    public void setTCPReservedECE(boolean flag) {
        RESERVED_ECE = flag;
        System.out.println("[SET TCP RESERVED ECE]: " + flag);
    }
}
