package com.example.duongthuhien.kltn.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.NguPhap_Frag;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 84973 on 6/13/2018.
 */

public class NguPhapFrag_adapter extends BaseExpandableListAdapter {
    private ArrayList<NguPhap_Frag> listNguPhapFrag;
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public NguPhapFrag_adapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData, ArrayList<NguPhap_Frag> listNguPhapFrag) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.listNguPhapFrag = listNguPhapFrag;
    }

    @Override
    public int getGroupCount() {
        return this.listNguPhapFrag.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listNguPhapFrag.get(groupPosition).getStrListTitle();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listNguPhapFrag.get(groupPosition).getStrListItem();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_group_nguphap_frag, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.listTitle);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(listNguPhapFrag.get(groupPosition).getStrListTitle());
        //lblListHeader.setText((String) getGroup(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_item_nguphap_fragment, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.ListItem);

        txtListChild.setText(listNguPhapFrag.get(childPosition).getStrListItem());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
