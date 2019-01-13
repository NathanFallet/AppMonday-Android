package me.nathanfallet.appmonday.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.nathanfallet.appmonday.R;

public class AddFragment extends Fragment {

    public AddFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        TextView open = v.findViewById(R.id.open_web);
        open.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.appmonday.xyz")));

                return true;
            }
        });

        return v;
    }

}
