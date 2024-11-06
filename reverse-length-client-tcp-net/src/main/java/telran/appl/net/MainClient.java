package telran.appl.net;

import java.io.IOException;

import telran.net.TcpClient;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class MainClient {
    static TcpClient client;

    public static void main(String[] args) {
        Menu menu = new Menu("Network application",
                Item.of("Start session", MainClient::startSession),
                Item.of("Exit", MainClient::exit, true));
        menu.perform(new StandardInputOutput());
    }

    static void startSession(InputOutput io) {
        String host = io.readString("Enter hostname");
        int port = io.readNumberRange("Enter port (3000-50000)", "Wrong port", 3000, 50000).intValue();
        tryingToClose();
        client = new TcpClient(host, port);
        Menu menu = new Menu("Run Session",
                Item.of("Enter command and data", MainClient::stringProcessing),
                Item.ofExit());
        menu.perform(io);
    }

    static void stringProcessing(InputOutput io) {
        String requestType = io.readString("Enter your command (reverse, lenght)");
        String requestData = io.readString("Enter your data");
        String response = client.sendAndReceive(requestType, requestData);
        io.writeLine(response);
    }

    static void exit(InputOutput io) {
        tryingToClose();
    }

    private static void tryingToClose() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}