package test.com.myapplication;

import android.content.Context;
import android.os.Bundle;
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

import bubei.tingshu.multimodule.adapter.GroupSpanSizeLookup;
import bubei.tingshu.multimodule.adapter.MultiGroupRecyclerAdapter;
import bubei.tingshu.multimodule.group.Group;
import bubei.tingshu.multimodule.group.OneHeaderGroup;
import bubei.tingshu.multimodule.listener.LoadMoreListener;

/**
 * Created by xiaojian.fu on 2017/8/11.
 */

public class TestFragment extends Fragment {
    Context context;
    private View fragmentView;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar loadingProgressBar;

    private List<String> data1 = new ArrayList<>();
    private List<String> data2 = new ArrayList<>();
    private List<String> data3 = new ArrayList<>();
    private List<String> data4 = new ArrayList<>();


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
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new LoadMoreListener(gridLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        MultiGroupRecyclerAdapter adapter = new MultiGroupRecyclerAdapter(true) {
            @Override
            protected List<Group> getGroupList() {
                List<Group> groups = new ArrayList<>();
                groups.add(new OneHeaderGroup(data1.size(),new BookGroup_1_ChildManager(gridLayoutManager)));
                groups.add(new OneHeaderGroup(data2.size(),new BookGroup_2_ChildManager(gridLayoutManager)));
                groups.add(new OneHeaderGroup(data3.size(),new BookGroup_1_ChildManager(gridLayoutManager)));
                groups.add(new OneHeaderGroup(data4.size(),new BookGroup_2_ChildManager(gridLayoutManager)));
                return groups;
            }
        };
        recyclerView.setAdapter(adapter);

        gridLayoutManager.setSpanSizeLookup(new GroupSpanSizeLookup(adapter,gridLayoutManager));
        getData();
    }

    private void showLoading(){
        loadingProgressBar.show();
        recyclerView.setVisibility(View.GONE);
    }

    private void showContent(){
        loadingProgressBar.hide();
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void loadMore(){
        int size = data4.size();
        for(int i=size;i<10+size;i++){
            data4.add("data4: "+i);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void getData(){
        for(int i=0;i<8;i++){
            data1.add("data1: "+i);
        }

        for(int i=0;i<3;i++){
            data2.add("data2: "+i);
        }

        for(int i=0;i<4;i++){
            data3.add("data3: "+i);
        }

        for(int i=0;i<10;i++){
            data4.add("data4: "+i);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
        showContent();
    }
}
