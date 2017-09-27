package bubei.tingshu.multimodule.group;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by xiaojian.fu on 2017/8/11.
 */

public abstract class SimpleGroupChildManager implements GroupChildManager {

    private GridLayoutManager gridLayoutManager;

    public SimpleGroupChildManager(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }

    public final int getSpanCount(){
        return gridLayoutManager.getSpanCount();
    }
}
