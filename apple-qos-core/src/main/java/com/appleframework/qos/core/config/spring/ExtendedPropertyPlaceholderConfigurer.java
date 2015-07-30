package com.appleframework.qos.core.config.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Map;
import java.util.Properties;

/**
 * Created by haiyang on 15/7/29.
 */
class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);

        final Properties systemProperties = System.getProperties();


        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(
                DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);
        for (Map.Entry<Object,Object> entry : props.entrySet()) {
            String stringKey = String.valueOf(entry.getKey());
            String stringValue = String.valueOf(entry.getValue());
            stringValue = helper.replacePlaceholders(stringValue, props);

            if (!systemProperties.contains(stringKey)){
                System.out.println("=====>" + stringKey + ": " + stringValue);
                systemProperties.put(stringKey, stringValue);
            }
        }
    }

}
