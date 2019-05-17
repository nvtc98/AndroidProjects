package pat.android.hochiminhtour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Trúc on 5/14/2019.
 */

public class DestinationAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Destination> destinationList;

    public DestinationAdapter(Context context, int layout, List<Destination> destinationList) {
        this.context = context;
        this.layout = layout;
        this.destinationList = destinationList;
    }

    @Override
    public int getCount() {
        return destinationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // View Holder
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        // Anh xa
        TextView txtName = (TextView) convertView.findViewById(R.id.txtname);
        ImageView img = (ImageView) convertView.findViewById(R.id.img);

        // Gan gia tri
        Destination destination = destinationList.get(position);
        txtName.setText(destination.getName());
        img.setImageResource(destination.getImage());

        return convertView;
    }
}
