## 一款开源的SQL语句转换成Java 实体类的小工具
## 需求起源
> 因为公司之前使用JPA，先写实体类再生产数据库建表语句，这样会有一个问题，数据建表语句都是没有注释的，对于新来的同事非常不友好，同时在开发中需要看字段意义的时候也并不方便,并且字段的类型和长度都需要显示的去声明。

## 如何打包
```bash
gradle bootRepackage
```
## 运行成功访问
> 访问 [http://localhost](http://localhost)

## 效果图
![](http://7xv4mv.com1.z0.glb.clouddn.com/2018-05-08-2.gif)

##在线地址
[Sql2entity](http://www.xiaozefeng.xyz/)

## 待优化
- ~~目前只支持单个建表语句，生成的文件也是单个的java文件~~
- sql解析很多地方是硬编码的，需要更灵活
- ~~包的导入目前还没有做~~
- 现在还不支持多种风格的代码生成 (jpa、mybatis)
