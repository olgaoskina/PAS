import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.*;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Created by Olga on 11.12.14.
 */
public class GenerateIPPacket {
    private byte TOS_PRECEDENCE = 0;
    private byte TOS_D = 0;
    private byte TOS_T = 0;
    private byte TOS_R = 0;
    private byte TOS_ECN = 0;
    private byte VERSION = 0;
    private byte HEADER_LENGTH = 0;
    private int TOTAL_LENGTH = 0;
    private int ID = 0;
    private int packetSize = 200;
    private static byte[] MAC_ADDRESS;
    private static String SELECTED_DEVICE;
    private byte FLAG_X = 0;
    private byte FLAG_D = 0;
    private byte FLAG_M = 0;
    private int OFFSET = 0;
    private int TTL = 0;
    private int CHECK_SUM = 0;
    private byte[] SOURCE_ADDRESS;
    private byte[] DESTINATION_ADDRESS;
    private boolean CALCULATE_CHECKSUM = false;

    private int SELECTED_TYPE = Tcp.ID;

    private int TCP_HEADER_LENGTH = 0;
    private int TCP_SOURCE_PORT = 0;
    private int TCP_DESTINATION_PORT = 0;
    private int TCP_CHECKSUM = 0;

    private int UDP_SOURCE_PORT = 0;
    private int UDP_DESTINATION_PORT = 0;
    private int UDP_HEADER_LENGTH = 0;
    private byte[] UDP_DATA;

    private int ICMP_TYPE = 0; // Echo request
    private int ICMP_ID = 0;

    private Ip4 ip4;

    private Pcap pcap;


    public GenerateIPPacket() {
        pcap = Pcap.openLive(SELECTED_DEVICE, Pcap.DEFAULT_SNAPLEN, Pcap.MODE_PROMISCUOUS, Pcap.DEFAULT_TIMEOUT, new StringBuilder());

    }

    public void send() {
        JMemoryPacket packet = new JMemoryPacket(packetSize);
        packet.order(ByteOrder.BIG_ENDIAN);
        packet.setUByte(12, 0x0800);
        packet.scan(JProtocol.ETHERNET_ID);

        Ethernet ethernet = packet.getHeader(new Ethernet());
        ethernet.source(MAC_ADDRESS);
        MAC_ADDRESS[MAC_ADDRESS.length - 1] = 0;
        ethernet.destination(MAC_ADDRESS);

        ethernet.type(0x05);
//        ethernet.checksum(ethernet.calculateChecksum());

        System.out.println(packet);

        packet.setUByte(14, 0x40 | 0x05);
        packet.scan(JProtocol.IP4_ID);
        ip4 = packet.getHeader(new Ip4());
        ip4.version(VERSION);
//        ip4.length(TOTAL_LENGTH);
//        ip4.hlen(HEADER_LENGTH);

//        int b;
//        b = (TOS_PRECEDENCE << 6);
//        b |= (TOS_D << 5);
//        b |= (TOS_T << 4);
//        b |= (TOS_R << 3);
//        b |= TOS_ECN;
//
//        ip4.tos(b);
//        System.out.println("[TOS]: " + BigInteger.valueOf(ip4.tos()).toString(2));
//
//        ip4.flags(FLAG_X << 2 | FLAG_D << 1 | FLAG_M);
//        ip4.id(ID);
//
//        ip4.offset(OFFSET);
//        ip4.ttl(TTL);
//        ip4.source(SOURCE_ADDRESS);
//        ip4.destination(DESTINATION_ADDRESS);
//
//
//        if (SELECTED_TYPE == Udp.ID) {
//            ip4.type(0x11);
//            packet.setUByte(46, 0x50);
//            packet.scan(JProtocol.UDP_ID);
//
//            Udp udp = packet.getHeader(new Udp());
//            udp.source(UDP_SOURCE_PORT);
//            udp.destination(UDP_DESTINATION_PORT);
//            udp.length(UDP_HEADER_LENGTH);
//            ip4.setByteArray(TOTAL_LENGTH, udp.getByteArray(0, udp.size()));
//
//            if (CALCULATE_CHECKSUM) {
//                ip4.checksum(ip4.calculateChecksum());
//            } else {
//                ip4.checksum(CHECK_SUM);
//            }
//
//            System.out.println(pcap.sendPacket(ByteBuffer.wrap(packet.getByteArray(0, packet.size()))));
//            System.out.println(packet);
//        }
//
//        if (SELECTED_TYPE == Icmp.ID && ICMP_TYPE == 1) {
//            ip4.type(JProtocol.ICMP_ID);
////            packet.setUByte(46, 160);
//            packet.scan(JProtocol.ICMP_ID);
//            Icmp.EchoReply echoReply = packet.getHeader(new Icmp.EchoReply());
//
//
//
//            if (CALCULATE_CHECKSUM) {
//                ip4.checksum(ip4.calculateChecksum());
//            } else {
//                ip4.checksum(CHECK_SUM);
//            }
//
//
//            System.out.println(packet);
//        }
//
//
        if (CALCULATE_CHECKSUM) {
            ip4.checksum(ip4.calculateChecksum());
        } else {
            ip4.checksum(CHECK_SUM);
        }

        System.out.println(pcap.sendPacket(ByteBuffer.wrap(packet.getByteArray(0, packet.size()))));
        System.out.println(packet);

    }


    public void setPrecedence(byte precedence) {
        if (less8(precedence)) {
            this.TOS_PRECEDENCE = precedence;
            System.out.println("[SET PRECEDENCE]: " + precedence);
        }
    }

    public void setTosD(byte D) {
        if (less2(D)) {
            TOS_D = D;
        }
    }

    public void setTosT(byte T) {
        if (less2(T)) {
            TOS_T = T;
        }
    }

    public void setTosR(byte R) {
        if (less2(R)) {
            TOS_R = R;
        }
    }

    public void setTosECN(byte ECN) {
        if (less4(ECN)) {
            TOS_ECN = ECN;
        }
    }

    public void setVersion(byte version) {
        if (version == 4 || version == 6) {
            VERSION |= version;
            System.out.println("[SET VERSION]: " + BigInteger.valueOf(VERSION).toString(2));
        }
    }

    public void setHeaderLength(byte length) {
        if (less16(length)) {
            HEADER_LENGTH |= length;
            System.out.println("[SET HEADER LENGTH]: " + BigInteger.valueOf(HEADER_LENGTH).toString(2));
        }
    }

    public void setTotalLength(int totalLength) {
        if (less65536(totalLength)) {
            TOTAL_LENGTH = totalLength;
            System.out.println("[SET TOTAL LENGTH]: " + BigInteger.valueOf(TOTAL_LENGTH).toString(2));
        }
    }

    public void setID(int id) {
        if (less65536(id)) {
            ID = id;
            System.out.println("[SET ID]: " + ID);
        }
    }

    public void setOffset(int offset) {
        if (less65536(offset)) {
            OFFSET = offset;
            System.out.println("[SET OFFSET]: " + OFFSET);
        }
    }

    public void setTTL(int ttl) {
        if (less256(ttl)) {
            TTL = ttl;
            System.out.println("[SET TTL]: " + TTL);
        }
    }

    private boolean less2(byte number) {
        return (number < 2 && number >= 0);
    }

    private boolean less8(byte number) {
        return (number < 8 && number >= 0);
    }

    private boolean less4(byte number) {
        return (number < 4 && number >= 0);
    }

    private boolean less16(int number) {
        return (number < 16 && number >= 0);
    }

    private boolean less256(int number) {
        return (number < 256 && number >= 0);
    }

    private boolean less65536(int number) {
        return (number < 65536 && number >= 0);
    }

    public static void setMacAddress(byte[] macAddress) {
        MAC_ADDRESS = macAddress;
    }

    public static void setSelectedDevice(String device) {
        SELECTED_DEVICE = device;
    }

    public void setFlagX(byte flagX) {
        if (flagX == 0 || flagX == 1) {
            FLAG_X = flagX;
            System.out.println("[SET FLAG X]: " + FLAG_X);
        }
    }

    public void setFlagD(byte flagD) {
        if (flagD == 0 || flagD == 1) {
            FLAG_D = flagD;
            System.out.println("[SET FLAG D]: " + FLAG_D);
        }
    }

    public void setFlagM(byte flagM) {
        if (flagM == 0 || flagM == 1) {
            FLAG_M = flagM;
            System.out.println("[SET FLAG M]: " + FLAG_M);
        }
    }

    public void setSourceAddress(byte[] sourceAddress) {
        SOURCE_ADDRESS = sourceAddress;
        System.out.println("[SET SOURCE ADDRESS]:" + FormatUtils.ip(SOURCE_ADDRESS));
    }

    public void setDestinationAddress(byte[] destinationAddress) {
        DESTINATION_ADDRESS = destinationAddress;
        System.out.println("[SET DESTINATION ADDRESS]:" + FormatUtils.ip(DESTINATION_ADDRESS));
    }

    public void setCheckSum(int checkSum) {
        if (less65536(checkSum)) {
            CHECK_SUM = checkSum;
            System.out.println("[SET CHECK SUM]:" + CHECK_SUM);
        }
    }

    public void setCalculateCheckSum(boolean calculateCheckSum) {
        CALCULATE_CHECKSUM = calculateCheckSum;
        System.out.println("[SET CALCULATE CHECK SUM]:" + CALCULATE_CHECKSUM);
    }

    public void setTCPHeaderLength(int headerLength) {
        if (less16(headerLength)) {
            TCP_HEADER_LENGTH = headerLength;
            System.out.println("[SET TCP HEADER LENGTH]:" + TCP_HEADER_LENGTH);
        }
    }

    public void setTCPSourcePort(int sourcePort) {
        if (less65536(sourcePort)) {
            TCP_SOURCE_PORT = sourcePort;
            System.out.println("[SET TCP SOURCE PORT]:" + TCP_SOURCE_PORT);
        }
    }

    public void setUDPSourcePort(int sourcePort) {
        if (less65536(sourcePort)) {
            UDP_SOURCE_PORT = sourcePort;
            System.out.println("[SET UDP SOURCE PORT]:" + UDP_SOURCE_PORT);
        }
    }

    public void setUDPDestinationPort(int sourcePort) {
        if (less65536(sourcePort)) {
            UDP_DESTINATION_PORT = sourcePort;
            System.out.println("[SET UDP DESTINATION PORT]:" + UDP_DESTINATION_PORT);
        }
    }

    public void setUDPHeaderLength(int sourcePort) {
        if (less65536(sourcePort)) {
            UDP_HEADER_LENGTH = sourcePort;
            System.out.println("[SET UDP HEADER LENGTH]:" + UDP_HEADER_LENGTH);
        }
    }

    public void setUDPData(byte[] data) {
        UDP_DATA = data;
        System.out.println("[SET UDP HEADER LENGTH]:" + Arrays.toString(UDP_DATA));
    }

    public void setICMPType(int icmpType) {
        ICMP_TYPE = icmpType;
    }

    public void setSelectedType(int type) {
        SELECTED_TYPE = type;
        System.out.println("[SET SELECTED TYPE]:" + SELECTED_TYPE);
    }

}
