package com.feechanz.aplikasimoviecatalogue.widget

import android.content.Intent
import android.widget.RemoteViewsService

/**
 * Created by Feechan on 11/10/2019.
 */
class StackWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        StackRemoteViewsFactory(this.applicationContext)
}