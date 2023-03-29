package com.controller;

import com.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.service.ICustomerService;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/customers")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("/customer/homepage");
        List<Customer>list=iCustomerService.findAll();
        modelAndView.addObject("customers",list);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView=new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("customer") Customer customer){
        iCustomerService.save(customer);
        ModelAndView modelAndView=new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message","lam lai di anh");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        Customer customer=iCustomerService.findById(id);
        if (customer!=null){
            ModelAndView modelAndView=new ModelAndView("/customer/editForm");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("/customer/editForm");
            modelAndView.addObject("message","khong co");
            return modelAndView;
        }

    }
    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute("customer") Customer customer){
        iCustomerService.save(customer);
        ModelAndView modelAndView =new ModelAndView("redirect:/customers");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
    @GetMapping("remove/{id}")
    public ModelAndView remove(@PathVariable Long id){
        iCustomerService.remove(id);
        return new ModelAndView("redirect:/customers");
    }

}
