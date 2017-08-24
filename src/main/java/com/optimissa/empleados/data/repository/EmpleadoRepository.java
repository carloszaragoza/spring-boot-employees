package com.optimissa.empleados.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.optimissa.empleados.data.entity.Empleado;

@Repository
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long>{

	Empleado findByNombre(String name);
	Iterable<Empleado> findByNombreIgnoreCaseContainingOrderByCurpDesc(String name);
	Iterable<Empleado> findByNombreIgnoreCaseContainingOrderByCurpAsc(String name);
	Iterable<Empleado> findByNombreIgnoreCaseContaining(String name);
	Page<Empleado> findByNombreIgnoreCaseContaining(String name, Pageable pageable);
	Empleado findByRfc(String rfc);
	
}
