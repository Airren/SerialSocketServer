package org.ecust.server.serial;

import javafx.scene.paint.Stop;
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

    public static String[] serialPortParam;


    public static void main(String[] args) {
        String name = serialPortParam[0];
        int bauds = Integer.parseInt(serialPortParam[1]);
        DataBits dataBits = null;
        switch (Integer.parseInt(serialPortParam[2])) {
            case 8:
                dataBits = DataBits.DATABITS_8;
                break;
            case 7:
                dataBits = DataBits.DATABITS_7;
                break;
            case 6:
                dataBits = DataBits.DATABITS_6;
                break;
            case 5:
                dataBits = DataBits.DATABITS_5;
                break;
            default:
                dataBits = DataBits.DATABITS_8;
                break;
        }

        StopBits stopBits = null;
        switch (Integer.parseInt(serialPortParam[3])) {
            case 1:
                stopBits = StopBits.BITS_1;
            case 2:
                stopBits = StopBits.BITS_2;
            default:
                stopBits = StopBits.BITS_1;
        }

        Parity parity = null;
        String[] paritys = {"None", "", "", "", ""};
        if (String.valueOf(serialPortParam[4]).equals("None")) {
            parity = Parity.NONE;
        } else if (String.valueOf(serialPortParam[4]).equals("EVEN")) {
            parity = Parity.EVEN;
        } else if (String.valueOf(serialPortParam[4]).equals("ODD")) {
            parity = Parity.ODD;
        } else if (String.valueOf(serialPortParam[4]).equals("SPACE")) {
            parity = Parity.SPACE;
        } else if (String.valueOf(serialPortParam[4]).equals("MARK")) {
            parity = Parity.MARK;
        } else {
            parity = Parity.NONE;
        }

        FlowControl flowControl = null;
        if (String.valueOf(serialPortParam[5]).equals("None")) {
            flowControl = FlowControl.NONE;
        } else if (String.valueOf(serialPortParam[4]).equals("MARK")) {
            flowControl = FlowControl.XONXOFF_IN_OUT;
        } else if (String.valueOf(serialPortParam[4]).equals("MARK")) {
            flowControl = FlowControl.RTSCTS_IN_OUT;
        } else {
            flowControl = FlowControl.NONE;
        }


        // 新建一个串口连接

        SerialConnector connector = new SerialConnector();
        // 设置串口 handler
        connector.setHandler(new SerialHandler());//DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl
        // 配置串口
        //SerialAddress portAddress = new SerialAddress("COM1", 115200, DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
        SerialAddress portAddress = new SerialAddress(name, bauds, dataBits, stopBits, parity, flowControl);
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
        String name = serialPortParam[0];
        int bauds = Integer.parseInt(serialPortParam[1]);
        DataBits dataBits = null;
        switch (Integer.parseInt(serialPortParam[2])) {
            case 8:
                dataBits = DataBits.DATABITS_8;
                break;
            case 7:
                dataBits = DataBits.DATABITS_7;
                break;
            case 6:
                dataBits = DataBits.DATABITS_6;
                break;
            case 5:
                dataBits = DataBits.DATABITS_5;
                break;
            default:
                dataBits = DataBits.DATABITS_8;
                break;
        }

        StopBits stopBits = null;
        switch (Integer.parseInt(serialPortParam[3])) {
            case 1:
                stopBits = StopBits.BITS_1;
            case 2:
                stopBits = StopBits.BITS_2;
            default:
                stopBits = StopBits.BITS_1;
        }

        Parity parity = null;

        if (String.valueOf(serialPortParam[4]).equals("None")) {
            parity = Parity.NONE;
        } else if (String.valueOf(serialPortParam[4]).equals("EVEN")) {
            parity = Parity.EVEN;
        } else if (String.valueOf(serialPortParam[4]).equals("ODD")) {
            parity = Parity.ODD;
        } else if (String.valueOf(serialPortParam[4]).equals("SPACE")) {
            parity = Parity.SPACE;
        } else if (String.valueOf(serialPortParam[4]).equals("MARK")) {
            parity = Parity.MARK;
        } else {
            parity = Parity.NONE;
        }

        FlowControl flowControl = null;
        if (String.valueOf(serialPortParam[5]).equals("None")) {
            flowControl = FlowControl.NONE;
        } else if (String.valueOf(serialPortParam[4]).equals("MARK")) {
            flowControl = FlowControl.XONXOFF_IN_OUT;
        } else if (String.valueOf(serialPortParam[4]).equals("MARK")) {
            flowControl = FlowControl.RTSCTS_IN_OUT;
        } else {
            flowControl = FlowControl.NONE;
        }
        System.out.println("SerialData :" + serialData);
        // 新建一个串口连接

        SerialConnector connector = new SerialConnector();
        // 设置串口 handler
        connector.setHandler(new SerialHandler());//DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl
        // 配置串口
        //SerialAddress portAddress = new SerialAddress("COM1", 115200, DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
        SerialAddress portAddress = new SerialAddress(name, bauds, dataBits, stopBits, parity, flowControl);
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

        //s=s.replace(" ", "");
        //System.out.println(s);
        //byte[]b=Convert.hexStringToBytes(s);
        //sessin.write(Convert.byteToIoBuffer(b, b.length));
        sessin.write(Convert.stringToIoBuffer(serialData+"\n"));
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sessin.closeNow();
        connector.dispose();


    }
}