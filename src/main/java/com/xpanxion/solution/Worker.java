package com.xpanxion.solution;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Person;
import com.xpanxion.java.assignments.model.PersonCat;
import com.xpanxion.java.assignments.model.Product;

public class Worker {

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    public void ex1 () {
        var deptMap = DataAccess.getDepartments().stream().collect(Collectors.toMap(Department::getId, Department::getName));
        System.out.println(DataAccess.getProducts().stream().peek(
                product -> product.setDepartmentName(
                        deptMap.get(product.getDepartmentId())
                ) ).collect(Collectors.toList()));
        //System.out.println(DataAccess.getProducts().stream().map(product -> product.setDepartmentName( DataAccess.getDepartments().stream().filter(dept -> dept.getId() == product.getDepartmentId()).findFirst().get().getName()) ).collect(Collectors.toList()));
    }

    public void ex2 () {
        System.out.println(DataAccess.getProducts().stream().peek(
                product -> product.setDepartmentName("N/A")).collect(Collectors.toList()));
    }

    public void ex3() {
        System.out.println(DataAccess.getProducts().stream().filter(
                product -> product.getDepartmentId() == 1).filter(
                        product -> product.getPrice() >= 10.00).collect(Collectors.toList()));
    }

    public void ex4() {
        System.out.println(currencyFormatter.format(
                DataAccess.getProducts().stream().filter(
                product -> product.getDepartmentId() == 2).map(
                        Product::getPrice).reduce(Float::sum).get()));
    }

    public void ex5() {
        System.out.println(DataAccess.getPeople().stream().filter(
                person -> person.getId() <= 3).peek(
                person -> person.setSsn(
                        person.getSsn().substring(7))
                ).collect(Collectors.toList()));
    }

    public void ex6() {
        System.out.println(DataAccess.getCats().stream().sorted().collect(Collectors.toList()));
    }

    public void ex7() {
        var wordMap = new HashMap<String, Integer>();
        String[] words = DataAccess.getWords().split(" ");
        for (String word : words) {
            var currVal = wordMap.putIfAbsent(word, 1);
            if ( currVal != null ) {
                wordMap.put(word, currVal+1);
            }
        }
        var wordTreeMap = new TreeMap<String, Integer>(wordMap);
        for ( Map.Entry<String, Integer> element : wordTreeMap.entrySet() ) {
            System.out.println(element.getKey() + " = " + element.getValue());
        }
    }

    public void ex8() {
        System.out.println(DataAccess.getPeople().stream().map(
                person -> new Person(
                        person.getId(),
                        person.getFirstName(),
                        "null",
                        0,
                        "null")).collect(Collectors.toList()));
    }

    public void ex9() {
        System.out.println(currencyFormatter.format(
                DataAccess.getProducts().stream().filter(
                        product -> product.getDepartmentId() == 1).map(
                        product -> product.getPrice() + 2.00).reduce(Double::sum).get()));
    }

    public void ex10() {
        System.out.println(DataAccess.getPeople().stream().map(
                person -> new PersonCat(
                        person.getId(),
                        person.getFirstName(),
                        DataAccess.getCats().stream().filter(
                                cat -> cat.getId() == person.getId()).collect(Collectors.toList()))
        ).collect(Collectors.toList()));
    }
}
