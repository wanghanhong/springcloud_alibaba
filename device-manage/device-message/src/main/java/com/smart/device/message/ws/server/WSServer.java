package com.smart.device.message.ws.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 ** 文件名：ChatServer.java
 ** 主要作用：TODO
 *@author 囚徒困境
 *创建日期：2014年12月30日
 */
public class WSServer {
	public final ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

	public final EventLoopGroup workerGroup = new NioEventLoopGroup();

	public static Channel channel ;
	
	public ChannelFuture start(InetSocketAddress address){
		ServerBootstrap boot = new ServerBootstrap();
		boot.group(workerGroup).channel(NioServerSocketChannel.class).childHandler(createInitializer(group));

		ChannelFuture f = boot.bind(address).syncUninterruptibly();
		channel = f.channel();
		return f;
	}

	protected ChannelHandler createInitializer(ChannelGroup group2) {
		return new ServerInitializer(group2);
	}
	
	public void destroy(){
		if(channel != null){
			channel.close();
		}else{
			group.close();
		}
		workerGroup.shutdownGracefully();
	}

	public static void WSServer() {
		try {
			WSServer server = new WSServer();
			ChannelFuture f = server.start(new InetSocketAddress(9101));
			System.out.println("server start................");
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					server.destroy();
				}
			});
			f.channel().closeFuture().syncUninterruptibly();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WSServer.WSServer();
	}


}
