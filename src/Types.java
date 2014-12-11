/**
 * Created by Olga on 21.11.14.
 */
public class Types {

    public enum Packet {
        IP("IP"),
        TCP("TCP"),
        UDP("UDP"),
        ICMP("ICMP");

        private String nameOfPacket;

        private Packet(String nameOfPacket) {
            this.nameOfPacket = nameOfPacket;
        }

        public String getNameOfPacket() {
            return nameOfPacket;
        }

        public static Packet getByName(String name) {
            if (name.equals("IP")) {
                return IP;
            } else if (name.equals("TCP")) {
                return TCP;
            } else if (name.equals("UDP")) {
                return UDP;
            } else if (name.equals("ICMP")) {
                return ICMP;
            } else {
                return null;
            }
        }
    }
}
