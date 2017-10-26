package com.baidu.test.mypremission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;


//@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionGen.needPermission(this, 100, new String[]{Manifest.permission.READ_SMS});
    }

    @PermissionSuccess(requestCode = 100)
    void getpermission() {
        //得到电话管理者
        TelephonyManager systemService = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        //得到电话号码（只能拿到单卡手机的手机号）
        String line1Number = systemService.getLine1Number();
        Toast.makeText(this, line1Number, Toast.LENGTH_SHORT).show();
    }
    @PermissionFail(requestCode = 100)
    public void shibai(){
        Toast.makeText(this, "失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

}

