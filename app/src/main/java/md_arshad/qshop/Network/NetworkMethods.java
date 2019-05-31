package md_arshad.qshop.Network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import md_arshad.qshop.Activity.MainActivity;
import md_arshad.qshop.R;
import md_arshad.qshop.Structures.UserDataStructure;

public class NetworkMethods {
    public static void signUp(String name,String email,String phone,String password){
        AndroidNetworking.post(URL.BASE_URL)
                .addBodyParameter("action", "signup")
                .addBodyParameter("name", name)
                .addBodyParameter("email", email)
                .addBodyParameter("phone", phone)
                .addBodyParameter("password", password)
                .setTag("signup")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response",response.toString());
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("Error Response",error.toString());
                    }
                });
    }

    public static void signIn(String email,String password){
        AndroidNetworking.post(URL.BASE_URL)
                .addBodyParameter("action", "signin")
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setTag("signin")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //{"0":"12","id":"12","1":"MOHAMMED ARSHAD","name":"MOHAMMED ARSHAD","2":"arshad0141786@gmail.com","email":"arshad0141786@gmail.com","3":"pbkdf2$10000$b62d958a0e8794f27896abd29fe5d114d872c1d37311dad5a5c4059f3c49864adc1c9d9abfcf5ff3640a611ee4d410d4ce3bd9f6fb63c69d8cc55ca3b9c1de5b38e842af30fe9a851d420ad6d20d8d6c9e8619e88423088744117b7b17d90e6f655aeea1e875565c6566e1b28b03e3804c581b2b5d93ea74c171e4b96293dc64$c3218528d1d72525400c4c0651a5e04d7d5ec68ceac6764a6122c28535432e3bbd9dee22efa9cd694f790818db6b7482a333a68ced61e21ad8e5138ff2ee8591124050cd38a6cbf9ae7a27133eec73ce15ec3fab513fc867632d1e5761f3f82f494ac17b973af62a11bf53ff81ec1fa5fa24247e5dfbcb53d37d9f536b1b304ba9a928b49321b368ba8b6c80cf70297e22374bc5f937dd8d9f9eeb7651c29f3b29bea673a3ac819f05c39c59ac10519592d58086cd27261a94a9024d4471dbb3cfb9696519666522d19004b3f7b8ac841ebee94d99a140a0ecdcce4696f308a832542370130d9a7e422500d172cd708b3a805792b1a7044e374f4c506a07fd27","password":"pbkdf2$10000$b62d958a0e8794f27896abd29fe5d114d872c1d37311dad5a5c4059f3c49864adc1c9d9abfcf5ff3640a611ee4d410d4ce3bd9f6fb63c69d8cc55ca3b9c1de5b38e842af30fe9a851d420ad6d20d8d6c9e8619e88423088744117b7b17d90e6f655aeea1e875565c6566e1b28b03e3804c581b2b5d93ea74c171e4b96293dc64$c3218528d1d72525400c4c0651a5e04d7d5ec68ceac6764a6122c28535432e3bbd9dee22efa9cd694f790818db6b7482a333a68ced61e21ad8e5138ff2ee8591124050cd38a6cbf9ae7a27133eec73ce15ec3fab513fc867632d1e5761f3f82f494ac17b973af62a11bf53ff81ec1fa5fa24247e5dfbcb53d37d9f536b1b304ba9a928b49321b368ba8b6c80cf70297e22374bc5f937dd8d9f9eeb7651c29f3b29bea673a3ac819f05c39c59ac10519592d58086cd27261a94a9024d4471dbb3cfb9696519666522d19004b3f7b8ac841ebee94d99a140a0ecdcce4696f308a832542370130d9a7e422500d172cd708b3a805792b1a7044e374f4c506a07fd27","4":"8441975563","phone_number":"8441975563","5":null,"city":null,"6":null,"state":null,"7":null,"user_image":null,"8":"2018-07-19 11:27:51","created":"2018-07-19 11:27:51"}

                        try {
                            response.getString("name");

                        Log.i("response",response.toString());
                        if (MainActivity.mUserDataStructure == null)
                            MainActivity.mUserDataStructure = new UserDataStructure();


                        MainActivity.mUserDataStructure.setName(response.getString("name"));
                        MainActivity.mUserDataStructure.setPhone(response.getString("phone_number"));
//                        MainActivity.mUserDataStructure.setImage_res(R.drawable.user_photo);
                        MainActivity.mUserDataStructure.setImage_url(response.getString("user_image"));
                        MainActivity.mUserDataStructure.setEmail(response.getString("email"));
                        if(response.getString("city")!=null) {
                            MainActivity.mUserDataStructure.setAddress(response.getString("state")+","+response.getString("country"));
                        }else{
                            MainActivity.mUserDataStructure.setAddress("");
                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("Error Response",error.toString());
                    }
                });
    }
}
