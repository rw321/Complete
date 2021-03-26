package com.example.complete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.complete.R;

import java.util.List;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.SimpleTextViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public SimpleTextAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public SimpleTextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_simple_text , viewGroup , false);
        return new SimpleTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleTextViewHolder holder, int i) {
        holder.tvText.setText("number " + mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class SimpleTextViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;
        public SimpleTextViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }

}
