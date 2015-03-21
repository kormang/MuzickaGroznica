package net.etfbl.muzickagroznica.form.bean;

import net.etfbl.muzickagroznica.controller.validation.constraint.NotEmptyFile;

import org.springframework.web.multipart.MultipartFile;

public class AvatarUploadForm {

	public AvatarUploadForm() {
		// TODO Auto-generated constructor stub
	}
	
	@NotEmptyFile
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
