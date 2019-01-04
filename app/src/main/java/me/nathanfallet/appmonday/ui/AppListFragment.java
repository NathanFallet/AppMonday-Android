package me.nathanfallet.appmonday.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import me.nathanfallet.appmonday.R;
import me.nathanfallet.appmonday.utils.App;
import me.nathanfallet.appmonday.utils.Utils;

public class AppListFragment extends Fragment {

    protected boolean loadingMore = false;
    protected boolean hasMore = true;
    protected ArrayList<App> apps = new ArrayList<App>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public AppListFragment() {

    }

    public void loadApps() {
        new AppListTask().execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadApps();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getActivity());
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new AppListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return mRecyclerView;
    }

    public class AppListTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String[] objects) {
            HashMap<String, Object> data = new HashMap<String, Object>();

            data.put("method", "Web:getApps()");
            data.put("start", loadingMore ? apps.size() : 0);
            data.put("limit", 10);

            try {

                return new JSONObject(Utils.query(data));

            } catch(Exception e) {

                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject array) {
            super.onPostExecute(array);

            try {

                Iterator<String> keys = array.keys();

                while(keys.hasNext()) {
                    String key = keys.next();

                    if (array.get(key) instanceof JSONObject) {

                        JSONObject object = array.getJSONObject(key);

                        App app = new App(object.getString("name"), object.getString("description"),
                                object.getString("user"), object.getString("link"),
                                object.getString("publish"), object.getString("logo"));

                        apps.add(app);

                    }
                }

                mAdapter.notifyDataSetChanged();

            } catch(Exception e) {

                e.printStackTrace();

            }
        }

    }

    public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.MyViewHolder> {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class MyViewHolder extends RecyclerView.ViewHolder {

            // each data item is just a string in this case
            public TextView mTextView;

            public MyViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }

        }

        // Create new views (invoked by the layout manager)
        @Override
        public AppListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.app_list_item, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(apps.get(position).getName());

        }

        @Override
        public int getItemCount() {
            return apps.size();
        }
    }

}
