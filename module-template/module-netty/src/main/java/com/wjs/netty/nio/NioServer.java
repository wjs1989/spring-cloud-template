package com.wjs.netty.nio;

import io.netty.channel.ServerChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServer implements Runnable {
    int port;
    Selector selector;
    ServerSocketChannel serverSocketChannel;
    boolean start = false;

    NioServer(int port) {
        this.port = port;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();

            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(this.port));

            //注册监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        start = true;
    }


    @Override
    public void run() {
        while (true) {
            if (!start) {
                Thread.yield();
                continue;
            }

            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext(); ) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void handleInput(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            SocketChannel accept = channel.accept();
            accept.configureBlocking(false);
            accept.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer bf = ByteBuffer.allocate(1024);
            int read = 0 ;
            try {
                read = channel.read(bf);
            }catch (IOException e){
                channel.close();
                key.cancel();
                throw e;
            }
            if (read > 0) {
                bf.flip();
                byte[] bytes = new byte[bf.remaining()];
                bf.get(bytes);

                String message = new String(bytes, "UTF-8");
                System.out.println("get message : " + message);

                String response = "server send message : hello";
                byte[] respBytes = response.getBytes();

                bf.clear();
                bf.put(respBytes);
                bf.flip();

                channel.write(bf);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new NioServer(10086)).start();
    }
}
