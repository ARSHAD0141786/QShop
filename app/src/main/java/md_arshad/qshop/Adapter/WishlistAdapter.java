package md_arshad.qshop.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import md_arshad.qshop.Activity.InnerActivities.ItemWise;
import md_arshad.qshop.Activity.InnerActivities.WishlistActivity;
import md_arshad.qshop.Fragments.CartFragment;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;

public class WishlistAdapter extends BaseAdapter {
    private Context mContext;
    public WishlistAdapter(Context c){
        mContext = c;
        if(WishlistActivity.wishlistData.size()==0){
            WishlistActivity.wishlistGridView.setVisibility(View.GONE);
            WishlistActivity.defaultWistlist.setVisibility(View.VISIBLE);
        }else{
            WishlistActivity.wishlistGridView.setVisibility(View.VISIBLE);
            WishlistActivity.defaultWistlist.setVisibility(View.GONE);
        }
    }
    @Override
    public int getCount() {
        return WishlistActivity.wishlistData.size();
    }

    @Override
    public Object getItem(int position) {
        return WishlistActivity.wishlistData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        if(WishlistActivity.wishlistData.size()==0){
            WishlistActivity.wishlistGridView.setVisibility(View.GONE);
            WishlistActivity.defaultWistlist.setVisibility(View.VISIBLE);
        }else{
            WishlistActivity.wishlistGridView.setVisibility(View.VISIBLE);
            WishlistActivity.defaultWistlist.setVisibility(View.GONE);
        }
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.wishlist_item,null);
        }

        ImageView itemImage = (ImageView) view.findViewById(R.id.item_image_view);

        //category take only first image of item come from api...
        itemImage.setImageResource(WishlistActivity.wishlistData.get(position).getImage()[0]);
//        itemImage.setImageResource(R.drawable.shirt_1);

        TextView itemTitle = (TextView) view.findViewById(R.id.item_title);
        itemTitle.setText(WishlistActivity.wishlistData.get(position).getItemTitle());

        TextView itemDescription = (TextView) view.findViewById(R.id.item_description_text_view);
        itemDescription.setText(WishlistActivity.wishlistData.get(position).getItemDescription());

        TextView currentPrice = (TextView) view.findViewById(R.id.item_current_price_text_view);
        currentPrice.setText(CartItemGridViewAdapter.getPrice(WishlistActivity.wishlistData.get(position).getCurrentPrice()));

        TextView previousPrice = (TextView) view.findViewById(R.id.item_previous_price);
        previousPrice.setText(CartItemGridViewAdapter.getPrice(WishlistActivity.wishlistData.get(position).getPreviousPrice()));
        previousPrice.setPaintFlags(previousPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView offerPercent = (TextView) view.findViewById(R.id.offer_text_view);
        offerPercent.setText(WishlistActivity.wishlistData.get(position).offerPercent());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send item id from here when apis are complete
                ItemWise.newItemWiseActivity(mContext,WishlistActivity.wishlistData.get(position));
            }
        });

        final ImageView deleteFromWishListBtn = (ImageView) view.findViewById(R.id.delete_item_form_wishlist_btn);
        deleteFromWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistActivity.removeFromWishlist(WishlistActivity.wishlistData.get(position));
                Toast.makeText(mContext,"Removed from wishlist",Toast.LENGTH_SHORT).show();
            }
        });

        final TextView moveToCartBtn = (TextView) view.findViewById(R.id.move_to_cart_btn);
        moveToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.addToCart(WishlistActivity.wishlistData.get(position));
                WishlistActivity.removeFromWishlist(WishlistActivity.wishlistData.get(position));
                Toast.makeText(mContext,"Successfully moved to cart",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
