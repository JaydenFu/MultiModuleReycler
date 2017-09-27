package bubei.tingshu.multimodule.adapter;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import bubei.tingshu.multimodule.R;
import bubei.tingshu.multimodule.viewholder.DefaultFooterStateViewHolder;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter{

    //控制下拉加载更多的显示
    public static final int INIT_STATE = 0;
    public static final int LOADING_MORE_STATE = 1;
    public static final int NO_MORE_STATE = 2;
    public static final int EMPTY_STATE = 3;
    public static final int HIDE_STATE = 4;

    public static final int FOOTER_TYPE = 999;

    //底部的状态
    private int footerState;
    //是否有加载更多功能
    private boolean hasLoadMoreFunction;

    @IntDef({INIT_STATE, LOADING_MORE_STATE, NO_MORE_STATE, EMPTY_STATE, HIDE_STATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FooterState {
    }

    public BaseRecyclerAdapter() {
        this(false);
    }

    public BaseRecyclerAdapter(boolean hasLoadMoreFunction) {
        this.hasLoadMoreFunction = hasLoadMoreFunction;
        this.footerState = hasLoadMoreFunction ? INIT_STATE : HIDE_STATE;
    }

    public void setFooterState(@FooterState int footerState) {
        if (this.footerState == footerState) return;
        this.footerState = footerState;
        if (footerState == HIDE_STATE) {
            notifyItemRemoved(getItemCount() - 1);
        } else {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(isFooterViewType(viewType)){
            return onCreateFooterViewHolder(parent);
        }else{
            return onCreateContentViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(isFooterViewType(viewType)){
            onBindFooterViewHolder(holder,footerState);
        }else{
            onBindContentViewHolder(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        if (needFooter()) {
            return getContentItemCount() + 1;
        }
        return getContentItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && needFooter()) {
            return FOOTER_TYPE;
        } else {
            return getContentItemViewType(position);
        }
    }

    boolean isFooterViewType(int viewType){
        return viewType == FOOTER_TYPE;
    }

    private boolean needFooter(){
        return hasLoadMoreFunction && footerState != HIDE_STATE;
    }

    protected abstract int getContentItemViewType(int position);

    protected abstract int getContentItemCount();

    protected abstract RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindContentViewHolder(RecyclerView.ViewHolder holder, int position);

    protected  RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent){
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.default_footer_state_layout,parent, false);
        DefaultFooterStateViewHolder viewHolder = new DefaultFooterStateViewHolder(view);
        return viewHolder;
    }

    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int footerState){
        if(holder instanceof DefaultFooterStateViewHolder){
            ((DefaultFooterStateViewHolder)holder).setFooterStateStyle(footerState);
        }
    }
}
