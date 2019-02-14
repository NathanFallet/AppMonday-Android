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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.nathanfallet.appmonday.R;
import me.nathanfallet.appmonday.models.Project;
import me.nathanfallet.appmonday.models.ProjectsManager;
import me.nathanfallet.appmonday.activities.ProjectDetailsActivity;

public class ProjectsFragment extends Fragment {

    protected boolean loading = false;
    protected boolean hasMore = true;
    protected ArrayList<Project> projects = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ProjectsFragment() {
        loadProjects();
    }

    public void loadProjects() {
        new ProjectsTask().execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getActivity());
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new ProjectsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!loading && hasMore && mRecyclerView.getChildAt(0).getBottom() <= (mRecyclerView.getHeight() + mRecyclerView.getScrollY())) {
                    loadProjects();
                }
            }
        });

        return mRecyclerView;
    }

    public class ProjectsTask extends AsyncTask<String, Void, ArrayList<Project>> {

        @Override
        protected ArrayList<Project> doInBackground(String[] objects) {
            loading = true;
            return new ProjectsManager().getList(projects.size(), 10);
        }

        @Override
        protected void onPostExecute(ArrayList<Project> array) {
            super.onPostExecute(array);

            loading = false;

            if(array.size() > 0) {
                projects.addAll(array);
                mAdapter.notifyDataSetChanged();
            } else {
                hasMore = false;
            }
        }

    }

    public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsHolder> {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ProjectsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private View mView;

            public ProjectsHolder(View v) {
                super(v);
                v.setOnClickListener(this);
                mView = v;
            }

            public View getView() {
                return mView;
            }

            @Override
            public void onClick(View v) {
                Project a = projects.get(getAdapterPosition());

                // Create an Intent to open app details
                Intent intent = new Intent(getActivity(), ProjectDetailsActivity.class);
                intent.putExtra("name", a.getName());
                intent.putExtra("user", a.getUser());
                intent.putExtra("description", a.getDescription());
                intent.putExtra("link", a.getLink());
                intent.putExtra("logo", a.getLogo());
                startActivity(intent);
            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ProjectsAdapter.ProjectsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_project,
                    parent, false);

            return new ProjectsHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ProjectsHolder holder, int position) {
            Project p = projects.get(position);

            View v = holder.getView();

            TextView name = v.findViewById(R.id.project_name);
            name.setText(p.getName());

            TextView user = v.findViewById(R.id.project_user);
            user.setText(p.getUser());

            TextView date = v.findViewById(R.id.project_date);
            date.setText(p.getDate());

            ImageView logo = v.findViewById(R.id.project_logo);
            Picasso.get().load(p.getLogo()).error(R.drawable.nologo).into(logo);
        }

        @Override
        public int getItemCount() {
            return projects.size();
        }
    }

}
