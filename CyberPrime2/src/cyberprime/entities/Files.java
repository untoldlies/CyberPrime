package cyberprime.entities;

public class Files {
	
	private String filePath;
	private String fileName;
	private String mimeType;
	private int length;
	
	
	public Files(){
		super();
	}
	
	public Files(String fileName, String filePath, String mimeType, int length) {
		this();
		this.fileName = fileName;
		this.filePath = filePath;
		this.length = length;
		this.mimeType = mimeType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
