package com.zzh.dream.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 17/03/2022
 **/
@Component
public class XxlJobHandler {
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("zzhJobHandler")
    public void zzhJobHandler() throws Exception {
        System.out.println("zzhJobHandler开始执行了。。。。。");
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }

        System.out.println("zzhJobHandler执行结束。。。。。");
        // default success
    }
}
