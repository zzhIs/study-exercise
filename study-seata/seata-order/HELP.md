# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


问题记录：
关于org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)的解决方案
原因：没有找到 SeataOrderMapper.xml
解决步骤：
1.跟踪SqlSessionFactoryBean发现mapperLocations为空
2.查询赋值地点：factoryBean.setMapperLocations(resolver.getResources(mapperScanPath))
3.resolver.getResources(mapperScanPath)为空，mapperScanPath 没有取得到配置文件的mapper-locations:；
且配置文件的路径和实际路径不对应，classpath*:mapper/**/*.xml

