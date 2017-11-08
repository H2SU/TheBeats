package the_beat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {//
	
	private Player player; //자바 줌 라이브러리 중 하나
	private boolean isLoop;//현재 곡이 무한반복인지 확인을 위한 변수
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {//생성자 해당곡이름과 무한반복여부를 파라미터로 받는다
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI()); //music폴더 아래 해당이름의 파일을 실행 toURL()로 그 위치를 가져오도록 함
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); //해당파일을 버퍼에 담아서 읽어올 수 있도록함
			player = new Player(bis); //해당 파일을 담을 수 있도록 함
		} catch (Exception e) { //오류처리
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() { //현재 실행 음악이 어떤 위치에서 실행되고 있는지 알려줌 0.001초단위로 알려줌 떨어지는 노트를 분석하는 함수
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() { //음악이 언제 실행되던지 종료하도록 하는 함수 
		isLoop = false;   //사용자가 음악실행중 뒤로가기를 누르더라도 해당 곡의 안정적으로 종료하도록 함
		player.close();
		this.interrupt();  //해당 thread를 중지상태로 만듬
	}
	
	@Override
	public void run() { //thread를 상속받으면 사용해야하는 함수
		try {
			do {
				player.play(); //곡실행
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); //무한반복검사
		} catch (Exception e) { //오류처리
			System.out.println(e.getMessage());
		}
	}

}
