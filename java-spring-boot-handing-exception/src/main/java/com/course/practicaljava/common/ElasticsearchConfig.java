package com.course.practicaljava.common;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = { "com.course.practicaljava.repository" })
public class ElasticsearchConfig {

	private static final String esHost = "127.0.0.1";
	private static final int esPort = 9300;

	@Bean
	public Client client() throws UnknownHostException {
		var transportClient = new PreBuiltTransportClient(Settings.EMPTY);
		transportClient.addTransportAddress(
				new TransportAddress(new InetSocketAddress(InetAddress.getByName(esHost), esPort)));

		return transportClient;
	}

	@Bean
	public ElasticsearchOperations esTemplate() throws UnknownHostException {
		return new ElasticsearchTemplate(client());
	}

}
