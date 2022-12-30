// Contributed by - Anuj Das ( GC University, Silchar - @ Department of Computer Science )

// 9. Write a program to implement following methods:
//  -  public String getHostName()
//  -  public byte[] getAddress()
//  -  public String getHostAddress()

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

class InetMethodsDemonstration {
    public static void main(String[] args) throws UnknownHostException {
        Scanner sc = new Scanner(System.in);

        InetAddress local = InetAddress.getLocalHost();
        System.out.println("Host Name: "+local.getHostName());
        System.out.println("Host Address: "+local.getHostAddress());

        System.out.print("\nEnter a valid URL:  ");
        String url = sc.next();
        InetAddress host = InetAddress.getByName(url);
        byte []addressBytes = host.getAddress();
       
        System.out.println(Arrays.toString(addressBytes));
    }    
}
