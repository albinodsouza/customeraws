package com.sony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sony.entity.Customer;
import com.sony.entity.CustomerList;
import com.sony.entity.ErrorInfo;
import com.sony.service.CustomerService;
import com.sony.service.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping
	public CustomerList handleGetAll() {
		return new CustomerList(service.getAllCustomers());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Object> handleGetOne(@PathVariable String customerId) {
		Customer customer = service.getCustomer(customerId);
		if (customer != null) {
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.status(404).body(new ErrorInfo("No customer found with id " + customerId));
	}

	@PostMapping
	public ResponseEntity<Object> handlePost(@RequestBody Customer customer) {
		try {
			log.info("{}", customer);
			customer = service.addNewCustomer(customer);
			return ResponseEntity.ok(customer);
		} catch (ServiceException ex) {
			return ResponseEntity.status(500).body(new ErrorInfo(ex.getMessage()));
		}
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Object> handlePut(@PathVariable String customerId, @RequestBody Customer customer) {
		try {
			customer = service.updateCustomer(customerId, customer);
			return ResponseEntity.ok(customer);
		} catch (ServiceException ex) {
			return ResponseEntity.status(500).body(new ErrorInfo(ex.getMessage()));
		}
	}
}