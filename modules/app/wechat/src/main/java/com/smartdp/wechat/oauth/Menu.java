/**
 * 微信公众平台开发模式(JAVA) SDK
 * @author pengfenglong
 * 
 */
package com.smartdp.wechat.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.smartdp.wechat.util.HttpKit;

/**
 * 菜单,可以将accessToken
 * 存储在session或者memcache中
 * @author L.cm
 * @date 2013-11-5 下午3:17:33
 */
@SuppressWarnings("unchecked")
public class Menu {

    /**
     * 创建菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
	public boolean createMenu(String accessToken,String params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken, params);
        Map<String, Object> map = JSON.parseObject(jsonStr,Map.class);
        return "0".equals(map.get("errcode").toString());
    }
    
    /**
     * 查询菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public Map<String, Object> getMenuInfo(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
        Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
        return map;
    }
    
    /**
     * 删除自定义菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public boolean deleteMenu(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
        Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
        return "0".equals(map.get("errcode").toString());
    }
}
