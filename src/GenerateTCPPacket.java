import com.savarese.rocksaw.net.RawSocket;
import org.savarese.vserv.tcpip.IPPacket;
import org.savarese.vserv.tcpip.TCPPacket;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * Created by Olga
 * 24 December 2014.
 */
public class GenerateTCPPacket extends GenerateIPPacket {

	private int TCP_HEADER_LENGTH = 0;
	private int TCP_SOURCE_PORT = 0;
	private int TCP_DESTINATION_PORT = 0;
	private int TCP_CHECKSUM = 0;
	private byte[] TCP_DATA;
	private long TCP_ACK_NUMBER = 0;
	private long TCP_SEQ_NUMBER = 0;
	private int TCP_WIN_SIZE = 0;
	private boolean TCP_ACK = false;
	private boolean TCP_FIN = false;
	private boolean TCP_PSH = false;
	private boolean TCP_RST = false;
	private boolean TCP_SYN = false;
	private boolean TCP_URG = false;
	private boolean TCP_NEED_CALCULATE_CHECK_SUM = false;
	private int TCP_URGENT_POINTER = 0;

	public GenerateTCPPacket(TCPPacket pack) {
		super(pack);
	}

	public void generate() {
		super.generate();
		TCPPacket tcpPacket = (TCPPacket) pack;

		tcpPacket.setProtocol(IPPacket.PROTOCOL_TCP);

		tcpPacket.setTCPDataByteLength(TCP_DATA.length);

		tcpPacket.setTCPHeaderLength(TCP_HEADER_LENGTH);

//		tcpPacket.setDestinationPort(TCP_DESTINATION_PORT);

//		tcpPacket.setSourcePort(TCP_SOURCE_PORT);

		tcpPacket.setAckNumber(TCP_ACK_NUMBER);

		tcpPacket.setSequenceNumber(TCP_SEQ_NUMBER);

		tcpPacket.setWindowSize(TCP_WIN_SIZE);

		tcpPacket.setUrgentPointer(TCP_URGENT_POINTER);

		if (TCP_NEED_CALCULATE_CHECK_SUM) {
			tcpPacket.computeTCPChecksum(true);
		} else {
			tcpPacket.setCheckSum(TCP_CHECKSUM);
		}


		byte[] buffer = new byte[tcpPacket.size()];
		tcpPacket.getData(buffer);

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
		tcpPacket.addControlFlags(flags);
		byte[] buffer1 = new byte[pack.size()];
		pack.getData(buffer1);
		System.out.println(Arrays.toString(buffer1));
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
}
