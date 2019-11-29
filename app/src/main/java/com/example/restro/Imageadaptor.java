package com.example.restro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;




public class Imageadaptor extends RecyclerView.Adapter<Imageadaptor.ImageViewHolder> implements Filterable {
    private Context mContext;
    private List<Upload> mUploads;
    private List<Upload> uploadfull;

    public Imageadaptor(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
        uploadfull = new ArrayList<>(mUploads);

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List <Upload> filterlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterlist.addAll(uploadfull);


            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Upload item : uploadfull) {

                  if (item.getName().toLowerCase().contains(filterPattern)) {
                        filterlist.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterlist;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
       mUploads.clear();
        mUploads.addAll((List)results.values);
        notifyDataSetChanged();
        }
        };
}
