package me.nathanfallet.appmonday.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.nathanfallet.appmonday.R;

public class AppDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String app_name = "";
        String app_user = "";
        String app_logo = "";
        if(intent != null){
            app_name = intent.getStringExtra("app_name");
            app_user = intent.getStringExtra("app_user");
            app_logo = intent.getStringExtra("app_logo");
        }

        TextView name = findViewById(R.id.app_name);
        name.setText(app_name);

        TextView user = findViewById(R.id.app_user);
        user.setText(app_user);

        ImageView logo = findViewById(R.id.app_logo);
        Picasso.get().load(app_logo).error(R.drawable.nologo).into(logo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
