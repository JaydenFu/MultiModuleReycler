package bubei.tingshu.multimodule.adapter;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by xiaojian.fu on 2017/8/11.
 */

public class GroupSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private MultiGroupRecyclerAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    public GroupSpanSizeLookup(MultiGroupRecyclerAdapter adapter, GridLayoutManager gridLayoutManager) {
        this.adapter = adapter;
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        int spanCount = gridLayoutManager.getSpanCount();
        int viewType = adapter.getItemViewType(position);

        if(adapter.isHeaderItemViewType(viewType)||adapter.isFooterViewType(viewType)){
            return spanCount;
        }
        int itemSpanSize = adapter.getItemSpanSize(position);
        return itemSpanSize;
    }
}
