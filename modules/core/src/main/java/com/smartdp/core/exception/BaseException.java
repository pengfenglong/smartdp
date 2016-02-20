/**
 * BaseException.java
 * com.acpframework.core.exception 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2012-1-23 		Liqi
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
*/

package com.smartdp.core.exception;
/**
 * 所有的模块的异常定义都要继承这个类
 * ClassName:BaseException
 *
 * @author   Liqi
 * @version  1.0.0 
 * @Date	 2012-1-23		上午11:53:40	 
 */
public class BaseException extends RuntimeException
{

    /**
     * （用一句话描述这个变量表示什么）
     *  @version Version 1.0
     */
    
    private static final long serialVersionUID = 1L;
    
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(createFriendlyErrMsg(message));
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**

     * 创建友好的报错信息

     * */

    private static String createFriendlyErrMsg(String msgBody) {

       String prefixStr = "抱歉。";

       String suffixStr = "请稍后再试或与管理员联系！";

       StringBuffer friendlyErrMsg = new StringBuffer();

       friendlyErrMsg.append(prefixStr);

       friendlyErrMsg.append(msgBody);

       friendlyErrMsg.append(suffixStr);

       return friendlyErrMsg.toString();

    }

}

