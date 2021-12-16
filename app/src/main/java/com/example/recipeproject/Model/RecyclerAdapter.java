package com.example.recipeproject.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeproject.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    public static ArrayList<RecipeData> ObjList;
    private Context context;
    OnItemClickListener mOnItemClickListener;
    private static ArrayList<RecipeData> ObjListFull;
    //Interface 5: DECLARE THE INTERFACE//
    //private final RecyclerViewRowInterface recyclerViewRowInterface;

    //RecyclerRow OnclickListener Interface method NUM1: declare this interface
    public interface OnItemClickListener {
        //it will have a single override method
        //detect the click and send the position of that item
        //go to the RecyclerActivity and implement this interface
        void onItemClick(int position);
    }
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
//        this.mOnItemClickListener = onItemClickListener;
//    }

    //Interface 5: ADD THE INTERFACE HERE//
    public RecyclerAdapter(Context context, ArrayList<RecipeData> ObjList) {
        this.context = context;
        this.ObjList = ObjList;
        this.mOnItemClickListener = (OnItemClickListener)context;
        //this.recyclerViewRowInterface = recyclerViewRowInterface;
        ObjListFull = new ArrayList<>(ObjList);

    }

    @Override
    public Filter getFilter() {
        return ObjListFilter;
    }

    private Filter ObjListFilter = new Filter() {
        //run in background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<RecipeData> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(ObjListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (RecipeData item : ObjListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        //runs on UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ObjList.clear();
            ObjList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


//    public void setOnItemClickListener(OnItemClickListener listener){
//        mOnItemClickListener = listener;
//    }


    //    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
//        this.mOnItemClickListener = OnItemClickListener;
//    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;
        private final TextView cuisine;
        private final TextView duration;
        private final ImageView img;

        //OnItemClickListener OnItemClickListener;
        //Constructor of inner class
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            cuisine = itemView.findViewById(R.id.cuisine);
            duration = itemView.findViewById(R.id.duration);
            img = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
            //this.OnItemClickListener = OnItemClickListener;
            //Interface 6//
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    mOnItemClickListener.onItemClick(pos);
                }
            }
        }
//        public TextView getTitle() {
//            return title;
//        }
//
//        public TextView getCuisine() {
//            return cuisine;
//        }
//
//        public TextView getDuration() {
//            return duration;
//        }
//
//        public ImageView getImg() {
//            return img;
//        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        System.out.println("16. RecyclerView view inflated in RecyclerAdapter");
        //Interface 6: ADD THE INTERFACE BELOW
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        System.out.println("17. Adapter binding the data from parsed recipes to RecyclerView row in RecyclerAdapater");
        RecipeData currentObj = ObjList.get(position);
        System.out.println("18.current object in the arraylist in RecyclerAdapter");
        holder.title.setText(currentObj.getTitle());
        System.out.println("19. title of the recipe in RecyclerAdapter");
        holder.cuisine.setText(currentObj.getCuisine());
        System.out.println("20. cuisine of the recipe in RecyclerAdapter");
        holder.duration.setText(String.valueOf(currentObj.getDuration()));
        System.out.println("21. duration of the recipe in RecyclerAdapter");
        //Glide Num1: using glide library to display image
        //go to build.gradle
        //Glide Num5 write the following code
        Glide.with(context)
                .load(currentObj.getImg())
                .into(holder.img);
        System.out.println("22.uploading the image in the recyclerview row");
        System.out.println("23.recyclerview is completed in RecyclerAdapter");
    }
    
    @Override
    public int getItemCount() {
        return ObjList.size();
    }

}
