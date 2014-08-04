package com.cbandi.msgbroker;

import java.util.HashMap;
import java.util.Map;

public class MessageStoreFactory {
	// Use interface for messageStore
	private static final Map<String, Object> messageStores = new HashMap<String, Object>();

	public static MessageStore getMessageStore() {
		MessageStore messageStore = new MessageStore();
		
		messageStores.put("default", messageStore);
		
		return messageStore;
	}
}
