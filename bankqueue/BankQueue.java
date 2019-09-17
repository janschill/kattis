package bankqueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class BankQueue {
  public static void main(String[] args) throws NumberFormatException, IOException {
     // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String path = "/Users/janschill/projects/university/master/1_semester/algorithm_design/lab/kattis/src/bankqueue/bank-01.in";
    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    LinkedList<Person> queue = new LinkedList<Person>();

    while (true) {
      String line = bufferedReader.readLine();
      if (line == null) {
        break;
      }
      String[] lineParts = line.split(" ");
      int numberOfPeople = Integer.parseInt(lineParts[0]);
      int maxTime = Integer.parseInt(lineParts[1]);

      for (int i = 0; i < numberOfPeople; i++) {
        String l = bufferedReader.readLine();
        String[] lps = l.split(" ");

        queue.add(new Person(Integer.parseInt(lps[0]), Integer.parseInt(lps[1])));
      }

      Collections.sort(queue);
      for (int i = 0; i < queue.size(); i++) {
        System.out.println(queue.get(i).cash + " " + queue.get(i).endurance);
      }
      System.out.println(solveBankQueue(maxTime, numberOfPeople, queue));
    }
    bufferedReader.close();
  }

  static int solveBankQueue(int maxTime, int numberOfPeople, LinkedList<Person> queue) {
    int[] toServe = new int[maxTime];
    int timeLeft = maxTime;
    int index = 0;
    int maxValue = 0;

    while (index < queue.size() && timeLeft > 0) {
      Person person = queue.get(index++);

      if (person.endurance < maxTime) {
        for (int j = person.endurance; j >= 0; j--) {
          if (toServe[j] == 0) {
            toServe[j] = person.cash;
            timeLeft--;
            break;
          }
        }
      }
    }

    for (int value : toServe) {
      maxValue += value;
    }

    return maxValue;
  }
}

class Person implements Comparable<Person> {
  int cash, endurance;

  public Person(int cash, int endurance) {
    this.cash = cash;
    this.endurance = endurance;
  }

  public int getCash() {
    return cash;
  }

  public int getEndurance() {
    return endurance;
  }

  public int compareTo(Person person) {
    return Comparator.comparing(Person::getCash).reversed().thenComparing(Person::getEndurance)
        .compare(this, person);
  }
}