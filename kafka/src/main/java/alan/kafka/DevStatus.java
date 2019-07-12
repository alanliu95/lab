package alan.kafka;

import org.codehaus.jackson.map.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DevStatus {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        ObjectMapper mapper= new ObjectMapper();
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        DevStatus devStatus=new DevStatus("123",df.format(new Date()),false);
        while(true){
            if(scanner.hasNext()){
                try {
                    boolean bool=scanner.nextBoolean();
                    devStatus.setOnline(bool);
                    devStatus.setTs(df.format(new Date()));
                    System.out.println(mapper.writeValueAsString(devStatus));

                }catch (Exception e){
                    String s=scanner.nextLine();
                    e.printStackTrace();
                }
            }
        }

    }
    private String devToken;
    private String ts;
    private Boolean online;

    public DevStatus(String devToken, String ts, Boolean online) {
        this.devToken = devToken;
        this.ts = ts;
        this.online = online;
    }

    public String getDevToken() {
        return devToken;
    }

    public void setDevToken(String devToken) {
        this.devToken = devToken;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
