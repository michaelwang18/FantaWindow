public class Entity {
    //fuck you michael - past michael
    private Entity(){}


    public static Character goblin(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(100 * scaling); //Hp
        int attack = (int) Math.round(20 * scaling + 20);
        int defense = (int) Math.round(15 * scaling);
        Character enemy = new Character("Goblin", health, attack, defense);
        enemy.addSkills(SkillBook.bite());
        enemy.addSkills(SkillBook.twinslash());
        enemy.addSkills(SkillBook.slash());
        return enemy;
    }

    public static Character orge(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(350 * scaling); //Hp
        int attack = (int) Math.round(35 * scaling + 15);
        int defense = (int) Math.round(5 * scaling);
        Character enemy = new Character("Orge", health, attack, defense);
        enemy.addSkills(SkillBook.bite());
        enemy.addSkills(SkillBook.clubBash());
        enemy.addSkills(SkillBook.clubBash());
        return enemy;
    }

    public static Character golem(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(200 * scaling + 50); //Hp
        int attack = (int) Math.round(50 * scaling + 15);
        int defense = (int) Math.round(30 * scaling);
        Character enemy = new Character("Golem", health, attack, defense);
        enemy.addSkills(SkillBook.clubBash());
        enemy.addSkills(SkillBook.clubBash());
        enemy.addSkills(SkillBook.clubBash());
        return enemy;
    }


    public static Character skeleton(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(120 * scaling); //Hp
        int attack = (int) Math.round(35 * scaling + 25);
        int defense = (int) Math.round(5 * scaling);
        Character enemy = new Character("Skeleton", health, attack, defense);
        enemy.addSkills(SkillBook.tripleShot());
        enemy.addSkills(SkillBook.arrowShot());
        enemy.addSkills(SkillBook.arrowShot());
        return enemy;
    }

    public static Character dragon(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(200 * scaling + 100); //Hp
        int attack = (int) Math.round(25 * scaling + 20);
        int defense = (int) Math.round(30 * scaling + 10);
        Character enemy = new Character("Dragon", health, attack, defense);
        enemy.addSkills(SkillBook.scratch());
        enemy.addSkills(SkillBook.fireball());
        enemy.addSkills(SkillBook.fireBreath());
        return enemy;
    }

    public static Character darkKnight(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(275 * scaling + 50); //Hp
        int attack = (int) Math.round(35 * scaling );
        int defense = (int) Math.round(45 * scaling);
        Character enemy = new Character("Dark Knight", health, attack, defense);
        enemy.addSkills(SkillBook.slash());
        enemy.addSkills(SkillBook.airSlash());
        enemy.addSkills(SkillBook.twinslash());
        return enemy;
    }

    public static Character sans(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(0 * scaling + 1); //Hp
        int attack = (int) Math.round(35 * scaling + 10);
        int defense = (int) Math.round(0 * scaling + 0);
        Character enemy = new Character("Sans", health, attack, defense);
        enemy.setDodgeChance(20);
        enemy.addSkills(SkillBook.gasterBlasters());
        enemy.addSkills(SkillBook.gasterBlasters());
        enemy.addSkills(SkillBook.gasterBlasters());
        return enemy;
    }

    public static Character shedinja(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(0 * scaling + 10); //Hp
        int attack = (int) Math.round(25 * scaling + 20);
        int defense = (int) Math.round(10000 * scaling + 0);
        Character enemy = new Character("Shedinja", health, attack, defense);
        enemy.setDodgeChance(1);
        enemy.addSkills(SkillBook.airSlash());
        enemy.addSkills(SkillBook.airSlash());
        enemy.addSkills(SkillBook.airSlash());
        return enemy;
    }

    public static Character goblinKing(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(200* scaling + 50); //Hp
        int attack = (int) Math.round(20 * scaling + 15);
        int defense = (int) Math.round(15 * scaling + 10);
        Character enemy = new Character("Goblin King", health, attack, defense);
        enemy.addSkills(SkillBook.swordCleave());
        enemy.addSkills(SkillBook.twinslash());
        enemy.addSkills(SkillBook.slash());
        return enemy;
    }

    public static Character hydraHead(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(200 * scaling + 150); //Hp
        int attack = (int) Math.round(25 * scaling + 20);
        int defense = (int) Math.round(20 * scaling + 5);
        Character enemy = new Character("Hydra Head", health, attack, defense);
        enemy.addSkills(SkillBook.bite());
        enemy.addSkills(SkillBook.fireball());
        enemy.addSkills(SkillBook.fireBreath());
        return enemy;
    }





    public static Character[] allEnemies(double scale){
        Character[] enemyList = new Character[32];
        enemyList[0] = goblin(scale);
        enemyList[1] = goblin(scale - .05);
        enemyList[2] = goblin(scale - .05);
        enemyList[3] = skeleton(scale - .05);
        enemyList[4] = skeleton(scale - .05);
        enemyList[5] = skeleton(scale - .05);
        enemyList[6] = goblin(scale);
        enemyList[7] = goblin(scale);
        enemyList[8] = orge(scale - .05);
        enemyList[9] = orge(scale - .05);
        enemyList[10] = orge(scale);
        enemyList[11] = golem(scale - .05);
        enemyList[12] = golem(scale - .05);
        enemyList[13] = golem(scale);
        enemyList[14] = orge(scale);
        enemyList[15] = skeleton(scale);
        enemyList[16] = dragon(scale);
        enemyList[17] = dragon(scale);
        enemyList[18] = skeleton(scale + .05);
        enemyList[19] = skeleton(scale + .05);
        enemyList[20] = skeleton(scale - .05);
        enemyList[21] = golem(scale - .05);
        enemyList[22] = goblin(scale - .05);
        enemyList[23] = goblin(scale - .05);
        enemyList[24] = goblin(scale + .05);
        enemyList[25] = goblin(scale + .05);
        enemyList[26] = skeleton(scale + .05);
        enemyList[27] = skeleton(scale);
        enemyList[28] = goblinKing(scale);
        enemyList[29] = sans(scale);
        enemyList[30] = shedinja(scale);
        enemyList[31] = darkKnight(scale);
        return enemyList;
    }

    public static Character[] storyEnemy(double scale){
        Character[] enemyList = new Character[32];
        enemyList[0] = goblin(scale);
        enemyList[1] = goblin(scale);
        enemyList[2] = goblin(scale + .05);
        enemyList[3] = goblin(scale + .05);
        enemyList[4] = skeleton(scale - .05);
        enemyList[5] = skeleton(scale + .05);
        enemyList[6] = skeleton(scale);
        enemyList[7] = skeleton(scale);
        enemyList[8] = orge(scale + .05);
        enemyList[9] = orge(scale - .05);
        enemyList[10] = dragon(scale);
        return enemyList;
    }


//GOOD PEOPLE PROLLY JUST HERE TO SEPERATE LINE CUZ YOU MESSY---------------------------------------------------------------------------------
    public static Character swordsman(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(175 * scaling); //Hp
        int attack = (int) Math.round(45 * scaling);
        int defense = (int) Math.round(35 * scaling);
        Character ally = new Character("Swordsman", health, attack, defense);
        ally.addSkills(SkillBook.twinslash());
        ally.addSkills(SkillBook.airSlash());
        ally.addSkills(SkillBook.slash());
        ally.addEvolutions(knight(1),duelist(1));
        return ally;
    }

    public static Character archer(double scaling) { // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(150 * scaling); //Hp
        int attack = (int) Math.round(55 * scaling);
        int defense = (int) Math.round(15 * scaling);
        Character ally = new Character("Archer", health, attack, defense);
        ally.addSkills(SkillBook.tripleShot());
        ally.addSkills(SkillBook.arrowShot());
        ally.addSkills(SkillBook.arrowVolley());
        ally.setCritChance(3);
        ally.addEvolutions(ranger(1),marksman(1));
        return ally;
    }

    public static Character barbarian(double scaling) { // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(200 * scaling); //Hp
        int attack = (int) Math.round(55 * scaling);
        int defense = (int) Math.round(10 * scaling);
        Character ally = new Character("Barbarian", health, attack, defense);
        ally.addSkills(SkillBook.twinslash());
        ally.addSkills(SkillBook.clubBash());
        ally.addSkills(SkillBook.rockThrow());
        return ally;
    }

    public static Character wizard(double scaling) { // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(125 * scaling); //Hp
        int attack = (int) Math.round(75 * scaling);
        int defense = (int) Math.round(5 * scaling);
        Character ally = new Character("Wizard", health, attack, defense);
        ally.addSkills(SkillBook.magicBolt());
        ally.addSkills(SkillBook.magicMissiles());
        ally.addSkills(SkillBook.spiritBomb());
        ally.addEvolutions(elementalist(1),druid(1));
        return ally;
    }

    public static Character tester(double scaling) { // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(999 * scaling); //Hp
        int attack = (int) Math.round(999 * scaling);
        int defense = (int) Math.round(999 * scaling);
        Character ally = new Character("Deus Ex Machina", health, attack, defense);
        ally.addSkills(SkillBook.obliterate());
        ally.addSkills(SkillBook.obliterate());
        ally.addSkills(SkillBook.obliterate());
        return ally;
    }

    public static Character[] startingChar(double scaling){
        Character[] list = {swordsman(scaling),archer(scaling),wizard(scaling)}; //BARBAR TEMP REMOVE
        return list;
    }

    //SWORDSMAN EVOLUTIONS

    public static Character knight(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(175 * scaling); //Hp
        int attack = (int) Math.round(50 * scaling);
        int defense = (int) Math.round(65 * scaling);
        Character ally = new Character("A Knight", health, attack, defense);
        ally.setDescription(Utility.color("Tier 2 Class",Color.PURPLE_BOLD_BRIGHT)
                + "\n" + Utility.color("His trumpets sound... The judgement day hath come.",Color.PURPLE_BOLD)  +"\nexcels at AOE attacks\nhigher defense and health");
        ally.addSkills(SkillBook.shieldBash()); //Shield Bash
        ally.addSkills(SkillBook.swordCleave());
        ally.addSkills(SkillBook.swordSweep());
        return ally;
    }





    public static Character duelist(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(180 * scaling); //Hp
        int attack = (int) Math.round(75 * scaling);
        int defense = (int) Math.round(25 * scaling);
        Character ally = new Character("Duelist", health, attack, defense);
        ally.setDodgeChance(3);
        ally.setCritChance(3);
        ally.setDescription(Utility.color(" Tier 2 Class",Color.PURPLE_BOLD_BRIGHT)
                + "\n" + Utility.color("Block Parry Dodge",Color.PURPLE_BOLD) + "\nexcels in high single target damage\nhigher Critical and Dodge rate");
        ally.addSkills(SkillBook.airSlash()); //Shield Bash
        ally.addSkills(SkillBook.swordThrust());
        ally.addSkills(SkillBook.tripleSlash());
        return ally;
    }

    //WIZARD EVOLUTIONS

    public static Character druid(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(175 * scaling); //Hp
        int attack = (int) Math.round(65 * scaling);
        int defense = (int) Math.round(65 * scaling);
        Character ally = new Character("Druid", health, attack, defense);
        ally.setDescription(Utility.color(" Tier 2 Class",Color.PURPLE_BOLD_BRIGHT)
                + "\n" + Utility.color("\" I am the Lorax, I speak for the trees\"",Color.PURPLE_BOLD) + "\nexcels in aoe attacks\nDodge chance and Defense");
        ally.setDodgeChance(3);
        ally.addSkills(SkillBook.thornBurst()); //Shield Bash
        ally.addSkills(SkillBook.mysticLeaves());
        ally.addSkills(SkillBook.magicBolt());
        return ally;
    }

    public static Character elementalist(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(200 * scaling); //Hp
        int attack = (int) Math.round(85 * scaling);
        int defense = (int) Math.round(30 * scaling);
        Character ally = new Character("Elementalist", health, attack, defense);
        ally.setDescription(Utility.color(" Tier 2 Class",Color.PURPLE_BOLD_BRIGHT)
                + "\n" + Utility.color( "don't let them cook",Color.PURPLE_BOLD) + "\nVersatile high damage\n HIGH ATTACK");
        ally.addSkills(SkillBook.thunder()); //Shield Bash
        ally.addSkills(SkillBook.fireball());
        ally.addSkills(SkillBook.icicleBarrage());
        return ally;
    }

    //ARCHER EVOLUTIONS

    public static Character marksman(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(175 * scaling); //Hp
        int attack = (int) Math.round(75 * scaling);
        int defense = (int) Math.round(15 * scaling);
        Character ally = new Character("Marksman", health, attack, defense);
        ally.setDescription(Utility.color(" Tier 2 Class",Color.PURPLE_BOLD_BRIGHT)
                + "\n" + Utility.color("OOH NAH WHY DOES HE HAVE A GUN, TWO GUNS" ,Color.PURPLE_BOLD) + "\nexcels Single Target Damage \nhigher Critical Rate and Attack");
        ally.setCritChance(3);
        ally.addSkills(SkillBook.doublebarel()); //Shield Bash
        ally.addSkills(SkillBook.revolver());
        ally.addSkills(SkillBook.grenade());
        return ally;
    }

    public static Character ranger(double scaling){ // 1.1 would be 10% scaling, 2.7 would be 170% scaling
        int health = (int) Math.round(200 * scaling); //Hp
        int attack = (int) Math.round(65 * scaling);
        int defense = (int) Math.round(30 * scaling);
        Character ally = new Character("Ranger", health, attack, defense);
        ally.setDescription(Utility.color(" Tier 2 Class",Color.PURPLE_BOLD_BRIGHT)
                + "\n" + Utility.color("Cant touch this" ,Color.PURPLE_BOLD)+ "\nexcels Aoe Damage\nhigher Crit and Dodge Rate ");
        ally.setCritChance(4);
        ally.addSkills(SkillBook.arrowRain()); //Shield Bash
        ally.addSkills(SkillBook.arrowShot());
        ally.addSkills(SkillBook.tripleShot());
        return ally;
    }








    //BARBARIAN










    }
