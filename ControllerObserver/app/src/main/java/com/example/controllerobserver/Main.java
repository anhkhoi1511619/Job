package com.example.controllerobserver;

import com.example.controllerobserver.data.CurrentOperationInfo;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static Timer timer = new Timer();

    public void startPermission4Sub(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CurrentOperationInfo.setStatusInfo("01");
                new Observer()
                        .confirmControllerDeployment(0,"01","");
            }
        },10000);
    }
}
