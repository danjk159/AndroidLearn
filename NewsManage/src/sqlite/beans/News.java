package sqlite.beans;

public class News {
	private long _id;
	private String Title;
	private String NewsDateTime;
	private String UsersName;
	private String Contetx;
	private byte[] ImgFile;

	public long get_id() {
		return _id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getNewsDateTime() {
		return NewsDateTime;
	}
	public void setNewsDateTime(String newsDateTime) {
		NewsDateTime = newsDateTime;
	}
	public String getUsersName() {
		return UsersName;
	}
	public void setUsersName(String usersName) {
		UsersName = usersName;
	}
	public String getContetx() {
		return Contetx;
	}
	public void setContetx(String contetx) {
		Contetx = contetx;
	}
	public byte[] getImgFile() {
		return ImgFile;
	}
	public void setImgFile(byte[] imgFile) {
		ImgFile = imgFile;
	}
	public void set_id(int rowid) {
		// TODO Auto-generated method stub
		_id=rowid;
	}
}
