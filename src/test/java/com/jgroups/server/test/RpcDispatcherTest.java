package com.jgroups.server.test;

import java.lang.reflect.Method;

import org.jgroups.JChannel;
import org.jgroups.blocks.MethodCall;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.blocks.RpcDispatcher;
import org.jgroups.stack.IpAddress;

import com.jgroups.server.ServerObject;

public class RpcDispatcherTest {

    JChannel           channel;
    RpcDispatcher      disp;


    public  int print(int number) throws Exception {
  
    	System.out.println(number);
        return number * 2;

    }


    public void start() throws Exception {
        RequestOptions options=new RequestOptions(ResponseMode.GET_ALL, 5000);
        channel=new JChannel("udp.xml");

        channel.connect("dms-cluster");
        System.out.println(channel.getAddress());

        
//        disp=new RpcDispatcher(channel,new Rpc());
//        Method method = WClassUtil.findMethod(Rpc.class,"findUser",new Class[]{String.class});
//        MethodCall call=new MethodCall(method,new Object[]{"00008888"});
//        Object callRemoteMethod = disp.callRemoteMethod(channel.getAddress(), call, options);
//        System.out.println(callRemoteMethod);
        
        
        RpcDispatcher rpcDispatcher = new RpcDispatcher(channel, new ServerObject());
          Method method2 = ServerObject.class.getMethod("getUserName");
        MethodCall call2=new MethodCall(method2);
        IpAddress ipAddress = new IpAddress("127.0.0.1", 10194);
        Object callRemoteMethod2 = rpcDispatcher.callRemoteMethod(channel.getAddress(), call2, options);
        System.out.println("============>>"+callRemoteMethod2);
          

        }




    public static void main(String[] args) throws Exception {

        new RpcDispatcherTest().start();

    }

}
