# Recreate a Classic Arcade Game
This repository holds the work produced from a practical research investigation which explores the feasibility in using programming languages, as opposed to pre-built game engine applications, to build and produce games as a game developer. This work is contained within a Java program which simply runs a basic recreation of Bomberman, a classic 2D arcade game first released in 1985. Whilst the written game engine and the main game itself are both largely unfinished, the two demonstrate the necessary functionality required to accurately test the study.


## Getting Started
IntelliJ IDEA is required to be installed on a computer system in order to run the project. This application is a Java integrated development environment (IDE) that allows for the development of computer software. More importantly, it is the interface that is used to the view and manipulate the Java program that this project is contained within. IntelliJ is provided by JetBrains, a certified company who specialise in distributing developer tools for professionals and teams. The application is distributed as open-source software which can be freely downloaded from JetBrains' official website without concern for malicious malware.


### Prerequisites
* IntelliJ IDEA Version 2016 (or later)
* Java Standard Edition Development Kit (JDK) 8 (or later)


### Installing and setting up IntelliJ IDEA
1. Navigate to the [official JetBrains official website](https://www.jetbrains.com) and select the IntelliJ-based IDEs option to be redirected to the [IntelliJ-based IDE page](https://www.jetbrains.com/products.html#type=ide).

2. Select the IntelliJ IDEA option on this page to once again be redirected this time to the main [IntelliJ IDEA page](https://www.jetbrains.com/idea).

3. Select the "DOWNLOAD" option to be further redirected to the [IntelliJ download page](https://www.jetbrains.com/idea/download/#section=windows). Here, two versions are listed: Ultimate and Community. A list of features is present on this page which highlight the differences between the two editions. Also take note that there are different versions of IntelliJ to provide a range of compatibility for different operating systems. The link provided is for downloading IntelliJ on a Windows operating system and this will need to be changed by manually navigating the IntelliJ IDEA page if using a different operating system. For the purposes of viewing the project, you should select the Community version which can be freely acquired without any hassle.

4. Download and save the binary .exe file, run it, and follow the instructions given to install the application.


### Downloading Java SE Development Kit 8
The Java Standard Edition Development Kit (JDK) is a collection of tools which provides IntelliJ with the necessary configurations to allow for it to work with Java programs. It can be downloaded from the [official Oracle website](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). Note that this provided link is only for downloading JDK 8. If you wish to use a newer version, you will need to navigate the website to find the appropriate page. The specific download required is entirely dependent on your operating system and your computer architecture (x86 vs x64). You **must** accept the Oracle Technology Network License Agreement for Oracle Java SE to download the JDK. Newer versions of the JDK support a limited but functional sense of backwards-compatibility with JDK 8.


#### Setting up the JDK
It is highly recommended to follow the link below for understanding how to configure the JDK to work in cohesion with IntelliJ and Java.
https://www.jetbrains.com/help/idea/sdk.html


#### Setting up and running the program
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


## References
* [Markdown Template](https://cseegit.essex.ac.uk/snippets/8)
* [JetBrains Website](https://www.jetbrains.com)
* [IntelliJ-based IDEs](https://www.jetbrains.com/products.html#type=ide)
* [IntelliJ Download (Windows)](https://www.jetbrains.com/idea/download/#section=windows)
* [IntelliJ SDK Help](https://www.jetbrains.com/help/idea/sdk.html)
* [Oracle JDK Download](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [GitLab Project Repository](https://cseegit.essex.ac.uk/ce301_2019/ce301_philippe_r)
* [Jira Project Repository](https://cseejira.essex.ac.uk/secure/Dashboard.jspa)

