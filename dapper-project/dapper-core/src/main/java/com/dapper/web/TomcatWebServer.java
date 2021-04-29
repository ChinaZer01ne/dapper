package com.dapper.web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author peach
 * @since 2021/4/28 13:41
 */
public class TomcatWebServer implements WebServer {


    /**
     * tomcat
     */
    private Tomcat tomcat;

    private File baseDirectory;

    /**
     * 默认端口
     */
    private int port = 8080;

    /**
     * The class name of default protocol used.
     */
    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

    private final String protocol = DEFAULT_PROTOCOL;

    /**
     * 启动标识
     */
    private volatile boolean started;

    public TomcatWebServer() {
        tomcat = new Tomcat();
        File baseDir = (this.baseDirectory != null) ? this.baseDirectory : createTempDir("tomcat");
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        // 创建连接器
        Connector connector = new Connector(this.protocol);
        connector.setThrowOnFailure(true);
        tomcat.getService().addConnector(connector);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        //for (Connector additionalConnector : this.additionalTomcatConnectors) {
        //    tomcat.getService().addConnector(additionalConnector);
        //}
    }

    @Override
    public void start() {
        if (started) {
            return;
        }
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        if (started) {
            try {
                tomcat.destroy();
            } catch (LifecycleException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Return the absolute temp dir for given web server.
     * @param prefix server name
     * @return the temp dir for given server.
     */
    protected final File createTempDir(String prefix) {
        try {
            File tempDir = Files.createTempDirectory(prefix + "." + getPort() + ".").toFile();
            tempDir.deleteOnExit();
            return tempDir;
        }
        catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex);
        }
    }

    @Override
    public int getPort() {
        return port;
    }
}