package com.cluster.demo.cluster_demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

import com.hazelcast.core.Message;

/**
 * 
 * @author zzy
 *
 */
public class Receiver extends AbstractVerticle {

	public static void main(String[] args) {
		Runner.runClustered(Receiver.class);
	}

	@Override
	public void start() throws Exception {

		EventBus eb = vertx.eventBus();

		eb.consumer("system.timer", message -> {
			System.out.println("Received message: " + message.body());
			// Now send back reply
			// message.reply("pong!");
			});
		System.out.println("Receiver ready!");
	}

}
