package com.videos.controller;

import com.videos.model.Response;
import com.videos.model.Video;
import com.videos.repository.VideoRepository;

public class VideoController {
	private final VideoRepository videoRepository = new VideoRepository();

	public String addVideo(String[] videoData) {
		Video video = new Video(videoData[0], videoData[1], videoData[2]);
		Response<Integer> response = videoRepository.create(video);
		if (response.get() != null) {
			Video retrievedVideo = videoRepository.find(response.get()).get();
			if (retrievedVideo != null && retrievedVideo.equals(video)) {
				return "\nVideo successfully saved";
			}
		}
		return "\nError saving video, try later!";
	}
	
	public String getUserVideos(String userName) {
		Response<String> response = videoRepository.findAll(userName);
		if (response.isExceptionThrown()) {
			return "\nSorry, unknown error, try later!\n";
		} else if (response.get().isEmpty()) {
			return "\nSorry, no videos found for this user!\n";
		}
		return response.get();
	}

	
}
