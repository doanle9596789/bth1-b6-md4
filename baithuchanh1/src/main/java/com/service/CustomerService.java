package com.service;

import com.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import com.repository.ICustomerRepository;

import java.util.List;

public class CustomerService implements ICustomerService{
    @Autowired
   private ICustomerRepository iCustomerRepository;

    @Override
    public List<Customer> findAll() {

        return iCustomerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return  iCustomerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
iCustomerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
iCustomerRepository.remove(id);
    }
}
