import java.util.*;

class Card {
    String value;
    String suit;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value.equals(card.value) && suit.equals(card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, suit);
    }
}

public class CardCollectionSystem {
    private Set<Card> cards;
    private Map<String, List<Card>> cardsBySuit;

    public CardCollectionSystem() {
        cards = new HashSet<>();
        cardsBySuit = new HashMap<>();
    }

    public void addCard(String value, String suit) {
        Card newCard = new Card(value, suit);
        if (cards.contains(newCard)) {
            System.out.println("Error: Card \"" + newCard + "\" already exists.");
        } else {
            cards.add(newCard);
            cardsBySuit.computeIfAbsent(suit, k -> new ArrayList<>()).add(newCard);
            System.out.println("Card added: " + newCard);
        }
    }

    public void findCardsBySuit(String suit) {
        if (cardsBySuit.containsKey(suit) && !cardsBySuit.get(suit).isEmpty()) {
            for (Card card : cardsBySuit.get(suit)) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for " + suit + ".");
        }
    }

    public void removeCard(String value, String suit) {
        Card cardToRemove = new Card(value, suit);
        if (cards.remove(cardToRemove)) {
            cardsBySuit.get(suit).remove(cardToRemove);
            System.out.println("Card removed: " + cardToRemove);
        } else {
            System.out.println("Error: Card \"" + cardToRemove + "\" not found.");
        }
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public static void main(String[] args) {
        CardCollectionSystem system = new CardCollectionSystem();

        System.out.println("Test Case 1: No Cards Initially");
        system.displayAllCards();

        System.out.println("\nTest Case 2: Adding Cards");
        system.addCard("Ace", "Spades");
        system.addCard("King", "Hearts");
        system.addCard("10", "Diamonds");
        system.addCard("5", "Clubs");

        System.out.println("\nTest Case 3: Finding Cards by Suit");
        system.findCardsBySuit("Hearts");

        System.out.println("\nTest Case 4: Searching Suit with No Cards");
        system.findCardsBySuit("Diamonds");

        System.out.println("\nTest Case 5: Displaying All Cards");
        system.displayAllCards();

        System.out.println("\nTest Case 6: Preventing Duplicate Cards");
        system.addCard("King", "Hearts");

        System.out.println("\nTest Case 7: Removing a Card");
        system.removeCard("10", "Diamonds");

        System.out.println("\nDisplaying All Cards After Removal:");
        system.displayAllCards();
    }
}
