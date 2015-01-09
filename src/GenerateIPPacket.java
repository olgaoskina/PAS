import com.savarese.rocksaw.net.RawSocket;
import org.savarese.vserv.tcpip.IPPacket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by Olga on 11.12.14.
 */
public class GenerateIPPacket {

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
	protected int CHECK_SUM = 0;
	protected byte[] SOURCE_ADDRESS;
	protected byte[] DESTINATION_ADDRESS;
	protected boolean CALCULATE_CHECKSUM = false;
	protected IPPacket pack;

	public void send() {
		byte[] buffer = new byte[pack.size()];
		pack.getData(buffer);
		RawSocket rawSocket = new RawSocket();
		try {

			rawSocket.open(RawSocket.PF_INET, IPPacket.PROTOCOL_TCP);
			rawSocket.setIPHeaderInclude(true);
			InetAddress inetAddress = InetAddress.getByAddress(DESTINATION_ADDRESS);
//			buffer[0] = 0;
//			buffer[1] = 0;
//			buffer[3] = 0;
//			buffer[12] = 0;
//			buffer[13] = 0;
//			buffer[15] = 0;
//			buffer[59] = 0;
//			buffer[60] = 0;
//			buffer[63] = 0;
//			buffer[64] = 0;
//			buffer[65] = 0;
//			buffer[67] = 0;
//			buffer[68] = 0;
			System.out.println(Arrays.toString(buffer));
			rawSocket.write(inetAddress, buffer);
			System.out.println("DONE!");


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rawSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public GenerateIPPacket(IPPacket pack) {
		this.pack = pack;
	}

	public void generate() {
		pack.setIPVersion(VERSION);
		pack.setIPHeaderLength(HEADER_LENGTH);
//		pack.setIPHeaderLength(21);

		int b;
		b = (TOS_PRECEDENCE << 6);
		b |= (TOS_D << 5);
		b |= (TOS_T << 4);
		b |= (TOS_R << 3);
		b |= TOS_ECN;
		pack.setTypeOfService(b);

		pack.setIPPacketLength(TOTAL_LENGTH);
		pack.setIdentification(ID);
		pack.setIPFlags(FLAG_X << 2 | FLAG_D << 1 | FLAG_M);
		pack.setFragmentOffset(OFFSET);
		pack.setTTL(TTL);
		pack.computeIPChecksum(true);
		pack.setSourceAsWord(ByteBuffer.wrap(SOURCE_ADDRESS).getInt());
		pack.setDestinationAsWord(ByteBuffer.wrap(DESTINATION_ADDRESS).getInt());
	}

	public void setPrecedence(int precedence) {
		if (less8(precedence)) {
			this.TOS_PRECEDENCE = precedence;
			System.out.println("[SET PRECEDENCE]: " + precedence);
		}
	}

	public void setTosD(int D) {
		if (less2(D)) {
			TOS_D = D;
			System.out.println("[SET TOS D]: " + TOS_D);
		}
	}

	public void setTosT(int T) {
		if (less2(T)) {
			TOS_T = T;
		}
	}

	public void setTosR(int R) {
		if (less2(R)) {
			TOS_R = R;
		}
	}

	public void setTosECN(int ECN) {
		if (less4(ECN)) {
			TOS_ECN = ECN;
		}
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

	protected boolean less2(int number) {
		return (number < 2 && number >= 0);
	}

	protected boolean less8(int number) {
		return (number < 8 && number >= 0);
	}

	protected boolean less4(int number) {
		return (number < 4 && number >= 0);
	}

	protected boolean less16(int number) {
		return (number < 16 && number >= 0);
	}

	protected boolean less256(int number) {
		return (number < 256 && number >= 0);
	}

	protected boolean less65536(int number) {
		return (number < 65536 && number >= 0);
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
		if (less65536(checkSum)) {
			CHECK_SUM = checkSum;
			System.out.println("[SET CHECK SUM]:" + CHECK_SUM);
		}
	}

	public void setCalculateCheckSum(boolean calculateCheckSum) {
		CALCULATE_CHECKSUM = calculateCheckSum;
		System.out.println("[SET CALCULATE CHECK SUM]:" + CALCULATE_CHECKSUM);
	}

}
