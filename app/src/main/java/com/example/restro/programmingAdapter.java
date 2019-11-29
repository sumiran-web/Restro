package com.example.restro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class programmingAdapter extends RecyclerView.Adapter<programmingAdapter.ProgrammingViewHolder>{

    // holds data to show in a recyclar view
private String[] data;
    public programmingAdapter(String[] data){

        this.data =data;
    }
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view= inflater.inflate(R.layout.list_item_layout, viewGroup, false);

      return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder programmingViewHolder, int position) {
    String title= data[position];
    programmingViewHolder.About.setText(title);}

    @Override

    //total count of data showed in a view
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
    ImageView imageitem;
    TextView About;
        public ProgrammingViewHolder(View itemView){
            super(itemView);
            imageitem= (ImageView) itemView.findViewById(R.id.imageItem);
            About=  (TextView) itemView.findViewById(R.id.About);

}



    }
}
