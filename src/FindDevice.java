import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olgaoskina
 * 03 February 2015
 */
public class FindDevice {
    private final StringBuilder errors = new StringBuilder();
    private final static FindDevice INSTANCE = new FindDevice();

    private FindDevice() {
    }

    public static FindDevice getInstance() {
        return INSTANCE;
    }

    public List<PcapIf> findAllDevices() {
        List<PcapIf> devices = new ArrayList<PcapIf>();
        Pcap.findAllDevs(devices, errors);
        return devices;
    }
}
