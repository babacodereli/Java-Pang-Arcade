# Java Pang Arcade

A classic arcade shooter game clone (inspired by "Pang" / "Buster Bros") developed in **Java** using the **Swing** library. This project features a complete user authentication system, high-score tracking, and level-based gameplay physics.

## 🎮 Features

* **Classic Gameplay:** Players shoot harpoons vertically to pop bouncing balloons.
* **User System:** Fully functional **Login** and **Register** system. Users must log in to play.
* **High Scores:** Scores are saved locally in CSV format and displayed in a sorted "Top 10" leaderboard.
* **Data Persistence:** User credentials and game history are stored permanently in `data/users.csv` and `data/scores.csv`.
* **Physics Engine:** Custom gravity and collision logic for balloons and projectiles.
* **GUI:** Built with Java Swing components (JFrame, JPanel, JMenuBar) for a responsive desktop interface.

## 📸 Screenshots

*(You can add screenshots of your game here later)*

## 🛠️ Tech Stack

* **Language:** Java (JDK 8+)
* **GUI Framework:** Java Swing & AWT
* **File I/O:** CSV file handling for persistence
* **Asset Management:** Custom image loading for sprites and backgrounds

## 🚀 Installation & How to Run

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
    cd your-repo-name
    ```

2.  **Compile the Project:**
    Make sure you have the Java Development Kit (JDK) installed.
    ```bash
    javac -d . *.java
    ```

3.  **Run the Game:**
    Since the project uses the `term_project` package:
    ```bash
    java term_project.GameWindow
    ```

## 🕹️ Controls

* **⬅️ Left Arrow:** Move Character Left
* **➡️ Right Arrow:** Move Character Right
* **spacebar Space:** Fire Weapon (Shoot Harpoon)


## ⚙️ Configuration

You can tweak game settings in `GameSettings.java`:
* **FPS:** Adjust the frame rate.
* **GRAVITY:** Modify the balloon physics.
* **WINDOW_WIDTH/HEIGHT:** Change the game resolution.

## 👥 Authors

**BERK DERELİ**
