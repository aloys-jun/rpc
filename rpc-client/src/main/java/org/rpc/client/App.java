package org.rpc.client;

import java.net.InetSocketAddress;

import org.rpc.api.ISayHello;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       RPCClient client = new RPCClient();
       ISayHello say = client.importor(ISayHello.class, new InetSocketAddress("localhost", 12345));
       
       System.out.println(say.sayHello("Tom"));
    }
}
