# springboot-redis

## 1.springDataRedis

### 1.1 springDataRedis

Spring-data-redis是spring大家族的一部分，
提供了在srping应用中通过简单的配置访问 redis服务，对reids底层开发包(Jedis, JRedis, and RJC)进行了高度封装，RedisTemplate 提供了redis各种操作

### 1.2 demo逻辑分析
根据前端传递的用户名，进行数据查询，首先查询缓存，缓存中没有，则进行穿透查询，查询数据库，并返回


