项目介绍
mongo-orm封装

- API接口文档
    - 引入方式
      ```  
        <dependency>
              <groupId>com.kai</groupId>
              <artifactId>easy-mongo-spring-boot-starter</artifactId>
              <version>${version}</version>
          </dependency>
        ```
    - dao层的使用
       ```
            @Repository
            public class TestEntityRepository extends EasyMongoRepository<TestEntity> {
             }
       ```
    - 实体类
        ```
          @EqualsAndHashCode(callSuper = true)
          @Data
          @Document
          public class TestEntity extends BaseEntity {
        
              private String name;
        
        
              private Date birthday;
        
              private Integer age;
        
              private Child master;
        
              private List<Child> children;
          }
        ```
    - 构建查询
       ```
        LambdaQueryWrapper<TestEntity> query = Wrappers.<TestEntity>lambdaQuery();
       ```
    - 基本使用
       ``` 
        与mybatis-plus查询基本一致
        唯一就是模糊查询有区别:
        query.regex()
      ```
    - and查询
       ```
         query.and(item -> {
            return item.eq(TestEntity::getAge, 15).eq(TestEntity::getMaster, "weide");
        });
    
       ```
    - or查询
       ```
         query.or(item -> {
            return item.eq(TestEntity::getAge, 15).eq(TestEntity::getMaster, "weide");
        });
       ```
    - 嵌套查询
      ```
       query.eq(TestEntity::getMaster, "weide").setChild(Child::getName);
       表查询文档中master属性中的name属性匹配
       注意setChild方法表示对当前拼接的查询拼接嵌套查询
        上面master属性是child类型 name是child的属性
      ```
    - 字段填充钩子
      ```
       public interface MongoEventProcess {
       /**
        * 处理前事件
        *
        * @param event 插入前事件处理
          */
          void beforeSaveEvent(BeforeSaveEvent<BaseEntity> event);

      /**
        * 删除
        *
        * @param event 删除前事件
          */
          void beforeDeleteEvent(BeforeDeleteEvent<BaseEntity> event);

      /**
        * 转换事件
        *
        * @param event 转换事件
          */
          void beforeConvertEvent(BeforeConvertEvent<BaseEntity> event);
          }
      ```
    - 更新
      ```
       LambdaUpdateWrapper<TestEntity> updateWrapper = Wrappers.<TestEntity>lambdaUpdate();
      ```