package com.optimissa.empleados;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.optimissa.empleados.business.service.EmpleadosService;
import com.optimissa.empleados.data.entity.Empleado;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpleadosApplicationTests {

	@Autowired
	private EmpleadosService empleadoService;
	
	@Test
	public void contextLoads() {
		Iterable<Empleado> employees = empleadoService.findAllEmployees();
		
	}

}
