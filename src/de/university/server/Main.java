package de.university.server;

import de.university.server.connect.Connect;
import de.university.server.save.Data;
import de.university.server.timer.Timer;

public class Main {


    public static void main(String[] args){
        System.out.println("started");
        //init
        Data data = new Data();
        //connect
        Connect connect = new Connect(data);
        //save
        //timer
        new Timer(connect,data).start();
    }
}
