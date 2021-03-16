package piazza;


import java.sql.*;
import java.util.*;


public class InstructorViewStatsCtrl extends DBConn {
    private UserLoginCtrl userLoginCtrl;

    public InstructorViewStatsCtrl (UserLoginCtrl userLoginCtrl) {
        connect();
        this.userLoginCtrl = userLoginCtrl;
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }

    // query -> select user.userName, count(UserPost.postID) as numCreatedPosts, count(UserViewedPost.postID) as numViewedPosts from user left join UserPost on user.userName = UserPost.userName inner join UserViewedPost on user.userName = UserViewedPost.userName group by user.userName order by numViewedPosts
    public void getUserStatistics(){
        try {
        //FIND THE ID OF THE POST
        String query = "select distinct user.userName, count(UserPost.postID) as numCreatedPosts, count(UserViewedPost.postID) as numViewedPosts from user left join UserPost on user.userName = UserPost.userName left join UserViewedPost on user.userName = UserViewedPost.userName group by user.userName order by numViewedPosts desc";
        PreparedStatement getPostID = conn.prepareStatement(query);
        getPostID.executeQuery();
        ResultSet rs = getPostID.getResultSet();
        String firstCol = "Username";
        String secondCol = "numViewedPosts";
        String thirdCol = "numCreatedPosts";
        System.out.println("\n\n-------------------------------------------------");
        System.out.format("|%-20s|%-15s|%-15s|%n",firstCol,secondCol,thirdCol);
        System.out.println("-------------------------------------------------");
        while(rs.next()) {
            String userName = rs.getString("userName");
            int numCreatedPosts = rs.getInt("numCreatedPosts");
            int numViewedPosts = rs.getInt("numViewedPosts");
            //System.out.println(userName + ": " + numCreatedPosts+ " " + numViewedPosts);
            System.out.format("|%-20s|%-15d|%-15d|%n", userName, numViewedPosts,numCreatedPosts);
        }
        System.out.println("-------------------------------------------------");
        getPostID.close();
        }
        catch (Exception e) {
            System.out.println("db error during getting user statistics "+e);
            return;
        }
    }






}
