package com.gobeyond.hazelcast.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class AppConfig {

	@Bean
	public HazelcastInstance instance() {

		return Hazelcast.newHazelcastInstance();

	}

}
