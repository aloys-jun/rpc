package org.rpc.server;

import org.rpc.api.ISayHello;

/**
 * 具体实现服务的类
 * @author XXJ
 *
 * 2019年3月12日 下午7:41:46
 */
public class SayHelloImpl implements ISayHello {

	@Override
	public String sayHello(String name) {
		return "Hello," + name;

	}

}
