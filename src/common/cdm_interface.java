package common;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import http_framework.frame_work;
import net.minidev.json.JSONValue;
import common.cdm_param.clusters_p;
import common.cdm_param.cluster;
public class cdm_interface {
    String cdm_Host=commparam.cdm_Host;
    Map<String, String> httphead=new HashMap<String, String>();
    static  String projectid=commparam.projectid;
    public cdm_interface(){
        iam_interface iam=new iam_interface();
        String token=iam.get_user_token();
        httphead.put("Host",cdm_Host);
        httphead.put("X-Auth-Token",token);
    }
    public List<cluster> get_cdm_cluster_list() throws InterruptedException {
        List list=null;
        try {
            frame_work frame_work=new frame_work();
            httphead.put("X-Language","zh-cn");
            String  resp=frame_work.http_get("https://"+cdm_Host+"/cdm/v1.0/"+projectid+"/clusters",httphead);
            System.out.println(resp);
            clusters_p clusters_pm=new clusters_p();
            clusters_p  jsStr=JSONValue.parse(resp.split("\r\n")[2],clusters_pm.getClass());
            List<cluster> clusters = jsStr.getClusters();
           return clusters;
        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;


    }

    public String create_cdm_cluster(){
        frame_work frame_work=new frame_work();
        String body="{\"cluster\":{\"name\":\"cdm-ab821\",\"datastore\":{\"version\":\"1.0.0\",\"type\":\"cdm\"},\"vpcId\":\"b6197873-92a2-48f8-9fad-ef8b0ab8873c\",\"instances\":[{\"flavorRef\":\"5ddb1071-c5d7-40e0-a874-8a032e81a697\",\"nics\":[{\"net-id\":\"36cb0e82-a492-4ec6-8b98-1ab40ca83448\",\"securityGroupId\":\"6c14a4fd-7049-4412-83d6-26dcfa736886\"}],\"availability_zone\":\"cn-north-1\",\"type\":\"cdm\",\"publicIP\":{}}]}}";
        httphead.put("X-Language","zh-cn");
        try {
            String  resp=frame_work.http_post("https://"+cdm_Host+"/cdm/v1.0/"+projectid+"/clusters",httphead,body);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

return null;
    }





}
