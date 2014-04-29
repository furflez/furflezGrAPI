package furflez.grapi;

import java.util.ArrayList;

public class Nodo {
	int x;
	int y;
	int id;
	String name;
	ArrayList<Nodo> neighbors = new ArrayList<Nodo>();
	
	public Nodo(){
		
	}
	
	public Nodo(int x, int y, int id, String name) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
		this.name = name;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int[] getPosition(){
		int[] position = {this.x, this.y};
		return position;
	}
	
	public void setPosition(int[] position){
		this.x = position[0];
		this.y = position[1];
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Nodo> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(ArrayList<Nodo> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void addNeighbor(Nodo neighbor){
		
		this.neighbors.add(neighbor);
	}
	
	
}
