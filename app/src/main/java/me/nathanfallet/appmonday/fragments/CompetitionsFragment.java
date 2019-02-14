package me.nathanfallet.appmonday.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;

import me.nathanfallet.appmonday.R;
import me.nathanfallet.appmonday.activities.CompetitionDetailsActivity;
import me.nathanfallet.appmonday.models.Competition;
import me.nathanfallet.appmonday.models.CompetitionsManager;

public class CompetitionsFragment extends Fragment {

    protected boolean loading = false;
    protected boolean hasMore = true;
    protected ArrayList<Competition> competitions = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public CompetitionsFragment() {

    }

    public void loadCompetitions() {
        new CompetitionsTask().execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCompetitions();
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
        mAdapter = new CompetitionsFragment.CompetitionsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!loading && hasMore && mRecyclerView.getChildAt(0).getBottom() <= (mRecyclerView.getHeight() + mRecyclerView.getScrollY())) {
                    loadCompetitions();
                }
            }
        });

        return mRecyclerView;
    }

    public class CompetitionsTask extends AsyncTask<String, Void, ArrayList<Competition>> {

        @Override
        protected ArrayList<Competition> doInBackground(String[] objects) {
            loading = true;
            return new CompetitionsManager().getList(competitions.size(), 10);
        }

        @Override
        protected void onPostExecute(ArrayList<Competition> array) {
            super.onPostExecute(array);

            loading = false;

            if(array.size() > 0) {
                competitions.addAll(array);
                mAdapter.notifyDataSetChanged();
            } else {
                hasMore = false;
            }
        }

    }

    public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsFragment.CompetitionsAdapter.CompetitionsHolder> {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class CompetitionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private View mView;

            public CompetitionsHolder(View v) {
                super(v);
                v.setOnClickListener(this);
                mView = v;
            }

            public View getView() {
                return mView;
            }

            @Override
            public void onClick(View v) {
                Competition c = competitions.get(getAdapterPosition());

                // Create an Intent to open app details
                Intent intent = new Intent(getActivity(), CompetitionDetailsActivity.class);
                intent.putExtra("name", c.getName());
                intent.putExtra("date", c.getStart()+" - "+c.getEnd());
                intent.putExtra("description", c.getDescription());
                intent.putExtra("criterias", c.getCriterias());
                startActivity(intent);
            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public CompetitionsFragment.CompetitionsAdapter.CompetitionsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_competition, parent, false);

            return new CompetitionsFragment.CompetitionsAdapter.CompetitionsHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(CompetitionsFragment.CompetitionsAdapter.CompetitionsHolder holder, int position) {
            Competition c = competitions.get(position);

            View v = holder.getView();

            TextView name = v.findViewById(R.id.competition_name);
            name.setText(c.getName());

            TextView date = v.findViewById(R.id.competition_date);
            date.setText(c.getStart()+" - "+c.getEnd());
        }

        @Override
        public int getItemCount() {
            return competitions.size();
        }
    }

}
