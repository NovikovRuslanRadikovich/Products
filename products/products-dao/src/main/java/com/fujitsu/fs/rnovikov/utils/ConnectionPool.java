package com.fujitsu.fs.rnovikov.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource hikariDataSource;

    static {


        hikariConfig.setDriverClassName(PropertiesJson.getDriver());
        hikariConfig.setJdbcUrl(PropertiesJson.getConnection_URL());
        hikariConfig.setUsername(PropertiesJson.getUsername());
        hikariConfig.setPassword(PropertiesJson.getPassword());

        hikariConfig.setMaximumPoolSize(10);

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private ConnectionPool() {

    }

    public static Connection getConnection() throws SQLException {

        return hikariDataSource.getConnection();
    }

}
