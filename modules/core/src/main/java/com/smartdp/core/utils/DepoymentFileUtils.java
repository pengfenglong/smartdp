/**
 * FileUtils.java
 * com.acpframework.core.util
 *
 * Function： TODO 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0		 2011-7-24 		T410i
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
*/

package com.smartdp.core.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:FileUtils
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   T410i
 * @version  
 * @since    Version 1.0
 * @Date	 2011-7-24		下午10:01:59
 *
 * @see 	 
 */
public abstract class DepoymentFileUtils
{
	private static final String DEFAULT_DEPLOPYMENT_NAME = "deploy.xml";
	
	public static List<String> getDeploymentLocations()
	{
		List<String> deploymentLocations = new ArrayList<String>();
		String classPath = ClassUtil.getDefaultClassLoader().getResource(".").getPath();
		File classPathDirectory = new File(classPath);
		if (classPathDirectory.isDirectory())
		{
			File files[] = classPathDirectory.listFiles(new FileFilter(){
				@Override
				public boolean accept(File pathname)
				{
					if (pathname.isDirectory())
					{
						return true;
					}
					return false;
				}
			});
			
			for(File file : files)
			{
				String dPath = file.getAbsolutePath() + FileUtils.File_SEPARATOR + "deploy" + FileUtils.File_SEPARATOR + DEFAULT_DEPLOPYMENT_NAME;
				try{
					File dFile = new File(dPath);
					if (dFile.exists())
					{
						deploymentLocations.add(dPath);
					}
				}
				catch(Exception ex)
				{
					
				}
			}
			
		}
		return deploymentLocations;
	}
}

