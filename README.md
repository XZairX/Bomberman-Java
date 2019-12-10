# Recreate a Classic Arcade Game
The project I have chosen to undertake is Michael Fairbank's "Recreate a Classic Arcade Game" where I am given the freedom to recreate a classic 2D arcade game from the 80s or 90s. The challenge from this task comes from the requirement of having to code everything myself, including the engine that the game is to run on. Unity, a popular "high-powered" game engine, is explicitly forbidden unless the project has a enough high complexity to warrant its use. The classic arcade game I have chosen is Bomberman (1985), a strategic maze-based franchise whose multiplayer components withstand the test of time. As I could not attain permission to use Unity despite having never used it before, I opted to use Java instead, as it is the programming language that I am most comfortable and willing to experiment with. At the time of writing, the game engine is functional with proper game structure data types present and collision detection/handling, but gameplay has yet to be implemented.


## Getting Started
In order to get the project running, you will need to have IntelliJ IDEA installed on your computer. This application is a Java IDE (integrated development environment) that allows for the development of computer software. More importantly, it allows for the viewing and manipulation of the Java program that this project is contained within. IntelliJ is provided by JetBrains, a certified company who specialise in distributing developer tools for professionals and teams. As the application is distributed as open-source software, it can be freely downloaded from JetBrain's official website without concern for malicious malware.


### Prerequisites
* IntelliJ IDEA Version 2016 and later
* Java JDK 8 or later


### Installing and setting up IntelliJ IDEA
1. Navigate to the [official JetBrains official website](https://www.jetbrains.com) and select the IntelliJ-based IDEs option to be redirected to the [IntelliJ-based IDE page](https://www.jetbrains.com/products.html#type=ide).

2. Select the IntelliJ IDEA option on this page to once again be redirected this time to the main [IntelliJ IDEA page](https://www.jetbrains.com/idea).

3. Select the "DOWNLOAD" option to be further redirected to the [IntelliJ download page](https://www.jetbrains.com/idea/download/#section=windows). Here, two versions are listed: Ultimate and Community. A list of features is present on this page which highlight the differences between the two editions. Also take note that there are different versions of IntelliJ for compatibility with different operating systems. The page provided is for downloading IntelliJ on a Windows operating system and this will need to be changed if not using a Windows computer. For the purposes of viewing the project, you should select the Community version which can be acquired for free.

4. Download and save the binary .exe file, run it, and follow the instructions given to install the application.


### Downloading Java JDK
The Java Standard Edition Development Kit (JDK) is a collection of tools which provides IntelliJ with the necessary configurations to allow for it to work with Java programs. It can be downloaded from the [official Oracle website](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). Note that this provided link is only for downloading JDK 8. If you wish to use a newer version, you will need to navigate the website to find the appropriate page. The specific download required is entirely dependent on your operating system and your computer architecture (x86 vs x64). You **must** accept the Oracle Technology Network License Agreement for Oracle Java SE to download the JDK. Newer versions of the JDK support a limited but functional sense of backwards-compatibility with JDK 8.


#### Setting up Java JDK
It is highly recommended to follow the link below for understanding how to configure the JDK to cohesively work with IntelliJ and Java.\
https://www.jetbrains.com/help/idea/sdk.html


#### Setting up and running the program
1. Navigate to the project repository, open the "src" directory and use the download button at the top-right to convert and download the current directory as a .ZIP file.

2. Extract (unzip) the contents and locate the two package folders "game" and "utilities" within the "src" folder.
 
3. Create and open a new project in IntelliJ and drag these two folders into your "src" directory within the project view sidebar on the left.

4. If you have correctly set up the Java JDK and you have correctly transferred the extracted files, you will now be able to view and run the game by running the GameMain class.


### Versioning Strategy
There is no specific versioning strategy being used for this project. Tasks relating to the project are uploaded and reviewed on Jira on a weekly basis. The development time of the project pertaining to these tasks is committed, pushed, and annotated as it progresses until the task is completed. Completed tasks are marked as done in Jira with a relevant comment provided to give detail on the work done and this process is repeated for every task created.

For every week that passes, my project supervisor and I hold a short meeting to reflect on the status of the project. This normally involves the discussion of development in terms of what had been done, the general progression of workflow, and what is to be done next. In relation to Jira, this involves looking at which Jira tasks have been completed, which remain, and what new tasks could be potentially created for the future. A supervisor feedback issue is created and marked as done for each week that these meetings are held to provide an overview of our discussions.


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

