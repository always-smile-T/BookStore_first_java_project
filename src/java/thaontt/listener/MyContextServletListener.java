/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaontt.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Thao
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying ..."); //To change body of generated methods, choose Tools | Templates.
        ServletContext context = sce.getServletContext();
        String siteMapLocation = context.getInitParameter("SITE_MAP_LOCALTION");
        
        InputStream is = null;
        Properties properties = null;
        try {
          is = context.getResourceAsStream(siteMapLocation);
           properties = new Properties();
           properties.load(is);
            // luu vo attribute
           context.setAttribute("SITEMAP", properties);
        }catch(IOException ex){
            context.log("MyContextServletListener _ IO " + ex.getMessage());
        }
        finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException ex) {
                    //viet log thong qua cotext
                    context.log("MyContextServletListener _ IO " + ex.getMessage());
                }
            }            
        }        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destroying ..."); //To change body of generated methods, choose Tools | Templates.
    }
}
