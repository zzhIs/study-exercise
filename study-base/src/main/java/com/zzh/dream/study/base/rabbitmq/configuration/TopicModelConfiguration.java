package com.zzh.dream.study.base.rabbitmq.configuration;


/**
 * 主题模式
 * topics模式的交换机和定向交换机一样，唯一不同的是解析routingkey的时候，定向交换机会根据消息中的routingkey找到对应的队列，
 * 这个值是确定的；但是topic模式又称为通配符模式，这个在绑定的时候指定的routingkey是可以以通配符的形式存在的，这里只有两种通配符符号：
 * #代表1个或多个单词 例如 abc.#则会匹配到 abc.d 或abc.d.f
 * *代表一个单词 例如。abc.*则只能匹配到abc.a 或abc.f的消息
 */
//@Configuration
public class TopicModelConfiguration {

}
