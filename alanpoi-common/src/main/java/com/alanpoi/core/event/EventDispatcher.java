package com.alanpoi.core.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EventDispatcher {

    private Executor executor;

    private Map<String, List<EventListener>> lsMapping = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock rLock = rwLock.readLock();
    private Lock wLock = rwLock.writeLock();

    public EventDispatcher(Executor executor) {
        this.executor = executor;
    }

    public EventDispatcher() {
        this.executor = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }

    public EventDispatcher on(String eventName, EventListener listener) {
        try {
            wLock.lock();
            List<EventListener> lsList = lsMapping.get(eventName);
            if (null == lsList) {
                lsList = new ArrayList<>();
                lsMapping.put(eventName, lsList);
            }
            if (!lsList.contains(listener)) {
                lsList.add(listener);
            }

        } finally {
            wLock.unlock();
        }
        return this;
    }

    public void off(String eventName, EventListener listener) {
        try {
            wLock.lock();
            List<EventListener> lsList = lsMapping.get(eventName);
            if (null == lsList) {
                return;
            }

            lsList.remove(listener);
            if (lsList.size() == 0) {
                lsMapping.remove(eventName);
            }
        } finally {
            wLock.unlock();
        }
    }

    public void trigger(final Event event) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                doDispatch(event);
            }
        });
    }

    private void doDispatch(Event event) {
        try {
            rLock.lock();
            List<EventListener> lsList = lsMapping.get(event.getName());
            if (null == lsList) {
                return;
            }
            for (EventListener ls : lsList) {
                ls.onEvent(event);
            }
        } finally {
            rLock.unlock();
        }
    }


}
