package a9chou.com.fangjiazhuangApp.module.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import a9chou.com.fangjiazhuangApp.R;
import a9chou.com.fangjiazhuangApp.module.MainActivity;

public class WhiteActibity extends AppCompatActivity {

    private String mSessionid;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_actibity);

          Intent intent = getIntent();

        mSessionid = intent.getStringExtra("sessionid");
        mId = intent.getStringExtra("Id");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WhiteActibity.this, MainActivity.class);
                i.putExtra("sss", mSessionid);
                i.putExtra("id", mId);
                startActivity(i);
            }
        });
    }
}
