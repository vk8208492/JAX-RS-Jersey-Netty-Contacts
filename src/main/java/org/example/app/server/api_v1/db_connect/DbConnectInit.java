package org.example.app.server.api_v1.db_connect;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.server.api_v1.entity.User;
import org.example.app.server.api_v1.utils.logging.LoggingErrorsMsg;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

public class DbConnectInit implements IConnection {
    private final Dotenv dotenv;
    private SessionFactory sessionFactory;
    private final String dbUrl;
    private boolean isConnected;
    @Getter
    private final List<String> connectErrors;
    private static final Logger DB_CONNECT_LOGGER =
            LogManager.getLogger(DbConnectInit.class);

    public DbConnectInit() {
        this.dotenv = Dotenv.configure().directory("src/main/resources").load();
        this.dbUrl = String.format("%s%s:%s/%s", this.dotenv.get("HIBERNATE_JDBC_URL_PREFIX"), this.dotenv.get("MYSQL_HOST"), this.dotenv.get("MYSQL_HOST_PORT"), this.dotenv.get("MYSQL_DATABASE"));
        this.isConnected = false;
        this.connectErrors = new ArrayList<>();
        getSessionFactory();
    }

    public Configuration initEnvironment() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", this.dotenv.get("HIBERNATE_JDBC_DRIVER"));
        configuration.setProperty("hibernate.connection.url", this.dbUrl);
        configuration.setProperty("hibernate.connection.username", this.dotenv.get("MYSQL_USER"));
        configuration.setProperty("hibernate.connection.password", this.dotenv.get("MYSQL_PASSWORD"));
        configuration.setProperty("hibernate.dialect", this.dotenv.get("HIBERNATE_DIALECT"));
        configuration.setProperty("hibernate.show_sql", this.dotenv.get("HIBERNATE_SHOW_SQL"));
        configuration.setProperty("hibernate.format_sql", this.dotenv.get("HIBERNATE_FORMAT_SQL"));
        configuration.setProperty("hibernate.hbm2ddl.auto", this.dotenv.get("HIBERNATE_HBM2DDL_AUTO"));
        configuration.setProperty("hibernate.current_session_context_class", this.dotenv.get("HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS"));
        configuration.setProperty("hibernate.connection.pool_size", this.dotenv.get("HIBERNATE_CONNECTION_POOL_SIZE"));
        configuration.setProperty("hibernate.generate_statistics", this.dotenv.get("HIBERNATE_GENERATE_STATISTICS"));
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }


    public boolean isConnected() {
        return this.isConnected;
    }

    private void setSessionFactory() {
        Configuration configuration = initEnvironment();
        ServiceRegistry serviceRegistry = null;
        try {
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(User.class);
            this.sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
            this.isConnected = true;
        } catch (HibernateException e) {
            DB_CONNECT_LOGGER.error(e.getMessage());
            this.isConnected = false;
            this.connectErrors.add(LoggingErrorsMsg.DB_CONNECTION_ERROR.getMsg());
            if (serviceRegistry != null) {
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
            }
        }
    }

    public SessionFactory getSessionFactory() {
        if (this.sessionFactory == null) {
            setSessionFactory();
        }
        return this.sessionFactory;
    }
}


