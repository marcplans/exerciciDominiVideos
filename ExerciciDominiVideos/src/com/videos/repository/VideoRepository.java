package com.videos.repository;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.videos.model.Response;
import com.videos.model.Video;

public class VideoRepository extends GenericRepository {
	private static final String VIDEO_PATH = System.getProperty("user.dir")
			+ "/data/video.json";

	@Override
	public <T> Response<Integer> create(T video) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(VIDEO_PATH);
		Integer videoId = null;
		try {
			JsonNode parsedJson = objectMapper.readTree(myFile);
			videoId = parsedJson.size() + 1;
			((Video) video).setVideoId(videoId);
			JsonNode node = objectMapper.convertValue(video, JsonNode.class);
			((ArrayNode) parsedJson).add(node);
			objectMapper.writeValue(myFile, parsedJson);
		} catch (IOException e) {
			return new Response<Integer>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<Integer>(videoId, "Video successfully saved");
	}

	@Override
	public <T> Object delete(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Response<Video> find(T videoId) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(VIDEO_PATH);
		try {
			JsonNode tree = objectMapper.readTree(myFile);
			for (JsonNode jsonNode : tree) {
				if (jsonNode.path("videoId").asText()
						.equals(Integer.toString((Integer) videoId))) {
					return new Response<Video>(
							objectMapper.convertValue(jsonNode, Video.class));
				}
			}
		} catch (IOException e) {
			return new Response<Video>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<Video>("Video not found");
	}

	@Override
	public <T> Object update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Iterable<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	// returns all videos of given user
	public Response<String> findAll(String userName) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(VIDEO_PATH);
		String videoList = "";
		int videoCounter = 0;
		try {
			JsonNode parsedJson = objectMapper.readTree(myFile);
			for (JsonNode jsonNode : parsedJson) {
				if (jsonNode.path("user").asText().equals(userName)) {
					videoList += (jsonNode.path("videoTitle").asText()) + "\n";
					videoCounter++;
				}
			}
		} catch (IOException e) {
			return new Response<String>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<String>(videoList,
				"" + videoCounter + " videos found");
	}

}
