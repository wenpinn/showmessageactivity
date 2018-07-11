package tools.wpfang.showmessageactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondScreen extends AppCompatActivity {
    TextView tv;
    EditText tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        tv=findViewById(R.id.message_receviced);
       Bundle bun= getIntent().getExtras();
       String s1=bun.getString("send_text");
       tv.setText(s1);
       tv2=findViewById(R.id.reply_ed);
    }

    public void responseMessage(View view) {
        String s1=tv2.getText().toString();
        Intent i1=new Intent();
        i1.putExtra("response_msg",s1);
        setResult(RESULT_OK,i1);
        finish();

    }
}
