package com.appleframework.qos.server.statistics.freemarker.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseDirective implements TemplateDirectiveModel {

	protected void setLocalVariable(String name, Object value, Environment env, TemplateDirectiveBody body) 
			throws TemplateException, IOException {
		TemplateModel sourceVariable = getVariable(name, env);
		setVariable(name, value, env);
		body.render(env.getOut());
		setVariable(name, sourceVariable, env);
	}

	protected void setLocalVariables(Map<String, Object> variables, Environment env, TemplateDirectiveBody body) 
			throws TemplateException, IOException {
		Map<String, Object> sourceVariables = new HashMap<String, Object>();
		for (String name : variables.keySet()) {
			TemplateModel sourceVariable = getVariable(name, env);
			sourceVariables.put(name, sourceVariable);
		}
		setVariables(variables, env);
		body.render(env.getOut());
		setVariables(sourceVariables, env);
	}
	
	public static TemplateModel getVariable(String name, Environment env) throws TemplateModelException {
		Assert.hasText(name);
		Assert.notNull(env);
		return env.getVariable(name);
	}
	
	@SuppressWarnings("deprecation")
	public static void setVariable(String name, Object value, Environment env) throws TemplateException {
		Assert.hasText(name);
		Assert.notNull(env);
		if (value instanceof TemplateModel) {
			env.setVariable(name, (TemplateModel) value);
		} else {
			env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
		}
	}

	@SuppressWarnings("deprecation")
	public static void setVariables(Map<String, Object> variables, Environment env) throws TemplateException {
		Assert.notNull(variables);
		Assert.notNull(env);
		for (Entry<String, Object> entry : variables.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof TemplateModel) {
				env.setVariable(name, (TemplateModel) value);
			} else {
				env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
			}
		}
	}

}