/**
 * Created by olgaoskina
 * 23 December 2014.
 */
public class Main {

    private static byte[] sourceIpAddress;

    public static void main(String[] args) {
        new Info();
        SelectDevice selectDevice = new SelectDevice();
        String device = selectDevice.getSelectedDevice();
        sourceIpAddress = selectDevice.getMacAddress();
        new GuiForm(device);
    }

    public static byte[] getSourceIpAddress() {
        return sourceIpAddress;
    }
}
