package me.nathanfallet.appmonday.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.nathanfallet.appmonday.R;

public class TipDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_details);
        setTitle("Tip details");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final String tip_name = intent != null ? intent.getStringExtra("name") : "";
        final String tip_description = intent != null ? intent.getStringExtra("description") : "";

        TextView name = findViewById(R.id.tip_name);
        name.setText(tip_name);

        TextView description = findViewById(R.id.tip_description);
        description.setText(tip_description);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
