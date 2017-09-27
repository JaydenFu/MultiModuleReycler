package test.com.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bubei.tingshu.multimodule.group.NoHeaderFooterGroupChildManager;

/**
 * 不带头部 竖向item
 * Created by xiaojian.fu on 2017/8/11.
 */

public class BookGroup_4_ChildManager extends NoHeaderFooterGroupChildManager {

    private List<String> datas;

    public BookGroup_4_ChildManager(GridLayoutManager gridLayoutManager) {
        super(gridLayoutManager);
    }

    public BookGroup_4_ChildManager(GridLayoutManager gridLayoutManager, List<String> datas) {
        super(gridLayoutManager);
        this.datas = datas;
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
        ((TextView)viewHolder.itemView).setText("竖向内容item---:"+dataPositionInGroup+";  data:"+datas.get(dataPositionInGroup));
        ((TextView)viewHolder.itemView).setBackgroundColor(Color.RED);
    }

}
