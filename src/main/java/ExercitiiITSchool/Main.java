package ExercitiiITSchool;

import ExercitiiITSchool.model.GameData;
import ExercitiiITSchool.service.GameDataService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        appMenu();
        GameDataService gameDataService = new GameDataService();
        gameDataService.insert100Games();

    }

    public static void appMenu() throws IOException, CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException {
        Scanner scanner = new Scanner(System.in);
        GameDataService gameDataService = new GameDataService();
        String option;
        String appMenu = """
                \n \n
                Alegeti o optiune:
                1. Afisati toate jocurile;
                2. Introduceti un joc nou;
                3. Cautati joc dupa nume;
                4. Modificati detaliile jocului dupa nume;
                5. Stergeti joc dupa nume;
                0. Inchideti aplicatia.
                """;
        System.out.println(appMenu);
        do {
            System.out.print("Introduceti numarul optiunii dorite: ");
            option = scanner.nextLine();
            switch (Integer.parseInt(option)) {
                case 0: {
                    System.out.println("Aplicatia se inchide...");
                    break;
                }
                case 1: {
                    gameDataService.displayAllGames();
                    System.out.println(appMenu);
                    break;
                }
                case 2: {
                    gameDataService.writeGameToFile(readGameFromConsole(scanner));
                    System.out.println(appMenu);
                    break;
                }

                case 3: {
                    getGameByGameName(scanner);
                    System.out.println(appMenu);
                    break;
                }
                case 4: {
                    updateGameByName(scanner, gameDataService);
                    System.out.println(appMenu);
                    break;
                }
                case 5: {
                    deleteGameByName(scanner);
                    System.out.println(appMenu);
                    break;
                }
                default: {
                    System.out.println("Valoare invalida. Introduceti o valoare intre 0 si 5.");
                    System.out.println(appMenu);
                    break;
                }
            }

        } while (Integer.parseInt(option) != 0);
        scanner.close();
    }


    private static GameData readGameFromConsole(Scanner scanner) {
        GameData gameData = new GameData();

        System.out.println("Introduceti detaliile jocului!");

        System.out.print("Numele jocului: ");
        String name = scanner.nextLine();
        gameData.setName(name);

        System.out.print("Pretul jocului: ");
        double price = scanner.nextDouble();
        gameData.setPrice(price);
        scanner.nextLine();

        System.out.print("Spatiul pe disc necesar: ");
        double diskSpace = scanner.nextDouble();
        gameData.setDiskSpace(diskSpace);
        scanner.nextLine();

        System.out.print("Tipul jocului: ");
        String type = scanner.nextLine();
        gameData.setType(type);

        System.out.print("Platforma: ");
        String platform = scanner.nextLine();
        gameData.setPlatform(platform);

        return gameData;
    }

    private static void getGameByGameName(Scanner scanner) throws FileNotFoundException {
        GameDataService gameDataService = new GameDataService();
        System.out.print("Introduceti numele jocului pe care doriti sa-l cautati: " );
        String name = scanner.nextLine();
        System.out.println(gameDataService.getGameByName(name));
    }
    private static void deleteGameByName(Scanner scanner) throws IOException, CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException {
        GameDataService gameDataService = new GameDataService();
        System.out.print("Introduceti numele jocului pe care doriti sa-l stergeti: ");
        gameDataService.deleteGame((scanner.nextLine()));
    }

    private static void updateGameByName(Scanner scanner, GameDataService gameDataService) throws FileNotFoundException {
        System.out.print("Introduceti numele jocului pe care doriti sa-l modificati: ");
        String gameNameToBeUpdated = scanner.nextLine();

        GameData existingGame = gameDataService.getGameByName(gameNameToBeUpdated);
        if (existingGame == null) {
            System.out.println("Jocul cu numele \"" + gameNameToBeUpdated + "\" nu a fost găsit.");
            return;
        }
        System.out.println("Detalii actuale ale jocului: " + existingGame);

        GameData updatedGameData = readGameFromConsole(scanner);
        updatedGameData.setName(existingGame.getName());
        gameDataService.updateGameDetails(updatedGameData);
        System.out.println("Jocul a fost actualizat cu succes. Noile detalii sunt: " + updatedGameData);
    }
}
