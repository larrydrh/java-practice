# 数据库

* 查看事物隔离级别
```sql
> show variables like 'tx_isolation'
+-----------------+----------------+
| Variable_name   | Value          |
|-----------------+----------------|
| tx_isolation    | READ-COMMITTED |
+-----------------+----------------+

```

* 分析行锁定
```sql
show status like 'innodb_row_lock%'
+-------------------------------+----------+
| Variable_name                 |    Value |
|-------------------------------+----------|
| Innodb_row_lock_current_waits |        0 |
| Innodb_row_lock_time          | 14125312 |
| Innodb_row_lock_time_avg      |       32 |
| Innodb_row_lock_time_max      |     4895 |
| Innodb_row_lock_waits         |   429786 |
+-------------------------------+----------+

innodb_row_lock_current_waits: 当前正在等待锁定的数量
innodb_row_lock_time: 从系统启动到现在锁定总时间长度；非常重要的参数，
innodb_row_lock_time_avg: 每次等待所花平均时间；非常重要的参数，
innodb_row_lock_time_max: 从系统启动到现在等待最长的一次所花的时间；
innodb_row_lock_waits: 系统启动后到现在总共等待的次数；非常重要的参数。直接决定优化的方向和策略。
```
