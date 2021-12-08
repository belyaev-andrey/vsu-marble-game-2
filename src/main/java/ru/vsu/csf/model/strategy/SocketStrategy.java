package ru.vsu.csf.model.strategy;

import ru.vsu.csf.model.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketStrategy implements BetStrategy {

    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public SocketStrategy(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot connect to client", ex);
        }
    }

    @Override
    public Integer getNext(int value) {
        String answer = "";
        try {
            String command = Command.BET.getCommandString()+Command.SEPARATOR+value;
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RESP.getCommandString().equals(answerParsed[0])) {
                System.out.println("Player's bet: " + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
            return Integer.parseInt(answerParsed[1]);
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }

    @Override
    public void endGame() {
        BetStrategy.super.endGame();
        try {
            String command = Command.END.getCommandString();
            System.out.println("To client:"+command);
            out.println(command);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Cannot close socket: "+e.getMessage());
            }
        }
    }
}