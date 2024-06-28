package playground;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.util.Comparator;
import java.util.TreeSet;

public class CustomObjectComparision {

    public static class Student {
        int rollno;
        String name;

        int age;

        public Student(final int rollno, final String name) {
            this.rollno = rollno;
            this.name = name;
            this.age = 0;
        }

        public Student(final int rollno, final String name, final int age) {
            this.rollno = rollno;
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public Integer getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            String result = "Rollno: " + this.rollno + " Name: " + this.name;
            return this.age == 0 ? result : result + " Age: " + this.age;
        }
    }

    // Helper class implementing Comparator interface
    static class Sortbyroll implements Comparator<Student> {
        // Sorting in ascending order of roll number
        public int compare(Student a, Student b)
        {
            return a.rollno - b.rollno;
        }
    }

    // Helper class implementing Comparator interface
    static class Sortbyname implements Comparator<Student> {
        // Sorting in ascending order of name
        public int compare(Student a, Student b)
        {
            return a.name.compareTo(b.name);
        }
    }

    // Helper class implementing Comparator interface
    static class CustomerSortingComparator
            implements Comparator<Student> {

        // To compare customers using multiple fields
        @Override
        public int compare(Student customer1, Student customer2)
        {
            // Comparing customers
            int NameCompare = customer1.getName().compareTo(
                    customer2.getName());

            int AgeCompare = customer1.getAge().compareTo(
                    customer2.getAge());

            // 2nd level comparison
            return (NameCompare == 0) ? AgeCompare
                    : NameCompare;
        }
    }

    static class Candidate implements Comparable {
        int index;
        long cost;

        public Candidate(final int index, final long cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            Candidate other = (Candidate) o;
            if (this.cost == other.cost)
            {
                return this.index - other.index;
            } else {
                return (int) (this.cost - other.cost);
            }
        }

        @Override
        public String toString() {
            return String.format("{cost: %d index: %d}",this.cost, this.index);
        }
    }

    public static void main(String[] args) {
        // Comparable
        TreeSet<Candidate> candidates = new TreeSet<>();
        candidates.add(new Candidate(2, 10));
        candidates.add(new Candidate(1, 5));
        candidates.add(new Candidate(2, 20));
        candidates.add(new Candidate(1, 10));
        System.out.println(candidates);
        // Sorted by cost, if cost equal sorted by index
        // [{cost: 5 index: 1}, {cost: 10 index: 1}, {cost: 10 index: 2}, {cost: 20 index: 2}]

        TreeSet<Student> studentsSortedByRoll = new TreeSet<>(new Sortbyroll());

        studentsSortedByRoll.add(new Student(10, "T1"));
        studentsSortedByRoll.add(new Student(9, "T3"));
        studentsSortedByRoll.add(new Student(11, "T2"));

        System.out.println(studentsSortedByRoll);
        // Sorted by rollno
        // [Rollno: 9 Name: T3, Rollno: 10 Name: T1, Rollno: 11 Name: T2]

        TreeSet<Student> studentsSortedByName = new TreeSet<>(new Sortbyname());

        studentsSortedByName.add(new Student(10, "T1"));
        studentsSortedByName.add(new Student(9, "T3"));
        studentsSortedByName.add(new Student(11, "T2"));

        System.out.println(studentsSortedByName);
        // Sorted by name
        // [Rollno: 10 Name: T1, Rollno: 11 Name: T2, Rollno: 9 Name: T3]

        TreeSet<Student> customerSorting = new TreeSet<>(new CustomerSortingComparator());
        customerSorting.add(new Student(10, "T1", 10));
        customerSorting.add(new Student(9, "T1", 11));
        customerSorting.add(new Student(11, "T1", 7));
        customerSorting.add(new Student(9, "T2", 7));

        System.out.println(customerSorting);
        // Sorted by name then age
        // [Rollno: 11 Name: T1 Age: 7, Rollno: 10 Name: T1 Age: 10, Rollno: 9 Name: T1 Age: 11, Rollno: 9 Name: T2 Age: 7]
    }
}
