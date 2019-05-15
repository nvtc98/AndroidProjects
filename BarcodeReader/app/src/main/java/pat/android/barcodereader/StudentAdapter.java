package pat.android.barcodereader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Trúc on 5/15/2019.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgHinh;
        public TextView txtID, txtNAME, txtDay;

        public ViewHolder(View itemView) {
            super(itemView);

            txtID = (TextView) itemView.findViewById(R.id.txtid);
            txtNAME = (TextView) itemView.findViewById(R.id.txtname);
            txtDay = (TextView) itemView.findViewById(R.id.txtday);
            imgHinh = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    List<Student> mStudents;

    int a = 0;

    public StudentAdapter(List<Student> mStudents) {
        this.mStudents = mStudents;
    }

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_listview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Student student = mStudents.get(position);

        // Set item views based on your views and data model
        viewHolder.txtID.setText(student.getId());
        viewHolder.txtNAME.setText(student.getName());
        viewHolder.txtDay.setText(student.getDay());


        viewHolder.imgHinh.setImageResource(student.getImg());
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }
}
