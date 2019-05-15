package com.example.cookingebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    private int pos;
    Context context;
    List<Recipe> recipeList;
    public class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        ImageView recipePhoto;
        CardView cv;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
              recipeName = (TextView)itemView.findViewById(R.id.person_name);
            recipeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,RecipeActivity.class);
                    pos=(int)cv.getTag();
                    Recipe recipe = recipeList.get(pos);
                    intent.putExtra("recipe",recipe);
                    v.getContext().startActivity(intent);
                }
            });
            recipePhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            recipePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,ThumbnailActivity.class);
                    pos=(int)cv.getTag();
                    Recipe recipe = recipeList.get(pos);
                    intent.putExtra("recipe",recipe);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }



    RVAdapter(List<Recipe> recipeList,Context context){
        this.recipeList = recipeList;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.recipeName.setText(recipeList.get(i).getName());
        personViewHolder.recipePhoto.setImageResource(recipeList.get(i).getPhotoId());
        personViewHolder.cv.setTag(i);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public int getItemPosition(){
        return pos;
    }
}
