package bubei.tingshu.multimodule.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import bubei.tingshu.multimodule.R;
import bubei.tingshu.multimodule.adapter.BaseRecyclerAdapter;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public class DefaultFooterStateViewHolder extends RecyclerView.ViewHolder {
    public View refreshLayout;
    public View noMoreLayout;
    public View emptyLayout;
    public ImageView refreshImageView;
    public ProgressBar refreshProgressBar;
    public TextView refreshTextView;
    public View mCustomNoMoreView;

    public DefaultFooterStateViewHolder(View itemView) {
        super(itemView);
        refreshLayout = itemView.findViewById(R.id.refreshLayout);
        noMoreLayout = itemView.findViewById(R.id.moreHintLayout);
        emptyLayout = itemView.findViewById(R.id.emptyLayout);
        refreshImageView = (ImageView) itemView.findViewById(R.id.refreshImageView);
        refreshProgressBar = (ProgressBar) itemView.findViewById(R.id.refreshProgressBar);
        refreshTextView = (TextView) itemView.findViewById(R.id.refreshTextView);
    }

    public DefaultFooterStateViewHolder(View itemView, View customNoMoreView) {
        this(itemView);
        this.mCustomNoMoreView = customNoMoreView;
        if (null != mCustomNoMoreView) {
            ((ViewGroup) noMoreLayout).removeAllViews();
            ((ViewGroup) noMoreLayout).addView(mCustomNoMoreView);
        }
    }

    public void setFooterStateStyle(int footerState){
        switch (footerState) {
            case BaseRecyclerAdapter.INIT_STATE:
                initStateStyle();
                break;
            case BaseRecyclerAdapter.LOADING_MORE_STATE:
                loadingStateStyle();
                break;
            case BaseRecyclerAdapter.NO_MORE_STATE:
                noMoreStateStyle();
                break;
            case BaseRecyclerAdapter.EMPTY_STATE:
                emptyStateStyle();
                break;
            case BaseRecyclerAdapter.HIDE_STATE:
                break;
            default:
        }
    }

    private void initStateStyle(){
        Context context = itemView.getContext();
        refreshLayout.setVisibility(View.VISIBLE);
        refreshImageView.setImageResource(R.drawable.pull_up);
        refreshImageView.setVisibility(View.VISIBLE);
        refreshTextView.setText(context.getString(R.string.pull_to_refresh_from_bottom_pull_label));
        refreshProgressBar.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
        noMoreLayout.setVisibility(View.GONE);
    }

    private void loadingStateStyle(){
        Context context = itemView.getContext();
        refreshLayout.setVisibility(View.VISIBLE);
        refreshImageView.setVisibility(View.GONE);
        refreshTextView.setText(context.getString(R.string.pull_to_refresh_from_bottom_refreshing_label));
        refreshProgressBar.setVisibility(View.VISIBLE);
        emptyLayout.setVisibility(View.GONE);
        noMoreLayout.setVisibility(View.GONE);
    }

    private void noMoreStateStyle(){
        noMoreLayout.setVisibility(View.VISIBLE);
        refreshLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
    }

    private void emptyStateStyle(){
        emptyLayout.setVisibility(View.VISIBLE);
        refreshLayout.setVisibility(View.GONE);
        noMoreLayout.setVisibility(View.GONE);
    }

}
