package com.example.controllerobserver.work;

import android.content.Context;

import java.util.List;
import java.util.Timer;

public abstract class Job {

    public enum Status {
        QUEUED,         // job is determined to run
        PENDING,        // job is pending
        WORKING,        // job is running
        FAILED,         // job is finished and failed
        SKIPPED,        // job was not run but skipped instead
        DONE            // job is done successfully
    }
    public enum ChainCondition {
        RUN_IF_SUCCESS,
        RUN_ALWAYS,
    }
    static final String TAG = "UPDATE_PROCEDURE";
    Status status = Status.PENDING;
    Timer timer = null;

    Job next = null;
    Job previous = null;
    ChainCondition condition = ChainCondition.RUN_ALWAYS;
    Runnable onFinished = null;
    Context context = null;
    public Job(Context context) {
        this.context = context;
    }

    public void clear() {
        if(timer != null) {
            timer.purge(); // clear queue
            timer.cancel(); // stop running task
            timer = null;
        }
    }

    public void rerun() {
        clear();
        status = Status.PENDING;
        run();
    }

    public void run() {
        timer = new Timer();
        if(finished()) {
            stop();
        } else {
            setStatus(Status.WORKING);
            doRun();
        }
    }

    abstract void doRun();

    void setStatus(Status next) {
        status = next;
        if(finished()) {
            stop();
        }
    }

    void stop() {
        if(!finished()) {
//            throw new RuntimeException();
            return;
        }
        boolean shouldRun = (condition == ChainCondition.RUN_IF_SUCCESS && done()) ||
                (condition == ChainCondition.RUN_ALWAYS && finished());
        boolean canRun = next != null;
        if(canRun) {
            if(shouldRun) {
                next.run();
            } else {
                next.status = status;
                next.stop();
            }
        }
        if(onFinished != null) {
            onFinished.run();
        }
    }

    public boolean readyToRun() {
        return status == Status.QUEUED || status == Status.PENDING;
    }

    public boolean busy() {
        return status == Status.WORKING;
    }

    public boolean finished() {
        return finished(status);
    }

    public boolean done() {
        return done(status);
    }

    static public boolean finished(Status s) {
        return s == Status.FAILED ||
                s == Status.SKIPPED ||
                s == Status.DONE;
    }

    static public boolean done(Status s) {
        return s == Status.SKIPPED ||
                s == Status.DONE;
    }
    static boolean finished(List<Status> a) {
        return a.stream().allMatch(Job::finished);
    }

    static boolean done(List<Status> a) {
        return a.stream().allMatch(Job::done);
    }

    public Job chain(Job other, ChainCondition condition) {
        next = other;
        other.previous = this;
        this.condition = condition;
        return other;
    }

    public Job then(Runnable callback) {
        this.onFinished = callback;
        return this;
    }

    static boolean shouldUpdate(String currentVersionHexString, String nextVersionFile) {
//        try {
//            int current = Integer.parseInt(currentVersionHexString, 16);
//            int versionHexString = Files.readAllLines(Paths.get(nextVersionFile)).get(0);
//            int next = Integer.parseInt(versionHexString, 16);
//            Log.d(TAG, "compare current version (" +
//                    Integer.toHexString(current) +
//                    ") vs available version (" +
//                    Integer.toHexString(next) +
//                    ")");
//            return next > current;
//        } catch (Exception e) {
//            Log.d(TAG, "could not read version file, return with no update");
//            return false;
//        }
        return true;
    }

}
