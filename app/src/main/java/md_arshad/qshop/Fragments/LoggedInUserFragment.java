package md_arshad.qshop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import org.w3c.dom.Text;

import md_arshad.qshop.Activity.MainActivity;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.UserDataStructure;

public class LoggedInUserFragment extends Fragment {

    private UserDataStructure userDataStructure;
    public static LoggedInUserFragment newInstance(UserDataStructure userData){
        LoggedInUserFragment userFragment = new LoggedInUserFragment();
        View v = MainActivity.menuHeaderLayout;
        ImageView userImage = (ImageView) v.findViewById(R.id.user_icon_image_view_nav_header);
        userImage.setImageResource(R.drawable.user_photo);
        TextView userName = (TextView) v.findViewById(R.id.name_text_View_nav_header);
        userName.setText(userData.getName());
        TextView userAddress = (TextView) v.findViewById(R.id.address_text_view_nav_header);
        userAddress.setText(userData.getAddress());
        return userFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.user_account_fragment, container, false);
        ImageView user_image = (ImageView) v.findViewById(R.id.user_icon_image);
        //user_image.setImageUrl(MainActivity.mUserDataStructure.getImage_url());
        Button logoutBtn = (Button) v.findViewById(R.id.logout_btn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.isUserLoggedIn = false;
                MainActivity.mUserDataStructure = null;

                // change menu tray header
                View view = MainActivity.menuHeaderLayout;
                ImageView userImage = (ImageView) view.findViewById(R.id.user_icon_image_view_nav_header);
                userImage.setImageResource(R.drawable.q_shop_icon);
                TextView userName = (TextView) view.findViewById(R.id.name_text_View_nav_header);
                userName.setText("Sign In");
                TextView userAddress = (TextView) view.findViewById(R.id.address_text_view_nav_header);
                userAddress.setText("to explore more features");

                // change the fragment
                Fragment fragment= new SignInFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.user_fragment_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.commit();

                // delete shared preferences
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
            }
        });
        return v;
    }
}
