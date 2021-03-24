package piazza;


import java.sql.*;


/*
* Controller used for viewing statistics regarding posts viewed/created for each user
*/
public class InstructorViewStatsCtrl extends DBConn {

    public InstructorViewStatsCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }

    public void getUserStatistics(){
        try {

        //FINDS THE NUMBER OF CREATED AND VIEWED POST FOR EACH USER
        String query = "select t1.userName,t1.numViewed,t2.numCreated from (select user.userName, count(UserViewedPost.postID) as numViewed from user left join UserViewedPost on user.userName = UserViewedPost.userName group by user.userName) as t1 left join  (select user.userName, count(UserPost.postID) as numCreated from user left join UserPost on user.userName = UserPost.userName group by user.userName) as t2 on t1.userName = t2.userName order by numViewed desc";
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
