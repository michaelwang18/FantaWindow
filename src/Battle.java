import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
    Character player1;
    Character player2;
    Character player3;
    Character enemy1;
    Character enemy2;
    Character enemy3;
    ArrayList<Character> playerTeam = new ArrayList<>();
    ArrayList<Character> enemyTeam = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public Battle(Character p1, Character p2, Character p3, Character e1, Character e2, Character e3){
        System.out.println("BATTLE START");
        player1 = p1;
        player2 = p2;
        player3 = p3;
        playerTeam.add(p1);
        playerTeam.add(p2);
        playerTeam.add(p3);
        enemy1 = e1;
        enemy2 = e2;
        enemy3 = e3;
        enemyTeam.add(e1);
        enemyTeam.add(e2);
        enemyTeam.add(e3);


    }
    public boolean start() { // true for win, false for lose
        int round = 1;
        while (enemy1.alive || enemy2.alive || enemy3.alive && (player1.alive || player2.alive || player3.alive) ) {

            System.out.println("--------------------" + Utility.color("ROUND " + round,Color.WHITE_BOLD_BRIGHT) + "-------------------------");

            for (int i = 0; i < playerTeam.size(); i++){
                if (playerTeam.get(i).alive){
                    System.out.println();
                    overview();}
                    useSkill(playerTeam.get(i));
            }
            if (!enemy1.alive && !enemy2.alive && !enemy3.alive ) {return true;}
            System.out.println("---------------" + Utility.color("Enemy Turn",Color.WHITE_BOLD_BRIGHT)+"--------------");
            enemySkill(enemy1);
            enemySkill(enemy2);
            enemySkill(enemy3);

            round++;
            System.out.println("\n-----------------------------------------\n\n");
            if (!enemy1.alive & !enemy2.alive & !enemy3.alive && (player1.alive || player2.alive || player3.alive)) {
                return true;
            } else if (!(player1.alive || player2.alive || player3.alive)){
                return false;
            }
        }
         return true;
        }

    public void overview(){
        if (enemy1.alive || enemy2.alive || enemy3.alive ) {
            for (int i = 1; i <= playerTeam.size(); i++) {
                Character temp = playerTeam.get(i - 1);
                System.out.println("(" + i + ") " + temp.info());
                System.out.println(Utility.healthBar(temp.getHP(),temp.getMaxHealth()));

            }
            System.out.println( Color.WHITE_BOLD_BRIGHT + "-------------------[O\\\\\\\\\\[========================-" + Color.RESET);
            for (int i = 1; i <= enemyTeam.size(); i++) {
                Character temp = enemyTeam.get(i - 1);
                System.out.println("(" + i + ") " + temp.info());
                System.out.println(Utility.healthBar(temp.getHP(),temp.getMaxHealth()));

            }
        }
    }


    private void useSkill (Character player) {
        if (player.alive && enemy1.alive || player.alive && enemy2.alive || player.alive && enemy3.alive ) {
            int alive = 0;
            int dead = 0;
            boolean single = false;
            for (int i = 0; i < enemyTeam.size(); i++){
                Character checked = enemyTeam.get(i);
                if (checked.alive){alive++;}
                else{dead++;}
            }
            if (alive == 1 && dead == 2){ single = true;}

            String info = player.turn(single);
            int atk = player.getATK();
            int dmg = (int) Math.round(Double.parseDouble(info.substring(3)) * atk);
            if (info.substring(0, 1).equals("M")) {
                for (int i = 0; i < enemyTeam.size(); i++) {
                    Character target = enemyTeam.get(i);
                    if (target.alive){
                        for (int j = 0; j < Integer.parseInt(info.substring(2,3)); j++){
                            target.takeDMG(dmg);
                        }
                    }
                }
            }
            if (info.substring(0, 1).equals("S")) {
                if (single){
                    int targetChoice = (int) (Math.random() * (enemyTeam.size()));
                    Character target = enemyTeam.get(targetChoice);
                    while (!target.alive) {
                        targetChoice = (int) (Math.random() * (enemyTeam.size()));
                        target = enemyTeam.get(targetChoice);
                    }
                    for (int j = 0; j < Integer.parseInt(info.substring(2,3)); j++){
                        target.takeDMG(dmg);}
                } else {
                    Character target = enemyTeam.get(Integer.parseInt(info.substring(1, 2))-1);
                    for (int j = 0; j < Integer.parseInt(info.substring(2,3)); j++){
                        target.takeDMG(dmg);}

                }
            }
        }
    }




    private void enemySkill (Character badGuy) { //im the bald guy
        int skillchoice = (int) (Math.random() * 3) ;
        String[] info = badGuy.getSkill(skillchoice); //Info is 0 = Name | 1 (Str) = damage mult (double) | 2 = targetting (Str)
        int dmg = (int) Math.round(Double.parseDouble(info[1]) * badGuy.getATK());
        if ((badGuy.getName().equals("Hydra Head") || badGuy.alive) && (player1.alive || player2.alive || player3.alive)) { //Only allows them to act if they are alive or hydra head

            System.out.println("\n" + Utility.color(badGuy.getName(), Color.CYAN_BOLD_BRIGHT) + " uses " + Utility.color(info[0],Color.BLUE_BRIGHT) );
            if (info[2].equals("Mass Attack")) {
                for (int i = 0; i < playerTeam.size(); i++) {
                    Character target = playerTeam.get(i);
                    if (target.alive) {
                        for (int j = 0; j < Integer.parseInt(info[3]); j++){
                            target.takeDMG(dmg);
                        }
                    }
                }
            } else if (info[2].equals("Single Target")) {
                int targetChoice = (int) (Math.random() * (playerTeam.size()));
                    Character target = playerTeam.get(targetChoice);
                    while (!target.alive) {
                        targetChoice = (int) (Math.random() * (playerTeam.size()));
                        target = playerTeam.get(targetChoice);
                }
                for (int j = 0; j < Integer.parseInt(info[3]); j++){
                    target.takeDMG(dmg);
                }
            }
        }
    }

}
