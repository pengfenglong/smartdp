package com.smartdp.weixin.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.codec.Base64;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.weixin.api.Weixin;
import com.smartdp.weixin.api.pojo.Fan;
import com.smartdp.weixin.api.pojo.MsgForm;
import com.smartdp.weixin.api.pojo.TextMsgForm;
import com.smartdp.weixin.model.Member;
import com.smartdp.weixin.utils.WeixinConstant;

/**
 * 微信会员
 * 
 * @author pengfenglong
 * 
 */
@Component("weixin-MemberAction")
public class MemberAction extends CrudActionSupport<Member> {

	private static final long serialVersionUID = -5779218995720922696L;

	private Member model;

	public void prepareModel() {
		if (id != null) {
			model = (Member) baseService.get(id);
		} else {
			model = new Member();
		}
	}

	public Member getModel() {
		return model;
	}

	public String listAll() {
		List<Member> members = baseService.getAll();
		result = members;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Member> memberPage = baseService
				.findPage(getPage(), getFilters());
		savePage(memberPage);
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		if (!StringUtils.isEmpty(getParameter("id"))) {
			long id = getParameterLong("id");
			model.setId(id);
		} else {
			if (baseService.findBy("weixinloginAccount",
					model.getWeixinloginAccount()).size() > 0) {
				resultJson.setSuccess(false);
				resultJson.setMsg("该微信登陆账号已经注册");
			} else {
				// 生成微信接口接入url
				// String mark = new
				// String(Base64.decode(model.getWeixinloginAccount().getBytes()));
				Random rd = new Random();
				String mark = String.valueOf(rd.nextDouble());
				model.setMark(String.valueOf(rd.nextDouble()));
				Weixin wx = new Weixin(model.getWeixinloginAccount(),
						model.getWeixinLoginPwd());
				if (wx._login()) {
					// wx.configInterfaceParam(WeixinConstant.WEIXIN_API_URL +
					// mark , WeixinConstant.WEIXIN_API_TOKEN);
					model.setCreateDate(new Timestamp(System
							.currentTimeMillis()));
					baseService.save(model);
				} else {
					resultJson.setSuccess(false);
					resultJson.setMsg(wx.getLoginErrMsg());
				}
			}

		}
		result = resultJson;
		return SUCCESS;
	}

	@Override
	public String delete() {
		String ids = getParameter("ids");
		baseService.deleteByIds(ids);
		result = resultJson;
		return SUCCESS;
	}

	/**
	 * 粉丝列表
	 * 
	 * @return
	 */
	public String listFans() {
		List<Fan> fans = new ArrayList<Fan>();
		String wxId = getRequest().getParameter("wxId");
		if (StringUtils.isNotEmpty(wxId)) {
			Member m = (Member) baseService.get(Long.parseLong(wxId));
			Weixin wx = new Weixin(m.getWeixinloginAccount(),
					m.getWeixinLoginPwd());
			fans = wx.getFans();
			String nick_name = getRequest().getParameter("nick_name");
			if (StringUtils.isNotEmpty(nick_name)) {
				List<Fan> temp = new ArrayList<Fan>();
				for (Fan fan : fans) {
					if (fan.getNick_name().indexOf(nick_name) != -1) {
						temp.add(fan);
					}
				}
				fans = temp;
			}
		}
		result = fans;
		return SUCCESS;

	}
	
	/**
	 * 发送消息
	 * @return
	 */
	public String sendMsg(){
		String wxId = getRequest().getParameter("wxId");
		String fanId = getRequest().getParameter("fanId");
		String content = getRequest().getParameter("content");
		if (StringUtils.isNotEmpty(wxId)) {
			Member m = (Member) baseService.get(Long.parseLong(wxId));
			Weixin wx = new Weixin(m.getWeixinloginAccount(),
					m.getWeixinLoginPwd());
			MsgForm form = new TextMsgForm();
			form.setContent(content);
			wx.sendSingleMsg(form, fanId);
		}
		result = resultJson;
		return SUCCESS;
		
	}

}
