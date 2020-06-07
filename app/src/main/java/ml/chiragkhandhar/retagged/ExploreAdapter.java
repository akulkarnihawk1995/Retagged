package ml.chiragkhandhar.retagged;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreViewHolder> {
    private static final String TAG = "ExploreAdapter";
    private List<Explore> exploreList;
    private ExploreActivity exploreActivity;

    public ExploreAdapter(List<Explore> exploreList, ExploreActivity exploreActivity) {
        this.exploreList = exploreList;
        this.exploreActivity = exploreActivity;
    }

    @NonNull
    @Override
    public ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_entry, parent, false);
        itemView.setOnClickListener(exploreActivity);
        return new ExploreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreViewHolder holder, int position) {
        Explore temp = exploreList.get(position);
        holder.name.setText(temp.getName());
        holder.type.setText(temp.getType());
        holder.distance.setText(temp.getDistance());
        holder.address.setText(temp.getAddress());

        ImageView picture = holder.icon;

        if (isNull(temp.getPhotoURL()))
            picture.setBackgroundResource(R.drawable.placeholder);
        else {
            Glide.with(exploreActivity)
                    .load(temp.getPhotoURL())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(picture);
        }

    }

    private boolean isNull(String photoURL) {
        return photoURL == null || photoURL.equals("null");
    }

    @Override
    public int getItemCount() {
        return exploreList.size();
    }
}
