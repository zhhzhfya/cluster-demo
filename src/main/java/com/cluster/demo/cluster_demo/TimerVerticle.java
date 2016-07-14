package com.cluster.demo.cluster_demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.SharedData;

import java.util.Random;

public class TimerVerticle extends AbstractVerticle {
	public static void main(String[] args) {
		// 设置本机的ip
		VertxOptions options = new VertxOptions().setClustered(true).setClusterHost("100.100.100.27");
		Runner.runClustered(TimerVerticle.class, options);
	}

	@Override
	public void start() throws Exception {
		vertx.setPeriodic(1000, id -> {
			Random r = new Random();
			JsonObject machineInfo = new JsonObject();
			machineInfo.put("cpu", String.valueOf(r.nextInt(100)));
			machineInfo.put("memery", String.valueOf(r.nextInt(10000)));
			machineInfo.put("net", String.valueOf(r.nextInt(200)));
			vertx.eventBus().send("system.timer", machineInfo.toString());
			System.out.println(machineInfo.toString());
		});

		SharedData sd = vertx.sharedData();
		vertx.setPeriodic(1000, id -> {
			sd.getClusterWideMap("mymap", map -> {
				map.result().put("a", "分布式map值：" + new Random().nextInt(), r -> {
					if (r.succeeded()) {
					}
				});
			});
		});
	}

}
