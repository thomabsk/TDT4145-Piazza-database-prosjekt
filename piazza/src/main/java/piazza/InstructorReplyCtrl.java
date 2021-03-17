package piazza;

import java.sql.*;

public class InstructorReplyCtrl extends DBConn{
    private int postID;
    private int threadID;
    private UserLoginCtrl userLoginCtrl;
    private String text;

    public InstructorReplyCtrl(UserLoginCtrl userLoginCtrl){
        connect();
        this.userLoginCtrl = userLoginCtrl;
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }


    public void setPostReplyID (int postID){
        this.postID = postID;
    }
    public void setText(String text){
        this.text = text;
    }

    public void viewAvailableThreads(){
        try {
            //FIND THE ID OF THE POST
            String query = "select postID, text from thread natural join post";
            PreparedStatement getPosts = conn.prepareStatement(query);
            getPosts.executeQuery();
            ResultSet rs = getPosts.getResultSet();
            String firstCol = "PostID";
            String secondCol = "Text";
            System.out.println("\n\n------------------------------------------------------");
            System.out.format("|%-20s|%-15s%n",firstCol,secondCol);
            System.out.println("------------------------------------------------------");
            while(rs.next()) {
                int postID = rs.getInt("postID");
                String text = rs.getString("text");
                //System.out.println(userName + ": " + numCreatedPosts+ " " + numViewedPosts);
                System.out.format("|%-20d|%-15s%n", postID, text);
            }
            System.out.println("------------------------------------------------------");
            getPosts.close();
            }
            catch (Exception e) {
                System.out.println("db error during getting user statistics "+e);
                return;
            }
    }
    public void makeReply(){
        try{

        
        //FIND THREAD ID
        String query = "select threadID from post where postID=?";
        PreparedStatement findFolder = conn.prepareStatement(query);
        findFolder.setInt(1,postID);
        findFolder.executeQuery();
        ResultSet rs = findFolder.getResultSet();
        while (rs.next()) {
            this.threadID = rs.getInt("threadID");
        }
        findFolder.close();

        //INSERT NEW POST INTO THREAD
        query = "insert into post (text, nrGoodComment, threadID) values (?,?,?)";
        PreparedStatement makePost = conn.prepareStatement(query);
        makePost.setString(1, text);
        makePost.setInt(2,0);
        makePost.setInt(3,this.threadID);
        makePost.executeUpdate();
        rs = makePost.getResultSet();
        makePost.close();

        conn.commit();


        //ADDS THE CREATED POST TO THE USER
        query = "insert into UserPost values (?,?,current_timestamp)";
        PreparedStatement userPost= conn.prepareStatement(query);
        userPost.setString(1,userLoginCtrl.getUserName());
        userPost.setInt(2, this.postID);
        userPost.executeUpdate();
        userPost.close();

        conn.commit();

        //ADDS THE VIEWED POST TO THE USER
        query = "insert into UserViewedPost values (?,?,current_timestamp)";
        PreparedStatement userViewedPost= conn.prepareStatement(query);
        userViewedPost.setString(1,userLoginCtrl.getUserName());
        userViewedPost.setInt(2, this.postID);
        userViewedPost.executeUpdate();
        userViewedPost.close();

        conn.commit();



        //UPDATE THREAD WITH COLOR
        query = "update thread set color=? where threadID = ?";
        PreparedStatement updateThread = conn.prepareStatement(query);
        updateThread.setString(1, "instructor-reply");
        updateThread.setInt(2, this.threadID);
        updateThread.executeUpdate();
        updateThread.close();
        
        conn.commit();

        System.out.println("You made the comment!");

        } catch (Exception e) {
            System.out.println("db error during making of post= "+e);
            return;
        }
    }


    

}
