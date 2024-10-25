# Java Game Data CRUD Operations

## Description
This project is a Java application that demonstrates CRUD (Create, Read, Update, Delete) operations on game data stored in a CSV file. The application uses OpenCSV for reading from and writing to the CSV file and JavaFaker for generating dummy game data.

## Subject
The application allows users to interact with game data by displaying all games, inserting a new game, searching for a game by name, updating game details, and deleting a game by name.

## Author's Note
I added functionalities with constant parameters for creating and deleting the CSV file and populating it with sample game data.

## Requirements
- Java Development Kit (JDK) version 8 or later
- Maven for building the project

## Setup
1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/your-repo-name.git
   cd your-repo-name
   
## Features
CRUD operations on game data stored in a CSV file.

Provides feedback when the game name does not exist in the CSV file.

Classes and Methods
Main
Purpose: Main entry point of the application. Contains logic to display the main menu options and handle user inputs.

Key Methods: main(String[] args), appMenu(), readGameFromConsole(Scanner scanner), getGameByGameName(Scanner scanner), deleteGameByName(Scanner scanner), updateGameByName(Scanner scanner, GameDataService gameDataService)

GameData
Purpose: Represents the game entity with the following attributes:

name: String

price: double

diskSpace: double

type: String

platform: String

GameDataService
Purpose: Contains methods for performing CRUD operations on the game data stored in the CSV file.

Key Methods:

getAllGames(): Retrieves all games from the CSV file.

displayAllGames(): Displays all games from the CSV file.

writeGameToFile(GameData gameData): Writes a new game to the CSV file.

getGameByName(String name): Retrieves a game by name from the CSV file.

updateGameDetails(GameData updatedGameData): Updates game details in the CSV file.

deleteGame(String name): Deletes a game by name from the CSV file.

insert100Games(): Inserts 100 dummy games into the CSV file using JavaFaker.

Maven Configuration (pom.xml)
Purpose: Defines the project's dependencies and build configuration.

Key Dependencies:

opencsv: Library for reading from and writing to CSV files.

lombok: Library for reducing boilerplate code in Java.

javafaker: Library for generating dummy data.

**How to Use**
Run the application: Follow the setup instructions and run the Main class.

Perform CRUD operations: The application will prompt you for inputs to perform various CRUD operations on the game data stored in the CSV file.

**Author**
Ene Bogdan Country: Romania