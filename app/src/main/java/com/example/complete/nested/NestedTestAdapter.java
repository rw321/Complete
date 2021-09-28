package com.example.complete.nested;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.complete.R;
import com.example.complete.databinding.ItemTextBinding;

import java.util.List;

public class NestedTestAdapter extends RecyclerView.Adapter<NestedTestAdapter.ViewHolder> {

    private List<String> data;

    public NestedTestAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_text, parent , false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.text.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemTextBinding binding;

        public ViewHolder(@NonNull ItemTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
