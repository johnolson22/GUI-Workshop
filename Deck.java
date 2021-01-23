import java.util.*;

public class Deck extends ArrayList<Card>{
      
   public Deck(){
      super(52);
      for(int n = 1; n <= 13; n++) {
         add(new Card("clubs", n));
         add(new Card("diamonds", n));
         add(new Card("hearts", n));
         add(new Card("spades", n));
      }
   }

   public void shuffleCards(){
      Collections.shuffle(this);
   }

   public Card randomCard(){
      return get(new Random().nextInt(size()));
   }


   public void printCards(){
      for(Card c : this)
         System.out.println(c);
   }
}