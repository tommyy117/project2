import java.util.Random;
import java.util.Scanner;

public class TommyProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Game! Enter if you are so brave! Join Thomas as he enters a forbidden land in search for glory");

        System.out.print("Choose your game mode: 0 for Standard and 1 for Survival\n");
        int gameMode = scanner.nextInt();

        int playerHealth = 30;
        int playerAttackPower = 10;
        int playerArmorLevel = 5;

        if (gameMode == 0) {
            System.out.println("You have chosen standard mode. Lets get it!");

            int numEnemies = 3;
            String[] enemyNames = {"Skeleton 1", "Skeleton 2", "Skeleton 3"};
            int[] enemyHealths = {15, 10, 8};
            int[] enemyAttacks = {10, 8, 6};
            int[] enemyArmors = {5, 4, 2};
            boolean[] enemiesAlive = {true, true, true};

            System.out.println("Upon arrival he is confronted by 3 undead skeletons intending on showing no mercy, they attack him!");

            for (int i = 0; i < numEnemies; i++) {
                while (true) {
                    int enemyIndex = -1;
                    while (enemyIndex == -1) {
                        int randIndex = random.nextInt(numEnemies);
                        if (enemiesAlive[randIndex]) {
                            enemyIndex = randIndex;
                        }
                    }

                    System.out.print(String.format("%s is attacking Thomas\n", enemyNames[enemyIndex]));
                    System.out.print("-------Confrontation Started-------\n");
                    System.out.print(String.format("Player: Thomas\nHealth: %d\nAttack power: %d\nArmor Level: %d\n", playerHealth, playerAttackPower, playerArmorLevel));
                    System.out.print(String.format("Enemy: %s\nHealth: %d\nAttack power: %d\nArmor Level: %d\n", enemyNames[enemyIndex], enemyHealths[enemyIndex], enemyAttacks[enemyIndex], enemyArmors[enemyIndex]));

                    int damageTaken = Math.max(enemyAttacks[enemyIndex] - playerArmorLevel, 0);
                    playerHealth -= damageTaken;
                    System.out.print(String.format("%s attacks with attack power %d\n", enemyNames[enemyIndex], enemyAttacks[enemyIndex]));
                    System.out.print(String.format("Thomas's health has been reduced to %d\n", playerHealth));
                    boolean playerAlive = playerHealth > 0;

                    if (!playerAlive) {
                        System.out.print("Player has survived = false\n");
                        System.out.print("-------Confrontation Complete-------\n");
                        System.out.print("You have been defeated!\n");
                        return;
                    } else {
                        System.out.print("Player has survived = true\n");
                    }

                    int damageDealt = Math.max(playerAttackPower - enemyArmors[enemyIndex], 0);
                    enemyHealths[enemyIndex] -= damageDealt;
                    System.out.print(String.format("Thomas attacks with attack power %d\n", playerAttackPower));
                    System.out.print(String.format("%s's health has been reduced to %d\n", enemyNames[enemyIndex], enemyHealths[enemyIndex]));
                    boolean enemyAlive = enemyHealths[enemyIndex] > 0;

                    if (!enemyAlive) {
                        System.out.print(String.format("%s has survived = false\n", enemyNames[enemyIndex]));
                        System.out.print("-------Confrontation Complete-------\n");
                        enemiesAlive[enemyIndex] = false;
                        boolean allEnemiesDead = true;
                        for (int j = 0; j < numEnemies; j++) {
                            if (enemiesAlive[j]) {
                                allEnemiesDead = false;
                                break;
                            }
                        }
                        if (allEnemiesDead) {
                            System.out.print("You have vanquished your foes!\n");
                            return;
                        } else {
                            System.out.print("The battle continues...\n");
                            break;
                        }
                    }
                }
            }
        } else if (gameMode == 1) {
            System.out.println("You are in Survival mode.");
            System.out.println("Enter the difficulty level:");
            System.out.println("0: Easy");
            System.out.println("1: Normal");
            System.out.println("2: Hard");
            System.out.println("3: Insane");

            int difficultyLevel = scanner.nextInt();
            int attackChangePercent, armorChangePercent, maxEnemyGroupSize;
            boolean playerAttacksFirst, restorePlayerHealth;

            switch (difficultyLevel) {
                case 0:
                    attackChangePercent = 0;
                    armorChangePercent = 0;
                    maxEnemyGroupSize = 3;
                    playerAttacksFirst = true;
                    restorePlayerHealth = true;
                    break;
                case 1:
                    attackChangePercent = 10;
                    armorChangePercent = 10;
                    maxEnemyGroupSize = 4;
                    playerAttacksFirst = true;
                    restorePlayerHealth = false;
                    break;
                case 2:
                    attackChangePercent = 25;
                    armorChangePercent = 25;
                    maxEnemyGroupSize = 5;
                    playerAttacksFirst = false;
                    restorePlayerHealth = false;
                    break;
                case 3:
                    attackChangePercent = 50;
                    armorChangePercent = 50;
                    maxEnemyGroupSize = 6;
                    playerAttacksFirst = false;
                    restorePlayerHealth = false;
                    break;
                default:
                    System.out.println("Invalid difficulty level. Exiting...");
                    return;
            }

            int roundsSurvived = 0;
            int initialPlayerHealth = playerHealth;
            String playerName = "Thomas";
            for (int round = 1; playerHealth > 0; round++) {
                System.out.println("Round " + round + " starts.");
                if (round > 1 && restorePlayerHealth) {
                    playerHealth = initialPlayerHealth;
                    System.out.println("Player's health has been restored.");
                }

                System.out.println("Player name: " + playerName + ", health: " + playerHealth + ", attack power: " + playerAttackPower + ", armor level: " + playerArmorLevel);
                String enemyGroupName = "Enemy Group " + round;
                int enemyGroupSize = new Random().nextInt(maxEnemyGroupSize - 1) + 2;

                int totalEnemyHealth = 0;
                int enemyGroupAttackPower = 0;
                int enemyGroupArmorLevel = 0;
                for (int i = 0; i < enemyGroupSize; i++) {
                    int enemyHealth = new Random().nextInt(11) + 5;
                    int enemyAttack = new Random().nextInt(6) + 5;
                    int enemyArmor = new Random().nextInt(3) + 1;

                    System.out.println("Enemy " + (i + 1) + " health: " + enemyHealth + ", attack power: " + enemyAttack + ", armor level: " + enemyArmor);

                    totalEnemyHealth += enemyHealth;
                    enemyGroupAttackPower += enemyAttack + (int) (((double) attackChangePercent / 100) * enemyAttack);
                    enemyGroupArmorLevel += enemyArmor + (int) (((double) armorChangePercent / 100) * enemyArmor);
                }

                System.out.println("Combat Begins!!!!!");

                while (playerHealth > 0 && totalEnemyHealth > 0) {
                    if (playerAttacksFirst) {
                        int damageToEnemies = playerAttackPower - enemyGroupArmorLevel;
                        totalEnemyHealth -= damageToEnemies;
                        if (totalEnemyHealth <= 0) {
                            System.out.println("Enemy group has been defeated.");
                            break;
                        }
                        System.out.println("Confrontation Complete");

                        int damageToPlayer = enemyGroupAttackPower - playerArmorLevel;
                        playerHealth -= damageToPlayer;
                        if (playerHealth <= 0) {
                            System.out.println("Player has been defeated.");
                            break;
                        }
                        System.out.println("Confrontation Complete");
                    } else {
                        int damageToPlayer = enemyGroupAttackPower - playerArmorLevel;
                        playerHealth -= damageToPlayer;
                        if (playerHealth <= 0) {
                            System.out.println("Player has been defeated.");
                            break;
                        }
                        System.out.println("Confrontation Complete");

                        int damageToEnemies = playerAttackPower - enemyGroupArmorLevel;
                        totalEnemyHealth -= damageToEnemies;
                        if (totalEnemyHealth <= 0) {
                            System.out.println("Enemy group has been defeated.");
                            break;
                        }
                        System.out.println("Confrontation Complete");
                    }
                }
                System.out.println("Round Complete");
                if (playerHealth > 0) {
                    roundsSurvived++;
                }
            }
            System.out.println("Player's final score: " + roundsSurvived);
        }
    }
}











