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
 Event:事件对象，包含事件名称、事件参数<br>
 EventDispatcher：事件核心处理器,绑定事件，触发事件<br>
 EventListener: 事件监听,用于用户定义事件<br>

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

### 基本描叙


 * 64位分布式ID(Long)（支持高并发(每毫秒32767，超过并发数，排队1毫秒,说无延时也不为过)）
 * 支持时间  此方法可以支持139年(应该是目前除开UUID(UUID是128为字节，32位的字符，生成的ID无法排序)，支持时间最长的分布式算法)
 * 支持节点  为了更好的支持高并发，适量的降低了服务器节点数，此方法最多支持128(0-127)台服务器节点部署，我相信应该满足99%的需求，我接触的华为公司一般都只有4-12台服务器节点
 * 容错率高 高并发的场景往往不能保持原子性，一般很容易出现问题，因此此算法引入了AtomicInteger（原子类，CAS算法）来解决此问题
 

### 算法（|42bit(timestamp)|15bit(version)|7bit(serverId)|）

### 和主流ID算法比较

1. 雪花算法生成ID: 采用的是41位事件戳生成ID,可以支持69年（怎么算的就不描叙了，二进制的基础知识）,而且多节点部署，如果服务器时间不一致就可能会出现生成重复ID，而且多线程使用SnowFlake不恰当也会导致大量重复
2. UUID: 此算法是32位字符，换算成二进制是采用的128位算法，其中主要的两部分就是时间、节点、随机数；包含的信息足够多，因此就转换成了16进制的字符展示；算法绝对唯一，但是排序怎么办；

### 使用

1. 单节点可以可以直接使用 ID.getId.next()，多节点也可以使用，不能保证真正的分布式唯一<br>
2. 多节点通过配置alanpoi.serverid.enable=true,或者通过配置类Bean开启如下：
```
    @Bean(destroyMethod = "destroy")
    public ServerID initServerID(RedisTemplate redisTemplate) {
        ServerID serverID = new ServerID(redisTemplate);
        return serverID;
    }
```
3. 基础用法，ID.getId().next()、ID.getId.current()





