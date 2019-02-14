package me.nathanfallet.appmonday.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;

import me.nathanfallet.appmonday.R;
import me.nathanfallet.appmonday.activities.TipDetailsActivity;
import me.nathanfallet.appmonday.models.Tip;
import me.nathanfallet.appmonday.models.TipsManager;

public class TipsFragment extends Fragment {

    protected boolean loading = false;
    protected boolean hasMore = true;
    protected ArrayList<Tip> tips = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public TipsFragment() {

    }

    public void loadTips() {
        new TipsFragment.TipsTask().execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadTips();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getActivity());
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new TipsFragment.TipsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!loading && hasMore && mRecyclerView.getChildAt(0).getBottom() <= (mRecyclerView.getHeight() + mRecyclerView.getScrollY())) {
                    loadTips();
                }
            }
        });

        return mRecyclerView;
    }

    public class TipsTask extends AsyncTask<String, Void, ArrayList<Tip>> {

        @Override
        protected ArrayList<Tip> doInBackground(String[] objects) {
            loading = true;
            return new TipsManager().getList(tips.size(), 10);
        }

        @Override
        protected void onPostExecute(ArrayList<Tip> array) {
            super.onPostExecute(array);

            loading = false;

            if(array.size() > 0) {
                tips.addAll(array);
                mAdapter.notifyDataSetChanged();
            } else {
                hasMore = false;
            }
        }

    }

    public class TipsAdapter extends RecyclerView.Adapter<TipsFragment.TipsAdapter.TipsHolder> {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class TipsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private View mView;

            public TipsHolder(View v) {
                super(v);
                v.setOnClickListener(this);
                mView = v;
            }

            public View getView() {
                return mView;
            }

            @Override
            public void onClick(View v) {
                Tip t = tips.get(getAdapterPosition());

                // Create an Intent to open app details
                Intent intent = new Intent(getActivity(), TipDetailsActivity.class);
                intent.putExtra("name", t.getName());
                intent.putExtra("date", t.getDate());
                intent.putExtra("description", t.getDescription());
                startActivity(intent);
            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public TipsFragment.TipsAdapter.TipsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_tip, parent, false);

            return new TipsFragment.TipsAdapter.TipsHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(TipsFragment.TipsAdapter.TipsHolder holder, int position) {
            Tip t = tips.get(position);

            View v = holder.getView();

            TextView name = v.findViewById(R.id.tip_name);
            name.setText(t.getName());

            TextView date = v.findViewById(R.id.tip_date);
            date.setText(t.getDate());
        }

        @Override
        public int getItemCount() {
            return tips.size();
        }
    }

}
