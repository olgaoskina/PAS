import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.savarese.vserv.tcpip.ICMPEchoPacket;
import org.savarese.vserv.tcpip.ICMPPacket;
import org.savarese.vserv.tcpip.IPPacket;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by olgaoskina
 * 03 February 2015
 */
public class GenerateICMPPacket extends GenerateIPPacket {

    private int ICMP_TYPE = 0;
    private int ICMP_CODE = 0;
    private int ICMP_ID = 0;
    private int ICMP_SEQ_NUMBER = 0;
    private int ICMP_CHECKSUM = 0;
    private byte[] ICMP_DATA;
    protected boolean ICMP_NEED_CALCULATE_CHECK_SUM = false;
    private ICMPEchoPacket icmpEchoPacket;

    public ICMPEchoPacket getIcmpEchoPacket() {
        return icmpEchoPacket;
    }

    public GenerateICMPPacket(ICMPPacket pack, String device) {
        super(pack, device);
    }

    @Override
    public void send() {
        byte[] bufferWithEthernet = getBufferWithEthernet(icmpEchoPacket);
        JPacket packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);

        if (NEED_CALCULATE_IP_CHECKSUM) {
            Pattern pattern = Pattern.compile("Ip:.*?checksum = [0-9A-Zx]+ \\([0-9]+\\) \\[incorrect: [0-9A-Zx]+");
            Matcher matcher = pattern.matcher(packet.toString());
            if (matcher.find()) {
                String newIPCheckSum = matcher.group().replaceFirst("^.*?incorrect: 0x", "");
                System.out.println("[FIND IP CHECKSUM]: " + newIPCheckSum);

                icmpEchoPacket.setIPCheckSum(Integer.parseInt(newIPCheckSum, 16));
                bufferWithEthernet = getBufferWithEthernet(icmpEchoPacket);
                packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);
            }
        }

        if (ICMP_NEED_CALCULATE_CHECK_SUM) {
            Pattern pattern = Pattern.compile("Icmp:.*?checksum = [0-9A-Zx]+ \\([0-9]+\\) \\[incorrect: [0-9A-Zx]+");
            Matcher matcher = pattern.matcher(packet.toString());
            if (matcher.find()) {
                String newIPCheckSum = matcher.group().replaceFirst("^.*?incorrect: 0x", "");
                System.out.println("[FIND TCP CHECKSUM]: " + newIPCheckSum);

                icmpEchoPacket.setICMPCheckSum(Integer.parseInt(newIPCheckSum, 16));
                bufferWithEthernet = getBufferWithEthernet(icmpEchoPacket);
                packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);
            }
        }
        packet.getHeader(new Ip4());
        packet.getHeader(new Icmp());
        packet.scan(Ethernet.ID);

        System.out.println(pcap.sendPacket(ByteBuffer.wrap(packet.getByteArray(0, packet.size()))));
        System.out.println(packet);
    }

    public void generate() {
        super.generate();
        icmpEchoPacket = (ICMPEchoPacket) pack;

        icmpEchoPacket.setProtocol(IPPacket.PROTOCOL_ICMP);
        icmpEchoPacket.setIdentifier(ICMP_ID);
        icmpEchoPacket.setCode(ICMP_CODE);
        icmpEchoPacket.setType(ICMP_TYPE);
        icmpEchoPacket.setSequenceNumber(ICMP_SEQ_NUMBER);

        if (ICMP_NEED_CALCULATE_CHECK_SUM) {
            icmpEchoPacket.computeICMPChecksum(true);
        } else {
            icmpEchoPacket.setICMPCheckSum(ICMP_CHECKSUM);
        }

        byte[] buffer = new byte[icmpEchoPacket.size()];
        icmpEchoPacket.getData(buffer);
        System.arraycopy(ICMP_DATA, 0, buffer, 4 * HEADER_LENGTH + 4 * icmpEchoPacket.getICMPHeaderByteLength() - 6 * 4, ICMP_DATA.length);
        icmpEchoPacket.setData(buffer);

        byte[] array = new byte[icmpEchoPacket.size()];
        icmpEchoPacket.getData(array);
        System.out.println("[ICMP PACKET]: " + Arrays.toString(array));
    }

    public void setICMPType(int type) {
        ICMP_TYPE = type;
        System.out.println("[SET ICMP TYPE]:" + ICMP_TYPE);
    }

    public void setICMPID(int ID) {
        ICMP_ID = ID;
        System.out.println("[SET ICMP ID]:" + ICMP_ID);
    }

    public void setICMPCode(int code) {
        ICMP_CODE = code;
        System.out.println("[SET ICMP CODE]:" + ICMP_CODE);
    }

    public void setICMPSeqNumber(int seqNumber) {
        this.ICMP_SEQ_NUMBER = seqNumber;
        System.out.println("[SET ICMP SEQ NUMBER]:" + ICMP_SEQ_NUMBER);
    }

    public void setICMPCheckSum(int ICMP_CHECKSUM) {
        this.ICMP_CHECKSUM = ICMP_CHECKSUM;
        System.out.println("[SET ICMP CHECKSUM]:" + ICMP_CHECKSUM);
    }

    public void setICMPData(byte[] ICMP_DATA) {
        this.ICMP_DATA = ICMP_DATA;
    }

    public void setNeedICMPCalculateCheckSum(boolean needCalculate) {
        this.ICMP_NEED_CALCULATE_CHECK_SUM = needCalculate;
        System.out.println("[SET ICMP_NEED_CALCULATE_CHECK_SUM]:" + ICMP_NEED_CALCULATE_CHECK_SUM);
    }
}
