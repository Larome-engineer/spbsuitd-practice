package consolechat;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Server implements TCPListenerInterface {

    private static final String DB_USERNAME = "postgres";
    public static final String DB_PASS = "la%2xr#ome";
    public static final String DB_URL = "jdbc:postgresql://localhost:8000/webapp";

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    public static void main(String[] args) {
        new Server();
    }

    private Server() {
        System.out.println("SERVER RUN...");
        try (ServerSocket serverSocket = new ServerSocket(8180)) {
            while (true) {
                try {

                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCP exception: " + e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void connectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendAllConnections("Client connected: " + tcpConnection); /*Так как, мы складываем объект со строкой,
        то у него неявно вызывается метод toString, а в классе
        TCPConnection мы переопределили метод toString,
        который возвращает нам строчку: адрес и порт*/
    }

    @Override
    public synchronized void receiveString(TCPConnection tcpConnection, String value) {
        sendAllConnections(value);
    }

    @Override
    public synchronized void disconnection(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendAllConnections("Client disconnect: " + tcpConnection);
    }

    @Override
    public synchronized void exception(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCP exception" + e);
    }

    private void sendAllConnections(String msg) {
        for (TCPConnection connection : connections) {
            connection.sendMessage(msg);
        }
    }
}
