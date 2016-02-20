package com.smartdp.core.classloader;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;

/**
 * 组件类加载器管理类
 * @author peng
 *
 */
public class ComponentLoaderManager {

	
	public static ClassLoader getComponentClassLoader(ServletContext servletcontext){
		File componentDir = new File(servletcontext.getRealPath("/component"));
		
		ClassLoader parent = findParentClassLoader();
		ClassLoader loader = null;
		try {
			loader = new ComponentClassLoader(parent, componentDir);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		return loader;
	}
	
	
    private static ClassLoader findParentClassLoader() {
        ClassLoader parent = null;
        if (parent == null) {
            parent = ComponentLoaderManager.class.getClassLoader();
            if (parent == null) {
                parent = ClassLoader.getSystemClassLoader();
            }
        }
        return parent;
    }
}
