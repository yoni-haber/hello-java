package com.yoni.hellojava;

public class Task {
  private final String description;
  private boolean completed;

  public Task(String description) {
    this.description = description;
    this.completed = false;
  }

  public String getDescription() {
    return description;
  }

  public void markCompleted() {
    this.completed = true;
  }

  public boolean isCompleted() {
    return this.completed;
  }

  @Override
  public String toString() {
    return (completed ? "[✓] " : "[ ] ") + description;
  }
}
