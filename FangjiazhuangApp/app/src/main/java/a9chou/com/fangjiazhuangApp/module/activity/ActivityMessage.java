package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.MessageAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseActivity;
import a9chou.com.fangjiazhuangApp.module.dao.Message;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zm9316 on 2017/12/29.
 */

public class ActivityMessage extends BaseActivity {

    @BindView(R.id.shyk_left_back)
    TextView mShykLeftBack;
    @BindView(R.id.shyk_title_title)
    TextView mShykTitleTitle;
    @BindView(R.id.shyk_xz)
    TextView mShykXz;
    @BindView(R.id.message_rv)
    RecyclerView mmessage_rv;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_message;
    }

    @Override
    protected void initViews() {

        mShykTitleTitle.setText("我的消息");
        mShykXz.setVisibility(View.GONE);
        mmessage_rv.setLayoutManager(new LinearLayoutManager(this));
        List<Message> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new Message("测试"));
        }
        MessageAdapter messageAdapter=new MessageAdapter(R.layout.message_item,list);
        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch(view.getId()){
                    case R.id.message_name:
                        jump(MyXXActivity.class);
                        break;
                }
            }
        });
        mmessage_rv.setAdapter(messageAdapter);
    }

    @OnClick({R.id.shyk_left_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shyk_left_back:
                finish();
                break;
        }
    }

    private void jump(Class c) {
        Intent i = new Intent(ActivityMessage.this, c);
        i.putExtra("tag", "1");
        startActivity(i);
    }
}
