public class Runner {
    
    public static int idNo; // Used to store the user idNo for universal access
    public static String[] groupNames; // Used to store the most recent set of groups user belongs to
    public static int[] groupIDs; // Used to store the most recent set of group id's user belongs to
    
    public static void main(String args[] ) {
        System.out.println("Start!");
        MySQL my = new MySQL();
        StartGUI gui = new StartGUI(my);
    }
    
    public static void printer(String[] array) {
        System.out.println("Array Start!");
        if(array != null) {
            for(int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }
        else
            System.out.println("Name array is null.");
    }
    public static void printer(int[] array) {
        System.out.println("Array Start!");
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        } else {
            System.out.println("group ID array is null.");
        }
    }
}