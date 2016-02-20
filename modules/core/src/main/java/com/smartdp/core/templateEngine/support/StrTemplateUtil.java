/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartdp.core.templateEngine.support;

import java.io.StringWriter;

import com.smartdp.core.templateEngine.Context;
import com.smartdp.core.templateEngine.MergeTemplateException;
import com.smartdp.core.templateEngine.Template;
import com.smartdp.core.templateEngine.TemplateEngine;


/** 
 * 
 * @author pengfenglong
 *
 */
public class StrTemplateUtil {
	private StrTemplateUtil(){
		
	}
	
	public static String merge(String pTemplateStr, Context pContext) throws MergeTemplateException{
		if ( pTemplateStr == null ){
			return null;
		}
		if ( pContext == null ){
			return pTemplateStr;
		} 
		Template template = new DefaultTemplate();
		template.setResource(DefaultTemplate.STR_PREFIX + pTemplateStr);
		StringWriter out = new StringWriter();
		TemplateEngine engine = TemplateEngineFactory.getInstance(TemplateType.VELOCITY);
		engine.mergeTemplate(template, pContext, out);
		return out.getBuffer().toString();
	}
}
