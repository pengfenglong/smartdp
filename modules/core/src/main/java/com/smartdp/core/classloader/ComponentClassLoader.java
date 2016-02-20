package com.smartdp.core.classloader;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 组件类加载器
 * 
 * @author peng
 * 
 */
public class ComponentClassLoader extends URLClassLoader {

	public ComponentClassLoader(ClassLoader parent, File componentDir)
			throws MalformedURLException {
		super(new URL[] { componentDir.toURI().toURL() }, parent);
		if (componentDir.listFiles() != null) {
			for (File file : componentDir.listFiles()) {
				// 将class加入到类路径
				addURL(new File(file.getAbsolutePath() + "\\java").toURL());

				// 将jar包或者zip包加入到类路径
				File jarsDir = new File(file.getAbsolutePath() + "\\java");
				File[] jars = jarsDir.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						boolean accept = false;
						String smallName = name.toLowerCase();
						if (smallName.endsWith(".jar")) {
							accept = true;
						} else if (smallName.endsWith(".zip")) {
							accept = true;
						}
						return accept;
					}
				});

				if (jars == null) {
					continue;
				}

				for (int i = 0; i < jars.length; i++) {
					if (jars[i].isFile()) {
						addURL(jars[i].toURI().toURL());
					}
				}

			}
		}

	}

}
