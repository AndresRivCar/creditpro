package co.edu.tdea.api.domain.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="tb_credits")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    @Id
    private String id;
    
    private String creditId;
    
    private String customerId;
    
    private String customerName;
    
    private Double amount;
    
    private String status;
    
    private LocalDate createdAt;
    
    private LocalDate updatedAt;
    
    @PrePersist
    protected void preCreate() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}