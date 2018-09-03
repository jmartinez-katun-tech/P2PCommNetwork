/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2papp.NET;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usyario
 */
public class MsgTransmitter extends Thread {
    String sMessage, sHostName;
    int iPort;
    
    public MsgTransmitter()
    {
        
    }
    
    public MsgTransmitter(String sMessage, String sHostName, int iPort)
    {
        this.sMessage = sMessage;
        this.sHostName = sHostName;
        this.iPort = iPort;
    }
    
    @Override
    public void run()
    {
        try {
            Socket oSocket = new Socket(sHostName, iPort);
            oSocket.getOutputStream().write(sMessage.getBytes());
            oSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(MsgTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
