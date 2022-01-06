package pam.latihan.appfoodmenu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ListViewHolder> {
    private ArrayList<Foods> foodData;

    FoodAdapter(ArrayList<Foods> list) {
        this.foodData = list;
    }

    @NonNull
    @Override
    public FoodAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Foods food = foodData.get(position);
        holder.name.setText(food.name);
        holder.price.setText(String.valueOf(food.price));
        holder.image.setBackground(food.image);
        holder.image.setImageDrawable(food.image);
    }

    @Override
    public int getItemCount(){
        return foodData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView image;
        public TextView name, price;
        public ListViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_photo);
            name = itemView.findViewById(R.id.food_name);
            price = itemView.findViewById(R.id.food_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int selected = getLayoutPosition();
            Intent intent = new Intent(view.getContext(), DetailMenuActivity.class);
            intent.putExtra("id", selected);
            view.getContext().startActivity(intent);
        }
    }
}
