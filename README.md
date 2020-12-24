# Recreate a Classic Arcade Game
## Abstract
This project explores the feasibility in using programming languages, as opposed to pre-built game engine applications, to build games as a game developer. This hypothesis has been tested in a Java environment by attempting to recreate Bomberman, a classic 2D arcade game first released in Japan, 1985. This approach requires the components of the game engine to be hard-coded from scratch in order to allow for a functional game to run on top of it. Examples of these components include: using threads to imitate concurrency within a single-threaded environment, describing and handling physics for the purposes of managing collisions between custom objects, and designing the GameObject framework to provide an adaptable grouping of common object behaviours which conform to object-oriented programming design principles.

## Academic Prize
This project was awarded with the two10degrees academic prize for achieving the highest mark out of all the Computer Science and Electronic Engineering (CSEE) Final Year Projects at the University of Essex. Additional details can be found in this [two10degrees archived news article](https://www.two10degrees.com/news/archives/08-2020) which was published following the distribution of the prize.

## Getting Started
This project was developed using IntelliJ IDEA Version 2016 (an Integrated Development Environment or IDE) with the Java Standard Edition Development Kit (JDK) 8. Other IDEs and JDKs may be used in their places to run the program, yet these may produce unexpected bugs as the application has not been tested for compatibility outside of its original development environment. The contained Bomberman program is largely unfinished in both its game engine and the game itself, yet the program demonstrates the necessary functionality required to accurately test the hypothesis which is detailed in the above [Abstract section of this README file](#abstract).

### Prerequisites
- Java IDE (IntelliJ IDEA Version 2016 (or later) recommended)
- Java Standard Edition Development Kit (JDK) 8 (or later)

### Running the program
1. Clone this repository
2. Open the cloned repository project with a compatible Java IDE
3. Run `GameMain.java` to run the `main()` method of the program
4. The application will run and a new window will pop up where you can interact with game

### Game Overview
The basic objective of the game is to blow up the other players and survive to win as the last player standing. Players start in one of the four corners of the arena and will be required to drop bombs in order to blow up the many soft blocks which surround them. Blowing up these blocks will form a traversable path to your opponents and may sometimes reveal power-ups which can be picked up to strengthen your capabilities.

### Customisation
The controls of the game are defined in `GameKeys.java` and can be edited to better suit your preferences. The size of the game arena can also be customised by editing `TILE_RADIUS` constant within `Constants.java`. Debug components (such as bombs displaying a countdown timer and having a HUD during gameplay) are development mode components that are solely used for the purposes of manual testing and do not represent the core Bomberman experience. As the game is unfinished, these have been deliberately left in to add depth to the approaches in which this product can be enjoyed.

### Versioning Notes
This project was initially hosted on a university GitLab server whose project management was handled using a Jira Board that was also on a university server. The original GitLab repository was ported over to GitHub with the intent to allow for the public to access the project following its final submission. Having now graduated, I can no longer access the university server as I am no longer considered a student of the university. Henceforth, I have taken the decision to remove all references to the original GitLab repository and Jira Board. Additionally, this GitHub README file has since been revisited and refined to cater to a more generalised README format as opposed to the specified format that was required as part of the original university assignment.

## Authors
* Riaz Philippe - BSc Computer Games Graduate

## References
* [two10degrees News Article](https://www.two10degrees.com/news/archives/08-2020)
* [Originally Required Markdown Template](https://cseegit.essex.ac.uk/snippets/8)
