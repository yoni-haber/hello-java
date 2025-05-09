package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskTest {

  /** Enure we can initialise a Task object with the correct values */
  @Test
  public void testTask1() {
    Task task = new Task("bread");
    assertEquals("bread", task.getDescription());
    assertFalse(task.isCompleted());
  }

  /** Ensure we can mark a task as completed */
  @Test
  public void testTask2() {
    Task task = new Task("eggs");
    task.markCompleted();
    assertTrue(task.isCompleted());
  }

  /** Ensure if a task is completed we mark it with an x */
  @Test
  public void testTask3() {
    Task task = new Task("water");
    assertEquals("[ ] water", task.toString());
    task.markCompleted();
    assertEquals("[✓] water", task.toString());
  }
}
