package net.etfbl.muzickagroznica.form.bean;

import org.springframework.web.multipart.MultipartFile;

public class AvatarUploadForm {

	public AvatarUploadForm() {
		// TODO Auto-generated constructor stub
	}
	
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
