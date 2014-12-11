import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga on 31.10.14.
 */
public class FindDevice {
    StringBuilder errors = new StringBuilder();

    public FindDevice() {

    }

    public List<PcapIf> findAllDevices() {
        List<PcapIf> devices = new ArrayList<PcapIf>();
        Pcap.findAllDevs(devices, errors);
        return devices;
    }

}
