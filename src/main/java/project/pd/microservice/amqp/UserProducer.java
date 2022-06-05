package project.pd.microservice.amqp;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import project.pd.microservice.model.Decision;
import project.pd.microservice.model.User;
import project.pd.microservice.service.UserService;

@Component
public class UserProducer {
	
	@Value("${sr.rabbitmq.exchange_1.name}")
	private String exchangeName;
	
	@Value("${sr.rabbitmq.queue_2.name}")
	private String queue_2;
	
	@Value("${sr.rabbitmq.queue_3.name}")
	private String queue_3;
	
	@Value("${sr.rabbitmq.queue_4.name}")
	private String queue_4;
	
	@Value("${sr.rabbitmq.queue_6.name}")
	private String queue_6;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
		
	public void sendDecisionToQueue(String key, Decision decision) {
		this.rabbitTemplate.convertAndSend(this.exchangeName, this.queue_2 + "_" + key, decision);
	}
	
	public void sendToQueue(String key, String message) {
		this.rabbitTemplate.convertAndSend(this.exchangeName, this.queue_3 + "_" + key, message);
	}
	
	public void sendToDeleteUser(String key, List<Long> decisions) {
		this.rabbitTemplate.convertAndSend(this.exchangeName, this.queue_4 + "_" + key, decisions);
	}
	
	public void sendToDeleteDecision(String key, Long decisionID) {
		this.rabbitTemplate.convertAndSend(this.exchangeName, this.queue_6 + "_" + key, decisionID);
	}
}
