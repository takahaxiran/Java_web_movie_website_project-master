package dao;

import domain.Comment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class CommentDao {

    /**
     * @param userId
     * @param movieId
     * @return
     * @Description: TODO(根据用户Id和电影id查找评论)
     */
    public String findCommentByUserIdAndMovieId(int userId, int movieId) {
        return null;

    }

    /**
     * @param movieName 电影名
     * @return 返回评论集合
     * @Description: 根据电影名，查找该电影的所有评论
     */
    public List<Comment> findCommentsByMovieName(String movieName) throws SQLException {
        String sql = "select * from comments where movieName = ?  ORDER BY addTime DESC";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<>(Comment.class), movieName);
    }

    /**
     * 添加一条评论
     *
     * @param comment 评论对象
     * @author GGBOY
     * @date 2019/12/3
     */
    public void addComment(Comment comment) throws SQLException {
        String sql = "insert into comments(userName,movieName,description) values(?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, comment.getUserName(), comment.getMovieName(), comment.getDescription());
    }
}
