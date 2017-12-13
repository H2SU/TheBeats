package thebeats;


public class Track {

	private String albumImg; 
	private String titleImg; 
	private String gameImg; 
	private String albumMusic;
	private String gameMusic; 
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
