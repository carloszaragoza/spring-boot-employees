package com.optimissa.empleados.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.optimissa.empleados.business.service.EmpleadosService;
import com.optimissa.empleados.data.dto.EmpleadoDTO;
import com.optimissa.empleados.data.entity.Empleado;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/v1/empleados")
@Api(value="employee", description="Data service operations on employees", tags = "employee")
public class EmpleadoServiceController {

	@Autowired
	private EmpleadosService service;
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value="Get employees depending onthe parameters that are received", nickname="getEmployees")
	List<Empleado> findAllEmpleados(@RequestParam(required = false) String name, @RequestParam(required = false) String sname, @RequestParam(required = false) String sort){
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		if(null == name
				&& null == sname) {
			Iterable<Empleado> results = this.service.findAllEmployees();
			results.forEach(e -> {
				empleados.add(e);
			});
		}else if(null != name
				&& null == sname) {
			
			
			Empleado e = this.service.findEmployeeByName(name);
			if(null != e) {
				empleados.add(e);
			}
			
		}else if(null == name
					&& null != sname) {
			if(null == sort) {
				Iterable<Empleado> results = this.service.searchEmployeeByName(sname);
				results.forEach(e -> {
					empleados.add(e);
				});				
			}else if(null != sort) {
				Iterable<Empleado> results = this.service.findEmployeeByNameOrderByCurp(sname, sort);
				
				results.forEach(e -> {
					empleados.add(e);
				});
				
			}
		}
		
		return empleados;
	}
	
	
	@RequestMapping(value="/{rfc}", method = RequestMethod.GET)
	@ApiOperation(value="Get a employee by rfc", nickname="getEmployeeByRFC")
	public Empleado searchEmpleadoByName(@PathVariable("rfc") String rfc){
		Empleado empleado = null;
		if(null != rfc) {
			empleado = this.service.findByRFC(rfc);
		}
		return empleado;
	}
	
	@RequestMapping(value="/searchName", method = RequestMethod.GET)
	@ApiOperation(value="Search employee by name and sort", nickname="searchEmployeeByNameAndSort")
	public Page<Empleado> searchEmpleadoByNameSort(@RequestParam("name") String name, Pageable pageable){
		Page<Empleado> empleados = null;
		
		
		if(null != name) {
			empleados = service.findByNombreIgnoreCaseContaining(name, pageable);
		}else {
			throw new NoSuchElementException("Employee does not exist " + name);
		}
		
		return empleados;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Create employee", nickname="createEmployee")
	public void createEmployee(@RequestBody EmpleadoDTO empleadoDTO) {
		if(null == service.findByRFC(empleadoDTO.getRfc())) {
			service.saveEmployee(new Empleado(empleadoDTO.getNombre(), empleadoDTO.getApellidoPaterno(), empleadoDTO.getApellidoMaterno(), empleadoDTO.getRfc(), empleadoDTO.getCurp()));
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Update Employee", nickname="updateEmployee")
	public Empleado updateEmployee(@PathVariable String id, @RequestBody EmpleadoDTO empleadoDTO) {
		Empleado empleado = service.findById(id);
		if(null != empleado) {
			empleado.setNombre(empleadoDTO.getNombre());
			empleado.setApellidoPaterno(empleadoDTO.getApellidoPaterno());
			empleado.setApellidoMaterno(empleadoDTO.getApellidoMaterno());
			empleado.setRfc(empleadoDTO.getRfc());
			empleado.setCurp(empleadoDTO.getCurp());
			service.saveEmployee(empleado);
		}
		return service.findById(id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Delete Employee", nickname="deleteEmployee")
	public void deleteEmployee(@PathVariable String id) {
		service.deleteEmployee(id);
	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();
	}
}
