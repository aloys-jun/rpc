package org.rpc.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.rpc.api.ISayHello;

import online.rpc.server.ServerRegister;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    
    {
       ISayHello say = new SayHelloImpl();
       ServerRegister server = new ServerRegister(ISayHello.class.getName(),say);
       
       try {
		server.publishServer(new InetSocketAddress("localhost", 12345));
	} catch (IOException e) {

		e.printStackTrace();
	}
    }
}
