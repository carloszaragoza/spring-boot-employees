package com.optimissa.empleados.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.optimissa.empleados.data.entity.Empleado;

public interface EmpleadosService {

	public Iterable<Empleado> findAllEmployees();
	public Iterable<Empleado> findEmployeeByNameOrderByCurp(String name, String sort);
	public Page<Empleado> findByNombreIgnoreCaseContaining(String name, Pageable pageable);
	public Empleado findEmployeeByName(String name);
	public Iterable<Empleado> searchEmployeeByName(String name);
	public Empleado findByRFC(String rfc);
	public Empleado findById(String id);
	public void deleteEmployee(String id);
	public void saveEmployee(Empleado empleado);
}
