package com.kai.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.kai.core.model.SetCondition;
import com.kai.core.wrapper.LambdaUpdateWrapper;
import lombok.experimental.UtilityClass;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * 修改工具构建工具
 *
 * @author kai
 * @date 2023/6/13
 */
@UtilityClass
public class UpdateBuildUtils {
    /**
     * 构建 mongo修改对象
     *
     * @param lambdaUpdateWrapper 修改条件构造器
     * @return 修改对象
     */
    public static Update buildUpdate(LambdaUpdateWrapper<?> lambdaUpdateWrapper) {
        Update update = new Update();
        List<SetCondition> setConditionList = lambdaUpdateWrapper.getSetConditionList();
        if (CollectionUtil.isNotEmpty(setConditionList)) {
            setConditionList.forEach(setCondition -> {
                update.set(setCondition.getCol(), setCondition.getArgs());
            });
        }
        return update;
    }


}
