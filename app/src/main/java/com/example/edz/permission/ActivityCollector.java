package com.example.edz.permission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * authr : edz on 2017/11/22  下午3:50
 * describe ：
 */


public class ActivityCollector {

    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static Activity getTopActivity() {
        if (activities.isEmpty()) {
            return null;
        } else {
            return activities.get(activities.size() - 1);
        }
    }
}
