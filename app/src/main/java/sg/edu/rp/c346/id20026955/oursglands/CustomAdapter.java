package sg.edu.rp.c346.id20026955.oursglands;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Song> objects){
        super (context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvSquare = rowView.findViewById(R.id.tvSquare);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);
        RatingBar ratingBar = rowView.findViewById(R.id.ratingStars);

        // Obtain the Android Version information based on the position
        Song currentItem = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentItem.getName());
        tvSquare.setText(currentItem.getSquare()+"");
        tvDescription.setText(currentItem.getDescription());

        ratingBar.setClickable(false);
        ratingBar.setRating(currentItem.getStars());
        return rowView;
    }
}
