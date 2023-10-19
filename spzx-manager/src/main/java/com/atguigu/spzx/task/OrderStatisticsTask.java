package com.atguigu.spzx.task;

import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import com.atguigu.spzx.service.OrderInfoService;
import com.atguigu.spzx.service.OrderStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务 订单统计
 */
@Component
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderStatisticsService orderStatisticsService;
    /**
     * cron = "0 0 2 * * ?" 每天凌晨2点运行一次
     * cron = "0/2 * * * * ?" 每隔2秒执行一次
     */
    @Scheduled(cron = "0 0 2 * * ?") //声明定时任务的运行周期
    public void executeOrderStatistics(){
       //获取前一天的日期 2023-10-17
       String data = DateUtil.offsetDay(new Date(),-1).toString(new SimpleDateFormat("yyyy-MM-dd"));

        //获取前一天订单数据
        OrderStatistics orderStatistics =orderInfoService.getOrderTotalAmount(data);
        //将统计的数据添加到order_statistics表
        orderStatisticsService.add(orderStatistics);
    }

}
