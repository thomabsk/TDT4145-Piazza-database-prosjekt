package piazza;

import java.sql.*;
import java.util.*;



public class MakePostCtrl extends DBConn {
    private String courseName;
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

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    public void viewAvailableCourses(){
        try {
            //Print out all of the available courses
            String query = "select courseID from course";
            PreparedStatement getPostID = conn.prepareStatement(query);
            getPostID.executeQuery();
            ResultSet rs = getPostID.getResultSet();
            String firstCol = "courseID";
            System.out.println("\n\n----------");
            System.out.format("|%-8s|%n",firstCol);
            System.out.println("----------");
            while(rs.next()) {
                String courseIDLocal = rs.getString("courseID");
                System.out.format("|%-8s|%n", courseIDLocal);
            }
            System.out.println("----------");
            getPostID.close();
            }
            catch (Exception e) {
                System.out.println("db error viewing courses "+e);
                return;
            }
    }
    //Prints out all the folders in the database
    public void viewAvailableFolders(){
        try {
            //Print out all of the folders in the given course
            String query = "select name from folder where courseID = ?";
            PreparedStatement getPostID = conn.prepareStatement(query);
            getPostID.setString(1, courseName);
            getPostID.executeQuery();
            ResultSet rs = getPostID.getResultSet();
            String firstCol = "Folder name";
            System.out.println("\n\n--------------");
            System.out.format("|%-12s|%n",firstCol);
            System.out.println("--------------");
            while(rs.next()) {
                String nameLocal = rs.getString("name");
                //System.out.println(userName + ": " + numCreatedPosts+ " " + numViewedPosts);
                System.out.format("|%-12s|%n", nameLocal);
            }
            System.out.println("--------------");
            getPostID.close();
            }
            catch (Exception e) {
                System.out.println("db error viewing folders "+e);
                return;
            }
    }

    //run makepost when all the parameters are set, to make the post with the given parameters.
    public void makePost(){
        try {
            //FIND FOLDER ID FROM FOLDER NAME
            String query = "select folderID from folder where name=? and courseID=?";
            PreparedStatement findFolder = conn.prepareStatement(query);
            findFolder.setString(1,folderName);
            findFolder.setString(2,courseName);
            findFolder.executeQuery();
            ResultSet rs = findFolder.getResultSet();
            while (rs.next()) {
                this.folderID = rs.getInt("folderID");
            }
            findFolder.close();

            //INSERT NEW THREAD
            query = "insert into thread (tag, folderID) values (?,?)";
            PreparedStatement makeThread = conn.prepareStatement(query);
            makeThread.setString(1,this.tag);
            makeThread.setInt(2,this.folderID);
            makeThread.executeUpdate();
            rs = makeThread.getResultSet();
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
            

            System.out.println("\nYou just a made the post:  " + courseName + " - " + folderName + " - " + tag + " - " + text);

        } catch (Exception e) {
            System.out.println("db error during making of post= "+e);
            System.out.println("\n\nAre you sure you added legal parameters? Try again!");
            return;
        }
    }
}
