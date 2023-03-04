import frontend.MainMenu;

public class App {
    public static void main(String[] args) {
        while(true){
            MainMenu.display();
            try{
                MainMenu.getInput(MainMenu.getMenuItemsSize());
            }catch(Exception e){
                System.out.println();
                System.out.println(e.getLocalizedMessage());
                continue;
            }
            break;
        }
    }
}
