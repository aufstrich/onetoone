package at.rsggr.onetone;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DiscriminatorOptions;

@Data
@Entity
@Table(name = "CUSTOMER_ADDRESS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorOptions(insert = false, force = true)
@IdClass(CustomerAddress.CustomerAddressId.class)
public class CustomerAddress implements Serializable {

  @Id
  Long customerId;
  @Id
  @Enumerated(EnumType.STRING)
  AddressType type;
  String name;
  String street;
  String code;
  String city;

  @OneToOne
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JoinColumn(name = "customerId", referencedColumnName = "id", insertable = false, updatable = false)
  Customer customer;

  @Data
  static class CustomerAddressId implements Serializable {

    Long customerId;
    AddressType type;
  }
}
