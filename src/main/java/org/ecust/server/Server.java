package org.ecust.server;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {


    public static void main(String[] args) {

        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        try {
            //设置handler
            acceptor.setHandler(new ServerHandler());
            //设置过滤器
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
            //绑定端口号
            acceptor.bind(new InetSocketAddress(Integer.parseInt(args[0])));
            System.out.println("服务端已启动...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
