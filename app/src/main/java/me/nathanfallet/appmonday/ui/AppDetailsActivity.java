package me.nathanfallet.appmonday.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.nathanfallet.appmonday.R;

public class AppDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final String app_name = intent != null ? intent.getStringExtra("app_name") : "";
        final String app_user = intent != null ? intent.getStringExtra("app_user") : "";
        final String app_logo = intent != null ? intent.getStringExtra("app_logo") : "";
        final String app_link = intent != null ? intent.getStringExtra("app_link") : "";
        final String app_description = intent != null ? intent.getStringExtra("app_description") : "";

        TextView name = findViewById(R.id.app_name);
        name.setText(app_name);

        TextView user = findViewById(R.id.app_user);
        user.setText(app_user);
        user.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Uri uri = Uri.parse("http://instagram.com/_u/"+app_user);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://instagram.com/"+app_user)));
                }

                return true;
            }
        });

        ImageView logo = findViewById(R.id.app_logo);
        Picasso.get().load(app_logo).error(R.drawable.nologo).into(logo);

        TextView link = findViewById(R.id.app_link);
        link.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(app_link)));

                return true;
            }
        });

        TextView description = findViewById(R.id.app_description);
        description.setText(app_description);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
