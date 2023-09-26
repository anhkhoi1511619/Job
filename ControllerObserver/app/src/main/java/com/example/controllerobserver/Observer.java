package com.example.controllerobserver;

import android.util.Log;

import com.example.controllerobserver.data.CurrentOperationInfo;
import com.example.controllerobserver.work.Download;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Observer {
    public static final String TAG = Observer.class.getSimpleName();

    public Observer(Main main, Sub sub) {
        this.main = main;
        this.sub = sub;
    }

    public Observer() {
    }

    private Main main;
    private Sub sub;
    public void confirmControllerDeployment(int controllerIdx, String ftpPermission, String statusInfo) {
        switch (controllerIdx) {
            case 0:
                checkDataFromMain(ftpPermission);
                break;
            case 1:
            case 2:
            case 3:
                checkDataFromSub(statusInfo);
                break;
            default:
                break;

        }
    }

    private void checkDataFromMain(String ftpPermission) {
        Log.e(TAG, "Input Data:   ftpPermission =    "+ftpPermission+"  CurrentOperationInfo.getStatusInfo()=   "+CurrentOperationInfo.getStatusInfo());

        ControllerStatus controllerStatus = Arrays.stream(ControllerStatus.values())
                .filter(e->e.isFtpPermission()==ftpPermission.equals("01"))
                .findFirst()
                .orElse(ControllerStatus.INIT);

        Log.e(TAG, "ControllerStatus:   "+controllerStatus.name()+" statusInfo:    "+controllerStatus.getStatusInfo());
        List<ControllerStatus> arrayList = Arrays.stream(ControllerStatus.values())
                .filter(e->e.isFtpPermission()==ftpPermission.equals("01"))
                .collect(Collectors.toList());

        for (ControllerStatus status : arrayList) {
            if(status.getStatusInfo().equals(CurrentOperationInfo.getStatusInfo())){
                Log.e(TAG, "ControllerStatus OK:   "+status.name()+" statusInfo:    "+status.getStatusInfo());
                switch (status) {
                    case REQUEST:
                    case UPDATE:
                        Download.doStartSub();
                        break;
                    default:
                        break;

                }
//                if(status == ControllerStatus.REQUEST){
//                    Download.getInstance().startSub();
//                }
            }
            Log.e(TAG, "ControllerStatus:   "+status.name()+" statusInfo:    "+status.getStatusInfo());
        }

    }

    private void checkDataFromSub(String statusInfo) {
        Log.e(TAG, "Input Data:   statusInfo =    "+statusInfo);

        boolean isFTPPermission = Arrays.stream(ControllerStatus.values())
                .filter(e->e.getStatusInfo().equals(statusInfo))
                .findFirst()
                .orElse(ControllerStatus.INIT)
                .isFtpPermission();

//        if(isFTPPermission) Download.getInstance().startSub();

        Log.e(TAG, "isFTPPermission:    "+isFTPPermission);
    }
}
