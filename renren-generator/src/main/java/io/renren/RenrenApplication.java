package io.renren;

import io.renren.config.CodeGeneratorProperties;
import io.renren.utils.GenUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@MapperScan("io.renren.dao")
public class RenrenApplication {

	public static void main(String[] args) {
		ApplicationContext  applicationContext  = (ApplicationContext)SpringApplication.run(RenrenApplication.class, args);
		CodeGeneratorProperties properties = (CodeGeneratorProperties) applicationContext.getBean("dataSourceProperties");
		GenUtils.setProperties(properties);
	}
}
