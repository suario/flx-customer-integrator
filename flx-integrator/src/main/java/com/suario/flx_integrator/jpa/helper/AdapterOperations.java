/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.helper;

import static java.util.stream.StreamSupport.stream;

import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public abstract class AdapterOperations<E, D, I, R extends CrudRepository<D, I> & QueryByExampleExecutor<D>> {
	protected R repository;
	protected DataConverter<E, D> mapper;
	private final Function<D, E> toEntityFn;

	protected AdapterOperations(R repository, DataConverter<E, D> mapper, Function<D, E> toEntityFn) {
		this.repository = repository;
		this.mapper = mapper;
		this.toEntityFn = toEntityFn;
	}

	protected D toData(E entity) {
		return mapper.toData(entity);
	}

	protected E toEntity(D data) {
		return data != null ? toEntityFn.apply(data) : null;
	}

	public E save(E entity) {
		D data = toData(entity);
		return toEntity(saveData(data));
	}

	protected List<E> saveAllEntities(List<E> entities) {
		List<D> list = entities.stream().map(this::toData).toList();
		return toList(saveData(list));
	}

	public List<E> toList(Iterable<D> iterable) {
		return stream(iterable.spliterator(), false).map(this::toEntity).toList();
	}

	protected D saveData(D data) {
		return repository.save(data);
	}

	protected Iterable<D> saveData(List<D> data) {
		return repository.saveAll(data);
	}

	public E findById(I id) {
		return toEntity(repository.findById(id).orElse(null));
	}

	public List<E> findByExample(E entity) {
		return toList(repository.findAll(Example.of(toData(entity))));
	}

	public List<E> findAll() {
		return toList(repository.findAll());
	}

	public boolean existsById(I id) {
		return repository.existsById(id);
	}

	public void deleteById(I id) {
		repository.deleteById(id);
	}
}
