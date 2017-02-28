package deliveryapp.yudala.com.deliveryapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kpono on 1/13/2017.
 */

public class MainActivity extends AppCompatActivity {
    String NAME=null, PASSWORD=null, EMAIL=null;
    Context ctx=this;
    private EditText tvEmail, tvPassword;
    private TextView tvSignUp;
    private Button btnMainactivity;
    String Name, Password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmail = (EditText) findViewById(R.id.mainactivity_email);
        tvPassword = (EditText) findViewById(R.id.mainactivity_password);
        btnMainactivity = (Button) findViewById(R.id.mainactivity_btnMainactivity);



//        btnMainactivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = tvEmail.getText().toString().trim();
//                String pass = tvPassword.getText().toString().trim();
//
//                if(email.equals("") || pass.equals("") || !email.contains("@")){
//                    Toast.makeText(MainActivity.this,"Enter a valid authentication detail",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                Data data = new Data(MainActivity.this);
//
//                if(email.equals(data.getUSERNAME())&& pass.equals(data.getPASSWORD())){
//                    Intent i = new Intent(MainActivity.this,MainActivity.class);
//                    startActivity(i);
//                }else{
//                    Toast.makeText(MainActivity.this,"Invalid Login Details", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



    }

    public void main_login(View v){
        Name = tvEmail.getText().toString().trim();
        Password =tvPassword.getText().toString().trim();
        BackGround b = new BackGround();
        b.execute(Name, Password);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://yudala.com/api/getDispatchedOrders.php");
                String urlParams = "email="+name;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");
                EMAIL = user_data.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            Intent i = new Intent(ctx, Main2Activity.class);
//            i.putExtra("name", NAME);
//            i.putExtra("password", PASSWORD);
//            i.putExtra("email", EMAIL);
//            i.putExtra("err", err);
            startActivity(i);

        }
    }
}


