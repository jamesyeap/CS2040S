/*
PSEUDO-CODE

FOR each card-type
	COMPUTE the profit from selling all of this card-type, sellProfit
	COMPUTE the cost of making this card-type a pair, pairCost
	
COMPUTE the profit from selling all the card-types, currProfit

SET currPairs = 0
FIND the card-type with the (LOWEST sellProfit AND LOWEST pairCost)
	UNDO its sale
		SUBTRACT sellProfit from currProfit
	BUY this card-type to make it a pair
		SUBTRACT pairCost from currProfit 
	INCREMENT currPairs by 1

IF currPairs is equal to target pairs
	return currProfit
ELSE 
	repeat the above 
	


*/


import java.util.Scanner;
import java.util.Arrays;

public class CardTrading {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nCards = sc.nextInt();
		int nTypes = sc.nextInt();
		int nTargetCombos = sc.nextInt();

		//	move cursor to the next line
		sc.nextLine(); 

		//	initialize a new array the length of the 
		//		number of different card-types
		Card[] cardTypes = new Card[nTypes];

		//	get the string of cards inside the deck
		String buffer = sc.nextLine();

		//	but don't process it yet
		//		instead, initialize all the card objects with their
		//		BUY and SELL prices first
		for (int i=0; i<nTypes; i++) {
			long buyPrice = sc.nextLong();
			long sellPrice = sc.nextLong();
			cardTypes[i] = new Card(buyPrice, sellPrice);
		}

		//	now, process the cards inside the deck
		String[] allCards = buffer.split(" ");
		for (int i=0; i<allCards.length; i++) {
			int currCardType = Integer.parseInt(allCards[i]);
			cardTypes[currCardType-1].nFoundInDeck++;
		}

		//	calculate the total profit gained from selling all the card-types
		long currProfit = 0;
		for (int i=0; i<cardTypes.length; i++) {
			currProfit += cardTypes[i].computeSellProfit();
		}

		//	sort the cardTypes 
		//		note: see the comparable implementation in the Card class below
		//				for the sorting criteria
		Arrays.sort(cardTypes);


		//	keeping undoing the sale and buying the pair until the 
		//		target number of pairs have been reached
		for (int i=0; i<nTargetCombos; i++) {
			currProfit -= (cardTypes[i].computeSellProfit() + cardTypes[i].computePairCost());
		}

		//	print out the profit
		System.out.println(currProfit);
		System.out.println("");
	}
}

class Card implements Comparable<Card> {
	public int nFoundInDeck;
	public long buyPrice;
	public long sellPrice;

	public Card(long buyPrice, long sellPrice) {
		this.nFoundInDeck = 0;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}

	public long computePairCost() {
		return (2-nFoundInDeck) * buyPrice;
	}

	public long computeSellProfit() {
		return (nFoundInDeck) * sellPrice;
	}


	@Override
	public String toString() {
		return Integer.toString(nFoundInDeck);
	}

	@Override
	public int compareTo(Card c1) {
		if ((this.computeSellProfit() + this.computePairCost()) == 
				(c1.computeSellProfit() + c1.computePairCost())) 
		{
			return 0;
		} else if ((this.computeSellProfit() + this.computePairCost()) <
				(c1.computeSellProfit() + c1.computePairCost()))
		{
			return -1;
		} else {
			return 1;
		}
	}
}