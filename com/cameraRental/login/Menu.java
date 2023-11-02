package com.cameraRental.login;

import java.util.ArrayList;
import java.util.Scanner;

import com.cameraRental.pojo.Camera;
import com.cameraRental.pojo.CameraDetails;
import com.cameraRental.pojo.MyWallet;

public class Menu {
	CameraDetails cameradetail = new CameraDetails();
	MyWallet wallet = new MyWallet();
	Scanner sc = new Scanner(System.in);

	public Menu() {
		cameradetail.addCameraList(new Camera("canon","cd15",500));
		cameradetail.addCameraList(new Camera("samsung","s123",100));
		cameradetail.addCameraList(new Camera("ikon","ik89",400));
		cameradetail.addCameraList(new Camera("LG","lg254",200));
		cameradetail.addCameraList(new Camera("Nikon","nk21",400));
		cameradetail.addCameraList(new Camera("Canon","c65",500));
		cameradetail.addCameraList(new Camera("samsung","s09",300));
		cameradetail.addCameraList(new Camera("Nikon","nk32",100));
		cameradetail.addCameraList(new Camera("Ikon","ik76",300));
		cameradetail.addCameraList(new Camera("Nikon","nk6",200));
	}
	
	public void menuDisplay() {
		while(true) {
			System.out.println();
			System.out.println("1. MY CAMERA");
			System.out.println("2. RENT A CAMERA");
			System.out.println("3. VIEW ALL CAMERA");
			System.out.println("4. MY WALLET");
			System.out.println("5. EXIT");
			System.out.println("Enter Your choice: ");
			int ch = sc.nextInt();
			switch (ch) {

			case 1:
				displayCamera();
				break;

			case 2:
				rentCamera();
				break;
			case 3:
				showCameras();
				break;
			case 4:
				myWallet();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("You enter wrong choice. Please enter correct option");
				break;
			}
		}	
	}
	public void displayCamera() {
		while(true) {
			System.out.println("1. ADD");
			System.out.println("2. REMOVE");
			System.out.println("3. VIEW MY CAMERAS");
			System.out.println("4. GO TO PREVIOUS PAGE");

			System.out.println("Enter Your Choice: ");
			int ch2 = sc.nextInt();
			switch (ch2) {
			case 1:
				addCamera();
				break;
			case 2:
				removeCamera();
				break;
			case 3:
				viewMyCamera();
				break;
			case 4:
				return ;
			default:
				System.out.println("You entered wrong choice. Please enter correct choice");
				break;
			}
		}
	}
	
	public void addCamera() {
		System.out.println("ENTER THE CAMERA BRAND: ");
		String brand =sc.next()+sc.nextLine();
		System.out.println("ENTER THE MODEL: ");
		String model = sc.next()+sc.nextLine();
		System.out.println("ENTER THE PER DAY PRICE: ");
		double price = sc.nextDouble();
		cameradetail.addCameraList(new Camera(brand,model,price));
		System.out.println("YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST");
	}
	public void removeCamera() {
		showCameras();
		System.out.println("Enter camera id which you want to remove ");
		int cameraId = sc.nextInt();
		//System.out.println("Camera id ="+cameraId);
		cameradetail.removeCamList(cameraId);
		System.out.println("Camera removed successfully");
	}
	public void showCameras() {
		ArrayList<Camera> cameraC = cameradetail.getCamera();
		if(cameraC.isEmpty()) {
			System.out.println("List is empty");
		}
		else {
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.printf("%-10s %-20s %-20s %-20s %-10s%n","Camera ID", "Brand", "Model", "Rent per Day (Rs)", "Status");
			System.out.println("-------------------------------------------------------------------------------------");
			for(int i=0;i<cameraC.size();i++) {
				Camera camera = cameraC.get(i);
				String status = cameradetail.isRented(camera) ? "Rented" : "Available";
//				if(!cameradetail.isRented(camera)) {
					System.out.printf("%-10s %-20s %-20s %-20s %-10s%n",+i+1,camera.getBrand(),camera.getModel(),camera.getPrice(),status);
//				}
			}
		}
	}
	public void viewMyCamera() {
		ArrayList<Camera> cameraC = cameradetail.getCamera();
		if(cameraC.isEmpty()) {
			System.out.println("List is empty");
		}
		else {
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.printf("%-10s %-20s %-20s %-20s %-10s%n","Camera ID", "Brand", "Model", "Rent per Day (Rs.)", "Status");
			System.out.println("-------------------------------------------------------------------------------------");
			for(int i=0;i<cameraC.size();i++) {
				Camera camera = cameraC.get(i);
				String status = cameradetail.isRented(camera) ? "Rented" : "Available";
				System.out.printf("%-10s %-20s %-20s %-20s %-10s%n",+i+1,camera.getBrand(),camera.getModel(),camera.getPrice(),status);
			}
		}
	}
	public void myWallet() {
		System.out.println("My current wallet balance is: "+wallet.getBalance());
		System.out.println("Do you want to deposit more money (1.Yes/2.No):");
		char choice= sc.next().charAt(0);
		if(choice == '1') {
			System.out.println("Enter the amount : ");
			int depositAmount = sc.nextInt();
			wallet.setBalance(depositAmount);
			System.out.println("Your wallet balance is updated successfully. Current wallet balance is Rs- "+wallet.getBalance());

		}else if(choice =='2') {
			return;
		}
	}
	public void rentCamera() {	
		ArrayList<Camera> cameras = cameradetail.getCamera();
		if (cameras.isEmpty()) {
			System.out.println("No cameras available for rent.");
			return;
		}
		showCameras() ;		
		System.out.println("Enter the camera id of the camera you want to rent:");
		int cameraId = sc.nextInt();
		if (cameraId < 1 || cameraId > cameras.size()) {
			System.out.println("Invalid id. Please enter a valid camera index.");
			return;
		}
		
		Camera camera = cameras.get(cameraId-1);
		if (wallet.rent(camera)) {
			System.out.println("You have rented the following camera: " + camera);
			cameradetail.markRented(camera);
		} else {
			System.out.println("Insufficient wallet balance. Please deposit funds");
		}
	}
}
