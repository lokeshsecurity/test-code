package bh.gov.cio.gbs.model;

import java.sql.ResultSet;

public interface FillFromResultSet {
	void fillFromResultSet(ResultSet resultSet) throws Throwable;
}
