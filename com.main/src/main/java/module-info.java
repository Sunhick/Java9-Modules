module com.main.main {
    requires org.apache.logging.log4j;
    requires slf4j.api;
    requires java.sql;

    requires org.main.main;
    opens com.main.resources to org.main.main;
}