import java.util.Scanner;

import frontend.MainMenu;

public class App {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int option;
        while(true){
            MainMenu.display();
            try{
                option = MainMenu.getInput(scanner, MainMenu.getMenuItemsSize());
                
            }catch(Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                continue;
            }
            break;
        }


    }
}
