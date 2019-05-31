package md_arshad.qshop.Adapter;

import android.app.ActionBar;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import md_arshad.qshop.R;

public class ExpandableListViewInMenuAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mHeaderList;
    private ArrayList<Integer> mHeaderIcons;
    private HashMap<String,List<String>> mChildDataStructure;

    public ExpandableListViewInMenuAdapter(Context mContext, List<String> mHeaderList, ArrayList<Integer> mHeaderIcons, HashMap<String, List<String>> mChildDataStructure) {
        this.mContext = mContext;
        this.mHeaderList = mHeaderList;
        this.mHeaderIcons = mHeaderIcons;
        this.mChildDataStructure = mChildDataStructure;
    }

    @Override
    public int getGroupCount() {
        return mHeaderList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildDataStructure.get(mHeaderList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeaderList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mChildDataStructure.get(this.mHeaderList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.menu_expandable_list_header,null);
        }

        ImageView icon = (ImageView) view.findViewById(R.id.header_icon);
        icon.setImageResource(mHeaderIcons.get(groupPosition));
        ImageView downArrow = (ImageView) view.findViewById(R.id.header_down_arrow);
        downArrow.setImageResource(R.drawable.ic_expand_down);
        TextView headerTitle = (TextView) view.findViewById(R.id.header_title);
        headerTitle.setText(mHeaderList.get(groupPosition));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.menu_expandable_list_item,null);
        }
        TextView textView = (TextView) view.findViewById(R.id.menu_expandable_list_item);
        textView.setText((String)getChild(groupPosition,childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
