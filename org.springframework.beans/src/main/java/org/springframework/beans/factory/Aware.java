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

package org.springframework.beans.factory;

/**
 * Marker superinterface indicating that a bean is eligible to be
 * notified by the Spring container of a particular framework object
 * through a callback-style method.  Actual method signature is
 * determined by individual subinterfaces, but should typically
 * consist of just one void-returning method that accepts a single
 * argument.
 * 
 * 特定父接口的标记，表明实现此接口的类是spring容器中特定的合适的对象，存在回调风格的方法。
 * 实际的方法签名由实际的子接口决定，但是应该由一个典型的方法组成，此方法必须接收一个参数，返回void。
 *
 * <p>Note that merely implementing {@link Aware} provides no default
 * functionality. Rather, processing must be done explicitly, for example
 * in a {@link org.springframework.beans.factory.config.BeanPostProcessor BeanPostProcessor}.
 * Refer to {@link org.springframework.context.support.ApplicationContextAwareProcessor}
 * and {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory}
 * for examples of processing {@code *Aware} interface callbacks.
 * 
 * 注意：实现此接口的类不提供默认的功能。但是，处理时必须明白，比如：
 * 在org.springframework.beans.factory.config.BeanPostProcessor中引用org.springframework.context.support.ApplicationContextAwareProcessor
 * 和org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory
 * 处理*Aware接口的回调。
 *
 * @author Chris Beams
 * @since 3.1
 */
public interface Aware {

}
