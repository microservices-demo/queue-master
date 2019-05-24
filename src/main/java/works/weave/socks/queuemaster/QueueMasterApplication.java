package works.weave.socks.queuemaster;

import com.feedzai.commons.tracing.engine.JaegerTracingEngine;
import com.feedzai.commons.tracing.engine.TraceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class QueueMasterApplication {
	public static void main(String[] args) throws InterruptedException {
		TraceUtil.init(new JaegerTracingEngine.Builder().withSampleRate(1).withCacheMaxSize(10000).withCacheDuration(Duration.ofDays(2)).withProcessName("Queue Master Service").withIp("172.31.0.10").build());
		SpringApplication.run(QueueMasterApplication.class, args);
	}
}
