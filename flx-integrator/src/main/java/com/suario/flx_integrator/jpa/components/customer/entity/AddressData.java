/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.entity;

import com.suario.flx_integrator.jpa.utils.DbNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = DbNames.AddressConst.TABLE_NAME)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AddressData implements Serializable {

	@Serial
	private static final long serialVersionUID = 126389435038614L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbNames.AddressConst.ID, nullable = false)
	private Long id;

	@Column(name = DbNames.AddressConst.STREET, length = 30, nullable = false)
	private String street;

	@Column(name = DbNames.AddressConst.CITY, length = 20, nullable = false)
	private String city;

	@Column(name = DbNames.AddressConst.STATE, length = 2, nullable = false)
	private String state;

	@Column(name = DbNames.AddressConst.ZIP_CODE, length = 8, nullable = false)
	private String zipCode;
}
