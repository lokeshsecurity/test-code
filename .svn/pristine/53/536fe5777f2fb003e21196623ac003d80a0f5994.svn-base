package bh.gov.cio.gbs.aspect;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class ContollerLogger {
	public static final Logger logger = LoggerFactory
			.getLogger(ContollerLogger.class);

	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void controller() {

	}

	@Pointcut("execution(* *(..))")
	public void methodPointcut() {
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RequestMapping *)")
	public void requestMapping() {
	}

	@SuppressWarnings("unchecked")
	@Before("controller() && methodPointcut() && requestMapping()")
	public void logBefore(JoinPoint joinPoint) {

		Logger logger = getLogger(joinPoint);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		StringBuilder logLine = new StringBuilder(joinPoint
				.getSignature().getName()).append("(");
		Map<String, Object> params = request.getParameterMap();
		if (params.size() != 0){
			for (Entry<String, Object> entry : params.entrySet()) {
				logLine.append("param-name : [ ").append(entry.getKey() + "=");
				Object[] allValue = (Object[]) entry.getValue();
				logLine.append(Arrays.toString(allValue)).append(" ] ");
			}
		}
		logLine.append(") :  started");
		logger.info(logLine.toString());
	}

	@SuppressWarnings("rawtypes")
	@Around("controller() && methodPointcut() && requestMapping()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = getLogger(joinPoint);
		Date before = new Date();
		Object r = null;
		
		try {
			String methodName = joinPoint.getSignature().getName();
			StringBuilder logLine = new StringBuilder(methodName).append(" () : ");
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
		} catch (IllegalArgumentException e) {
			logger.info("Illegal argument "
					+ Arrays.toString(joinPoint.getArgs()) + " in "
					+ joinPoint.getSignature().getName() + "()");
			throw e;
		}
		return r;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Before("controller() && methodPointcut() && requestMapping()")
	public void logAfter(JoinPoint joinPoint) {
		Logger logger = getLogger(joinPoint);
		Object[] paramValues = joinPoint.getArgs();
		Class[] paramNames = ((CodeSignature) joinPoint
				.getSignature()).getParameterTypes();
		StringBuilder logLine = new StringBuilder(joinPoint
				.getSignature().getName()).append("(");
		if (paramNames.length != 0)
			logParamValues(logLine, paramNames, paramValues);
		logLine.append(") :  end");
		logger.info(logLine.toString());
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
