# Nerve

整合APP数据采集的框架

数据采集（依赖宿主）

1.Crash

​	1.1 Crash_Java

​	1.2 Crash_Native

2.ANR

3.DNS

4.Frame

​	4.1 利用Choreographer+IdleHandler

5.MemInfo

​	5.1 利用Debug.MemoryInfo

数据上传（单独进程，与数据采集做风险隔离）

