package utils;

import domain.Visitor;

import java.util.ArrayList;

/**
 * @ClassName: SessionUtil
 * @Description: Session相关的工具类
 * @author: GGBOY
 * @date 2019/11/21 16:34
 * @Version: 1.0
 **/
public class SessionUtil {
    /**
     * 根据sessionId判断当前用户是否存在在集合中  如果存在 返回当前用户  否则返回null
     *
     * @param visitors  在线的观察者集合
     * @param sessionId session的id
     * @return domain.User
     * @author GGBOY
     * @date 2019/11/21
     */
    public static Visitor getUserBySessionId(ArrayList<Visitor> visitors, String sessionId) {
        for (Visitor visitor : visitors) {
            if (sessionId.equals(visitor.getSessionId())) {
                return visitor;
            }
        }
        return null;
    }
}
