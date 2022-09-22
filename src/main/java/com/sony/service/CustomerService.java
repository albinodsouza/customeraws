package com.sony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.entity.Customer;
import com.sony.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	public Customer getCustomer(String customerId) {
		Optional<Customer> op = repo.findById(customerId);
		return op.isEmpty() ? null : op.get();
	}

	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	public Customer addNewCustomer(Customer customer) {
		Customer c1 = this.getCustomer(customer.getCustomerId());
		if (c1 == null) {
			return repo.save(customer);
		}
		throw new ServiceException("Customer with this id already exists");
	}

	public Customer updateCustomer(String customerId, Customer customer) {
		Customer c1 = this.getCustomer(customer.getCustomerId());
		if (c1 == null) {
			throw new ServiceException("Customer with this id does not exist");
		}
		customer.setCustomerId(customerId);
		return repo.save(customer);
	}

}
