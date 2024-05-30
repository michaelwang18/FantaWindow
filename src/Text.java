public class Text{   //Courtesy of my freind teaching me of how this works (to which i still dont get tooo much)
    //MODIFIERS
    public static final int BRIGHT = 1;
    public static final int DARK = 2;
    public static final int ITALICS = 3;
    //COLORS
    public static final int BLACK = 30;
    public static final int RED = 31;
    public static final int GREEN = 32;
    public static final int YELLOW = 33;
    public static final int BLUE = 34;
    public static final int MAGENTA = 35;
    public static final int CYAN = 36;
    public static final int WHITE = 37;
    //CONTROLS
    public static final String CLEAR_SCREEN =  "\033[2J";
    public static final String HIDE_CURSOR =  "\033[?25l";
    public static final String SHOW_CURSOR =  "\033[?25h";
    public static final String Reset_Text = "\u001b[39m";
    public static final String Reset_Background = "\u001b[49m";

    //use this to go back to normal terminal before exiting.
    public static final String RESET = "\033[0m"+SHOW_CURSOR;

    //use this to convert from color to background (30 to 37 becomes 40 to 47)
    public static int  background(int color){
        return color + 10;
    }

    public static int  brighterColor(int color){
        return color + 60;
    }


    //terminal specific character to move the cursor to a location
    //top left is 1,1
    public static void go(int x,int y){
        System.out.print("\033[" + x + ";" + y + "H");
    }

    //Overloarded color commands (give 1 or more colors/modifiers)
    public static void color(int m){
        System.out.print ("\033[;" + m + "m");
    }
    public static void color(int m1, int m2){
        System.out.print ("\033["+ m1 + ";" + m2 + "m");
    }
    public static void color(int m1, int m2, int m3){
        System.out.print ("\033["+ m1 + ";" + m2 + ";" + m3 + "m");
    }
    public static void color(int m1, int m2, int m3, int m4){
        System.out.print ("\033["+ m1 + ";" + m2 + ";" + m3 + ";"+m4+"m");
    }

    //And don't forget you can easily delay the printing if needed:
    public static void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }

    //erase (black bakground)
    public static void erase(int r, int c, int size){
        go(r,c);
        System.out.print("\033[0m");
        for(int i = 0; i < size;i++){
            System.out.print(" ");
        }
    }

}