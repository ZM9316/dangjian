package a9chou.com.fangjiazhuangApp.module.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.tu.loadingdialog.LoadingDailog;

import java.util.ArrayList;
import java.util.List;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.adapter.MenberMessageAdapter;
import a9chou.com.fangjiazhuangApp.base.BaseFragment;
import a9chou.com.fangjiazhuangApp.module.dao.MenberMessage;
import butterknife.BindView;

/**
 * Created by zm9316 on 2017/12/29.
 */

public class ActivityUnreadFragment extends BaseFragment {

    @BindView(R.id.message_rv)
    RecyclerView mmessage_rv;
    private LoadingDailog mDialog;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_unread;
    }

    @Override
    protected void initViews() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getActivity())
                .setMessage("加载中...")
                .setCancelable(true);
        mDialog = loadBuilder.create();
        mDialog.show();
        mmessage_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<MenberMessage> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new MenberMessage("未读测试"));
        }
        MenberMessageAdapter menberMessageAdapter=new MenberMessageAdapter(R.layout.message_item,list);
        mmessage_rv.setAdapter(menberMessageAdapter);
        mDialog.dismiss();
    }

}
