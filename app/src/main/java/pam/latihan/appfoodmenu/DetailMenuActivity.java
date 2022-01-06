package pam.latihan.appfoodmenu;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;

public class DetailMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        int id = getIntent().getIntExtra("id", -1);

        ArrayList<Foods> list = FoodData.getData(getApplicationContext());
        Foods food = list.get(id);

        if (food != null) {
            TextView nameTV = findViewById(R.id.detail_name);
            TextView priceTV = findViewById(R.id.detail_price);
            TextView descTV = findViewById(R.id.detail_desc);
            ImageView image = findViewById(R.id.detail_image);

            image.setImageDrawable(food.image);
            nameTV.setText(food.name);
            priceTV.setText(String.valueOf(food.price));
            descTV.setText(food.desc);
        }
    }
}