# Recreate a Classic Arcade Game
## Abstract
This project explores the feasibility in using programming languages, as opposed to pre-built game engine applications, to build games as a game developer. This hypothesis has been tested in a Java environment by attempting to recreate Bomberman, a classic 2D arcade game first released in Japan, 1985. This approach requires the components of the game engine to be hard-coded from scratch in order to allow for a functional game to run on top of it. Examples of these components include: using threads to imitate concurrency within a single-threaded environment, describing and handling physics for the purposes of managing collisions between custom objects, and designing the GameObject framework to provide an adaptable grouping of common object behaviours which conform to object-oriented programming design principles.


## Introduction
This repository holds the work produced from a practical research investigation which is detailed in the abstract above. It is worth mentioning that this project was developed using IntelliJ IDEA Version 2016 with the Java Standard Edition Development Kit (JDK) 8. Other Java IDEs may be used in its place to run the program, but it is currently unknown if any compatibility issues would arise from doing so. The contained Bomberman program is largely unfinished in both its game engine and the game itself, yet the two demonstrate the necessary functionality to accurately test the above hypothesis.


## Academic Prize
This project was awarded with the two10degrees academic prize for achieving the highest mark out of all the Computer Science and Electronic Engineering (CSEE) Final Year Projects at the University of Essex. Additional details can be found in this [two10degrees archived news article](https://www.two10degrees.com/news/archives/08-2020) which was published following the distribution of the prize.


### Prerequisites
- Java IDE (IntelliJ IDEA Version 2016 (or later) recommended)
- Java Standard Edition Development Kit (JDK) 8 (or later)


### Running the program
After cloning the repository and opening it with a compatible IDE, run ```GameMain.java``` to run the application.

1. Navigate to the GitLab project repository, open the "src" directory and use the download button at the top-right to convert and download the current directory as a .ZIP file.

2. Extract (unzip) the contents and locate the two package folders "game" and "utilities" within the "src" folder.
 
3. Create and open a new project in IntelliJ and drag these two folders into your "src" directory within the project view sidebar on the left.

4. If you have correctly set up the JDK and you have correctly transferred the extracted files, you will now be able to run and interact with the game by running the GameMain class.


### Game Overview
The basic objective of the game is to blow up the other players and survive to win as the last player standing. Players start in one of the four corners of the arena and will be required to drop bombs in order to blow up the many soft blocks which surround them. Blowing up these blocks will form a traversable path to your opponents and may sometimes reveal power-ups which can be picked up to strengthen your capabilities.


### Customisation
The controls of the game are defined in the GameKeys class and can be edited to better suit your preferences. Debug commands are developer actions that are used for the purposes of testing and do not represent the core Bomberman experience. As the game is unfinished, these have been deliberately left in to add depth to the approaches in which this product can be enjoyed. The size of the game arena can also be customised by editing the TILE_RADIUS constant within the Constants class.


### Versioning Strategy
There is no specific versioning strategy that was used for this project. Project management is solely handled by Jira where tasks are uploaded and reviewed on a weekly basis. The development time pertaining to a task is committed, pushed, and annotated to GitLab as progress is made until the task satisfies its completion requirements as determined by its given description. Completed tasks are marked as done in Jira with a relevant comment provided to give detail on the work done and this process repeats for every task that is created.


## Authors
* Riaz Philippe - University of Essex BSc Computer Games Undergraduate
Notes(originally stored in GitLab/Jira. No longer existing. Mirror of original repo. Refine)

## References
* [two10degrees News Article](https://www.two10degrees.com/news/archives/08-2020)
* [Markdown Template](https://cseegit.essex.ac.uk/snippets/8)
* [JetBrains Website](https://www.jetbrains.com)
* [IntelliJ-based IDEs](https://www.jetbrains.com/products.html#type=ide)
* [IntelliJ Download (Windows)](https://www.jetbrains.com/idea/download/#section=windows)
* [IntelliJ SDK Help](https://www.jetbrains.com/help/idea/sdk.html)
* [Oracle JDK Download](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [GitLab Project Repository](https://cseegit.essex.ac.uk/ce301_2019/ce301_philippe_r)
* [Jira Project Repository](https://cseejira.essex.ac.uk/secure/Dashboard.jspa)

