package de.university.server.save;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Data {

    private HashMap<String,Socket> sockets = new HashMap<String,Socket>();
    private HashMap<String,Integer> score = new HashMap<String, Integer>();
    private ArrayList<String> users = new ArrayList<String>();

    public void addNewUser(String s, Socket socket) {
        sockets.put(s,socket);
        users.add(s);
    }
    public void replaceScore(String user, int value) {
        score.replace(user,value);
    }

    public String ScoresToString(){
        String msg = "ping-";
        for (String s:users) {
            msg += s+"_"+score.get(s)+"-";
        }
        return msg;
    }

    public Socket[] getSocketList() {
        ArrayList<Socket> temp = new ArrayList<Socket>();
        for (String s:users) {
            temp.add(sockets.get(s));
        }
        Socket[] out = new Socket[temp.size()];
        for (Socket s:temp) {
            out[temp.indexOf(s)] = s;
        }

        return out;
    }
}
