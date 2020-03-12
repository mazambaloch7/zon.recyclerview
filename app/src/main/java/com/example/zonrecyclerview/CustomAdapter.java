package com.example.zonrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

    Context context;
    ArrayList<ModelList> arrayList;
    public static OnItemClickListener onItemClickListener;

    public CustomAdapter(Context context, ArrayList<ModelList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public CustomAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlist, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public void onBindViewHolder(final CustomAdapter.viewHolder viewHolder, final int i) {
        viewHolder.pimg.setImageResource(arrayList.get(i).getResImg());
        viewHolder.prating.setText(arrayList.get(i).getPrating());
        viewHolder.pname.setText(arrayList.get(i).getPname());
        viewHolder.pprice.setText(arrayList.get(i).getPprice());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView pimg;
        TextView pname, pprice, prating;

        public viewHolder(View itemView) {
            super(itemView);
            pimg = (ImageView) itemView.findViewById(R.id.pimg);
            pname = (TextView) itemView.findViewById(R.id.pname);
            pprice = (TextView) itemView.findViewById(R.id.pprice);
            prating = (TextView) itemView.findViewById(R.id.prating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(), v);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

}