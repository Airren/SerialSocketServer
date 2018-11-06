package org.ecust.server;

//查看本机上所有的串口
import java.util.ArrayList;
import java.util.Enumeration;
import gnu.io.CommPortIdentifier;

public class ListSerialPort {
    ArrayList<String> serialPortName = new ArrayList();
    public void listPorts(){
            Enumeration enumeration=CommPortIdentifier.getPortIdentifiers();
            CommPortIdentifier portId;
            while(enumeration.hasMoreElements()){
                portId=(CommPortIdentifier)enumeration.nextElement();
                if(portId.getPortType()==CommPortIdentifier.PORT_SERIAL){

                    System.out.println("port name :"+portId.getName());
                    serialPortName.add(portId.getName());
                }
            }
        }
        public static void main(String[] args) {
            ListSerialPort vPorts=new ListSerialPort();
            vPorts.listPorts();
        }

}
