# 动态修改App的Logo

[参考文献](http://blog.csdn.net/eclipsexys/article/details/53791818)

效果图
![第一次安装的效果图](https://ww3.sinaimg.cn/large/006tKfTcgw1fbejhzje8qj30hq0b6dge.jpg)

![App图标](https://ww3.sinaimg.cn/large/006tKfTcgw1fbejj4kp66j30kg11egp2.jpg)

---
**点击Logo1后**
![Logo1](https://ww3.sinaimg.cn/large/006tKfTcgw1fbejkii4t9j30kg11edj7.jpg)

![Logo2](https://ww3.sinaimg.cn/large/006tKfTcgw1fbejlau9yfj30kg11ewhz.jpg)

## 源码
AndroidManifest.xml 
	
	<activity-alias
            android:name="logo1"
            android:enabled="false"
            android:icon="@mipmap/ic_launcher1"
            android:label="Logo1"
            android:targetActivity=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity-alias>

        <activity-alias
            android:name="logo2"
            android:enabled="false"
            android:icon="@mipmap/ic_launcher2"
            android:label="logo2"
            android:targetActivity=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity-alias>


MainActivity.java
	
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
