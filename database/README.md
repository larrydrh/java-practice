### mybatis-plus与flyway同时使用配置步骤
1. 配置依赖, mybatis-plus与flyway版本可能会有冲突, 所以如果出现错误出现 Invalid bound statement (not found) 异常,有可能是版本冲突, 可以换个版本试一下, 以下版本经过测试是可以的:
    ```groovy
        compile group: 'org.flywaydb', name: 'flyway-core', version: '6.1.0'
        compile group: 'com.baomidou', name: 'mybatis-plus', version: '3.2.0'
        compile group: 'com.baomidou', name: 'mybatis-plus-boot-starter', version: '3.2.0'
    ```

2. 由于数据库表名的命名习惯与java不同所以, 需要配置以下属性
    ```yaml
    mybatis-plus.global-config.db-column-underline=true
    #  mapper-locations: classpath:/mapper/*Mapper.xml
    mybatis-plus.configuration.map-underscore-to-camel-case=true
    mybatis-plus.configuration.cache-enabled=false
    ```

3. 数据库表格可以根据预先设计好的ER图用数据库表创建, 然后使用数据库工具到处sql的表格式, 放在resources下schema.sql

4. 可以利用MysqlGenerator根据数据库表格式生成项目的一些基本类

5. 创建配置类FlywayConfig和MybatisPlusConfig

    * mybatis-plus文档: https://mp.baomidou.com/guide/crud-interface.html#service-crud-%E6%8E%A5%E5%8F%A3