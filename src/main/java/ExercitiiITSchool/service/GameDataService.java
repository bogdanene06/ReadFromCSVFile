/*
Author: Ene Bogdan
Country: Romania
*/
package ExercitiiITSchool.service;

import ExercitiiITSchool.model.GameData;
import com.github.javafaker.Faker;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameDataService {

    Logger logger = Logger.getLogger(GameDataService.class.getName());
    private final String fileName = "games.csv";


    public List<GameData> getAllGames() throws FileNotFoundException {
        logger.info("Se executa metoda \"getAllGames()\".");
        Reader reader = new BufferedReader(new FileReader(fileName));

        CsvToBean<GameData> csvReader = new CsvToBeanBuilder<GameData>(reader)
                .withType(GameData.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();

        return csvReader.parse();

    }


    public void displayAllGames() throws FileNotFoundException {
        logger.info("Se executa metoda \"displayAllGames()\".");
        for (GameData gameData : getAllGames()) {
            System.out.println(gameData);
        }
    }


    public void writeGameToFile(GameData gameData) throws IOException, CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException {
        logger.info("Se executa metoda \"writeGameToFile()\".");

        for (GameData game : getAllGames()) {
            if (game.getName().equalsIgnoreCase(gameData.getName())) {
                logger.log(Level.WARNING, "Game already exists.");
                return;
            }
        }
        List<GameData> games = getAllGames();
        try (FileWriter writer = new FileWriter(fileName)) {
            StatefulBeanToCsv<GameData> beanWriter = new StatefulBeanToCsvBuilder<GameData>(writer)
                    .build();
            games.add(gameData);
            beanWriter.write(games);
        }
    }

    public GameData getGameByName(String name) throws FileNotFoundException {
        logger.info("Se executa metoda \"getGameByName()\".");
        List<GameData> allGames = getAllGames();
        for (GameData gameData : allGames) {
            if (gameData.getName().equalsIgnoreCase(name)) {
                System.out.print("Detaliile jocului \"" + name + "\": ");
                return gameData;
            }
        }
        return null;
    }

    public void updateGameDetails(GameData updatedGameData) throws FileNotFoundException {
        logger.info("Se executa metoda \"updateGameDetails()\".");
        List<GameData> allGames = getAllGames();
        boolean gameFound = false;

        for (int i = 0; i < allGames.size(); i++) {
            GameData game = allGames.get(i);
            if (game.getName().equalsIgnoreCase(updatedGameData.getName())) {
                allGames.set(i, updatedGameData); // Actualizează jocul din listă
                gameFound = true;
                break;
            }
        }

        if (!gameFound) {
            logger.log(Level.WARNING, "Jocul nu a fost găsit în baza de date.");
            return;
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            StatefulBeanToCsv<GameData> beanWriter = new StatefulBeanToCsvBuilder<GameData>(writer).build();
            beanWriter.write(allGames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }

        logger.info("Jocul a fost actualizat cu succes.");
    }


    public void deleteGame(String name) throws IOException, CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException {
        logger.info("Se executa metoda \"deleteGame()\".");
        List<GameData> allGames = getAllGames();
        GameData gameToBeDeleted = new GameData();
        for (GameData game : allGames) {
            if (game.getName().equalsIgnoreCase(name)) {
                gameToBeDeleted = game;
            }
        }
        allGames.remove(gameToBeDeleted);
        FileWriter writer = new FileWriter(fileName);
        StatefulBeanToCsv<GameData> beanWriter = new StatefulBeanToCsvBuilder<GameData>(writer)
                .build();
        beanWriter.write(allGames);
        writer.close();
        logger.info("Jocul \"" + name + "\" a fost sters cu succes.");
    }

    public void insert100Games() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Faker faker = new Faker();
        FileWriter writer = new FileWriter(fileName);
        StatefulBeanToCsv<GameData> beanWriter = new StatefulBeanToCsvBuilder<GameData>(writer)
                .build();
        List<GameData> games = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            GameData gameData = new GameData(faker.esports().game(), faker.random().nextDouble(),
                    faker.random().nextDouble(), faker.esports().league(), faker.programmingLanguage().name());
            games.add(gameData);

        }
        beanWriter.write(games);
        writer.close();
    }


}
