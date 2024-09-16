package com.smart.device.message.ws.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by YANGTAO on 2019/11/17 0017.
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    private static Map<String, ChannelHandlerContext> channelHandlerContextConcurrentHashMap = new ConcurrentHashMap<>();

    private static final Map<String, String> replyMap = new ConcurrentHashMap<>();
    static {
        replyMap.put("在吗", "在");
    }

    public void messageReceived(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception{
        channelHandlerContextConcurrentHashMap.put(channelHandlerContext.channel().toString(), channelHandlerContext);
        // http
        if (msg instanceof FullHttpRequest){
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) msg);
        }else if (msg instanceof WebSocketFrame){ // WebSocket
            handleWebSocketFrame(channelHandlerContext, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception{
        if (channelHandlerContextConcurrentHashMap.size() > 1){
            for (String key : channelHandlerContextConcurrentHashMap.keySet()) {
                ChannelHandlerContext current = channelHandlerContextConcurrentHashMap.get(key);
                if (channelHandlerContext == current)
                    continue;
                current.flush();
            }
        }else {
            // 单条处理
            channelHandlerContext.flush();
        }
    }

    private void handleHttpRequest(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception{
        // 验证解码是否异常
        if (!"websocket".equals(request.headers().get("Upgrade")) || request.decoderResult().isFailure()){
            // todo send response bad
            System.err.println("解析http信息异常");
            return;
        }

        // 创建握手工厂类
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
                "ws:/".concat(channelHandlerContext.channel().localAddress().toString()),
                null,
                false
        );
        handshaker = factory.newHandshaker(request);

        if (handshaker == null)
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channelHandlerContext.channel());
        else
            // 响应握手消息给客户端
            handshaker.handshake(channelHandlerContext.channel(), request);

    }

    private void handleWebSocketFrame(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame){
        // 关闭链路
        if (webSocketFrame instanceof CloseWebSocketFrame){
            handshaker.close(channelHandlerContext.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
            return;
        }

        // Ping消息
        if (webSocketFrame instanceof PingWebSocketFrame){
            channelHandlerContext.channel().write(
                    new PongWebSocketFrame(webSocketFrame.content().retain())
            );
            return;
        }

        // Pong消息
        if (webSocketFrame instanceof PongWebSocketFrame){
            // todo Pong消息处理
        }

        // 二进制消息
        if (webSocketFrame instanceof BinaryWebSocketFrame){
            // todo 二进制消息处理
        }

        // 拆分数据
        if (webSocketFrame instanceof ContinuationWebSocketFrame){
            // todo 数据被拆分为多个websocketframe处理
        }

        // 文本信息处理
        if (webSocketFrame instanceof TextWebSocketFrame){
            // 推送过来的消息
            String  msg = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println(String.format("%s 收到消息 : %s", new Date(), msg));

            String responseMsg = "";
            if (channelHandlerContextConcurrentHashMap.size() > 1){
                responseMsg = msg;
                for (String key : channelHandlerContextConcurrentHashMap.keySet()) {
                    ChannelHandlerContext current = channelHandlerContextConcurrentHashMap.get(key);
                    if (channelHandlerContext == current)
                        continue;
                    Channel channel = current.channel();
                    channel.write(
                            new TextWebSocketFrame(responseMsg)
                    );
                }
            }else {
                // 自动回复
                responseMsg = this.answer(msg);
                if(responseMsg == null)
                    responseMsg = "暂时无法回答你的问题 ->_->";
                System.out.println("回复消息："+responseMsg);
                Channel channel = channelHandlerContext.channel();
                channel.write(
                        new TextWebSocketFrame("【服务端】" + responseMsg)
                );
            }
        }

    }

    private String answer(String msg){
        for (String key : replyMap.keySet()) {
            if (msg.contains(key))
                return replyMap.get(key);
        }
        return null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable){
        throwable.printStackTrace();
        channelHandlerContext.close();
    }

    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise promise) throws Exception {
        channelHandlerContextConcurrentHashMap.remove(channelHandlerContext.channel().toString());
        channelHandlerContext.close(promise);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}

