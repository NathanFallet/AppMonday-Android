package me.nathanfallet.appmonday.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.nathanfallet.appmonday.R;
import me.nathanfallet.appmonday.utils.SettingsElement;
import me.nathanfallet.appmonday.utils.SettingsElementButton;
import me.nathanfallet.appmonday.utils.SettingsSection;

public class SettingsFragment extends Fragment {

    protected ArrayList<SettingsSection> sections = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SettingsFragment() {

    }

    public void loadSettings() {
        sections.clear();

        SettingsElement[] section1 = {
                new SettingsElementButton("moreInfo", "Check out our website for more!", new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.appmonday.xyz/")));
                    }
                })
        };

        SettingsElement[] section2 = {
                new SettingsElementButton("instagram_nathanfallet", "@nathanfallet", new Runnable() {
                    @Override
                    public void run() {
                        Uri uri = Uri.parse("http://instagram.com/_u/nathanfallet");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://instagram.com/nathanfallet")));
                        }
                    }
                }),
                new SettingsElementButton("instagram_code.community", "@code.community", new Runnable() {
                    @Override
                    public void run() {
                        Uri uri = Uri.parse("http://instagram.com/_u/code.community");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://instagram.com/code.community")));
                        }
                    }
                })
        };

        SettingsElement[] section3 = {
                new SettingsElementButton("moreApps", "More apps by our team", new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7477103942295309472")));
                    }
                }),
                new SettingsElementButton("donate", "Donate", new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/NathanFallet")));
                    }
                })
        };

        sections.add(new SettingsSection("What is AppMonday?", section1));
        sections.add(new SettingsSection("Instragram accounts", section2));
        sections.add(new SettingsSection("Developer", section3));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadSettings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new SettingsFragment.SettingsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return mRecyclerView;
    }

    public class SettingsAdapter extends RecyclerView.Adapter<SettingsFragment.SettingsAdapter.SettingsHolder> {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class SettingsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private View mView;

            public SettingsHolder(View v) {
                super(v);
                v.setOnClickListener(this);
                mView = v;
            }

            public View getView() {
                return mView;
            }

            @Override
            public void onClick(View v) {
                SettingsElement e = getAt(getAdapterPosition());

                if(e instanceof SettingsElementButton) {
                    SettingsElementButton b = (SettingsElementButton) e;
                    b.getHandler().run();
                }
            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public SettingsFragment.SettingsAdapter.SettingsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_settings, parent, false);

            return new SettingsFragment.SettingsAdapter.SettingsHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(SettingsFragment.SettingsAdapter.SettingsHolder holder, int position) {
            SettingsElement e = getAt(position);

            View v = holder.getView();

            TextView content = v.findViewById(R.id.settings_name);
            content.setText(e.getText());
        }

        @Override
        public int getItemCount() {
            int count = 0;
            for (SettingsSection s : sections) {
                count += s.getElements().length;
            }
            return count;
        }

        public SettingsElement getAt(int position) {
            for (SettingsSection s : sections) {
                for (SettingsElement e : s.getElements()) {
                    if (position == 0) {
                        return e;
                    }
                    position--;
                }
            }
            return null;
        }

    }

}
