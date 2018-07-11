package tools.wpfang.showmessageactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int MESSAGE_REQ =1 ;
    private static final int REQUEST_CAMERA =2 ;
    EditText send_ed;
    TextView tvr;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_ed=findViewById(R.id.send_edit);
        tvr=findViewById(R.id.response_text);
        iv = findViewById(R.id.imageView);


//------------------ my browser  ----
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String uri_string = "URI: " + uri.toString();
            tvr.setText(uri_string);
        }
        if(savedInstanceState!=null)
        {
            String s1=savedInstanceState.getString("txtvalue");
            tvr.setText(s1);
        }
    }

    public void sendMessage(View view) {
        Intent it=new Intent(this,SecondScreen.class);
        it.putExtra("send_text",send_ed.getText().toString());
      //  startActivity(it);
        startActivityForResult(it,MESSAGE_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==MESSAGE_REQ)
        {
            if(resultCode==RESULT_OK)
            {
                Bundle b1=data.getExtras();
                tvr.setText(b1.getString("response_msg"));
            }
        }
        else if(requestCode==REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap bmp = (Bitmap) extras.get("data");
                iv.setImageBitmap(bmp);
            }
        }
    }

    public void openCamera(View view) {
            Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(it, REQUEST_CAMERA);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("txtvalue",tvr.getText().toString());
        Log.i("wp","rec:"+tvr.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
