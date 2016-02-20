package com.smartdp.weixin.api.pojo;

import java.util.List;

/**
 * 图文消息表单
 * @author pengfenglong
 *
 */
public class ImgTextMsgForm extends MsgForm {
	protected String type = "10";
	protected String error = "false";
	protected String count;
	protected String AppMsgId = "";
	protected String token;
	protected String ajax = "1";
	protected String lang = "zh_CN";
	protected String t = "ajax-response";
	protected String sub = "create";
	protected List<Piece> pieces;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

	public String getAppMsgId() {
		return AppMsgId;
	}

	public void setAppMsgId(String appMsgId) {
		AppMsgId = appMsgId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
		if (null != pieces)
			this.count = pieces.size() + "";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static class Piece {
		protected String title;
		protected String digest;
		protected String content;
		protected String fileid;
		protected ImgMsgForm img;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getDigest() {
			return digest;
		}

		public void setDigest(String digest) {
			this.digest = digest;
		}

		public String getFileid() {
			return fileid;
		}

		public void setFileid(String fileid) {
			this.fileid = fileid;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public ImgMsgForm getImg() {
			return img;
		}

		public void setImg(ImgMsgForm img) {
			this.img = img;
		}
	}
}
