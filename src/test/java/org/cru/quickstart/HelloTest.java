package org.cru.quickstart;

import org.cru.cdi.HttpObjectsProducer;
import org.cru.cdi.RequestWebListener;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloTest extends Arquillian {
    @Deployment
    public static WebArchive createTestArchive() {
        MavenDependencyResolver resolver = DependencyResolvers
                .use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml");

        return ShrinkWrap.create(WebArchive.class)
                .addClasses(RequestWebListener.class, HttpObjectsProducer.class, InjectionTestServlet.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void simpleTest() {
        try {
            URL url = new URL("http://localhost:8080/arquillian-service/InjectionTest");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(1000);

            httpURLConnection.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }

            final String string = sb.toString();
            Assert.assertTrue(string.contains("request"));
            Assert.assertTrue(string.contains("response"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
