package players;

import main.Card;
import main.PokerHand;

import java.util.List;

public class PLOPokerPlayer extends PokerPlayer {
    public PLOPokerPlayer(Card[] holeCards) {
        super(holeCards);
        if (holeCards.length != 4) {
            throw new AssertionError("Expected 4 player hole cards for PLO.");
        }
    }

    @Override
    public PokerHand getBestHand(List<Card> communityCards) {
        PokerHand bestHand = null;
        for (List<Integer> holeCardIndices : holeCardIndexCombos) {
            final Card holdCard1 = holeCards[holeCardIndices.get(0)];
            final Card holdCard2 = holeCards[holeCardIndices.get(1)];
            for (List<Integer> communityCardIndices : communityCardIndexCombos) {
                final Card communityCard1 = communityCards.get(communityCardIndices.get(0));
                final Card communityCard2 = communityCards.get(communityCardIndices.get(1));
                final Card communityCard3 = communityCards.get(communityCardIndices.get(2));
                final PokerHand pokerHand = new PokerHand(isFlopped(communityCardIndices), holdCard1, holdCard2, communityCard1, communityCard2, communityCard3);
                if (bestHand == null) {
                    bestHand = pokerHand;
                    continue;
                }
                if (pokerHand.compare(bestHand) > 0) {
                    bestHand = pokerHand;
                }
            }
        }
        return bestHand;
    }
}
