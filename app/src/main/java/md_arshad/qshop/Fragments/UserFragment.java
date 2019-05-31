package md_arshad.qshop.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import md_arshad.qshop.Activity.MainActivity;
import md_arshad.qshop.R;

public class UserFragment extends Fragment {

    public static UserFragment newInstance(){
        UserFragment userFragment = new UserFragment();
        return userFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_fragment,container,false);
        if(MainActivity.isUserLoggedIn){
            Fragment fragment = LoggedInUserFragment.newInstance(MainActivity.mUserDataStructure);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.user_fragment_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
//                transaction.addToBackStack(null);  // this will manage backstack
            transaction.commit();
        }else {
            Fragment fragment = SignInFragment.newInstance();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.user_fragment_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
//                transaction.addToBackStack(null);  // this will manage backstack
            transaction.commit();
        }
        return v;
    }
}
