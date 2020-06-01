package com.demo.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocSectioncontroller {
	 @Autowired
	 DocSectionSL sl;

	@Autowired
	PropInt propInt;

	/*
	 * @RequestMapping(value = "/getsectiondetails/{id}", method =
	 * RequestMethod.GET) public Documents getprop(@PathVariable("id") Integer id) {
	 * Documents doc = null; List<Prop> listOfProperties =
	 * propInt.findAllByDocuments_Id(id);
	 * 
	 * System.out.println("Size of the list is: " + listOfProperties.size());
	 * for(Prop listOfProperty : listOfProperties) {
	 * System.out.println(listOfProperty.getFont());
	 * 
	 * }
	 * 
	 * return doc; }
	 */
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addSection(@RequestBody DocSectionDTO dto) throws Exception {
		System.out.println(dto.getDocname());
		String name = dto.getDocname();
		System.out.println(dto.getSec1());
		System.out.println(dto.getSec2());

		System.out.println(dto.getSec3());

		System.out.println(dto.getSec4());

		System.out.println(dto.getSec5());
sl.getprop(dto);
		return "Success";

	}

	/*
	 * System.out.println("Test"); Documents prop=new Documents();
	 * prop=sl.getSection(id,"word");
	 */
	/* Prop pr=sl.getSection(secId, ) */

}
/*
 * @RequestMapping(value = "/getsectiondtails/{id}", method = RequestMethod.GET)
 * public Documents getdocuments(@PathVariable("id") Integer id) {
 * System.out.println("Test"); Documents doc=sl.getdoc(1); return doc; }
 */
/*
 * @RequestMapping(value = "/adddetails" , method = RequestMethod.POST) public
 * String addEmployee(@RequestBody DocSectionSL sl) throws Exception {
 * 
 * System.out.println("lname is "+employeeDto.getLname());
 * System.out.println("fName is " +employeeDto.getFname());
 * 
 * System.out.println("age is "+employeeDto.getjOb_title());
 * System.out.println("sal is "+employeeDto.getMgrname());
 * 
 * @SuppressWarnings("unused") employee
 * test=sl.addemployee(employeeDto.getFname(),employeeDto.getLname(),employeeDto
 * .getjOb_title(),employeeDto.getMgrname(),employeeDto.getStart_date(),
 * employeeDto.getCompany_number());
 * 
 * return "Success";
 * 
 * 
 * }
 */
