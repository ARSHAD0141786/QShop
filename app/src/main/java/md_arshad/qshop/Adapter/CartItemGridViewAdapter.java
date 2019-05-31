package md_arshad.qshop.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import md_arshad.qshop.Activity.InnerActivities.ItemWise;
import md_arshad.qshop.Fragments.CartFragment;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;

public class CartItemGridViewAdapter extends BaseAdapter {

    Context mContext;
    double total_amount = 0.0;
    public CartItemGridViewAdapter(Context context){
        mContext = context;
        if(CartFragment.cartItemsArray.size()==0){
            CartFragment.cartItemGridView.setVisibility(View.GONE);
            CartFragment.cartTotaLayout.setVisibility(View.GONE);
            CartFragment.cartDefaultView.setVisibility(View.VISIBLE);
        }else{
            CartFragment.cartDefaultView.setVisibility(View.GONE);
            CartFragment.cartTotaLayout.setVisibility(View.VISIBLE);
            CartFragment.cartItemGridView.setVisibility(View.VISIBLE);
        }
        for(int i=0;i<CartFragment.cartItemsArray.size();i++){
            total_amount+=CartFragment.cartItemsArray.get(i).getCurrentPrice() * CartFragment.cartItemsArray.get(i).getItemCount();
        }
        CartFragment.totalCartAmountTextView.setText(getPrice(total_amount));
        CartFragment.total_cart_amount = total_amount;
    }


    @Override
    public void notifyDataSetChanged() {
        total_amount=0.0;
        if(CartFragment.cartItemsArray.size()==0){
            CartFragment.cartItemGridView.setVisibility(View.GONE);
            CartFragment.cartTotaLayout.setVisibility(View.GONE);
            CartFragment.cartDefaultView.setVisibility(View.VISIBLE);
        }else{
            CartFragment.cartDefaultView.setVisibility(View.GONE);
            CartFragment.cartTotaLayout.setVisibility(View.VISIBLE);
            CartFragment.cartItemGridView.setVisibility(View.VISIBLE);
        }
        for(int i=0;i<CartFragment.cartItemsArray.size();i++){
            total_amount+=CartFragment.cartItemsArray.get(i).getCurrentPrice() * CartFragment.cartItemsArray.get(i).getItemCount();
        }
        CartFragment.totalCartAmountTextView.setText(getPrice(total_amount));
        CartFragment.total_cart_amount = total_amount;
        super.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return CartFragment.cartItemsArray.size();
    }

    @Override
    public Object getItem(int position) {
        return CartFragment.cartItemsArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.cart_item_layout,null);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemWise.newItemWiseActivity(mContext,CartFragment.cartItemsArray.get(position));
            }
        });

        TextView title = (TextView) convertView.findViewById(R.id.cart_item_title);
        title.setText(CartFragment.cartItemsArray.get(position).getItemTitle());

        TextView previousPrice = (TextView) convertView.findViewById(R.id.cart_item_previous_price);
        previousPrice.setText(getPrice(CartFragment.cartItemsArray.get(position).getPreviousPrice()));
        previousPrice.setPaintFlags(previousPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView latestPrice = (TextView) convertView.findViewById(R.id.cart_item_latest_price);
        latestPrice.setText(getPrice(CartFragment.cartItemsArray.get(position).getCurrentPrice()));

        final TextView itemCount = (TextView) convertView.findViewById(R.id.cart_item_count);
        itemCount.setText(CartFragment.cartItemsArray.get(position).getItemCount()+"");

        ImageView cartItemImage = (ImageView) convertView.findViewById(R.id.cart_item_image);
        cartItemImage.setImageResource(CartFragment.cartItemsArray.get(position).getImage()[0]);

        final Button decCount = (Button) convertView.findViewById(R.id.decrement_count_btn);

        Button incCount = (Button) convertView.findViewById(R.id.increment_count_btn);

        ImageButton deleteItem = (ImageButton) convertView.findViewById(R.id.delete_item_button);
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 TODO: show dialog before delete item
                CartFragment.cartItemsArray.remove(position);
                CartItemGridViewAdapter.this.notifyDataSetChanged();
            }
        });
        if(CartFragment.cartItemsArray.get(position).getItemCount()<=1) {
            decCount.setEnabled(false);
            CartFragment.cartItemsArray.get(position).setItemCount(1);
        }
        decCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CartFragment.cartItemsArray.get(position).getItemCount()>1) {
                    CartFragment.cartItemsArray.get(position).setItemCount(CartFragment.cartItemsArray.get(position).getItemCount() - 1);
                    itemCount.setText(CartFragment.cartItemsArray.get(position).getItemCount()+"");
                    total_amount-=CartFragment.cartItemsArray.get(position).getCurrentPrice();
                    CartFragment.totalCartAmountTextView.setText(getPrice(total_amount));
                }
                if(CartFragment.cartItemsArray.get(position).getItemCount()<=1)
                    decCount.setEnabled(false);

            }
        });
        incCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decCount.setEnabled(true);
                CartFragment.cartItemsArray.get(position).setItemCount(CartFragment.cartItemsArray.get(position).getItemCount() + 1);
                itemCount.setText(CartFragment.cartItemsArray.get(position).getItemCount()+"");
                total_amount+=CartFragment.cartItemsArray.get(position).getCurrentPrice();
                CartFragment.totalCartAmountTextView.setText(getPrice(total_amount));
            }
        });
//        Bitmap image;
//        try {
//            image = new DownloadImage().execute(CartFragment.cartItemsArray.get(position).getImage_url()).get();
//            cartItemImage.setImageBitmap(image);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        return convertView;
    }

    public static String getPrice(double price){
        String priceString = "\u20B9 ";
        int temp = (int) Math.floor(price);

        float x = (float)(price-temp);
        if(price>9999999){
            priceString += temp/10000000 + ",";
            temp=(int)temp%10000000;
            if(temp<=999999) priceString+="0";
        }
        if(price>99999){
            priceString += temp/100000 + ",";
            temp=(int)temp%100000;
            if(temp<=9999) priceString+="0";
        }
        if(price>999){
            priceString += temp/1000 + ",";
            temp=(int)temp%1000;
            if(temp<=9) priceString+="00";
            else if(temp<=99) priceString+="0";
        }
        priceString += String.format("%.2f",temp+x);
        return priceString;
    }
    private class DownloadImage extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
