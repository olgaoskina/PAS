import org.jnetpcap.Pcap;
import org.savarese.vserv.tcpip.IPPacket;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by olgaoskina
 * 03 February 2015
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

    protected String SELECTED_DEVICE;
    protected byte[] SOURCE_MAC_ADDRESS;
    protected byte[] DESTINATION_MAC_ADDRESS;
    protected Pcap pcap;

    abstract public void send();

    public GenerateIPPacket(IPPacket pack, String device) {
        SELECTED_DEVICE = device;
        this.pack = pack;
        pcap = Pcap.openLive(
                SELECTED_DEVICE,
                Pcap.DEFAULT_SNAPLEN,
                Pcap.MODE_PROMISCUOUS,
                Pcap.DEFAULT_TIMEOUT,
                new StringBuilder()
        );
    }

    protected void computeMacAddresses() {
        try {
            byte[] macAddress = GetMacAddressByIpAddress.getInstance().getMacAddress(
                    InetAddress.getByAddress(SOURCE_ADDRESS)
            );
            if (macAddress != null) {
                SOURCE_MAC_ADDRESS = macAddress;
            }
            macAddress = GetMacAddressByIpAddress.getInstance().getMacAddress(
                    InetAddress.getByAddress(DESTINATION_ADDRESS)
            );
            if (macAddress != null) {
                DESTINATION_MAC_ADDRESS = macAddress;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
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
        } else {
            pack.setIPCheckSum(IP_CHECK_SUM);
        }
    }

    public byte[] getBufferWithEthernet(IPPacket packet) {
        computeMacAddresses();
        byte[] buffer = new byte[packet.size()];
        byte[] bufferWithEthernet = new byte[packet.size() + 14];
        packet.getData(buffer);

        System.arraycopy(DESTINATION_MAC_ADDRESS, 0, bufferWithEthernet, 0, DESTINATION_ADDRESS.length);
        System.arraycopy(SOURCE_MAC_ADDRESS, 0, bufferWithEthernet, DESTINATION_ADDRESS.length, SOURCE_MAC_ADDRESS.length);

        bufferWithEthernet[2 * SOURCE_MAC_ADDRESS.length] = 0x08;
        bufferWithEthernet[2 * SOURCE_MAC_ADDRESS.length + 1] = 0x00;

        System.arraycopy(buffer, 0, bufferWithEthernet, 2 * SOURCE_MAC_ADDRESS.length + 2, buffer.length);

        System.out.println("[BUFFER WITH ETHERNET]: " + Arrays.toString(bufferWithEthernet));
        System.out.println("[BUFFER]: " + Arrays.toString(buffer));
        return bufferWithEthernet;
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
        VERSION |= version;
        System.out.println("[SET VERSION]: " + BigInteger.valueOf(VERSION).toString(2));
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
