package com.zzh.dream.study.base.bistoury;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * @description: 测试
 * @author: zhangzihao
 * @date: 21/03/2022
 **/
@Slf4j
public class BistouryBiz implements InitializingBean {

    private volatile int count = 10000;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("BistouryBiz.afterPropertiesSet方法执行了...");
        while (count-- > 0) {
            new Thread(() -> {
                DateUtil dateUtil = new DateUtil();
                log.info("线程执行中...." + dateUtil.toString());
            }).start();

            Thread.sleep(10000L);
        }

    }


}
