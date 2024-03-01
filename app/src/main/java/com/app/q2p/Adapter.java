package com.app.q2p;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    private Context context;
    private ArrayList<DataClass> arrayList;

    public Adapter(Context context, ArrayList<DataClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView nam;
        TextView idss;

        CircleImageView img;

        TextView price;
        TextView brand;

        public Viewholder(View itemView) {
            super(itemView);
            nam = itemView.findViewById(R.id.name);
            idss = itemView.findViewById(R.id.visiblity);
            img=itemView.findViewById(R.id.profile_image);
            price=itemView.findViewById(R.id.price);
            brand=itemView.findViewById(R.id.brand);
        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.nam.setText(arrayList.get(position).getTitle());
        holder.idss.setText(arrayList.get(position).getId());
        holder.price.setText(arrayList.get(position).getPrice());
        holder.brand.setText(arrayList.get(position).getBrand());
        Glide.with(holder.itemView.getContext()).load(arrayList.get(position).getImg()).into(holder.img);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetail.class);
            intent.putExtra("idss", arrayList.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
