/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.dtos;

import java.util.List;

public record ListWrapperResponse<D>(List<D> records) {
}
