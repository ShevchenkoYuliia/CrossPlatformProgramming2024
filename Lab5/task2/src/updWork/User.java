package updWork;

import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {
    private final long serialVersionUID = -1L;
    private InetAddress address;
    private int port;
    public User(InetAddress address, int port){
        this.address = address;
        this.port = port;
    }
    public User(){

    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String toString() {
        return "User{" +
                "address=" + address +
                ", port=" + port +
                '}';
    }
}
