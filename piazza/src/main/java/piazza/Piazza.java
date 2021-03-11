package piazza;

/**
 *
 * @author thomabsk
 */
public class Piazza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        

        UserLoginCtrl loginCtrl = new UserLoginCtrl();

        boolean h = loginCtrl.loginUser("t@s.com", "thomas123");
        if(loginCtrl.isLoggedIn()){
            System.out.println("You logged in!"); 
        }
        else{
            System.out.println("Login failed!"); 
        }
        // LagAvtaleCtrl lagAvtaleCtrl = new LagAvtaleCtrl ();
        // lagAvtaleCtrl.lagAvtale(100, 120);
        // lagAvtaleCtrl.velgBruker(1);
        // lagAvtaleCtrl.velgBruker(2);
        // lagAvtaleCtrl.velgTidspunkt(110, 1);
        // lagAvtaleCtrl.fullfoerAvtale();
    }
}