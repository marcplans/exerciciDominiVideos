package com.videos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 

public class Video {
	private Integer videoId;
	private String videoTitle;
	private String videoURL;
	private ArrayList<String> videoTags;
	private String user;
	
	public Video() {
		super();
	}

	public Video(String videoTitle,
			String videoTags, String userName) {
		this.videoTitle = videoTitle;
		this.videoURL = getUrl(userName);
		this.videoTags = splitTags(videoTags);
		user = userName;
	}

	public final Integer getVideoId() {
		return videoId;
	}
	
	public final void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public final void setUser(String user) {
		this.user = user;
	}

	public final String getVideoTitle() {
		return videoTitle;
	}

	public final void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public final String getVideoURL() {
		return videoURL;
	}

	public final void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public final ArrayList<String> getVideoTags() {
		return videoTags;
	}

	public final void setVideoTags(ArrayList<String> videoTags) {
		this.videoTags = videoTags;
	}

	public final String getUser() {
		return user;
	}
	
	public boolean equals(Video otherVideo) {
		return ((this.getVideoId() == otherVideo.getVideoId())
				&& (this.getVideoTitle().equals(otherVideo.getVideoTitle()))
				&& (this.getVideoURL().equals(otherVideo.getVideoURL()))
				&& (this.getVideoTags().equals(otherVideo.getVideoTags()))
				&& (this.getUser().equals(otherVideo.getUser())));
	}
	
	private static String getUrl (String user) {
		return "https://videos.com/" + user + "/" + LocalDateTime.now().format(
				DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}
	
	private static ArrayList<String> splitTags (String listTags) {
		listTags = listTags.replaceAll(",", " ");
		listTags = listTags.replaceAll("\\.", " ");
		listTags = listTags.replaceAll("/", " ");
		listTags = listTags.toLowerCase();
		String[] tagArray = listTags.split("\\s+");
		ArrayList<String> tagArrayList = new ArrayList<>();
		for (int i = 0; i < tagArray.length; i++) {
			tagArrayList.add(tagArray[i]);
		}
		return tagArrayList;
	}
		
	public String toString() {
		return "\nVideo id: " + getVideoId() + "\nTitle: "
				+ this.getVideoTitle() + "\nURL: " + this.getVideoURL()
				+ "\nUser: " + this.getUser() + "\nTags: "
				+ this.getVideoTags();
	}
	
}
