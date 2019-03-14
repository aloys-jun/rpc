package online.rpc.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.rpc.po.RequestData;


/**
 * 服务注册与发布
 * @author XXJ
 *
 * 2019年3月12日 下午7:46:39
 */
public class ServerRegister {
	
	private Map<String,Object> map = new ConcurrentHashMap<>();
	
	private static Executor executor = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
	
	public ServerRegister(String intefaceName,Object instance){
		map.put(intefaceName, instance);
	}
	
	@SuppressWarnings("resource")
	public void publishServer(InetSocketAddress address) throws IOException{
		
		//监听服务端口
		ServerSocket serversocket = new ServerSocket();
		serversocket.bind(address);
		System.out.println("服务已启动。。。。。。");
		while(true){
			executor.execute(new Task(serversocket.accept()));
		}
	}
	
	//具体执行过程
	private class Task implements Runnable {

		private Socket socket;
		
		public Task(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run() {
			
			try(ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())){
				RequestData data = (RequestData) input.readObject();
				//System.out.println(map.get(data.getInterfaceName()).getClass());
				Method method = map.get(data.getInterfaceName()).getClass().getMethod(data.getMethodName(), data.getParameterTypes());
				
				Object result = method.invoke(map.get(data.getInterfaceName()), data.getParameters());
				
				output.writeObject(result);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
	}

}







