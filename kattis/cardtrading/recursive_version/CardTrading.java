import java.util.Scanner;
import java.util.Arrays;

/*
public class CardTrading {
	public static Long getMaxProfit(
		Card[] deck, 
		int currCardType, 
		int nTargetCombos, 
		int nCurrCombos, 
		long currProfit,
		String action
		) 
	{
		// System.out.println(action);
		// System.out.format("current card: %d\n",currCardType+1);
		// System.out.println(nTargetCombos);
		// System.out.println(nCurrCombos);
		// System.out.println(currProfit);
		// System.out.println("-----");

		if (nCurrCombos == nTargetCombos) {
			return currProfit;
		}

		if (currCardType == (deck.length)) {
			return null;
		}

		Long sellExcessProfit = getMaxProfit(
			deck, 
			currCardType+1, 
			nTargetCombos, 
			nCurrCombos, 
			currProfit+((deck[currCardType].nFoundInDeck-2)*deck[currCardType].sellPrice),
			String.format("SELL Excess %d", currCardType+1));

		Long sellAllProfit = getMaxProfit(
			deck,
			currCardType+1,
			nTargetCombos,
			deck[currCardType].hasCombo() ? nCurrCombos-1 : nCurrCombos,
			currProfit+((deck[currCardType].nFoundInDeck)*deck[currCardType].sellPrice),
			String.format("SELL All of %d", currCardType+1));

		Long buyProfit = deck[currCardType].hasCombo() ? null : getMaxProfit(
			deck,
			currCardType+1,
			nTargetCombos,
			nCurrCombos+1,
			currProfit-((2-deck[currCardType].nFoundInDeck)*deck[currCardType].buyPrice),
			String.format("BUY %d", currCardType+1));

		Long bestProfit = null;
		Long allProfits[] = { sellExcessProfit, sellAllProfit, buyProfit };

		// System.out.println(Arrays.toString(allProfits));

		for (int i=0; i<allProfits.length; i++) {
			if (bestProfit == null) {
				bestProfit = allProfits[i];
			}

			if (allProfits[i] != null && allProfits[i] > bestProfit) {
				bestProfit = allProfits[i];
			}
		}

		return bestProfit;
	}

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
		int nCurrCombos = 0;
		String[] allCards = buffer.split(" ");
		for (int i=0; i<allCards.length; i++) {
			int card = Integer.parseInt(allCards[i]);
			cardTypes[card-1].nFoundInDeck++;

			if (cardTypes[card-1].hasCombo()) {
				nCurrCombos++;
			}
		}

		// System.out.println(nCurrCombos);
		// System.out.println(Arrays.toString(cardTypes));

		Long bestProfit = getMaxProfit(cardTypes, 0, nTargetCombos, nCurrCombos, 0, "START");
		System.out.println(bestProfit);
	}
}
*/


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
		long currProfit = 0;

		//		random thought
		long[] buyingPriority = new long[nTypes];

		String[] allCards = buffer.split(" ");
		for (int i=0; i<allCards.length; i++) {
			int currCardType = Integer.parseInt(allCards[i]);
			cardTypes[currCardType-1].nFoundInDeck++;

			buyingPriority[i] =
		}
	}
}

class Card {
	public int nFoundInDeck;
	public Integer initialNumberInDeck; // can optimise here if needed by using vanilla int
	public long buyPrice;
	public long sellPrice;

	public Card(long buyPrice, long sellPrice) {
		this.nFoundInDeck = 0;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}

	public boolean hasCombo() {
		return (nFoundInDeck == 2);
	}

	public long sellAll() {
		initialNumberInDeck = nFoundInDeck;
		long result = nFoundInDeck * sellPrice;
		nFoundInDeck = 0;

		return result;  
	}

	@Override
	public String toString() {
		return Integer.toString(nFoundInDeck);
	}
}