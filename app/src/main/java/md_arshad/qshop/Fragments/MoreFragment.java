package md_arshad.qshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import md_arshad.qshop.R;

public class MoreFragment extends Fragment {

    public static MoreFragment newInstance(){
        MoreFragment moreFragment = new MoreFragment();
        return moreFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.more_content_fragment,container,false);
        return v;
    }
}
