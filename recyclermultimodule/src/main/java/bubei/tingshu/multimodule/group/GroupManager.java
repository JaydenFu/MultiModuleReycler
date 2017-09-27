package bubei.tingshu.multimodule.group;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojian.fu on 2017/8/11.
 */

public class GroupManager {

    private List<GroupChildManager> childManagers = new ArrayList<>();


    public void registerGroups(List<Group> groups){
        childManagers.clear();
        for(Group group:groups){
            childManagers.add(group.getGroupChildManager());
        }
    }

    public RecyclerView.ViewHolder onCreateGroupHeaderViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        //找到一个能够创建该viewType类型的ViewHolder的groupChildManager就跳出循环
        for(GroupChildManager groupChildManager:childManagers){
            viewHolder = groupChildManager.onCreateHeaderViewHolder(parent,viewType);
            if(viewHolder!=null){
                break;
            }
        }
        return viewHolder;
    }

    public RecyclerView.ViewHolder onCreateGroupFooterViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        //找到一个能够创建该viewType类型的ViewHolder的groupChildManager就跳出循环
        for(GroupChildManager groupChildManager:childManagers){
            viewHolder = groupChildManager.onCreateFooterViewHolder(parent,viewType);
            if(viewHolder!=null){
                break;
            }
        }
        return viewHolder;
    }

    public RecyclerView.ViewHolder onCreateGroupContentItemViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        //找到一个能够创建该viewType类型的ViewHolder的groupChildManager就跳出循环
        for(GroupChildManager groupChildManager:childManagers){
            viewHolder = groupChildManager.onCreateViewHolder(parent,viewType);
            if(viewHolder!=null){
                break;
            }
        }
        return viewHolder;
    }

    public void onBindGroupHeaderViewHolder(RecyclerView.ViewHolder holder, int viewType, int groupNum, int positionInHeaders) {
        GroupChildManager groupChildManager = childManagers.get(groupNum);
        groupChildManager.onBindHeaderViewHolder(holder,viewType,positionInHeaders);
    }

    public void onBindGroupFooterViewHolder(RecyclerView.ViewHolder holder, int viewType, int groupNum, int positionInFooters) {
        GroupChildManager groupChildManager = childManagers.get(groupNum);
        groupChildManager.onBindFooterViewHolder(holder,viewType,positionInFooters);
    }

    public void onBindGroupContentItemViewHolder(RecyclerView.ViewHolder holder, int viewType, int groupNum, int dataPositionInGroup) {
        GroupChildManager groupChildManager = childManagers.get(groupNum);
        groupChildManager.onBindViewHolder(holder,viewType,dataPositionInGroup);
    }
}
