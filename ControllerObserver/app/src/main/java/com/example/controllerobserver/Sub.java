package com.example.controllerobserver;

import com.example.controllerobserver.data.CurrentOperationInfo;

import java.util.Timer;
import java.util.TimerTask;

public class Sub {
    static Timer timer = new Timer();

    public void start4Main() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Observer()
                        .confirmControllerDeployment(2,"","01");
            }
        },20000);
    }
}
