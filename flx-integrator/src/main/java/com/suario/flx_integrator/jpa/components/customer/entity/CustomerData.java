/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.entity;

import com.suario.flx_integrator.jpa.utils.DbNames;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = DbNames.CustomerConst.TABLE_NAME)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CustomerData implements Serializable {

	@Serial
	private static final long serialVersionUID = 258953029117616L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbNames.CustomerConst.CUSTOMER_ID, nullable = false)
	private Long customerId;

	@Column(name = DbNames.CustomerConst.FIRST_NAME, length = 45, nullable = false)
	private String firstName;

	@Column(name = DbNames.CustomerConst.LAST_NAME, length = 45, nullable = false)
	private String lastName;

	@Column(name = DbNames.CustomerConst.EMAIL, length = 120, nullable = false)
	private String email;

	@Column(name = DbNames.CustomerConst.PHONE_NUMBER, length = 10, nullable = false)
	private String phoneNumber;

	@OneToOne(targetEntity = AddressData.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = DbNames.CustomerConst.ADDRESS_ID, referencedColumnName = DbNames.AddressConst.ID, nullable = false)
	private AddressData address;
}
