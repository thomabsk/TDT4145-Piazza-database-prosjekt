package piazza;

import java.util.*;

/**
 *
 * @author thomabsk
 */
public class Piazza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean loggedIntoAUser = false;
        UserLoginCtrl loginCtrl = new UserLoginCtrl();
        while(true){
            if(loginCtrl.isLoggedIn()){
                System.out.println("\n\n");
                System.out.println("You are logged in to " + loginCtrl.getUserName() +" as " + loginCtrl.getUserType() + "!");
            }
            else{
                System.out.println("You are not logged in.");
            }
            Scanner myInput = new Scanner(System.in);
            System.out.println("Welcome to Piazza! Please select one of the 5 user cases below!\n1. Log in to user\n2. Make a post in a folder\n3. Reply to a post by ID as an instructor\n4. Search for a post with a specific keyword\n5. View statistics as an instructor\n6. Log out\n\nYour choice (input number): ");
            int choice = myInput.nextInt();
            
            switch(choice){
                case 1: {
                    myInput.nextLine();
                    System.out.println("\nEnter username: ");
                    String userName = myInput.nextLine();
                    System.out.println("\nEnter password: ");
                    String password = myInput.nextLine();
                    
                    loginCtrl.loginUser(userName, password);
                    if(loginCtrl.isLoggedIn()){ 
                        loggedIntoAUser = true;
                    }
                    else{
                        System.out.println("Password and/or username is wrong!"); 
                    }
                    break;
                }
                case 2: {
                    if(loggedIntoAUser){
                        MakePostCtrl makePostCtrl = new MakePostCtrl(loginCtrl);
                        myInput.nextLine();
                        makePostCtrl.viewAvailableFolders();
                        System.out.println("\n Which courseID is the folder in? (courseID)");
                        String courseID = myInput.nextLine();
                        makePostCtrl.setCourseName(courseID);

                        System.out.println("\nWhich folder do you want to post in? (name)");
                        String folderName = myInput.nextLine();
                        makePostCtrl.setFolderName(folderName);
                        
                        System.out.println("\nWhat should be the tag? eq: Question");
                        String tag = myInput.nextLine();
                        makePostCtrl.setTag(tag);
                        
                        System.out.println("\nWhat should the text be?");
                        String postText = myInput.nextLine();
                        makePostCtrl.setText(postText);

                        makePostCtrl.makePost();
                    }
                    break;
                } 
                case 3: {
                    if(loggedIntoAUser && loginCtrl.getUserType().equals("instructor")){
                        InstructorReplyCtrl instrReply = new InstructorReplyCtrl(loginCtrl);
                        instrReply.viewAvailableThreads();
                        myInput.nextLine();
                        System.out.println("\nWhich thread do you want to reply to? (Write the postID)");
                        int postIdChoice = myInput.nextInt();
                        myInput.nextLine();
                        System.out.println("\nWhat should the reply be? (Write the text)");
                        String text  = myInput.nextLine();
                        instrReply.setPostReplyID(postIdChoice);
                        instrReply.setText(text);
                        instrReply.makeReply();
                    }
                    else{
                        System.out.println("You are not logged in as an instructor!\n\n");
                    }
                    break;
                }
                case 4: {
                    if(loggedIntoAUser){
                        myInput.nextLine();
                        System.out.println("\nWhat are you searching for: ");
                        String searchWord = myInput.nextLine();

                        SearchForPostCtrl searchForPostCtrl = new SearchForPostCtrl();
                        searchForPostCtrl.setPostSearch(searchWord);
                        System.out.println("\nThe following postIDs include the search: " + searchWord);
                        searchForPostCtrl.searchForPost();
                    }
                    else{
                        System.out.println("\nYou need to be logged into a user to perform the search!\n");
                    }
                    
                    break;
                }
                case 5: {
                    if(loggedIntoAUser){
                        if(loginCtrl.getUserType().equals("instructor")){
                            InstructorViewStatsCtrl stats = new InstructorViewStatsCtrl();
                            stats.getUserStatistics();
                        }
                        else{
                            System.out.println("You are not logged in as an instructor!\n\n");
                        }
                    }
                    else{
                        
                        System.out.println("You are not logged in as an instructor!\n\n");
                        
                    }
                    break;
                }
                case 6: {
                    loginCtrl = new UserLoginCtrl();
                    loggedIntoAUser = false;
                    break;
                }
                
            }
        }
    }
}