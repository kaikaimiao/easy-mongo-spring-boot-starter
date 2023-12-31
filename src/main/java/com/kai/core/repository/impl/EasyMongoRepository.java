package com.kai.core.repository.impl;

import cn.hutool.core.util.ObjectUtil;
import com.kai.core.model.Page;
import com.kai.core.repository.IEasyMongoRepository;
import com.kai.core.wrapper.LambdaQueryWrapper;
import com.kai.core.wrapper.LambdaUpdateWrapper;
import com.kai.utils.ClassFieldUtil;
import com.kai.utils.ProxyUtils;
import com.kai.utils.QueryBuildUtils;
import com.kai.utils.UpdateBuildUtils;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 接口默认类
 *
 * @author kai
 * @date 2023/6/13
 */
@SuppressWarnings("all")
public class EasyMongoRepository<T> implements IEasyMongoRepository<T> {

    /**
     * 服务类对应的mongo实体类
     */
    private final Class<T> targetClass =
            (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Resource
    protected MongoTemplate mongoTemplate;

    @Override
    public T getOne(LambdaQueryWrapper<T> queryWrapper) {

        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.findOne(query, targetClass);

    }

    @Override
    public T getOne(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.findOne(query, targetClass, getCollectionName.get());
    }

    @Override
    public boolean save(T entity) {
        return Objects.nonNull(mongoTemplate.save(entity));
    }

    @Override
    public boolean save(T entity, Supplier<String> getCollectionName) {
        return Objects.nonNull(mongoTemplate.save(entity, getCollectionName.get()));
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {

        entityList.forEach(item -> mongoTemplate.save(item));
        return true;

    }

    @Override
    public boolean insert(T entity, Supplier<String> getCollectionName) {
        return Objects.nonNull(mongoTemplate.insert(entity, getCollectionName.get()));

    }

    @Override
    public boolean insertBatch(Collection<T> entityList, Supplier<String> getCollectionName) {
        mongoTemplate.insert(entityList, getCollectionName.get());
        return true;

    }

    @Override
    public boolean insert(T entity) {
        return Objects.nonNull(mongoTemplate.insert(entity));

    }

    @Override
    public boolean insertBatch(Collection<T> entityList) {
        mongoTemplate.insert(entityList, targetClass);
        return true;

    }

    @Override
    public boolean saveBatch(Collection<T> entityList, Supplier<String> getCollectionName) {
        String collectionName = getCollectionName.get();
        entityList.forEach(item -> mongoTemplate.save(item, collectionName));
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {

        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        DeleteResult deleteResult = mongoTemplate.remove(query, targetClass);
        return deleteResult.getDeletedCount() > 0;

    }

    @Override
    public boolean removeById(Serializable id, Supplier<String> getCollectionName) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        DeleteResult deleteResult = mongoTemplate.remove(query, targetClass, getCollectionName.get());
        return deleteResult.getDeletedCount() > 0;
    }

    @Override
    public boolean remove(LambdaQueryWrapper<T> queryWrapper) {

        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        DeleteResult remove = mongoTemplate.remove(query, targetClass);
        return remove.getDeletedCount() > 0;

    }

    @Override
    public boolean remove(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        DeleteResult remove = mongoTemplate.remove(query, targetClass, getCollectionName.get());
        return remove.getDeletedCount() > 0;
    }

    @Override
    public boolean updateById(T entity) {

        return this.save(entity);

    }

    @Override
    public boolean updateById(T entity, Supplier<String> getCollectionName) {
        Criteria criteria = Criteria.where("_id").is(ClassFieldUtil.getId(entity));
        Query query = new Query(criteria);
        Update update = getUpdate(entity);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, targetClass, getCollectionName.get());
        return updateResult.getModifiedCount() > 0;
    }

    /**
     * 通过反射获取需要更新的字段
     */
    private Update getUpdate(T entity) {
        Update update = new Update();
        update(entity, entity.getClass(), update);
        return update;

    }

    /**
     * 递归查询
     *
     * @param entity 实际对象
     * @param aclass calss信息
     * @param update 更新对象
     * @param <T>    泛型
     */
    public void update(T entity, Class<?> aclass, Update update) {
        for (Field field : aclass.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.getName().contains("$")) {
                    continue;
                }
                Object result = field.get(entity);
                if (ObjectUtil.isNotEmpty(result)) {
                    update.set(field.getName(), result);
                }
            } catch (Exception ignore) {
            }
        }
        Class<?> superclass = aclass.getSuperclass();
        if (superclass != null && !ProxyUtils.isProxyClass(superclass)) {
            update(entity, superclass, update);
        }

    }


    @Override
    public boolean update(T entity, LambdaQueryWrapper<T> queryWrapper) {

        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        Update update = getUpdate(entity);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, targetClass);
        return updateResult.getModifiedCount() > 0;

    }

    @Override
    public boolean update(T entity, LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        Update update = getUpdate(entity);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, targetClass, getCollectionName.get());
        return updateResult.getModifiedCount() > 0;
    }

    @Override
    public T getById(Serializable id) {

        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, targetClass);

    }

    @Override
    public T getById(Serializable id, Supplier<String> getCollectionName) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, targetClass, getCollectionName.get());
    }

    @Override
    public Collection<T> listByIds(Collection<? extends Serializable> idList) {

        Criteria criteria = Criteria.where("_id").in(idList);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, targetClass);

    }

    @Override
    public Collection<T> listByIds(Collection<? extends Serializable> idList, Supplier<String> getCollectionName) {
        Criteria criteria = Criteria.where("_id").in(idList);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, targetClass, getCollectionName.get());
    }

    @Override
    public long count(LambdaQueryWrapper<T> queryWrapper) {

        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.count(query, targetClass);

    }

    @Override
    public long count(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.count(query, targetClass, getCollectionName.get());
    }

    @Override
    public List<T> list(LambdaQueryWrapper<T> queryWrapper) {

        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.find(query, targetClass);

    }

    @Override
    public List<T> list(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.find(query, targetClass, getCollectionName.get());
    }

    @Override
    public Page<T> page(LambdaQueryWrapper<T> queryWrapper, int pageNo, int pageSize) {

        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        Page<T> page = new Page<>();
        page.setPageSize(pageSize);
        page.setPageNum(pageNo);
        long total = mongoTemplate.count(query, targetClass);
        page.setTotal(total);
        if (total <= 0) {
            return page;
        }
        query.skip((long) (pageNo - 1) * pageSize).limit(pageSize);
        List<T> list = mongoTemplate.find(query, targetClass);
        page.setContent(list);
        page.setTotalPages(pageSize == 0 ? 1 : (int) Math.ceil((double) total / (double) pageSize));
        return page;

    }

    @Override
    public Page<T> page(LambdaQueryWrapper<T> queryWrapper, int pageNo, int pageSize,
                        Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        Page<T> page = new Page<>();
        page.setPageSize(pageSize);
        page.setPageNum(pageNo);
        long total = mongoTemplate.count(query, targetClass, getCollectionName.get());
        page.setTotal(total);
        if (total <= 0) {
            return page;
        }
        query.skip((long) (pageNo - 1) * pageSize).limit(pageSize);
        List<T> list = mongoTemplate.find(query, targetClass, getCollectionName.get());
        page.setContent(list);
        page.setTotalPages(pageSize == 0 ? 1 : (int) Math.ceil((double) total / (double) pageSize));
        return page;
    }

    @Override
    public boolean exist(LambdaQueryWrapper<T> queryWrapper) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.exists(query, targetClass);
    }

    @Override
    public boolean exist(LambdaQueryWrapper<T> queryWrapper, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(queryWrapper);
        return mongoTemplate.exists(query, targetClass, getCollectionName.get());
    }

    @Override
    public boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper) {
        Query query = QueryBuildUtils.buildQuery(lambdaUpdateWrapper.query());
        Update update = UpdateBuildUtils.buildUpdate(lambdaUpdateWrapper);
        return mongoTemplate.updateFirst(query, update, targetClass).wasAcknowledged();
    }

    @Override
    public boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper, boolean multi) {
        Query query = QueryBuildUtils.buildQuery(lambdaUpdateWrapper.query());
        Update update = UpdateBuildUtils.buildUpdate(lambdaUpdateWrapper);
        if (multi) {
            return mongoTemplate.updateMulti(query, update, targetClass).wasAcknowledged();
        }
        return mongoTemplate.updateFirst(query, update, targetClass).wasAcknowledged();
    }

    @Override
    public boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(lambdaUpdateWrapper.query());
        Update update = UpdateBuildUtils.buildUpdate(lambdaUpdateWrapper);
        return mongoTemplate.updateFirst(query, update, getCollectionName.get()).wasAcknowledged();
    }


    @Override
    public boolean update(LambdaUpdateWrapper<T> lambdaUpdateWrapper, boolean multi, Supplier<String> getCollectionName) {
        Query query = QueryBuildUtils.buildQuery(lambdaUpdateWrapper.query());
        Update update = UpdateBuildUtils.buildUpdate(lambdaUpdateWrapper);
        if (multi) {
            return mongoTemplate.updateMulti(query, update, getCollectionName.get()).wasAcknowledged();
        }
        return mongoTemplate.updateFirst(query, update, getCollectionName.get()).wasAcknowledged();
    }

}
