package bh.gov.cio.gbs.aspect;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bh.gov.cio.gbs.exception.ApplicationException;

@Aspect
public class SupplementaryLogger {

	public static final Logger logger = LoggerFactory
			.getLogger(SupplementaryLogger.class);
	
	
//	@Pointcut("execution(* bh.gov.cio.gbs.service.SupplementaryModelManager.*(..))")
//	public void SupplementaryModelManager() {
//
//	}
//	
////	@Pointcut("execution(* bh.gov.cio.gbs.dataaccess.QueryStore.*(..))")
////	public void dataaccess() {
////	}
//	
//	@Pointcut("execution(* *(..))")
//	public void methodPointcut() {
//	}
	
	
	
	
	@SuppressWarnings("rawtypes")
	public void logBefore(JoinPoint joinPoint) {

		Logger logger = getLogger(joinPoint);
		Object[] paramValues = joinPoint.getArgs();
		Class[] paramNames = ((CodeSignature) joinPoint
				.getSignature()).getParameterTypes();
		StringBuilder logLine = new StringBuilder(joinPoint
				.getSignature().getName()).append("(");
		if (paramNames.length != 0)
			logParamValues(logLine, paramNames, paramValues);
		logLine.append(") : started");
		logger.info(logLine.toString());
	}

	@SuppressWarnings("rawtypes")
	public void logAfter(JoinPoint joinPoint) {
		Logger logger = getLogger(joinPoint);
		Object[] paramValues = joinPoint.getArgs();
		Class[] paramNames = ((CodeSignature) joinPoint
				.getSignature()).getParameterTypes();
		StringBuilder logLine = new StringBuilder(joinPoint
				.getSignature().getName()).append("(");
		if (paramNames.length != 0)
			logParamValues(logLine, paramNames, paramValues);
		logLine.append(") : end");
		logger.info(logLine.toString());
	}

	@SuppressWarnings("rawtypes")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		Object r = null;
		String methodName = joinPoint.getSignature().getName();
		Date before = new Date();
		try {
			Logger logger = getLogger(joinPoint);
			StringBuilder logLine = new StringBuilder(methodName).append(" : ");
			r = joinPoint.proceed();
			Date after = new Date();
			logLine.append(" [ execution-time = ").append(
					(after.getTime() - before.getTime())).append(" ms ").append(" ] ");
			logger.info(logLine.toString());
			if (r != null && (!(r instanceof List) || ((List) r).size() != 0)) {
				StringBuilder rv = new StringBuilder(methodName).append("() : ").append(" [ Return Value : ");
				if (r instanceof List)
					rv.append(((List) r).size()).append(" item(s)");
				else {
					rv.append(r);
				}
				rv.append("  ] ");
				logger.info(rv.toString());
			}
		
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			logger.error("- Exception Thrown : " + ((throwable.getMessage() != null) ? throwable.getMessage() : "<>"));
			throw new ApplicationException(throwable);
		}

		return r;
	}

	
	
	
	private Logger getLogger(JoinPoint joinPoint) {
		try {
			@SuppressWarnings("rawtypes")
			Class declaringType = joinPoint.getSignature().getDeclaringType();
			Field loggerField = declaringType.getDeclaredField("logger");
			loggerField.setAccessible(true);
			return (Logger) loggerField.get(null);
		} catch (NoSuchFieldException e) {
			return logger;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	private void logParamValues(StringBuilder logLine, Class[] paramNames,
			Object[] paramValues) {
		for (int i = 0; i < paramValues.length; i++) {
			logLine.append(paramNames[i].getSimpleName()).append("=");
			if (paramValues[i] == null)
				logLine.append("<>");
			else if (paramValues[i] instanceof Connection)
				logLine.append("dbCon");
			else if (paramValues[i] instanceof List){
				logLine.append("list{").append(((List)paramValues[i]).size()).append("} - {");
				logLine.append("items{");
				for (int j = 0; j < ((List)paramValues[i]).size(); j++) {
					logLine.append(((List)paramValues[i]).get(j)).append(",");
					if(j >= 10 ){
						logLine.append("...");
						break;
					}
				}
				logLine.append("}");
			}
			else if (paramValues[i] instanceof Map)
				logLine.append("items{").append(((Map) paramValues[i]).size())
						.append("}");
			else
				logLine.append(paramValues[i]);
			if (i < paramValues.length - 1)
				logLine.append(", ");
		}
	}
}
