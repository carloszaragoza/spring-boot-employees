package com.optimissa.empleados.business.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.optimissa.empleados.business.service.EmpleadosService;
import com.optimissa.empleados.data.entity.Empleado;
import com.optimissa.empleados.data.repository.EmpleadoRepository;

@Service
public class EmpleadosServiceImpl implements EmpleadosService {

	private EmpleadoRepository empleadorepository;
	
	@Autowired
	public EmpleadosServiceImpl(EmpleadoRepository empleadoRepository) {
		this.empleadorepository = empleadoRepository;
	}

	@Override
	public Iterable<Empleado> findAllEmployees() {
		return empleadorepository.findAll();
	}

	@Override
	public Empleado findEmployeeByName(String name) {
		return empleadorepository.findByNombre(name);
	}

	@Override
	public Iterable<Empleado> searchEmployeeByName(String name) {
		return empleadorepository.findByNombreIgnoreCaseContaining(name);
	}

	@Override
	public Empleado findByRFC(String rfc) {
		return empleadorepository.findByRfc(rfc);
	}

	@Override
	public Iterable<Empleado> findEmployeeByNameOrderByCurp(String name, String sort) {
		Boolean asc = true;
		
		if(sort.equals("-curp")) {
			asc= false;
		}
		
		if(asc) {
			return empleadorepository.findByNombreIgnoreCaseContainingOrderByCurpAsc(name);
		}else {
			return empleadorepository.findByNombreIgnoreCaseContainingOrderByCurpDesc(name);
		}
	}

	@Override
	public Page<Empleado> findByNombreIgnoreCaseContaining(String name, Pageable pageable) {
		return empleadorepository.findByNombreIgnoreCaseContaining(name, pageable);
	}

	@Override
	public void saveEmployee(Empleado empleado) {
		empleadorepository.save(empleado);
	}

	@Override
	public Empleado findById(String id) {
		return empleadorepository.findOne(new Long(id));
	}

	@Override
	public void deleteEmployee(String id) {
		if(empleadorepository.exists(new Long(id))) {
			empleadorepository.delete(new Long(id));			
		}
	}
}
