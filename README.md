# vert.x cluster-demo

vertx的集群实例
修改setClusterHost

```java
VertxOptions options = new VertxOptions().setClustered(true).setClusterHost("100.100.100.27");
```
修改memebers
```xml
<tcp-ip enabled="true">
  <!-- 局域网里其他机器ip -->
  <members>100.100.100.27,100.100.100.142</members>
</tcp-ip>
````

2个机器分别运行TimeVerticle、Receiver
