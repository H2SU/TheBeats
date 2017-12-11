package the_beat;

public class Rank {

	private int perfect;
	private int good;
	private int miss;
	private int bad;
	private int noteCnt;
	private char score;

	public Rank(int noteCnt) {
		bad = 0;
		good = 0;
		perfect = 0;
		miss = 0;
		this.noteCnt = noteCnt;
	}

	public char getScore() {
		return score;
	}

	public int getPerfect() {
		return perfect;
	}

	public int getGood() {
		return good;
	}

	public int getMiss() {
		return miss;
	}

	public int getBad() {
		return bad;
	}

	public int getNoteCnt() {
		return noteCnt;
	}

	public void plusScore(String s) {

		if (s.equals("Perfect")) {
			++perfect;
		} else if (s.equals("Good")) {
			++good;
		} else if (s.equals("Miss")) {
			++miss;
		} else if (s.equals("Bad")) {
			++bad;
		}
	}

	public char calculateGrade() {
		int max = (int) ((perfect * 1 + good * 0.7 + bad * 0.3 + miss * 0) / noteCnt * 100);

		System.out.println("ÀüÃ¼³ëÆ®°³¼ö:" + noteCnt + " ,ÆÛÆåÆ®:" + perfect + ", ±Â: " + good + " ,¹î: " + bad + " ,¹Ì½º: " + miss);

		if (max == 100)
			return score = 'S';
		else if (max >= 70)
			return score ='A';
		else if (max >= 50)
			return score = 'B';
		else if (max >= 30)
			return score ='C';
		else
			return score ='F';
	}

}