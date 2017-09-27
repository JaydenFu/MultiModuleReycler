package bubei.tingshu.multimodule.group;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by xiaojian.fu on 2017/8/11.
 */

public abstract class OneHeaderGroupChildManager extends SimpleGroupChildManager {

    public OneHeaderGroupChildManager(GridLayoutManager gridLayoutManager) {
        super(gridLayoutManager);
    }

    @Override
    public final int getFooterViewType(int positionInFooters) {
        return 0;
    }

    @Override
    public final int getFooterSpanSize(int positionInFooters) {
        return 0;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public final void onBindFooterViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int positionInFooters) {
    }
}
