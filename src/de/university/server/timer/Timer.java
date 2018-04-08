package de.university.server.timer;

import de.university.server.connect.Connect;
import de.university.server.save.Data;

import java.io.IOException;
import java.net.Socket;

public class Timer extends Thread {
    private Connect connect;
    private Data data;

    public Timer (Connect connect, Data data){
        this.connect = connect;
        this.data = data;
    }

    public void start(){
        while(true){
            try {

                for (Socket s:data.getSocketList()) {
                    connect.schreibeNachricht(s,data.ScoresToString());//communicate with the clients
                }

            } catch (IOException e) {
            }
            //Save.save(); //save everything to txt


            try {
                Thread.sleep(100000);//wait
                System.out.println("next Round");
            } catch (InterruptedException e) {
            }
        }
    }
}
