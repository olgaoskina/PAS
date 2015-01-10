import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.savarese.vserv.tcpip.IPPacket;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga on 11.12.14.
 */
abstract public class GenerateIPPacket {
    protected int TOS_PRECEDENCE = 0;
    protected int TOS_D = 0;
    protected int TOS_T = 0;
    protected int TOS_R = 0;
    protected int TOS_ECN = 0;
    protected int VERSION = 0;
    protected int HEADER_LENGTH = 0;
    protected int TOTAL_LENGTH = 0;
    protected int ID = 0;
    protected int FLAG_X = 0;
    protected int FLAG_D = 0;
    protected int FLAG_M = 0;
    protected int OFFSET = 0;
    protected int TTL = 0;
    protected int IP_CHECK_SUM = 0;
    protected boolean NEED_CALCULATE_IP_CHECKSUM = false;
    protected byte[] SOURCE_ADDRESS;
    protected byte[] DESTINATION_ADDRESS;
    protected boolean CALCULATE_CHECKSUM = false;
    protected IPPacket pack;

    protected String SELECTED_DEVICE = "\\Device\\NPF_{A7C70EAE-F31D-44EE-B8AD-6A71B237CBBF}";
    protected byte[] SOURCE_MAC_ADDRESS = new byte[]{8, 0, 39, 83, -15, -78};
    protected byte[] DESTINATION_MAC_ADDRESS = new byte[]{8, 0, 39, 83, -15, -78};
    protected Pcap pcap;

    abstract public void send();

    public GenerateIPPacket(IPPacket pack) {
        this.pack = pack;
        pcap = Pcap.openLive(SELECTED_DEVICE, Pcap.DEFAULT_SNAPLEN, Pcap.MODE_PROMISCUOUS, Pcap.DEFAULT_TIMEOUT, new StringBuilder());
    }

    public void generate() {
        pack.setIPVersion(VERSION);
        pack.setIPHeaderLength(HEADER_LENGTH);
        int b;
        b = (TOS_PRECEDENCE << 5);
        b |= (TOS_D << 4);
        b |= (TOS_T << 3);
        b |= (TOS_R << 2);
        b |= TOS_ECN;
        pack.setTypeOfService(b);
        pack.setIPPacketLength(TOTAL_LENGTH);
        pack.setIdentification(ID);
        pack.setIPFlags(FLAG_X << 2 | FLAG_D << 1 | FLAG_M);
        pack.setFragmentOffset(OFFSET);
        pack.setTTL(TTL);
        pack.setSourceAsWord(ByteBuffer.wrap(SOURCE_ADDRESS).getInt());
        pack.setDestinationAsWord(ByteBuffer.wrap(DESTINATION_ADDRESS).getInt());

        if (NEED_CALCULATE_IP_CHECKSUM) {
            pack.computeIPChecksum(true);

//            System.out.println(pack.toString().matches("checksum = [0-9A-Zx]+ \\([0-9]+\\) \\[incorrect: \\]"));
        } else {
            pack.setIPCheckSum(IP_CHECK_SUM);
        }

    }

    public void setNeedCalculateIPCheckSum(boolean needCalculateIPCheckSum) {
        NEED_CALCULATE_IP_CHECKSUM = needCalculateIPCheckSum;
    }

    public void setPrecedence(int precedence) {
        this.TOS_PRECEDENCE = precedence;
        System.out.println("[SET PRECEDENCE]: " + precedence);
    }

    public void setTosD(int D) {
        TOS_D = D;
        System.out.println("[SET TOS D]: " + TOS_D);
    }

    public void setTosT(int T) {
        TOS_T = T;
        System.out.println("[SET TOS T]: " + TOS_T);
    }

    public void setTosR(int R) {
        TOS_R = R;
        System.out.println("[SET TOS R]: " + TOS_R);
    }

    public void setTosECN(int ECN) {
        TOS_ECN = ECN;
        System.out.println("[SET TOS ECN]: " + TOS_ECN);
    }

    public void setVersion(int version) {
        if (version == 4 || version == 6) {
            VERSION |= version;
            System.out.println("[SET VERSION]: " + BigInteger.valueOf(VERSION).toString(2));
        }
    }

    public void setHeaderLength(int length) {
        HEADER_LENGTH |= length;
        System.out.println("[SET IP HEADER LENGTH]: " + BigInteger.valueOf(HEADER_LENGTH).toString(2));
    }

    public void setTotalLength(int totalLength) {
        TOTAL_LENGTH = totalLength;
        System.out.println("[SET TOTAL LENGTH]: " + BigInteger.valueOf(TOTAL_LENGTH).toString(2));
    }

    public void setID(int id) {
        ID = id;
        System.out.println("[SET ID]: " + ID);
    }

    public void setOffset(int offset) {
        OFFSET = offset;
        System.out.println("[SET OFFSET]: " + OFFSET);
    }

    public void setTTL(int ttl) {
        TTL = ttl;
        System.out.println("[SET TTL]: " + TTL);
    }


    public void setFlagX(int flagX) {
        if (flagX == 0 || flagX == 1) {
            FLAG_X = flagX;
            System.out.println("[SET FLAG X]: " + FLAG_X);
        }
    }

    public void setFlagD(int flagD) {
        if (flagD == 0 || flagD == 1) {
            FLAG_D = flagD;
            System.out.println("[SET FLAG D]: " + FLAG_D);
        }
    }

    public void setFlagM(int flagM) {
        if (flagM == 0 || flagM == 1) {
            FLAG_M = flagM;
            System.out.println("[SET FLAG M]: " + FLAG_M);
        }
    }

    public void setSourceAddress(byte[] sourceAddress) {
        SOURCE_ADDRESS = sourceAddress;
        try {
            System.out.println("[SET SOURCE ADDRESS]: " + InetAddress.getByAddress(SOURCE_ADDRESS).getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setDestinationAddress(byte[] destinationAddress) {
        DESTINATION_ADDRESS = destinationAddress;
        try {
            System.out.println("[SET DESTINATION ADDRESS]:" + InetAddress.getByAddress(DESTINATION_ADDRESS).getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setCheckSum(int checkSum) {
        IP_CHECK_SUM = checkSum;
        System.out.println("[SET CHECK SUM]:" + IP_CHECK_SUM);
    }

    public void setNeedCalculateTCPCheckSum(boolean calculateCheckSum) {
        CALCULATE_CHECKSUM = calculateCheckSum;
        System.out.println("[SET CALCULATE CHECK SUM]:" + CALCULATE_CHECKSUM);
    }

}
