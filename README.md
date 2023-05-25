
# CryptGame
Demonstration od cryptographic problems through an interactive educational game

## Details
**Created by:
[Anezka Fisarova](https://github.com/xfisar02)**

Repository contains a cryptography game that was developed as part of a bachelor's degree project

The purpose of the project is to familiarize users with the basic issues of cryptography. The form of the computer game is illustrative and entertaining, so it is the best choice in this case. For this purpose, the Java programming language and the JavaFX framework are used, through which we develop the user interface. It is possible to choose the difficulty level, which will determine the complexity of the ciphers. At the same time, a score is stored for each player for successfully successfully cracked ciphers.

Uses Java and JavaFx.

## Features
- Three difficulties (EASY, MEDIUM, HARD)
- PC Console Themed
- Ciphers Available: Password Cracking, Morse Cipher, Caesar Cipher, Atbash Cipher, Polybius Cipher, Vigenere Cipher, Bifid Cipher
- Saves score and player name
- Unique hints for each cipher
- Switching between ciphers and menu
- Learning about ciphers in a fun way

## Implementation
- Navigation is via FXML files
- Menu is used to select the difficulty, enter a name for the player and view the score table
- Game is accessed by pressing the play button in the menu, which is unlocked after filling in the player's name and confirming by pressing enter
- According to the selected difficulty, 3 ciphers are automatically selected, which you can repeat until you return to the menu
- Player gets points for correctly solving the cipher
- If the player does not know what to do, there is a hint

## Screenshots from the game
![menu](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/menuDesign.PNG)
![passwordhack](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/PasswdCrackGame.png)
![morse](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/MorseGame.png)
![caesar](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/CaesarGame.png)
![atbash](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/AtbashGame.png)
![polybius](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/PolybiusGame.png)
![vigenere](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/VigenereGame.png)
![bifid](https://github.com/xfisar02/CryptGame/blob/main/GameScreenshots/BifidGame.png)

## How To play
### Executing the JAR File
To play the game, the following steps can be followed:
- An executable file is currently being prepared and will be uploaded later. In the meantime, the only option is to play directly in the development environment (InteliJ, Netbeans, ...). 
- Project is using JavaFX SDK version 20.0.1
