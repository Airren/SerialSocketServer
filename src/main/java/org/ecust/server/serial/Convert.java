package org.ecust.server.serial;


import java.nio.ByteOrder;
import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;

import sun.nio.cs.ext.EUC_TW.Encoder;

public class Convert {

    public static String ioBufferToString1(IoBuffer iobuffer) {
        System.out.println("message = " + iobuffer + iobuffer.limit());
        iobuffer.flip();    //调换buffer当前位置，并将当前位置设置成0
        byte[] b = new byte[iobuffer.limit()];
        iobuffer.get(b);
        //此处用stringbuffer是因为　String类是字符串常量，是不可更改的常量。而StringBuffer是字符串变量，它的对象是可以扩充和修改的。
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < b.length; i++) {
            System.out.println("====" + b[i]);
            stringBuffer.append((char) b[i]); //可以根据需要自己改变类型
            System.out.println(b[i] + "---------" + i);
        }
        return stringBuffer.toString();
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }



    public static void main(String[] args) throws CharacterCodingException {
        IoBuffer iobuffer = IoBuffer.allocate(8);
        iobuffer.order(ByteOrder.LITTLE_ENDIAN);
//        iobuffer.setAutoExpand(true);
        iobuffer.putChar('z');

        String str = ioBufferToString1(iobuffer);
        System.out.println(str);

    }

    public static String byteToString(byte[] b) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            stringBuffer.append((char) b[i]);
        }
        return stringBuffer.toString();
    }


    public static IoBuffer stringToIoBuffer(String str) {

        byte bt[] = str.getBytes();

        IoBuffer ioBuffer = IoBuffer.allocate(bt.length);
        ioBuffer.put(bt, 0, bt.length);
        ioBuffer.flip();
        return ioBuffer;
    }

    public static IoBuffer byteToIoBuffer(byte[] bt, int length) {

        IoBuffer ioBuffer = IoBuffer.allocate(length);
        ioBuffer.put(bt, 0, length);
        ioBuffer.flip();
        return ioBuffer;
    }

    public static byte[] ioBufferToByte(Object message) {
        if (!(message instanceof IoBuffer)) {
            return null;
        }
        IoBuffer ioBuffer = (IoBuffer) message;
        byte[] b = new byte[ioBuffer.limit()];
        ioBuffer.get(b);
        return b;
    }

    public static String ioBufferToString(Object message) {
        if (!(message instanceof IoBuffer)) {
            return "";
        }
        IoBuffer ioBuffer = (IoBuffer) message;
        byte[] b = new byte[ioBuffer.limit()];
        ioBuffer.get(b);
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < b.length; i++) {

            stringBuffer.append((char) b[i]);
        }
        return stringBuffer.toString();
    }

}

