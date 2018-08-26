package com.example.android.schoolapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;


public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    final private postClickListener mOnClickListener;

    private static int viewHolderCount;

    private String[] date_List = new String[]{"17 min", "1 hour", "1 Day", "1 Week", "14 min","17 min", "6 hour", "5 Day", "1 Week", "34 min","17 min", "2 hour", "1 Day", "1 Mon", "34 min","17 min", "4 hour", "5 Day", "1 Week", "10 min"};
    private int numListPost;


    public interface postClickListener {
        void onPostClick(int clickedPostIndex);
    }

    public Adapter(int numListPosts, postClickListener listener) {
        numListPost = numListPosts;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForPostItem = R.layout.post_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForPostItem, parent, shouldAttachToParentImmediately);
        viewHolder viewHolder = new viewHolder(view);

        viewHolder.Date.setText( date_List[viewHolderCount] );
                viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.bind("this is the #number "+
                String.valueOf( position+1 )
                +" Post");
    }

    @Override
    public int getItemCount() {
        return numListPost;
    }

    class viewHolder extends RecyclerView.ViewHolder
    implements OnClickListener{


        TextView listPostView;

        TextView Date;

        public viewHolder(View itemView) {
            super( itemView );
            listPostView = itemView.findViewById(R.id.tv_item_post);
            Date = itemView.findViewById(R.id.tv_Date);

            itemView.setOnClickListener(this);

        }
        void bind(String Post) {
            listPostView.setText(Post);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onPostClick(clickedPosition);
        }
    }
}
