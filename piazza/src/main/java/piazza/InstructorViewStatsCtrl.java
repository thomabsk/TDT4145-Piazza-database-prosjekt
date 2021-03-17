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
        String query = "select t1.userName,t1.numViewed,t2.numCreated from (select user.userName, count(userviewedpost.postID) as numViewed from user left join userviewedpost on user.userName = userviewedpost.userName group by user.userName) as t1 left join  (select user.userName, count(userpost.postID) as numCreated from user left join userpost on user.userName = userpost.userName group by user.userName) as t2 on t1.userName = t2.userName order by numViewed desc";
        PreparedStatement getPostID = conn.prepareStatement(query);
        getPostID.executeQuery();
        ResultSet rs = getPostID.getResultSet();
        String firstCol = "Username";
        String secondCol = "numViewedPosts";
        String thirdCol = "numCreatedPosts";
        System.out.println("\n\n------------------------------------------------------");
        System.out.format("|%-20s|%-15s|%-15s|%n",firstCol,secondCol,thirdCol);
        System.out.println("------------------------------------------------------");
        while(rs.next()) {
            String userName = rs.getString("userName");
            int numCreatedPosts = rs.getInt("numCreated");
            int numViewedPosts = rs.getInt("numViewed");
            //System.out.println(userName + ": " + numCreatedPosts+ " " + numViewedPosts);
            System.out.format("|%-20s|%-15d|%-15d|%n", userName, numViewedPosts,numCreatedPosts);
        }
        System.out.println("------------------------------------------------------");
        getPostID.close();
        }
        catch (Exception e) {
            System.out.println("db error during getting user statistics "+e);
            return;
        }
    }






}
