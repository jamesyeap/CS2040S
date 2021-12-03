/*	PSEUDO-CODE

SET nSyllables = s;
SET nHands = n;  

INIT players[] of length (n);
INIT playerHands[] of length (n * 2);

INIT currHandPos = 0;
COMPUTE selectedHand = (currHandPos + nSyllables - 1) mod nHands;

WHILE players.length > 1
	IF playerHands[selectedHand] is "FOLDED"
		SET playerHands[selectedHand] to "FIST"
		SET playerHands[selectedHand + 1] to "FIST"

		INCREMENT nHands by 1;
		SET currHandPos to selectedHand;
		COMPUTE selectedHand = (currHandPos + nSyllables - 1) mod nHands;

	IF playerHands[selectedHand] is FIST
		SET playerHands[selectedHand] to "PALM DOWN"

		SET currHandPos to (selectedHand + 1) mod nHands;
		COMPUTE selectedHand = (currHandPos + nSyllables - 1) mod nHands;

	IF playerHands[selectedHand] is "PALM DOWN"
		SET playerHands[selectedHand] to "BEHIND BACK"

		IF playerHands[selectedHand + 1] or playerHands[selectedHand - 1] 
			(belongs to the same player) AND (is also "BEHIND BACK")
			REMOVE this player from players[]
			
		DECREMENT nHands by 1;
		SET currHandPos to (selectedHand + 1) mod nHands;
		COMPUTE selectedHand = (currHandPos + nSyllables - 1) mod nHands;

PRINT players[0].name;

*/

import java.util.Scanner;
import java.util.ArrayList;

class Hand {
	public int playerId; 
	public int state; 

	public Hand(int playerId) {
		this.playerId = playerId;
		this.state = 0;
	}

	public Hand(int playerId, int state) {
		this.playerId = playerId;
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format("%d", this.state);
	}
}

public class Coconut {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nSyllables = sc.nextInt();
		int nPlayers = sc.nextInt();

		ArrayList<Hand> allHands = new ArrayList<Hand>();
		for (int i=0; i<nPlayers; i++) {
			allHands.add(new Hand(i));
		}

		int currHandPos = 0;

		while (nPlayers > 1) {
			int handHitPos = (currHandPos + nSyllables - 1) % allHands.size();
			Hand handHit = allHands.get(handHitPos);

			if (handHit.state == 0) { // if hand is FOLDED
				handHit.state = 1;
				allHands.add(handHitPos+1, new Hand(handHit.playerId, 1));
				currHandPos = handHitPos;

			} else if (handHit.state == 1) { // if hand is FIST
				handHit.state = 2;
				currHandPos = (handHitPos + 1) % allHands.size();

			} else { // if hand is PALM_DOWN
				handHit.state = 3;

				allHands.remove(handHitPos);
				currHandPos = (handHitPos) % allHands.size();

				int whichPlayer = handHit.playerId;
				boolean isPlayerStillIn = false;
				for (int i=0; i<allHands.size(); i++) {
					if (allHands.get(i).playerId == whichPlayer) {
						isPlayerStillIn = true;

						break;
					}
				}

				if (!isPlayerStillIn) {
					nPlayers--;
				}
			}
		}

		System.out.println(allHands.get(0).playerId+1);
	}
}






















