package thebeats;

public class Rank {

	private int perfect;
	private int good;
	private int miss;
	private int bad;
	private int noteCnt;

	final char[] grade = new char[] { 'S', 'A', 'B', 'C', 'F' };

	public Rank(int noteCnt) {
		bad = 0;
		good = 0;
		perfect = 0;
		miss = 0;
		this.noteCnt = noteCnt;
	}

	
	
	public void plusScore(String score){
		String s = score.toLowerCase();
		
		if(s.equals("perfect")){
			++perfect;
		}
		else if(s.equals("good")){
			++good;
		}
		else if(s.equals("miss")){
			miss++;
		}
		else if(s.equals("bad")){
			bad++;
		}
	}


	public char calculateGrade(){
		int max = (int)((perfect*1 + good*0.7 + bad*0.3 + miss*0)/noteCnt*100);
		
		System.out.println("전체노트개수:"+noteCnt+" ,퍼펙트:"+perfect+", 굿: "+ good+" ,뱃: "+bad+" ,미스: "+miss);
			
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
