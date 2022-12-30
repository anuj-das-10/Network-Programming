// 8. WAP A program that prints all the addresses of www.youtube.com

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GetAllAddresses {
    public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a valid URL:  ");
    String web_url = sc.next();             //  www.youtube.com

        try {
            InetAddress[] myHost = InetAddress.getAllByName(web_url);
            System.out.println("\nAll Addresses of "+web_url+" :");
            for(InetAddress host:myHost) {
            System.out.println(host.getHostAddress());
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}