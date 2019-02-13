package common;
import http_framework.frame_work;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class iam_interface {

      String iam_host="iam.cn-north-1.myhuaweicloud.com";
      //String iam_host="127.0.0.1:8008";
      String iam_get_toke_uri="/v3/auth/tokens";
      Map<String, String> httphead=new HashMap<String, String>();
    static  String projectid=commparam.projectid;
    static  String username=commparam.username;
    static  String passwd=commparam.passwd;
      String iam="{\"auth\":{\"identity\":{\"methods\":[\"password\"],\"password\":{\"user\":{\"name\":\""+username+"\",\"password\":\""+passwd+"\",\"domain\":{\"name\":\""+username+"\"}}}},\"scope\":{\"project\":{\"id\":\""+projectid+"\"}}}}";

    public     String get_user_token()
    {
        httphead.put("Host",iam_host);
        String resp= null;
        try {
            frame_work frame=new frame_work();
            resp = frame.http_post("https://"+iam_host+iam_get_toke_uri,httphead,iam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsStr =(JSONObject) JSONValue.parse(resp.split("\r\n")[1]);
        return jsStr.get("X-Subject-Token").toString();
    }

    public  String getIam_get_toke_uri() {
        return iam_get_toke_uri;
    }

    public  void setHttphead(Map<String, String> httphead) {
        this.httphead = httphead;
    }

    public String getIam_host() {
        return iam_host;
    }

    public  Map<String, String> getHttphead() {
        return httphead;
    }

    public void setIam_host(String iam_host) {
        this.iam_host = iam_host;
    }

    public  void setIam_get_toke_uri(String iam_get_toke_uri) {
        this.iam_get_toke_uri = iam_get_toke_uri;
    }

    public  void setIam(String iam) {
        this.iam = iam;
    }
}
