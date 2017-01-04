package hm.com.changeapplogodemo;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLog1, btnLog2;
    private ComponentName mDefault;
    private ComponentName mDouble11;
    private ComponentName mDouble12;
    private PackageManager mPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLog1 = (Button) findViewById(R.id.btn_log1);
        btnLog2 = (Button) findViewById(R.id.btn_log2);

        btnLog1.setOnClickListener(this);
        btnLog2.setOnClickListener(this);

        mDefault = getComponentName();
        mDouble11 = new ComponentName(
                getBaseContext(),
                "hm.com.changeapplogodemo.logo1");
        mDouble12 = new ComponentName(
                getBaseContext(),
                "hm.com.changeapplogodemo.logo2");
        mPm = getApplicationContext().getPackageManager();

    }

    @Override
    public void onClick(View v) {
        if (v == btnLog1) {
            disableComponent(mDefault);
            disableComponent(mDouble12);
            enableComponent(mDouble11);
        } else if (v == btnLog2) {
            disableComponent(mDefault);
            disableComponent(mDouble11);
            enableComponent(mDouble12);
        }
    }

    //设置启用
    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    //设置不可用
    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
