/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.components.customercrm.entity;

import com.suario.flx_crm_service.jpa.utils.DbNames;
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
@Table(name = DbNames.CustomerCrmConst.TABLE_NAME)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CustomerCrmData implements Serializable {

	@Serial
	private static final long serialVersionUID = 672843271162099L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DbNames.CustomerCrmConst.ID, nullable = false)
	private Long id;

	@Column(name = DbNames.CustomerCrmConst.FULL_NAME, length = 90, nullable = false)
	private String fullName;

	@Column(name = DbNames.CustomerCrmConst.CONTACT_EMAIL, length = 120, nullable = false)
	private String contactEmail;

	@Column(name = DbNames.CustomerCrmConst.PRIMARY_PHONE, length = 10, nullable = false)
	private String primaryPhone;

	@Column(name = DbNames.CustomerCrmConst.LOCATION, length = 78, nullable = false)
	private String location;
}
