# Preferance

You can find a little game like Preferance here

# Dependencies
This program uses such libs as:

1) Log4j

# API`s
Once you created a "Game" object, you can use some API`s for writing needed information to .log file called Preferance.log
Here explanation of API methods:

1) getResultsOfGivingCardsInRound(int roundNumber) - method for writing to log all information about giving of starting cards in the 
specified round
2) getResultOfMerchencyInRound(int roundNumber) - method for writing to log all information about merchency process in the specified round
3) getProcessOfOrderingInRound(int roundNumber) - method for writing to log all information about ordering process in the specified round
4) getResultOfRound(int roundNumber) - method for writing to log results of round and player`s bullets
5) getFullProcessOfRound(int roundNumber) - method for writing to log all information about round (It simply uses first four methods)
6) getProcessOfGame() - method for writing to log all information about game (Works like 5th method, but for all rounds in game)
