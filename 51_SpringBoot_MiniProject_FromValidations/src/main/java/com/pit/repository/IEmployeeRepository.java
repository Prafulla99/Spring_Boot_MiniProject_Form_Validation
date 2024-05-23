package com.pit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pit.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
