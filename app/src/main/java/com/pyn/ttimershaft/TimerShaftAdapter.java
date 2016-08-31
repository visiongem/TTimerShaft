package com.pyn.ttimershaft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * describtion:
 * Created by pengyn on 2016/7/27.
 */
public class TimerShaftAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater = null;
    private Context mContext;
    private List<TimeShaftParentBean> timeShaftParentBeans;

    public TimerShaftAdapter(Context context, List<TimeShaftParentBean> timeShaftBeans) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.timeShaftParentBeans = timeShaftBeans;
        this.mContext = context;
    }

    @Override
    public int getGroupCount() {
        return timeShaftParentBeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String data = timeShaftParentBeans.get(groupPosition).getData();
        List<TimeShaftChildBean> childBeans = JSON.parseArray(data, TimeShaftChildBean.class);
        return childBeans.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return timeShaftParentBeans.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String data = timeShaftParentBeans.get(groupPosition).getData();
        List<TimeShaftChildBean> childBeans = JSON.parseArray(data, TimeShaftChildBean.class);
        return childBeans.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupHolder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_parent, null);
            groupHolder = new GroupViewHolder();
            groupHolder.tvDay = (TextView) convertView.findViewById(R.id.tv_day);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }
        String time = timestamp2StrTime(timeShaftParentBeans.get(groupPosition).getTime());
        groupHolder.tvDay.setText(time);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childHolder = null;
        TimeShaftChildBean childBean = (TimeShaftChildBean) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_child, null);
            childHolder = new ChildViewHolder();
            childHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            childHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildViewHolder) convertView.getTag();
        }
        childHolder.tvTitle.setText(childBean.getTitle());
        childHolder.tvContent.setText(childBean.getContent());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupViewHolder {
        TextView tvDay;
    }

    private class ChildViewHolder {
        TextView tvTitle;
        TextView tvContent;
    }

    public String timestamp2StrTime(String timestamp) {

        String result = "";

        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        long time = Long.valueOf(timestamp);
        result = sdf.format(new Date(time * 1000L));
        return result;

    }
}
