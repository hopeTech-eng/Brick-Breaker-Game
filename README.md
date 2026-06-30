<div align="center">

<br/>

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/UI-Swing-4F9CFF)
![Platform](https://img.shields.io/badge/Platform-NetBeans-1B6AC6?logo=apache-netbeans-ide&logoColor=white)
![Status](https://img.shields.io/badge/Status-Playable-7ee36b)


A classic Breakout-style arcade game built in Java using Swing.
Bounce the ball off your paddle, smash every brick, don't let it fall past you.

</div>

---

<div align="center">
  <img src="assets/gameplay-preview.svg" alt="Gameplay preview" width="320"/>
  <br/>
  <sub><i>Layout shown matches the real brick grid, paddle, and HUD positions in <code>GamePanel.java</code></i></sub>
</div>

---

## 🚀 How to Run

### NetBeans
1. Open the project folder in NetBeans (**File → Open Project**), or create a new Java Application project and copy these files into the default package.
2. Make sure `Main` is set as the project's main class (**Project Properties → Run**, if NetBeans doesn't detect it automatically).
3. Press **F6** to run.

### Command line
```bash
javac *.java
java Main
```

## 🎮 Controls

| Key | Action |
|:---:|---|
| `←` `→` | Move the paddle left / right |
| `Space` | Start the game |
| `R` | Restart — works any time, mid-game or after win/loss |

## 📋 How to Play

- 🟡 Press **Space** to launch the ball from the start screen.
- 🏓 Move the paddle to keep the ball in play.
- 🧱 Break every brick to win.
- ❤️ You have **3 lives** — losing the ball past the paddle costs one life and resets the ball to center. Lose all 3 and it's game over.
- 🎯 Where the ball hits the paddle changes its bounce angle — off-center hits send it off sharper, just like the classic arcade originals.

## 🗂️ Project Structure

```
brickbreaker/
├── Main.java              # Entry point — launches the game window
├── GameFrame.java         # JFrame window that hosts the game
├── GamePanel.java         # Core engine: game loop, input, collisions, rendering, state
├── Paddle.java            # Player-controlled paddle
├── Ball.java              # Ball position, velocity, and movement
├── Brick.java             # Individual brick (position, color, visibility)
└── assets/
    ├── banner.svg
    └── gameplay-preview.svg
```

| File | Responsibility |
|---|---|
| `Main.java` | Starts the program. Contains only `main()`. |
| `GameFrame.java` | Creates and configures the application window. |
| `GamePanel.java` | Everything that makes the game run: the `Timer`-driven game loop, keyboard handling, collision detection, scoring, and win/lose/start states. |
| `Paddle.java` | Tracks the paddle's position and draws it; clamps movement to stay on screen. |
| `Ball.java` | Tracks the ball's position and velocity; moves itself each tick. |
| `Brick.java` | A single brick: position, color, and whether it's still visible. |

## ⚙️ How It Works

The game runs on a Swing `Timer` firing roughly every 8 milliseconds. Each tick:

1. **Move** — update the paddle's and ball's positions.
2. **Check collisions** — resolve ball-vs-wall, ball-vs-paddle, and ball-vs-brick overlaps using rectangle intersection (`Rectangle.intersects()`).
3. **Repaint** — redraw the screen.

The ball doesn't move until `Space` is pressed (controlled by a `started` flag), and the whole game resets cleanly on `R` via `initGame()`.

📖 For the full breakdown — build order, architecture, and the math behind collisions and the paddle bounce angle — see [`brick-breaker-plan-and-explanation.md`](./brick-breaker-plan-and-explanation.md).

## 🛠️ Customizing

A handful of constants at the top of `GamePanel.java` control the game without touching any logic:

| Constant | Effect |
|---|---|
| `ROWS` / `COLS` | Number of brick rows/columns |
| `BRICK_WIDTH` / `BRICK_HEIGHT` / `BRICK_GAP` | Brick size and spacing |
| `BRICK_TOP_OFFSET` | Vertical space above the brick grid (leaves room for the HUD) |
| `DELAY` | Game loop speed in milliseconds — lower = faster game |

Paddle speed and ball starting speed are set where `Paddle` and `Ball` are constructed in `initGame()`.

## 🔭 Possible Next Steps

- 🎁 Power-ups (multi-ball, wider paddle, slower ball) dropping from broken bricks
- 🧩 Multiple levels with different brick layouts
- ⏸️ A proper start/pause menu instead of a single text prompt
- 🔊 Sound effects on brick break, paddle hit, and game over

## 📦 Requirements

- Java 8 or later — uses only standard `javax.swing` and `java.awt`, no external dependencies

---

<div align="center">
<sub>Built with Java Swing • No external libraries • No frameworks, just <code>java.awt.Rectangle</code> doing all the work</sub>
</div>
