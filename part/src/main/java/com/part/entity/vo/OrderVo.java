package com.part.entity.vo;

import com.part.entity.Order;
import com.part.entity.Parkinglot;
import com.part.entity.User;

public class OrderVo extends Order{
     
	private User user;
	private Parkinglot parkinglot;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Parkinglot getParkinglot() {
		return parkinglot;
	}
	public void setParkinglot(Parkinglot parkinglot) {
		this.parkinglot = parkinglot;
	}
	@Override
	public String toString() {
		return "OrderVo [user=" + user + ", parkinglot=" + parkinglot + "]";
	}
	
}
