package md_arshad.qshop.Activity.InnerActivities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

import md_arshad.qshop.Adapter.CategoryItemGridViewAdapter;
import md_arshad.qshop.Adapter.WishlistAdapter;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;

public class WishlistActivity extends AppCompatActivity {

    public static ArrayList<QShopItemStructure> wishlistData = new ArrayList<>();
    public static WishlistAdapter wishlistAdapter;
    public static GridView wishlistGridView;
    public static ConstraintLayout defaultWistlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        defaultWistlist = (ConstraintLayout) findViewById(R.id.default_wishlist);
        wishlistGridView = (GridView) findViewById(R.id.wish_list_grid_view);
        wishlistAdapter = new WishlistAdapter(this);
        wishlistGridView.setAdapter(wishlistAdapter);
    }

    public static void addToWishlist(QShopItemStructure item){
        item.setInWishList(true);
        WishlistActivity.wishlistData.add(item);
        if(WishlistActivity.wishlistAdapter!=null)
            WishlistActivity.wishlistAdapter.notifyDataSetChanged();
    }
    public static void removeFromWishlist(QShopItemStructure item){
        item.setInWishList(false);
        WishlistActivity.wishlistData.remove(item);
        if(WishlistActivity.wishlistAdapter!=null)
            WishlistActivity.wishlistAdapter.notifyDataSetChanged();
    }
    public void goBack(View v){
        finish();
    }
}
