package com.smartdp.weixin.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.smartdp.weixin.api.pojo.Fan;
import com.smartdp.weixin.api.pojo.ImgTextMsgForm;
import com.smartdp.weixin.api.pojo.LoginJson;
import com.smartdp.weixin.api.pojo.MsgForm;
import com.smartdp.weixin.api.pojo.MsgJson;
import com.smartdp.weixin.api.pojo.MsgType;

/**
 * 微信模拟公众平台后台登陆发送消息
 * 
 * @author pengfenglong
 * 
 */
public class Weixin {

	private final static Log log = LogFactory.getLog(Weixin.class);
	public final static String HOST = "https://mp.weixin.qq.com/";
	public final static String LOGIN_URL = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	public final static String INDEX_URL = "http://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
	public final static String FANS_URL = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
	public final static String LOGOUT_URL = "http://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN";
	public final static String DOWNLOAD_URL = "http://mp.weixin.qq.com/cgi-bin/downloadfile?";
	public final static String VERIFY_CODE = "http://mp.weixin.qq.com/cgi-bin/verifycode?";
	public final static String GROUP_SEND_MSG_URL = "https://mp.weixin.qq.com/cgi-bin/masssend?t=ajax-response";// 群发消息
	public final static String SINGLE_DEND_MSG_URL = "https://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN";// 单发消息
	public final static String SINGLEMSGPAGE_REFERER = "https://mp.weixin.qq.com/cgi-bin/singlesendpage";
	public final static String VIEW_HEAD_IMG = "http://mp.weixin.qq.com/cgi-bin/viewheadimg";
	public final static String GET_IMG_DATA = "http://mp.weixin.qq.com/cgi-bin/getimgdata";
	public final static String GET_REGIONS = "http://mp.weixin.qq.com/cgi-bin/getregions";
	public final static String GET_MESSAGE = "http://mp.weixin.qq.com/cgi-bin/getmessage";
	public final static String OPER_ADVANCED_FUNC = "http://mp.weixin.qq.com/cgi-bin/operadvancedfunc";
	public final static String MASSSEND_PAGE = "http://mp.weixin.qq.com/cgi-bin/masssendpage";
	public final static String FILE_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/filemanagepage";
	public final static String OPERATE_APPMSG = "https://mp.weixin.qq.com/cgi-bin/operate_appmsg";
	public final static String FMS_TRANSPORT = "http://mp.weixin.qq.com/cgi-bin/fmstransport";
	public final static String CONTACT_MANAGE_PAGE = "https://mp.weixin.qq.com/cgi-bin/contactmanage";
	public final static String OPER_SELF_MENU = "http://mp.weixin.qq.com/cgi-bin/operselfmenu";
	public final static String REPLY_RULE_PAGE = "http://mp.weixin.qq.com/cgi-bin/replyrulepage";
	public final static String USER_INFO_PAGE = "http://mp.weixin.qq.com/cgi-bin/userinfopage";
	public final static String DEV_APPLY = "http://mp.weixin.qq.com/cgi-bin/devapply";
	public final static String UPLOAD_MATERIAL = "https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&t=iframe-uploadfile&lang=zh_CN&formId=1";

	public final static String USER_AGENT_H = "User-Agent";
	public final static String REFERER_H = "Referer";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8";

	private HttpClient client = new HttpClient();

	private DefaultHttpClient httpclient = new DefaultHttpClient();

	private Cookie[] cookies;
	private List<org.apache.http.cookie.Cookie> cookieList;
	private String cookiestr;

	private String token;
	private int loginErrCode;
	private String loginErrMsg;
	private int msgSendCode;
	private String msgSendMsg;

	private String loginUser;
	private String loginPwd;
	public boolean isLogin = false;

	public Weixin(String user, String pwd) {
		this.loginUser = user;
		this.loginPwd = pwd;
	}

	public Cookie[] getCookies() {
		return cookies;
	}

	public void setCookies(Cookie[] cookies) {
		this.cookies = cookies;
	}

	public String getCookiestr() {
		return cookiestr;
	}

	public void setCookiestr(String cookiestr) {
		this.cookiestr = cookiestr;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getLoginErrCode() {
		return loginErrCode;
	}

	public void setLoginErrCode(int loginErrCode) {
		this.loginErrCode = loginErrCode;
	}

	public String getLoginErrMsg() {
		return loginErrMsg;
	}

	public void setLoginErrMsg(String loginErrMsg) {
		this.loginErrMsg = loginErrMsg;
	}

	public int getMsgSendCode() {
		return msgSendCode;
	}

	public void setMsgSendCode(int msgSendCode) {
		this.msgSendCode = msgSendCode;
	}

	public String getMsgSendMsg() {
		return msgSendMsg;
	}

	public void setMsgSendMsg(String msgSendMsg) {
		this.msgSendMsg = msgSendMsg;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * 登录,登录失败会重复请求登录
	 */
	public void login() {
		boolean bool = _login();
		while (!bool) {
			String info = "【登录失败】【错误代码：" + this.loginErrMsg + "】【账号："
					+ this.loginUser + "】正在尝试重新登录....";
			log.debug(info);
			System.out.println(info);
			bool = _login();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				bool = _login();
			}

		}
		System.out.println("登陆成功：");
	}

	/**
	 * 发送登录信息,记录cookie，登录状态，token等信息
	 * 
	 * @return
	 */
	public boolean _login_bak() {
		try {

			PostMethod post = new PostMethod(LOGIN_URL);
			post.setRequestHeader(REFERER_H, HOST);
			NameValuePair[] params = new NameValuePair[] {
					new NameValuePair("username", this.loginUser),
					new NameValuePair("pwd", DigestUtils.md5Hex(this.loginPwd
							.getBytes())), new NameValuePair("f", "json"),
					new NameValuePair("imagecode", "") };
			post.setQueryString(params);
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				String ret = post.getResponseBodyAsString();
				LoginJson retcode = JSON.parseObject(ret, LoginJson.class);
				if (retcode.getBase_resp().getRet() == 0) {
					this.cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					for (Cookie c : client.getState().getCookies()) {
						cookie.append(c.getName()).append("=")
								.append(c.getValue()).append(";");
					}
					this.cookiestr = cookie.toString();
					this.isLogin = true;
					this.token = getToken(retcode.getRedirect_url());
					return true;
				}
				int errCode = retcode.getBase_resp().getRet();
				this.loginErrCode = errCode;
				switch (errCode) {
				case -1:
					this.loginErrMsg = "系统错误";
					return false;
				case -2:
					this.loginErrMsg = "帐号或密码错误";
					return false;
				case -3:
					this.loginErrMsg = "密码错误";
					return false;
				case -4:
					this.loginErrMsg = "不存在该帐户";
					return false;
				case -5:
					this.loginErrMsg = "访问受限";
					return false;
				case -6:
					this.loginErrMsg = "需要输入验证码";
					return false;
				case -7:
					this.loginErrMsg = "此帐号已绑定私人微信号，不可用于公众平台登录";
					return false;
				case -8:
					this.loginErrMsg = "邮箱已存在";
					return false;
				case -32:
					this.loginErrMsg = "验证码输入错误";
					return false;
				case -200:
					this.loginErrMsg = "因频繁提交虚假资料，该帐号被拒绝登录";
					return false;
				case -94:
					this.loginErrMsg = "请使用邮箱登陆";
					return false;
				case 10:
					this.loginErrMsg = "该公众会议号已经过期，无法再登录使用";
					return false;
				case 65201:
				case 65202:
					this.loginErrMsg = "成功登陆，正在跳转...";
					return true;
				case 0:
					this.loginErrMsg = "成功登陆，正在跳转...";
					return true;
				default:
					this.loginErrMsg = "未知的返回";
					return false;
				}
			}
		} catch (Exception e) {
			String info = "【登录失败】【发生异常：" + e.getMessage() + "】";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return false;
		}
		return false;
	}

	/**
	 * 发送登录信息,记录cookie，登录状态，token等信息
	 * 
	 * @return
	 */
	public boolean _login() {
		try {
			HttpPost httppost = new HttpPost(LOGIN_URL);
			System.out.println("请求: " + httppost.getRequestLine());

			// post 参数 传递
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			nvps.add(new BasicNameValuePair("username", this.loginUser));
			nvps.add(new BasicNameValuePair("pwd", DigestUtils
					.md5Hex(this.loginPwd.getBytes())));
			nvps.add(new BasicNameValuePair("imagecode", ""));
			httppost.addHeader(REFERER_H, HOST);
			httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post

			HttpResponse response = httpclient.execute(httppost);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();

				String ret = EntityUtils.toString(entity, "utf-8");
				LoginJson retcode = JSON.parseObject(ret, LoginJson.class);
				if (retcode.getBase_resp().getRet() == 0) {
					CookieStore cookieStore = httpclient.getCookieStore();
					this.cookieList = cookieStore.getCookies();
					log.info("微信公众平台登陆cookies：" + this.cookieList.size());
					StringBuffer cookie = new StringBuffer();
					for (org.apache.http.cookie.Cookie c : this.cookieList) {
						cookie.append(c.getName()).append("=")
								.append(c.getValue()).append(";");
					}
					this.cookiestr = cookie.toString();
					this.isLogin = true;
					this.token = getToken(retcode.getRedirect_url());
					return true;
				}
				int errCode = retcode.getBase_resp().getRet();
				this.loginErrCode = errCode;
				switch (errCode) {
				case -1:
					this.loginErrMsg = "系统错误";
					return false;
				case -2:
					this.loginErrMsg = "帐号或密码错误";
					return false;
				case -3:
					this.loginErrMsg = "密码错误";
					return false;
				case -4:
					this.loginErrMsg = "不存在该帐户";
					return false;
				case -5:
					this.loginErrMsg = "访问受限";
					return false;
				case -6:
					this.loginErrMsg = "需要输入验证码";
					return false;
				case -7:
					this.loginErrMsg = "此帐号已绑定私人微信号，不可用于公众平台登录";
					return false;
				case -8:
					this.loginErrMsg = "邮箱已存在";
					return false;
				case -32:
					this.loginErrMsg = "验证码输入错误";
					return false;
				case -200:
					this.loginErrMsg = "因频繁提交虚假资料，该帐号被拒绝登录";
					return false;
				case -94:
					this.loginErrMsg = "请使用邮箱登陆";
					return false;
				case 10:
					this.loginErrMsg = "该公众会议号已经过期，无法再登录使用";
					return false;
				case 65201:
				case 65202:
					this.loginErrMsg = "成功登陆，正在跳转...";
					return true;
				case 0:
					this.loginErrMsg = "成功登陆，正在跳转...";
					return true;
				default:
					this.loginErrMsg = "未知的返回";
					return false;
				}
			}

		} catch (Exception e) {
			String info = "【登录失败】【发生异常：" + e.getMessage() + "】";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return false;
		}
		return false;
	}

	/**
	 * 从登录成功的信息中分离出token信息
	 * 
	 * @param s
	 * @return
	 */
	private String getToken(String s) {
		try {
			if (StringUtils.isBlank(s))
				return null;

			String[] ss = StringUtils.split(s, "?");
			String[] params = null;
			if (ss.length == 2) {
				if (!StringUtils.isBlank(ss[1]))
					params = StringUtils.split(ss[1], "&");
			} else if (ss.length == 1) {
				if (!StringUtils.isBlank(ss[0]) && ss[0].indexOf("&") != -1)
					params = StringUtils.split(ss[0], "&");
			} else {
				return null;
			}
			for (String param : params) {
				if (StringUtils.isBlank(param))
					continue;
				String[] p = StringUtils.split(param, "=");
				if (null != p && p.length == 2
						&& StringUtils.equalsIgnoreCase(p[0], "token"))
					return p[1];
			}
		} catch (Exception e) {
			String info = "【解析Token失败】【发生异常：" + e.getMessage() + "】";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return null;
		}
		return null;
	}

	/**
	 * 获取首页
	 * 
	 * @throws org.apache.commons.httpclient.HttpException
	 * 
	 * @throws java.io.IOException
	 */
	public void index() throws HttpException, IOException {
		GetMethod get = new GetMethod(INDEX_URL);
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader("Cookie", this.cookiestr);
		int status = client.executeMethod(get);
		if (status == HttpStatus.SC_OK) {
			System.out.println(get.getResponseBodyAsString());
		}
	}

	/**
	 * 登出操作
	 * 
	 * @throws org.apache.commons.httpclient.HttpException
	 * 
	 * @throws java.io.IOException
	 */
	public void logout() throws HttpException, IOException {
		GetMethod get = new GetMethod(LOGOUT_URL);
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader("Cookie", this.cookiestr);
		int status = client.executeMethod(get);
		if (status == HttpStatus.SC_OK) {
			System.err.println("-----------注销登录成功-----------");
		}
	}

	/**
	 * 获取验证码
	 * 
	 * @throws org.apache.commons.httpclient.HttpException
	 * 
	 * @throws java.io.IOException
	 */
	public InputStream code() throws HttpException, IOException {
		GetMethod get = new GetMethod(VERIFY_CODE);
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader("Cookie", this.cookiestr);
		NameValuePair[] params = new NameValuePair[] {
				new NameValuePair("username", this.loginUser),
				new NameValuePair("r", "1365318662649") };
		get.setQueryString(params);
		int status = client.executeMethod(get);
		if (status == HttpStatus.SC_OK) {
			return get.getResponseBodyAsStream();
		}
		return null;
	}

	/**
	 * 获得粉丝数量,如果解析成功会返回粉丝数，如果解析失败则返回-1
	 * 
	 * @return
	 */
	public List<Fan> getFans() {
		List<Fan> fans = new ArrayList<Fan>();
		try {
			String paramStr = "?t=user/index&token=" + this.token
					+ "&lang=zh_CN&pagesize=10000&pageidx=0&type=0&groupid=0";
			if (!this.isLogin) {
				this._login();
			}
			if (this.isLogin) {
				GetMethod get = new GetMethod(CONTACT_MANAGE_PAGE + paramStr);
				get.setRequestHeader(REFERER_H, INDEX_URL);
				get.setRequestHeader("Cookie", this.cookiestr);
				int status = client.executeMethod(get);
				if (status == HttpStatus.SC_OK) {
					String text = get.getResponseBodyAsString();
					String temp = "friendsList : ({\"contacts\":";
					int start = text.indexOf(temp);
					int end = text.indexOf("}).contacts,");
					String txt = text.substring(start + temp.length(), end);
					fans = JSON.parseArray(txt, Fan.class);
				}
			}
		} catch (Exception e) {
			String info = "【获取粉丝数失败】【可能登录过期】";
			System.err.println(info);
			log.debug(info);
			log.info(info);
		}
		return fans;
	}

	/**
	 * 构建发送消息参数
	 * 
	 * @param form
	 * @param fanId
	 * @return
	 */
	public List<Part> buildSendMsgParams(MsgForm form, String fanId) {
		List<Part> params = new ArrayList<Part>();
		// 图文
		if (form.getType() == MsgType.IMGTEXT) {
			// ImgTextMsgList list = this.getImgTextMsgList();
			// List<ImgTextMsgList.ImgTextMsg> imgTextMsgs = list.getList();
			// if (null != imgTextMsgs && !imgTextMsgs.isEmpty()) {
			// ImgTextMsgList.ImgTextMsg imgTextMsg = imgTextMsgs.get(0);
			// if (null != imgTextMsg) {
			// form.setAppmsgid(imgTextMsg.getAppId());
			// form.setFid(imgTextMsg.getAppId());
			// }
			// }
			// if (StringUtils.isBlank(form.getAppmsgid())
			// || StringUtils.isBlank(form.getFid())) {
			// this.msgSendMsg = "参数错误:appmsgid为空";
			// }
			String[] ids = this.getImgTextMsgList();
			params.add(new StringPart("appmsgid", ids[0]));
			params.add(new StringPart("fid", ids[1]));

		}
		// 不是文本的需要上传文件
		else if (form.getType() != MsgType.TEXT) {
			String fileid = uploadFile(form);
			params.add(new StringPart("fid", fileid, UTF_8));
			params.add(new StringPart("fileid", fileid, UTF_8));
		}
		params.add(new StringPart("content", form.getContent(), UTF_8));
		params.add(new StringPart("type", form.getType(), UTF_8));
		params.add(new StringPart("error", "false", UTF_8));
		params.add(new StringPart("imagecode", "", UTF_8));
		params.add(new StringPart("tofakeid", fanId, UTF_8));
		params.add(new StringPart("token", this.token, UTF_8));
		params.add(new StringPart("ajax", "1", UTF_8));
		return params;
	}

	/**
	 * 单发消息
	 * 
	 * @param form
	 * @param type
	 */
	public boolean sendSingleMsg(MsgForm form, String fanId) {
		try {
			PostMethod post = new PostMethod(SINGLE_DEND_MSG_URL);
			post.setRequestHeader("Cookie", this.cookiestr);
			post.setRequestHeader(REFERER_H, SINGLEMSGPAGE_REFERER);

			List<Part> params = buildSendMsgParams(form, fanId);
			Part[] parts = new Part[params.size()];
			MultipartRequestEntity entity = new MultipartRequestEntity(
					params.toArray(parts), post.getParams());
			post.setRequestEntity(entity);
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				String text = post.getResponseBodyAsString();
				System.out.println(text);
				return true;
			}
		} catch (Exception e) {
			String info = "【单发信息失败】" + e.getMessage();
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return false;
		}
		return false;
	}

	/**
	 * 群发
	 * 
	 * @param form
	 * @param type
	 * @return
	 */
	public boolean sendGroupMsg(MsgForm form) {
		try {
			if (!this.isLogin) {
				this._login();
			}
			if (this.isLogin) {
				form.setToken(this.token);
				PostMethod post = new PostMethod(GROUP_SEND_MSG_URL);
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				post.setRequestHeader(REFERER_H, INDEX_URL);
				post.setRequestHeader("Cookie", this.cookiestr);
				List<Part> params = buildSendMsgParams(form, "");
				Part[] parts = new Part[params.size()];
				MultipartRequestEntity entity = new MultipartRequestEntity(
						params.toArray(parts), post.getParams());
				post.setRequestEntity(entity);
				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					try {
						MsgJson ret = JSON.parseObject(text, MsgJson.class);
						this.msgSendCode = ret.getRet();
						switch (this.msgSendCode) {
						case 0:
							this.msgSendMsg = "发送成功";
							return true;
						case -2:
							this.msgSendMsg = "参数错误，请仔细检查";
							return false;
						case 64004:
							this.msgSendMsg = "今天的群发数量已到，无法群发";
							return false;
						case -20000:
							this.msgSendMsg = "请求被禁止，请仔细检查token是否合法";
							return false;
						default:
							this.msgSendMsg = "未知错误!";
							return false;
						}
					} catch (Exception e) {
						String info = "【群发信息失败】【解析json错误】" + e.getMessage()
								+ "\n\t【文本:】\n\t" + text;
						System.err.println(info);
						log.debug(info);
						log.info(info);
						return false;
					}
				}
			}
		} catch (Exception e) {
			String info = "【群发信息失败】" + e.getMessage();
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return false;
		}
		return false;
	}

	private String updateImgErr;

	public String getUpdateImgErr() {
		return updateImgErr;
	}

	public void setUpdateImgErr(String updateImgErr) {
		this.updateImgErr = updateImgErr;
	}

	/**
	 * 获得ticket
	 * 
	 * @return
	 */
	public String getTicket() {
		String url = "https://mp.weixin.qq.com/cgi-bin/appmsg?begin=0&count=10&t=media/appmsg_list&type=10&action=list&token="
				+ this.token + "&lang=zh_CN";
		GetMethod get = new GetMethod(url);
		get.setRequestHeader(REFERER_H, INDEX_URL);
		get.setRequestHeader("Cookie", this.cookiestr);
		try {
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String ret = get.getResponseBodyAsString();
				return ret.substring(ret.indexOf("ticket:\"") + 8,
						ret.indexOf("ticket:\"") + 48);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 上传文件
	 * 
	 * @param form
	 * @return
	 */
	public String uploadFile(MsgForm form) {
		try {
			if (!this.isLogin)
				this.isLogin();
			if (this.isLogin) {
				String ticket_id = this.cookiestr.substring(
						this.cookiestr.indexOf("slave_user=") + 11,
						this.cookiestr.indexOf(";slave_sid"));
				String ticket = getTicket();
				String url = "https://mp.weixin.qq.com/cgi-bin/filetransfer";
				PostMethod post = new PostMethod(url);
				post.setRequestHeader("Cookie", this.cookiestr);
				post.setRequestHeader("Referer",
						"https://mp.weixin.qq.com/cgi-bin/filepage");
				String fileName = form.getUploadfile().getName();
				String ext = StringUtils.substring(fileName,
						fileName.indexOf("."), fileName.length());
				FilePart file = new FilePart("file", form.getUploadfile(),
						form.getContent_type() + ext, "UTF-8");
				Part[] parts = new Part[] {
						new StringPart("action", "upload_material"),
						new StringPart("f", "json"),
						new StringPart("ticket_id", ticket_id),
						new StringPart("ticket", ticket),
						new StringPart("token", this.token),
						new StringPart("lang", "zh_CN"), file };
				MultipartRequestEntity entity = new MultipartRequestEntity(
						parts, post.getParams());
				post.setRequestEntity(entity);
				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					System.out.println(text);
					String temp = "\"content\":\"";
					return text.substring(text.indexOf(temp) + temp.length(),
							text.lastIndexOf("\"}"));
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String parseUploadImgText(String text) {
		try {
			if (StringUtils.isBlank(text))
				return null;
			String sub = null;
			int type = 0;
			if (text.indexOf("top.W.upload.suc(") != -1) {
				sub = "top.W.upload.suc(";
				type = 1;
			} else if (text.indexOf("top.W.upload.err(") != -1) {
				sub = "top.W.upload.err(";
				type = 2;
			}
			StringBuffer ret = new StringBuffer();
			for (int i = text.indexOf(sub) + sub.length(), len = text.length(); i < len; i++) {
				char c = text.charAt(i);
				if (c == ')')
					break;
				ret.append(c);
			}
			String result = ret.toString().replaceAll("['|\"]", "");
			String[] s = null;
			switch (type) {
			case 1:
				s = StringUtils.split(result, ",");
				if (null != s && s.length == 4) {
					this.updateImgErr = StringUtils.trim(s[0]);
					return StringUtils.trim(s[3]);
				}
				this.updateImgErr = "未知错误";
				return null;
			case 2:
				s = StringUtils.split(result, ",");
				if (null != s && s.length == 3) {
					this.updateImgErr = StringUtils.trim(s[0]);
					return null;
				}
				this.updateImgErr = "未知错误";
				return null;
			default:
				this.updateImgErr = "未知错误";
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	private String imgTextSendErr = "";
	private int imgTextSendCode;

	public String getImgTextSendErr() {
		return imgTextSendErr;
	}

	public void setImgTextSendErr(String imgTextSendErr) {
		this.imgTextSendErr = imgTextSendErr;
	}

	public int getImgTextSendCode() {
		return imgTextSendCode;
	}

	public void setImgTextSendCode(int imgTextSendCode) {
		this.imgTextSendCode = imgTextSendCode;
	}

	public boolean saveImgText(ImgTextMsgForm form) {
		try {
			if (!this.isLogin)
				this.isLogin();
			if (this.isLogin) {
				form.setToken(this.getToken());
				PostMethod post = new PostMethod(OPERATE_APPMSG);
				post.setRequestHeader(USER_AGENT_H, USER_AGENT);
				post.setRequestHeader(REFERER_H, INDEX_URL);

				post.setRequestHeader("Cookie", this.cookiestr);

				List<Part> params = new ArrayList<Part>();
				params.add(new StringPart("error", form.getError(), UTF_8));
				params.add(new StringPart("count", form.getCount(), UTF_8));
				params.add(new StringPart("AppMsgId", form.getAppMsgId(), UTF_8));
				params.add(new StringPart("token", form.getToken(), UTF_8));
				params.add(new StringPart("ajax", form.getAjax(), UTF_8));
				params.add(new StringPart("lang", form.getLang(), UTF_8));
				params.add(new StringPart("t", form.getT(), UTF_8));
				params.add(new StringPart("sub", form.getSub(), UTF_8));

				int i = 0;
				for (ImgTextMsgForm.Piece piece : form.getPieces()) {
					if (null != piece.getImg()) {
						String fileid = this.uploadFile(piece.getImg());
						if (StringUtils.isBlank(fileid))
							continue;
						piece.setFileid(fileid);
						params.add(new StringPart("title" + i,
								piece.getTitle(), UTF_8));
						params.add(new StringPart("digest" + i, piece
								.getDigest(), UTF_8));
						params.add(new StringPart("content" + i, piece
								.getContent(), UTF_8));
						params.add(new StringPart("fileid" + i, piece
								.getFileid(), UTF_8));
						i++;
					}
				}
				Part[] parts = new Part[params.size()];
				MultipartRequestEntity entity = new MultipartRequestEntity(
						params.toArray(parts), post.getParams());
				post.setRequestEntity(entity);
				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					try {
						MsgJson ret = JSON.parseObject(text, MsgJson.class);
						this.imgTextSendCode = ret.getRet();
						System.out.println(text);
						switch (this.msgSendCode) {
						case 0:
							this.imgTextSendErr = "发送成功";
							return true;
						case -2:
							this.imgTextSendErr = "参数错误，请仔细检查";
							return false;
						case 64004:
							this.imgTextSendErr = "今天的群发数量已到，无法群发";
							return false;
						case -20000:
							this.imgTextSendErr = "请求被禁止，请仔细检查token是否合法";
							return false;
						default:
							this.imgTextSendErr = "未知错误!";
							return false;
						}
					} catch (Exception e) {
						String info = "【群发信息失败】【解析json错误】" + e.getMessage()
								+ "\n\t【文本:】\n\t" + text;
						System.err.println(info);
						log.debug(info);
						log.info(info);
						return false;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getImgTextMsgList() {
		String[] ids = new String[2];
		try {
			if (!this.isLogin)
				this.isLogin();
			if (this.isLogin) {

				GetMethod post = new GetMethod(
						"https://mp.weixin.qq.com/cgi-bin/appmsg");
				post.setRequestHeader(REFERER_H,
						"https://mp.weixin.qq.com/cgi-bin/appmsg");
				post.setRequestHeader("Cookie", this.cookiestr);
				/**
				 * sub=list&type=10&subtype=3&t=wxm-appmsgs-list-new&pagesize=10
				 * &pageidx=0&token=1004476860&lang=zh_CN
				 */
				NameValuePair[] params = new NameValuePair[] {
						new NameValuePair("begin", "0"),
						new NameValuePair("count", "10"),
						new NameValuePair("t", "media/appmsg_list"),
						new NameValuePair("type", "10"),
						new NameValuePair("action", "list"),
						new NameValuePair("token", this.getToken()),
						new NameValuePair("lang", "zh_CN") };
				post.setQueryString(params);

				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					// System.out.println(text);
					Document doc = Jsoup.parse(text);
					Elements eles = doc.select("script");
					for (Element e : eles) {
						String html = e.html();
						System.out.println(html);
						if (html.indexOf("\"app_id\"") != -1) {
							String app_id_s = "\"app_id\"";
							String file_id_s = "\"file_id\"";
							String app_id = html.substring(
									html.indexOf(app_id_s) + app_id_s.length()
											+ 1, html.indexOf(file_id_s) - 1);
							String file_id = html.substring(
									html.indexOf(file_id_s)
											+ file_id_s.length(),
									html.indexOf("\"title\"") - 1);
							ids[0] = app_id;
							ids[1] = file_id;
						}
						// ImgTextMsgList ret = JSON.parseObject(html,
						// ImgTextMsgList.class);
						// return ret;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public String getImgTextMsgsJson_bak() {
		try {
			if (!this.isLogin)
				this.isLogin();
			if (this.isLogin) {

				GetMethod post = new GetMethod(
						"https://mp.weixin.qq.com/cgi-bin/appmsg");
				post.setRequestHeader(REFERER_H,
						"https://mp.weixin.qq.com/cgi-bin/appmsg");
				post.setRequestHeader("Cookie", this.cookiestr);
				/**
				 * sub=list&type=10&subtype=3&t=wxm-appmsgs-list-new&pagesize=10
				 * &pageidx=0&token=1004476860&lang=zh_CN
				 */
				NameValuePair[] params = new NameValuePair[] {
						new NameValuePair("begin", "0"),
						new NameValuePair("count", "100"),
						new NameValuePair("t", "media/appmsg_list"),
						new NameValuePair("type", "10"),
						new NameValuePair("action", "list"),
						new NameValuePair("token", this.getToken()),
						new NameValuePair("lang", "zh_CN") };
				post.setQueryString(params);

				int status = client.executeMethod(post);
				if (status == HttpStatus.SC_OK) {
					String text = post.getResponseBodyAsString();
					// System.out.println(text);
					Document doc = Jsoup.parse(text);
					Elements eles = doc.select("script");
					for (Element e : eles) {
						String html = e.html();
						if (html.indexOf("\"app_id\"") != -1
								&& html.indexOf("\"file_id\"") != -1
								&& html.indexOf("\"title\"") != -1) {

							String json = html.substring(
									"wx.cgiData = ".length(),
									html.indexOf("};") + 1);
							return json;
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getImgTextMsgsJson(String begin,String count) {
		try {
			if (!this.isLogin)
				this.isLogin();
			if (this.isLogin) {

				HttpPost httpget = new HttpPost(
						"https://mp.weixin.qq.com/cgi-bin/appmsg");
				httpget.setHeader(REFERER_H,
						"https://mp.weixin.qq.com/cgi-bin/appmsg");
				httpget.setHeader("Cookie", this.cookiestr);

				StringEntity reqEntity = new StringEntity(
						"begin="+begin+"&count="+count+"&t=media/appmsg_list&type=10&action=list&lang=zh_CN&token="
								+ this.getToken());
				reqEntity.setContentType("application/x-www-form-urlencoded");
				httpget.setEntity(reqEntity);

				HttpResponse response = httpclient.execute(httpget);

				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();

					String text = EntityUtils.toString(entity, "utf-8");
					// System.out.println(text);
					Document doc = Jsoup.parse(text);
					Elements eles = doc.select("script");
					for (Element e : eles) {
						String html = e.html();
						if (html.indexOf("\"app_id\"") != -1
								&& html.indexOf("\"file_id\"") != -1
								&& html.indexOf("\"title\"") != -1) {

							String json = html.substring(
									"wx.cgiData = ".length(),
									html.indexOf("};") + 1);
							return json;
						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	/**
	 * 页面跳转
	 * 
	 * @param url
	 */

	public void redirect(String url) {
		if (url.indexOf("https://") == -1)
			url = HOST + url;
		try {
			if (this.isLogin) {
				GetMethod get = new GetMethod(url);
				get.setRequestHeader(USER_AGENT_H, USER_AGENT);
				get.setRequestHeader(REFERER_H, INDEX_URL);
				get.setRequestHeader("Cookie", this.cookiestr);
				int status = client.executeMethod(get);
				if (status == HttpStatus.SC_OK) {
					System.err.println("正在跳转.....");
					System.out.println(get.getResponseBodyAsString());
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 使用方法:<br>
	 * new Weixin()对象，先登录再取粉丝数或者群发。<br>
	 * 群发需要传入一个MsgForm参数： 默认发送文本消息，发送给中国区<br>
	 * 所以不需要再设置其他参数，调用setContent将需要发送的内容填充就OK<br>
	 * 内容中的超链接可以直接发送不用使用<a>标签
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String LOGIN_USER = "150521330@qq.com";
		String LOGIN_PWD = "pfl@123";

		// String LOGIN_USER = "imoke@sina.cn";
		// String LOGIN_PWD = "aimoke";
		Weixin wx = new Weixin(LOGIN_USER, LOGIN_PWD);
		wx.login();
		//System.out.println(wx.getImgTextMsgsJson());
		// // wx.getMsgTextList();
		/*
		 * wx.getCookiestr(); List<Fan> fans = wx.getFans(); //
		 * System.out.println(fans);
		 * 
		 * // for(Fan fan : fans){ // if(fan.getNick_name().equals("Owen Van")){
		 * // System.out.println(fan); // } // // }
		 * 
		 * MsgForm form = null; String fanId = "1103331500";
		 * 
		 * // 单发文本消息 form = new TextMsgForm(); form.setContent("test4444"); //
		 * wx.sendSingleMsg(form, fanId);
		 * 
		 * // 单发图片消息 form = new ImgMsgForm(); form.setUploadfile(new
		 * File("E:\\test.jpg")); // wx.sendSingleMsg(form, fanId);
		 * 
		 * // 单发声音消息 form = new VoiceMsgForm(); form.setUploadfile(new
		 * File("E:\\test.mp3")); // wx.sendSingleMsg(form, fanId);
		 * 
		 * // 单发视频消息 form = new VideoMsgForm(); form.setUploadfile(new
		 * File("E:\\test.avi")); // wx.sendSingleMsg(form, fanId);
		 * 
		 * // 单发图文消息 File file = new File("E:\\test.jpg"); ImgMsgForm img = new
		 * ImgMsgForm(); img.setUploadfile(file); ImgTextMsgForm _form = new
		 * ImgTextMsgForm(); List<ImgTextMsgForm.Piece> pieces = new
		 * ArrayList<ImgTextMsgForm.Piece>();
		 * 
		 * ImgTextMsgForm.Piece piece = new ImgTextMsgForm.Piece();
		 * piece.setContent("第一个第一个第一个第一个第一个第一个"); piece.setDigest("第一个第一个");
		 * piece.setImg(img); piece.setTitle("第一个"); pieces.add(piece);
		 * 
		 * ImgTextMsgForm.Piece piece1 = new ImgTextMsgForm.Piece();
		 * piece1.setContent("第二个第二个第二个第二个第二个第二个"); piece1.setDigest("第二个第二个");
		 * piece1.setImg(img); piece1.setTitle("第二个"); pieces.add(piece1);
		 * 
		 * ImgTextMsgForm.Piece piece2 = new ImgTextMsgForm.Piece();
		 * piece2.setContent("第三个第三个第三个第三个第三个第三个"); piece2.setDigest("第三个第三个");
		 * piece2.setImg(img); piece2.setTitle("第三个"); pieces.add(piece2);
		 * 
		 * ImgTextMsgForm.Piece piece3 = new ImgTextMsgForm.Piece();
		 * piece3.setContent("第三个第三个第三个第三个第三个第三个"); piece3.setDigest("第三个第三个");
		 * piece3.setImg(img); piece3.setTitle("第三个"); pieces.add(piece3);
		 * 
		 * _form.setPieces(pieces); // wx.saveImgText(_form); //
		 * wx.sendSingleMsg(_form, fanId);
		 * 
		 * // 群发消息 // wx.sendGroupMsg(_form);
		 */
	}
}
