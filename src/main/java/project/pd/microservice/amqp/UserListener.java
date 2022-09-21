package project.pd.microservice.amqp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import project.pd.microservice.model.Decision;
import project.pd.microservice.model.User;
import project.pd.microservice.service.UserService;

@Component
public class UserListener {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserProducer userProducer;

	@RabbitListener(queues = "${sr.rabbitmq.queue_1.name}")
	public void insertOrUpdate(@Payload Decision decision) {
		try {
			User usuario = this.userService.getUserById(decision.getIduser());
			if(usuario != null) {
				List<Long> decisions = usuario.getDecisions();
							
				if(decisions.contains(decision.getIdDecision()) == false) {
					decisions.add(decision.getIdDecision());
					usuario.setDecisions(decisions);
					this.userService.insertOrUpdate(usuario);
				}		
				this.userProducer.sendDecisionToQueue("bk_1", decision);
			}
			else {
				this.userProducer.sendToQueue("bk_1", "É necessario a persistência do usuário!");
				this.userProducer.sendToDeleteDecision("bk_1", decision.getIdDecision());
			}
		}
		catch(Exception e) {
			this.userProducer.sendToQueue("bk_1", "É necessario que o id do usuário esteja dentro do relacionamento!");
			this.userProducer.sendToDeleteDecision("bk_1", decision.getIdDecision());
		}
	}
	
	@RabbitListener(queues = "${sr.rabbitmq.queue_5.name}")
	public void deleteInList(@Payload Decision decision) {
		try {
			User usuario = this.userService.getUserById(decision.getIduser());
			List<Long> decisions = usuario.getDecisions();
			for(int i = 0; i < decisions.size(); i++) {
				if(decisions.get(i).equals(decision.getIdDecision())) {
					decisions.remove(i);
				}
			}
			usuario.setDecisions(decisions);
			this.userService.insertOrUpdate(usuario);
		}
		catch(Exception e) {
			this.userProducer.sendToQueue("bk_1", "Decisão Invalida!");
		}
	}
}
