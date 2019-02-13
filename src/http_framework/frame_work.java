package http_framework;
import common.resourceparam.disresource.param;
import net.minidev.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class frame_work {


    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        };
        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
  // static  String projectid="6f2bf33af5104f45ab85de31d7841f5a";//
    //static  String username="dps_test";
    //static  String passwd="Dps@1234"  ;//3344520ab.

    public class param_test extends param{

String test;

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }

    public  String http_get(String url,Map<String, String> httphead) throws IOException, InterruptedException {
        SSLContext sslcontext = null;
        String body="";
        try {
            sslcontext = createIgnoreVerifySSL();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        String uri=url.substring(url.indexOf("//"),url.length());
        uri.substring(0,uri.indexOf("//"));
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
        HttpGet httpget=new HttpGet(url);
        Iterator iter = httphead.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            httpget.setHeader(key.toString(), val.toString());
        };
        CloseableHttpResponse response = client.execute(httpget);
        Header[] rehead=response.getAllHeaders();
        for(int i=0;i<rehead.length;i++) {
            jsonObject.put(rehead[i].getName(),rehead[i].getValue());
        }
        HttpEntity entity = response.getEntity();
        byte[] a = new byte[1024];
       if (entity != null) {
        //   body = EntityUtils.toString(entity, "utf-8");

           a=EntityUtils.toByteArray(entity);
        }
        //File file=new File("dlf-usermanual.pdf");
       InputStream is=entity.getContent();
        FileOutputStream fos = new FileOutputStream("dlf-usermanual.pdf");
       // byte[] b = new byte[1024];
        byte[] c=new byte[is.available()];
        //is.read(c);
      //  fos.write(c);
        System.out.println(a.length);

        fos.write(a,0,a.length);
        fos
        fos.flush();

        Thread.sleep(10000);
        fos.close();
     //   fos.flush();
       // is.close();
        //fos.close();
      //  response.close();

        //System.out.println(body);
        //System.out.println(Arrays.toString(response.getAllHeaders()));
        return body;
    }

    public  String http_delete(String url,Map<String, String> httphead) throws IOException {
        SSLContext sslcontext = null;
        String body="";
        try {
            sslcontext = createIgnoreVerifySSL();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        String uri=url.substring(url.indexOf("//"),url.length());
        uri.substring(0,uri.indexOf("//"));
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
        HttpDelete httpget=new HttpDelete(url);
        Iterator iter = httphead.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            httpget.setHeader(key.toString(), val.toString());
        };
        CloseableHttpResponse response = client.execute(httpget);
        Header[] rehead=response.getAllHeaders();
        for(int i=0;i<rehead.length;i++) {
            jsonObject.put(rehead[i].getName(),rehead[i].getValue());
        }
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            body = EntityUtils.toString(entity, "utf-8");
        }
        EntityUtils.consume(entity);
        response.close();
        return response.getStatusLine().getStatusCode()+"\r\n"+jsonObject.toString()+"\r\n"+body;
    }



    public  String http_post(String url,Map<String, String> httphead,String body) throws IOException {
        SSLContext sslcontext = null;
        try {
            sslcontext = createIgnoreVerifySSL();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
        JSONObject jsonObject=new JSONObject();
        String uri=url.substring(url.indexOf("//"),url.length());
        uri.substring(0,uri.indexOf("//"));
        HttpPost httppost=new HttpPost(url);
        Iterator iter = httphead.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            httppost.setHeader(key.toString(), val.toString());
        }
        StringEntity repuestentity = new StringEntity(body, Charset.forName("UTF-8"));
        httppost.setEntity(repuestentity);
        CloseableHttpResponse response = client.execute(httppost);
        Header[] rehead=response.getAllHeaders();
        for(int i=0;i<rehead.length;i++) {
            jsonObject.put(rehead[i].getName(),rehead[i].getValue());
        }
        HttpEntity responseentity = response.getEntity();
        if (responseentity != null) {
            body = EntityUtils.toString(responseentity, "utf-8");
        }
        EntityUtils.consume(responseentity);
        response.close();
        return response.getStatusLine().getStatusCode()+"\r\n"+jsonObject.toString()+"\r\n"+body;
    }





 public  void  login_dps_signl_user(String url,String user_name,String passwd) throws KeyManagementException, NoSuchAlgorithmException, IOException, InterruptedException {
     String tmp="userpasswordcredentials.domain=&userpasswordcredentials.domainType=name&userpasswordcredentials.username="+user_name+"&userpasswordcredentials.countryCode=0086&userpasswordcredentials.verifycode=&userpasswordcredentials.password="+passwd+"&userpasswordcredentials.companyLogin=false&userpasswordcredentials.userInfoType=name&__checkbox_warnCheck=true&isAjax=true&Submit=Login";
     url="https://127.0.0.1:8008/dps/";
     String body = "";
     SSLContext sslcontext = createIgnoreVerifySSL();
     Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
             .register("http", PlainConnectionSocketFactory.INSTANCE)
             .register("https", new SSLConnectionSocketFactory(sslcontext))
             .build();
     PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
     HttpClients.custom().setConnectionManager(connManager);
     CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
     String url_tmp=http_get("https://127.0.0.1:8008/dps/",null);
     Pattern pt = Pattern.compile("https://\\w+.\\w+.\\w+/[a-zA-Z/?]+=");
     Matcher match = pt.matcher(url_tmp);
     if(match.find()) {
         url=match.group()+url;
     }
     else return;
     String url_tmp1=http_get(url,null);
     HttpPost httpPost = new HttpPost(url);
     StringEntity se = new StringEntity(tmp);
     httpPost.setEntity(se);
     httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
     httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
     CloseableHttpResponse response = client.execute(httpPost);
     HttpEntity entity = response.getEntity();
     if (entity != null) {
         body = EntityUtils.toString(entity, "utf-8");
     }
     EntityUtils.consume(entity);
     response.close();
     System.out.println(body);

 }


 public static void  test() throws NoSuchAlgorithmException, KeyManagementException, IOException {

   //  login_dps_signl_user("","dps_test","Huawei%2540123");
 }



}
