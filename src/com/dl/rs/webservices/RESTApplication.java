package com.dl.rs.webservices;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.core.Application;

public class RESTApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public RESTApplication(){
	     singletons.add(new RestResource());
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
