import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by olgaoskina
 * 23 December 2014.
 */
public class Main {

    public static byte[] SOURCE_IP_ADDRESS;

	public static void main(String[] args) {
        new Info();
		SelectDevice selectDevice = new SelectDevice();
        String device = selectDevice.getSelectDevice();
        SOURCE_IP_ADDRESS = selectDevice.getMacAddress();
        GuiForm guiForm = new GuiForm(device);
	}
}
