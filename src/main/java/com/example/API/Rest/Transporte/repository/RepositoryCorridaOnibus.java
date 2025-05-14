package com.example.API.Rest.Transporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.API.Rest.Transporte.models.corridas.CorridaOnibus;

public interface RepositoryCorridaOnibus extends JpaRepository<CorridaOnibus, Long> {
}
