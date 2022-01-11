package com.xpanxion.java.assignments.student1;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Cat;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Product;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Worker1 {

    public void ex1() {
        var products = DataAccess.getProducts();
        Map<Integer, Department> departmentMap = DataAccess.getDepartments().stream()
                .collect(Collectors.toMap(Department::getId, Function.identity()));

        products.forEach(p -> p.setDepartmentName(departmentMap.get(p.getDepartmentId()).getName()));
        System.out.println(products);
    }

    public void ex2() {
        var products = DataAccess.getProducts();
      
        products.forEach(p -> p.setDepartmentName("N/A"));
        System.out.println(products);
    }

    public void ex3() {
        var products = DataAccess.getProducts();

        var result = products.stream().filter(p -> p.getPrice() >= 10.0);
        System.out.println(result.collect(Collectors.toList()));
    }

    public void ex4() {
        var products = DataAccess.getProducts();
        double total;
        NumberFormat fomatter = NumberFormat.getCurrencyInstance();
        String moneyString;

        total = products.stream()
                .filter(p -> p.getDepartmentId() == 2)
                .mapToDouble(Product::getPrice).sum();

        moneyString = fomatter.format(total);
        System.out.println(moneyString);
    }

    public void ex5() {
        var people = DataAccess.getPeople();

        var result = people.stream()
                .filter(person -> person.getId() <= 3)
                .map(p -> {
                    p.setSsn(p.getSsn().split("-")[2]);
                    return p;
                })
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public void ex6() {
        var cats = DataAccess.getCats();
        Collections.sort(cats, new com.xpanxion.java.assignments.student1.Sortbyname());
        System.out.println(cats);
    }
}

class Sortbyname implements Comparator<Cat> {

    public int compare(Cat a, Cat b) {
        return a.getName().compareTo(b.getName());
    }
}