package thebeats;


public class Track {

	private String albumImg; // 앨범 부분 이미지
	private String titleImg; // 제목
	private String gameImg; //
	private String albumMusic; // 게임 선택 창 음악
	private String gameMusic; // 해당 곡을 실행했을 때 음악
	private String titleName;
	
	public Track(){}
	
	public Track(String albumImg, String titleImg, String gameImg, String albumMusic, String gameMusic, String titleName){
		this.albumImg = albumImg;
		this.titleImg = titleImg;
		this.gameImg = gameImg;
		this.albumMusic = albumMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}

	
	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getAlbumImg() {
		return albumImg;
	}

	public void setAlbumImg(String albumImg) {
		this.albumImg = albumImg;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getAlbumMusic() {
		return albumMusic;
	}

	public void setAlbumMusic(String albumMusic) {
		this.albumMusic = albumMusic;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}

	public String getGameImg() {
		return gameImg;
	}

	public void setGameImg(String gameImg) {
		this.gameImg = gameImg;
	}

	
	
}
