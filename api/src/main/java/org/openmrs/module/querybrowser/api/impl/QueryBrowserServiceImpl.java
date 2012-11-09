/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.querybrowser.api.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.querybrowser.api.QueryBrowserService;
import org.openmrs.module.querybrowser.api.QueryResults;

/**
 * It is a default implementation of {@link QueryBrowserService}.
 */
public class QueryBrowserServiceImpl extends BaseOpenmrsService implements QueryBrowserService {

	protected final Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public QueryResults query(String query) {

		final Connection conn = sessionFactory.getCurrentSession().connection();

		final QueryResults qResults = new QueryResults();

		query = query.trim();
		boolean dataManipulation = false;

		final String sqlLower = query.toLowerCase();
		if (sqlLower.startsWith("insert") || sqlLower.startsWith("update") || sqlLower.startsWith("delete")
		        || sqlLower.startsWith("alter") || sqlLower.startsWith("drop") || sqlLower.startsWith("create")
		        || sqlLower.startsWith("rename")) {
			dataManipulation = true;
		}

		PreparedStatement ps = null;
		final List<List<Object>> results = new Vector<List<Object>>();
		final List<String> headers = new ArrayList<String>();

		try {
			ps = conn.prepareStatement(query);

			if (dataManipulation == true) {
				final Integer i = ps.executeUpdate();
				final List<Object> row = new Vector<Object>();
				row.add(i);
				results.add(row);
			} else {
				final ResultSet resultSet = ps.executeQuery();

				final ResultSetMetaData rmd = resultSet.getMetaData();
				final int columnCount = rmd.getColumnCount();

				for (int i = 1; i < columnCount; i++) {
					headers.add(rmd.getColumnLabel(i));
				}

				while (resultSet.next()) {
					final List<Object> rowObjects = new Vector<Object>();
					for (int x = 1; x <= columnCount; x++) {
						rowObjects.add(resultSet.getObject(x));
					}
					results.add(rowObjects);
				}
			}
		}
		catch (final Exception e) {
			log.debug("Error while running sql: " + query, e);
			throw new DAOException("Error while running sql: " + query + " . Message: " + e.getMessage(), e);
		}
		finally {
			if (ps != null) {
				try {
					ps.close();
				}
				catch (final SQLException e) {
					log.error("Error generated while closing statement", e);
				}
			}
		}

		qResults.setHeaders(headers);
		qResults.setResults(results);
		return qResults;
	}
}