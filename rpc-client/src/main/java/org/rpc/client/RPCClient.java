package org.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.rpc.po.RequestData;

public class RPCClient {
	
	@SuppressWarnings("unchecked")
	public<T> T importor(Class<?> serviceClass,InetSocketAddress address){
		return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, new InvocationHandler() {
			
			@SuppressWarnings("resource")
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket socket = new Socket();
				socket.connect(address);
				try(ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
						ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
					
					RequestData data = new RequestData();
					
					data.setInterfaceName(serviceClass.getName());
					data.setMethodName(method.getName());
					data.setParameters(args);
					data.setParameterTypes(method.getParameterTypes());
					output.writeObject(data);
					return input.readObject();
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return null;
				
			}
		});
		

	}

}
