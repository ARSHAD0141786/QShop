package md_arshad.qshop.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import md_arshad.qshop.Activity.MainActivity;
import md_arshad.qshop.Network.NetworkMethods;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.UserDataStructure;

public class SignInFragment extends Fragment {

    public static SignInFragment newInstance(){
        SignInFragment signInFragment = new SignInFragment();
        return signInFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.sign_in_fragment, container, false);
        Button signIn = (Button) v.findViewById(R.id.sign_in_btn);
        Button signUp = (Button) v.findViewById(R.id.sign_up_btn);
        final EditText username = (EditText) v.findViewById(R.id.sign_in_username);
        final EditText password = (EditText) v.findViewById(R.id.sign_in_password);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    //check username and password API work

                    NetworkMethods.signIn("arshad0141786@gmail.com", "arshad01");
                    if (MainActivity.mUserDataStructure == null)
                        MainActivity.mUserDataStructure = new UserDataStructure();

                    //TODO: data come from API here

                    MainActivity.mUserDataStructure.setName("Mohammed Arshad");
                    MainActivity.mUserDataStructure.setPhone("+91 8441975563");
//                    MainActivity.mUserDataStructure.setImage_res(R.drawable.user_photo);
                    MainActivity.mUserDataStructure.setImage_url("http://user_photo.jpg");
                    MainActivity.mUserDataStructure.setEmail("arshadmohammed0141@gmail.com");
                    MainActivity.mUserDataStructure.setAddress("Rajasthan,India");


                    //save data in phone memory for future use

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(getString(R.string.saved_user_login_info), true);
                    editor.putString(getString(R.string.saved_user_name), MainActivity.mUserDataStructure.getName());
                    editor.putString(getString(R.string.saved_address), MainActivity.mUserDataStructure.getAddress());
                    editor.putString(getString(R.string.saved_phone_number), MainActivity.mUserDataStructure.getPhone());
                    editor.putString(getString(R.string.saved_image_url), MainActivity.mUserDataStructure.getImage_url());
                    editor.putString(getString(R.string.saved_email_id), MainActivity.mUserDataStructure.getEmail());
                    editor.commit();

                    Fragment fragment = LoggedInUserFragment.newInstance(MainActivity.mUserDataStructure);
                    MainActivity.isUserLoggedIn = true;
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.user_fragment_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                    transaction.commit();

                } else {
                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    username.findFocus();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load Sign Up Fragment here
//                SignUpFragment fragment = SignUpFragment.newInstance();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, fragment);

                Fragment fragment= new SignUpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                getActivity().getSupportFragmentManager().getFragments().get(0).onDestroy();
                transaction.replace(R.id.user_fragment_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });
        return v;
    }
}
