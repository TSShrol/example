package ua.rv.cpto.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.rv.cpto.web.model.Customer;
import ua.rv.cpto.web.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository repo;

	public void save(Customer customer) {
		repo.save(customer);
	}

	public List<Customer> listAll() {
		return (List<Customer>) repo.findAll();
	}

	public Customer get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public List<Customer> findByNameContaining(String keyword) {
	    return repo.findByNameContaining(keyword);
	}

}
