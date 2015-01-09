import com.savarese.rocksaw.net.RawSocket;
import org.savarese.vserv.tcpip.IPPacket;
import org.savarese.vserv.tcpip.TCPPacket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * Created by olgaoskina
 * 23 December 2014.
 */
public class Main {

	public static void main(String[] args) throws UnknownHostException {
//		SelectDevice selectDevice = new SelectDevice();
//        String device = selectDevice.getSelectDevice();
//        GenerateIPPacket.setMacAddress(selectDevice.getMacAddress());
//        GenerateIPPacket.setSelectedDevice(device);
        GuiForm guiForm = new GuiForm();
//
//		byte[] buffer = new byte[200];
//		RawSocket rawSocket = new RawSocket();
//		TCPPacket pack = new TCPPacket(200);
//		pack.setProtocol(IPPacket.PROTOCOL_TCP);
//		pack.setIPVersion(6);
////		pack.setSourcePort(100);
////		pack.setDestinationPort(2030);
//		pack.setSequenceNumber(200);
//		pack.setAckNumber(123);
//		pack.setTCPHeaderLength(10);
//		pack.setControlFlags(0);
//		pack.setWindowSize(23);
////		pack.setCheckSum(false, 100);
//		pack.setUrgentPointer(2);
//		pack.setSourceAsWord(ByteBuffer.wrap(InetAddress.getByName("192.168.0.107").getAddress()).getInt());
//
//		pack.setIPVersion(4);
//		pack.getData(buffer);
//
//		try {
//			rawSocket.open(RawSocket.PF_INET, RawSocket.getProtocolByName("TCP"));
////			rawSocket.setIPHeaderInclude(true);
//			rawSocket.write(InetAddress.getByName("8.8.8.8"), buffer);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rawSocket.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

	}
}
