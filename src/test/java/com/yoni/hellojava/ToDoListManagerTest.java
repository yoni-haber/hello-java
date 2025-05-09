package com.yoni.hellojava;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToDoListManagerTest {

  @BeforeEach
  public void resetToDoList() {
    ToDoListManager.tasks.clear();
  }

  private String runWithInput(String input) {
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    System.setIn(in);
    System.setOut(new PrintStream(out));

    try {
      ToDoListManager.main(new String[] {});
      return out.toString();
    } finally {
      System.setIn(originalIn);
      System.setOut(originalOut);
    }
  }

  /** Ensure the user is presented with all the to do list options */
  @Test
  public void testToDoList1() {
    String output = runWithInput("5\n");
    assertTrue(output.contains("Welcome to the To Do list program!"));
    assertTrue(output.contains("1. View tasks"));
    assertTrue(output.contains("2. Add task"));
    assertTrue(output.contains("3. Mark task as done"));
    assertTrue(output.contains("4. Delete task"));
    assertTrue(output.contains("5. Exit"));
    assertTrue(output.contains("Choose an option: "));
  }

  /** Ensure the user can only select a valid option */
  @Test
  public void testToDoList2() {
    String output = runWithInput("8\nabc\n5\n");
    assertTrue(output.contains("Invalid choice. Please choose a number between 1 and 5."));
    assertTrue(output.contains("Invalid input. Please enter a number between 1 and 5."));
  }

  /** Ensure the user can add a task to the list and display it */
  @Test
  public void testToDoList3() {
    String output = runWithInput("2\nget eggs\n1\n5\n");
    assertTrue(output.contains("Enter a task description: "));
    assertTrue(output.contains("'get eggs' added successfully to the list."));
    assertTrue(output.contains("Here are the items on your list: "));
    assertTrue(output.contains("[ ] get eggs")); // list output
  }

  /** Ensure the user cannot add an empty task to the list */
  @Test
  public void testToDoList4() {
    String output = runWithInput("2\n\nmilk\n5\n");
    assertTrue(output.contains("Task description cannot be empty."));
  }

  /** Ensure the user is prompted to try again when an empty task description is provided */
  @Test
  public void testToDoList5() {
    String output = runWithInput("2\n\nbread\n5\n");
    assertTrue(output.contains("Task description cannot be empty. Please try again."));
    assertTrue(output.contains("'bread' added successfully to the list."));
  }

  /** Ensure if there are no items to display we tell the user */
  @Test
  public void testToDoList6() {
    String output = runWithInput("1\n5\n");
    assertTrue(output.contains("Your to-do list is empty."));
  }

  /** Ensure a task can be marked as completed */
  @Test
  public void testToDoList7() {
    String output = runWithInput("2\ncall mum\n3\n1\n1\n5\n");
    assertTrue(output.contains("Marked as done."));
    assertTrue(output.contains("[✓] call mum"));
  }

  /** Ensure if there are no tasks to complete we tell the user */
  @Test
  public void testToDoList8() {
    String output = runWithInput("3\n5\n");
    assertTrue(output.contains("No tasks to complete."));
  }

  /** Ensure a task can be deleted */
  @Test
  public void testToDoList9() {
    String output = runWithInput("2\ndo laundry\n4\n1\n1\n5\n");
    assertTrue(output.contains("Deleted: do laundry"));
    assertTrue(output.contains("Your to-do list is empty."));
  }

  /** Ensure if there are no tasks to delete we tell the user */
  @Test
  public void testToDoList10() {
    String output = runWithInput("4\n5\n");
    assertTrue(output.contains("No tasks to delete."));
  }

  /**
   * Ensure if the user attempts to delete an invalid task number we tell them (task does not exist)
   */
  @Test
  public void testToDoList11() {
    String output = runWithInput("2\nabc\n4\n5\n5\n");
    assertTrue(output.contains("Invalid input. Must be a number."));
    assertTrue(output.contains("Invalid task number."));
  }

  /**
   * Ensure if the user attempts to delete an invalid task number we tell them (invalid task number)
   */
  @Test
  public void testToDoList12() {
    String output = runWithInput("2\nabc\n4\n0\n5\n");
    assertTrue(output.contains("Invalid input. Must be a number."));
    assertTrue(output.contains("Invalid task number."));
  }

  /** Ensure if the user provides invalid input for complete or delete task we tell them */
  @Test
  public void testToDoList13() {
    String output = runWithInput("2\nbuy milk\n3\nabc\n4\nabc\n5\n");
    assertTrue(output.contains("Invalid input. Must be a number."));
  }
}
