// Contributed by - Anuj Das ( GC University, Silchar - @ Department of Computer Science )

// 6. WAP to find the address of the local machine.

import java.net.*;

class GetLocalHostAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("LocalHost Address: "+localhost.getHostAddress());
    }
}
