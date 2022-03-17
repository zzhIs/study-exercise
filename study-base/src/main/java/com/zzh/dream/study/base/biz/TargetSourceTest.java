package com.zzh.dream.study.base.biz;

import org.springframework.aop.TargetSource;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 17/03/2022
 **/
public class TargetSourceTest implements TargetSource {
    @Override
    public Class<?> getTargetClass() {
        return null;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getTarget() throws Exception {
        return null;
    }

    @Override
    public void releaseTarget(Object target) throws Exception {

    }
}
