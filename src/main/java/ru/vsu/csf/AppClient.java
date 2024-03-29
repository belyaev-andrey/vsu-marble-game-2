package ru.vsu.csf;

import ru.vsu.csf.model.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppClient {

    private final String host;
    private final int port;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) throws IOException {
        AppClient client = new AppClient("localhost", 9999);
        client.start();
    }

    public AppClient (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        String command;
        while (!socket.isClosed() && (command = in.readLine()) != null) {
            String[] parsed = command.split(Command.SEPARATOR);
            System.out.println("From server:"+command);
            if (Command.END.getCommandString().equals(parsed[0])) {
                socket.close();
            }
            if (Command.BET.getCommandString().equals(parsed[0])) {
                System.out.print("Enter your bet (up to "+parsed[1]+"): ");
                int nextInt = new Scanner(System.in).nextInt();
                String resp = Command.RESP.getCommandString()+ Command.SEPARATOR + nextInt;
                System.out.println("To server:"+resp);
                out.println(resp);
            }
        }
    }

}