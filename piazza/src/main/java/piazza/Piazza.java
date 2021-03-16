package piazza;

import javax.xml.crypto.Data;
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
            Scanner myInput = new Scanner(System.in);
            System.out.println("Welcome to Piazza!, please select one of the 5 user cases below!\n1. Log in to user\n2. Make a post in a folder\n3. Reply to as post by ID\n4. Search for a post with a specific keyword\n5. View statistics as an instructor\n\nYour choice (input number): ");
            int choice = myInput.nextInt();
            
            switch(choice){
                case 1: {
                    myInput.nextLine();
                    System.out.println("\nEnter username: ");
                    String userName = myInput.nextLine();
                    System.out.println("\nEnter password: ");
                    String password = myInput.nextLine();
                    
                    boolean h = loginCtrl.loginUser(userName, password);
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

                        makePostCtrl.setFolderName("Exam");
                        makePostCtrl.setTag("Question");
                        makePostCtrl.setText("hvordan");

                        makePostCtrl.makePost();
                    }
                    break;
                } 
                case 3: {
                    InstructorReplyCtrl instrReply = new InstructorReplyCtrl(loginCtrl);
                    instrReply.setPostReplyID(1);
                    instrReply.setText("hehe noob");

                    instrReply.makeReply();
                    break;
                }
                case 5: {
                    if(loggedIntoAUser){
                        if(loginCtrl.getUserType().equals("instructor")){
                            InstructorViewStatsCtrl stats = new InstructorViewStatsCtrl(loginCtrl);
                            stats.getUserStatistics();
                        }
                        else{
                            System.out.println("You are not logged in as an instructor!\n\n");
                        }
                    }
                    else{
                        
                        System.out.println("You are not logged in as an instructor!\n\n");
                        
                    }
                }
            }
        }
        

        // MakePostCtrl makePostCtrl = new MakePostCtrl(loginCtrl);

        // makePostCtrl.setFolderName("Exam");
        // makePostCtrl.setTag("Question");
        // makePostCtrl.setText("hvordan");

        // makePostCtrl.makePost();


        // InstructorReplyCtrl instrReply = new InstructorReplyCtrl(loginCtrl);
        // instrReply.setPostReplyID(1);
        // instrReply.setText("Dette tror jeg blir feil ja");

        // instrReply.makeReply();

        // InstructorViewStatsCtrl stats = new InstructorViewStatsCtrl(loginCtrl);

        // stats.getUserStatistics();


        // LagAvtaleCtrl lagAvtaleCtrl = new LagAvtaleCtrl ();
        // lagAvtaleCtrl.lagAvtale(100, 120);
        // lagAvtaleCtrl.velgBruker(1);
        // lagAvtaleCtrl.velgBruker(2);
        // lagAvtaleCtrl.velgTidspunkt(110, 1);
        // lagAvtaleCtrl.fullfoerAvtale();
    }
}