package com.rwl.Bit_coin.service;

import java.util.List;

import com.rwl.Bit_coin.entity.User;

public interface SpinWheelService {

	List<User> getUsers();

	User spinWheel(Long gameId);

	User addUser(String name);

}
