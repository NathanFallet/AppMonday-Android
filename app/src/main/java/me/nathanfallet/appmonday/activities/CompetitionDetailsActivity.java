package me.nathanfallet.appmonday.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.nathanfallet.appmonday.R;

public class CompetitionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_details);
        setTitle("Competition details");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final String competition_name = intent != null ? intent.getStringExtra("name") : "";
        final String competition_date = intent != null ? intent.getStringExtra("date") : "";
        final String competition_description = intent != null ? intent.getStringExtra("description") : "";
        final String competition_criterias = intent != null ? intent.getStringExtra("criterias") : "";

        TextView name = findViewById(R.id.competition_name);
        name.setText(competition_name);

        TextView date = findViewById(R.id.competition_date);
        date.setText(competition_date);

        TextView description = findViewById(R.id.competition_description);
        description.setText(competition_description);

        TextView criterias = findViewById(R.id.competition_criterias);
        criterias.setText(competition_criterias);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
