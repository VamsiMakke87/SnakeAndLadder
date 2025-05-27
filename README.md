# Snake and Ladder Game

A robust, extensible, and scalable Java implementation of the classic **Snake and Ladder** board game designed with clean architecture and best practices. The game supports multiple players, customizable board sizes, dynamic dice types, and randomized snake and ladder placements.

---

## Table of Contents

* [Folder Structure](#folder-structure)
* [Design Patterns & SOLID Principles](#design-patterns--solid-principles)
* [Extensibility & Scalability](#extensibility--scalability)
* [How to Build and Run](#how-to-build-and-run)
* [Usage Instructions](#usage-instructions)
* [Future Enhancements](#future-enhancements)

---

## Folder Structure

```
src/
├── model/
│   ├── config/
│   │   └── GameConfig.java                  # Game settings/configuration
│   ├── dice/
│   │   ├── Dice.java                        # Dice interface
│   │   ├── DiceFactory.java                 # Factory to create dice instances
│   │   ├── DiceType.java                    # Enum defining dice types
│   │   └── impl/
│   │       ├── SixFacedDice.java            # Six-sided dice implementation
│   │       ├── EightFacedDice.java          # Eight-sided dice implementation
│   │       └── TenFacedDice.java            # Ten-sided dice implementation
│   ├── exception/
│   │   └── InvalidDiceTypeException.java   # Custom exception for invalid dice
│   ├── obstacle/
│   │   ├── Obstacle.java                    # Snake or Ladder entity
│   │   └── ObstacleType.java                # Enum for obstacle types (Snake/Ladder)
│   ├── Board.java                           # Singleton class managing game board and logic
│   └── Player.java                          # Player state and actions
└── Main.java                               # Application entry point, user interaction
```

### Explanation:

* The `model` package contains the core domain classes representing game entities.
* `config` abstracts game configuration parameters.
* `dice` encapsulates different dice implementations using interfaces and the factory pattern.
* `exception` handles specific error scenarios gracefully.
* `obstacle` models game elements like snakes and ladders clearly.
* Separation of concerns is maintained for modularity and maintainability.

---

## Design Patterns & SOLID Principles

### Key Design Patterns

* **Singleton Pattern (Board):**
  The `Board` class is a singleton to guarantee a single game board instance controlling game state and logic, avoiding inconsistent states during gameplay.

* **Factory Pattern (DiceFactory):**
  Abstracts dice creation logic, allowing easy addition of new dice types (e.g., 12-sided dice) without modifying the main codebase.

* **Strategy Pattern (Dice Interface):**
  Different dice types implement a common interface, enabling the game logic to use any dice interchangeably, facilitating flexible game rules.

### Adherence to SOLID Principles

* **Single Responsibility Principle:**
  Each class has a well-defined responsibility. For example, `Player` manages only player state, `Board` handles the game board and movements, and `DiceFactory` creates dice objects.

* **Open/Closed Principle:**
  The design is open for extension but closed for modification. New dice types or obstacles can be added by extending classes or enums without changing existing code.

* **Liskov Substitution Principle:**
  All dice implementations can replace the base `Dice` interface without affecting client code, ensuring substitutability.

* **Interface Segregation Principle:**
  The `Dice` interface defines minimal behavior, preventing bloated interfaces.

* **Dependency Inversion Principle:**
  High-level modules (game controller) depend on abstractions (`Dice` interface) instead of concrete implementations, promoting loose coupling.

---

## Extensibility & Scalability

* **Adding New Dice Types:**
  Implement the `Dice` interface with the new dice logic, register it in `DiceFactory`, and update the `DiceType` enum. This requires no changes to `Board` or player logic.

* **Adding New Obstacles:**
  Extend the `ObstacleType` enum and update obstacle generation logic to support new obstacle behaviors (e.g., teleporters).

* **Customizable Board Size and Player Count:**
  The `GameConfig` allows dynamic setting of board size and number of players, supporting scalable game sessions from small to large.

* **Randomized Game Board:**
  Snakes and ladders are randomly placed each game session to keep gameplay fresh and challenging.

* **Thread Safety:**
  The `Board` initialization is synchronized to prevent race conditions when initializing the singleton in a multi-threaded environment.

---

## How to Build and Run

### Prerequisites

* Java Development Kit (JDK) 11 or higher
* Git (optional, for cloning repository)
* Command-line terminal (Linux/Mac/Windows PowerShell or CMD)

### Clone the repository (if applicable)

```bash
git clone https://github.com/VamsiMakke87/SnakeAndLadder.git
cd SnakeAndLadder
```

### Compile the source code

From the project root directory (where `src/` is located), run:

```bash
javac -d out $(find src -name "*.java")
```

* `-d out` specifies output directory for compiled `.class` files.
* `find src -name "*.java"` collects all Java source files.

### Run the game

```bash
java -cp out Main
```

---

## Usage Instructions (Commands & Flow)

1. **Start Game**
   Launch the app via the above command. The game prompts for user input.

2. **Input Number of Players**
   Enter the total number of participants (Minimum 2 players).

3. **Input Player Names**
   Enter unique names for each player when prompted.

4. **Input Board Size**
   Enter the total number of cells on the board (commonly 100).

5. **Select Dice Type**
   Choose from the following dice types by typing their exact name:

    * `SIXFACED`
    * `EIGHTFACED`
    * `TENFACED`

6. **Input Number of Dice**
   Enter how many dice to roll per turn. The number must not exceed the board size.

7. **Gameplay**
   Players take turns rolling dice. The game prints:

    * Dice outcomes
    * Player movement including encounters with snakes or ladders
    * When a player reaches the final cell (winner)
    * When a player loses (last to finish)

8. **Game End**
   The game automatically ends after all but one player finishes. Winners and loser are announced.

---

## Future Enhancements

* **Graphical User Interface (GUI):**
  Implement a Swing/JavaFX or web-based frontend to replace console interaction.

* **Multiplayer Networking:**
  Enable remote players to connect and play over a network.

* **Configurable Obstacles:**
  Support custom obstacle configurations via external files.

* **Enhanced Game Rules:**
  Add power-ups, penalty squares, or timed moves.

* **Persistence:**
  Store game state for pause/resume functionality.

---
