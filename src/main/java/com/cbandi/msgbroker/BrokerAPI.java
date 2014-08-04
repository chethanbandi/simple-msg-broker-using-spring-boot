package com.cbandi.msgbroker;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbandi.msgbroker.exception.BrokerException;

@RestController
public class BrokerAPI {
	@Autowired TopicRegistery topicRegistery;
	@Autowired MessageStore messageStore;
	@Autowired TaskExecutor taskExecutor;

	
	@RequestMapping(value = "/topic", method = RequestMethod.POST)
	public String createTopic(@RequestParam(value = "topic", required = true) String topic, HttpServletResponse response) {
		try {
			topicRegistery.createTopic(topic);
		} catch (Exception e) {
			System.out.println("There is an exception in creating topic");
			
            //response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.setStatus(500);
		}

		return "topic is successfully created";
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publishMessage(@RequestParam(value = "topic", required = true) String topic,
			@RequestParam(value = "message", required = true) String message, HttpServletResponse response) {
		
		try {
			messageStore.store(topic, message);
		} catch (BrokerException e) {
			System.out.println("There is an exception in creating topic");
			e.printStackTrace();
			
            //response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.setStatus(500);
		}

		return "Message is published";
	}
	
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public String subscribeMessage(@RequestParam(value = "topic", required = true) String topic) {
		return "subscription is successfull";
	}
}