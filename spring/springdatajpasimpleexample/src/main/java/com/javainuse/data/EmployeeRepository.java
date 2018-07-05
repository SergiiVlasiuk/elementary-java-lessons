package com.javainuse.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
