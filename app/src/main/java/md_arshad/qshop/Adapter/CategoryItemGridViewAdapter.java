package md_arshad.qshop.Adapter;

import android.content.Context;
import android.graphics.Paint;
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
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;

public class CategoryItemGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<QShopItemStructure> mData;

    public CategoryItemGridViewAdapter(Context mContext, ArrayList<QShopItemStructure> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.category_item,null);
        }

        ImageView itemImage = (ImageView) view.findViewById(R.id.item_image_view);

        //category take only first image of item come from api...
        itemImage.setImageResource(mData.get(position).getImage()[0]);
//        itemImage.setImageResource(R.drawable.shirt_1);

        TextView itemTitle = (TextView) view.findViewById(R.id.item_title);
        itemTitle.setText(mData.get(position).getItemTitle());

        TextView itemDescription = (TextView) view.findViewById(R.id.item_description_text_view);
        itemDescription.setText(mData.get(position).getItemDescription());

        TextView currentPrice = (TextView) view.findViewById(R.id.item_current_price_text_view);
        currentPrice.setText(CartItemGridViewAdapter.getPrice(mData.get(position).getCurrentPrice()));

        TextView previousPrice = (TextView) view.findViewById(R.id.item_previous_price);
        previousPrice.setText(CartItemGridViewAdapter.getPrice(mData.get(position).getPreviousPrice()));
        previousPrice.setPaintFlags(previousPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView offerPercent = (TextView) view.findViewById(R.id.offer_text_view);
        offerPercent.setText(mData.get(position).offerPercent());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send item id from here when apis are complete

                ItemWise.newItemWiseActivity(mContext,mData.get(position));
            }
        });

        final ImageView bookmark = (ImageView) view.findViewById(R.id.bookmark_icon);
        if(mData.get(position).isInWishList()){
            bookmark.setImageResource(R.drawable.ic_bookmark_fill);
        }else{
            bookmark.setImageResource(R.drawable.ic_bookmark_empty);
        }
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mData.get(position).isInWishList()) {
                    mData.get(position).setInWishList(false);
                    WishlistActivity.removeFromWishlist(mData.get(position));
                    bookmark.setImageResource(R.drawable.ic_bookmark_empty);
                    Toast.makeText(mContext,"Removed Successfully",Toast.LENGTH_SHORT).show();
                }else{
                    mData.get(position).setInWishList(true);
                    WishlistActivity.addToWishlist(mData.get(position));
                    bookmark.setImageResource(R.drawable.ic_bookmark_fill);
                    Toast.makeText(mContext,"Added Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
