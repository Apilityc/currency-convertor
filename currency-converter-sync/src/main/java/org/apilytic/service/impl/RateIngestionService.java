package org.apilytic.service.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.apilytic.model.TwitterMessage;
import org.apilytic.service.CurrencyIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 
 * @author Georgi Lambov
 * 
 */
@Service
public class RateIngestionService implements CurrencyIngestionService {

	private final Cache<Long, TwitterMessage> rateMessages;

	public RateIngestionService() {
		rateMessages = CacheBuilder.newBuilder().maximumSize(10).build();
	}

	@Autowired
	@Qualifier("controlBusChannel")
	private DirectChannel channel;

	@Override
	public void startCurrencyIngestion() {
		final MessagingTemplate m = new MessagingTemplate();
		final Message<String> operation = MessageBuilder.withPayload(
				"@twitter.start()").build();

		m.send(channel, operation);
	}

	@Override
	public void stopCurrencyIngestion() {
		final MessagingTemplate m = new MessagingTemplate();
		final Message<String> operation = MessageBuilder.withPayload(
				"@currencyIngestion.stop()").build();

		m.send(channel, operation);
	}

	@Override
	public boolean isCurrencyIngestionRunning() {
		final MessagingTemplate m = new MessagingTemplate();
		final Message<String> operation = MessageBuilder.withPayload(
				"@currencyIngestion.isRunning()").build();

		@SuppressWarnings("unchecked")
		Message<Boolean> reply = (Message<Boolean>) m.sendAndReceive(channel,
				operation);

		return reply.getPayload();

	}

	/**
	 * Called by Spring Integration to populate a simple LRU cache.
	 * 
	 * @param tweet
	 *            - The Spring Integration tweet object.
	 */
	public void addTwitterMessages(Tweet tweet) {

		this.rateMessages.put(tweet.getId(), new TwitterMessage(tweet.getId(),
				tweet.getCreatedAt(), tweet.getText(), tweet.getFromUser(),
				tweet.getProfileImageUrl()));

	}

	@Override
	public SortedSet<TwitterMessage> getRateMessages() {
		SortedSet<TwitterMessage> messages = new TreeSet<TwitterMessage>();
		messages.addAll(this.rateMessages.asMap().values());
		return messages;

	}

}
