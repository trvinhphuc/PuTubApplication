package webservice.server;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class DataSource {

    private static DataSource     datasource;
    private BasicDataSource ds;

    public DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //ds.setDriverClassName("net.sourceforce.jtds.jdbc.Driver");
        ds.setUsername("sa");
        ds.setPassword("12345");
        ds.setUrl("jdbc:sqlserver://localhost\\mssqlserver2:1433; databaseName=PuTub_DB;");
        //System.out.println();
     // the settings below are optional -- dbcp can work with defaults
        ds.setMinEvictableIdleTimeMillis(9000);
        ds.setTestOnBorrow(true);
        ds.setTimeBetweenEvictionRunsMillis(9000);
        ds.setMinIdle(1);
        ds.setValidationQuery("select 1");
    }

    public DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
        	//System.out.println("DB empty");
            datasource = new DataSource();
          
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
    	//System.out.println(this.ds.getConnection());
    	return this.ds.getConnection();
    }

}
