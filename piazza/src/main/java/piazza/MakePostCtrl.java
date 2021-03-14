package piazza;

import java.sql.*;



public class MakePostCtrl extends DBConn {
    private String folderName;
    private int folderID;
    private String tag;
    private UserLoginCtrl userLoginCtrl;
    private String text;
    private int threadID;
    private int postID;



    public MakePostCtrl (UserLoginCtrl userLoginCtrl) {
        connect();
        this.userLoginCtrl = userLoginCtrl;
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }

    public void setFolderName(String folderName){
        this.folderName = folderName;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public void setText(String text){
        this.text = text;
    }
    public void makePost(){
        try {
            //FIND FOLDER ID FROM FOLDER NAME
            String query = "select folderID from folder where name=?";
            PreparedStatement findFolder = conn.prepareStatement(query);
            findFolder.setString(1,folderName);
            findFolder.executeQuery();
            ResultSet rs = findFolder.getResultSet();
            while (rs.next()) {
                this.folderID = rs.getInt("folderID");
            }
            System.out.println(this.folderID); 
            findFolder.close();

            //INSERT NEW THREAD
            query = "insert into thread (tag, folderID) values (?,?)";
            PreparedStatement makeThread = conn.prepareStatement(query);
            makeThread.setString(1,this.tag);
            makeThread.setInt(2,this.folderID);
            makeThread.executeUpdate();
            rs = makeThread.getResultSet();
            System.out.println(tag); 
            makeThread.close();

            conn.commit();

            //FIND THE ID OF THE THREAD
            query = "SELECT LAST_INSERT_ID()";
            PreparedStatement getThreadID = conn.prepareStatement(query);
            getThreadID.executeQuery();
            rs = getThreadID.getResultSet();
            
            while(rs.next()) {
                this.threadID = rs.getInt(1);
            }

            System.out.println(this.threadID); 
            getThreadID.close();

            //MAKE A NEW POST
            query = "insert into post (text, nrGoodComment, threadID) values (?,0,?)";
            PreparedStatement createPost = conn.prepareStatement(query);
            createPost.setString(1,this.text);
            createPost.setInt(2, this.threadID);
            createPost.executeUpdate();
            createPost.close();

            conn.commit();
            //FIND THE ID OF THE POST
            query = "SELECT LAST_INSERT_ID()";
            PreparedStatement getPostID = conn.prepareStatement(query);
            getPostID.executeQuery();
            rs = getPostID.getResultSet();
            
            while(rs.next()) {
                this.postID = rs.getInt(1);
            }
            getPostID.close();

            //UPDATE THREAD WITH POST ID
            query = "update thread set postID = ?, color=? where threadID = ?";
            PreparedStatement updateThread = conn.prepareStatement(query);
            updateThread.setInt(1, this.postID);
            updateThread.setString(2, "no-reply");
            updateThread.setInt(3, this.threadID);
            updateThread.executeUpdate();

            updateThread.close();
            conn.commit();


            //CONNECT THE USER TO THE POST
            query = "insert into UserPost values (?,?,current_timestamp)";
            PreparedStatement userPost= conn.prepareStatement(query);
            userPost.setString(1,userLoginCtrl.getUserName());
            userPost.setInt(2, this.postID);
            userPost.executeUpdate();
            userPost.close();

            conn.commit();
            

        } catch (Exception e) {
            System.out.println("db error during making of post= "+e);
            return;
        }
    }
}
