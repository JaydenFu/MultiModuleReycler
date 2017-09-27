package bubei.tingshu.multimodule.group;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public class Group {

    private int dataCount;
    private GroupChildManager groupChildManager;

    public Group(int dataCount,GroupChildManager groupChildManager) {
        this.dataCount = dataCount;
        this.groupChildManager = groupChildManager;
    }

    public int getHeaderCount(){
        return 0;
    }

    public int getFooterCount(){
        return 0;
    }

    public final int getItemCount(){
        return dataCount + getHeaderCount() + getFooterCount();
    }

    public final int getItemViewType(int positionInGroup){
        if(positionInGroup < getHeaderCount()){
            return groupChildManager.getHeaderViewType(positionInGroup);
        }else if(positionInGroup>= getItemCount() - getFooterCount()){
            return groupChildManager.getFooterViewType(getFooterCount() - (getItemCount() - positionInGroup));
        }
        return groupChildManager.getItemViewType(positionInGroup - getHeaderCount());
    }

    public final int getItemSpanSize(int positionInGroup){
        if(positionInGroup < getHeaderCount()){
            return groupChildManager.getHeaderSpanSize(positionInGroup);
        }else if(positionInGroup>= getItemCount() - getFooterCount()){
            return groupChildManager.getFooterSpanSize(getFooterCount() - (getItemCount() - positionInGroup));
        }
        return groupChildManager.getItemSpanSize(positionInGroup - getHeaderCount());
    }

    public final GroupChildManager getGroupChildManager(){
        return groupChildManager;
    }
}
