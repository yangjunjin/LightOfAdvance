package com.jim.lightofadvance.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jim.lightofadvance.R;
import com.jim.lightofadvance.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView 使用，自定义线割，瀑布流
 */
public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    //不可以删除添加
    //private List<String> mlist = Arrays.asList("a", "b", "c", "d", "d", "f", "g", "f", "a", "b", "c", "d", "d", "f", "g", "f","a", "b", "c", "d", "d", "f", "g", "f","a", "b", "c", "d", "d", "f", "g", "f");
    private List<String> mlist = new ArrayList<>();
    private List<Integer> mHeigths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        mRecyclerView = findViewById(R.id.recycleView);

        //初始化数据
        for (int i = 0; i < 50; i++)
            mlist.add("牛逼" + i);
        for (int i = 0; i<mlist .size(); i++)
            mHeigths.add((int) (100+Math.random()*500));

        mHomeAdapter = new HomeAdapter(this, mlist,mHeigths);
        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mHomeAdapter.removeData(position);
                Toast.makeText(RecycleViewActivity.this, "hah" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //竖直方向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //横向
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //GridLayoutManager
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        //设置布局管理器
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        //设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设备器
        mRecyclerView.setAdapter(mHomeAdapter);
        //分割线
        //ItemDecoration itemDecoration = new ItemDecoration(this, ItemDecoration.HORIZONTAL_LIST);
        //mRecyclerView.addItemDecoration(itemDecoration);

    }
}
