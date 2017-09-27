package test.com.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import bubei.tingshu.multimodule.group.OneHeaderGroupChildManager;

/**
 * 带头部 竖向item
 * Created by xiaojian.fu on 2017/8/11.
 */

public class BookGroup_2_ChildManager extends OneHeaderGroupChildManager {

    public BookGroup_2_ChildManager(GridLayoutManager gridLayoutManager) {
        super(gridLayoutManager);
    }

    @Override
    public int getHeaderViewType(int positionInHeaders) {
        return 0;
    }

    @Override
    public int getHeaderSpanSize(int positionInHeaders) {
        return getSpanCount();
    }

    @Override
    public int getItemViewType(int dataPositionInGroup) {
        return 2;
    }

    @Override
    public int getItemSpanSize(int dataPositionInGroup) {
        return getSpanCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        TextView textView = new TextView(context);
        RecyclerView.LayoutParams layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300);
        layoutParams.setMargins(10,10,10,10);
        textView.setLayoutParams(layoutParams);
        return new RecyclerView.ViewHolder(textView) {};
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int dataPositionInGroup) {
        ((TextView)viewHolder.itemView).setText("竖向内容item---:"+dataPositionInGroup);
        ((TextView)viewHolder.itemView).setBackgroundColor(Color.RED);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        TextView textView = new TextView(context);
        RecyclerView.LayoutParams layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        textView.setLayoutParams(layoutParams);
        return new RecyclerView.ViewHolder(textView) {};
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int positionInHeaders) {
        ((TextView)viewHolder.itemView).setText("头部--------");
        viewHolder.itemView.setBackgroundColor(Color.YELLOW);
    }
}
