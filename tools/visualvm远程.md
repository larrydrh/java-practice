1. java启动参数添加如下参数
```
"-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.authenticate=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.local.only=false", "-Dcom.sun.management.jmxremote.port=30090", "-Dcom.sun.management.jmxremote.rmi.port=30090", "-Djava.rmi.server.hostname=127.0.0.1",
```
