package furflez.grapi;

import java.util.ArrayList;

public class Node {
	private int x;
	private int y;
	private int id;
	private String name;
	ArrayList<Node> neighbors = new ArrayList<Node>();
	
	public Node(){
		
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
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(ArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void addNeighbor(Node neighbor){
		
		this.neighbors.add(neighbor);
	}
	
	
}
