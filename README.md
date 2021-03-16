<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
***
***
***
*** To avoid retyping too much info. Do a search and replace for the following:
*** github_username, repo_name, twitter_handle, email, project_title, project_description
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Online gaming is an enormous industry that grows each year. As the audience for online multiplayer games has gotten bigger, the technology to meaningfully match these players has lagged behind. Often times players, especially in competitive settings, are matched using game metrics and statistics in order to have players of a similar skill caliber play together. As this has become commonplace, players find themselves matched with people who appear only as a username. With a lack of personal connection, some players engage in negative communication which results in a lower quality gaming experience for all.

This team project aims to resolve existing matchmaking issues by matching players on criteria beyond simply skill. The end product will collect more information about a player, including parameters related to play-style, preferences and attitude. Thus, it will reintroduce a human component to online teammates and help to brighten a gamer’s horizon with improved gaming experiences.

### Built With

* [Discord](https://www.discord.com)
* [Github](https://www.github.com)
* [Trello](https://trello.com/en-US)
* [Android Studio](https://developer.android.com/studio)
* [Firebase](https://firebase.google.com/)

### Product Vision (Near and Far Vision Statements)
Online gaming has become a major form of entertainment in recent years. Players get online to play their favorite games with either strangers or friends. Online games currently tend to provide matchmaking criteria to ensure that the player is matched with players of similar rank or skill. However, there lacks a human element when playing with strangers as games tend not to provide information about other players rather than rank. This can lead to no or rather negative communication between players and rather cause a negative experience with the game itself.   
The goal of this team is to address this negative environment by creating a new online matchmaking system that will provide a more positive and healthy experience during online gaming. Using this system, online games will now be able to match players based on play-style, preferences, attitude as well as skill. The system shall be designed through an Android mobile app that players can access on their phones. The future vision of the app is that after the development of the Android mobile app is complete, the company wishes to expand the scope of the mobile app to include Apple products as well.


### Our Definition of 'Ready'
Our definition of Ready follows the INVEST criteria.

**I**ndependent Each backlog item is self-contained in a way that it can be implemented in a single sprint as a minimum viable product.   
**N**egotiable Regular sprint meetings are held before, during, and at the conclusion of sprints in order to determine optimal implementation for current and future sprints.   
**V**aluable The stakeholders understand the value of the backlog item and the project owner has determined the priority of the backlog item.   
**E**stimable The size of the backlog item has been estimated based upon past experience with delivering similar products. A story point consists of 30 minute segments.   
**S**mall The backlog item is small enough to be completed within the time frame of one sprint which lasts no more than 2 weeks.   
**T**estable A set of test criteria has been determined through the use of JUnit testing. 

### Rationale for Backlog Order
The backlog items were ordered based upon critical systems first. The database stores all the user information, which requires the Database System be developed first (User Story #13). Next, a User Login System (User Story #6) is needed to allow users to log into the system followed by the Account Management System (User Story #7) whic is needed to allow users to view and modify the data in their accounts. Finally, the Player Profile system (User Story #16) is needed as it is the foundation of the MatchMaking System which will come next in a parallel task with the remaining items (User Story #8). Once the critical systems are are in place, systems related to connecting players with friends will be next up. These are Player Searching (User Story #2), Adding Friends (User Story #3), and Friend Sorting (User Story #9). Final items listed in the backlog is Group Forming (User Story #5) and Player Identity (User Story #18).

### Initial Backlog Items
Database System
User Login
Account Management
Player Profile
Matchmaking
System 
Player Searching
Adding Friends
Friend Sorting
Group Forming
Player Identity




<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites
* Git (v2.26 at least)
* GitClient / Source Control Tool
* Android Studio
* Script Editor (Visual Studio Code preferred) 

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/JohnMurwin/SWE6733T2-GamingBehavioralMatchmakingSystem.git
   ```
2. Initialize GitFlow using:
   ```sh
   Master Branch: main  
   Develop Branch: dev
   Feature Prefix: feature/
   Release Prefix: release/
   Hotfix Prefix: hotfix/
   Version Tag Prefix: v
   ```
   _Note:_ If you are not utilizing Command Line, or a Client that pre-locks GitFlow you will need to keep the above naming convention in mind as you will need to manually create your branch names following this pattern.


<!-- USAGE EXAMPLES -->
## Usage

Follows standard Android Studio developer guidelines.

_For examples, please refer to the Android Developer Design Reference: [Android Developer Reference](https://developer.android.com/design)_



<!-- ROADMAP -->
## Roadmap

See the [Trello Board](https://trello.com/b/Ciy6lBaC/agile-sprint-board) for a list of milestones, features, and assigned issues. Additionaly, known issues will be tracked via the Github Issues system which you can access [here](https://github.com/SWE-Spring2020-Team2/SWE6733T2-GamingBehavioralMatchmakingSystem/issues). 



<!-- CONTRIBUTING -->
## Branch Workflow

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Clone the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request using Standard PR Template. 


<!-- LICENSE -->
## License

Apache License, General Distribution, John Murwin: 2021. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

John Murwin: Lead Developer - [@JohnMurwin](https://twitter.com/JohnMurwin) - john.murwin@gmail.com / jmurwin@students.kennesaw.edu

Christopher Baxter: Documentation & Software Testing - [@]() - baxtercrb9@gmail.com

Bui Tu: Developer  - [@]() - Tlaaron104@gmail.com

Thade Huichapa: Developer - [@]() - thaidehuichapa@gmail.com

L. Renee: Scrum Master - [@]() - ldavisto@students.kennesaw.edu


Project Link: [https://github.com/JohnMurwin/SWE6733T2-GamingBehavioralMatchmakingSystem](https://github.com/SWE-Spring2020-Team2/SWE6733T2-GamingBehavioralMatchmakingSystem)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* [@Othneildrew](https://github.com/othneildrew)
* [@stevemao](https://github.com/stevemao)
* [@Allar](https://github.com/Allar)



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo.svg?style=for-the-badge
[contributors-url]: https://github.com/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo.svg?style=for-the-badge
[forks-url]: https://github.com/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo/network/members
[stars-shield]: https://img.shields.io/github/stars/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo.svg?style=for-the-badge
[stars-url]: https://github.com/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo/stargazers
[issues-shield]: https://img.shields.io/github/issues/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo.svg?style=for-the-badge
[issues-url]: https://github.com/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo/issues
[license-shield]: https://img.shields.io/github/license/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo.svg?style=for-the-badge
[license-url]: https://github.com/SWE-Spring2020-Team2/GamingBehavioralMatchmakingSystem/repo/blob/master/LICENSE
