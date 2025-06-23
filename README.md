OOP_PROJECT - JAVA GAME COLLECTION
==================================

Description
-----------
This project is a collection of simple games developed in Java using Object-Oriented Programming (OOP) principles. Each version (Game_v1 to Game_v5) represents either a stage of improvement or a different game entirely, including Sudoku and Snake.

Date
-----------------
May 2024 - June 2024

Project Structure
-----------------
```plaintext
/src
├── Game_v1/
│   ├── Sudoku.java          # Basic Sudoku logic
│   └── SudokuGame.java      # Main class and UI for Sudoku v1
│
├── Game_v2/
│   ├── Answer.java          # Handles Sudoku answers
│   ├── EZ.java              # Easy difficulty level
│   ├── Mid.java             # Medium difficulty
│   ├── Hard.java            # Hard difficulty
│   ├── Hint.java            # Hint functionality
│   ├── Reset.java           # Reset the game
│   ├── Solve.java           # Auto-solve the Sudoku board
│   ├── SudokuFrame.java     # Main UI for Sudoku v2
│   └── SudokuTest.java      # Test class for Sudoku
│
├── Game_v3/
│   └── SudokuGame.java      # Improved version of Sudoku game
│
├── Game_v4/
│   ├── Panel.java           # Game panel for basic Snake
│   └── SnakeGame.java       # Simple Snake game
│
├── Game_v5/
│   ├── Food.java            # Handles food logic
│   ├── Snake.java           # Snake movement and collision
│   ├── GamePanel.java       # Panel with game logic and events
│   └── Game.java            # Main launcher for enhanced Snake
```
Requirements
------------
- Java Development Kit (JDK) 8 or higher
- A Java IDE such as IntelliJ IDEA or Eclipse (recommended)

How to Run
----------
1. Download the project: https://github.com/Thnh01/OOP_Project

2. Open the project in your preferred Java IDE.

3. Run the main class depending on the game you want to play:
   - `Game_v1/SudokuGame.java` or  
   - `Game_v2/SudokuFrame.java` for Sudoku  
   - `Game_v4/SnakeGame.java` or `Game_v5/Game.java` for Snake

4. You can modify difficulty levels or add new features as you wish.

Learning Objectives
-------------------
- Practice core OOP concepts: inheritance, abstraction, encapsulation, and polymorphism.
- Learn Java Swing for GUI development.
- Develop small-scale games to understand event handling and game logic.

Author
------
Ton Duy Thanh - ITCSIU21234

GitHub: https://github.com/Thnh01

Notes
-----
- This project is intended for educational purposes and OOP practice.
- Each version demonstrates an improvement in game logic or user interface.
- Feel free to fork the project and enhance it with features like save/load, Sudoku solver AI, or Snake power-ups.
