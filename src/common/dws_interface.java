package common;

import http_framework.frame_work;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class dws_interface {
    String dws_Host=commparam.dws_Host;
    Map<String, String> httphead=new HashMap<String, String>();
    static  String projectid=commparam.projectid;
    public  dws_interface(){
        iam_interface iam=new iam_interface();
        String token=iam.get_user_token();
        httphead.put("X-Language","zh-cn");
        httphead.put("Host",dws_Host);
        httphead.put("Content-Type","application/json");
        httphead.put("X-Auth-Token",token);
    }
    public String create_dws_cluster(){
        frame_work frame_work=new frame_work();
        String resp="";
        String body="{\"cluster\":{\"instances\":[{\"flavorRef\":\"254e6f0e-4269-42f2-a8d0-0088ee911e6c\",\"locality\":\"anti-affinity\",\"volume\":{\"volume_type\":\"ULTRAHIGH\",\"tag\":\"data\",\"num\":2,\"size\":160,\"volumeExt\":[{\"size\":100,\"tag\":\"log\",\"volume_type\":\"COMMON\"}]},\"nics\":[{\"net-id\":\"19f02ceb-a616-46f2-84d6-986bc0e245e0\",\"securityGroupType\":\"auto_assign\",\"securityGroupRules\":[{\"direction\":\"ingress\",\"ethertype\":\"IPv4\",\"protocol\":\"TCP\",\"port_range_min\":\"8000\",\"port_range_max\":\"8000\",\"remote_ip_prefix\":\"0.0.0.0/0\"},{\"direction\":\"egress\",\"ethertype\":\"IPv4\",\"remote_ip_prefix\":\"0.0.0.0/0\"}]}],\"availability_zone\":\"cn-north-1b\",\"extended_properties\":{\"ssl\":true},\"type\":\"dws-cn\",\"dbPort\":\"8000\",\"publicIP\":{}},{\"flavorRef\":\"254e6f0e-4269-42f2-a8d0-0088ee911e6c\",\"locality\":\"anti-affinity\",\"volume\":{\"volume_type\":\"ULTRAHIGH\",\"tag\":\"data\",\"num\":2,\"size\":160,\"volumeExt\":[{\"size\":100,\"tag\":\"log\",\"volume_type\":\"COMMON\"}]},\"nics\":[{\"net-id\":\"19f02ceb-a616-46f2-84d6-986bc0e245e0\",\"securityGroupType\":\"auto_assign\",\"securityGroupRules\":[{\"direction\":\"ingress\",\"ethertype\":\"IPv4\",\"protocol\":\"TCP\",\"port_range_min\":\"8000\",\"port_range_max\":\"8000\",\"remote_ip_prefix\":\"0.0.0.0/0\"},{\"direction\":\"egress\",\"ethertype\":\"IPv4\",\"remote_ip_prefix\":\"0.0.0.0/0\"}]}],\"availability_zone\":\"cn-north-1b\",\"extended_properties\":{\"ssl\":true},\"type\":\"dws-cn\",\"dbPort\":\"8000\",\"publicIP\":{}},{\"flavorRef\":\"254e6f0e-4269-42f2-a8d0-0088ee911e6c\",\"locality\":\"anti-affinity\",\"volume\":{\"volume_type\":\"ULTRAHIGH\",\"tag\":\"data\",\"num\":2,\"size\":160,\"volumeExt\":[{\"size\":100,\"tag\":\"log\",\"volume_type\":\"COMMON\"}]},\"nics\":[{\"net-id\":\"19f02ceb-a616-46f2-84d6-986bc0e245e0\",\"securityGroupType\":\"auto_assign\",\"securityGroupRules\":[{\"direction\":\"ingress\",\"ethertype\":\"IPv4\",\"protocol\":\"TCP\",\"port_range_min\":\"8000\",\"port_range_max\":\"8000\",\"remote_ip_prefix\":\"0.0.0.0/0\"},{\"direction\":\"egress\",\"ethertype\":\"IPv4\",\"remote_ip_prefix\":\"0.0.0.0/0\"}]}],\"availability_zone\":\"cn-north-1b\",\"extended_properties\":{\"ssl\":true},\"type\":\"dws\",\"dbPort\":\"8000\",\"publicIP\":{}}],\"name\":\"a1111\",\"datastore\":{\"version\":\"1.1.2\",\"type\":\"dws\"},\"vpcId\":\"f74e5834-9f9f-4228-a63d-0470cad7998c\",\"dbUser\":\"dbadmin\",\"dbRtPd\":\"Fanqie@123.\",\"backupStrategy\":{\"period\":\"* * 1,2,3,4,5,6,7 *\",\"startTime\":\"00:00:00\",\"keepDays\":0},\"clusterMode\":\"sharding\",\"maintainWindow\":{\"day\":\"Thu\",\"startTime\":\"14:00\",\"endTime\":\"18:00\"}}}";

        try {
            resp=frame_work.http_post("https://"+dws_Host+"/v1.0/"+projectid+"/clusters",httphead,body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;
    }

    public String get_dws_cluster_list(){
        frame_work frame_work=new frame_work();
        String resp="";
       // String body="{\"cluster\":{\"instances\":[{\"flavorRef\":\"254e6f0e-4269-42f2-a8d0-0088ee911e6c\",\"locality\":\"anti-affinity\",\"volume\":{\"volume_type\":\"ULTRAHIGH\",\"tag\":\"data\",\"num\":2,\"size\":160,\"volumeExt\":[{\"size\":100,\"tag\":\"log\",\"volume_type\":\"COMMON\"}]},\"nics\":[{\"net-id\":\"19f02ceb-a616-46f2-84d6-986bc0e245e0\",\"securityGroupType\":\"auto_assign\",\"securityGroupRules\":[{\"direction\":\"ingress\",\"ethertype\":\"IPv4\",\"protocol\":\"TCP\",\"port_range_min\":\"8000\",\"port_range_max\":\"8000\",\"remote_ip_prefix\":\"0.0.0.0/0\"},{\"direction\":\"egress\",\"ethertype\":\"IPv4\",\"remote_ip_prefix\":\"0.0.0.0/0\"}]}],\"availability_zone\":\"cn-north-1b\",\"extended_properties\":{\"ssl\":true},\"type\":\"dws-cn\",\"dbPort\":\"8000\",\"publicIP\":{}},{\"flavorRef\":\"254e6f0e-4269-42f2-a8d0-0088ee911e6c\",\"locality\":\"anti-affinity\",\"volume\":{\"volume_type\":\"ULTRAHIGH\",\"tag\":\"data\",\"num\":2,\"size\":160,\"volumeExt\":[{\"size\":100,\"tag\":\"log\",\"volume_type\":\"COMMON\"}]},\"nics\":[{\"net-id\":\"19f02ceb-a616-46f2-84d6-986bc0e245e0\",\"securityGroupType\":\"auto_assign\",\"securityGroupRules\":[{\"direction\":\"ingress\",\"ethertype\":\"IPv4\",\"protocol\":\"TCP\",\"port_range_min\":\"8000\",\"port_range_max\":\"8000\",\"remote_ip_prefix\":\"0.0.0.0/0\"},{\"direction\":\"egress\",\"ethertype\":\"IPv4\",\"remote_ip_prefix\":\"0.0.0.0/0\"}]}],\"availability_zone\":\"cn-north-1b\",\"extended_properties\":{\"ssl\":true},\"type\":\"dws-cn\",\"dbPort\":\"8000\",\"publicIP\":{}},{\"flavorRef\":\"254e6f0e-4269-42f2-a8d0-0088ee911e6c\",\"locality\":\"anti-affinity\",\"volume\":{\"volume_type\":\"ULTRAHIGH\",\"tag\":\"data\",\"num\":2,\"size\":160,\"volumeExt\":[{\"size\":100,\"tag\":\"log\",\"volume_type\":\"COMMON\"}]},\"nics\":[{\"net-id\":\"19f02ceb-a616-46f2-84d6-986bc0e245e0\",\"securityGroupType\":\"auto_assign\",\"securityGroupRules\":[{\"direction\":\"ingress\",\"ethertype\":\"IPv4\",\"protocol\":\"TCP\",\"port_range_min\":\"8000\",\"port_range_max\":\"8000\",\"remote_ip_prefix\":\"0.0.0.0/0\"},{\"direction\":\"egress\",\"ethertype\":\"IPv4\",\"remote_ip_prefix\":\"0.0.0.0/0\"}]}],\"availability_zone\":\"cn-north-1b\",\"extended_properties\":{\"ssl\":true},\"type\":\"dws\",\"dbPort\":\"8000\",\"publicIP\":{}}],\"name\":\"a1111\",\"datastore\":{\"version\":\"1.1.2\",\"type\":\"dws\"},\"vpcId\":\"f74e5834-9f9f-4228-a63d-0470cad7998c\",\"dbUser\":\"dbadmin\",\"dbRtPd\":\"Fanqie@123.\",\"backupStrategy\":{\"period\":\"* * 1,2,3,4,5,6,7 *\",\"startTime\":\"00:00:00\",\"keepDays\":0},\"clusterMode\":\"sharding\",\"maintainWindow\":{\"day\":\"Thu\",\"startTime\":\"14:00\",\"endTime\":\"18:00\"}}}";
        httphead.put("X-Language","zh-cn");
        httphead.put("Host",dws_Host);
        try {
            resp=frame_work.http_get("https://"+dws_Host+"/v1.0/"+projectid+"/clusters",httphead);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resp;
    }

}
