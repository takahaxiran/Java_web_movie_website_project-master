package dao;

import domain.Collection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: CollectionDao.java
 * @Description: TODO(处理与collection表相关的操作)
 * @author: zhuhaipeng
 * @version: V1.0
 * @Date: 2019年10月27日 下午4:11:06
 */
public class CollectionDao {

    /**
     * @param userName 用户名
     * @Description: 根据用户名查找记录
     */
    public List<Collection> findAllRecordByUserName(String userName) throws SQLException {
        String sql = "select * from collection where userName = ? ORDER BY addTime desc";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<>(Collection.class), userName);
    }

    /**
     * 向收藏表中添加一条用户喜欢电影的记录
     *
     * @param userName  用户名
     * @param movieName 电影名
     * @author GGBOY
     * @date 2019/11/16
     */
    public void addUserCollectionMovie(String userName, String movieName) throws SQLException {
        String sql = "insert into collection(userName, movieName) values(?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, userName, movieName);
    }


    /**
     * 删除用户对某个电影的收藏记录
     *
     * @param userName  用户名
     * @param movieName 电影名
     * @return void
     * @author GGBOY
     * @date 2019/11/16
     */
    public void cancelUserCollectionMovie(String userName, String movieName) throws SQLException {
        String sql = "DELETE FROM collection WHERE userName=? and movieName=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, userName, movieName);
    }

}
