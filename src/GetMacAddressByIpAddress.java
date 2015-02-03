import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by olgaoskina
 * 03 February 2015
 */
public class GetMacAddressByIpAddress {

    private final static GetMacAddressByIpAddress INSTANCE = new GetMacAddressByIpAddress();

    private GetMacAddressByIpAddress() {
    }

    public static GetMacAddressByIpAddress getInstance() {
        return INSTANCE;
    }

    public byte[] getMacAddress(String ipAddress) {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"arp", "-a", ipAddress});
            Pattern pattern = Pattern.compile(ipAddress + ".*?[0-9a-z-]+");
            Matcher matcher = pattern.matcher(getAnswer(process));
            if (matcher.find()) {
                String macAddress = matcher.group().replaceFirst("^" + ipAddress + "[^0-9a-z-]+", "");
                return parseMacAddress(macAddress);
            } else {
                return Main.getSourceIpAddress();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getMacAddress(InetAddress ipAddress) {
        return getMacAddress(ipAddress.getHostAddress());
    }

    private String getAnswer(Process process) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder answer = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                answer.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }

    private byte[] parseMacAddress(String macAddress) {
        String[] hexAddress = macAddress.split("-");
        byte[] byteAddress = new byte[hexAddress.length];
        for (int i = 0; i < hexAddress.length; i++) {
            byteAddress[i] = (byte) Integer.parseInt(hexAddress[i], 16);
        }
        return byteAddress;
    }
}
