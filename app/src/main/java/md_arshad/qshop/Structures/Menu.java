package md_arshad.qshop.Structures;

import android.content.Context;
import android.media.Image;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import md_arshad.qshop.Adapter.ExpandableListViewInMenuAdapter;
import md_arshad.qshop.R;

public class Menu {
    View menu;
    Context mContext;
    public static List<String> mMenuHeaders;
    public static ArrayList<Integer> mHeaderIcons;
    public static HashMap<String,List<String>> mMenuChildData;

    public Menu(View menu,Context context) {
        this.menu = menu;
        mContext = context;
        initMenu();
        final ExpandableListView expandableListView = (ExpandableListView) menu.findViewById(R.id.expandable_list_view_for_menu);
        expandableListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,84*mMenuHeaders.size()));
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext,"Child : "+childPosition,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                ImageView arrow = (ImageView) parent.findViewById(R.id.header_down_arrow);
                arrow.setImageResource(R.drawable.ic_up_arrow);
                Toast.makeText(mContext,"Item : "+groupPosition,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for(int i=0;i<mMenuHeaders.size();i++){
                    if( i!=groupPosition && expandableListView.isGroupExpanded(i)){
                        expandableListView.collapseGroup(i);
                    }
                }
                expandableListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,84*mMenuHeaders.size() + 64 * mMenuChildData.get(mMenuHeaders.get(groupPosition)).size()));

            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                expandableListView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,84*mMenuHeaders.size()));
            }
        });
        expandableListView.setAdapter(new ExpandableListViewInMenuAdapter(mContext,mMenuHeaders,mHeaderIcons,mMenuChildData));
    }

    private void initMenu(){
        mMenuHeaders = new ArrayList<String>();
        mMenuChildData = new HashMap<String,List<String>>();
        mHeaderIcons = new ArrayList<>();

        mHeaderIcons.add(R.drawable.menu_men_icon);
        mHeaderIcons.add(R.drawable.menu_women_icon);
        mHeaderIcons.add(R.drawable.ic_kids_couple);
        mHeaderIcons.add(R.drawable.ic_sports);
        mHeaderIcons.add(R.drawable.menu_home_fashion_icon);
        mHeaderIcons.add(R.drawable.ic_brand);

        mMenuHeaders.add("Men");
        mMenuHeaders.add("Women");
        mMenuHeaders.add("Kids");
        mMenuHeaders.add("Sports");
        mMenuHeaders.add("Home Fashion");
        mMenuHeaders.add("Brand");

        List<String> men = new ArrayList<>();
        men.add("Watches");
        men.add("Clothes");
        men.add("Footwears");
        men.add("Bands");

        List<String> women = new ArrayList<>();
        women.add("Watches");
        women.add("Clothes");
        women.add("Footwears");

        List<String> kids = new ArrayList<>();
        kids.add("Kids Wear");
        kids.add("Accessories");
        kids.add("Footwears");

        List<String> sports = new ArrayList<>();
        sports.add("Sport Wear");
        sports.add("Sports Kit");
        sports.add("Accessories");

        List<String> homeFashion = new ArrayList<>();
        homeFashion.add("Kitchen");
        homeFashion.add("Bedroom");
        homeFashion.add("Bathroom");
        homeFashion.add("Dining room");


        List<String> brand = new ArrayList<>();
        brand.add("Addidas");
        brand.add("Nike");
        brand.add("Woodland");
        brand.add("Fastrack");

        mMenuChildData.put(mMenuHeaders.get(0),men);
        mMenuChildData.put(mMenuHeaders.get(1),women);
        mMenuChildData.put(mMenuHeaders.get(2),kids);
        mMenuChildData.put(mMenuHeaders.get(3),sports);
        mMenuChildData.put(mMenuHeaders.get(4),homeFashion);
        mMenuChildData.put(mMenuHeaders.get(5),brand);

    }
}
