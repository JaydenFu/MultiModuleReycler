package bubei.tingshu.multimodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public abstract class MultiHeaderRecyclerAdapter extends BaseRecyclerAdapter {

    private List<Integer> headerViewTypes = new ArrayList<>();

    public MultiHeaderRecyclerAdapter() {
        this(false);
    }

    public MultiHeaderRecyclerAdapter(boolean hasLoadMoreFunction) {
        super(hasLoadMoreFunction);
    }


    protected abstract int getHeaderItemCount();

    protected abstract int getBodyItemCount();

    protected abstract int getHeaderItemViewType(int position);

    protected abstract int getBodyItemViewType(int position);

    protected abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract RecyclerView.ViewHolder onCreateBodyViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int viewType, int position);

    protected abstract void onBindBodyViewHolder(RecyclerView.ViewHolder holder, int viewType, int position);

    boolean isHeaderItemViewType(int viewType){
        return headerViewTypes.contains(viewType);
    }

    @Override
    protected int getContentItemViewType(int position) {
        if (position < getHeaderItemCount()) {
            int headerItemViewType = getHeaderItemViewType(position);
            headerViewTypes.add(headerItemViewType);
            return headerItemViewType;
        }
        return getBodyItemViewType(position - getHeaderItemCount());
    }

    @Override
    protected int getContentItemCount() {
        headerViewTypes.clear();
        return getHeaderItemCount() + getBodyItemCount();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        if(isHeaderItemViewType(viewType)){
            onCreateHeaderViewHolder(parent,viewType);
        }
        return onCreateBodyViewHolder(parent,viewType);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getContentItemViewType(position);
        if(isHeaderItemViewType(viewType)){
            onBindHeaderViewHolder(holder,viewType,position);
        }
        onBindBodyViewHolder(holder,viewType,position - getHeaderItemCount());
    }
}
