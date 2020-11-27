package allcom.example.attensanceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class Myadapter extends BaseExpandableListAdapter {

    Context context;
    List<String> branches;
    Map<String,List<String>> claases;

    public Myadapter() {
        this.context = context;
        this.branches = branches;
        this.claases = claases;
    }

    @Override
    public int getGroupCount() {

        return branches.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return claases.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return branches.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return claases.get(branches.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String branches=(String) getGroup(i);
        if(view == null);
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.division,null);
            TextView txtparent=(TextView) view.findViewById((R.id.tvdivision));
            txtparent.setText(branches);
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String classes=(String) getChild(i,i1);
        if(view == null);
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.division,null);
            TextView txt=(TextView) view.findViewById((R.id.classtv));
            txt.setText(classes);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
