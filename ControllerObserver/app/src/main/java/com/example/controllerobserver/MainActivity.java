package com.example.controllerobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.controllerobserver.data.CurrentOperationInfo;
import com.example.controllerobserver.work.Startup;

public class MainActivity extends AppCompatActivity {
    String STATUS_RSP_NORMAL        = "00";             //  00h:通常
    String STATUS_RSP_INIT          = "00";             //  00h:初期化処理中
    String STATUS_RSP_FILE_WAIT     = "01";             //  01h:ファイル取得待ち
    String STATUS_RSP_STARTED       = "10";             //  10h:起動完了（通常）
    String STATUS_RSP_UPDATING      = "81";             //  81h:更新ファイル取得中
    String STATUS_RSP_UPDATED       = "82";             //  82h:更新ファイル取得完了
    String STATUS_RSP_DATA_PROGRESS = "90";             //  90h:注入処理中(イレース・ライト・サム計算)

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main main = new Main();
        Sub sub = new Sub();
        Observer observer = new Observer(main,sub);
        Startup startup = new Startup(getApplicationContext());
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startup.run();
                CurrentOperationInfo.setStatusInfo(STATUS_RSP_DATA_PROGRESS);
                observer.confirmControllerDeployment(0,"01","");
                main.startPermission4Sub();
                sub.start4Main();
            }
        });
    }
}