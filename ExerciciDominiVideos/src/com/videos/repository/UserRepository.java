package com.videos.repository;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.videos.model.Response;
import com.videos.model.User;


public class UserRepository extends GenericRepository {
	
	private static final String USER_PATH = System.getProperty("user.dir")
			+ "/data/user.json";

	@Override
	public <T> Response<User> create(T user) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(USER_PATH);
		JsonNode node = objectMapper.convertValue(user, JsonNode.class);
		try {
			JsonNode tree = objectMapper.readTree(myFile);
			((ArrayNode) tree).add(node);
			objectMapper.writeValue(myFile, tree);
		} catch (Exception e) {
			return new Response<User>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<>((User) user, "User successfully registered");
	}

	@Override
	public <T> Response<Boolean> delete(T userName) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(USER_PATH);
		try {
			JsonNode tree = objectMapper.readTree(myFile);
			for (int i = 0; i < tree.size(); i++) {
				if (tree.get(i).path("userName").asText().equals(userName)) {
					((ArrayNode) tree).remove(i);
					objectMapper.writeValue(myFile, tree);
					return new Response<Boolean>(true,
							"User successfully removed");
				}
			}
		} catch (Exception e) {
			return new Response<Boolean>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<Boolean>(false, "User not found");
	}

	@Override
	public <T> Response<User> find(T userName) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(USER_PATH);
		try {
			JsonNode tree = objectMapper.readTree(myFile);
			for (JsonNode node : tree) {
				if (node.path("userName").asText().equals(userName)) {
					return new Response<User>(
							objectMapper.convertValue(node, User.class),
							"User successfully retrieved");
				}
			}
		} catch (Exception e) {
			return new Response<User>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<User>("User not found");
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

	// Checks if given user is already existing in Json file
	public Response<Boolean> isExistingUser(String userName) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(USER_PATH);
		try {
			JsonNode tree = objectMapper.readTree(myFile);
			for (JsonNode node : tree) {
				if (node.path("userName").asText().equals(userName)) {
					return new Response<Boolean>(true,
							node.path("userName").asText());
				}
			}
		} catch (IOException e) {
			return new Response<Boolean>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<Boolean>(false);
	}

	
	public Response<Boolean> login(String[] loginData) {
		ObjectMapper objectMapper = new ObjectMapper();
		File myFile = new File(USER_PATH);
		try {
			JsonNode tree = objectMapper.readTree(myFile);
			for (JsonNode node : tree) {
				if (node.path("userName").asText().equals(loginData[0]) && node
						.path("password").asText().equals(loginData[1])) {
					return new Response<Boolean>(true,
							node.path("userName").asText());
				}
			}
		} catch (Exception e) {
			return new Response<Boolean>(e.getClass().toString().substring(6)
					+ "\n" + e.getLocalizedMessage() + "\n", true);
		}
		return new Response<Boolean>(false, "");
	}
	
}
