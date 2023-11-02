package com.cameraRental.pojo;

import java.util.ArrayList;
import java.util.HashMap;

public class CameraDetails {
	ArrayList<Camera> cam= new ArrayList<>();
	HashMap<Camera,Boolean> rentStatus = new HashMap<>();
	
	//add new camera
	public void addCameraList(Camera camera) {
		cam.add(camera);
	}
	public ArrayList<Camera> getCamera(){
		return cam;
	}
	public void markRented(Camera camera) {
		rentStatus.put(camera, true);
	}
	public void markAvailable(Camera camera) {
		rentStatus.put(camera, false);
	}
	//
	public boolean isRented(Camera camera) {
		Boolean status = rentStatus.get(camera);
		return status != null && status;
	}	
	public void removeCamList (int cameraId) {

		if(cameraId >= 1 && cameraId <= cam.size()) {
			Camera removed = cam.get(cameraId);
			cam.remove(cameraId);
			rentStatus.remove(removed);
		}
	}	
}
