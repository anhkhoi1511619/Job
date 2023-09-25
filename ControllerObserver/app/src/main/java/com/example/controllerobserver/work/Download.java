package com.example.controllerobserver.work;

import android.content.Context;
import android.util.Log;

import com.example.controllerobserver.Observer;

import java.util.TimerTask;

public class Download extends Job{

    static Context context;
    static Download instance = null;

    public static Download getInstance() {
        if(instance == null) {
            instance = new Download(context);
        }
        return instance;
    }
    DownloadAppList downloadAppList;
    DownloadApp downloadApp;
    DownloadSubAppList downloadSubAppList;
    DownloadSubApp downloadSubApp;
    public Download(Context context) {
        super(context);
        downloadAppList = new DownloadAppList(context);
        downloadApp = new DownloadApp(context);
        downloadSubAppList = new DownloadSubAppList(context);
        downloadSubApp = new DownloadSubApp(context);
        downloadAppList.chain(downloadApp, ChainCondition.RUN_IF_SUCCESS)
                .then(this::report);

        downloadSubAppList.chain(downloadSubApp, ChainCondition.RUN_IF_SUCCESS)
                .then(this::reportSub);
    }
    void report(){
        Log.v(TAG, "downloadAppList:  "+downloadAppList.done());
        Log.v(TAG, "downloadApp:  "+downloadApp.done());
        boolean success = downloadAppList.done()&&downloadApp.done();
        setStatus(success?Status.DONE:Status.FAILED);
    }

    void reportSub(){
        Log.v(TAG, "downloadSubAppList:  "+downloadSubAppList.done());
        Log.v(TAG, "downloadSubApp:  "+downloadSubApp.done());
        boolean success = downloadSubAppList.done()&&downloadSubApp.done();
        setStatus(success?Status.DONE:Status.FAILED);
    }

    @Override
    public void clear() {
        super.clear();
        instance = null;
    }

    @Override
    void doRun() {
        downloadAppList.run();
        instance = this;
    }
    public void startSub() {
        instance.downloadSubAppList.run();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Observer().confirmControllerDeployment(0,"01","");
            }
        },2000);
    }
}
