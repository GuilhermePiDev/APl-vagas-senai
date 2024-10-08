package com.senai.apimuralvagas.repositorys;

import  java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.senai.apimuralvagas.models.EmpresaModel; 


@Repository
public interface EmpresaRepo extends JpaRepository<EmpresaModel, Integer> {
    Optional<EmpresaModel> findByCnpj(String cnpj);
    
    UserDetails findByEmail(String email);
}
