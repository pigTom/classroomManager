package com.tang.myCloud.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("aop")
@Aspect
public class DaoAop {
	@Pointcut("execution(* com.tang.myCloud.dao.*.add(..))")
	public void pc() {
		System.out.println("this is pc");
	}

	@Before("pc()")
	public void before() {

		System.out.println("do before");

	}

	@After("pc()")
	public void after() {
		System.out.println("do after");
	}
}
