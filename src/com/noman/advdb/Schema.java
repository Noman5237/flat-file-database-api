package com.noman.advdb;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public interface Schema extends Serializable, Cloneable {
//	FIXME: Implement proper flat serialization for Schemas
	
	/**
	 * @param key
	 *
	 * @return
	 */
	default Object get(String key) {
		String methodName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
		try {
			return getClass().getMethod(methodName).invoke(this);
		} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	default void set(String key, Object obj) throws UnsupportedOperationException {
		String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
		Optional<Method> requiredSetterMethod = Arrays.stream(getClass().getMethods())
				.filter(method -> method.getName().equals(methodName))
				.findFirst();
		if (requiredSetterMethod.isPresent()) {
			Method setter = requiredSetterMethod.get();
			Class<?> parameterType = setter.getParameterTypes()[0];
			try {
				setter.invoke(this, parameterType.cast(obj));
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new UnsupportedOperationException(e.getMessage() + " .Cannot perform required operation which is not supported by the schema");
			}
		}
	}
}
