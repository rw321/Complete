package com.example.complete.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.complete.R;
import com.example.complete.bean.Star;
import com.example.complete.databinding.ItemTextBinding;

import java.util.List;

public class SuspendTestAdapter extends RecyclerView.Adapter<SuspendTestAdapter.ViewHolder> {


    private List<Star> data;

    public SuspendTestAdapter(List<Star> data) {
        this.data = data;
    }

    public Boolean isHeader(int position) {
        if (position == 0)
            return true;
        else{
            if (data.get(position).groupName.equals(data.get(position-1).groupName)) {
                return false;
            }else {
                return true;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()) , R.layout.item_text , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBinding.text.setText(data.get(position).name);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTextBinding mBinding;
        public ViewHolder(@NonNull ItemTextBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

}
