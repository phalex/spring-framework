/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.env;

/**
 * Interface representing the environment in which the current application is running.
 * Models two key aspects of the application environment: <em>profiles</em> and
 * <em>properties</em>. Methods related to property access are exposed via the
 * {@link PropertyResolver} superinterface.
 * 
 * 代表当前应用运行环境的接口。主要包括应用环境的两个方面：配置和属性。与属性有关的方法通过PropertyResolver父接口访问。
 *
 * <p>A <em>profile</em> is a named, logical group of bean definitions to be registered
 * with the container only if the given profile is <em>active</em>. Beans may be assigned
 * to a profile whether defined in XML or via annotations; see the spring-beans 3.1 schema
 * or the {@link org.springframework.context.annotation.Profile @Profile} annotation for
 * syntax details. The role of the {@code Environment} object with relation to profiles is
 * in determining which profiles (if any) are currently {@linkplain #getActiveProfiles
 * active}, and which profiles (if any) should be {@linkplain #getDefaultProfiles active
 * by default}.
 * 
 * 配置是一个命名的， 容器中bean定义的逻辑组，只有给出的配置才是激活的。
 * 可以通过xml文件或者注解进行配置；通过spring-beans 3.1 schema或者{@link org.springframework.context.annotation.Profile @Profile}查看语法详情。
 * Environment对象的角色与配置有关，他决定了哪些配置是当前使用的，哪些是默认值。
 *
 * <p><em>Properties</em> play an important role in almost all applications, and may
 * originate from a variety of sources: properties files, JVM system properties, system
 * environment variables, JNDI, servlet context parameters, ad-hoc Properties objects,
 * Maps, and so on. The role of the environment object with relation to properties is to
 * provide the user with a convenient service interface for configuring property sources
 * and resolving properties from them.
 * 
 * 属性在几乎所有的应用中都扮演着一个重要的角色，同时可能来自不同的来源：属性文件，java虚拟机系统属性，系统环境变量，JNDI，servlet上下文参数，ad-hoc属性对象，
 * maps等等。环境对象的角色是提供用户方便的服务接口配置属性来源和解析属性。
 *
 * <p>Beans managed within an {@code ApplicationContext} may register to be {@link
 * org.springframework.context.EnvironmentAware EnvironmentAware} or {@code @Inject} the
 * {@code Environment} in order to query profile state or resolve properties directly.
 * 
 * bean在一个ApplicationContext中进行管理，登记在org.springframework.context.EnvironmentAware EnvironmentAware 或者 注入Environment
 * 为了查询配置状态或者解析属性路径。
 *
 * <p>In most cases, however, application-level beans should not need to interact with the
 * {@code Environment} directly but instead may have to have <code>${...}</code> property
 * values replaced by a property placeholder configurer such as
 * {@link org.springframework.context.support.PropertySourcesPlaceholderConfigurer
 * PropertySourcesPlaceholderConfigurer}, which itself is {@code EnvironmentAware} and
 * as of Spring 3.1 is registered by default when using
 * {@code <context:property-placeholder/>}.
 * 
 * 然而，在一般情况下，应用级的bean对象不应要求直接通过Environment路径来配置，而是应该通过${...}这种属性变量通过属性替换器进行配置，比如：
 * org.springframework.context.support.PropertySourcesPlaceholderConfigurer， 他本身就实现了EnvironmentAware接口， 另外在spring3.1中如果使用
 * <context:property-placeholder/>将默认注册此替换器。
 *
 * <p>Configuration of the environment object must be done through the
 * {@code ConfigurableEnvironment} interface, returned from all
 * {@code AbstractApplicationContext} subclass {@code getEnvironment()} methods. See
 * {@link ConfigurableEnvironment} Javadoc for usage examples demonstrating manipulation
 * of property sources prior to application context {@code refresh()}.
 * 
 * 配置这个环境对象必须从AbstractApplicationContext的所有子类的getEnvironment方法返回的ConfigurableEnvironment接口。
 * 查看ConfigurableEnvironment的javadoc查看比如：演示在应用上下文refresh方法之前操作属性来源的使用方法。
 *
 * @author Chris Beams
 * @since 3.1
 * @see PropertyResolver
 * @see EnvironmentCapable
 * @see ConfigurableEnvironment
 * @see AbstractEnvironment
 * @see StandardEnvironment
 * @see org.springframework.context.EnvironmentAware
 * @see org.springframework.context.ConfigurableApplicationContext#getEnvironment
 * @see org.springframework.context.ConfigurableApplicationContext#setEnvironment
 * @see org.springframework.context.support.AbstractApplicationContext#createEnvironment
 */
public interface Environment extends PropertyResolver {

	/**
	 * Return the set of profiles explicitly made active for this environment. Profiles
	 * are used for creating logical groupings of bean definitions to be registered
	 * conditionally, for example based on deployment environment.  Profiles can be
	 * activated by setting {@linkplain AbstractEnvironment#ACTIVE_PROFILES_PROPERTY_NAME
	 * "spring.profiles.active"} as a system property or by calling
	 * {@link ConfigurableEnvironment#setActiveProfiles(String...)}.
	 * 
	 * 返回环境中明确设定的激活配置的集合。配置用来创建bean定义的逻辑分组有条件的注册，例如：部署环境。配置通过设置
	 * AbstractEnvironment#ACTIVE_PROFILES_PROPERTY_NAME 【spring.profiles.active】来激活作为系统属性或者通过调用
	 * ConfigurableEnvironment#setActiveProfiles(String...)方法来激活。
	 *
	 * <p>If no profiles have explicitly been specified as active, then any {@linkplain
	 * #getDefaultProfiles() default profiles} will automatically be activated.
	 *
	 * @see #getDefaultProfiles
	 * @see ConfigurableEnvironment#setActiveProfiles
	 * @see AbstractEnvironment#ACTIVE_PROFILES_PROPERTY_NAME
	 */
	String[] getActiveProfiles();

	/**
	 * Return the set of profiles to be active by default when no active profiles have
	 * been set explicitly.
	 * 
	 * 返回默认激活的配置集合。
	 *
	 * @see #getActiveProfiles
	 * @see ConfigurableEnvironment#setDefaultProfiles
	 * @see AbstractEnvironment#DEFAULT_PROFILES_PROPERTY_NAME
	 */
	String[] getDefaultProfiles();

	/**
	 * Return whether one or more of the given profiles is active or, in the case of no
	 * explicit active profiles, whether one or more of the given profiles is included in
	 * the set of default profiles
	 * 
	 * 返回指定的一个或者多个给定的配置，当没有指定时返回默认的配置。
	 * @throws IllegalArgumentException if called with zero arguments
	 * @throws IllegalArgumentException if any profile is null, empty or whitespace-only
	 * @see #getActiveProfiles
	 * @see #getDefaultProfiles
	 */
	boolean acceptsProfiles(String... profiles);

}
