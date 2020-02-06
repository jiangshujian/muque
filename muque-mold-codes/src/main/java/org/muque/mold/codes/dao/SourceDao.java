package org.muque.mold.codes.dao;

import java.util.List;

import org.muque.mold.codes.dto.SourceColumn;
import org.muque.mold.codes.dto.SourceTable;

public interface SourceDao {

	public abstract List<SourceTable> getTableList(String databaseName);

	public abstract List<SourceColumn> getColumnList(String databaseName,
			String tableName);

}