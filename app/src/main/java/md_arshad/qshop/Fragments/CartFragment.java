package md_arshad.qshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import md_arshad.qshop.Adapter.CartItemGridViewAdapter;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.QShopItemStructure;

public class CartFragment extends Fragment {

    public static ArrayList<QShopItemStructure> cartItemsArray;
    public static double total_cart_amount;
    public static TextView totalCartAmountTextView;
    public static ConstraintLayout cartDefaultView;
    public static GridView cartItemGridView;
    public static ConstraintLayout cartTotaLayout;
    public static CartItemGridViewAdapter cartAdapter;

    public static void addToCart(QShopItemStructure item){
        item.setInCart(true);
        item.setItemCount(1);
        CartFragment.cartItemsArray.add(item);
        if(CartFragment.cartAdapter!=null)
            CartFragment.cartAdapter.notifyDataSetChanged();
    }
    public static void removeFromCart(QShopItemStructure item){
        item.setInCart(false);
        item.setItemCount(0);
        CartFragment.cartItemsArray.remove(item);
        if(CartFragment.cartAdapter!=null)
            CartFragment.cartAdapter.notifyDataSetChanged();
    }

    public static CartFragment newInstance(ArrayList<QShopItemStructure> cartData){
        CartFragment cartFragment = new CartFragment();
        cartFragment.cartItemsArray = cartData;
        return cartFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.cart_fragment, container, false);

        totalCartAmountTextView = (TextView) v.findViewById(R.id.cart_total_amount);

        cartDefaultView =(ConstraintLayout) v.findViewById(R.id.shopping_cart_default_view);
        cartTotaLayout = (ConstraintLayout) v.findViewById(R.id.cart_total_layout);
        cartItemGridView = (GridView) v.findViewById(R.id.cart_grid_view);

        Button payButton = (Button) v.findViewById(R.id.cart_pay_button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Payment method is not completed yet",Toast.LENGTH_LONG).show();
            }
        });
        if(CartFragment.cartItemsArray.size()==0){
            CartFragment.cartItemGridView.setVisibility(View.GONE);
            CartFragment.cartTotaLayout.setVisibility(View.GONE);
            CartFragment.cartDefaultView.setVisibility(View.VISIBLE);
        }else{
            CartFragment.cartDefaultView.setVisibility(View.GONE);
            CartFragment.cartTotaLayout.setVisibility(View.VISIBLE);
            CartFragment.cartItemGridView.setVisibility(View.VISIBLE);
        }
        cartAdapter = new CartItemGridViewAdapter(getContext());
        cartItemGridView.setAdapter(cartAdapter);

        return v;
    }
}
