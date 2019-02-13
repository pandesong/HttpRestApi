package http.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import common.cdm_param.cluster;
import common.dws_interface;
import common.iam_interface;
import common.resourceparam.keyparis.allkeyparis;
import http_framework.frame_work;
import common.cdm_interface;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import common.commparam;
class MyThread extends Thread {
    private String name;
    public MyThread(String name) {
        this.name=name;
    }
    @Override
    public void run() {
        frame_work https = new frame_work();
        iam_interface iam=new iam_interface();
        Map<String, String> head=new HashMap<String, String>();
        allkeyparis allkeyparis=new allkeyparis();
        head.put("X-Auth-Token",iam.get_user_token());
        int size=1;
//            String tmp="";
//            while (size>0){
//                synchronized (this){
//                    tmp=list.get(0).toString();
//                    list.remove(0);
//                    size=list.size();
//                }
//                System.out.println(size);
//                allkeyparis obj=JSONValue.parse(tmp,allkeyparis.getClass());
//                name=obj.getKeypair().getName();
        try {
            String resp=https.http_delete("https://"+commparam.ecs_host+"/v2/"+commparam.projectid+"/os-keypairs/"+name,head);
            if(resp.split("\r\n")[0].equals("202")) System.out.println("deleted the "+name+" "+this.getId());
            else System.out.println("deleted the error "+this.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public class work {
   // public static JSONArray list=new JSONArray();
    static  Map<String, String> head=new HashMap<String, String>();


        public static void delete_ecs_key() throws InterruptedException {
            iam_interface iam = new iam_interface();
            frame_work frame = new frame_work();
            head.put("X-Auth-Token", iam.get_user_token());
            String resp = null;
            try {
                resp = frame.http_get("https://" + commparam.ecs_host + "/v2/" + commparam.projectid + "/os-keypairs", head);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsStr = (JSONObject) JSONValue.parse(resp.split("\r\n")[2]);
            JSONArray list = (JSONArray) jsStr.get("keypairs");
            allkeyparis allkeyparis = new allkeyparis();
            List<MyThread> mTh=new ArrayList<MyThread>();
            int thread=300;
            String name;
            for (int i = 0; i <thread; i++) {
                allkeyparis obj=JSONValue.parse(list.get(i).toString(),allkeyparis.getClass());
                name=obj.getKeypair().getName();
                mTh.add(new MyThread(name));
                mTh.get(i).start();
            }
            for(int i = 0; i < thread; i++) {
                list.remove(i);
            }
            System.out.println(list.size());
            while (list.size()!=0) {
                for(int i = 0; i < thread; i++)
                    if(!mTh.get(i).isAlive()){
                        System.out.println(list.size());
                        allkeyparis obj=JSONValue.parse(list.get(0).toString(),allkeyparis.getClass());
                        name=obj.getKeypair().getName();
                        mTh.set(i,new MyThread(name));
                        list.remove(0);
                        mTh.get(i).start();
                    }
            }

            System.out.println(mTh.size());



        }


    public static void main(String[] args) throws Exception {

/*        cdm_interface cdm = new cdm_interface();
        iam_interface iam = new iam_interface();
       // System.out.println(iam.get_user_token());
        List<cluster> cdmcluster = cdm.get_cdm_cluster_list();
        System.out.println(cdm.create_cdm_cluster());
        for (cluster clus : cdmcluster) {
            System.out.println(clus.getEndpoint() + " " + clus.getId() + " " + clus.getName() + " " + clus.getPublicEndpoint());
        }*/
        frame_work frame = new frame_work();
        String resp = null;
        resp = frame.http_get("https://support.huaweicloud.com/usermanual-dlf/dlf-usermanual.pdf", head);
System.out.println(resp);
        FileOutputStream   outputStream = new FileOutputStream("dlf-usermanual.pdf");
        outputStream.write(resp.getBytes());
        //建立文件对象
        File file=new File("3.txt");
        try
        {
            String content="abcdefg";

            FileOutputStream out=new FileOutputStream(file,false);
            out.write(content.getBytes());

        }

        catch (FileNotFoundException e)
        {

            System.out.println("文件不存在或者文件不可读或者文件是目录");
        }
        catch (IOException e)
        {
            System.out.println("读取过程存在异常");
        }


       file=new File("31.txt");
        FileOutputStream fos = new FileOutputStream(file,true);

        fos.write("111".getBytes());


    }




}
