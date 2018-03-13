package a9chou.com.fangjiazhuangApp.adapter;


/**
 * Created by zhang on 2018/1/30.
 */

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import a9chou.com.fangjiazhuangApp.R;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    LayoutInflater layoutInflater;
    private TextView mTextView;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();//注意此处
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.grid_item, null);
        mTextView = (TextView) convertView.findViewById(R.id.position);
        StringBuffer str = new StringBuffer(list.get(position).toString());
        String strInsert = "\n";
        str.insert(5,strInsert + "");
        mTextView.setText(str);
        return convertView;
    }

}
