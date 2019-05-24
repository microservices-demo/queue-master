package works.weave.socks.queuemaster.configuration;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import works.weave.socks.queuemaster.ShippingTaskHandler;

@Configuration
public class ShippingConsumerConfiguration extends RabbitMqConfiguration
{
	protected final String queueName = "shipping-task";

    @Autowired
    private ShippingTaskHandler shippingTaskHandler;

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setQueue(this.queueName);
        template.setMessageConverter(jsonMessageConverter());
		return template;
	}

    @Bean
	public Queue queueName() {
		return new Queue(this.queueName, false);
	}

	@Bean
	public RabbitListenerContainerFactory listenerContainer() {
		SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
		container.setConnectionFactory(connectionFactory());
		return container;
	}

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(shippingTaskHandler, jsonMessageConverter());
    }
}
