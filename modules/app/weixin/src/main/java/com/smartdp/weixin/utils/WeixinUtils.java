package com.smartdp.weixin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * 微信工具类
 * 
 * @author pengfenglong
 */
public class WeixinUtils
{

//    /**
//     * 返回文本处理
//     * (这里用一句话描述这个方法的作用)
//     * @param tuWen
//     * @return
//     */
//    public static String text(TuWen tuWen)
//    {
//        StringBuilder sb = new StringBuilder();
//        sb.append("<xml>");
//        sb.append("<ToUserName><![CDATA[" + tuWen.getFromUserName() + "]]></ToUserName>");
//        sb.append("<FromUserName><![CDATA[" + tuWen.getToUserName() + "]]></FromUserName>");
//        sb.append("<CreateTime>" + System.currentTimeMillis() + "</CreateTime>");
//        sb.append("<MsgType><![CDATA[text]]></MsgType>");
//        sb.append("<Content><![CDATA[" + tuWen.getContent() + "]]></Content>");
//        sb.append("<FuncFlag>0</FuncFlag>");
//        sb.append("</xml>");
//        return sb.toString();
//    }
//
//    /**
//     * 返回图文处理
//     * (这里用一句话描述这个方法的作用)
//     * @param tuWen
//     * @return
//     */
//    public static String huifutuwen(TuWen tuWen)
//    {
//        StringBuilder sb = new StringBuilder();
//        sb.append("<xml>");
//        sb.append("<ToUserName><![CDATA[" + tuWen.getFromUserName() + "]]></ToUserName>");
//        sb.append("<FromUserName><![CDATA[" + tuWen.getToUserName() + "]]></FromUserName>");
//        sb.append("<CreateTime>" + tuWen.getCreateTime() + "</CreateTime>");
//        sb.append("<MsgType><![CDATA[news]]></MsgType>");
//        sb.append("<ArticleCount>" + tuWen.getArticleCount() + "</ArticleCount>");
//        sb.append("<Articles>");
//        for (Item item : tuWen.getArticles())
//        {
//            sb.append("<item>");
//            sb.append("<Title><![CDATA[" + item.getTitle() + "]]></Title>");
//            sb.append("<Description><![CDATA[" + item.getDescription() + "]]></Description>");
//            sb.append("<PicUrl><![CDATA[" + item.getPicUrl() + "]]></PicUrl>");
//            sb.append("<Url><![CDATA[" + item.getUrl() + "]]></Url>");
//            sb.append("</item>");
//        }
//        sb.append("</Articles>");
//        sb.append("<FuncFlag>1</FuncFlag>");
//        sb.append("</xml>");
//        return sb.toString();
//    }
//
//    /**
//     * 处理微信请求数据
//     * (这里用一句话描述这个方法的作用)
//     * @param request
//     * @return
//     * @throws IOException
//     * @throws DocumentException
//     */
//    public static Document handleWeixinRequest(HttpServletRequest request) throws IOException, DocumentException
//    {
//        // =============>取数据
//        InputStream is = request.getInputStream();
//        // 取HTTP请求流长度
//        int size = request.getContentLength();
//        // 用于缓存每次读取的数据
//        byte[] buffer = new byte[size];
//        // 用于存放结果的数组
//        byte[] xmldataByte = new byte[size];
//        int count = 0;
//        int rbyte = 0;
//        // 循环读取
//        while (count < size)
//        {
//            // 每次实际读取长度存于rbyte中
//            rbyte = is.read(buffer);
//            for (int i = 0; i < rbyte; i++)
//            {
//                xmldataByte[count + i] = buffer[i];
//            }
//            count += rbyte;
//        }
//        is.close();
//        String requestStr = new String(xmldataByte, "UTF-8");
//        return DocumentHelper.parseText(requestStr);
//    }
//    
//    public static Member handleWeixinMemberInfo(HttpServletRequest request)
//    {
//        IMemberService memberService = (IMemberService) SpringContextUtil
//                .getBean((IMemberService.SERVICE_ID));
//        int temp = request.getRequestURI().lastIndexOf("/wx")+4;
//        String urlId = request.getRequestURI().substring(temp, request.getRequestURI().length());
//        Member member = memberService.getByUrlId(urlId);
//        return member;
//    }
//    
//    public static Message saveMessage(String msgType, String toUserName, String fromUserName, String msgId, String mes,
//            Member member)
//    {
//      //记录消息
//        Message message = new Message();
//        message.setToUserName(toUserName);
//        message.setFromUserName(fromUserName);
//        message.setMsgId(msgId);
//        message.setMsgType(msgType);
//        message.setCreateTime(new Date());
//        message.setMes(mes);
//        message.setMember(member);
//        
//        IMessageService messageService = (IMessageService) SpringContextUtil
//                .getBean(IMessageService.SERVICE_ID);
//        messageService.saveOrUpdate(message);
//        return message;
//    }
//    
//    public static KeyWord queryKeyWord(IKeyWordService keyWordService, String key, Member member)
//    {
//        return keyWordService.getKeyWordByKeyAndMember(key,member);
//    }
//    
//    public static KeyWord queryWelcomeWord(IKeyWordService keyWordService, Member member)
//    {
//        return queryKeyWord(keyWordService, "欢迎语", member);
//    }
//    
//    public static KeyWord queryUnknowWord(IKeyWordService keyWordService, Member member)
//    {
//        return queryKeyWord(keyWordService, "无法识别", member);
//    } 
//
    /**
     * 
     * (这里用一句话描述这个方法的作用)
     * @param strSrc
     * @return
     */
    public static String Encrypt(String strSrc)
    {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try
        {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1)
            {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
