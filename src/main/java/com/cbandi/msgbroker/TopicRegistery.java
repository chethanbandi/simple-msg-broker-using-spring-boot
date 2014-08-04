package com.cbandi.msgbroker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cbandi.msgbroker.exception.TopicRegisteryException;

@Component
public class TopicRegistery {
	private static final List<String> registery = new ArrayList<String>();

	public void createTopic(String topic) throws TopicRegisteryException {
		if(registery.contains(topic)) return;
		
		synchronized(registery) {
			registery.add(topic);
		}
		
		try {
			String topicDirPath = K.MSG_STORE_PATH + topic;
			File topicDir = new File(topicDirPath);
			topicDir.mkdir();
			
		} catch(Exception e) {
			registery.remove(topic);
			
			throw new TopicRegisteryException("There is an exception creating topic", e);
		}
		
	}
}
