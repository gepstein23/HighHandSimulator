package tables;

import main.Card;
import main.Deck;
import main.HighHand;
import main.PokerHand;
import players.NLHPokerPlayer;
import players.PokerPlayer;

import java.util.ArrayList;
import java.util.Collection;

public class NLHTable extends PokerTable {
    public NLHTable(int numPlayers, double tableHandsPerHour) {
        super(numPlayers, tableHandsPerHour);
    }

    @Override
    protected boolean isPloTable() {
        return false;
    }

    @Override
    protected boolean isQualifyingHighHand(PokerHand winner, HighHand highHand) {
        return winner.compare(highHand.getMinimumQualifyingHand()) > 0;
    }

    @Override
    protected Collection<PokerPlayer> dealPlayers(Deck deck) {
        final Collection<PokerPlayer> dealtPlayers = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            final int holeCard1Index = i;
            final int holeCard2Index = i + numPlayers;
            final Card holeCard1 = deck.get(holeCard1Index);
            final Card holeCard2 = deck.get(holeCard2Index);
            final NLHPokerPlayer nlhPokerPlayer =
                    new NLHPokerPlayer(new Card[] { holeCard1, holeCard2});
            dealtPlayers.add(nlhPokerPlayer);
        }
        return dealtPlayers;
    }
}
