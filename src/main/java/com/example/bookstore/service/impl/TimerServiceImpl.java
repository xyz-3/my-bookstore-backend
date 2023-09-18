package com.example.bookstore.service.impl;

import com.example.bookstore.service.TimerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.text.SimpleDateFormat;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class TimerServiceImpl implements TimerService {

    private long time = 0;

    private String startTimeStr = "";

    @Override
    public void timerStart() {
        if(time == 0){
            time = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            startTimeStr = simpleDateFormat.format(new Date(time));
        }
    }

    @Override
    public String timerStop() {
        if(time > 0){
            long time_interval = System.currentTimeMillis() - time;
            time = 0;
            long hour = time_interval / (1000 * 60 * 60);
            long minute = (time_interval / 1000 % 3600) / 60;
            long second = (time_interval / 1000 % 3600) % 60;
            return "计时器开始时间：" + startTimeStr + "\n" + "计时器运行时间：" + hour + "小时" + minute + "分钟" + second + "秒";
        }else{
            return "没有发起计时器";
        }
    }
}
