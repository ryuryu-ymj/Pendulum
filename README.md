# Pendulum
Java 2D action game like Spiderman.  
## How to play
1. First, title "Pendulum" is displayed, so click or push spacekey.
2. And, stage number is displayed, so click or push spacekey again.
3. Then, the game is began. 
## Description
* **Player** : the orange ball in the display.
* **Joint** : the blue ball. If you click a joint, you can extend a wire from the player to the joint,
and the player moves like **Pendulum**.  
Also if you push spacekey while you are extending the wire, you can pull the wire,
and the player speeds up.  
    -Other type of joints-  
  * Limit joint : joint surrounded by a big circle. You can catch the limit joint only within the circle.
  * Item joint : joint including item. If you loop around this joint, you can get the item.
  * Enemy joint : joint shooting bullets. If you loop around this joint, you can defeat the enemy.
* **Ground** : the green line.
* **Item**
  * Coin : if you collect 10 coins, you can get one heart.
  * Heart : if you get a heart, you can gain hearts. 
  If you die, you lose one heart. If you lose all your hearts, you are GameOver.
* **Goal** : if you see a red joint, that is the goal. Try to catch the joint.
