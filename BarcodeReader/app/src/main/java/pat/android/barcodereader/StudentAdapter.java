package pat.android.barcodereader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Trúc on 5/15/2019.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> implements Filterable {


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
    List<Student> mStudentsFull;
    int a = 0;

    public StudentAdapter(List<Student> mStudents) {
        this.mStudents = mStudents;
        mStudentsFull = new ArrayList<>(mStudents);
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
    private ArrayList<Student> myList;  // for loading main list
    private ArrayList<Student> arraylist=null;  // for loading  filter data
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        myList.clear();
        if (charText.length() == 0) {
            myList.addAll(arraylist);
        }
        else
        {
            for (Student wp : arraylist) {
                if (wp.getId().toLowerCase(Locale.getDefault()).contains(charText)) {
                    myList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Student> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mStudentsFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Student item :mStudentsFull){
                    if(item.getId().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values=filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mStudents.clear();
            mStudents.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
