package com.example.idea;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int port = 5000;
        String host = "193.105.234.22";
        //---------------------------
        try {
            System.out.println("------- client started! ---------");
            Client client = new Client(host, port);
            client.sendReceive("Salam");
            Thread.sleep(2000);
            client.sendReceive("quit");
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        //---------------------------
        /*
        int totalClients = 4;
        Stream.iterate(1, x -> x + 1).limit(totalClients).forEach(id -> {
            new Thread(() -> {
                try (Client client = new Client(host, port)) {
                    client.sendReceive("HELO" + id);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });*/
    }
}
