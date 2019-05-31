package md_arshad.qshop.Activity.InnerActivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import md_arshad.qshop.Adapter.CategoryItemGridViewAdapter;
import md_arshad.qshop.Adapter.ItemWisePageAdapter;
import md_arshad.qshop.Fragments.CartFragment;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;
import me.relex.circleindicator.CircleIndicator;

public class ItemWise extends AppCompatActivity {

    ViewPager mPager;
    CircleIndicator mCircleIndicator;
    ArrayList<Integer> imgs;
    private static QShopItemStructure item;

    public static void newItemWiseActivity(Context startActivity, QShopItemStructure item){
        ItemWise.item = item;
        Intent intent = new Intent(startActivity,ItemWise.class);
        startActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_wise);

        imgs = new ArrayList<>();
        imgs.add(R.drawable.shirt_1);
        imgs.add(R.drawable.shirt_2);
        imgs.add(R.drawable.shirt_4);
        imgs.add(R.drawable.shirt_3);
        imgs.add(R.drawable.jeans_1);
        imgs.add(R.drawable.jeans_2);
        imgs.add(R.drawable.jeans_3);
        imgs.add(R.drawable.jeans_5);

        TextView companyName = (TextView) findViewById(R.id.item_company_name);
        companyName.setText(item.getCompanyName());
        TextView itemName = (TextView) findViewById(R.id.item_name);
        itemName.setText(item.getItemName());
        TextView currentPrice = (TextView) findViewById(R.id.item_current_price);
        currentPrice.setText(QShopItemStructure.getPriceString(item.getCurrentPrice()));
        TextView previousPrice = (TextView) findViewById(R.id.item_previous_price);
        previousPrice.setText(QShopItemStructure.getPriceString(item.getPreviousPrice()));
        previousPrice.setPaintFlags(previousPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView offerTag = (TextView) findViewById(R.id.offer_text_view);
        offerTag.setText("( "+item.offerPercent()+" )");
        TextView itemDescription = (TextView) findViewById(R.id.item_description);
        itemDescription.setText(item.getItemDescription());
        item.setItemCount(1);

        if(item.isInCart()){
            TextView textView = (TextView) findViewById(R.id.add_cart_btn_text);
            textView.setText("REMOVE FROM CART");
        }

        if(item.isInWishList()){
            TextView textView = (TextView) findViewById(R.id.wishlist_btn_text);
            textView.setText("REMOVE FROM WISHLIST");
        }

        mPager = (ViewPager) findViewById(R.id.item_wise_view_pager);
        mCircleIndicator = (CircleIndicator) findViewById(R.id.item_wise_circle_indicator);
        ImageView backBtn = (ImageView) findViewById(R.id.go_back_button);
        mPager.setAdapter(new ItemWisePageAdapter(getApplicationContext(),imgs));
        mCircleIndicator.setViewPager(mPager);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayout saveToWishListBtn = (LinearLayout) findViewById(R.id.save_to_wish_list_btn);
        LinearLayout addToCartBtn = (LinearLayout) findViewById(R.id.add_to_cart_btn);

        saveToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isInWishList()){
                    Toast.makeText(getApplicationContext(),"Removed from wishlist",Toast.LENGTH_SHORT).show();
                    TextView textView = (TextView) v.findViewById(R.id.wishlist_btn_text);
                    textView.setText("SAVE TO WISHLIST");
                    WishlistActivity.removeFromWishlist(item);
                }else{
                    Toast.makeText(getApplicationContext(),"Added to wishlist",Toast.LENGTH_SHORT).show();
                    TextView textView = (TextView) v.findViewById(R.id.wishlist_btn_text);
                    textView.setText("REMOVE FROM WISHLIST");
                    WishlistActivity.addToWishlist(item);
                }
                CategoryWise.categoryItemGridViewAdapter.notifyDataSetChanged();
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isInCart()){
                    Toast.makeText(getApplicationContext(),"Removed from cart",Toast.LENGTH_SHORT).show();
                    TextView t= (TextView) v.findViewById(R.id.add_cart_btn_text);
                    t.setText("ADD TO CART");
                    CartFragment.removeFromCart(item);
                }else{
                    Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_SHORT).show();
                    TextView t= (TextView) v.findViewById(R.id.add_cart_btn_text);
                    t.setText("REMOVE FROM CART");
                    CartFragment.addToCart(item);
                }
            }
        });
    }
}
