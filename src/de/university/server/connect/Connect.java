package de.university.server.connect;


// Server.java

// import java.net.ServerSocket;
// import java.net.Socket;
import de.university.server.save.Data;

import java.io.*;
import java.net.Socket;

public class Connect {
    private Data data;

    public Connect(Data data) {
        this.data = data;
    }

    public void test() throws IOException {
        int port = 5565;
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
        java.net.Socket client = warteAufAnmeldung(serverSocket);
        String nachricht = leseNachricht(client);
        analysiereNachricht(nachricht, client);
        schreibeNachricht(client, nachricht);
    }

    private void analysiereNachricht(String nachricht,Socket socket) {
        String[] msg = nachricht.split("-");
        if(msg[0].equals("join")){
            data.addNewUser(msg[1], socket);
        }else if(msg[0].equals("score")){
           data.replaceScore(msg[1],Integer.parseInt(msg[2]));
        }
    }

    public java.net.Socket warteAufAnmeldung(java.net.ServerSocket serverSocket) throws IOException {
        java.net.Socket socket = serverSocket.accept(); // blockiert, bis sich ein Client angemeldet hat
        return socket;
    }

    public String leseNachricht(java.net.Socket socket) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        char[] buffer = new char[200];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
        String nachricht = new String(buffer, 0, anzahlZeichen);
        return nachricht;
    }

    public void schreibeNachricht(java.net.Socket socket, String nachricht) throws IOException {
        PrintWriter printWriter =
                new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
        printWriter.print(nachricht);
        printWriter.flush();
    }
}

