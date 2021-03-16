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

        System.out.println(this.threadID);
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


        //CONNECT THE USER TO THE POST
        query = "insert into UserPost values (?,?,current_timestamp)";
        PreparedStatement userPost= conn.prepareStatement(query);
        userPost.setString(1,userLoginCtrl.getUserName());
        userPost.setInt(2, this.postID);
        userPost.executeUpdate();
        userPost.close();

        conn.commit();


        //UPDATE THREAD WITH COLOR
        query = "update thread set color=? where threadID = ?";
        PreparedStatement updateThread = conn.prepareStatement(query);
        updateThread.setString(1, "instructor-reply");
        updateThread.setInt(2, this.threadID);
        updateThread.executeUpdate();
        updateThread.close();
        
        conn.commit();

        

        } catch (Exception e) {
            System.out.println("db error during making of post= "+e);
            return;
        }
    }


    

}
