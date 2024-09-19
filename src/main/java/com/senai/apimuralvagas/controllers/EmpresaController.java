package com.senai.apimuralvagas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apimuralvagas.models.EmpresaModel;
import com.senai.apimuralvagas.services.EmpresaService;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("empresa")
public class EmpresaController {
	EmpresaModel empresa;

	@Autowired
	private EmpresaService empresaService;

	@GetMapping
	public List<EmpresaModel> returnAllEmpresasC() {
		return empresaService.returnAllEmpresas();
	}

	@GetMapping("/{id}") 
	public ResponseEntity<java.lang.Object> returnOneEmpresa(@PathVariable int id) {
		try {
			 empresa = empresaService.returnOneEmpresa(id);
			return new ResponseEntity<>(empresa, HttpStatus.OK); 
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); 
		}
	}

	@PostMapping
	public ResponseEntity<EmpresaModel> criarEmpresa( @RequestBody EmpresaModel empresa) {

		try {
			empresaService.postEmpresa(empresa);
			return new ResponseEntity<>(empresa, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarEmpresa(@PathVariable int id){
		try {
			empresa = empresaService.returnOneEmpresa(id);
			empresaService.deleteEmpresa(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping("/{id}")
    public ResponseEntity<java.lang.Object> patchEmpresa(@Valid @PathVariable int id, @RequestBody EmpresaModel empresaPatch) {
	try {
		empresa = empresaService.updateEmpresaParcial(empresaPatch, id);
		return new ResponseEntity<>(empresa , HttpStatus.OK);
		
	} catch (EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
	}
    }
}