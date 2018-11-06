package org.ecust.server.serial;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.ecust.server.ServerHandler;
import org.ecust.server.serial.SerialAddress;
import org.ecust.server.serial.SerialConnector;
import org.ecust.server.serial.SerialAddress.DataBits;
import org.ecust.server.serial.SerialAddress.FlowControl;
import org.ecust.server.serial.SerialAddress.Parity;
import org.ecust.server.serial.SerialAddress.StopBits;

public class Test {
 public void Test(){

 }
 public static void main(String[] args) {
  SerialConnector connector = new SerialConnector();
  connector.setHandler(new SerialHandler());//DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl
  SerialAddress portAddress=new SerialAddress("COM1", 115200,DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
  ConnectFuture future = connector.connect(portAddress);
  try {
   future.await();
  } catch (InterruptedException e) {
   e.printStackTrace();
  }
  IoSession sessin = future.getSession();
//  sessin.getFilterChain().addFirst("first",  new addfilter());
  IoSessionConfig sc=sessin.getService().getSessionConfig();
  sessin.setAttribute("comname", "COM1");
  String s="1111111111111111111111111111111111111111111111111111111111111111111111";
  s=s.replace(" ", "");
  System.out.println(s);
  byte[]b=Convert.hexStringToBytes(s);
  sessin.write(Convert.byteToIoBuffer(b, b.length));
//  sessin.write(s);
  connector.dispose();
 }
 public void serialDialog(String serialData){
  System.out.println("SerialData :"+serialData);

  SerialConnector connector = new SerialConnector();
  connector.setHandler(new SerialHandler());//DataBits dataBits, StopBits stopBits, Parity parity, FlowControl flowControl
  SerialAddress portAddress=new SerialAddress("COM1", 115200,DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
  ConnectFuture future = connector.connect(portAddress);
  try {
   future.await();
  } catch (InterruptedException e) {
   e.printStackTrace();
  }
  IoSession sessin = future.getSession();
//  sessin.getFilterChain().addFirst("first",  new addfilter());
  IoSessionConfig sc=sessin.getService().getSessionConfig();
  sessin.setAttribute("comname", "COM1");
  //String s="1111111111111111111111111111111111111111111111111111111111111111111111";
  String s = serialData;
  s=s.replace(" ", "");
  System.out.println(s);
  byte[]b=Convert.hexStringToBytes(s);
  sessin.write(Convert.byteToIoBuffer(b, b.length));
//  sessin.write(s);
 // sessin.closeNow();

  connector.dispose();
//  sessin.closeNow();

 }
}