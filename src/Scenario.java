import java.util.Scanner;
public class Scenario {

    private static Scanner scan = new Scanner(System.in);

    private Scenario(){}

    public static void afterBattle(Character p1, Character p2, Character p3){
        System.out.println("After Your Battle You Decide To...\n");
        int options = 3;
        int choice1 = (int) (Math.random() * 4) + 1; //4 options
        int choice2 = (int) (Math.random() * 4) + 1;
        while (choice2 == choice1) {            //Confirm not the same number
            choice2 = (int) (Math.random() * 4) + 1;
        }

        System.out.print("(1) ");
        if (choice1 == 1 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Visit The Blacksmith" ,25)  + Color.CYAN_BOLD + "+15 ATK⚔\uFE0F " + Color.RESET);
        }
        if (choice1 == 2 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Visit The Temple" ,25)  + Color.CYAN_BOLD + "+40 Max HP\uD83D\uDC9A  +80 HP\uD83D\uDC9A" + Color.RESET);
        }
        if (choice1 == 3 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Train" ,25)  + Color.CYAN_BOLD + " +20 Max HP\uD83D\uDC9A  +20 HP\uD83D\uDC9A  +15 DEF\uD83D\uDEE1\uFE0F" + Color.RESET);
        }
        if (choice1 == 4 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Review Strategy" ,25)  + Color.CYAN_BOLD + "+15 Max HP\uD83D\uDC9A  +5 ATK⚔\uFE0F  +5 DEF\uD83D\uDEE1\uFE0F" + Color.RESET);
        }

        System.out.print("\n(2) ");

        if (choice2 == 1 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Visit The Blacksmith" ,25) + Color.CYAN_BOLD + "+15 ATK⚔\uFE0F" + Color.RESET);
        }
        if (choice2 == 2 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT +  Utility.spaceout("Visit The Temple" ,25)  + Color.CYAN_BOLD + "+40 Max HP\uD83D\uDC9A  +80 HP\uD83D\uDC9A" + Color.RESET);
        }
        if (choice2 == 3 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Train" ,25)  + Color.CYAN_BOLD + " +20 Max HP\uD83D\uDC9A  +20 HP\uD83D\uDC9A  +15 DEF\uD83D\uDEE1\uFE0F" + Color.RESET);
        }
        if (choice2 == 4 ){
            System.out.println(Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Review Strategy" ,25)  + Color.CYAN_BOLD + "+15 Max HP\uD83D\uDC9A  +5 ATK⚔\uFE0F  +5 DEF\uD83D\uDEE1\uFE0F" + Color.RESET);
        }


        //DOING IT FUNNY

        System.out.print("\n(3) ");
        System.out.println( Color.WHITE_BOLD_BRIGHT + Utility.spaceout("Rest" ,25)  + Color.GREEN_BOLD_BRIGHT + "     -- FULL HEAL --" + Color.RESET);

        if (Utility.chance(1,100)){ //sacrifice
            System.out.println("\n(4)" + Utility.color(" BLOOD SACRIFICE",Color.RED_BOLD_BRIGHT));
            options++;
        }

        int pick = Utility.tryInput(scan.nextLine(),options);
        if  (pick == 3){
            p1.heal();
            p2.heal();
            p3.heal();
            pick = 0;
        }

        if (pick == 4){
            p1.evolve(Entity.tester(1));
            p2.evolve(new Character());
            p3.evolve(new Character());
            pick = 0;
        }

        if (pick == 1){   //Generic Choices
            pick = choice1;
        } else if (pick == 2){
            pick = choice2;
        }


        if (pick == 1){
            p1.statChange(0,15,0,0);
            p2.statChange(0,15,0,0);
            p3.statChange(0,15,0,0);
        }

        if (pick == 2){
            p1.statChange(80,0,0,40);
            p2.statChange(80,0,0,40);
            p3.statChange(80,0,0,40);
        }

        if (pick == 3){
            p1.statChange(20,0,15,20);
            p2.statChange(20,0,15,20);
            p3.statChange(20,0,15,20);
        }
        if (pick == 4){
            p1.statChange(0,5,5,15);
            p2.statChange(0,5,5,15);
            p3.statChange(0,5,5,15);
        }
    }

    public static void event(Character p1, Character p2, Character p3) {
        Character[] team = {p1,p2,p3};
        Character victim = team[(int) (Math.random() * 3)];
        String name = victim.getName();
        int random = (int) (Math.random() * 5 + 1);   //options
        boolean lucky = (Utility.chance(1, 2));
        switch (random) {
            case 1:
                System.out.println(Utility.color(name + " finds a healing potion \uD83E\uDDEA at the bottom of their backpack,\nWho knows how long it has been there for" +
                    "\nDrink It?\n(1) Yes\n(2) No",Color.YELLOW_BOLD));
                int choice = Utility.tryInput(scan.nextLine(),2);
                if (choice == 1){  //YES
                    if (lucky){//YES AND LUCK
                        System.out.println("Despite the off putting oder,\n" + name + " downs the potion and feels reintegrated, perhaps even a little stronger");
                        System.out.println("Full Heal For " + name + " alongside +30 Max Health\uD83D\uDC9A,\nalbeit the taste was awful");
                        victim.statChange(30,0,0,30);
                        victim.heal();


                    }else{ //YES AND BAD LUCK
                        System.out.println(Color.WHITE_BRIGHT + "The potion was been expired for years now \n" + name + " should've looked for the expiration date.");
                        System.out.println("-35% of Health for " + name);
                        int healing = -(int)Math.round(victim.getMaxHealth() * .35);
                        victim.statChange(healing,0,0,0);
                        if (victim.getHP() <= 0){
                            victim.alive = false;
                        }
                    }
                } else { //NO
                        System.out.println(Color.WHITE_BRIGHT + "it was the right call to leave it behind, no reason to take unnecessary risks" + Color.RESET);
                }
                break;
/////////////////////
            case 2:
                System.out.println(Utility.color(name + " finds a suspicious potion \uD83E\uDDEA at the bottom of their backpack,\nWho knows how long it has been there for," +
                        "\nDrink It?\n(1) Yes\n(2) No",Color.GREEN_BOLD));
                int choice2 = Utility.tryInput(scan.nextLine(),2);
                if (choice2 == 1){  //YES
                    if (lucky){//YES AND LUCK
                        System.out.println(Color.WHITE + "Taking the risk was worth\n as " + name + " feels empowered" + Color.RESET);
                        int attackup = (int)Math.round(victim.getATK() * .10);
                        int defense =  (int)Math.round(victim.getDEF() * .10);
                        System.out.println( "10% Attack up for " + name + " and 10% Defense\uD83D\uDC9A");
                        victim.statChange(0,attackup,defense,0);

                    }else{ //YES AND BAD LUCK
                        System.out.println(Color.WHITE_BRIGHT + "The moment " + name + " smelt it, they should've not drank it.\n" + name + " threw up shortly after.");
                        System.out.println("-15% defense and -15% attack and 25% hp for " + name);
                        int attackup = (int)Math.round(victim.getATK() * .15);
                        int defense =  (int)Math.round(victim.getDEF() * .15);
                        int hp = (int)Math.round(victim.getMaxHealth() * .15);
                        victim.statChange(-hp,-attackup,-defense,0);
                        if (victim.getHP() <= 0){
                            victim.setAlive(false);
                        }
                    }
                } else { //NO
                    System.out.println(Color.WHITE_BRIGHT + "It was the right call to leave it behind, no reason to take unnecessary risks, considering its pungent odor" + Color.RESET);
                }
                break;
///////////////////////////////////////////////////////////////////////////////////////
            case 3:
                System.out.println(Utility.color("Camping out in the relatively peaceful forest \ndoes the party decide to take turns keeping watch?\n(1) Yes \n(2) No",Color.YELLOW_BOLD));
                int choice3 = Utility.tryInput(scan.nextLine(),2);
                if (choice3 == 1){  //YES
                    if (lucky){//YES AND LUCK
                        System.out.println(Color.WHITE_BRIGHT + "It is good to stay alert, no matter how safe you feel");
                        System.out.println("And that was the right choice saving you from getting robbed, rather you gained from the midnight encounter");
                        System.out.println("10% Attack + 10% Defense for party!" + Color.RESET);
                        for (int i = 0; i < team.length; i++) {
                            int attackup = (int) Math.round(victim.getATK() * .10);
                            int defense = (int) Math.round(victim.getDEF() * .10);
                            victim.statChange(0, attackup, defense, 0);
                        }

                    }else{ //YES AND BAD LUCK
                        System.out.println(Color.WHITE_BRIGHT + "It is good to stay alert, no matter how safe you feel");
                        System.out.println("But the forest was calm through the night, yet no one was able to deeply sleep");
                        System.out.println("-15% max health for party\uD83D\uDC9A!" + Color.RESET);
                        for (int i = 0; i < team.length; i++){
                            victim = team[i];
                            int healing = (int)Math.round(victim.getHP() * .25);
                            victim.statChange(-healing,0,0,-healing);
                            if (victim.getHP() <= 0){
                                victim.setAlive(false);
                            }
                        }

                    }
                } else { //NO AND LUCK
                    if (lucky) {
                        System.out.println(Color.WHITE_BRIGHT + "Everyone feels well rested and prepared for the journey ahead");
                        System.out.println(Color.GREEN_BOLD + "Full Heal & 20% Max Hp" + Color.RESET);
                        for (int i = 0; i < team.length; i++) {
                            victim = team[i];
                            victim.heal();
                            int healing = (int) Math.round(victim.getHP() * .20);
                            victim.statChange(healing, 0, 0, healing);
                            if (victim.getHP() <= 0) {
                                victim.setAlive(false);
                            }
                        }
                    } else {
                        System.out.println(Color.WHITE_BRIGHT + "Being woken up by goblins doesn't feel great, especially in the middle of the night");
                        System.out.println(Color.GREEN_BOLD + "-35% Max Hp" + Color.RESET);
                        for (int i = 0; i < team.length; i++) {
                            victim = team[i];
                            int healing = (int) Math.round(victim.getHP() * .15);
                            victim.statChange(-healing, 0, 0, -healing);
                            if (victim.getHP() <= 0) {
                                victim.setAlive(false);
                            }
                        }
                    }
                }
                break;
///////////////////////////////////////////////////////////////////////////////////////
            case 4:
                System.out.println(Utility.color("The party's equipment look like they could use some help, but funds are getting low\nHead to the Blacksmith?\n(1) Yes\n(2) No",Color.YELLOW_BOLD));
                int choice4 = Utility.tryInput(scan.nextLine(),2);
                if (choice4 == 1){  //YES
                    if (lucky){//YES AND LUCK
                        System.out.println(Color.WHITE_BRIGHT + "Turns out your Adventurer's Lisence comes with warranty over equipment bought in town.");
                        System.out.println("Such a novel idea for a fantastical world, you even were able to trade up to the new I-Wand 15 Pro!");
                        System.out.println("10% Attack + 5% Defense for party!" + Color.RESET);
                        for (int i = 0; i < team.length; i++){
                            victim = team[i];
                            int attackup = (int)Math.round(victim.getATK() * .1);
                            int defense =  (int)Math.round(victim.getDEF() * .05);
                            victim.statChange(0,attackup,defense,0);
                        }
                    }else{ //YES AND BAD LUCK
                        System.out.println(Color.WHITE_BRIGHT + "Turns out your Adventurer's License comes with warranty over equipment bought in town.");
                        System.out.println("However your weapons are too old and arent supported anymore,\npaying the cost came with oatmeal gruel for a week");
                        System.out.println("5% Attack + 2.5% Defense for party!\nBut -25% & -20 HP and Max Hp after starving" + Color.RESET);
                        for (int i = 0; i < team.length; i++){
                            victim = team[i];
                            int healing = (int)Math.round(victim.getMaxHealth() * .25) + 20;
                            int attackup = (int)Math.round(victim.getATK() * .05);
                            int defense =  (int)Math.round(victim.getDEF() * .025);
                            victim.statChange(-healing,attackup,defense,-healing);
                            if (victim.getHP() <= 0){
                                victim.setAlive(false);
                            }
                        }

                    }
                } else { //NO AND LUCK
                    if (lucky) {
                        System.out.println(Color.WHITE_BRIGHT + "Everyone was just over reacting, you don't need the newest Magi-tech products to journey.");
                        System.out.println( "A full wallet, a full heart,\nFull Heal + 20 Max Hp" + Color.RESET);
                        for (int i = 0; i < team.length; i++) {
                            victim = team[i];
                            victim.statChange(20, 0, 0, 20);
                            victim.heal();
                        }
                    } else {
                        System.out.println(Color.WHITE_BRIGHT + "Having your weapons crumble in your hands isn't all that nice./n-10% attack & -5% defense" + Color.RESET);
                        for (int i = 0; i < team.length; i++) {
                            victim = team[i];
                            int attackup = -(int)Math.round(victim.getATK() * .10);
                            int defense =  -(int)Math.round(victim.getDEF() * .05);
                            victim.statChange(0, attackup, defense, 0);
                        }
                    }
                }
                break;
                /////////////////////////////////////////////////////////////////////////////////////////////////
            case 5:
                System.out.println(Utility.color("A treasure chest was found, but the eerie blood stains at the hinge are unsettling?\n(1) Yes\n(2) No",Color.YELLOW_BOLD));
                int choice5 = Utility.tryInput(scan.nextLine(),2);
                if (choice5 == 1){  //YES
                    if (lucky){//YES AND LUCK
                        System.out.println(Color.WHITE_BRIGHT + "You found various goods, some to use, some to sell");
                        System.out.println("However the blood still leaves questions to be asked");
                        System.out.println(Color.YELLOW_BOLD_BRIGHT + "20% Attack + 15% Defense for party!" + Color.RESET);
                        int attackup = (int)Math.round(victim.getATK() * .20);
                        int defense =  (int)Math.round(victim.getDEF() * .15);
                        victim.statChange(0,attackup,defense,0);

                    }else{ //YES AND BAD LUCK
                        System.out.println(Color.WHITE_BOLD_BRIGHT + name + "'s head get viciously bitten off,");
                        System.out.println("That explained quite alot... \nat what cost");
                        System.out.println( Color.RED_BOLD_BRIGHT + name + " dies" + Color.RESET);
                            victim.setHealth(0);
                            victim.setAlive(false);
                    }
                } else { //NO
                    System.out.println(Color.WHITE_BOLD_BRIGHT + "Perhaps it was better left unopened, but the unease of the stomach churning sight lingers");
                    System.out.println("-5% HP for team (yea yea kinda unfair blah blah idc)" + Color.RESET);
                    for (int i = 0; i < team.length; i++) {
                        victim = team[i];
                        int healing = -(int) (victim.getMaxHealth() * .05);
                        victim.statChange(healing, 0, 0, 0);
                        if (victim.getHP() <= 0) {
                            victim.setAlive(false);
                        }
                    }
                }
                break;
        }
    }
    public static void upgrade(Character player){
        System.out.println(Utility.color(player.getName(),Color.CYAN_BOLD_BRIGHT) + " Can Promote Into...  \n");
        for (int i = 0; i < player.getEvolutions().size() ; i++){
            Character newCharacter = player.getEvolutions().get(i);
            System.out.println("(" + (i + 1) + ") " + Utility.color(newCharacter.getName(),Color.CYAN_BOLD_BRIGHT) + " " + newCharacter.getDescription() +"\n");

        }
            int choice = Utility.tryInput(scan.nextLine(),player.getEvolutions().size()) - 1;
            player.evolve(player.getEvolutions().get(choice));

        System.out.println(player.info());
        player.printSkillInfo();
        scan.nextLine();

    }


}
