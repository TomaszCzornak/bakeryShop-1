package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    PaymentMethod findAllById(Long id);
    List<PaymentMethod> findAllBy();
}
