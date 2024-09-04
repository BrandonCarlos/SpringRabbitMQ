package com.dailycodebuffer.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class MQConfig {

    private static final String QUEUE = "message_queue";
    private static final String EXCHANGE = "message_exchange";
    private static final String RoutingKey = "message_routingKey";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RoutingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return (MessageConverter) new Jackson2JsonMessageConverter();
    }


}
