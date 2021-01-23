package com.example.embedded.tomcat;

import java.io.File;
import javax.servlet.ServletException;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * @author chensongyu
 */
public class WebServer {
  public static void main(String[] args) throws ServletException, LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    Context ctx = tomcat
        .addWebapp("", new File("src/main/webapp").getAbsolutePath());
    WebResourceRoot resources = new StandardRoot(ctx);
    resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
        new File("target/classes").getAbsolutePath(), "/"));
    ctx.setResources(resources);

    tomcat.start();
    tomcat.getServer().await();
  }
}
