package org.ecust.server.serial;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
//import org.ecust.server.serial.SerialAddress;
//import org.ecust.server.serial.SerialConnector;
import org.ecust.server.serial.SerialAddress.DataBits;
import org.ecust.server.serial.SerialAddress.FlowControl;
import org.ecust.server.serial.SerialAddress.Parity;
import org.ecust.server.serial.SerialAddress.StopBits;

public class SerialDialog {
    public void Test() {

    }

    public static void main(String[] args) {

        // 新建一个串口连接

        SerialConnector connector = new SerialConnector();
        // 设置串口 handler
        connector.setHandler(new SerialHandler());//DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl
        // 配置串口
        SerialAddress portAddress = new SerialAddress("COM1", 115200, DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
        ConnectFuture future = connector.connect(portAddress);


        try {
            future.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        IoSession sessin = future.getSession();
//  sessin.getFilterChain().addFirst("first",  new addfilter());
        // IoSessionConfig sc=sessin.getService().getSessionConfig();
        //sessin.setAttribute("comname", "COM1");

        String s = "ABCDEFFFFFFFF";
        //s=s.replace(" ", "");
        //System.out.println(s);
        //byte[]b=Convert.hexStringToBytes(s);
        //sessin.write(Convert.byteToIoBuffer(b, b.length));
        sessin.write(Convert.stringToIoBuffer("hello"));
        connector.dispose();
    }

    public void serialDialogs(String serialData) {
        System.out.println("SerialData :" + serialData);
        // 新建一个串口连接

        SerialConnector connector = new SerialConnector();
        // 设置串口 handler
        connector.setHandler(new SerialHandler());//DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl
        // 配置串口
        SerialAddress portAddress = new SerialAddress("COM1", 115200, DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
        ConnectFuture future = connector.connect(portAddress);


        try {
            future.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        IoSession sessin = future.getSession();
//  sessin.getFilterChain().addFirst("first",  new addfilter());
        // IoSessionConfig sc=sessin.getService().getSessionConfig();
        //sessin.setAttribute("comname", "COM1");

        String s = "ABCDEFFFFFFFF";
        //s=s.replace(" ", "");
        //System.out.println(s);
        //byte[]b=Convert.hexStringToBytes(s);
        //sessin.write(Convert.byteToIoBuffer(b, b.length));
        sessin.write(Convert.stringToIoBuffer(serialData));
        connector.dispose();
        sessin.closeNow();


    }
}