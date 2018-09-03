/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2papp.NET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usyario
 */
public class MessageListener extends Thread {
    ServerSocket oServSocket;
    WritableGUI oGui;
    int iPort = 7788;
    
    public MessageListener(WritableGUI oGui, int iPort)
    {
        this.oGui = oGui;
        this.iPort = iPort;
        try {
            oServSocket = new ServerSocket(iPort);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public MessageListener()
    {
        try {
            oServSocket = new ServerSocket(iPort);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        Socket oClientSocket;
        
        try {
            while((oClientSocket = oServSocket.accept()) != null)
            {
                InputStream oInputData = oClientSocket.getInputStream();
                BufferedReader oBufferRead = new BufferedReader(new InputStreamReader(oInputData));
                String sLine = oBufferRead.readLine();
                
                if(sLine != null)
                {
                    oGui.write(sLine);
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
