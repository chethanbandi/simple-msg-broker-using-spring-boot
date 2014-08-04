package com.cbandi.msgbroker;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import com.cbandi.msgbroker.exception.BrokerException;

@Component
public class MessageStore {
	private static final CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<String>();

	public void store(String topic, String message) throws BrokerException {
		String messageKey = getMessageKey(topic);
		String fileName = getFileName(topic, messageKey);
		File msgFile = new File(fileName);

		try {
			msgFile.createNewFile();

			FileOutputStream mfileStream = new FileOutputStream(fileName);
			mfileStream.write(message.getBytes());
			mfileStream.flush();
			mfileStream.close();

			messages.add(messageKey);

		} catch (Exception e) {
			System.out.println("Exception occured while storing file");
			e.printStackTrace();

			throw new BrokerException("unbale to store file", e);
		}
	}

	public final List<String> getMessages() {
		return messages;
	}

	public void removeMessage(String messageId) {
		messages.remove(messageId);

		File msgFile = new File(K.MSG_STORE_PATH + messageId);
		msgFile.delete();
	}

	private String getFileName(String topic, String messageKey) {
		return K.MSG_STORE_PATH + messageKey;
	}

	private String getMessageKey(String topic) {
		return topic + "/" + UUID.randomUUID();
	}
}
