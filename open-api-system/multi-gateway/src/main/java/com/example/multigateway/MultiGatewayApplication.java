package com.example.multigateway;

import com.example.multicommon.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author pengYuJun
 */
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@EnableDubbo
public class MultiGatewayApplication {

	@DubboReference
	private DemoService demoService;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MultiGatewayApplication.class, args);
		MultiGatewayApplication application = context.getBean(MultiGatewayApplication.class);
		String result = application.doSayHello("world");
		String result2 = application.doSayHello2("world");
		System.out.println("result: " + result);
		System.out.println("result: " + result2);
	}

	public String doSayHello(String name) {
		return demoService.sayHello(name);
	}

	public String doSayHello2(String name) {
		return demoService.sayHello2(name);
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("path_route", r -> r.path("/baidu")
//						.uri("http://baidu.org"))
//				.route("host_route", r -> r.path("/yupiicu")
//						.uri("http://yupi.icu"))
//				.build();
//	}

}
