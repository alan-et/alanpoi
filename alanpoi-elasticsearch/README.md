# anpoi common

项目中使用:
```
  <dependency>
      <groupId>com.alanpoi</groupId>
      <artifactId>alanpoi-elasticsearch</artifactId>
      <version>1.3.4</version>
  </dependency>
```

## Api document
通过 [Api doc](https://alanpoi.com:8161/index.html) 更加深入了解它！

## 环境变量

 | 变量名 | 值 | 描叙 |
 | ----  |---- | ---- |
 | alanpoi.elasticsearch.enable | true/false| true 开启es,false 关闭es |
 | alanpoi.elasticsearch.nodes | 1|  es服务器节点|
 | alanpoi.elasticsearch.username | elastic| es登录帐号|
 | alanpoi.elasticsearch.password | 1| es登录密码 |

## Samples

1. 批量操作

```
   public void bulkTest() throws IOException {
      BatchRequest request=BatchRequest.build("alanpoi-index","POST");
      request.add("6", ESOpType.index,"{\"name\":\"测试\"}");
      JSONObject result= esRestClient.bulk(request);
      System.out.println(result);
   }
```
2. 索引数据

```
  public void indexTest() throws IOException {
     JSONObject result=esRestClient.insertDoc("qzd_cms","5", JSON.parseObject("{\"name\":\"测试\"}"));
     System.out.println(result);
  }
```
