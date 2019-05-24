package works.weave.socks.queuemaster;

import com.feedzai.commons.tracing.engine.TraceUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import works.weave.socks.shipping.entities.Shipment;

import java.io.Serializable;

@Component
public class ShippingTaskHandler {

	@Autowired
	DockerSpawner docker;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "shipping-task", durable = "false"),
			exchange = @Exchange(value = "shipping-task-exchange"))
	)
	public void handleMessage(Shipment shipment, Message message) {
		System.out.println("Received shipment task: " + shipment.getName());
		TraceUtil.instance().newProcess(() -> {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "Received Shipment", TraceUtil.instance().deserializeContext((Serializable) message.getMessageProperties().getHeaders()));
		//docker.init();
		//docker.spawn();
	}
}
