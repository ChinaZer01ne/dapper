package com.dapper.beans;

import java.util.Objects;

/**
 * 仅仅是用来做一个封装对象
 * @author peach
 * @since 2021/4/28 15:03
 */
public class BeanDefinitionHolder {

	private final BeanDefinition beanDefinition;

	private final String beanName;

	public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
		this.beanDefinition = beanDefinition;
		this.beanName = beanName;
	}

	public BeanDefinition getBeanDefinition() {
		return beanDefinition;
	}

	public String getBeanName() {
		return beanName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BeanDefinitionHolder that = (BeanDefinitionHolder) o;
		return beanDefinition.equals(that.beanDefinition) && beanName.equals(that.beanName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(beanDefinition, beanName);
	}
}
