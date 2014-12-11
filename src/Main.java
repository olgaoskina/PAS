/**
 * Created by olgaoskina
 * 23 December 2014.
 */
public class Main {

	public static void main(String[] args) {
		SelectDevice selectDevice = new SelectDevice();
        String device = selectDevice.getSelectDevice();
        GenerateIPPacket.setMacAddress(selectDevice.getMacAddress());
        GenerateIPPacket.setSelectedDevice(device);
        GuiForm guiForm = new GuiForm();
	}
}
