package com.example.bookstore.util.listener;

import com.example.bookstore.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderListener {
    @Autowired
    private OrderService orderService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = {"directOrder"})
    public void DirectOrderListener(ConsumerRecord<String, String> record) throws Exception {
        if (record == null || record.value() == null) {
            return;
        }
        Pattern pattern = Pattern.compile("\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(record.value());

        Long bookId;
        Integer userId;
        Integer quantity;
        String[] values = new String[3];

        if (matcher.find()) {
            String content = matcher.group(1); // 获取 {} 内的内容

            // 分割内容并提取每个变量的值
            String[] parts = content.split(", ");
            for(int i = 0; i < 3; ++i){
                String part = parts[i];
                String[] keyValue = part.split("=");
                String value = keyValue[1].trim();
                values[i] = value;
            }
        }
        bookId = Long.parseLong(values[0]);
        userId = Integer.parseInt(values[2]);
        quantity = Integer.parseInt(values[1]);
        orderService.purchaseBookDirectly(bookId, userId, quantity);
        kafkaTemplate.send("orderDone", record.key());
    }

    @KafkaListener(topics = {"cartOrder"})
    public void CartOrderListener(ConsumerRecord<String, String> record) throws Exception {
        if (record == null || record.value() == null) {
            return;
        }
        int userId = 0;
        List<Long> cartItemids = new ArrayList<>();
        try {
            // 创建ObjectMapper对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 解析JSON字符串为JsonNode对象
            JsonNode jsonNode = objectMapper.readTree(record.value());

            // 提取userId的值
            userId = jsonNode.get("userId").asInt();

            // 提取cartItemIds的值
            JsonNode cartItemIdsNode = jsonNode.get("cartItemIds");
            if (cartItemIdsNode.isArray()) {
                for (JsonNode idNode : cartItemIdsNode) {
                    int cartItemId = idNode.asInt();
                    cartItemids.add((long) cartItemId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        orderService.addCartOrder(userId, cartItemids);
        kafkaTemplate.send("orderDone", record.key());
    }

    @KafkaListener(topics = {"orderDone"})
    public void OrderDoneListener(ConsumerRecord<String, String> record) throws Exception {
        if (record == null || record.value() == null) {
            return;
        }
        String value = record.value();
        System.out.println("处理订单完成消息：" + value);
        //TODO websocket 通知用户
    }
}
