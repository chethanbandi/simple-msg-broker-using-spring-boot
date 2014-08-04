package com.cbandi.msgbroker;

import java.util.List;


public class DeliveryManager implements Runnable {

	public void run() {
		MessageStore messageStore = MessageStoreFactory.getMessageStore();

		while (true) {
			// check the registery for consumers and call consumers to deliver
			// the message
			System.out.println("I am running");

			List<String> messageIds = messageStore.getMessages();

			if (messageIds != null) {
				for (String messageId : messageIds) {
					// TODO look all the subscribers and deliver to them
					// span a thread for each subscriber. Use threadpool
					System.out.println("The message is " + messageId);
					messageStore.removeMessage(messageId);
				}
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
