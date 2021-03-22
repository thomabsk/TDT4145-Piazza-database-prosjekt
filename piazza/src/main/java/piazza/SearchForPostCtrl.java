package piazza;

/**
 *
 * @author lotteei
 *
 */

import java.sql.*;



public class SearchForPostCtrl extends DBConn {
    private String postSearch;
    private int postID;
    private UserLoginCtrl userLoginCtrl;

    //CHECKS IF YOU ARE LOGGED IN
    public SearchForPostCtrl () {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }

    //SETTING SEARCHWORD
    public void setPostSearch(String postSearch1){
        this.postSearch = postSearch1;
    }

    
    //SEARCH FOR POSTS THAT CONTAINS SEARCHWORD
    public void searchForPost(){
        try {
            //FIND POSTID FROM POST WITH SEARCHWORD
            String query = "select postID from post where post.text like ? ";
            PreparedStatement findPost = conn.prepareStatement(query);
            findPost.setString(1,"%" + postSearch + "%");
            findPost.executeQuery();

            //PRINTING TABLE WITH POSTIDs THAT CONATINS SEARCHWORD
            ResultSet rs = findPost.getResultSet();
            String firstCol = "PostID";
            System.out.println("\n\n----------");
            System.out.format("|%-8s|%n", firstCol);
            System.out.println("----------");
            //FILLING TABLE
            while (rs.next()) {
                int postID = rs.getInt("postID");
                System.out.format("|%-8s|%n", postID);
            }
            System.out.println("----------");
            findPost.close();
        } 
        //CATCHING ERROR
        catch (Exception e) {
        System.out.println("db error during search for post= "+e);
        return;
        }
    }
}
