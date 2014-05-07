package com.example.phoneguard.update;

public class UpdateInfo {
	private String version;
	private String description;
	private String url;
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		return "UpdateInfo [version=" + version + ", description="
				+ description + ", url=" + url + "]";
	}
	public String getUrl() {
		return url;
	}
	public String getVersion() {
		return version;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
