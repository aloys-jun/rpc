package org.rpc.po;

import java.io.Serializable;

public class RequestData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3866469063627295476L;
	private String interfaceName;
	private String methodName;
	private Class<?>[] parameterTypes;
	private Object[] parameters;
	
	
	
	public RequestData() {
		
	}



	/**
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return interfaceName;
	}



	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}



	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}



	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}



	/**
	 * @return the parameterTypes
	 */
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}



	/**
	 * @param parameterTypes the parameterTypes to set
	 */
	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}



	/**
	 * @return the parameters
	 */
	public Object[] getParameters() {
		return parameters;
	}



	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	
}
