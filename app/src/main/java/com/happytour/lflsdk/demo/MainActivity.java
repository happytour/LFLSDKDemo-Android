package com.happytour.lflsdk.demo;

//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alldls.lflsdk.CustomTaskType;
import com.alldls.lflsdk.LflSDK;
import com.alldls.lflsdk.listener.EventListener;
import com.alldls.lflsdk.listener.InitListener;
import com.alldls.lflsdk.listener.LflCustomTaskListener;

public class MainActivity extends AppCompatActivity {

    private Button btnSettings;
    private Button btnShow;
    private TextView tvInfo;

    private String appId;
    private String userId;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("demo_lfl_config", Context.MODE_PRIVATE);
        appId = sp.getString("app_id", "");
        userId = sp.getString("user_id", "");

        btnSettings = findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, SettingsActivity.class), 100);
            }
        });

        tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText(String.format("demo version: %s(%d)\nsdk version: %s(%d)\napp id: %s\nuser id: %s", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, LflSDK.sdkVersion(), LflSDK.sdkVersionCode(), appId, userId));

        btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(userId)) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("AppId??????UserId????????????")
                            .setPositiveButton("?????????", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                    return;
                }

                LflSDK.init(getApplication(), appId, new InitListener() {
                    @Override
                    public void initSuccess() {
                        Toast.makeText(MainActivity.this, "initSuccess\nsdk ver: " + LflSDK.sdkVersion() + " \nweb ver: " + LflSDK.sdkAssetsVersion(), Toast.LENGTH_SHORT).show();


                        LflSDK.show(MainActivity.this, userId, new EventListener() {
                            @Override
                            public void onPageClose() {
                                Toast.makeText(MainActivity.this, "onPageClose", Toast.LENGTH_SHORT).show();
                            }
                        });
                        LflSDK.addListener(new LflCustomTaskListener() {
                            @Override
                            public void onCallCustomTask(Context context, int customTaskType) {
                                if (customTaskType == CustomTaskType.SHARE) {
                                    //???????????????????????????
                                    new AlertDialog.Builder(context)
                                            .setTitle("??????")
                                            .setMessage("??????????????????")
                                            .setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerFail(CustomTaskType.SHARE);
                                                }
                                            })
                                            .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerSuccess(CustomTaskType.SHARE);
                                                }
                                            }).show();
                                } else if (customTaskType == CustomTaskType.INVITE) {
                                    //???????????????????????????
                                    new AlertDialog.Builder(context)
                                            .setTitle("??????")
                                            .setMessage("??????????????????")
                                            .setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerFail(CustomTaskType.INVITE);
                                                }
                                            })
                                            .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerSuccess(CustomTaskType.INVITE);
                                                }
                                            }).show();
                                } else if (customTaskType == CustomTaskType.TAKE_PHOTO) {
                                    //???????????????????????????
                                    new AlertDialog.Builder(context)
                                            .setTitle("??????")
                                            .setMessage("??????????????????")
                                            .setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerFail(CustomTaskType.TAKE_PHOTO);
                                                }
                                            })
                                            .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerSuccess(CustomTaskType.TAKE_PHOTO);
                                                }
                                            }).show();
                                } else if (customTaskType == CustomTaskType.CHECK_LOGIN) { //?????????????????????????????????
                                    new AlertDialog.Builder(context)
                                            .setTitle("????????????")
                                            .setMessage("????????????????????????")
                                            .setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerFail(CustomTaskType.CHECK_LOGIN);
                                                }
                                            })
                                            .setPositiveButton("?????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerSuccess(CustomTaskType.CHECK_LOGIN);
                                                }
                                            }).show();
                                } else if (customTaskType == CustomTaskType.LOGIN) { //???????????????????????????
                                    new AlertDialog.Builder(context)
                                            .setTitle("??????")
                                            .setMessage("??????????????????")
                                            .setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerFail(CustomTaskType.LOGIN);
                                                }
                                            })
                                            .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerSuccess(CustomTaskType.LOGIN);
                                                }
                                            }).show();
                                } else {
                                    new AlertDialog.Builder(context)
                                            .setTitle("??????")
                                            .setMessage("?????????????????????" + customTaskType)
                                            .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerFail(customTaskType);
                                                }
                                            })
                                            .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LflSDK.triggerSuccess(customTaskType);
                                                }
                                            }).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void initFail() {
                        Toast.makeText(MainActivity.this, "initFail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                appId = sp.getString("app_id", "");
                userId = sp.getString("user_id", "");
                tvInfo.setText(String.format("demo version: %s(%d)\nsdk version: %s(%d)\napp id: %s\nuser id: %s", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, LflSDK.sdkVersion(), LflSDK.sdkVersionCode(), appId, userId));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}