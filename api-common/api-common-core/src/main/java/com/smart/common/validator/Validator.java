package com.smart.common.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Validator<T> {
	
	private static ValidatorFactory factory = null;
	private static javax.validation.Validator validator = null;
	static{
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator(); 
	}
	
	/**
	 * 校验对象，如果校验成功返回null,不成功返回校验出错信息
	 * @param obj
	 * @return
	 */
	public  String validate(T obj){
		if(null == obj){
			return null;
		}
		obj.getClass().getName();
		Set<ConstraintViolation<T>> violations = validator.validate(obj);
		String msg = "";
		if(violations.size() == 0) { 
   		   return null;
	   	}
		
	   	else {
	   		for(ConstraintViolation<T> violation: violations) {
	   			msg += violation.getPropertyPath()+":"+violation.getMessage()+"\n";
	   		}
	   	}
		return msg;
	}
	
	/**
	 * 校验对象，如果校验成功返回null,不成功返回校验出错信息
	 * @param obj
	 * @param properties
	 * @return
	 */
	public String validate(T obj,List<String> properties){
		if(null == obj || properties == null || properties.size()<1){
			return null;
		}
		String msg = "";
		int errorCount = 0;
		Set<ConstraintViolation<T>> violations;
		for(String prop:properties){
			violations = validator.validateProperty(obj, prop);
			errorCount += violations.size();
			for(ConstraintViolation<T> violation: violations) {
	   			msg += violation.getPropertyPath()+":"+violation.getMessage();
	   		}
		}
		
		return errorCount > 0  ? msg : null ;
	}
	
	/**
	 * 校验对象，如果校验成功返回null,不成功返回校验出错信息
	 * @param obj
	 * @param properties
	 * @return
	 */
	public String validate(T obj,String[] properties){
		if(null == obj || properties == null || properties.length<1){
			return null;
		}
		List<String> prop = new ArrayList<String>();
		for(String tmp:properties){
			prop.add(tmp);
		}
		return this.validate(obj, prop);
	}
	
	/**
	 * 取对象中能检验通过的字段
	 * 如果properties为null则校验所有的字段  
	 * @param obj
	 * @param properties
	 * @return
	 */
	public List<String> getValidatedProperties(T obj,List<String> properties) {
		if(properties==null || properties.size()<1){
			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
			   properties.add(f.getName());
			}
		}
		Set<ConstraintViolation<T>> violations;
		List<String> result = new ArrayList<String>();
		
		for(String prop:properties){
			violations = validator.validateProperty(obj, prop);
			if(violations.size()==0){
				result.add(prop);
			}
			
		}
		return result;
	}
	
	/**
	 * 取对象中能检验通过的字段
	 * 如果properties为null则校验所有的字段
	 * @param obj
	 * @param properties
	 * @return
	 */
	public List<String> getValidatedProperties(T obj,String[] properties) {
		List<String> tmp = new ArrayList<String>();
		if(properties!=null) {
			for(String prop:properties){
				tmp.add(prop);
			}
		}
		return this.getValidatedProperties(obj, tmp);
	}
	
	/**
	 * 校验某些字段以外的所有的字段
	 * @param obj
	 * @param properties
	 * @return
	 */
	public List<String> getValidatedWithoutProperties(T obj,List<String> properties){
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		List<String> all = new ArrayList<String>();
		for(Field f : fields) {
			if(properties!=null && properties.contains(f.getName())){
				continue;
			}
			all.add(f.getName());
			
		}
		Set<ConstraintViolation<T>> violations;
		List<String> result = new ArrayList<String>();
		for(String prop:all){
			violations = validator.validateProperty(obj, prop);
			if(violations.size()==0){
				result.add(prop);
			}
			
		}
		return result;
		
	}
	
	/**
	 * 校验某些字段以外的所有的字段
	 * @param obj
	 * @param properties
	 * @return
	 */
	public List<String> getValidatedWithoutProperties(T obj,String[] properties){
		List<String> tmp = new ArrayList<String>();
		if(properties!=null) {
			for(String prop:properties){
				tmp.add(prop);
			}
		}
		return this.getValidatedWithoutProperties(obj, tmp);
	}

}
