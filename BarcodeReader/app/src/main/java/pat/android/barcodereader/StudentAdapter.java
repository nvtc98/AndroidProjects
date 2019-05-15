package pat.android.barcodereader;

import android.content.Context;
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
public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context,  List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return studentList.indexOf(getItem(position));
    }

    private class  ViewHolder{
        ImageView imgHinh;
        TextView txtID, txtNAME, txtDay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview,null);

            holder = new ViewHolder();
            // Anh xa view
            holder.txtID = (TextView) convertView.findViewById(R.id.txtid);
            holder.txtNAME = (TextView) convertView.findViewById(R.id.txtname);
            holder.txtDay = (TextView) convertView.findViewById(R.id.txtday);
            holder.imgHinh = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        // Gan gia tri
        Student student = studentList.get(position);
        holder.txtID.setText(student.getId());
        holder.txtNAME.setText(student.getName());
        holder.txtDay.setText(student.getDay());
        holder.imgHinh.setImageResource(student.getImg());

        return convertView;
    }
}
