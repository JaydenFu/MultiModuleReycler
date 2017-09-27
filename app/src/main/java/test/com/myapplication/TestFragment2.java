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

public class TestFragment2 extends Fragment {
    Context context;
    private View fragmentView;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar loadingProgressBar;

    private List<List<String>> list = new ArrayList<>();


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
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
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
                for(int i=0;i<list.size();i++){
                    if(i==0){
                        groups.add(new OneHeaderGroup(list.get(i).size(), new BookGroup_1_ChildManager(gridLayoutManager)));
                    }else{
                        groups.add(new OneHeaderGroup(list.get(i).size(), new BookGroup_2_ChildManager(gridLayoutManager)));
                    }
                }
                return groups;
            }
        };
        recyclerView.setAdapter(adapter);

        gridLayoutManager.setSpanSizeLookup(new GroupSpanSizeLookup(adapter, gridLayoutManager));
        getData();
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
        List<String> data;
        for (int i = 0; i < 5; i++) {
            data = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                data.add("data1: " + i);
            }
            list.add(data);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void getData() {
        List<String> data;
        for (int i = 0; i < 5; i++) {
            data = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < 8; j++) {
                    data.add("data1: " + i);
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    data.add("data1: " + i);
                }
            }
            list.add(data);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
        showContent();
    }
}
