# apple-qos
Zero Configure needed Log based QOS system.
Support spring，dubbo，java.

## Collector
The unify log tool for GOS.

## Qos Agent
Collect the logs in runtime, and send to monitor via kafka.

## Qos Monitor
Consume Log message from kafka, calcuate and save to database.

## Qos Statistics
Support Web UI of Statisics results.

# usage

* Add Maven Dependency
```
	<dependency>
		<groupId>com.appleframework.qos</groupId>
		<artifactId>apple-qos-collector-spring</artifactId>
		<version>0.0.2-SNAPSHOT</version>
	</dependency>
```

* Integret the Collector

```
	<bean id="qosCollectInterceptor" class="com.appleframework.qos.collector.spring.CollectInterceptor"/>
	<aop:config>
		<aop:pointcut id="api_wapi" expression="execution(* com.kplus.carwashing.*api.*Controller.*(..)))"/>
		<aop:advisor pointcut-ref="api_wapi" advice-ref="qosCollectInterceptor"/>
	</aop:config>
```

* Run Monitor
```./bin/qos_montior.sh start ./config/monitor.properties ```

# Run Agent
``` ./bin/qos_agent.sh start ./config/agent.properties ```


