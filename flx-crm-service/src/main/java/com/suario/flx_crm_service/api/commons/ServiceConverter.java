/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.commons;

import static java.util.stream.StreamSupport.stream;

import java.util.List;
import java.util.stream.Collectors;

public interface ServiceConverter<E, T, R> {
	R toResponse(E model);

	E toModel(T request);

	default List<R> toResponseList(Iterable<E> iterable) {
		return stream(iterable.spliterator(), false).map(this::toResponse).collect(Collectors.toList());
	}
}
