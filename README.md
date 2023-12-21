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
    - 字段填充钩子，可以用于字段填充，比如创建时间，修改时间等
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
    - 提供的基础方法
      ```
        /**
      * mongo 基础方法接口
      *
      * @author kai
      * @date 2023/6/13
        */
        public interface IEasyMongoRepository<T> {

        /**
          * 查询单条数据
          *
          * @param queryWrapper 条件构造器
          * @return 查询到的集合数据
            */
            T getOne(LambdaQueryWrapper<T> queryWrapper);

        /**
          * 查询单条数据
          *
          * @param queryWrapper      条件构造器
          * @param getCollectionName 获取集合名称
          * @return 查询到的集合数据
            */
            T getOne(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName);


        /**
         * 保存新的数据
         *
         * @param entity 需要保存的实体
         * @return 是否保存成功
         */
        boolean save(T entity);
    
        /**
         * 保存新的数据
         *
         * @param getCollectionName 获取集合名称
         * @param entity            需要保存的实体
         * @return 是否保存成功
         */
        boolean save(T entity, Supplier<String> getCollectionName);
    
        /**
         * 批量保存新的数据 内部递归调用单个保存
         *
         * @param entityList 需要保存的数据列表
         * @return 是否保存成功
         */
        boolean saveBatch(Collection<T> entityList);
    
    
        /**
         * 插入
         *
         * @param entity            实体
         * @param getCollectionName 获取集合名称
         * @return boolean
         */
        boolean insert(T entity, Supplier<String> getCollectionName);
    
        /**
         * 批量插入
         *
         * @param entityList        实体列表
         * @param getCollectionName 获取集合名称
         * @return boolean
         */
        boolean insertBatch(Collection<T> entityList, Supplier<String> getCollectionName);
    
        /**
         * 插入
         *
         * @param entity 实体
         * @return boolean
         */
        boolean insert(T entity);
    
        /**
         * 批量插入
         *
         * @param entityList 实体列表
         * @return boolean
         */
        boolean insertBatch(Collection<T> entityList);
    
        /**
         * 批量保存新的数据 内部递归调用单个保存
         *
         * @param getCollectionName 获取集合名称
         * @param entityList        需要保存的数据列表
         * @return 是否保存成功
         */
        boolean saveBatch(Collection<T> entityList, Supplier<String> getCollectionName);
    
        /**
         * 通过ID删除数据
         *
         * @param id 数据ID
         * @return 是否删除成功
         */
        boolean removeById(Serializable id);
    
        /**
         * 通过ID删除数据
         *
         * @param getCollectionName 获取集合名称
         * @param id                数据ID
         * @return 是否删除成功
         */
        boolean removeById(Serializable id, Supplier<String> getCollectionName);
    
        /**
         * 通过条件构建器删除数据
         *
         * @param queryWrapper 条件构建器
         * @return 是否删除成功
         */
        boolean remove(LambdaQueryWrapper<T> queryWrapper);
    
        /**
         * 通过条件构建器删除数据
         *
         * @param getCollectionName 获取集合名称
         * @param queryWrapper      条件构建器
         * @return 是否删除成功
         */
        boolean remove(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName);
    
        /**
         * 通过ID更新数据 只有存在数据的字段才会更新
         *
         * @param entity 需要更新的数据
         * @return 是否更新成功
         */
        boolean updateById(T entity);
    
        /**
         * 通过ID更新数据 只有存在数据的字段才会更新
         *
         * @param getCollectionName 获取集合名称
         * @param entity            需要更新的数据
         * @return 是否更新成功
         */
        boolean updateById(T entity, Supplier<String> getCollectionName);
    
        /**
         * 通过条件构造器更新数据 只有存在数据的字段才会更新
         *
         * @param entity       需要更新的数据
         * @param queryWrapper 条件构建起
         * @return 是否更新成功
         */
        boolean update(T entity, LambdaQueryWrapper<T> queryWrapper);
    
        /**
         * 通过条件构造器更新数据 只有存在数据的字段才会更新
         *
         * @param getCollectionName 获取集合名称
         * @param entity            需要更新的数据
         * @param queryWrapper      条件构建起
         * @return 是否更新成功
         */
        boolean update(T entity, LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName);
    
        /**
         * 通过ID获取数据
         *
         * @param id 数据ID
         * @return 集合中的数据
         */
        T getById(Serializable id);
    
        /**
         * @param getCollectionName 获取集合名称
         * @param id                数据ID
         * @return 集合中的数据
         */
        T getById(Serializable id, Supplier<String> getCollectionName);
    
        /**
         * 通过数据ID集合获取数据集合
         *
         * @param idList 数据ID集合
         * @return 查询到的数据集合
         */
        Collection<T> listByIds(Collection<? extends Serializable> idList);
    
    
        /**
         * 通过数据ID集合获取数据集合
         *
         * @param getCollectionName 获取集合名称
         * @param idList            数据ID集合
         * @return 查询到的数据集合
         */
        Collection<T> listByIds(Collection<? extends Serializable> idList, Supplier<String> getCollectionName);
    
        /**
         * 通过条件构建起统计数据量
         *
         * @param queryWrapper 条件构建起
         * @return 数据两
         */
        long count(LambdaQueryWrapper<T> queryWrapper);
    
        /**
         * 通过条件构建起统计数据量
         *
         * @param getCollectionName 获取集合名称
         * @param queryWrapper      条件构建起
         * @return 数据两
         */
        long count(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName);
    
        /**
         * 通过条件构建起查询列表
         *
         * @param queryWrapper 条件构建器
         * @return 数据集合
         */
        List<T> list(LambdaQueryWrapper<T> queryWrapper);
    
        /**
         * 通过条件构建起查询列表
         *
         * @param getCollectionName 获取集合名称
         * @param queryWrapper      条件构建器
         * @return 数据集合
         */
        List<T> list(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName);
    
        /**
         * 通过条件构建起进行分页查询
         *
         * @param queryWrapper 条件构建器
         * @param pageNo       页面
         * @param pageSize     页面大小
         * @return 分页对象
         */
        Page<T> page(LambdaQueryWrapper<T> queryWrapper, int pageNo, int pageSize);
    
        /**
         * 通过条件构建起进行分页查询
         *
         * @param getCollectionName 获取集合名称
         * @param queryWrapper      条件构建器
         * @param pageNo            页面
         * @param pageSize          页面大小
         * @return 分页对象
         */
        Page<T> page(LambdaQueryWrapper<T> queryWrapper, int pageNo, int pageSize, Supplier<String> getCollectionName);
    
        /**
         * 通过条件构建器判断是否存在数据
         *
         * @param queryWrapper 条件构建器
         * @return 是否存在数据
         */
        boolean exist(LambdaQueryWrapper<T> queryWrapper);
    
        /**
         * 通过条件构建器判断是否存在数据
         *
         * @param getCollectionName 获取集合名称
         * @param queryWrapper      条件构建器
         * @return 是否存在数据
         */
        boolean exist(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName);
    
        /**
         * 跟据条件构建器修改
         *
         * @param lambdaUpdateWrapper 条件构建器
         * @return 是否更新成功
         */
        boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper);
    
        ;
    
    
        /**
         * 跟据条件构建器修改
         *
         * @param lambdaUpdateWrapper 条件构建器
         * @param multi               是否批量
         * @return 是否更新成功
         */
        boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper, boolean multi);
    
        /**
         * 跟据条件构建器修改
         *
         * @param lambdaUpdateWrapper 条件构建器
         * @param getCollectionName   集合构建器
         * @return 是否更新成功
         */
         boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper, Supplier<String> getCollectionName);
    
    
        /**
         * 跟据条件构建器修改
         *
         * @param lambdaUpdateWrapper 条件构建器
         * @param multi               是否批量
         * @param getCollectionName   集合构建器
         * @return 是否更新成功
         */
        boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper, boolean multi, Supplier<String> getCollectionName);

      ```
