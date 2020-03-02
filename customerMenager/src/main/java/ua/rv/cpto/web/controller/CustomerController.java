package ua.rv.cpto.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ua.rv.cpto.web.model.Customer;
import ua.rv.cpto.web.service.CustomerService;
 
 
@RestController
public class CustomerController {
 
    @Autowired
//    @Qualifier("customerService")
    private CustomerService customerService;
    
    
    @RequestMapping("/")
    public ModelAndView home() {
        List<Customer> listCustomer = customerService.listAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listCustomer", listCustomer);
        return mav;
    }
    
    @RequestMapping("/new")
    public ModelAndView home2() {
       
    	 Customer customer = new Customer();
    	 
        ModelAndView mav = new ModelAndView("new_customer");
        mav.addObject("customer", customer);
        return mav;
    }
    
//    @RequestMapping("/")
//    public String nehomr() {
//        
//        return "new_customer";
//    }
  
//    @RequestMapping("/new")
//    public String newCustomerForm(Map<String, Object> model) {
//        Customer customer = new Customer();
//        model.put("customer", customer);
//    }
//        return "new_customer";

    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public RedirectView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return new RedirectView("/");
    }
    
    @RequestMapping("/edit")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_customer");
        Customer customer = customerService.get(id);
        mav.addObject("customer", customer);
        return mav;
    }
    
    
//    @RequestMapping("/delete")
//    public String deleteCustomerForm(@RequestParam long id) {
//        customerService.delete(id);
//        return "redirect:/";       
//    }
    @RequestMapping(value ="/delete")
    public RedirectView deleteCustomerForm(@RequestParam long id) {
    	customerService.delete(id);
        return new RedirectView("/");
    }
    
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = customerService.findByNameContaining(keyword);
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("result", result);
        return mav;    
    }
    
}
