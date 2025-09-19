## alanpoi

### 需JDK 17+ 版本

[alanpoi-common(公共组件)](https://github.com/alan-et/alanpoi/blob/develop/alanpoi-common/README.md)

[alanpoi-analysis(解析服务)](https://github.com/alan-et/alanpoi/blob/develop/alanpoi-analysis/README.md)

### 发布

1. 生成gpg
```gpg --gen-key```

2. 上传公钥
```gpg --keyserver keyserver.ubuntu.com --send-keys <替换成密钥ID> ```

3. 查询是否成功
``` gpg --keyserver keyserver.ubuntu.com --recv-keys <替换成密钥ID>  ```

4. 中央仓库获取token配置到setting.xml文件

#### 如果上传报错

*执行以下命令*
```shell
gpgconf --kill dirmngr
dirmngr --debug-all --daemon --standard-resolver
```



