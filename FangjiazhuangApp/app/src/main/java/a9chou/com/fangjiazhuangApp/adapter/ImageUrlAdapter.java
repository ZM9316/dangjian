package a9chou.com.fangjiazhuangApp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import a9chou.com.fangjiazhuangApp.R;

/**
 * Created by zm9316 on 2017/12/23.
 */

public class ImageUrlAdapter extends RecyclerView.Adapter<ImageUrlAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<Bitmap> mDatas;

    public ImageUrlAdapter(Context context, ArrayList<Bitmap> datats)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        ImageView mImg;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.showinfo_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.im_show);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {

        viewHolder.mImg.setImageBitmap(mDatas.get(i));

    }

}
