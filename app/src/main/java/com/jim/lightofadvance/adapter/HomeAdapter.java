package com.jim.lightofadvance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jim.lightofadvance.R;
import com.jim.lightofadvance.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yangjunjin
 * date : 2020/5/21 0:47
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHomeHolder> implements View.OnClickListener {

    private Context mContext;
    private List<String> mlist = new ArrayList<>();
    private List<Integer> mHeigths = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter(Context context, List<String> list,List<Integer> heights) {
        this.mlist = list;
        this.mContext = context;
        this.mHeigths = heights;

    }

    public void removeData(int position) {
        mlist.remove(position);
        //使用这种方法，可能会越界
        //notifyItemRemoved(position);

        notifyDataSetChanged();
    }

    //设置布局
    @NonNull
    @Override
    public MyHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycleview, parent, false);
        MyHomeHolder holder = new MyHomeHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull MyHomeHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.textView.setText(mlist.get(position));

        //设置每个itemView的高度
        ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
        lp.height = mHeigths.get(position);
        holder.textView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener!=null)
            mOnItemClickListener.onItemClick(view, (int) view.getTag());

    }

    class MyHomeHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyHomeHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
}
