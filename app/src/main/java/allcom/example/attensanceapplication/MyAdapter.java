package allcom.example.attensanceapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<Classes> listData;
    private String teacherName;
    private String student,value,sDivision;

    Context context;
    public MyAdapter(Context context, List<Classes> listData, String teacherName, String student,String value,String sDivision) {
        this.listData = listData;
        this.teacherName=teacherName;
        this.student=student;
        this.context=context;
        this.value=value;
        this.sDivision=sDivision;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classes ld=listData.get(position);
//        holder.txtid.setText(ld.teacherId);

        if(teacherName!=null&&value!=null) {
            holder.txtname.setText(ld.selectedSubject);
            holder.txtmovie.setText(ld.selectedDivision);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(context,Present_absent_list.class);
                    i.putExtra("teacher id",ld.teacherId);
                    i.putExtra("date",ld.selectedDate);
                    i.putExtra("time",ld.selectedTime);
                    context.startActivity(i);
                }
            });

        }
       else if(teacherName!=null){
            holder.txtname.setText(ld.selectedSubject);
            holder.txtmovie.setText(ld.selectedDivision);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(context,Day_date_student.class);
                    i.putExtra("teacher id",ld.teacherId);
                    i.putExtra("teacherName",ld.teacherName);
                    i.putExtra("selectedYear",ld.selectedYear);
                    i.putExtra("selectedSubject",ld.selectedSubject);
                    i.putExtra("selectedDivision",ld.selectedDivision);
                    i.putExtra("selectedClass",ld.selectedClass);
                    context.startActivity(i);
                }
            });
        }
        if(student!=null){
            holder.txtname.setText(ld.teacherName+"'s");
            holder.txtmovie.setText(ld.selectedSubject);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(context,Scan.class);
                    i.putExtra("teacher id",ld.teacherId);
                    i.putExtra("student name",student);
                    i.putExtra("student division",sDivision);
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtid,txtname,txtmovie;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            //  txtid=(TextView)itemView.findViewById(R.id.idtxt);
            txtname=(TextView)itemView.findViewById(R.id.nametxt);
            txtmovie=(TextView)itemView.findViewById(R.id.movietxt);
            cardView=(CardView)itemView.findViewById(R.id.card);
        }
    }
}
