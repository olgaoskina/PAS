import org.jnetpcap.packet.JMemoryPacket;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Udp;
import org.savarese.vserv.tcpip.IPPacket;
import org.savarese.vserv.tcpip.UDPPacket;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by olgaoskina
 * 03 February 2015
 */
public class GenerateUDPPacket extends GenerateIPPacket {

    private int UDP_LENGTH = 0;
    private int UDP_SOURCE_PORT = 0;
    private int UDP_DESTINATION_PORT = 0;
    private int UDP_CHECKSUM = 0;
    private byte[] UDP_DATA;
    private UDPPacket udpPacket;
    protected boolean UDP_NEED_CALCULATE_CHECK_SUM = false;

    public UDPPacket getUdpPacket() {
        return udpPacket;
    }

    public GenerateUDPPacket(UDPPacket pack, String device) {
        super(pack, device);
    }

    @Override
    public void send() {
        byte[] bufferWithEthernet = getBufferWithEthernet(udpPacket);
        JPacket packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);

        if (NEED_CALCULATE_IP_CHECKSUM) {
            Pattern pattern = Pattern.compile("Ip:.*?checksum = [0-9A-Zx]+ \\([0-9]+\\) \\[incorrect: [0-9A-Zx]+");
            Matcher matcher = pattern.matcher(packet.toString());
            if (matcher.find()) {
                String newIPCheckSum = matcher.group().replaceFirst("^.*?incorrect: 0x", "");
                System.out.println("[FIND IP CHECKSUM]: " + newIPCheckSum);

                udpPacket.setIPCheckSum(Integer.parseInt(newIPCheckSum, 16));
                bufferWithEthernet = getBufferWithEthernet(udpPacket);
                packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);
            }
        }

        if (UDP_NEED_CALCULATE_CHECK_SUM) {
            Pattern pattern = Pattern.compile("Udp:.*?checksum = [0-9A-Zx]+ \\([0-9]+\\) \\[incorrect: [0-9A-Zx]+");
            Matcher matcher = pattern.matcher(packet.toString());
            if (matcher.find()) {
                String newIPCheckSum = matcher.group().replaceFirst("^.*?incorrect: 0x", "");
                System.out.println("[FIND TCP CHECKSUM]: " + newIPCheckSum);

                udpPacket.setUDPCheckSum(Integer.parseInt(newIPCheckSum, 16));
                bufferWithEthernet = getBufferWithEthernet(udpPacket);
                packet = new JMemoryPacket(JProtocol.ETHERNET_ID, bufferWithEthernet);
            }
        }

        packet.getHeader(new Ip4());
        packet.getHeader(new Udp());
        packet.scan(Ethernet.ID);

        System.out.println(pcap.sendPacket(ByteBuffer.wrap(packet.getByteArray(0, packet.size()))));
        System.out.println(packet);
    }

    public void generate() {
        super.generate();
        udpPacket = (UDPPacket) pack;

        udpPacket.setProtocol(IPPacket.PROTOCOL_UDP);

        udpPacket.setUDPPacketLength(UDP_LENGTH);

        udpPacket.setDestinationPort(UDP_DESTINATION_PORT);

        udpPacket.setSourcePort(UDP_SOURCE_PORT);

        if (UDP_NEED_CALCULATE_CHECK_SUM) {
            udpPacket.computeUDPChecksum(true);
        } else {
            udpPacket.setUDPCheckSum(UDP_CHECKSUM);
        }

        byte[] buffer = new byte[udpPacket.size()];
        udpPacket.getData(buffer);

        System.arraycopy(UDP_DATA, 0, buffer, 4 * HEADER_LENGTH + 4 * UDP_LENGTH, UDP_DATA.length);
        udpPacket.setData(buffer);

        byte[] array = new byte[udpPacket.size()];
        udpPacket.getData(array);
        System.out.println("[UDP PACKET]: " + Arrays.toString(array));
    }

    public void setUDPLength(int UDPLength) {
        UDP_LENGTH = UDPLength;
        System.out.println("[SET UDP LENGTH]:" + UDP_LENGTH);
    }

    public void setUDPSourcePort(int sourcePort) {
        UDP_SOURCE_PORT = sourcePort;
        System.out.println("[SET UDP SOURCE PORT]:" + UDP_SOURCE_PORT);
    }

    public void setUDPDestinationPort(int UDP_DESTINATION_PORT) {
        this.UDP_DESTINATION_PORT = UDP_DESTINATION_PORT;
    }

    public void setUDPCheckSum(int UDP_CHECKSUM) {
        this.UDP_CHECKSUM = UDP_CHECKSUM;
    }

    public void setUDPData(byte[] UDP_DATA) {
        this.UDP_DATA = UDP_DATA;
    }

    public void setNeedUDPCalculateCheckSum(boolean needCalculate) {
        this.UDP_NEED_CALCULATE_CHECK_SUM = needCalculate;
    }
}
