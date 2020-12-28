package com.wjs.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioClient {
    String host;
    int port;
    Selector selector;
    SocketChannel socketChannel;

    public NioClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void start() {
        //连接
        try {
            connect();
            while (true) {
                handler();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selector != null) {
                    selector.close();
                }
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void connect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void handler() throws IOException {
        selector.select(1000);
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        for (Iterator<SelectionKey> iterator = selectedKeys.iterator(); iterator.hasNext(); ) {
            SelectionKey key = iterator.next();
            iterator.remove();

            try {
                handler(key);
            } catch (IOException e) {
                if (key != null) {
                    key.cancel();
                    if (key.channel() != null) {
                        key.channel().close();
                    }
                }
                throw e;
            }
        }
    }

    private void handler(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }
        SocketChannel channel = (SocketChannel) key.channel();
        if (key.isConnectable()) {
            //连接事件
            if (channel.finishConnect()) {
                channel.register(selector, SelectionKey.OP_READ);
            } else {
                System.exit(1);
            }
        } else if (key.isReadable()) {
            ByteBuffer bf = ByteBuffer.allocate(1024);
            int read = channel.read(bf);
            if (read > 0) {
                bf.flip();
                byte[] bytes = new byte[bf.remaining()];
                bf.get(bytes);

                String message = new String(bytes);
                System.out.println("get message : " + message);
            } else if (read < 0) {
                System.out.println("close =========== ");
                key.cancel();
                channel.close();
            }
        }

        sendMessage();

    }

    void sendMessage() throws IOException {
        String message = " hello , 我是客户端";

        byte[] bytes = message.getBytes(Charset.forName("UTF-8"));
        ByteBuffer bf = ByteBuffer.allocate(bytes.length);
        bf.put(bytes);
        bf.flip();
        socketChannel.write(bf);
    }

    public static void main(String[] args) {
        new NioClient("127.0.0.1", 10086).start();
    }
}
