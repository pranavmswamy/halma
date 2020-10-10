# Halma AI - Adversarial Search

Adversarial search is a search where we examine the problem which arises when we try to plan ahead of the world and other agents are planning against us. So, searches in which two or more players with conflicting goals are trying to explore the same search space for the solution, are called adversarial searches, often known as Games. Games are modeled as a *Search problem and heuristic evaluation function*, and these are the two main factors which help to model and solve games in AI.

Although by using the above approach, it is theoretically possible to win any adversarial search based game like Chess, Tic-Tac-Toe, Backgammon, Chinese Checkers, Halma, etc, it is in most cases not feasible to implement and generate the entire search tree. Chess has an average branching factor of about 35,
and games often go to 50 moves by each player, so the search tree has about 35^100 or 10^154 nodes (although the search graph has “only” about 10^40 distinct nodes.). Due to this, limitation of adversarial search, we can only computationally search a few levels deep, and take the next best move.
