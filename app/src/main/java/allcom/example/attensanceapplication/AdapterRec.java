package allcom.example.attensanceapplication;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


public class AdapterRec extends RecyclerView.Adapter<AdapterRec.ViewHolder> {
   // private String teacherId;
    private List<Classes> listData;

    public AdapterRec(List<Classes> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewrec,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classes class1=listData.get(position);
        holder.rectxt.setText(class1.studentName);
        System.out.println("hellooooo "+class1.studentName);
    }



    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rectxt;
        public ViewHolder(View itemView){
            super(itemView);


            rectxt=(TextView) itemView.findViewById(R.id.rectxt);

        }
    }
}
