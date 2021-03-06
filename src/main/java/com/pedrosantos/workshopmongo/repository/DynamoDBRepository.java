package com.pedrosantos.workshopmongo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.pedrosantos.workshopmongo.domain.User;

@Repository
public class DynamoDBRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBRepository.class);

	@Autowired
	private DynamoDBMapper mapper;

	public void insertIntoDynamoDB(User user) {
		mapper.save(user);
	}

	public User getUserDetails(String userId, String email) {
		return mapper.load(User.class, userId, email);
	}

	public List<User> getAllUsers() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		PaginatedScanList<User> paginatedScanList = mapper.scan(User.class, scanExpression);

		return paginatedScanList;
	}

	public void updateUserDetails(User user) {
		try {
			mapper.save(user, buildDynamoDBSaveExpression(user));
		} catch (ConditionalCheckFailedException exception) {
			LOGGER.error("invalid data - " + exception.getMessage());
		}
	}

	public Boolean deleteUserDetails(User user) {

		if (user != null)
			mapper.delete(user);
		else
			return false;

		return true;
	}

	private DynamoDBSaveExpression buildDynamoDBSaveExpression(User user) {
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();

		Map<String, ExpectedAttributeValue> expected = new HashMap<>();

		expected.put("userId", new ExpectedAttributeValue(new AttributeValue(user.getId()))
				.withComparisonOperator(ComparisonOperator.EQ));

		saveExpression.setExpected(expected);

		return saveExpression;
	}
}
