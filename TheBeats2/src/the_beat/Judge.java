package the_beat;

public class Judge {

   private int perfect;
   private int good;
   private int miss;
   private int bad;
   private int noteCnt;

   final String[] grade = new String[] { "S", "A", "B", "C", "F" };

   public Judge() {
      bad = 0;
      good = 0;
      perfect = 0;
      miss = 0;
   }

   public String[] getGrade() {
      return grade;
   }
   
   public void plusScore(String score){
      String s = score.toLowerCase();
      
      if(s.equals("perfect"))
         ++perfect;
      else if(s.equals("good"))
         ++good;
      else if(s.equals("miss"))
         miss++;
      else if(s.equals("bad"))
         bad++;
      System.out.println(s+ good);
   }


   public String calculateGrade(){
      System.out.println(noteCnt);
      int max = (int)((perfect*1 + good*0.7 + bad*0.3 + miss*0)/noteCnt);
      
      if(max == 100)
         return grade[0];
      else if(max >=70)
         return grade[1];
      else if(max >=50)
         return grade[2];
      else if(max>=30)
         return grade[3];
      else
         return grade[4];
   }

}