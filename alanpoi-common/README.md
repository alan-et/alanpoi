# anpoi common 

## Event事件
(参照JQuery事件原理)

### 背景
基于目前大部分java项目本该用到事件处理的，但是几乎都没有用，都是从头写到尾，大部分人只记住了oop思想，却忘记了主从业务的隔离或者说一个主对应多个从又或者一个主对应多个主的逻辑，这些情况如果不用到事件机制，写出来的代码是很扰乱的，后期维护成本也会增加，可能我去过的几个项目，我用了线程池基本解决了这些问题，但是代码还是看起来很乱，还有更好的处理机制——————事件

### 原理（流程很简单）
 **1. 绑定和触发流程**<br>
 ——》定义事件方法——》注册事件——》绑定事件到处理器 <br>
 ——》触发事件——》处理器派发事件——》执行监听方法<br><br>
 **2. 对象说明**<br>
 Event:事件对象，包含事件名称、事件参数
 EventDispatcher：事件核心处理器,绑定事件，触发事件
 EventListener: 事件监听,用于用户定义事件

### 使用

1. 通过配置类实例化全局EventDispatcher，或者像下面一样，通过全局单例模式<br>
```
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
```
2. 通过继承EventListener实现onEvent方法定义事件，然后通过处理器绑定事件,可参照下面<br>
```
    @PostConstruct
    public void init() {
        PoiEventManager.getDispatcher().on(PoiEventManager.POI_IMPORT_EVENT_NAME, this::onEvent);
    }
```
3. 在需要触发的时候调用处理器的trigger方法,通过event事件名触发绑定的所有事件
```
   PoiEventManager.getDispatcher().trigger(event);
```

## 全局高并发分布式ID

### 算法（|43bit(timestamp)|15bit(version)|6bit(serverId)|）





