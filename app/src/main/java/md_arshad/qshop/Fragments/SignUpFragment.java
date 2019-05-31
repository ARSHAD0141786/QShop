package md_arshad.qshop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import md_arshad.qshop.Network.NetworkMethods;
import md_arshad.qshop.R;

public class SignUpFragment extends Fragment {

    public static SignUpFragment newInstance(){
        SignUpFragment signUpFragment = new SignUpFragment();
        return signUpFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sign_up_fragment,container,false);
        final EditText name = (EditText) v.findViewById(R.id.sign_in_username);
        final EditText email = (EditText) v.findViewById(R.id.sign_in_email);
        final EditText phone = (EditText) v.findViewById(R.id.sign_in_phone_number);
        final EditText password = (EditText) v.findViewById(R.id.sign_in_password);
        Button btn = (Button) v.findViewById(R.id.sign_up_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Signing up...",Toast.LENGTH_SHORT).show();
                NetworkMethods.signUp(name.getText().toString(),email.getText().toString(),phone.getText().toString(),password.getText().toString());
            }
        });
        return v;
    }
}
