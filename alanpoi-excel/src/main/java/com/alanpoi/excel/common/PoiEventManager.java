package com.alanpoi.excel.common;

import com.alanpoi.common.event.EventDispatcher;

/**
 * POI事件管理器
 *
 * @author pengzhuoxun
 * @since version 1.1.2 , 2020-3-28
 */
public class PoiEventManager {

    public static String POI_IMPORT_EVENT_NAME = "poi.import.event";

    private static EventDispatcher eventDispatcher;

    public PoiEventManager() {
    }

    public static EventDispatcher getDispatcher() {
        if (eventDispatcher != null) {
            return eventDispatcher;
        }
        synchronized (PoiEventManager.class) {
            if (null == eventDispatcher) eventDispatcher = new EventDispatcher();
            return eventDispatcher;
        }

    }
}
