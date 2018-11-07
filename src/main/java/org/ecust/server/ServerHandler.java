package org.ecust.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import org.ecust.server.serial.Test;

public class ServerHandler extends IoHandlerAdapter {


    @Override
    public void messageReceived(IoSession arg0, Object arg1) throws Exception {
        String msg = (String) arg1;
        System.out.println("messageReceived server: " + msg);
        if (msg.equals("ConnectTest")) {
            //arg0.write("ConnectSuccess");
            ListSerialPort listSerialPort = new ListSerialPort();
            listSerialPort.listPorts();
            String serialPortName = listSerialPort.serialPortName.toString();
            arg0.write(serialPortName.substring(serialPortName.indexOf("[") + 1, serialPortName.indexOf("]")));
        } else if (msg.startsWith("COM")) {
            System.out.println("SUCCESS");

            arg0.write("SUCCESS");


        } else {
            Thread.sleep(100);
            System.out.println("come in Serial");

            Test serialDialog = new Test();


            serialDialog.serialDialog(msg);
            serialDialog = null;
            arg0.write("test");

        }

    }

    @Override
    public void messageSent(IoSession arg0, Object arg1) throws Exception {
        System.out.println("messageSent");
    }


}
