package telran.appl.net;

import telran.net.Protocol;
import telran.net.TcpServer;

public class MainServer {
    public static void main(String[] args) {
        int port = 5000;

        Protocol protocol = new ReverseLengthProtocol();
        
        TcpServer server = new TcpServer(protocol, port);
        new Thread(server).start();

        System.out.println("Reverse Length Server started on port " + port);
    }
}