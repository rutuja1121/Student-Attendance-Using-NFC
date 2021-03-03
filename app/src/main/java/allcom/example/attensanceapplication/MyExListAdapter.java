package allcom.example.attensanceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyExListAdapter extends BaseExpandableListAdapter {
   private Context context;
   private List<String> branches;
   private Map<String,List<String>> divisions;

   public  MyExListAdapter(Context context,List<String> branches, Map<String,List<String>> divisions){
       this.branches=branches;
       this.context=context;
       this.divisions=divisions;

   }
    @Override
    public int getGroupCount() {

        return this.branches.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.divisions.get(branches.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.branches.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.divisions.get(branches.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i)
    {
        return i;
    }


    @Override
    public long getChildId(int i, int i1)
    {
        return i1;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
       String branches=(String) getGroup(i);
       if(view==null){
           LayoutInflater inflator=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
           view= inflator.inflate(R.layout.list_parent,null);

       }
        TextView txt=(TextView) view.findViewById(R.id.parent);
       txt.setText(branches);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String divisions=(String) getGroup(i);
        if(view==null){
            LayoutInflater inflator1=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view= inflator1.inflate(R.layout.list_child,null);

        }
        TextView txt1=(TextView) view.findViewById(R.id.child);
        txt1.setText(divisions);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1)
    {
        return true;
    }
}
