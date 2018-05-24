# COMP401
COMP 401 Sushi Project

A9_3.jar is an importable jar file containing the source files for this java project. The game is run from src/sushigame.game/SushiGame.java 

This project is a game involving Sushi chefs competing against each other at a restaurant to see who can sell the most sushi. I developed it as part of my assignments for COMP401 at the University of North Carolina at Chapel Hill. A description of the game is included below:

At the sushi restaurant Kurama near Franklin street, Sushi plates are placed on a rotating belt around which customers sit. Customers can select sushi from the belt and eat it immediately. The color of the plate (red, green, blue, gold) indicates the price of the sushi ($1, $2, $3, $5). When you are done eating, you pay for the stack of plates in front of you, based on their color.

This game involves a similar belt-style sushi restaurant structure. The player plays as a chef, and 3 other chefs are randomly generated for the player to compete against. The belt is represented by the grey vertical column in the middle. The belt has 20 spaces on it, each space can hold a plate. 4 (invisible) customers are evently placed around the belt, at designated spaces. The belt rotates when the player clicks "Rotate".

Before clicking rotate, the player can construct a plate of sushi using the "Create Your Plate Here" area. One plate can be created per rotation. When the player clicks "rotate", the opponent chefs all create respective plates, the belt rotates by one, and the customers evaluate the plate in front of them, and may decide to eat it. 

Plates contain a Sushi object, which is some arrangement of ingredients. Each possible ingredient has a weight and cost (the amount of money the restaurant spends to obtain it) associated with it. Therefore, each plate has a food weight and cost associated with it, as well as a price (the amount of money the customer pays for the sushi) associated with it. The Plate can spoil depending on how long it has been on the belt and what type of ingredients it has.

Since each plate is associated with the chef that made it, the total profits made by each chef, the total food weight sold by each chef, and the total food put made by each chef that spoiled can all be calculated. This information is represented by the scoreboard on the left.
