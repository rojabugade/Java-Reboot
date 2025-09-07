package com.example.todoapp.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Todo {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	// Getters & Setters

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private boolean completed;
}