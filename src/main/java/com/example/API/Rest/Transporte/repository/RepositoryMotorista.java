package com.example.API.Rest.Transporte.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.API.Rest.Transporte.models.peoples.Motorista;

public interface RepositoryMotorista extends JpaRepository<Motorista, Long> {
    
    @Query(value = "SELECT * FROM motoristas WHERE disponivel=true", nativeQuery = true)
    List<Motorista> findByDisponibilidadeDeMotoristas();

    Optional<Motorista> findByEmailAndSenha(String email, String senha);

    Optional<Motorista> findByEmail(String email);

    @Query(value = "SELECT * FROM motoristas WHERE tipo_de_veiculo='onibus' AND disponivel=true", nativeQuery = true)
    Optional<List<Motorista>> findByMotoristasOnibus();

    @Query(value = "SELECT * FROM motoristas WHERE tipo_de_veiculo='carro' AND disponivel=true", nativeQuery = true)
    Optional<List<Motorista>> findByMotoristasCarro();
}
