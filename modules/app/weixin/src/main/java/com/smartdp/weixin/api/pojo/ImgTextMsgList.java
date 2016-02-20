package com.smartdp.weixin.api.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息列表
 * @author pengfenglong
 *
 */
public class ImgTextMsgList {
    protected int count;
    protected int totalCount;
    protected String type;
    protected String subtype;
    protected List<ImgTextMsg> list = new ArrayList<ImgTextMsg>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<ImgTextMsg> getList() {
        return list;
    }

    public void setList(List<ImgTextMsg> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ImgTextMsgList{" +
                "count=" + count +
                ", totalCount=" + totalCount +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", list=" + list +
                '}';
    }

    public static class ImgTextMsg {
        protected String appId;
        protected int count;
        protected String time;
        protected List<ImgText> appmsgList = new ArrayList<ImgText>();

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ImgText> getAppmsgList() {
            return appmsgList;
        }

        public void setAppmsgList(List<ImgText> appmsgList) {
            this.appmsgList = appmsgList;
        }

        @Override
        public String toString() {
            return "ImgTextMsg{" +
                    "appId='" + appId + '\'' +
                    ", count=" + count +
                    ", time='" + time + '\'' +
                    ", appmsgList=" + appmsgList +
                    '}';
        }

        public static class ImgText {
            protected String imgURL;
            protected String title;
            protected String desc;
            protected String url;
            protected int ClickCount;
            protected int ClickMemberCount;

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getClickCount() {
                return ClickCount;
            }

            public void setClickCount(int clickCount) {
                ClickCount = clickCount;
            }

            public int getClickMemberCount() {
                return ClickMemberCount;
            }

            public void setClickMemberCount(int clickMemberCount) {
                ClickMemberCount = clickMemberCount;
            }

            @Override
            public String toString() {
                return "ImgText{" +
                        "imgURL='" + imgURL + '\'' +
                        ", title='" + title + '\'' +
                        ", desc='" + desc + '\'' +
                        ", url='" + url + '\'' +
                        ", ClickCount=" + ClickCount +
                        ", ClickMemberCount=" + ClickMemberCount +
                        '}';
            }
        }
    }


}
