package md_arshad.qshop.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import md_arshad.qshop.Activity.InnerActivities.WishlistActivity;
import md_arshad.qshop.Fragments.CartFragment;
import md_arshad.qshop.Fragments.HomeFragment;
import md_arshad.qshop.Fragments.MoreFragment;
import md_arshad.qshop.Fragments.OfferFragment;
import md_arshad.qshop.Fragments.UserFragment;
import md_arshad.qshop.Network.FetchData;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;
import md_arshad.qshop.Structures.Menu;
import md_arshad.qshop.Structures.UserDataStructure;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    ImageView menu_icon;
    public static UserDataStructure mUserDataStructure;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    private static ViewPager mPager;
    private static final Integer[] img = {
            R.drawable.samsung_banner,
            R.drawable.watch_banner,
            R.drawable.smasung_banner
    };
    private static ArrayList<Integer> Images = new ArrayList<Integer>();
    private QShopItemStructure newArrivalItem = FetchData.newArrivalItem;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0 : return userFragment;
                case 1 : return offerFragment;
                case 2 : return homeFragment;
                case 3 : return cartFragment;
                case 4 : return moreFragment;
            }
            return HomeFragment.newInstance(Images,newArrivalItem);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }
    }

    public TabLayout tabLayout;
    public static boolean isUserLoggedIn;
    public static View menuHeaderLayout;
    public static View menu;
    protected static TextView currentPageTextView;
    // only use when user already have some items in cart
    private static ArrayList<QShopItemStructure> cartItemsArray = new ArrayList<>();

    private static OfferFragment offerFragment;
    private static HomeFragment homeFragment;
    private static CartFragment cartFragment;
    private static MoreFragment moreFragment;
    private static UserFragment userFragment;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY),Context.MODE_PRIVATE);
        isUserLoggedIn = sharedPreferences.getBoolean(getString(R.string.saved_user_login_info),false);
        if(isUserLoggedIn){
            mUserDataStructure = new UserDataStructure();
            mUserDataStructure.setName(sharedPreferences.getString(getString(R.string.saved_user_name),null));
            mUserDataStructure.setAddress(sharedPreferences.getString(getString(R.string.saved_address),null));
            mUserDataStructure.setEmail(sharedPreferences.getString(getString(R.string.saved_email_id),null));
            mUserDataStructure.setImage_url(sharedPreferences.getString(getString(R.string.saved_image_url),null));
            mUserDataStructure.setPhone(sharedPreferences.getString(getString(R.string.saved_phone_number),null));
        }else{
            mUserDataStructure = null;
        }

         offerFragment = OfferFragment.newInstance();
         homeFragment = HomeFragment.newInstance(Images,newArrivalItem);
         cartFragment = CartFragment.newInstance(cartItemsArray);
         moreFragment = MoreFragment.newInstance();
         userFragment = UserFragment.newInstance();

        initCartItems();
        initHomeBannerImages();

        ImageView searchButton = (ImageView) findViewById(R.id.search_btn);
        ImageView notificationBtn = (ImageView) findViewById(R.id.notification_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CustomSearchActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Search button tapped",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView wishlistImageIcon = (ImageView) findViewById(R.id.wishlist_btn_image);
        wishlistImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        currentPageTextView = findViewById(R.id.current_page_text_view);
        menu_icon = (ImageView) findViewById(R.id.menu_image_view);
        drawer.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return true;
            }
        });


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.fragment_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
//        mViewPager.setCurrentItem(2);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_home_white).select();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              switch (tab.getPosition()){
                  case 0 : tab.setIcon(R.drawable.ic_user_icon_white);currentPageTextView.setText("User");break;
                  case 1 : tab.setIcon(R.drawable.ic_offer_icon_white);currentPageTextView.setText("Offer");break;
                  case 2 : tab.setIcon(R.drawable.ic_home_white);currentPageTextView.setText("Home");break;
                  case 3 : tab.setIcon(R.drawable.ic_shopping_cart_white);currentPageTextView.setText("Cart");break;
                  case 4 : tab.setIcon(R.drawable.ic_more_three_dots_white);currentPageTextView.setText("More");break;
              }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : tab.setIcon(R.drawable.ic_user_icon_black);break;
                    case 1 : tab.setIcon(R.drawable.ic_offer_icon_black);break;
                    case 2 : tab.setIcon(R.drawable.ic_home_black);break;
                    case 3 : tab.setIcon(R.drawable.ic_shopping_cart_black);break;
                    case 4 : tab.setIcon(R.drawable.ic_more_three_dots_black);break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
//        menu = navView.getHeaderView(0);
        menu = (View) navView.findViewById(R.id.nav_header_view);
        Menu menu1 = new Menu(menu,getApplicationContext());
        menuHeaderLayout = menu.findViewById(R.id.menu_header_layout);

        if(isUserLoggedIn) {
            View v = MainActivity.menuHeaderLayout;
            ImageView userImage = (ImageView) v.findViewById(R.id.user_icon_image_view_nav_header);
            userImage.setImageResource(R.drawable.user_photo);
            TextView userName = (TextView) v.findViewById(R.id.name_text_View_nav_header);
            userName.setText(mUserDataStructure.getName());
            TextView userAddress = (TextView) v.findViewById(R.id.address_text_view_nav_header);
            userAddress.setText(mUserDataStructure.getAddress());
        }
        menuHeaderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLayout.getTabAt(0).setIcon(R.drawable.ic_user_icon_white).select();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void initHomeBannerImages() {
        for(int i=0;i<img.length;i++)
            Images.add(img[i]);
    }


    private void initCartItems(){

        //TODO: Init cart items here by API

//        QShopItemStructure QShopItemStructure;
//        QShopItemStructure = new QShopItemStructure();
//        QShopItemStructure.setItemTitle("Samsung Galaxy S9");
//        QShopItemStructure.setPreviousPrice(66000.00);
//        QShopItemStructure.setCurrentPrice(61999.99);
//        QShopItemStructure.setItemCount(1);
//        QShopItemStructure.setImage(R.drawable.cart_a);
//        QShopItemStructure.setImage_url("https://i5.walmartimages.com/dfw/4ff9c6c9-126c/k2-_142b2c31-3cd2-49c3-9fbb-9ffdbe4b20ef.v1.jpg");
//        cartItemsArray.add(QShopItemStructure);
//
//        QShopItemStructure = new QShopItemStructure();
//        QShopItemStructure.setItemTitle("Rolex Edirio");
//        QShopItemStructure.setPreviousPrice(18000.00);
//        QShopItemStructure.setCurrentPrice(10999.99);
//        QShopItemStructure.setItemCount(1);
//        QShopItemStructure.setImage(R.drawable.cart_b);
//        QShopItemStructure.setImage_url("https://static.highsnobiety.com/wp-content/uploads/2017/01/26184733/lenny-kravitz-rolex-interview-02.jpg");
//        cartItemsArray.add(QShopItemStructure);
//
//        QShopItemStructure = new QShopItemStructure();
//        QShopItemStructure.setItemTitle("Sony Extra Bass");
//        QShopItemStructure.setPreviousPrice(5000.00);
//        QShopItemStructure.setCurrentPrice(4200.00);
//        QShopItemStructure.setItemCount(1);
//        QShopItemStructure.setImage(R.drawable.cart_c);
//        QShopItemStructure.setImage_url("http://img.bbystatic.com/BestBuy_US/images/products/5739/5739304_ra.jpg");
//        cartItemsArray.add(QShopItemStructure);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void toggleMenu(View v){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }
}
