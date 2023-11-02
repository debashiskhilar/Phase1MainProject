package com.cameraRental.pojo;

public class MyWallet {
	CameraDetails cameradetail = new CameraDetails();
	private double balance=100.0d;

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance += balance;
	}

	public boolean canRent(Camera camera) {
		return balance >= camera.getPrice();
	}
	public boolean rent(Camera camera) {
		if(balance >= camera.getPrice() && !(cameradetail.isRented(camera))) {
			balance -= camera.getPrice();
			cameradetail.markRented(camera);
			return true;
		}
		return false;
	}

}
