package org.ecust.server.serial;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class SerialHandler implements IoHandler {

    public static String msg;

    public void sessionCreated(IoSession ioSession) throws Exception {

    }

    public void sessionOpened(IoSession ioSession) throws Exception {

    }

    public void sessionClosed(IoSession ioSession) throws Exception {

    }

    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

    }

    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

    }

    public void messageReceived(IoSession ioSession, Object o) throws Exception {
        msg = Convert.ioBufferToString(o);

        //System.out.println("message Received" + o.toString());
        System.out.println(msg);

    }

    public void messageSent(IoSession ioSession, Object o) throws Exception {
        System.out.println("message sent"+ioSession.getLastReadTime());

    }

    public void inputClosed(IoSession ioSession) throws Exception {

    }
}
