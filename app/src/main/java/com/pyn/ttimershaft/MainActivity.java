package com.pyn.ttimershaft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView elvTimerShaft;
    private TimerShaftAdapter adapter;

    private String datas = "[{\"time\":1456761600,\"data\":[{\"title\":\"title\",\"content\":\"新人报到啦啦啦啦啦\"},{\"title\":\"标题咯\",\"content\":\"新人报到\"}]},{\"time\":1455638400,\"data\":[{\"title\":\"我\",\"content\":\"哈哈哈哈哈哈\"}]},{\"time\":1453651200,\"data\":[{\"title\":\"新人报道\",\"content\":\"签到啦\"}]}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elvTimerShaft = (ExpandableListView) findViewById(R.id.elv_timer_shaft);

        List<TimeShaftParentBean> parents = JSON.parseArray(datas, TimeShaftParentBean.class);
        adapter = new TimerShaftAdapter(MainActivity.this, parents);
        elvTimerShaft.setAdapter(adapter);
        // 遍历所有group,将所有项设置成默认展开
        for (int i = 0; i < parents.size(); i++) {
            elvTimerShaft.expandGroup(i);
        }
        elvTimerShaft.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }
}
