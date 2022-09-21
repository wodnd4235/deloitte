package com.deloitte.solution.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(value = "popbill")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class PopbillProperties {
	
	private final String linkId;
	private final String secretKey;
	private final String corpNum;
}
