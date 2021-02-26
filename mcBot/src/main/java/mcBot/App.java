/**
 * 
 */
package mcBot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * @author Noah Jaussi
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String token = "ODE0NzEzMDg1MjYwNTk1MjMw.YDh2sA.uBud69rcPIM3J-n4SZhyXuPE7TM";
		final DiscordClient cli = DiscordClient.create(token);
		final GatewayDiscordClient gateway = cli.login().block();
		
		gateway.on(MessageCreateEvent.class).subscribe(event -> {
			final Message message = event.getMessage();
			
			if ("!wake".equals(message.getContent())) {
				final MessageChannel channel = message.getChannel().block();
				channel.createMessage("I am awake now").block();
			}
		});
		
		gateway.onDisconnect().block();
		
	}

}
