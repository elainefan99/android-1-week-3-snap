package com.ucsdextandroid1.snapapp.chat;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class ChatAdapter extends RecyclerView.Adapter {

    @Nullable private Callback callback;
    private List<Chat> items = new ArrayList<>();

    public void setItems(List<Chat> chats) {
        this.items.clear();
        this.items.addAll(chats);
        notifyDataSetChanged();
    }

    public void setCallback(@Nullable Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItemViewHolder viewHolder = ChatItemViewHolder.inflate(parent);
        viewHolder.setClickListener(defaultCallback);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ChatItemViewHolder) {
            ((ChatItemViewHolder) holder).bind(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public interface Callback extends ChatItemViewHolder.ChatClickListener {
        // just extents all the click listeners supported by the adapter
    }

    private Callback defaultCallback = new Callback() {
        @Override
        public void onChatClicked(Chat chat) {
            if(callback != null)
                callback.onChatClicked(chat);
        }
    };

}
