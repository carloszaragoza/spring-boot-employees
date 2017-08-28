package com.optimissa.empleados;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.optimissa.empleados.data.entity.Empleado;
import com.optimissa.empleados.data.repository.EmpleadoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpleadosApplicationTests {

	@Autowired
	private EmpleadoRepository repository;
	
	@Test
	public void contextLoads() {
		Empleado employee1 = new Empleado("Ricardo", "Zaragoza", "Solis", "", "");
		repository.save(employee1);
		
		Iterable<Empleado> employees = repository.findAll();
		employees.forEach(e -> {
			System.out.println(e.toString());
		});
		
	}

}
