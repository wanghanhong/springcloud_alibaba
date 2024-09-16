package com.smart.device.message.ws.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.URI;

public class WSClient {

    private static final String wsuri = "ws://192.168.0.79:9101/ws";

    public static void main(String[] args) throws Exception{
        sendMsg("{'Message':'操作成功','Data':{'1':30,'2':5,'3':15,'4':221,'5':2425},'Code':200}");
    }
    public static void sendMsg(String  msg){
        EventLoopGroup group=new NioEventLoopGroup();
        try {
            Bootstrap boot=new Bootstrap();
            boot.option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_BACKLOG,1024*1024*10)
                    .group(group)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new ChannelHandler[]{new HttpClientCodec(),
                                    new HttpObjectAggregator(1024*1024*10)});
                            p.addLast("hookedHandler", new WebSocketClientHandler());
                        }
                    });
            URI websocketURI = new URI(wsuri);
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            //进行握手
            WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String)null, true,httpHeaders);
            System.out.println("connect");
            final Channel channel= boot.connect(websocketURI.getHost(),websocketURI.getPort()).sync().channel();
            WebSocketClientHandler handler = (WebSocketClientHandler)channel.pipeline().get("hookedHandler");
            handler.setHandshaker(handshaker);
            handshaker.handshake(channel);
            //阻塞等待是否握手成功
            handler.handshakeFuture().sync();

            TextWebSocketFrame frame = new TextWebSocketFrame(msg);
            channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        System.out.println("text send success");
                    }else{
                        System.out.println("text send failed  "+channelFuture.cause().getMessage());
                    }
                }
            });
            System.out.println("channel");

            channel.close();
            handshaker.close(channel,new CloseWebSocketFrame());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }

}
