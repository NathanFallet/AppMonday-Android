package me.nathanfallet.appmonday.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.nathanfallet.appmonday.R;

public class ProjectDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final String project_name = intent != null ? intent.getStringExtra("name") : "";
        final String project_user = intent != null ? intent.getStringExtra("user") : "";
        final String project_logo = intent != null ? intent.getStringExtra("logo") : "";
        final String project_link = intent != null ? intent.getStringExtra("link") : "";
        final String project_description = intent != null ? intent.getStringExtra("description") : "";

        TextView name = findViewById(R.id.project_name);
        name.setText(project_name);

        TextView user = findViewById(R.id.project_user);
        user.setText(project_user);
        user.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Uri uri = Uri.parse("http://instagram.com/_u/"+project_user);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://instagram.com/"+project_user)));
                }

                return true;
            }
        });

        ImageView logo = findViewById(R.id.project_logo);
        Picasso.get().load(project_logo).error(R.drawable.nologo).into(logo);

        TextView link = findViewById(R.id.project_link);
        link.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(project_link)));

                return true;
            }
        });

        TextView description = findViewById(R.id.project_description);
        description.setText(project_description);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
