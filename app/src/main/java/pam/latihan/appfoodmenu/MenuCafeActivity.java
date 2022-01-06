package pam.latihan.appfoodmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MenuCafeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cafe);

        DBHelper db = new DBHelper(getApplicationContext());

        ArrayList<Foods> data = FoodData.getData(getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.list_menu);
        FoodAdapter adapter = new FoodAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_food_menu, menu);
        return true;
    }

    public void loadData(){
        DBHelper db = new DBHelper(getApplicationContext());
        ArrayList<Foods> data = FoodData.getData(getApplicationContext());

        for (Foods food: data) {
            db.addData(food);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_button:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                this.finish();
                break;

            case R.id.add_button:
                loadData();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}