package com.ollien.volumecontrol;
import android.util.Log;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * Created by Nick on 3/19/14.
 */
public class VolumeTransmit extends AsyncTask<String,Void,Void> {
    DatagramSocket socket;
    InetAddress ip;

    public void setAddress(String ipAddr, int port){
        try{
            ip=InetAddress.getByName(ipAddr);
        }
        catch (UnknownHostException e){
            e.printStackTrace();
            Log.e("VolumeRemote","Failed to connect to " + ipAddr);
        }
        try{
            socket = new DatagramSocket();
            socket.connect(ip,port);
        }
        catch (SocketException e){
            e.printStackTrace();
            Log.e("VolumeRemote","Socket exception on "+ipAddr);
        }

    }

    public void send(String s){
        try{
            socket.send(new DatagramPacket(s.getBytes(),s.getBytes().length));
        }
        catch (IOException e){
            e.printStackTrace();
            Log.e("VolumeRemote","IOexception on sending file" );
        }
    }

    @Override
    protected Void doInBackground(String... strings) {
        if (strings.length!=2){
            Log.e("VolumeRemote","Failed to run. !=2");
            return null;
        }
        setAddress(strings[0],Integer.valueOf(strings[1]));

        return null;
    }
}
