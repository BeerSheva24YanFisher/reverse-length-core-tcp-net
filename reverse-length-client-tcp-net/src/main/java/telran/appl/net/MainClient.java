package telran.appl.net;

import java.util.Scanner;

import telran.net.TcpClient;

public class MainClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (TcpClient client = new TcpClient(host, port)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter text to reverse and get its length (type 'exit' to quit):");

            while (true) {
                System.out.print("Input: ");
                String input = scanner.nextLine();
                
                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Exiting...");
                    break;
                }

                String response = client.sendAndReceive("reverseLength", input);
                System.out.println("Response from server: " + response);
            }

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}