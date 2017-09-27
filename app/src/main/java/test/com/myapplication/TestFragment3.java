package test.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bubei.tingshu.multimodule.adapter.BaseRecyclerAdapter;
import bubei.tingshu.multimodule.adapter.GroupSpanSizeLookup;
import bubei.tingshu.multimodule.adapter.MultiGroupRecyclerAdapter;
import bubei.tingshu.multimodule.group.Group;
import bubei.tingshu.multimodule.group.OneHeaderGroup;
import bubei.tingshu.multimodule.listener.LoadMoreListener;

/**
 * Created by xiaojian.fu on 2017/8/11.
 */

public class TestFragment3 extends Fragment {
    Context context;
    private View fragmentView;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar loadingProgressBar;
    GridLayoutManager gridLayoutManager;
    MultiGroupRecyclerAdapter adapter;

    private List<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_test, container, false);
        context = inflater.getContext();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadingProgressBar = (ContentLoadingProgressBar) fragmentView.findViewById(R.id.loading_view);
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        showLoading();
        gridLayoutManager = new GridLayoutManager(context, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new LoadMoreListener(gridLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        adapter = new MultiGroupRecyclerAdapter(true) {
            @Override
            protected List<Group> getGroupList() {
                return getGroups();
            }
        };
        recyclerView.setAdapter(adapter);

        gridLayoutManager.setSpanSizeLookup(new GroupSpanSizeLookup(adapter, gridLayoutManager));
        getData();
    }

    private List<Group> getGroups() {
        return groups;
    }

    private void showLoading() {
        loadingProgressBar.show();
        recyclerView.setVisibility(View.GONE);
    }

    private void showContent() {
        loadingProgressBar.hide();
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void loadMore() {
        if(list.size()>20) return;
        adapter.setFooterState(BaseRecyclerAdapter.LOADING_MORE_STATE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                String data;
                int size = list.size();
                for (int i = size; i < 20 + size; i++) {
                    data = "data1:" + i;
                    list.add(data);
                }
                size = list2.size();
                for (int i = size; i < 40 + size; i++) {
                    data = "data2:" + i;
                    list2.add(data);
                }
                assembleGroups();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setFooterState(BaseRecyclerAdapter.NO_MORE_STATE);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }

    private void getData() {
        String data;
        for (int i = 0; i < 18; i++) {
            data = "data1:" + i;
            list.add(data);
        }
        for (int i = 0; i < 40; i++) {
            data = "data2:" + i;
            list2.add(data);
        }

        assembleGroups();
        recyclerView.getAdapter().notifyDataSetChanged();
        showContent();
    }

    private void assembleGroups() {
        groups.clear();
        int gap = 4;
        List<List<String>> groupDatas1 = new ArrayList<>();
        for (int lastIndex = gap; lastIndex <= list.size(); lastIndex += gap) {
            groupDatas1.add(list.subList(lastIndex - gap, lastIndex));
        }

        List<List<String>> groupDatas2 = new ArrayList<>();
        for (int lastIndex = gap; lastIndex <= list2.size(); lastIndex += gap) {
            groupDatas2.add(list2.subList(lastIndex - gap, lastIndex));
        }
        int minsize = groupDatas1.size() < groupDatas2.size() ? groupDatas1.size() : groupDatas2.size();
        Group group;
        for (int i = 0; i < minsize; i++) {
            if (i == 0) {
                group = new OneHeaderGroup(groupDatas1.get(i).size(), new BookGroup_2_ChildManager(gridLayoutManager));
            } else {
                group = new Group(groupDatas1.get(i).size(), new BookGroup_4_ChildManager(gridLayoutManager,groupDatas1.get(i)));
            }
            groups.add(group);
            group = new Group(groupDatas2.get(i).size(), new BookGroup_3_ChildManager(gridLayoutManager,groupDatas2.get(i)));
            groups.add(group);
        }
    }
}
