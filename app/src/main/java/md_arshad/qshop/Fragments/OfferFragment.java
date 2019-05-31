package md_arshad.qshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import md_arshad.qshop.R;

public class OfferFragment extends Fragment {

    static ArrayList<Integer> mOffersImages = new ArrayList<>();

    static {
        mOffersImages.add(R.drawable.offer_a);
        mOffersImages.add(R.drawable.offer_b);
        mOffersImages.add(R.drawable.offer_a);
        mOffersImages.add(R.drawable.offer_b);
        mOffersImages.add(R.drawable.offer_a);
    }

    public static OfferFragment newInstance(){
        OfferFragment offerFragment = new OfferFragment();
        return offerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.offer_fragment,container,false);
        RecyclerView recyclerView = v.findViewById(R.id.offer_fragment_recycler_view);
        recyclerView.setAdapter(new RecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_banner_single_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imageView.setImageResource(mOffersImages.get(position));
        }

        @Override
        public int getItemCount() {
            return mOffersImages.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            public ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.offer_banner_card_view_image_view);
            }
        }
    }
}
