package bubei.tingshu.multimodule.group;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by xiaojian.fu on 2017/8/11.
 */

public interface GroupChildManager {

    int getHeaderViewType(int positionInHeaders);

    int getFooterViewType(int positionInFooters);

    int getHeaderSpanSize(int positionInHeaders);

    int getFooterSpanSize(int positionInFooters);

    int getItemViewType(int dataPositionInGroup);

    int getItemSpanSize(int dataPositionInGroup);

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int dataPositionInGroup);

    void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int positionInHeaders);

    void onBindFooterViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int positionInFooters);
}
