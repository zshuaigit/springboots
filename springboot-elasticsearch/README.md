# springboot-elasticsearch

Spring Data Elasticsearch的页面：https://projects.spring.io/spring-data-elasticsearch/

特征：

1. 支持Spring的基于@Configuration的java配置方式，或者XML配置方式
2. 提供了用于操作ES的便捷工具类ElasticsearchTemplate。包括实现文档到POJO之间的自动智能映射。
3. 利用Spring的数据转换服务实现的功能丰富的对象映射
4. 基于注解的元数据映射方式，而且可扩展以支持更多不同的数据格式
5. 根据持久层接口自动生成对应实现方法，无需人工编写基本操作代码（类似mybatis，根据接口自动得到实现）。也支持人工定制查询
创建索引


# ElasticsearchTemplate中提供了创建索引和映射的API以及删除索引的API：
直接拷贝过来，减去查找代码的时间
...

    public String createIndex(Class<Item> itemClass) {
        //创建索引
        boolean tag = elasticsearchTemplate.createIndex(itemClass);
        logger.info("创建索引成功！");
        //添加映射 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        boolean mapping = elasticsearchTemplate.putMapping(itemClass);
        logger.info("添加映射成功成功！");
        if(tag && mapping){
            return "创建索引、映射成功";
        }else{
            return "创建失败，请查看日志!";
        }
    }

    public String deleteIndex(Class<Item> itemClass) {
        //删除索引
        boolean tag = elasticsearchTemplate.deleteIndex(itemClass);
        logger.info("删除索引成功！");
        //添加映射 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        if(tag ){
            return "删除索引成功";
        }else{
            return "删除失败，请查看日志!";
        }
    }

...

# Repository接口

Spring Data 的强大之处，就在于你不用写任何DAO处理，自动根据方法名或类的信息进行CRUD操作。只要你定义一个接口，然后继承Repository提供的一些子接口，就能具备各种基本的CRUD功能。

来看下Repository的继承关系：
图。后补


我们只需要定义接口，然后继承它就OK了。

...

    public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    }
...


聚合工程，注意父子之间依赖。

运行后查看api文档。http://localhost:1996/swagger-ui.html 对于接口又相信说明

