package deliveryapp.yudala.com.deliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kpono on 1/13/2017.
 */
public class MainActivity extends AppCompatActivity {
    private EditText tvEmail, tvPassword;
    private TextView tvSignUp;
    private Button btnMainactivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmail = (EditText) findViewById(R.id.mainactivity_email);
        tvPassword = (EditText) findViewById(R.id.mainactivity_password);
        btnMainactivity = (Button) findViewById(R.id.mainactivity_btnMainactivity);



        btnMainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tvEmail.getText().toString().trim();
                String pass = tvPassword.getText().toString().trim();

                if(email.equals("") || pass.equals("") || !email.contains("@")){
                    Toast.makeText(MainActivity.this,"Enter a valid authentication detail",Toast.LENGTH_SHORT).show();
                    return;
                }

                Data data = new Data(MainActivity.this);

                if(email.equals(data.getUSERNAME())&& pass.equals(data.getPASSWORD())){
                    Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this,"Invalid Login Details", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
