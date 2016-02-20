/**
 * 微信公众平台开发模式(JAVA) SDK
 * @author pengfenglong
 * 
 */
package com.smartdp.wechat.inf;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.smartdp.core.service.impl.BaseServiceImpl;
import com.smartdp.core.utils.ServletContextHolder;
import com.smartdp.lifequery.Query;
import com.smartdp.lifequery.idcard.IdCardQuery;
import com.smartdp.lifequery.lbs.LBSQuery;
import com.smartdp.lifequery.phone.PhoneAttributionQuery;
import com.smartdp.lifequery.weather.CityCodeConfig;
import com.smartdp.lifequery.weather.WeatherUtil;
import com.smartdp.wechat.bean.Articles;
import com.smartdp.wechat.bean.InMessage;
import com.smartdp.wechat.bean.NewsOutMessage;
import com.smartdp.wechat.bean.OutMessage;
import com.smartdp.wechat.bean.TextOutMessage;
import com.smartdp.wechat.model.WxMessage;
import com.smartdp.wechat.util.Cache;

public class DefaultMessageProcessingHandlerImpl implements
		MessageProcessingHandler {

	private final Logger LOGGER = Logger
			.getLogger(DefaultMessageProcessingHandlerImpl.class);

	private OutMessage outMessage;

	@Override
	public void allType(InMessage msg) {

		// 保存接收到的消息
		try {

			BaseServiceImpl baseService = (BaseServiceImpl) WebApplicationContextUtils
					.getRequiredWebApplicationContext(
							ServletContextHolder.getServletContext()).getBean(
							"baseService");
			WxMessage message = new WxMessage();
			message.setMsgType(msg.getMsgType());
			message.setContent(msg.getContent());
			message.setCreateTime(new Timestamp(System.currentTimeMillis()));
			message.setFromUserName(msg.getFromUserName());
			message.setMsgId(msg.getMsgId());
			message.setToUserName(msg.getToUserName());
			message.setDescription(msg.getDescription());
			message.setErrorCount(msg.getErrorCount());
			message.setEvent(message.getEvent());
			message.setEventKey(msg.getEventKey());
			message.setFilterCount(msg.getFilterCount());
			message.setFormat(msg.getFormat());
			message.setLabel(msg.getLabel());
			message.setLatitude(msg.getLatitude());
			message.setLocation_X(msg.getLocation_X());
			message.setLocation_Y(msg.getLocation_Y());
			message.setLongitude(msg.getLongitude());
			message.setMediaId(msg.getMediaId());
			message.setPicUrl(msg.getPicUrl());
			message.setPrecision(msg.getPrecision());
			message.setRecognition(msg.getRecognition());
			message.setScale(msg.getScale());
			message.setSentCount(msg.getSentCount());
			message.setStatus(msg.getStatus());
			message.setTicket(msg.getTicket());
			message.setTitle(msg.getTitle());
			message.setTotalCount(msg.getTotalCount());
			message.setUrl(msg.getUrl());
			baseService.save(message);
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public String helpContent() {
		StringBuffer sBuff = new StringBuffer();
		sBuff.append("【请求帮助】输入“帮助”或者”？“").append("\n");
		sBuff.append("【进入微官网】输入“首页”").append("\n");
		sBuff.append("【天气查询】例如”深圳天气“").append("\n");
		sBuff.append("【手机归属地查询】例如”手机13812345678“").append("\n");
		sBuff.append("【身份证查询】例如”身份证123456789123456789“").append("\n");
		sBuff.append("【周边信息查询】请先发送位置给我们,再按照提示输入需要查询的内容\n");
		sBuff.append("\n\n");
		sBuff.append("我们将推出更多功能，敬请关注！");
		return sBuff.toString();
	}

	@Override
	public void textTypeMsg(InMessage msg) {
		String content = msg.getContent();
		if ("首页".equals(content)) {
			NewsOutMessage out = new NewsOutMessage();
			// out.setDescription("微官网");
			// out.setPicUrl("");
			// out.setTitle("微官网");
			// out.setUrl("http://weixinext.sinaapp.com/template/wap/index.html");
			List<Articles> articles = new ArrayList<Articles>();
			Articles a1 = new Articles();
			a1.setTitle("微OS");
			a1.setDescription("百事通，行天下！");
			a1.setUrl("http://weixinext.sinaapp.com/webos/index.html");
			//a1.setPicUrl("");
			articles.add(a1);
			Articles a2 = new Articles();
			a2.setTitle("公众账号新闻");
			//a1.setPicUrl("");
			a2.setUrl("http://weixinext.sinaapp.com/template/wx/index.html");
			articles.add(a2);
			Articles a3 = new Articles();
			a3.setTitle("CMS新闻");
			a3.setUrl("http://weixinext.sinaapp.com/template/cms/index.html");
			articles.add(a3);
			out.setArticles(articles);
			setOutMessage(out);
		} else if ("帮助".equals(content) || "?".equals(content)
				|| "？".equals(content)) {
//			TextOutMessage out = new TextOutMessage();
//			out.setContent(helpContent());
//			setOutMessage(out);
			
			NewsOutMessage out = new NewsOutMessage();
			List<Articles> articles = new ArrayList<Articles>();
			Articles a1 = new Articles();
			a1.setTitle("帮助信息");
			a1.setDescription(helpContent());
			a1.setUrl("http://weixinext.sinaapp.com/template/wap/index.html?site=1");
			articles.add(a1);
			out.setArticles(articles);
			setOutMessage(out);
		} else if (content.indexOf("天气") != -1) {
			String city = content.split("天气")[0];
			String cityCode = CityCodeConfig.getInstance().cityAndCodeMap
					.get(city);
			String s = city + ":\n";
			try {
				s += WeatherUtil.getWeather(cityCode);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TextOutMessage out = new TextOutMessage();
			out.setContent(s);
			setOutMessage(out);

		} else if (content.indexOf("手机") != -1) {
			String phone = content.split("手机")[1];
			StringBuffer sBuff = new StringBuffer();
			Query query = new PhoneAttributionQuery();
			Map map = query.getJsonObjectResult(phone);
			sBuff.append("手机号码：").append(phone).append("\n");
			sBuff.append("归属地：").append(map.get("carrier"));
			TextOutMessage out = new TextOutMessage();
			out.setContent(sBuff.toString());
			setOutMessage(out);

		} else if (content.indexOf("身份证") != -1) {
			String idCard = content.split("身份证")[1];
			StringBuffer sBuff = new StringBuffer();
			Query query = new IdCardQuery();
			Map map = (Map) query.getJsonObjectResult(idCard).get("result");
			sBuff.append("身份证号：").append(idCard).append("\n");
			sBuff.append("生日：").append(map.get("born")).append("\n");
			sBuff.append("性别：").append(map.get("sex")).append("\n");
			sBuff.append("地址：").append(map.get("att"));
			TextOutMessage out = new TextOutMessage();
			out.setContent(sBuff.toString());
			setOutMessage(out);

		} else if (content.indexOf("附近") != -1) {
			String input = content.split("附近")[1];
			// SaeMemcache mc = new SaeMemcache();
			// mc.init();
			// InMessage locationInfo = (InMessage)
			// mc.get(msg.getFromUserName());
			InMessage locationInfo = (InMessage) Cache.getInstance().get(
					msg.getFromUserName());
			if (locationInfo != null) {
				LBSQuery query = new LBSQuery();
				//"{\"status\":0,\"message\":\"ok\",\"results\":[{\"name\":\"都季商务快捷酒店\",\"location\":{\"lat\":39.917603,\"lng\":116.413349},\"address\":\"南河沿大街华龙街\",\"street_id\":\"f60a15ed3d7c8ef5bb17e70a\",\"telephone\":\"(010)85116665\",\"uid\":\"f60a15ed3d7c8ef5bb17e70a\",\"detail_info\":{\"distance\":848,\"type\":\"hotel\",\"tag\":\"王府井/东单快捷酒店\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=f60a15ed3d7c8ef5bb17e70a&output=html&source=placeapi_v2\",\"price\":\"258.0\",\"overall_rating\":\"4.3\",\"service_rating\":\"4.4\",\"facility_rating\":\"4.1\",\"hygiene_rating\":\"4.2\",\"image_num\":\"7\",\"comment_num\":\"3837\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"北京鸿炜亿家连锁酒店(王府井店)\",\"location\":{\"lat\":39.924506,\"lng\":116.414177},\"address\":\"北京市东城区灯市口西街36号\",\"street_id\":\"9dbbd6ef93b7a9e621022066\",\"telephone\":\"(010)65136833,(010)65235677\",\"uid\":\"9dbbd6ef93b7a9e621022066\",\"detail_info\":{\"distance\":1368,\"type\":\"hotel\",\"tag\":\"王府井/东单\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=9dbbd6ef93b7a9e621022066&output=html&source=placeapi_v2\",\"price\":\"243.0\",\"overall_rating\":\"4.3\",\"service_rating\":\"4.1\",\"facility_rating\":\"4.1\",\"hygiene_rating\":\"4.2\",\"image_num\":\"9\",\"groupon_num\":4,\"comment_num\":\"654\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"中联鑫华酒店前门店\",\"location\":{\"lat\":39.905987,\"lng\":116.392607},\"address\":\"西城区前门西大街甲10号\",\"street_id\":\"c21db9e5e95ba09a00ff2dd5\",\"telephone\":\"(010)63026066\",\"uid\":\"c21db9e5e95ba09a00ff2dd5\",\"detail_info\":{\"distance\":1395,\"type\":\"hotel\",\"tag\":\"和平门/前门三星级\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=c21db9e5e95ba09a00ff2dd5&output=html&source=placeapi_v2\",\"price\":\"271.0\",\"overall_rating\":\"4.1\",\"service_rating\":\"4.0\",\"facility_rating\":\"3.7\",\"hygiene_rating\":\"4.0\",\"image_num\":\"10\",\"groupon_num\":1,\"comment_num\":\"611\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"紫金宫饭店\",\"location\":{\"lat\":39.916807,\"lng\":116.413368},\"address\":\"北京市东城区华龙街86号（贵宾楼向北100米）\",\"street_id\":\"3f826ed3affb665fd1f49e3b\",\"telephone\":\"(010)65130088\",\"uid\":\"3f826ed3affb665fd1f49e3b\",\"detail_info\":{\"distance\":824,\"type\":\"hotel\",\"tag\":\"王府井/东单三星级\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=3f826ed3affb665fd1f49e3b&output=html&source=placeapi_v2\",\"price\":\"248.0\",\"overall_rating\":\"3.3\",\"service_rating\":\"3.8\",\"facility_rating\":\"3.1\",\"hygiene_rating\":\"3.5\",\"image_num\":\"10\",\"comment_num\":\"355\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"北京富豪宾馆（B座）\",\"location\":{\"lat\":39.928033,\"lng\":116.416531},\"address\":\"北京市东城区王府井大街45号（面对人民艺术剧院）\",\"street_id\":\"da403456ce5722d7e311038c\",\"telephone\":\"(010)65237788\",\"uid\":\"da403456ce5722d7e311038c\",\"detail_info\":{\"distance\":1800,\"type\":\"hotel\",\"tag\":\"东四三星级\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=da403456ce5722d7e311038c&output=html&source=placeapi_v2\",\"price\":\"244.0\",\"overall_rating\":\"4.1\",\"service_rating\":\"4.0\",\"facility_rating\":\"3.7\",\"hygiene_rating\":\"4.2\",\"image_num\":\"8\",\"groupon_num\":1,\"comment_num\":\"697\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"沐阳时尚宾馆连锁第106店(王府井店)\",\"location\":{\"lat\":39.921988,\"lng\":116.415046},\"address\":\"北京市东城区东安门大街45号\",\"street_id\":\"6308169567ac0091e31103ce\",\"telephone\":\"(010)66514811\",\"uid\":\"6308169567ac0091e31103ce\",\"detail_info\":{\"distance\":1221,\"type\":\"hotel\",\"tag\":\"王府井/东单\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=6308169567ac0091e31103ce&output=html&source=placeapi_v2\",\"price\":\"127.0\",\"overall_rating\":\"3.2\",\"service_rating\":\"3.3\",\"facility_rating\":\"2.9\",\"hygiene_rating\":\"3.0\",\"image_num\":\"3\",\"groupon_num\":1,\"comment_num\":\"234\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"飘HOME连锁酒店（王府井店）\",\"location\":{\"lat\":39.921988,\"lng\":116.415046},\"address\":\"北京东城区东城区东安门大街43号（中国儿童剧院正对面，王府井金钱豹向东50米）\",\"street_id\":\"9f97b1c5b753bf1fb64f543c\",\"telephone\":\"(010)57305888\",\"uid\":\"9f97b1c5b753bf1fb64f543c\",\"detail_info\":{\"distance\":1221,\"type\":\"hotel\",\"tag\":\"王府井/东单快捷酒店\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=9f97b1c5b753bf1fb64f543c&output=html&source=placeapi_v2\",\"price\":\"246.0\",\"overall_rating\":\"3.9\",\"service_rating\":\"3.8\",\"facility_rating\":\"3.9\",\"hygiene_rating\":\"3.9\",\"image_num\":\"8\",\"comment_num\":\"4678\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"东华饭店\",\"location\":{\"lat\":39.924603,\"lng\":116.415298},\"address\":\"东城区王府井地区灯市口西街32号（灯市口西街与北官场胡同交汇处）\",\"street_id\":\"d991e1161f90fe4c1f389434\",\"telephone\":\"(010)65257531\",\"uid\":\"d991e1161f90fe4c1f389434\",\"detail_info\":{\"distance\":1438,\"type\":\"hotel\",\"tag\":\"王府井/东单\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=d991e1161f90fe4c1f389434&output=html&source=placeapi_v2\",\"price\":\"242.0\",\"overall_rating\":\"4.0\",\"service_rating\":\"4.0\",\"facility_rating\":\"3.6\",\"hygiene_rating\":\"3.6\",\"image_num\":\"9\",\"comment_num\":\"1507\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"万程华府酒店\",\"location\":{\"lat\":39.921794,\"lng\":116.414537},\"address\":\"北京市东城区东安门大街53号(新东安购物广场附近)\",\"street_id\":\"f3c2373ca8e5d54bd22dd60a\",\"telephone\":\"(010)51209588\",\"uid\":\"f3c2373ca8e5d54bd22dd60a\",\"detail_info\":{\"distance\":1174,\"type\":\"hotel\",\"tag\":\"王府井/东单三星级\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=f3c2373ca8e5d54bd22dd60a&output=html&source=placeapi_v2\",\"price\":\"238.0\",\"overall_rating\":\"3.6\",\"service_rating\":\"3.6\",\"facility_rating\":\"3.2\",\"hygiene_rating\":\"3.3\",\"image_num\":\"11\",\"comment_num\":\"2455\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}},{\"name\":\"北京府右街宾馆\",\"location\":{\"lat\":39.918633,\"lng\":116.386794},\"address\":\"北京市西城区太仆寺街21号（近西单商场后面）\",\"street_id\":\"3a0fbba85f3bec589ee32105\",\"telephone\":\"(010)66052277\",\"uid\":\"3a0fbba85f3bec589ee32105\",\"detail_info\":{\"distance\":1522,\"type\":\"hotel\",\"tag\":\"三星级\",\"detail_url\":\"http://api.map.baidu.com/place/detail?uid=3a0fbba85f3bec589ee32105&output=html&source=placeapi_v2\",\"price\":\"263.0\",\"overall_rating\":\"4.2\",\"service_rating\":\"4.1\",\"facility_rating\":\"4.1\",\"hygiene_rating\":\"4.3\",\"image_num\":\"12\",\"comment_num\":\"1850\",\"favorite_num\":\"0\",\"checkin_num\":\"0\"}}]}"
				Map map = query.getJsonObjectResult(input + ","
						+ locationInfo.getLocation_X() + ","
						+ locationInfo.getLocation_Y());
				if ("0".equals(map.get("status").toString())) {
					NewsOutMessage out = new NewsOutMessage();
					List<Articles> articles = new ArrayList<Articles>();
					Articles a0 = new Articles();
					a0.setTitle("发现您身边的兴趣点");
					// a0.setDescription("");
					a0.setUrl("http://weixinext.sinaapp.com/template/wap/index.html?site=1");
					a0.setPicUrl("https://res.wx.qq.com/mpres/htmledition/images/bg/bg_logo1df61f.png");
					articles.add(a0);
					JSONArray results = (JSONArray) map.get("results");
					for (int i = 0; i < results.size(); i++) {
						JSONObject result = results.getJSONObject(i);
						// System.out.println("名称:"+result.get("name"));
						// System.out.println("纬度:"+result.getJSONObject("location").getString("lat"));
						// System.out.println("经度:"+result.getJSONObject("location").getString("lng"));
						// System.out.println("地址:"+result.get("address"));
						// System.out.println("电话:"+result.get("telephone"));
						// System.out.println("距离:"+result.getJSONObject("detail_info").getString("distance"));
						// System.out.println("详细介绍:"+result.getJSONObject("detail_info").getString("detail_url"));

						Articles a1 = new Articles();
						String title = result.get("name").toString();
						if (result.getJSONObject("detail_info") != null) {
							title += "\n距离约"
									+ result.getJSONObject("detail_info")
											.getString("distance") + "米";
							if (result.getJSONObject("detail_info").get(
									"detail_url") != null) {
								a1.setUrl(result.getJSONObject("detail_info")
										.get("detail_url").toString());
							}
						}
						a1.setTitle(title);
						// a1.setDescription("");

						// a1.setPicUrl("http://developer.baidu.com/map/static/img/index/search.png");
						articles.add(a1);
					}

					out.setArticles(articles);
					setOutMessage(out);

				} else {
					TextOutMessage out = new TextOutMessage();
					out.setContent("抱歉，未查询到周边相关信息！");
					setOutMessage(out);
				}
			} else {
				TextOutMessage out = new TextOutMessage();
				out.setContent("请先发送位置信息给我们！");
				setOutMessage(out);
			}
		} else {
			TextOutMessage out = new TextOutMessage();
			out.setContent("您的请求我们暂时无法回复您，请关注我们的后续功能！");
			setOutMessage(out);
		}

	}

	@Override
	public void locationTypeMsg(InMessage msg) {
		String fromUserName = msg.getFromUserName();
		// SaeMemcache mc = new SaeMemcache();
		// mc.init();
		// if (mc.get(fromUserName) == null) {
		// mc.set(fromUserName, msg);
		// } else {
		// mc.replace(fromUserName, msg);
		// }

		Cache.getInstance().set(fromUserName, msg);
		TextOutMessage out = new TextOutMessage();
		out.setContent("您的位置消息已经收到，请输入关键字查询周边信息，例如附近酒店、附近银行。必须以“附近“开头!");
		setOutMessage(out);
	}

	@Override
	public void imageTypeMsg(InMessage msg) {
		TextOutMessage out = new TextOutMessage();
		out.setContent("您的图片消息已经收到！");
		setOutMessage(out);
	}

	@Override
	public void videoTypeMsg(InMessage msg) {
		TextOutMessage out = new TextOutMessage();
		out.setContent("您的视频消息已经收到！");
		setOutMessage(out);
	}

	@Override
	public void voiceTypeMsg(InMessage msg) {
		TextOutMessage out = new TextOutMessage();
		out.setContent("您的语音消息已经收到！");
		setOutMessage(out);
	}

	@Override
	public void linkTypeMsg(InMessage msg) {
		TextOutMessage out = new TextOutMessage();
		out.setContent("您的链接消息已经收到！");
		setOutMessage(out);
	}

	@Override
	public void eventTypeMsg(InMessage msg) {
		if ("subscribe".equals(msg.getEvent())) {
			// 关注
//			TextOutMessage out = new TextOutMessage();
//			out.setContent(helpContent());
//			setOutMessage(out);
			NewsOutMessage out = new NewsOutMessage();
			List<Articles> articles = new ArrayList<Articles>();
			Articles a1 = new Articles();
			a1.setTitle("谢谢您的关注");
			a1.setDescription(helpContent());
			a1.setUrl("http://weixinext.sinaapp.com/template/wap/index.html?site=1");
			articles.add(a1);
			out.setArticles(articles);
			setOutMessage(out);
		} else if ("unsubscribe".equals(msg.getEvent())) {
			// 取消关注
		} else {
			TextOutMessage out = new TextOutMessage();
			out.setContent("您的事件消息已经收到！");
			setOutMessage(out);
		}

	}

	@Override
	public void setOutMessage(OutMessage outMessage) {
		this.outMessage = outMessage;
	}

	@Override
	public void afterProcess(InMessage inMessage, OutMessage outMessage) {
	}

	@Override
	public OutMessage getOutMessage() {
		return outMessage;
	}
}
