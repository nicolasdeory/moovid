package aiss.model.photos.album;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class SharedAlbumOptions {
	private Boolean isCollaborative;
	private Boolean isCommentable;
	
	public Boolean getIsCollaborative() {
		return isCollaborative;
	}
	public void setIsCollaborative(Boolean isCollaborative) {
		this.isCollaborative = isCollaborative;
	}
	public Boolean getIsCommentable() {
		return isCommentable;
	}
	public void setIsCommentable(Boolean isCommentable) {
		this.isCommentable = isCommentable;
	}
	
}