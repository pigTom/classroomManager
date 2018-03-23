package com.tang.myCloud.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
	public static void copy(Object dest, Object orig)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		// get set method of dest object
		Method[] methods = sets(dest.getClass());
		for (Method method : methods) {

			String methodName = method.getName();

			// get value of the orig
			Object value = getValue("get" + methodName.substring(3), orig);
			if (value != null) {
				// get the value that should be copied to dest object
				Object obj = BeanConverter.converter.convert(
						method.getParameterTypes()[0], value);
				// copy the value to dest object
				if (obj != null) {
					method.invoke(dest, obj);
				}
			}
		}
	}

	/**
	 * methodName must start with get and the method named with methodName has
	 * no argument
	 * 
	 * @param methodName
	 * @param obj
	 * @return
	 */
	public static Object getValue(String methodName, Object obj) {
		Method method = null;
		Object o = null;
		try {
			method = obj.getClass().getMethod(methodName);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			return null;

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (method != null)
			try {
				o = method.invoke(obj);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return o;
	}
	/**
	 * set value to entity field with specified <code>name</code>(ignored care),
	 * using <code>Converter</code> to converter type.if you want customerized <code>Converter</code>,
	 * please user <code>BeanConverter.register(Converter converter, Class<?> clazz)
	 * 
	 * @param entity
	 * @param name
	 * @param value
	 */
	public static void setProperty(Object entity, String name, Object value) {
		Field[] fields = entity.getClass().getDeclaredFields();
		for( Field field : fields){
			field.setAccessible(true);
			if( field.getName().equalsIgnoreCase(name) ){
				setValue(field,entity, value);
			}
		}
	}
	/**
	 * according the <code>value, set value to field,
	 * if field is like that: <code>String name;</code>and <code>value = "Tom";<code>,
	 * then the name will be <code>name="Tom";</code>
	 * if the type is not equal, the result will be depend on <code>Converter</code>
	 * @param filed
	 * @param obj
	 * @param value
	 */
	private static void setValue(Field field, Object obj, Object value){
		
		Object o = BeanConverter.converter.convert(
				field.getType(), value);
		if( o != null )
			try {
				field.set(obj, o);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	// // get Methods named contains start with get string
	// private static Method[] gets(Class<?> clazz) {
	// Method[] methods = clazz.getMethods();
	// List<Method> list = new ArrayList<>();
	// for (Method method : methods) {
	// if (method.getName().startsWith("get")
	// && !method.getName().equals("getClass"))
	// list.add(method);
	// }
	//
	// Method[] a = new Method[list.size()];
	// return list.toArray(a);
	// }

	// get Methods named contains start with set string
	private static Method[] sets(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		List<Method> list = new ArrayList<>();
		for (Method method : methods) {
			if (method.getName().startsWith("set"))
				list.add(method);
		}

		Method[] a = new Method[list.size()];
		return list.toArray(a);
	}

	/*
	 * private static Map<String, Object> getFields(Method[] methods, Object
	 * obj) throws IllegalAccessException, IllegalArgumentException,
	 * InvocationTargetException { Map<String, Object> map = new HashMap<>();
	 * for (Method method : methods) { Object o = method.invoke(obj); if (o !=
	 * null) map.put(method.getName().replaceFirst("g", "s"), o); } return map;
	 * }
	 */
}
