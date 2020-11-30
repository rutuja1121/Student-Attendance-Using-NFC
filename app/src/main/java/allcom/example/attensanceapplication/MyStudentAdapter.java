package allcom.example.attensanceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyStudentAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> langs;
    Map<String,List<String>> topics;

    public  MyStudentAdapter(Context context,List<String> langs, Map<String,List<String>> topics){
        this.langs=langs;
        this.context=context;
        this.topics=topics;

    }
    @Override
    public int getGroupCount() {

        return langs.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return topics.get(langs.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return langs.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return topics.get(langs.get(i)).get(i1);
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
        String lang=(String) getGroup(i);
        if(view==null){
            LayoutInflater inflator=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view= inflator.inflate(R.layout.list_parent,null);

        }
        TextView txt=(TextView) view.findViewById(R.id.parent);
        txt.setText(lang);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String topic=(String) getGroup(i);
        if(view==null){
            LayoutInflater inflator1=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view= inflator1.inflate(R.layout.list_child,null);

        }
        TextView txt1=(TextView) view.findViewById(R.id.child);
        txt1.setText(topic);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
