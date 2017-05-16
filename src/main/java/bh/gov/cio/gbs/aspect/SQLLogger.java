package bh.gov.cio.gbs.aspect;

import java.lang.reflect.Field;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class SQLLogger {

	public static final Logger logger = LoggerFactory
			.getLogger(SQLLogger.class);
	  private static final DateTimeFormatter  DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");;
	    private static final DateTimeFormatter  TIMESTAMP_FORMATTER  = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");


//	@Pointcut("execution(* org.springframework.jdbc.core.JdbcOperations.*(..))")
//	protected void JdbcOperations() {
//	}
//	
////	@Pointcut("execution(* org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations.*(..))")
////	protected void NamedParameterJdbcOperations() {
////	}
//	
//	@Pointcut("execution(* *.*(..))")
//	protected void allMethod() {
//	}

	public void logBefore(JoinPoint joinPoint) throws Throwable {
		Logger logger = getLogger(joinPoint);
		Object[] methodArgs = joinPoint.getArgs(), sqlArgs = null;
		StringBuilder logLine = new StringBuilder("[ SQL: \"");
		// get the SQL statement
		String statement = methodArgs[0].toString();

		// find the SQL arguments (parameters)
		for (int i = 1, n = methodArgs.length; i < n; i++) {
			Object arg = methodArgs[i];
			if (arg instanceof Object[]) {
				sqlArgs = (Object[]) arg;
				break;
			}
		}

		// fill in any SQL parameter place-holders (?'s)
		String completedStatement = (sqlArgs == null ? statement
				: fillParameters(statement, sqlArgs));

		// log it
		logLine.append(completedStatement).append("\" ] ");
		logger.info(logLine.toString());
	}

	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = getLogger(joinPoint);
		Date before = new Date();
		Object obj = joinPoint.proceed();
		Date after = new Date();
		StringBuilder logLine = new StringBuilder("[ SQL: \"")
				.append(joinPoint.getSignature().getName())
				.append(" ( ")
				.append(joinPoint.getArgs() != null
						&& joinPoint.getArgs().length > 0 ? joinPoint.getArgs()[0]
						: "Parmas()").append(" ) ");
		logLine.append("\" ] , [ execution-time = ");
		logLine.append(after.getTime() - before.getTime()).append(" ] ");
		logger.info(logLine.toString());
		return obj;
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

	private String fillParameters(String statement, Object[] sqlArgs) {
		 // initialize a StringBuilder with a guesstimated final length
        StringBuilder completedSqlBuilder = new StringBuilder(Math.round(statement.length() * 1.2f));
        int index, // will hold the index of the next ?
            prevIndex = 0; // will hold the index of the previous ? + 1
 
        // loop through each SQL argument
        for (Object arg : sqlArgs) {
            index = statement.indexOf("?", prevIndex);
            if (index == -1)
                break; // bail out if there's a mismatch in # of args vs. ?'s
 
            // append the chunk of SQL coming before this ?
            completedSqlBuilder.append(statement.substring(prevIndex, index));
            if (arg == null)
                completedSqlBuilder.append("NULL");
            else if (arg instanceof String) {
                // wrap the String in quotes and escape any quotes within
                completedSqlBuilder.append('\'')
                   .append(arg.toString().replace("'", "''"))
                    .append('\'');
            }
            else if (arg instanceof Date) {
                // convert it to a Joda DateTime
                DateTime dateTime = new DateTime((Date)arg);
                // test to see if it's a DATE or a TIMESTAMP
                if (dateTime.getHourOfDay() == LocalTime.MIDNIGHT.getHourOfDay() &&
                    dateTime.getMinuteOfHour() == LocalTime.MIDNIGHT.getMinuteOfHour() &&
                    dateTime.getSecondOfMinute() == LocalTime.MIDNIGHT.getSecondOfMinute()) {
                    completedSqlBuilder.append("DATE '")
                        .append(DATE_FORMATTER.print(dateTime))
                        .append('\'');
                }
                else {
                    completedSqlBuilder.append("TIMESTAMP '")
                        .append(TIMESTAMP_FORMATTER.print(dateTime))
                        .append('\'');
                }
            }
            else
                completedSqlBuilder.append(arg.toString());
 
            prevIndex = index + 1;
        }
 
        // add the rest of the SQL if any
        if (prevIndex != statement.length())
            completedSqlBuilder.append(statement.substring(prevIndex));
 
        return completedSqlBuilder.toString();
	}

}
