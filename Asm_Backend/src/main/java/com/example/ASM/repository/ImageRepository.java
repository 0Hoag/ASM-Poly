package com.example.ASM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ASM.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {}
