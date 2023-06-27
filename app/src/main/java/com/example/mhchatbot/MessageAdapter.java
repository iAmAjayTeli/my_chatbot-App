package com.example.mhchatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.viewHolder> {
    List<MessageModel>modelList;

    public MessageAdapter(List<MessageModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,null);
        return  new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        MessageModel model=modelList.get(position);

        if(model.getSentBy().equals(MessageModel.Sent_By_Me)){
            holder.left_chat.setVisibility(View.GONE);
            holder.right_chat.setVisibility(View.VISIBLE);
            holder.RightTxt.setText(model.getMessage());
        }
        else{
            holder.right_chat.setVisibility(View.GONE);
            holder.left_chat.setVisibility(View.VISIBLE);
            holder.leftText.setText(model.getMessage());

        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout left_chat, right_chat;
        TextView leftText, RightTxt;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            left_chat=itemView.findViewById(R.id.left_chat);
            right_chat=itemView.findViewById(R.id.right_chat);
            leftText=itemView.findViewById(R.id.left_text);
            RightTxt=itemView.findViewById(R.id.right_text);



        }
    }
}
