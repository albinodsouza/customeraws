package com.sony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sony.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}