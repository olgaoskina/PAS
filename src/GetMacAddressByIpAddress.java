import org.omg.SendingContext.RunTime;

import java.io.*;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga on 10.01.15.
 */
public class GetMacAddressByIpAddress {

    private GetMacAddressByIpAddress() {
    }

    public static byte[] getMacAddress(String ipAddress) {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"arp", "-a", ipAddress});
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder answ = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                answ.append(line).append("\n");
            }

            Pattern pattern = Pattern.compile(ipAddress + ".*?[0-9a-z-]+");
            Matcher matcher = pattern.matcher(answ.toString());
            if (matcher.find()) {
                String macAddress = matcher.group().replaceFirst("^" + ipAddress + "[^0-9a-z-]+", "");
                System.out.println("[MAC ADDRESS]: " + macAddress);
                return parseMacAddress(macAddress);
            } else {
                return Main.SOURCE_IP_ADDRESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getMacAddress(InetAddress ipAddress) {
        return getMacAddress(ipAddress.getHostAddress());
    }

    public static byte[] parseMacAddress(String macAddress) {
        String[] hexAddress = macAddress.split("-");
        byte[] byteAddress = new byte[hexAddress.length];
        for (int i = 0; i < hexAddress.length; i++) {
            byteAddress[i] = (byte) Integer.parseInt(hexAddress[i], 16);
        }
        return byteAddress;
    }

}
