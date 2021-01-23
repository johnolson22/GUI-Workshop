import java.util.*;
import javax.swing.*;

public class InitialBlackjack extends JFrame{
    private int balance = 1000;
    private int bet;
    private ArrayList<Card> myCards;
    private ArrayList<Card> dHand;
    private Deck cards = new Deck();
    private Deck dCards = new Deck();
    private Scanner scn = new Scanner(System.in);
    
    public void initialize(){
        setVisible(true);
        
        resetCards();
    }

    public void resetCards(){
        myCards = new ArrayList<Card>();
        dHand = new ArrayList<Card>();

        cards.clear();
        cards = new Deck();

        dCards.clear();
        dCards = new Deck();

        cards.shuffleCards();
        dCards.shuffleCards();

        System.out.println("Your balance is " + balance);
        System.out.print("What is your bet?   ");
        updateBet(scn.next());
        }
    
    
    public void hit(){
        Card next = cards.get(myCards.size());
        myCards.add(next);
        System.out.println(next);
        System.out.println("Value: " + totalValue(myCards));
        if(totalValue(myCards) >= 21)
            stand();

        hitOrStand();
    }

    public void hitOrStand(){
        System.out.print("Would you like to hit or stand?   ");
        if(scn.nextLine().equals("stand"))
            stand();
        else if(scn.nextLine().equals("hit"))
            hit();
        else hitOrStand();
    }

    public int totalValue(ArrayList<Card> list){
        int sum = 0;
        for(Card c : list)
            sum+=c.getValue();
        return sum;
    }

    public void chooseCards(){
        while(totalValue(dHand) < 17)
            dHand.add(dCards.get(dHand.size()));
    }
    
    public void stand(){
        System.out.println("stand");
        chooseCards();
        int pVal = totalValue(myCards);
        int dVal = totalValue(dHand);

        if((pVal > dVal || dVal > 21) && pVal <= 21){
            balance += bet;
            System.out.println("You win! You won $" + bet);
        }
        else if (pVal != dVal && dVal <= 21){
            balance -= bet;
            System.out.println("Dealer wins! You lost $" + bet);
        }
        else
            System.out.println("Push");
        
        System.out.println("Your cards:");
        for(Card cd : myCards)
            System.out.println(cd);
        System.out.println("\nDealer's cards:");
        for(Card cd : dHand)
            System.out.println(cd);
        System.out.println("\n\n\n");
        resetCards();

    }

    public void updateBet(String myBet){
        try{
            int bet = Integer.parseInt(myBet);
            if(bet <= balance){
                this.bet = bet;
                hit();
            }
            else{ 
                System.out.println("Please enter a valid bet");
                updateBet(scn.next());
            }
        }
        catch(Exception e){
            System.out.println("Please enter a valid bet");
            updateBet(scn.next());
        }

    }

    public static void main(String[] args){
        InitialBlackjack bj = new InitialBlackjack();
        bj.initialize();
    }

}