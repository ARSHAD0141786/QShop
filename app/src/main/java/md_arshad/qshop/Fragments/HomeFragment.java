package md_arshad.qshop.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import md_arshad.qshop.Activity.InnerActivities.CategoryWise;
import md_arshad.qshop.Activity.InnerActivities.ItemWise;
import md_arshad.qshop.Adapter.CategoryItemGridViewAdapter;
import md_arshad.qshop.Adapter.HomeBannerAdapter;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;
import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    private static ArrayList<Integer> images;
    private static int mBannerCurrentPage=0;
    private static ViewPager mPager;
    private static boolean firstTime = true;
    private QShopItemStructure newArrivalItem;

    public HomeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(ArrayList<Integer> bannerImages,QShopItemStructure item) {
        HomeFragment fragment = new HomeFragment();
        images = bannerImages;
        fragment.newArrivalItem = item;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.home_fragment, container, false);
        LinearLayout electronicsLayout = (LinearLayout) v.findViewById(R.id.electronics_layout);
        LinearLayout fashionLayout = (LinearLayout) v.findViewById(R.id.fashion_layout);
        LinearLayout homeFashionLayout = (LinearLayout) v.findViewById(R.id.home_fashion_layout);
        LinearLayout kidsLayout = (LinearLayout) v.findViewById(R.id.kids_layout);

        CardView offerOnlyForYou = (CardView) v.findViewById(R.id.offer_only_for_you);
        CardView newArrival = (CardView) v.findViewById(R.id.new_arrivals);
        offerOnlyForYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryWise.class);
                intent.putExtra("activity_code",1);
                intent.putExtra("activity_name","Offer Only For You");
                startActivity(intent);
            }
        });
        newArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemWise.newItemWiseActivity(getContext(),newArrivalItem);
            }
        });
        electronicsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryWise.class);
                intent.putExtra("activity_code",0);
                intent.putExtra("activity_name","Electronics");
                startActivity(intent);
            }
        });

        fashionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryWise.class);
                intent.putExtra("activity_name","Fashion");
                intent.putExtra("activity_code",1);
                startActivity(intent);
            }
        });

        homeFashionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryWise.class);
                intent.putExtra("activity_name","Home Fashion");
                intent.putExtra("activity_code",2);
                startActivity(intent);
            }
        });

        kidsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryWise.class);
                intent.putExtra("activity_name","Kids Fashion");
                intent.putExtra("activity_code",3);
                startActivity(intent);
            }
        });

        if(firstTime)
            initBanner(v);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(Update);
    }

    private void initBanner(View v){
        mPager = (ViewPager) v.findViewById(R.id.home_banner_pager);
        mPager.setAdapter(new HomeBannerAdapter(getContext(), images));
        CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.home_banner_circle_indicator);
        indicator.setViewPager(mPager);
//        firstTime = false;

        mBannerCurrentPage = 0;
        handler.postDelayed(Update,3000);
    }
    static Handler handler = new Handler();
    static Runnable Update = new Runnable() {
        public void run() {
            mBannerCurrentPage = mPager.getCurrentItem();
            if (mBannerCurrentPage == images.size()-1) {
                mBannerCurrentPage = -1;
            }
            mPager.setCurrentItem(++mBannerCurrentPage, true);
            handler.postDelayed(Update,3000);
        }
    };
}
