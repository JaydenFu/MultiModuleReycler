package bubei.tingshu.multimodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bubei.tingshu.multimodule.group.Group;
import bubei.tingshu.multimodule.group.GroupManager;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public abstract class MultiGroupRecyclerAdapter extends MultiHeaderRecyclerAdapter {

    private GroupManager groupManager;
    private List<Group> groupList = new ArrayList<>();
    private List<Integer> groupHeaderViewTypes = new ArrayList<>();
    private List<Integer> groupFooterViewTypes = new ArrayList<>();

    public MultiGroupRecyclerAdapter() {
        this(false);
    }

    public MultiGroupRecyclerAdapter(boolean hasLoadMoreFunction) {
        super(hasLoadMoreFunction);
        groupManager = new GroupManager();
    }

    @Override
    protected int getHeaderItemCount() {
        return 0;
    }

    @Override
    protected int getHeaderItemViewType(int position) {
        return 0;
    }


    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int viewType, int position) {
    }


    protected void setupGroup() {
        List<Group> groupList = getGroupList();
        this.groupList.clear();
        if (groupList != null) {
            this.groupList.addAll(groupList);
        }
        groupManager.registerGroups(this.groupList);

        groupHeaderViewTypes.clear();
        groupFooterViewTypes.clear();
        int i;
        int m;
        for (Group group : groupList) {
            i = group.getHeaderCount();
            m = group.getFooterCount();
            for (int j = 0; j < i; j++) {
                groupHeaderViewTypes.add(group.getItemViewType(j));
            }

            for (int k = 0; k < m; k++) {
                groupFooterViewTypes.add(group.getItemViewType(group.getItemCount() - m + k));
            }
        }
    }

    private int calculateItemCount() {
        int count = 0;
        for (Group group : groupList) {
            count += group.getItemCount();
        }
        return count;
    }

    int[] calculateGroupNumAndPositionInGroup(int position) {
        int[] arr = new int[2];
        int groupNum = 0;
        int itemCount = 0;
        int positionInGroup = 0;
        Group group;
        for (int i = 0; i < groupList.size(); i++) {
            groupNum = i;
            group = groupList.get(i);
            itemCount += group.getItemCount();
            if (itemCount > position) {
                positionInGroup = position - (itemCount - group.getItemCount());
                break;
            }
        }
        arr[0] = groupNum;
        arr[1] = positionInGroup;
        return arr;
    }

    int getItemSpanSize(int position){
        int[] arr = calculateGroupNumAndPositionInGroup(position);
        int groupNum = arr[0];
        int positionInGroup = arr[1];
        Group group = groupList.get(groupNum);
        return group.getItemSpanSize(positionInGroup);
    }

//    private int calculateDataPosition(int groupNum,int positionInGroup){
//        int dataPosition = 0;
//        Group group;
//        for(int i=0;i<groupNum;i++){
//            group = groupList.get(i);
//            dataPosition += (group.getItemCount() - group.getFooterCount() - group.getHeaderCount());
//        }
//
//        group = groupList.get(groupNum);
//        dataPosition += (positionInGroup - group.getHeaderCount());
//        return dataPosition;
//    }

    private int calculateDataPositionInGroup(int groupNum, int positionInGroup) {
        Group group = groupList.get(groupNum);
        return positionInGroup - group.getHeaderCount();
    }

    protected abstract List<Group> getGroupList();


    private RecyclerView.ViewHolder onCreateGroupHeaderViewHolder(ViewGroup parent, int viewType){
        return groupManager.onCreateGroupHeaderViewHolder(parent,viewType);
    }

    private RecyclerView.ViewHolder onCreateGroupFooterViewHolder(ViewGroup parent, int viewType){
        return groupManager.onCreateGroupFooterViewHolder(parent,viewType);
    }

    private RecyclerView.ViewHolder onCreateGroupContentItemViewHolder(ViewGroup parent, int viewType){
        return groupManager.onCreateGroupContentItemViewHolder(parent,viewType);
    }

    private void onBindGroupHeaderViewHolder(RecyclerView.ViewHolder holder, int viewType, int groupNum, int positionInHeaders){
         groupManager.onBindGroupHeaderViewHolder(holder,viewType,groupNum,positionInHeaders);
    }

    private void onBindGroupFooterViewHolder(RecyclerView.ViewHolder holder, int viewType, int groupNum, int positionInFooters){
        groupManager.onBindGroupFooterViewHolder(holder,viewType,groupNum,positionInFooters);
    }

    private void onBindGroupContentItemViewHolder(RecyclerView.ViewHolder holder, int viewType, int groupNum, int dataPositionInGroup){
        groupManager.onBindGroupContentItemViewHolder(holder,viewType,groupNum,dataPositionInGroup);
    }

    boolean isGroupHeaderViewType(int viewType) {
        return groupHeaderViewTypes.contains(viewType);
    }

    boolean isGroupFooterViewType(int viewType) {
        return groupFooterViewTypes.contains(viewType);
    }

    @Override
    protected int getBodyItemCount() {
        setupGroup();
        return calculateItemCount();
    }


    @Override
    protected int getBodyItemViewType(int position) {
        int[] arr = calculateGroupNumAndPositionInGroup(position);
        Group group = groupList.get(arr[0]);
        return group.getItemViewType(arr[1]);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateBodyViewHolder(ViewGroup parent, int viewType) {
        if (isGroupHeaderViewType(viewType)) {
            return onCreateGroupHeaderViewHolder(parent, viewType);
        } else if (isGroupFooterViewType(viewType)) {
            return onCreateGroupFooterViewHolder(parent, viewType);
        }
        return onCreateGroupContentItemViewHolder(parent, viewType);
    }

    @Override
    protected void onBindBodyViewHolder(RecyclerView.ViewHolder holder, int viewType, int position) {
        int[] arr = calculateGroupNumAndPositionInGroup(position);
        int groupNum = arr[0];
        int positionInGroup = arr[1];
        int itemCount = groupList.get(groupNum).getItemCount();
        int headerCount = groupList.get(groupNum).getHeaderCount();
        int footerCount = groupList.get(groupNum).getFooterCount();
        int dataPosition = calculateDataPositionInGroup(groupNum, positionInGroup);

        //这里双重判断是为了防止groupHeader的type有可能和groupFooter的type一致的问题
        if (isGroupHeaderViewType(viewType)&&positionInGroup<headerCount) {
            onBindGroupHeaderViewHolder(holder, viewType, groupNum,positionInGroup);
        } else if (isGroupFooterViewType(viewType)&&positionInGroup>= itemCount - footerCount) {
            onBindGroupFooterViewHolder(holder, viewType, groupNum, footerCount - (itemCount - positionInGroup));
        } else {
            onBindGroupContentItemViewHolder(holder, viewType, groupNum, dataPosition);
        }

    }
}
