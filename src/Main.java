import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by olgaoskina
 * 23 December 2014.
 */
public class Main {
	public static void main(String[] args) {
		SelectDevice selectDevice = new SelectDevice();
        String device = selectDevice.getSelectDevice();
        GuiForm guiForm = new GuiForm(device);
	}
}
