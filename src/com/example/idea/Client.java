package com.example.idea;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Stream;

/**
 * Created by rasoolzadeh
 */
public class Client implements  AutoCloseable{
    private final Socket client;
    private final DataOutputStream os;
    private final BufferedReader is;

    public Client(String host, int port) throws UnknownHostException, IOException {
        client = new Socket(host, port);
        os = new DataOutputStream(client.getOutputStream());
        is = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void sendReceive(String message) {
        try {
            System.out.println("Thread = " + Thread.currentThread());
            System.out.println("Sending to Server: " + message);
            os.writeBytes(message + "\n");
            os.flush();
            // keep on reading from/to the socket till we receive the "Ok" from Server,
            // once we received that we break.
            String responseLine = is.readLine();
            if (responseLine != null) {
                System.out.println("Server Sent: " + responseLine);
            } else {
                System.out.println("Server Sent: No Response");
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void close() throws IOException {
        sendReceive("QUIT");
        is.close();
        os.close();
    }
}
