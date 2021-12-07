package com.allwin.first.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allwin.first.exception.AddAddressException;
import com.allwin.first.exception.AddEmployeeException;
import com.allwin.first.exception.DeleteAddressException;
import com.allwin.first.exception.DeleteEmployeeException;
import com.allwin.first.exception.EmployeeIdAlreadyExistException;
import com.allwin.first.exception.EmployeeNotFoundException;
import com.allwin.first.exception.NoMatchesFoundException;
import com.allwin.first.exception.UpdateEmployeeException;
import com.allwin.first.model.Address;
import com.allwin.first.model.AutoComplete;
import com.allwin.first.model.Employee;
import com.allwin.first.model.Notification;
import com.allwin.first.repo.AutoCompleteRepo;
import com.allwin.first.repo.EmployeeRepo;
import com.allwin.first.service.EmployeeService;

@Controller
public class EmployeeController 
{
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private AutoCompleteRepo autoComRepo;
	
	@Autowired
	private EmployeeService servRepo;
	
	@GetMapping("/")
	public String index(Model m) 
	{
		m.addAttribute("employeeList", empRepo.findAll());
		m.addAttribute("notification", new Notification("",""));
		return "index";
	}
	
	@GetMapping("/index/{type}/{message}")
	public String index(@PathVariable("type") String type, @PathVariable("message") String message, Model m) 
	{
		m.addAttribute("employeeList", empRepo.findAll());
		m.addAttribute("notification", new Notification(type,message));
		return "index";
	}
	
	@GetMapping("addEmployee")
	public String addEmployee(Employee employee, Address address, Model m) 
	{
		m.addAttribute("notification", new Notification("",""));
		return "addEmployee";
	}
	
	@PostMapping("addEmployee")
	public String addEmployee(@Valid Employee employee, BindingResult bindingResult, Model m)
	{
		if (bindingResult.hasErrors()) {
			m.addAttribute("notification", new Notification("",""));
			return "addEmployee";
		} else {
			try {
				if(servRepo.findOneByIdServ(employee.getEmpId()) != null) {
					throw new EmployeeIdAlreadyExistException("Employee ID Already Exist !");
				}
				
				LocalDateTime localDateTime = LocalDateTime.now();
				employee.setLastUpdated(localDateTime.toString());
				
				Employee addedEmp = servRepo.addEmployeeServ(employee);
				
				if(addedEmp == null) {
					throw new AddEmployeeException("Employee not Added !");
				}
				
				return "redirect:/index/Success/Employee Added Successfully !";
			} catch(EmployeeIdAlreadyExistException e) {
				m.addAttribute("employee", employee);
				m.addAttribute("notification", new Notification("Error",e.getMessage()));
				return "addEmployee";
			} catch(AddEmployeeException e) {
				return "redirect:/index/Error/"+e.getMessage();
			}
		}
	}
	
	@GetMapping("editEmployee/{empId}")
	public String editEmployee(@PathVariable("empId") String empId, Model m)
	{
		try {
			Employee employee = servRepo.findOneByIdServ(empId);
			
			if(employee == null) {
				throw new EmployeeNotFoundException("Employee Not Found !");
			}
			
			m.addAttribute("employee", employee);
			m.addAttribute("notification", new Notification("",""));
			return "updateEmployee";
		} catch(EmployeeNotFoundException e) {
			return "redirect:/index/Error/"+e.getMessage();
		}
	}
	
	@PostMapping("updateEmployee")
	public String updateEmployee(@Valid Employee employee, BindingResult bindingResult, Model m) 
	{
		if (bindingResult.hasErrors()) {
			m.addAttribute("notification", new Notification("",""));
			return "updateEmployee";
		} else {
			try {
				LocalDateTime localDateTime = LocalDateTime.now();
				employee.setLastUpdated(localDateTime.toString());
				Employee updatedEmp = servRepo.updateEmployeeServ(employee);
				
				if(updatedEmp == null) {
					throw new UpdateEmployeeException("Employee not Updated !");
				}
				
				return "redirect:/index/Success/Employee Updated Successfully !";
			} catch(UpdateEmployeeException e) {
				return "redirect:/index/Error/"+e.getMessage();
			}
		}
	}
	
	@GetMapping("deleteEmployee/{empId}")
	public String deleteEmployee(@PathVariable("empId") String empId)
	{
		try {
			int deletedEmp = servRepo.deleteEmployeeServ(empId);
			
			if(deletedEmp != 1) {
				throw new DeleteEmployeeException("Employee not Deleted !");
			}
			
			List<Address> addrCount = servRepo.findAllByEmpIdServ(empId);
			int deletedAddr = servRepo.deleteAllByEmpIdServ(empId);
			
			if(addrCount.size() != deletedAddr) {
				throw new DeleteAddressException("Employee address(es) not Deleted !");
			}
			
			return "redirect:/index/Success/Employee Deleted Successfully !";
		} catch(DeleteEmployeeException e) {
			return "redirect:/index/Error/"+e.getMessage();
		} catch(DeleteAddressException e) {
			return "redirect:/index/Error/"+e.getMessage();
		}
	}
	
	@PostMapping("searchEmployee")
	public String searchEmployee(@RequestParam("searchText") String searchText, Model m) 
	{
		try {
			List<Employee> empList = servRepo.searchEmployeeServ(".*"+searchText+".*");
			List<Address> addrList = servRepo.searchAddressServ(".*"+searchText+".*");
			
			for(int i=0;i<addrList.size();i++) {
				Employee emp = servRepo.findOneByIdServ(addrList.get(i).getEmpId());
				if(empList.contains(emp) == false) { empList.add(emp); }
			}
			
			/*addrList.stream()
			.filter(s-> !(empList.contains(servRepo.findOneByIdServ(s.getEmpId()))))
			.forEach(s -> empList.add(servRepo.findOneByIdServ(s.getEmpId())));*/
			
			if(empList.size() == 0) {
				throw new NoMatchesFoundException("No Match Found !");
			}
			
			m.addAttribute("employeeList", empList);
			m.addAttribute("searchText", searchText);
			m.addAttribute("notification", new Notification("",""));
			return "index";
		} catch(NoMatchesFoundException e) {
			m.addAttribute("searchText", searchText);
			m.addAttribute("notification", new Notification("Error", e.getMessage()));
			return "index";
		}
	}
	
	@GetMapping("addAddress/{empId}")
	public String addAddress(@PathVariable("empId") String empId, Address address, Model m) 
	{
		address.setAddressId(0);
		m.addAttribute("notification", new Notification("",""));
		return "addAddress";
	}
	
	@PostMapping("addAddress")
	public String addAddress(@Valid Address address, BindingResult bindingResult, Model m)
	{
		if (bindingResult.hasErrors()) {
			m.addAttribute("notification", new Notification("",""));
			return "addAddress";
		} else {
			try {
				LocalDateTime localDateTime = LocalDateTime.now();
				address.setLastUpdated(localDateTime.toString());
				
				AutoComplete autoCompleteId = autoComRepo.findById("AutoCompleteCollection").orElse(null);
				address.setAddressId(autoCompleteId.getAddressId()+1);
				autoComRepo.save(new AutoComplete("AutoCompleteCollection", autoCompleteId.getAddressId()+1));
				
				Address addedAddr = servRepo.addAddressServ(address);
				
				if(addedAddr == null) {
					throw new AddAddressException("Address not Added !");
				}
				
				return "redirect:/index/Success/Address Added Successfully !";
			} catch(AddAddressException e) {
				return "redirect:/index/Error/"+e.getMessage();
			}
		}
	}
	
	@GetMapping("viewEmployee/{empId}")
	public String viewEmployee(@PathVariable("empId") String empId, Model m)
	{
		try {
			Employee employee = servRepo.findOneByIdServ(empId);
			
			if(employee == null) {
				throw new EmployeeNotFoundException("Employee Not Found !");
			}
			
			m.addAttribute("employeeData", employee);
			List<Address> empAddress = servRepo.findAllByEmpIdServ(empId);
			
			if(empAddress.size() == 0) { empAddress.add(new Address(0,"","","","","","","")); }
			
			m.addAttribute("addressData", empAddress);
			m.addAttribute("notification", new Notification("",""));
			return "viewEmployee";
		} catch(EmployeeNotFoundException e) {
			return "redirect:/index/Error/"+e.getMessage();
		}
	}
	
}
