package domain;

/**
 * @ClassName: Visitor
 * @Description: 参观者实体类
 * @author: GGBOY
 * @date 2019/11/21 16:26
 * @Version: 1.0
 **/
public class Visitor {
    /**
     * 当前用户的session id
     */
    private String sessionId;
    /**
     * 当前用户的ip地址
     */
    private String ip;
    /**
     * 当前用户第一次访问的时间
     */
    private String firstTime;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "sessionId='" + sessionId + '\'' +
                ", ip='" + ip + '\'' +
                ", firstTime='" + firstTime + '\'' +
                '}';
    }
}
