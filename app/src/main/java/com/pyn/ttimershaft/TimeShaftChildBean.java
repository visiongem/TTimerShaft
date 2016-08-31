package com.pyn.ttimershaft;

/**
 * describtion:
 * Created by pengyn on 2016/7/27.
 */
public class TimeShaftChildBean {

    private String _id;     // id
    private String title;   // 标题
    private String content; // 内容

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
